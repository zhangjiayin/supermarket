package com.linkwee.tc.fee.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.tc.fee.service.CurrentMonthPayFeeService;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.web.dao.TCFeePayMapper;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;
import com.linkwee.web.service.AcBalanceRecordService;
import com.linkwee.web.service.CimInsuranceFeedetailService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.PushMessageHelper;


public abstract class AbstractCurrentMonthPayFeeService implements CurrentMonthPayFeeService {
	
	@Autowired
	private AcBalanceRecordService  balanceRecordService;
	@Autowired
	protected TCFeeDetailService feeDetailService;
	@Autowired
	private TCFeePayMapper feePayMapper;
	@Resource
	private SmMessageQueueService messageQueueService;
	@Resource
	private SysMsgService sysMsgService;
	@Resource
	private WeiXinMsgService weiXinMsgService;
	@Autowired
	private PushMessageHelper pushMessageHelper;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private CimInsuranceFeedetailService cimInsuranceFeedetailService;
	
	/**
	 * 获取佣金类型
	 * @return
	 */
	protected abstract String getFeeType();
	
	/**
	 * 获取产品类型
	 * @return
	 */
	protected abstract Integer getProductClassify();
	
	/**
	 * 设置佣金佣金统计数据
	 * @param currentMonthPayFeeStatistics
	 * @param feeSummaryBuilder
	 */
	protected abstract void setFeeSummary(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics,FeeSummaryBuilder feeSummaryBuilder);
	
	/**
	 * 获取银行账户记录基础信息
	 * @param month
	 * @return
	 */
	protected abstract AcBalanceRecord getBalanceRecord(String month);

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public CurrentMonthPayFeeStatistics prePayFee(String month,String monthStart, String monthEnd,FeeSummaryBuilder feeSummaryBuilder) throws ServiceException{
		//当前月份佣金数据统计
		CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics = null;
		//佣金类型 1001=佣金|1002=推荐津贴|1005=直接管理津贴|1006=团队管理津贴|1011=基础加拥（平台加佣券）|1012=推荐加拥（平台加佣券）|1021=个人加佣（个人加佣券）
		String feeType = getFeeType();
		//产品类型  0-网贷 1-保险
		Integer productClassify = getProductClassify();
		//当前时间
		Date time = DateTime.now().toDate();
		//人数
		int number = 0;
		//累计佣金
		BigDecimal totalFeeAmt = BigDecimal.ZERO;
		//待结算的佣金
		List<TCFeePay> feePayInfos = null;
		try{
			if(productClassify == 0){//网贷
				feePayInfos = feePayMapper.getPayFeeInfoByCurMonthFeedetail(feeType, monthStart, monthEnd);
			} else if(productClassify == 1){//保险
				feePayInfos = feePayMapper.getInsurancePayFeeInfoByCurMonthFeedetail(feeType, monthStart, monthEnd);
			}
			//统计累计佣金
			if(CollectionUtils.isNotEmpty(feePayInfos)){
				for (Iterator<TCFeePay> iterator = feePayInfos.iterator(); iterator.hasNext();) {
					TCFeePay feePayInfo = iterator.next();
					if(null != feePayInfo && feePayInfo.getFeeAmount().compareTo(BigDecimal.ZERO)>0){
						feePayInfo.setBillId(StringUtils.join(new Object[]{feePayInfo.getUserId(),productClassify,feeType,month},"_"));
						feePayInfo.setMonth(month);
						feePayInfo.setType(feeType);
						feePayInfo.setCreateTime(time);
						feePayInfo.setUpdateTime(time);
						feePayInfo.setProductClassify(productClassify);
						totalFeeAmt = totalFeeAmt.add(feePayInfo.getFeeAmount());
					}else{
						iterator.remove();
					}
				}
				//统计人数
				if(CollectionUtils.isNotEmpty(feePayInfos)){
					number = feePayMapper.insertFeePays(feePayInfos);
				}
			}
			currentMonthPayFeeStatistics = new CurrentMonthPayFeeStatistics(totalFeeAmt, feeType, Integer.valueOf(number));
			
			/**
			 * 设置佣金佣金统计数据
			 */
			setFeeSummary(currentMonthPayFeeStatistics, feeSummaryBuilder);
			return currentMonthPayFeeStatistics;
		}catch(Exception e){
			throw new ServiceException( "结算productClassify= "+productClassify+",feeType="+ feeType +" 类型佣金异常 ",e);
		}
	}

