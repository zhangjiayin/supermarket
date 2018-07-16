package com.xiaoniu.account.service;

import com.xiaoniu.account.domain.UserAuthenticationReq;
import com.xiaoniu.account.domain.UserQueryAuthReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.UserAuthenticationRlt;

/**
 * 
 * @Description: 用户认证信息相关操作
 * @author 周锋恒
 * @date 2015年8月5日
 *
 */
public interface IUserAuthenticationService {

	/**
	 * 保存用户认证信息
	 * @param req
	 * @return
	 */
	public CommonRlt<EmptyObject> saveAuthentication(UserAuthenticationReq req);
	
	
	/**
	 * 查询用户认证信息
	 * @param userId
	 * @return
	 */
	public CommonRlt<UserAuthenticationRlt> queryAuthentication(String userId);
	
	
	/**
	 * 查询用户认证信息
	 * @param userId
	 * @return
	 */
	public CommonRlt<UserAuthenticationRlt> queryAuthentication(UserQueryAuthReq req);
}
