package com.xiaoniu.account.service;

import java.util.Map;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.OutExpectReachTimeReq;
import com.xiaoniu.account.domain.OutRecordReq;
import com.xiaoniu.account.domain.QueryOutRecordReq;
import com.xiaoniu.account.domain.QuerySingleOutRecordReq;
import com.xiaoniu.account.domain.QueryUserOutReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.OutExpectReachTimeRlt;
import com.xiaoniu.account.domain.result.OutRecord2Rlt;
import com.xiaoniu.account.domain.result.OutRecordInfoRlt;
import com.xiaoniu.account.domain.result.UserOutFeeRlt;
import com.xiaoniu.account.domain.result.UserOutSumRlt;

/**
 * 
 * @Description: 提现相关接口
 * @author 周锋恒
 * @date 2015年7月24日
 *
 */
public interface IOutRecordSOAService {	
	
	
	/**
	 * 用户提现，记录提现记录
	 * @param req
	 * @return Map{outRecordNo：提现流水号}
	 */
	public CommonRlt<Map<String, String>> userWithdrawRecord(OutRecordReq req); 
	
	
	/**
	 * 查询用户提现总金额和提现中的金额
	 * @param req
	 * @return
	 */
	public CommonRlt<UserOutSumRlt> getUserOutSum(QueryUserOutReq req);
	
	/** 
	 * 查询用户手续费情况
	 * @param req
	 * @return
	 */
	public CommonRlt<UserOutFeeRlt> getUserFee(QueryUserOutReq req);
	

	/**
	 * 查询提现记录
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<OutRecord2Rlt>> outRecordListPage(QueryOutRecordReq req);

	/**
	 * 查询提现预计到账时间
	 * @param req
	 * @return
	 */
	public CommonRlt<OutExpectReachTimeRlt> outRecordExpectedReachTime(OutExpectReachTimeReq req);

	/**
	 * 查询提现记录
	 * @param req
	 * @return
	 */
	public CommonRlt<OutRecordInfoRlt> querySingleOutRecord(QuerySingleOutRecordReq req);
}
