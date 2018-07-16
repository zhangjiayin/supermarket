package com.xiaoniu.account.service;

import com.xiaoniu.account.domain.TransferRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.DebtTransferRlt;

/**
 * 
 * @author 周锋恒
 * 
 * @Date 2015年11月23日
 */
public interface ITransferRecordSOAService {	
	
	

	/**
	 * 债权转让操作
	 * @param req
	 * @return 
	 */
	public CommonRlt<DebtTransferRlt> userTransfer(TransferRecordReq req) ; 
	
	
}
