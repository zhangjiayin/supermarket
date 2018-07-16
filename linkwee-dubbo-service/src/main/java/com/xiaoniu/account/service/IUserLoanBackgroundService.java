package com.xiaoniu.account.service;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.QueryLoanRecordReq;
import com.xiaoniu.account.domain.QueryUserLoanReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.LoanRecordRlt;
import com.xiaoniu.account.domain.result.UserLoanRlt;

/**
 * 
 * @Description: 用户借贷资产相关操作实现类
 * @author 颜彩云
 * @date 2016年5月19日
 *
 */
public interface IUserLoanBackgroundService {
	
	/**
	 * 查询用户借贷资产
	 * @param req
	 * @return CommonRlt.data @see UserAssetRlt
	 */
	public CommonRlt<UserLoanRlt> getUserLoan(QueryUserLoanReq req);
	
	
	/**
	 * 查询用户借贷资产流水记录
	 * @param req
	 * @return CommonRlt.data  @see List<UserBalanceRlt>
	 * 
	 */
	public CommonRlt<PageResult<LoanRecordRlt>> queryLoanRecord(QueryLoanRecordReq req);
	
	
}
