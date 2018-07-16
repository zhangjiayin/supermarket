package com.linkwee.tc.fee.common.profitCalculation;

import java.util.List;

import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;

public interface InsuranceProfitCalculation {
	
	/**
	 * 收益计算
	 * @param investor 投资人
	 * @param cfplanner 理财师
	 * @param insuranceFeedetailWrapper 保险投资记录
	 * @return 
	 */
	List<InsuranceFeedetailWrapper> calculate(CrmInvestor crmInvestor,CrmCfplanner crmCfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper);

}
