package com.linkwee.web.service;


import java.util.List;

import com.linkwee.web.model.Usercustomerrel;

 /**
 * 
 * @描述： 客户信息接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月12日 09:37:08
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface UsercustomerrelService{

	/**
	 * 根据手机号查询客户信息
	 * @param mobile 手机号
	 * @return
	 */
	public Usercustomerrel queryByMobile(String mobile);
	
	/**
	 * 根据客户id查询客户信息
	 * @param mobile 手机号
	 * @return
	 */
	public List<Usercustomerrel> queryByIds(String[] ids);
	
	/**
	 * 
	 * @param ids
	 * @return
	 */
	public Usercustomerrel queryUserInfo(String userId);
	
	/**
	 * 删除总要客户
	 * @param saleNumber
	 * @param customerId
	 */
	public void removeImportantCust(String saleNumber,String customerId);
	
	/**
	 * 添加重要客户
	 * @param saleNumber
	 * @param customerId
	 */
	public void addImportantCust(String saleNumber,String customerId);
	
	/**
	 * 更新用户信息
	 * @param usercustomerrel
	 */
	public void update(Usercustomerrel usercustomerrel);

	/**
	 * 根据环信帐号查用户信息
	 * @Auther ZhongLing
	 * @Date 2016年1月4日
	 * @param easemobAcct
	 * @return
	 */
	public List<Usercustomerrel> getUserInfoByEasemob(List<String> easemobAcctList);

	/**
	 * 查询新人
	 * @Auther xuzhao
	 * @Date 2016年1月20日 上午11:43:21
	 * @param userId
	 * @return
	 */
	public Usercustomerrel queryNewCustmers(String userId);

	/**
	 * 根据客户id 查询用户信息
	 * @Auther xuzhao
	 * @Date 2016年1月26日 下午3:34:43
	 * @param customerId
	 * @return
	 */
	public Usercustomerrel queryByCustomerId(String customerId);

	/**
	 * //更新用户业务员关联关系表
	 * @Auther xuzhao
	 * @Date 2016年2月22日 下午1:35:13
	 * @param i
	 * @param number
	 * @param userId
	 * @return
	 */
	public int updateRegreInfo(int i, String number, String userId);

	/***
	 * 添加信息
	 * @Auther xuzhao
	 * @Date 2016年2月22日 下午1:48:30
	 * @param usercustomerrel
	 */
	public void add(Usercustomerrel usercustomerrel);
	/**
	 * 根据手机号码查询客户信息（包含理财师信息）
	 * @param mobile
	 * @return
	 */
	public Usercustomerrel findSaleInfoByMobile(String mobile);
	
	public String findUserIdByMobile(String mobile);

	/**
	 * 更新用户实名
	 * @param mobile
	 * @param name
	 */
	public void updateRealName(String mobile, String name);
}
