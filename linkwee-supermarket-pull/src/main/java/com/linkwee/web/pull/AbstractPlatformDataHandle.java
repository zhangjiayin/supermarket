package com.linkwee.web.pull;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.service.CimProductInvestRecordPullService;
import com.linkwee.web.service.CimProductRepaymentRecordPullService;

public abstract class AbstractPlatformDataHandle<I,R> implements PlatformDataPull,PlatformDataAdapter<I, R>{
	
	@Autowired
	private CimProductInvestRecordPullService investRecordPullService;
	
	@Autowired
	private CimProductRepaymentRecordPullService repaymentRecordPullService;
	

	@Override
	public void pullInvestRecord(String orgNumber,String startTime,String endTime) throws Throwable {
		List<I> datas = getInvestRecord(orgNumber,startTime,endTime);
		if(CollectionUtils.isEmpty(datas))return;
		List<CimProductInvestRecordPull> investRecords = Lists.newArrayListWithCapacity(datas.size());
		for (I i : datas) {
			investRecords.addAll(investRecordAdapter(i));
		}
		investRecordPullService.inserts(investRecords);
	}
	
	protected abstract List<I> getInvestRecord(String orgNumber,String startTime,String endTime)throws Throwable;
	

	@Override
	public void pullRepaymentRecord(String orgNumber,String startTime,String endTime) throws Throwable {
		List<R> datas = getRepaymentRecord(orgNumber,startTime,endTime);
		if(CollectionUtils.isEmpty(datas))return;
		List<CimProductRepaymentRecordPull> repaymentRecords = Lists.newArrayListWithCapacity(datas.size());
		for (R r : datas) {
			List<CimProductRepaymentRecordPull> repaymentRecordList = repaymentRecordAdapter(r);
			if(CollectionUtils.isEmpty(repaymentRecordList))continue;
			repaymentRecords.addAll(repaymentRecordList);
		}
		if(CollectionUtils.isEmpty(repaymentRecords))return;
		repaymentRecordPullService.inserts(repaymentRecords);	
	}
	
	protected abstract List<R> getRepaymentRecord(String orgNumber,String startTime,String endTime)throws Throwable;

}
