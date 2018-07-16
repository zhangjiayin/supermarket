package com.linkwee.tc.fee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linkwee.tc.fee.common.InsuranceFeeCalcDelegate;
import com.linkwee.tc.fee.service.InsuranceFeeCalcService;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;

@Service("insuranceFeeCalcService")
public class InsuranceFeeCalcServiceImpl implements InsuranceFeeCalcService {
	
	@Resource
	private InsuranceFeeCalcDelegate insuranceFeeCalcDelegate;

	@Override
	public void insuranceInvestRecordProcess(InsuranceInvestRecordWrapper insuranceInvestRecordWrapper) throws Exception{
		// TODO Auto-generated method stub
		insuranceFeeCalcDelegate.feeCalc(insuranceInvestRecordWrapper);
	}

}
