package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.dao.SysPushMessageMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.mc.SysPushMessage;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysPushMessageService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;


 /**
 * 
 * @描述：SysPushMessageService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月25日 16:17:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("sysPushMessageService")
public class SysPushMessageServiceImpl extends GenericServiceImpl<SysPushMessage, Long> implements SysPushMessageService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysPushMessageServiceImpl.class);
	
	@Autowired
	private SysPushMessageMapper sysPushMessageMapper;
	
	@Autowired
	private PushMessageHelper pushMessageHelper; //app推送
	
	@Autowired
	private ConfigHelper configHelper;
	
	@Autowired
	private CrmUserInfoService userInfoService;
	
	@Override
    public GenericDao<SysPushMessage, Long> getDao() {
        return sysPushMessageMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SysPushMessage -- 排序和模糊查询 ");
		Page<SysPushMessage> page = new Page<SysPushMessage>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SysPushMessage> list = this.sysPushMessageMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<SysPushMessage> querySysPushMessageList(
			Page<SysPushMessage> page, Map<String, Object> conditions) {
		PaginatorResponse<SysPushMessage> paginatorResponse = new PaginatorResponse<SysPushMessage>();
		List<SysPushMessage> queryCimOrginfoList = sysPushMessageMapper.querySysPushMessageList(page,conditions);
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Integer renewBatch(List<SysPushMessage> list) {
		return sysPushMessageMapper.updateBatch(list);				
	}

	@Override
	public Integer saveBatch(List<SysPushMessage> sysPushMessage) {
		return sysPushMessageMapper.saveBatch(sysPushMessage);		
	}
	
	/**
	 * 团队成员推送
	 */
	public void pushAppMessage(final List<FeedetailWrapper> feedetails,final InvestRecordWrapper inRecord){
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				Map<String, FeedetailWrapper> maps = Maps.newHashMap();
				//lowType//下级类型(0=默认|1=下级2=下下级)
				for(FeedetailWrapper feeDetail :feedetails){
					// 佣金类型。《 1001：基础佣金, 1002： 推荐奖励,  1005： 直接管理津贴,  1006：团队管理津贴》
					String  key = StringUtils.join(new Object[]{feeDetail.getFeetype(),feeDetail.getLowType()});
					maps.put(key, feeDetail);
					pushCustomerBuySuccessMessage(feeDetail,inRecord); //客户购买成功推送消息到理财师			
				}
				
				/**
				 * 下列条件满足其一即可推送团队消息
				 * 1:产品非募集
				 * 2:产品募集且募集成功
				 */
				if(inRecord.getIsCollect() == 1 || (inRecord.getIsCollect() == 2 && inRecord.getStatus() == 2)){				
					pushTeamMessage(maps,inRecord);
				}
				
			}
		});
	}
	
	
	private void pushTeamMessage(Map<String, FeedetailWrapper> maps,InvestRecordWrapper inRecord){
	
		FeedetailWrapper feedetailWrapper_1002 = maps.get("1002"); //推荐奖励
		FeedetailWrapper feedetailWrapper_10060 = maps.get("10060"); //团队成员津贴
		StringBuilder sb = new StringBuilder();
		if(feedetailWrapper_10060 == null){
			if(feedetailWrapper_1002 != null && feedetailWrapper_1002.getFeeamount().compareTo(BigDecimal.ZERO) == 1){//推荐奖励>0推送(返回值：0=等于、-1小于、1=大于)
				StringBuilder recommendReward = new StringBuilder(String.format("恭喜您,您的%s，您将获得",feedetailWrapper_1002.getRemark()));
				recommendReward.append(String.format("%s元推荐奖励。",feedetailWrapper_1002.getFeeamount().setScale(2,BigDecimal.ROUND_DOWN).toString()));
				//推送消息
				pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
						SmsTypeEnum.LGRADEONESALE, //点击跳转链接
						feedetailWrapper_1002.getProfitCfplannerId(), 
						"团队一级成员销售成功", recommendReward.toString(), null, true);
			}
			
		}else{ //一级团队成员津贴
				sb.append(String.format("恭喜您,您的团队成员%s，您将获得",feedetailWrapper_10060.getRemark()));
				sb.append(String.format("%s元推荐奖励",feedetailWrapper_1002.getFeeamount().setScale(2,BigDecimal.ROUND_DOWN).toString()));
				FeedetailWrapper feedetailWrapper_1005 = maps.get("1005");
				if(feedetailWrapper_1005 != null && feedetailWrapper_1005.getFeeamount().compareTo(BigDecimal.ZERO) == 1){
					sb.append(String.format("、%s元直接管理津贴",feedetailWrapper_1005.getFeeamount().setScale(2,BigDecimal.ROUND_DOWN).toString()));
				}
				
				if(feedetailWrapper_10060.getFeeamount().compareTo(BigDecimal.ZERO) == 1){
					sb.append(String.format("、%s元团队管理津贴",feedetailWrapper_10060.getFeeamount().setScale(2,BigDecimal.ROUND_DOWN).toString()));
				}
				sb.append("。");
				//推送消息
				pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
						SmsTypeEnum.LGRADEONESALE, //点击跳转链接
						feedetailWrapper_10060.getProfitCfplannerId(), 
						"团队一级成员销售成功", sb.toString(), null, true);
			}
			
		
		
		FeedetailWrapper feedetailWrapper_10061 = maps.get("10061");
		if(feedetailWrapper_10061 != null){ //二级团队成员
			String content = String.format("恭喜您,您的团队成员%s，您将获得%s元团队管理津贴。",feedetailWrapper_10061.getRemark(),feedetailWrapper_10061.getFeeamount().setScale(2,BigDecimal.ROUND_DOWN).toString());
			//推送消息
			pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
					SmsTypeEnum.LGRADETWOSALE, //点击跳转链接
					feedetailWrapper_10061.getProfitCfplannerId(), 
					"团队二级成员销售成功", content, null, true);
		}
		FeedetailWrapper feedetailWrapper_10062 = maps.get("10062");
		if(feedetailWrapper_10062 != null){ //三级团队成员
			String content = String.format("恭喜您,您的团队成员%s，您将获得%s元团队管理津贴。",feedetailWrapper_10062.getRemark(),feedetailWrapper_10062.getFeeamount().setScale(2,BigDecimal.ROUND_DOWN).toString());
			//推送消息
			pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
					SmsTypeEnum.LGRADETHREESALE, //点击跳转链接
					feedetailWrapper_10062.getProfitCfplannerId(), 
					"团队三级成员销售成功", content, null, true);
		}
	}
	

	/**
	 * 推送客户购买成功消息到理财师
	 * @author yalin 
	 * @date 2017年4月10日 下午5:47:21  
	 * @param key
	 * @param feeDetail
	 */
	private void pushCustomerBuySuccessMessage(FeedetailWrapper feeDetail,InvestRecordWrapper inRecord){
		//feetype; 1001：基础佣金, 1002： 推荐奖励,  1005： 直接管理津贴 , 1006：团队管理津贴
		if(null == feeDetail || !feeDetail.getFeetype().equals("1001")){
			return;
		}
		
		CrmUserInfo investor = userInfoService.queryUserInfoByUserId(feeDetail.getInvestorId()); //投资用户信息
		if(investor == null){
			return;
		} else {		
			String userNameMsg = investor.getUserName()!=null?investor.getUserName():"" + investor.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
			String productName = feeDetail.getProductName() != null ? feeDetail.getProductName():""; //产品名称
			String investmentAmount = feeDetail.getInvestmentAmount() != null ? NumberUtils.getFormat(feeDetail.getInvestmentAmount(), "0.00"):""; //投资金额
			String feeamount = feeDetail.getFeeamount() != null ? NumberUtils.getFormat(feeDetail.getFeeamount(), "0.00"):""; //基础佣金
			String orgName = inRecord.getOrgName();//机构名称
			
			/**
			 * 1:非募集产品
			 * 2:募集产品且募集成功
			 */
			if( inRecord.getIsCollect() == 1 || (inRecord.getIsCollect() == 2 && inRecord.getStatus() == 2)){			
				String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCUSTOMERBUY); //模板配置
				String content = String.format(contentTemp,userNameMsg ,productName,investmentAmount,feeamount); 
				pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
						SmsTypeEnum.LCUSTOMERBUY,//点击跳转链接
						feeDetail.getProfitCfplannerId(), //收益理财师
						"客户购买成功", content, null, true);
			} else if(inRecord.getIsCollect() == 2 && inRecord.getStatus() == 1){ //募集产品募集中
				/*String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCUSTOMERBUY_ONCOLLECT); //模板配置
				String content = String.format(contentTemp,userNameMsg,productName,investmentAmount); 
				pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
						SmsTypeEnum.LCUSTOMERBUY,//点击跳转链接
						feeDetail.getProfitCfplannerId(), //收益理财师
						"客户购买成功", content, null, true);*/
			} else if(inRecord.getIsCollect() == 2 && inRecord.getStatus() == 3){ //募集产品募集失败
				String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCUSTOMERBUY_COLLECTFAIL); //模板配置
				String content = String.format(contentTemp,userNameMsg,productName,investmentAmount,orgName); 
				pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
						SmsTypeEnum.LCUSTOMERBUY,//点击跳转链接
						feeDetail.getProfitCfplannerId(), //收益理财师
						"客户购买成功", content, null, true);
			}
		}
	}

}
