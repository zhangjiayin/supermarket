package com.xiaoniu.account.service;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.CreateUserAssetReq;
import com.xiaoniu.account.domain.QueryBalanceReq;
import com.xiaoniu.account.domain.QueryUserAssetReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.UserAssetRlt;
import com.xiaoniu.account.domain.result.UserBalanceRlt;

/**
 * 
 * @Description: 用户资产相关操作接口。
 * @author 周锋恒
 * @date 2015年7月29日
 *
 */
public interface IUserAssetSOAService {

	/**
	 * 用户注册是创建用户资产账户
	 * @param req
	 * @return 	ResultMsgEnum.PARAM_ERROR || ResultMsgEnum.SIGN_ERROR ||
	 * 			ResultMsgEnum.USER_EXIST || ResultMsgEnum.SUCCESS || ResultMsgEnum.CREATE_USER_FAILE
	 */
	public CommonRlt<EmptyObject> createUserAsset(CreateUserAssetReq req);
	
	/**
	 * 查询用户资产
	 * @param req
	 * @return CommonRlt.data @see UserAssetRlt
	 */
	public CommonRlt<UserAssetRlt> getUserAsset(QueryUserAssetReq req);
	
	
	/**
	 * 查询用户资立流水记录
	 * @param req
	 * @return CommonRlt.data  @see List<UserBalanceRlt>
	 * 
	 */
	public CommonRlt<PageResult<UserBalanceRlt>> queryBalanceRecord(QueryBalanceReq req);
	
	/**
	 * 查询用户收益统计数据
	 * @param req
	 * @return
	 */
	public CommonRlt<Long> queryBalanceSum(QueryBalanceReq req);
	
}
