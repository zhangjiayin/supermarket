package com.linkwee.web.service;

import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;


public interface InsuranceInvestRecordAware{
	
	/**
	 * 保险投资记录处理
	 * @param investRecord
	 * @throws Exception
	 */
	void insuranceInvestRecordProcess(InsuranceInvestRecordWrapper insuranceInvestRecordWrapper) throws Exception;
}
