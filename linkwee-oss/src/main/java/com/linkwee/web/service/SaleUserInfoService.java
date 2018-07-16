package com.linkwee.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.linkwee.web.model.SaleUserInfo;


/**
 * 
 * @描述：理财师账户信息 服务类
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年07月31日 11:49:53
 * 
 *                   Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface SaleUserInfoService {


	
	/**
	 * 根据手机号码查询 用户信息
	 * 
	 * @param mobile 手机号码
	 * @return 理财师用户信息
	 */
	public SaleUserInfo getSaleUserInfoByMobile(String mobile);
	
	/**
	 * 根据用户id查询 用户信息
	 * 
	 * @param mobile 手机号码
	 * @return 理财师用户信息
	 */
	public SaleUserInfo getSaleUserInfoByCustomerId(String customerId);
	
	/**
	 * 根据投资用户id查理财师信息
	 * @Auther ZhongLing
	 * @Date 2016年1月4日
	 * @param customerId
	 * @return
	 */
	public SaleUserInfo getSaleUserInfoByInvester(String customerId);

	/**
	 * 根据用户编码查询 用户信息
	 * 
	 * @param number 用户编码
	 * @return 理财师用户信息
	 */
	public SaleUserInfo getSaleUserInfoByNumber(String number);
	
	/**
	 * 新增
	 * @param saleUserInfo
	 */
	public void add(SaleUserInfo saleUserInfo);
	
	/**
	 * 修改
	 * @param saleUserInfo
	 */
	public void update(SaleUserInfo saleUserInfo);
	
	/**
	 * 判断 是否新财富账号
	 * @param number 用户编码
	 * @return true是，false 否
	 */
	public boolean isNewWealthUser(String number);

	/**
	 * 是否一级下级
	 * @Auther ZhongLing
	 * @Date 2016年2月29日
	 * @param cfpMobile 推荐人手机号码
	 * @param customerMobile 理财师手机号码
	 * @return
	 */
	public boolean isLevelOne(String cfpMobile, String customerMobile);

	/**
	 * 查上级理财师
	 * @Auther ZhongLing
	 * @Date 2016年2月29日
	 * @param eventCfpmobile
	 * @return
	 */
	public SaleUserInfo queryParentByMobile(String eventCfpmobile);

	/**
	 * 帐号是否锁定
	 * @Auther ZhongLing
	 * @Date 2016年4月7日
	 * @param mobile
	 * @return
	 */
	public boolean isLocked(String mobile);
	
	/**
	 * 根据投资用户电话号码查理财师
	 * @param moible
	 * @return
	 */
	public SaleUserInfo queryLscByInvestMobile(String moible);

	/**
	 * 更新用户实名
	 * @param mobile
	 * @param name
	 */
	public void updateRealName(String mobile, String name);
	/**
	 * 查询理财师数据
	 * @return
	 */
	public List<SaleUserInfo> listUserList();
	
	/**
	 * 更新用户头像
	 * */
	public void updateByImage(SaleUserInfo saleUserInfo);

	/**
	 * 更换上级
	 * @param mobile
	 * @param parentMobile
	 * @param changeType
	 */
	public void changeParent(String mobile, String parentMobile, String changeType,SaleUserInfo saleUserInfo);
	
	/**
	 * 根据parentId查理财师
	 */
	public List<SaleUserInfo> querySaleUserByParent(String parentId);

	/**
	 * 禁止登录90天
	 * @param mobile
	 */
	public void disabledLogin90days(String mobile);
	
	/**
	 * 查询禁止登录90天开始时间
	 * @param mobile
	 * @return
	 */
	public Date queryDisabledLoginTime(String mobile);
	
	/**
	 * 分页查询理财师数据
	 * @return
	 */
	public List<SaleUserInfo> getAllSaleInfo(Map<String, Object> map);
	
	/**
	 * 查询所有理财师总人数
	 * @author yalin 
	 * @date 2016年6月24日 上午11:50:07  
	 * @return
	 */
	public int getAllSaleCounts(Map<String, Object> map);
	
}
