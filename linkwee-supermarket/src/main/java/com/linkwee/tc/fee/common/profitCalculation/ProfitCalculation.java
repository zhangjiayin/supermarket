package com.linkwee.tc.fee.common.profitCalculation;

import java.util.List;

import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;

public interface ProfitCalculation {
	
	/**
	 * 收益计算
	 * @param investor 投资人
	 * @param cfplanner 理财师
	 * @param investRecord 投资记录
	 * @return 
	 */
	List<FeedetailWrapper> calculate(CrmInvestor investor,CrmCfplanner cfplanner,InvestRecordWrapper investRecord);

}
