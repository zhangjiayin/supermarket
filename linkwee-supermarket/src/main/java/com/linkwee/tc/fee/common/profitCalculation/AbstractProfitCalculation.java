package com.linkwee.tc.fee.common.profitCalculation;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CrmCfpLevelRewardRateService;
import com.linkwee.web.service.CrmCfplannerService;

public abstract class AbstractProfitCalculation implements ProfitCalculation{
	
	@Autowired
	private CrmCfplannerService cfplannerService;
	
	@Autowired
	private CrmCfpLevelRewardRateService levelRewardRateService;
	
	
	/**
	 * 获取理财师信息
	 * @param cfplannerId 理财师编号
	 */
	protected CrmCfplanner getCfplanner(String cfplannerId){
		if(StringUtils.isBlank(cfplannerId))return null;
		return cfplannerService.queryCfplannerByUserId(cfplannerId);
	}
	
	/**
	 * 职级奖励
	 * @param levelCode
	 * @return
	 */
	protected CrmCfpLevelRewardRate getLevelRewardRate(String levelCode){
		CrmCfpLevelRewardRate levelRewardRate = new CrmCfpLevelRewardRate();
		levelRewardRate.setLevelCode(levelCode);
		return levelRewardRateService.selectOne(levelRewardRate);
	}

	
	protected FeedetailWrapper getBaseFeedetailWrapper(InvestRecordWrapper investRecord){	
		FeedetailWrapper feedetailWrapper = new FeedetailWrapper();
		feedetailWrapper.setBillId(investRecord.getBizId());
		
		feedetailWrapper.setInvestorId(investRecord.getUserId());
	
		feedetailWrapper.setProductOrgId(investRecord.getProductOrgId());
		
		feedetailWrapper.setProductId(investRecord.getProductId());
		feedetailWrapper.setProductName(investRecord.getProductName());
		
		feedetailWrapper.setInvestmentAmount(investRecord.getInvestAmt());
		feedetailWrapper.setYearPurAmount(investRecord.getYearPurAmount());
		
		feedetailWrapper.setInvestDate(investRecord.getInvestTime());
		feedetailWrapper.setEndDate(investRecord.getEndTime());
		
		return feedetailWrapper;
	}
	
	
	@Override
	public List<FeedetailWrapper> calculate(CrmInvestor investor, CrmCfplanner cfplanner,InvestRecordWrapper investRecord) {
		FeedetailWrapper feedetailWrapper = getBaseFeedetailWrapper(investRecord);
		Boolean isExecute = preCalculate(investor, cfplanner, investRecord, feedetailWrapper);
		if(!isExecute) return null;
		internalCalculate( investor,  cfplanner, investRecord, feedetailWrapper);
		afterCalculate(investor, cfplanner, investRecord, feedetailWrapper);
		return Lists.newArrayList(feedetailWrapper);
	}

	/**
	 * 计算之前自定义设置信息
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 * @param feedetailWrapper
	 */
	protected  Boolean preCalculate(CrmInvestor investor,CrmCfplanner cfplanner,InvestRecordWrapper investRecord,FeedetailWrapper feedetailWrapper){ return Boolean.TRUE; }

	/**
	 * 内部计算
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 * @param feedetailWrapper
	 */
	protected abstract void internalCalculate(CrmInvestor investor,CrmCfplanner cfplanner,InvestRecordWrapper investRecord,FeedetailWrapper feedetailWrapper);
	
	/**
	 * 计算之后自定义设置信息
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 * @param feedetailWrapper
	 */
	protected void afterCalculate(CrmInvestor investor,CrmCfplanner cfplanner,InvestRecordWrapper investRecord,FeedetailWrapper feedetailWrapper){};
	
}
