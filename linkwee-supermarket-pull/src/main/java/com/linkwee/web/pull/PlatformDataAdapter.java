package com.linkwee.web.pull;

import java.util.List;

import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.model.CimProductRepaymentRecordPull;


public interface PlatformDataAdapter<I ,R> {

	List<CimProductInvestRecordPull>  investRecordAdapter(I investRecord);
	
	List<CimProductRepaymentRecordPull>  repaymentRecordAdapter(R repaymentRecord);
}
