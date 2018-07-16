package com.xiaoniu.account.service;

import java.util.Map;

import com.xiaoniu.account.domain.ReturnRecordReq;
import com.xiaoniu.account.domain.TransferReturnReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.RecordRlt;

/**
 * 
 * @Description: 回款相关接口
 * @author 周锋恒
 * @date 2015年7月25日
 *
 */
public interface IReturnRecordSOAService {	
	
	

	/**
	 * 回款操作，记录回款记录
	 * @param req
	 * @return Map {returnRecordNo：回款记录}
	 */
	public CommonRlt<Map<String, Object>> userReturnRecord(ReturnRecordReq req); 
	

	/**
	 * 转让回款操作，
	 * @return
	 */
	public CommonRlt<RecordRlt> transferReturn(TransferReturnReq req);
	
	
}
