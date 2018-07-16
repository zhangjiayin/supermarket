package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.openApi.enums.RequestTypeEnums;
import com.linkwee.openApi.request.InvestRecordReq;
import com.linkwee.openApi.request.JfqzInvestPushReq;
import com.linkwee.openApi.request.RepaymentRecordReq;
import com.linkwee.openApi.utils.OpenApiCommonUtils;
import com.linkwee.web.dao.CimJfqzPushRecordMapper;
import com.linkwee.web.dao.CimProductInvestRecordPushMapper;
import com.linkwee.web.dao.SysErrorLogMapper;
import com.linkwee.web.model.CimJfqzPushRecord;
import com.linkwee.web.model.CimProductInvestRecordPush;
import com.linkwee.web.model.SysErrorLog;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.push.vo.ResultVO;
import com.linkwee.web.service.CimJfqzPushRecordService;
import com.linkwee.web.service.CimProductInvestRecordPushService;
import com.linkwee.web.service.SysErrorLogService;
import com.linkwee.web.service.SysThirdkeyConfigPullService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.util.ThreadpoolService;


 /**
 * 
 * @描述：CimProductInvestRecordPullService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductInvestRecordPushService")
public class CimProductInvestRecordPushServiceImpl implements CimProductInvestRecordPushService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductInvestRecordPushServiceImpl.class);
	
	@Resource
	private CimProductInvestRecordPushMapper cimProductInvestRecordPullMapper;
	@Resource
	private CimJfqzPushRecordService cimJfqzPushRecordService;
	@Resource
    private CimJfqzPushRecordMapper CimJfqzPushRecordMapper;
	@Resource
	private SysErrorLogMapper sysErrorLogMapper;
	@Resource
	private SysErrorLogService sysErrorLogService;
	@Resource
	private ConfigHelper configHelper;
	@Resource
	private SysThirdkeyConfigPullService thirdkeyConfigPullService;
	/**
	 * 请求方式   需根据具体的请求方式作修改
	 */
	private static RequestTypeEnums openRequestType = RequestTypeEnums.POST;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int inserts(List<CimProductInvestRecordPush> investRecords) {
		if(CollectionUtils.isEmpty(investRecords))return 0;
		List<String> investRecordIds = cimProductInvestRecordPullMapper.getInvestRecordIds(investRecords);
		if(CollectionUtils.isNotEmpty(investRecordIds)){
			Set<String> investRecordIdsSet = Sets.newHashSet(investRecordIds);
			List<CimProductInvestRecordPush> investRecordTemps = Lists.newArrayListWithCapacity(investRecords.size() - investRecordIds.size());
			for (CimProductInvestRecordPush investRecord : investRecords) {
				if(investRecordIdsSet.contains(investRecord.getInvestId()))continue;
				investRecordTemps.add(investRecord);
			}
			if(CollectionUtils.isEmpty(investRecordTemps)) return 0;
			investRecords = investRecordTemps;
		}
		return cimProductInvestRecordPullMapper.inserts(investRecords);
	}

	@Override
	public List<CimProductInvestRecordPush> getInvestRecord() {
		return cimProductInvestRecordPullMapper.getInvestRecord();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updateInvestRecordInStatus(Integer[] preStatus,Integer afterStatus,String msg) {
		return cimProductInvestRecordPullMapper.updateInvestRecordInStatus(preStatus, afterStatus, msg);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updatePushInvestRecordStatus(List<ResultVO> results,Integer status) {
		return cimProductInvestRecordPullMapper.updatePushInvestRecordStatus(results, status);
	}

	@Override
	public BigDecimal getStockInvestAmt(String investId) {
		return cimProductInvestRecordPullMapper.getStockInvestAmt(investId);
	}

	@Override
	public void createOrderJFQZ(JfqzInvestPushReq req) {
		CimJfqzPushRecord t = new CimJfqzPushRecord();
		t.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
		CimJfqzPushRecord ret = CimJfqzPushRecordMapper.selectOneByCondition(t);
		if(ret==null){
			CimJfqzPushRecord record = new CimJfqzPushRecord();
			record.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
			record.setCode(req.getCode());
			record.setAmount(new BigDecimal(req.getJfqzInvestRecord().getAmount()));
			record.setCreateTime(new Date());
			record.setIssuePreiod(req.getJfqzInvestRecord().getIssuePeriod());
			record.setProductId(req.getJfqzInvestRecord().getProductId()+"_"+req.getJfqzInvestRecord().getIssuePeriod());
			record.setRate(new BigDecimal(req.getJfqzInvestRecord().getProfit()));
			record.setSpendTime(new Date(req.getJfqzInvestRecord().getSpendTime()));
			record.setPeriod(req.getJfqzInvestRecord().getPeriod());
			record.setUserId(req.getJfqzInvestRecord().getUserId());
			record.setExprieProcessMode(req.getJfqzInvestRecord().getExprieProcessMode());
			record.setExpctedEarning(req.getJfqzInvestRecord().getExpctedEarning());
			record.setInvestId(StringUtils.getUUID());
			CimJfqzPushRecordMapper.insertSelective(record);
		}else{
			LOGGER.info("JFQZ创建订单接口【{}】重复推送",req.getJfqzInvestRecord().getOrderNo());
			LOGGER.info("JFQZ创建订单接口【{}】重复推送：investRecordReq={}",req.getJfqzInvestRecord().getOrderNo(),req);
			LOGGER.info("JFQZ创建订单接口【{}】重复推送",req.getJfqzInvestRecord().getOrderNo());
		}
	}

	@Override
	public void createPayOrderJFQZ(JfqzInvestPushReq req) {
		CimJfqzPushRecord t = new CimJfqzPushRecord();
		t.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
		CimJfqzPushRecord ret = CimJfqzPushRecordMapper.selectOneByCondition(t);
		//有下单记录，先修改jfqz数据库，在将记录推送到supermark
		if(ret!=null&&ret.getId()!=null){
			t.setId(ret.getId());
			t.setPayAmount(req.getJfqzInvestRecord().getPayAmount());
			t.setBankName(req.getJfqzInvestRecord().getBankName());
			t.setBankCardNo(req.getJfqzInvestRecord().getBankCardNo());
//			t.setInterestTime(new Date(req.getJfqzInvestRecord().getInterestTime()));//2018-05-17去掉
//			t.setRedemptionTime(new Date(req.getJfqzInvestRecord().getRedemptionTime()));//2018-05-17去掉
			t.setBankName(req.getJfqzInvestRecord().getBankName());
			t.setBankCardNo(req.getJfqzInvestRecord().getBankCardNo());
			t.setUpdateTime(new Date());
			t.setCode(req.getCode());
			t.setRepaymentId("OPEN_JIUFUQINGZHOU_WEB_"+StringUtils.getUUID());
			CimJfqzPushRecordMapper.updateByPrimaryKeySelective(t);
			//执行推送
			pushInvestRecordSupermarket(ret,t);
		}else{
			//查不到之前的下单记录程序出现异常了
			SysErrorLog sysError = new SysErrorLog();
			sysError.setCreateTime(new Date());
			sysError.setMethod("订单支付成功接口JFQZ_002");
			sysError.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
			sysError.setReqParm(req.toString());
			sysError.setRemark(req.getCode()+"查不到之前的下单记录");
			sysErrorLogService.insert(sysError);
		}
	}
	
	//执行推送购买记录到supermarket
	public void pushInvestRecordSupermarket(final CimJfqzPushRecord ret,final CimJfqzPushRecord t){
		ThreadpoolService.execute(new Runnable() {			
			@Override
			public void run() {
				InvestRecordReq investRecordReq = new InvestRecordReq();
				investRecordReq.setInvestId(ret.getInvestId());
				investRecordReq.setUserId(ret.getUserId());
				investRecordReq.setTxId(ret.getOrderNo());
				System.out.println("============================>>>>");
				System.out.println("============================>>>>");
				investRecordReq.setInvestStartTime(DateUtils.format(DateUtils.addDay(new Date(), 0)));//取的是用户下单时间,才能同步投资日历里面的时间
				investRecordReq.setInvestEndTime(DateUtils.format(DateUtils.addDay(new Date(),Integer.parseInt(ret.getPeriod()))) );
				System.out.println("============================"+DateUtils.format(DateUtils.addDay(new Date(),Integer.parseInt(ret.getPeriod()))));
				System.out.println("============================>>>>>");
				System.out.println("============================>>>>>");
				investRecordReq.setProductId(ret.getProductId());
				investRecordReq.setInvestAmount(t.getPayAmount());
				investRecordReq.setProfit(ret.getExpctedEarning());
				investRecordReq.setPlatfromId("OPEN_JIUFUQINGZHOU_WEB");
				LOGGER.info("JFQZ投资记录推送接口：investRecordReq={}",JSONObject.toJSONString(investRecordReq));
				Map<String, String> configMap = configHelper.getValuesByType(0, "LINKWEE");
				String linkweeBaseUrL = configMap.get("linkweeBaseUrL");
				SysThirdkeyConfigPull thirdkeyConfig = new SysThirdkeyConfigPull();
				thirdkeyConfig.setOrgNumber("OPEN_JIUFUQINGZHOU_WEB");
				thirdkeyConfig = thirdkeyConfigPullService.selectOne(thirdkeyConfig);
				String result = OpenApiCommonUtils.httpRequest(thirdkeyConfig,openRequestType,linkweeBaseUrL+"/invest/pushInvestRecord",investRecordReq);//发送请求
				CimJfqzPushRecord update = new CimJfqzPushRecord();
				update.setRemark(result);
				update.setId(t.getId());
				update.setUpdateStatus(result.indexOf("errors") != -1?"2":"0");
				CimJfqzPushRecordMapper.updateByPrimaryKeySelective(update);
			}
		});
	}

	@Override
	public void createRepaymentOrderJFQZ(JfqzInvestPushReq req) {
		CimJfqzPushRecord t = new CimJfqzPushRecord();
		t.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
		CimJfqzPushRecord ret = CimJfqzPushRecordMapper.selectOneByCondition(t);
		if(ret!=null&&ret.getId()!=null){
			t.setId(ret.getId());
			t.setUpdateTime(new Date());
			t.setCode(req.getCode());
			t.setSubOrderNo(req.getJfqzInvestRecord().getSubBankCardNo());
			t.setCashTime(new Date(req.getJfqzInvestRecord().getCashTime()));
			t.setEarnings(req.getJfqzInvestRecord().getEarnings());
			CimJfqzPushRecordMapper.updateByPrimaryKeySelective(t);
			
			//将回款记录推送到supermarket
			pushRepaymentRecordSupermarket(ret,t);
		}else{
			//查不到之前的下单记录程序出现异常了
			SysErrorLog sysError = new SysErrorLog();
			sysError.setCreateTime(new Date());
			sysError.setMethod("回款接口JFQZ_003");
			sysError.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
			sysError.setReqParm(req.toString());
			sysError.setRemark(req.getCode()+"查不到之前的下单记录");
			sysErrorLogService.insert(sysError);
		}
	}

	//执行推送回款到supermarket
	private void pushRepaymentRecordSupermarket(final CimJfqzPushRecord ret, final CimJfqzPushRecord t) {
		ThreadpoolService.execute(new Runnable() {			
			@Override
			public void run() {
				RepaymentRecordReq repaymentRecordReq = new RepaymentRecordReq();
				repaymentRecordReq.setUserId(ret.getUserId());
				repaymentRecordReq.setRepaymentId(ret.getRepaymentId());//再次确认
				repaymentRecordReq.setInvestId(ret.getInvestId());
				repaymentRecordReq.setProductId(ret.getProductId());
				repaymentRecordReq.setRepaymentAmount(t.getEarnings());
				repaymentRecordReq.setProfit(ret.getExpctedEarning());//跟肖建宇再次确认
				repaymentRecordReq.setRepaymentTime(DateUtils.format(t.getCashTime()));
				repaymentRecordReq.setStatus(3);//回款成功  再次确认
				LOGGER.info("JFQZ投资回款推送接口：repaymentRecordReq={}",JSONObject.toJSONString(repaymentRecordReq));
				Map<String, String> configMap = configHelper.getValuesByType(0, "LINKWEE");
				String linkweeBaseUrL = configMap.get("linkweeBaseUrL");
				SysThirdkeyConfigPull thirdkeyConfig = new SysThirdkeyConfigPull();
				thirdkeyConfig.setOrgNumber("OPEN_JIUFUQINGZHOU_WEB");
				thirdkeyConfig = thirdkeyConfigPullService.selectOne(thirdkeyConfig);
				String result = OpenApiCommonUtils.httpRequest(thirdkeyConfig,openRequestType,linkweeBaseUrL+"/invest/pushRepaymentRecord",repaymentRecordReq);
				CimJfqzPushRecord update = new CimJfqzPushRecord();
				update.setRemark(result);
				update.setId(t.getId());
				update.setUpdateStatus(result.indexOf("errors") != -1?"2":"0");
				CimJfqzPushRecordMapper.updateByPrimaryKeySelective(update);
			}
		});
	}

	@Override
	public void createRedemptionTimeJFQZ(JfqzInvestPushReq req) {
		CimJfqzPushRecord t = new CimJfqzPushRecord();
		t.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
		CimJfqzPushRecord ret = CimJfqzPushRecordMapper.selectOneByCondition(t);
		//有下单记录，先修改jfqz数据库，在将记录推送到supermark
		if(ret!=null&&ret.getId()!=null){
			t.setId(ret.getId());
			t.setInterestTime(new Date(req.getJfqzInvestRecord().getInterestTime()));//2018-05-17添加
			t.setRedemptionTime(new Date(req.getJfqzInvestRecord().getRedemptionTime()));//2018-05-17添加
			CimJfqzPushRecordMapper.updateByPrimaryKeySelective(t);
		}else{
			//查不到之前的下单记录程序出现异常了
			SysErrorLog sysError = new SysErrorLog();
			sysError.setCreateTime(new Date());
			sysError.setMethod("计息时间到期时间接口JFQZ_011");
			sysError.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
			sysError.setReqParm(req.toString());
			sysError.setRemark(req.getCode()+"查不到之前的下单记录");
			sysErrorLogService.insert(sysError);
		}
		//同步到supermarket
		
	}

    
 

}
