package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.SaleUserInfo;


 /**
 * 
 * @描述：理财师 Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年07月31日 11:49:53
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface SaleUserInfoDao extends BasePageDao<SaleUserInfo>{
	
	/**
	 * 查询新财富员工
	 * @param saleUserInfo
	 * @return
	 */
	public List<SaleUserInfo> findNewWealthUser(SaleUserInfo saleUserInfo);

	/**
	 * 查询可分配的理财师列表
	 * @return 
	 * @Auther xuzhao
	 * @Date 2016年2月20日 下午4:24:29
	 */
	public List<SaleUserInfo> queryCandividendList();

	/**
	 * 查所有一级下级手机号码
	 * @Auther ZhongLing
	 * @Date 2016年2月29日
	 * @param mobile
	 * @return
	 */
	public List<String> queryLevelOneLcs(String mobile);

	/**
	 * 查上级理财师
	 * @Auther ZhongLing
	 * @Date 2016年2月29日
	 * @param eventCfpmobile
	 * @return
	 */
	public SaleUserInfo queryParentByMobile(String eventCfpmobile);
	/**
	 * 变更为自由理财师
	 */
	public Integer beFreeCfp(Object[] objects);
	
	/**
	 * 成为新财富理财师
	 * @param saleUserInfo
	 * @return
	 */
	public Integer beXcfCfp(String department,String mobile);
	/**
	 * 根据f-number备份数据
	 * @param number
	 */
	public void backSaleUserByNumber(String number);
	/**
	 * 物理删除
	 */
	public void phDeleteByNumber(String number);
	/**
	 * 查是否锁定字段
	 * @Auther ZhongLing
	 * @Date 2016年4月7日
	 * @param mobile
	 * @return
	 */
	public int queryIsLocked(String mobile);

	/**
	 * 根据投资用户电话号码查理财师
	 * @param moible 投资者的电话号码
	 * @return
	 */
	public SaleUserInfo queryLscByInvestMobile(String moible);

	/**
	 * 更新用户实名
	 * @param mobile
	 * @param name
	 */
	public void updateRealName(@Param("mobile")String mobile, @Param("name")String name);
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
	 * 更新禁止登录90天开始时间
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