	@Override
	public CurrentMonthPayFeeStatistics payFee(String month,String monthStart,String monthEnd,String operator)throws ServiceException{
		//佣金类型 1001=佣金|1002=推荐津贴|1005=直接管理津贴|1006=团队管理津贴|1011=基础加拥（平台加佣券）|1012=推荐加拥（平台加佣券）|1021=个人加佣（个人加佣券）
		String feeType = getFeeType();
		//产品类型  0-网贷 1-保险
		Integer productClassify = getProductClassify();
		//当前时间
		Date time = DateTime.now().toDate();
		//人数
		int number = 0;
		//累计佣金
		BigDecimal totalFeeAmt = BigDecimal.ZERO;
		//未发放的佣金
		List<TCFeePay> noPayFeeList = null;
		try{
			//未发放的佣金
			noPayFeeList = feePayMapper.getPayFeeByCurMonth(productClassify,feeType, month);
			
			if(CollectionUtils.isNotEmpty(noPayFeeList)){			
				List<Map<String, String>> balanceMaps= Lists.newArrayListWithCapacity(noPayFeeList.size());
				List<String> cfplannerIds= Lists.newArrayListWithCapacity(noPayFeeList.size());
				List<String> billIds= Lists.newArrayListWithCapacity(noPayFeeList.size());
				
				List<AcBalanceRecord> balanceRecords = Lists.newArrayListWithCapacity(noPayFeeList.size());
				AcBalanceRecord balanceRecord = null;
				for (TCFeePay noPayFee : noPayFeeList) {
					
					balanceRecord = getBalanceRecord(month);
					
					balanceRecord.setOrderId(com.linkwee.core.util.StringUtils.getUUID());
					balanceRecord.setSerialNumber(noPayFee.getBillId());
					balanceRecord.setUserType(1);
					balanceRecord.setUserId(noPayFee.getUserId());
					balanceRecord.setUserName(noPayFee.getUserName());
					balanceRecord.setMobile(noPayFee.getUserMobile());
					balanceRecord.setTransAmount(noPayFee.getFeeAmount());
					balanceRecord.setTransDate(time);
					balanceRecord.setCreatePerson(operator);
					balanceRecords.add(balanceRecord);
					totalFeeAmt = totalFeeAmt.add(noPayFee.getFeeAmount());
					
					Map<String, String> balanceMap = Maps.newHashMapWithExpectedSize(4);
					cfplannerIds.add(noPayFee.getUserId());
					billIds.add(noPayFee.getBillId());
					balanceMap.put("userId", noPayFee.getUserId());
					balanceMap.put("balanceNumber", noPayFee.getBillId());
					balanceMaps.add(balanceMap);
				}
				//充值  发放奖励
				balanceRecordService.grantProfit(balanceRecords);
				//更新佣金发放表
				feePayMapper.updateStatus(billIds, 2, "success","成功");
				/**
				 * 根据理财师编号与佣金类型 批量更新佣金明细结算状态
				 */
				if(productClassify == 0){//网贷				
					feeDetailService.batchUpdateBalanceStatusBycfplannerIdAndFeeType(cfplannerIds, feeType, balanceMaps, 2, monthStart, monthEnd);
				} else if(productClassify == 1){//保险
					cimInsuranceFeedetailService.batchUpdateBalanceStatusBycfplannerIdAndFeeType(cfplannerIds, feeType, balanceMaps, 2, monthStart, monthEnd);
				}
				number = Integer.valueOf(balanceRecords.size());
			}
			
			return new CurrentMonthPayFeeStatistics(totalFeeAmt,feeType,number);
		}catch(Exception e){
			throw new ServiceException( "发放 productClassify = "+productClassify+",feeType ="+ feeType +" 类型佣金异常 ",e);
		}
	}
	
	protected String useMessageChannel() {//使用那个短信通道 0是梦网  1是聚合
		String channel = sysConfigService.getValuesByKey("change_message_channel", 1);
		return channel;
	}
	
	protected  String getMonth() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		if (month == 0) {
			//1月份处理去年12月份
			year = year - 1;
			month = 12;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(year).append("年");
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month).append("月");
		return sb.toString();
	}
	

}
