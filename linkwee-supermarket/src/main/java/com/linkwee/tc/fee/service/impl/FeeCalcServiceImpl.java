package com.linkwee.tc.fee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.tc.fee.common.strategy.FeeCalcStrategy;
import com.linkwee.tc.fee.service.FeeCalcService;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.xoss.util.RejectedExecuteRetry;

@Service("feeCalcService")
public class FeeCalcServiceImpl implements FeeCalcService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeeCalcServiceImpl.class);

	@Autowired
	private List<FeeCalcStrategy> feeCalcStrategys;
	
	
	/**
	 * 匹配机构佣金计算策略 并进行计算
	 * @param investRecordWrapper
	 */
	protected void feeCalc(InvestRecordWrapper investRecordWrapper) throws Exception{
		for (FeeCalcStrategy feeCalcStrategy : feeCalcStrategys) {
			if(feeCalcStrategy.matchCalcStrategy(investRecordWrapper)){
				feeCalcStrategy.feeCalc(investRecordWrapper);
			}
		}
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@RejectedExecuteRetry
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		try{
			feeCalc(investRecord);
		}catch(Exception e){
			LOGGER.warn("calculateFee Exception investRecordWrapper={},exception={}", investRecord,e);
			throw e;
		}
	}
	
	


}
