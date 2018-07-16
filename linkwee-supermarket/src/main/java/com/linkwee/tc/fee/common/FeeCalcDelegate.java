package com.linkwee.tc.fee.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.linkwee.tc.fee.common.profitCalculation.ProfitCalculation;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SysPushMessageService;
import com.linkwee.web.service.WeiXinMsgService;
/**
 * 实际执行佣金计算类
 * @author ch
 *
 */
@Component
public class FeeCalcDelegate {
	
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(FeeCalcDelegate.class);
	
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private CrmCfplannerService cfplannerService;
	
	@Autowired
	private TCFeeDetailService feeDetailService;
	
	
	@Autowired
	private List<ProfitCalculation> profitCalculations;
	
	@Autowired
	private SysPushMessageService sysPushMessageService;
	
	@Autowired
	private WeiXinMsgService weiXinMsgService;
	/**
	 * 获取理财师信息
	 * @param cfplannerId 理财师编号
	 */
	protected CrmCfplanner getCfplanner(String cfplannerId){
		if(StringUtils.isBlank(cfplannerId))return null;
		return cfplannerService.queryCfplannerByUserId(cfplannerId);
	}
	
	
	public void feeCalc(InvestRecordWrapper investRecord) throws Exception{
		//查询用户
		CrmInvestor investor = investorService.queryInvestorByUserId(investRecord.getUserId());
		
		if(investor == null){
			LOGGER.warn("calculate  fee investor do not exist investor id ={}", investRecord.getUserId());
			return;
		}
		//查询理财师
		CrmCfplanner cfplanner = getCfplanner(investor.getCfplanner());
		
		if(cfplanner==null){
			LOGGER.warn("calculate  fee cfplanner do not exist cfplanner id ={}", investor.getCfplanner());
			return;
		}
		investRecord.setYearPurAmount(CalculateTools.yearpurAmountCompute(investRecord.getInvestAmt(), investRecord.getProductDays()));
		List<FeedetailWrapper> feedetailWrappers = Lists.newLinkedList();
		for (ProfitCalculation profitCalculation : profitCalculations) {
			List<FeedetailWrapper> results = profitCalculation.calculate(investor, cfplanner, investRecord);
			if(CollectionUtils.isEmpty(results)) continue;
			feedetailWrappers.addAll(results);
		}
		savaFeedetails(feedetailWrappers.toArray(new FeedetailWrapper[0]));
		
		sysPushMessageService.pushAppMessage(feedetailWrappers, investRecord); //一、二、三级团队成员APP消息推送
		weiXinMsgService.pushWeiXinMsg(feedetailWrappers,investRecord); //微信消息推送
		//weiXinMsgService.teamMemberSellSuccessWeiXinMsg(feedetailWrappers,investRecord);//一、二、三级团队成员微信消息推送
		//weiXinMsgService.investSuccesssendWeiXinMsg(feedetailWrappers,investRecord); //微信客户投资成功通知
		/*FeedetailWrapper feedetailWrapper = new FeedetailWrapper();
		setBaseInfo(feedetailWrapper,investRecord);
		setRatioAndRemark(feedetailWrapper, investRecord,cfplanner,investor);
		internalFeeCalc(feedetailWrapper, investRecord);*/
	}
	
	
	/**
	 * 设置佣金明细基础信息
	 * @param feedetailWrapper
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 *//*
	private void setBaseInfo(FeedetailWrapper feedetailWrapper,InvestRecordWrapper investRecord){		
		feedetailWrapper.setBillId(investRecord.getBizId());
		feedetailWrapper.setPlatformRatio(investRecord.getFeeRatio().doubleValue());
		feedetailWrapper.setInvestorId(investRecord.getUserId());
		feedetailWrapper.setProductId(investRecord.getProductId());
		feedetailWrapper.setProductName(investRecord.getProductName());
		feedetailWrapper.setProductOrgId(investRecord.getProductOrgId());
		feedetailWrapper.setInvestmentAmount(investRecord.getInvestAmt());
		feedetailWrapper.setInvestDate(investRecord.getInvestTime());
		feedetailWrapper.setEndDate(investRecord.getEndTime());
	}
	
	*//**
	 * 设置祖先路径
	 * @param feedetailWrapper 明细包装类
	 * @param cfplannerId 理财师编号
	 *//*
	private CrmCfplanner getCfplanner(String cfplannerId){
		if(StringUtils.isBlank(cfplannerId))return null;
		return cfplannerService.queryCfplannerByUserId(cfplannerId);
	}
	
	*//**
	 * 职级奖励
	 * @param levelCode
	 * @return
	 *//*
	private CrmCfpLevelRewardRate getLevelRewardRate(String levelCode){
		CrmCfpLevelRewardRate levelRewardRate = new CrmCfpLevelRewardRate();
		levelRewardRate.setLevelCode(levelCode);
		return levelRewardRateService.selectOne(levelRewardRate);
	}
	
	*//**
	 * 设置佣金率
	 * @param feedetailWrapper 明细包装类
	 * @param investRecord 投资记录包装类
	 *//*
	private void setRatioAndRemark(FeedetailWrapper feedetailWrapper,InvestRecordWrapper investRecord,CrmCfplanner cfplanner,CrmInvestor investor){
		String remark = investRecord.getRemark();
		String productName= investRecord.getProductName();
		BigDecimal amt = investRecord.getInvestAmt();
		String mobile=null,name=null;
		String nweRemark = remark;
		if(StringUtils.isBlank(remark)){
		//理财师描述
		 mobile = investor.getMobile();
		 mobile = StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***");	
		 name = StringUtils.join(new Object[]{investor.getUserName(),mobile},' ');
		 nweRemark = String.format("客户%s购买 %s，金额%s元",name,productName,amt);
		}
		//理财师
		String cfplannerId = cfplanner.getUserId();
		
		CrmCfpLevelRewardRate levelRewardRate = getLevelRewardRate(cfplanner.getJobGrade());
		
		feedetailWrapper.setCfplannerId(cfplannerId);
		feedetailWrapper.setRatio(levelRewardRate.getBaseFeeRate().doubleValue());
		feedetailWrapper.setCfpWeight(cfplanner.getJobGradeWeight());
		feedetailWrapper.setCfplannerRemark(nweRemark);
		
		
		//上级理财师
		cfplannerId =cfplanner.getParentId();
		CrmCfplanner pCfplanner = getCfplanner(cfplannerId);
		if(pCfplanner == null ) return;
		
		levelRewardRate = getLevelRewardRate(pCfplanner.getJobGrade());
		

		if(StringUtils.isBlank(remark)){
			//上级理财师描述
			mobile = cfplanner.getMobile();
			mobile = StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***");	
			name =  StringUtils.join(new Object[]{cfplanner.getUserName(),mobile},' ');
			nweRemark = String.format("团队成员%s销售 %s，金额%s元", name,productName,amt);
		}
		feedetailWrapper.setParentCfplannerId(cfplannerId);
		feedetailWrapper.setParentRatio(levelRewardRate.getRecommendRate().doubleValue());
		feedetailWrapper.setParentWeight(pCfplanner.getJobGradeWeight());
		feedetailWrapper.setParentChildAllowanceRate(levelRewardRate.getChildAllowanceRate().doubleValue());
		feedetailWrapper.setParentChildAllowanceRemark(nweRemark);
		feedetailWrapper.setParentCfplannerRemark(nweRemark);
		
		mobile = pCfplanner.getMobile();
		mobile = StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***");
		name =  StringUtils.join(new Object[]{pCfplanner.getUserName(),mobile},' ');
		
		//上上级理财师
		cfplannerId = pCfplanner.getParentId();
		pCfplanner = getCfplanner(feedetailWrapper,cfplannerId);
		if(pCfplanner == null ) return;
		feedetailWrapper.setGrandParentCfplannerId(cfplannerId);
		feedetailWrapper.setGrandparentratio(feeConfig.getThird_ratio());
		feedetailWrapper.setGrandParentCfplannerRemak(remark==null?String.format("团队成员%s的下级销售 %s，金额%s元", name,productName,amt):remark);
	}

	private void internalFeeCalc(FeedetailWrapper feedetailWrapper,InvestRecordWrapper investRecord) throws Exception {
		
		try {
			
			List<FeedetailWrapper> wrappers = Lists.newArrayListWithCapacity(2);
			//理财师基础佣金
			FeedetailWrapper wrapper = new FeedetailWrapper();
			feedetailWrapper.setYearPurAmount(CalculateTools.yearpurAmountCompute(investRecord.getInvestAmt(), investRecord.getProductDays()));
			BeanUtils.copyProperties(wrapper, feedetailWrapper);
			
			BigDecimal baseFeeAmount = CalculateTools.feeAmountCompute(feedetailWrapper.getYearPurAmount(),feedetailWrapper.getPlatformRatio());
			
			BigDecimal feeAmount = CalculateTools.feeAmountCompute(baseFeeAmount,feedetailWrapper.getRatio());
			wrapper.setFeeamount(feeAmount);
			wrapper.setCurCfplannerId(feedetailWrapper.getCfplannerId());
			wrapper.setCurRatio(feedetailWrapper.getPlatformRatio());
			wrapper.setRemark(feedetailWrapper.getCfplannerRemark());
			wrapper.setFeetype(FEE_TYPE);
			wrappers.add(wrapper);
			
			//上级理财师推荐佣金
			if(StringUtils.isNotBlank(feedetailWrapper.getParentCfplannerId())){
				wrapper = new FeedetailWrapper();
				BeanUtils.copyProperties(wrapper, feedetailWrapper);
				if(!ObjectUtils.equals(investRecord.getFeeRatio(), BigDecimal.ZERO)){
					Double differential = orginfoService.queryOrgDiffFeeRatio(investRecord.getProductOrgId()).doubleValue();
					baseFeeAmount = CalculateTools.feeAmountCompute(feedetailWrapper.getYearPurAmount(),differential);
				}
				wrapper.setFeeamount(CalculateTools.feeAmountCompute(baseFeeAmount, feedetailWrapper.getParentRatio()));
				wrapper.setCurCfplannerId(feedetailWrapper.getParentCfplannerId());
				wrapper.setCurRatio(feedetailWrapper.getParentRatio());
				wrapper.setFeetype(RECOMMEND_FEE_TYPE);
				wrapper.setRemark(feedetailWrapper.getParentCfplannerRemark());
				wrappers.add(wrapper);
				
				//直接管理津贴
				if(feedetailWrapper.getParentWeight()>feedetailWrapper.getCfpWeight() && feedetailWrapper.getParentChildAllowanceRate()>0d){
					wrapper = new FeedetailWrapper();
					BeanUtils.copyProperties(wrapper, feedetailWrapper);
					wrapper.setFeeamount(CalculateTools.feeAmountCompute(baseFeeAmount, feedetailWrapper.getParentChildAllowanceRate()));
					wrapper.setCurCfplannerId(feedetailWrapper.getParentCfplannerId());
					wrapper.setCurRatio(feedetailWrapper.getParentChildAllowanceRate());
					wrapper.setRemark(feedetailWrapper.getParentChildAllowanceRemark());
					wrapper.setFeetype(CHILD_ALLOWANCE_TYPE);
					wrappers.add(wrapper);
				}
			}
			
			
			
			if(StringUtils.isNotBlank( feedetailWrapper.getGrandParentCfplannerId())){
				//二级理财师佣金
				wrapper = new FeedetailWrapper();
				BeanUtils.copyProperties(wrapper, feedetailWrapper);
				wrapper.setFeeamount(CalculateTools.feeAmountCompute(feeAmount, feedetailWrapper.getGrandparentratio()));
				wrapper.setCurCfplannerId(feedetailWrapper.getGrandParentCfplannerId());
				wrapper.setCurRatio(feedetailWrapper.getGrandparentratio());
				wrapper.setFeetype("1002");
				wrapper.setRemak(feedetailWrapper.getGrandParentCfplannerRemak());
				wrappers.add(wrapper);
			}
			savaFeedetails(wrappers.toArray(new FeedetailWrapper[0]));
			weiXinMsgService.investSuccesssendWeiXinMsg(wrappers,investRecord);
		} catch (Exception e) {
			LOGGER.warn("internalExecuteCalcu exception feedetailWrapper={},investRecord={},e={}", new Object[]{feedetailWrapper,investRecord,e});
			throw e;
		} 
	}*/
	
	
	/***
	 * 保存佣金明细
	 * @param feedetailWrappers
	 */
	private void savaFeedetails(FeedetailWrapper... feedetailWrappers){
		
		try {
			feeDetailService.insertFeedetail(feedetailWrappers);
		} catch (Exception e) {
			LOGGER.warn("SavaFeedetail Exception feedetailWrapper = {}", feedetailWrappers);
		} 
	}
}
