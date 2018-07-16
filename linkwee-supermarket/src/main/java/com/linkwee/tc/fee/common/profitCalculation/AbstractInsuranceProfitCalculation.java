package com.linkwee.tc.fee.common.profitCalculation;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;
import com.linkwee.web.service.CrmCfpLevelRewardRateService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.util.business.ProfitCalculationUtils;

/**
 * 抽象佣金计算接口   佣金   推荐奖励  直接管理津贴
 * @author liqimoon
 *
 */
public abstract class AbstractInsuranceProfitCalculation implements InsuranceProfitCalculation{
	
	@Autowired
	private CrmCfplannerService cfplannerService;
	@Autowired
	private CrmCfpLevelRewardRateService levelRewardRateService;

	@Override
	public List<InsuranceFeedetailWrapper> calculate(CrmInvestor investor,CrmCfplanner cfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper) {
		InsuranceFeedetailWrapper insuranceFeedetailWrapper = ProfitCalculationUtils.getBaseFeedetailWrapper(insuranceInvestRecordWrapper,cfplanner);
		Boolean isExecute = preCalculate(investor, cfplanner, insuranceInvestRecordWrapper, insuranceFeedetailWrapper);
		if(!isExecute) return null;
		internalCalculate( investor,  cfplanner, insuranceInvestRecordWrapper, insuranceFeedetailWrapper);
		afterCalculate(investor, cfplanner, insuranceInvestRecordWrapper, insuranceFeedetailWrapper);
		return Lists.newArrayList(insuranceFeedetailWrapper);
	}
	
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
		return levelRewardRateService.selectOneByLevelCode(levelCode);
	}
	
	/**
	 * 计算之前自定义设置信息
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 * @param feedetailWrapper
	 */
	protected  abstract Boolean preCalculate(CrmInvestor crmInvestor,CrmCfplanner crmCfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper,InsuranceFeedetailWrapper insuranceFeedetailWrapper);

	/**
	 * 内部计算
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 * @param feedetailWrapper
	 */
	protected abstract void internalCalculate(CrmInvestor crmInvestor,CrmCfplanner crmCfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper,InsuranceFeedetailWrapper insuranceFeedetailWrapper);
	
	/**
	 * 计算之后自定义设置信息
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 * @param feedetailWrapper
	 */
	protected void afterCalculate(CrmInvestor crmInvestor,CrmCfplanner crmCfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper,InsuranceFeedetailWrapper insuranceFeedetailWrapper){};

}
