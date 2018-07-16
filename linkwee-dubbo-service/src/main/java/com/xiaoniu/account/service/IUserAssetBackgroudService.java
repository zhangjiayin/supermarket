package com.xiaoniu.account.service;

import java.util.List;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.comm.page.PageResultExt;
import com.xiaoniu.account.domain.GetBalanceReq;
import com.xiaoniu.account.domain.GetTransTypeReq;
import com.xiaoniu.account.domain.GetUserAssetReq;
import com.xiaoniu.account.domain.UserAssetListReq;
import com.xiaoniu.account.domain.UserAssetStatisticReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.TransTypeBackRlt;
import com.xiaoniu.account.domain.result.UserAssetRlt;
import com.xiaoniu.account.domain.result.UserAssetStatisticRlt;
import com.xiaoniu.account.domain.result.UserBalanceRlt;

/**
 * 后台用户账户查询
 * @author 颜彩云
 *
 */
public interface IUserAssetBackgroudService {
	
	/**
	 * 查询用户资产
	 * @param req
	 * @return 
	 */
	public CommonRlt<UserAssetRlt> getUserAsset(GetUserAssetReq req);
	
	
	/**
	 * 查询用户资立流水记录
	 * @param req
	 * @return   
	 * 
	 */
	public CommonRlt<PageResult<UserBalanceRlt>> getBalanceRecord(GetBalanceReq req);
	/**
	 * 分页查询用户资产列表
	 * @param req
	 * @return
	 */
	public CommonRlt<PageResult<UserAssetRlt>> getUserAssetList(UserAssetListReq req);
	
	
	/**
	 * 查询交易类型
	 * @param req
	 * @return
	 */
	public CommonRlt<List<TransTypeBackRlt>> getTransType(GetTransTypeReq req);
	
	/**
	 * 查询用户余额列表
	 * @return
	 */
	public CommonRlt<PageResultExt<UserAssetStatisticRlt>> getToInvestList(UserAssetStatisticReq req);
	
	/**
	 * 查询用户在投金额列表
	 * @return
	 */
	public CommonRlt<PageResultExt<UserAssetStatisticRlt>> getInvestedList(UserAssetStatisticReq req);
}
