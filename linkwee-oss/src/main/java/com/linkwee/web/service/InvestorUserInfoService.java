package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.InvestorDtlResp;
import com.linkwee.web.model.InvestorUserInfo;


 /**
 * 
 * 描述：投资者服务类
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月25日 15:06:26
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface InvestorUserInfoService{

	/**
	 * 更新投资者信息
	 * @param investorUserInfo
	 */
	public void update(InvestorUserInfo investorUserInfo);
	
	/**
	 * 查询投资者信息
	 * @param userId
	 * @return
	 */
	public InvestorUserInfo queryInvestorUserInfo(String userId);
	
	/**
	 * 投资者用户是否存在
	 * @param mobile 手机号码
	 * @return
	 */
	public boolean isExistsInvestor(String mobile);
	
	
	/**
	 * 预注册
	 * @param mobile 手机号
	 * @param refUser 推荐人
	 * @param userSource 用户来源
	 * @return
	 */
	public int preRegister(String mobile,String userName,String refUser,Integer userSource);
	
	/**
	 * 完成注册
	 * @param 流水号
	 * @param userId 用户id
	 */
	public void completeRegister(int id,String userId,String recommendUserId);
	
	/**
	 * 直接注册
	 * @param mobile 手机号
	 * @param refUser 推荐人
	 * @param userSource 用户来源
	 */
	public void register(String userId,String mobile,String userName,String refUser,Integer userSource);
	
	/**
	 * 根据手机号码查询投资用户
	 * @param mobile 手机号码
	 * @return
	 */
	public InvestorUserInfo queryInvestorUserInfoByMobile(String mobile);

	/**
	 * 查用户是否为理财师用户
	 * @Auther ZhongLing
	 * @Date 2016年1月6日
	 * @param userId
	 */
	public boolean isCfp(String userId);

	/**
	 * 查没有用户id的错误数据
	 * @Auther ZhongLing
	 * @Date 2016年3月16日
	 * @return
	 */
	public InvestorUserInfo queryErrInvestorUserInfo();

	/**
	 * 初始化关联关系表数据
	 * @Auther xuzhao
	 * @Date 2016年3月18日 下午3:09:54
	 * @param userId
	 * @param mobile
	 * @param userName
	 * @param refUser
	 * @param userSource
	 */
	public void initusercustomerrel(String userId, String mobile, String userName, String refUser);

	/**
	 * 没用关联关系数据的用户
	 * @Auther ZhongLing
	 * @Date 2016年3月18日
	 * @return
	 */
	public InvestorUserInfo queryErrInvestorUserInfoOfRefIsNull();
	
	public Map<String,InvestorUserInfo> getInvestUserInfoMap(List<String> userIds);
	/**
	 * 帐号是否锁定
	 * @Auther ZhongLing
	 * @Date 2016年4月7日
	 * @param mobile
	 * @return
	 */
	public boolean isLocked(String mobile);

	/**
	 * 更新用户实名
	 * @param mobile
	 * @param name
	 */
	public void updateRealName(String mobile, String name);
	
	/**
	 * 更新用户头像
	 * */
	public void updateByImage(InvestorUserInfo invest);
	
	public String findUserIdByNameOrMobile(String buyUserName);

	/**
	 * 查用户详情
	 */
	public InvestorDtlResp queryInvestorDetail(String mobile);

	/**
	 * 删除头像
	 * @param mobile
	 * @return
	 */
	public boolean removeInvestorHeadImage(String mobile);
	
	public PaginatorResponse<InvestorUserInfo> queryInvestorUserInfo(Page<InvestorUserInfo> page,Map<String,Object> conditions);
	
	
}
