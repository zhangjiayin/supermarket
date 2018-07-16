package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.Usercustomerrel;


 /**
 * 
 * @描述：客户 Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月12日 09:37:08
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface UsercustomerrelDao extends BasePageDao<Usercustomerrel>{
	
	
	/**
	 * 条件查询
	 * @param objects
	 * @return
	 */
	public List<Usercustomerrel> queryByIds(Object[] objects);

	/**
	 * 根据环信帐号查用户信息
	 * @Auther ZhongLing
	 * @Date 2016年1月4日
	 * @param easemobAcct
	 * @return
	 */
	public List<Usercustomerrel> getUserInfoByEasemob(List<String> easemobAcctList);

	/**
	 * 根据
	 * @Auther xuzhao
	 * @Date 2016年1月20日 下午1:49:01
	 * @param userId
	 * @return
	 */
	public Usercustomerrel queryNewCustmers(String userId);

	/**
	 * 更新用户业务员关联关系表
	 * @Auther xuzhao
	 * @Date 2016年2月22日 下午1:37:13
	 * @param i
	 * @param number
	 * @param userId
	 * @return
	 */
	public int updateRegreInfo(int i, String number, String userId);
	
	public void updateCftForCustomer(Usercustomerrel usercustomerrel);
	
	public Integer cleanRelForCustomer(Object[] objects);
	
	public Integer beFreeCustomer(Object saleUserNumber);
	
	public Usercustomerrel findSaleInfoByMobile(String mobile);

	/**
	 * 更新用户实名
	 * @param mobile
	 * @param name
	 */
	public void updateRealName(@Param("mobile")String mobile, @Param("name")String name);

	/**
	 * 删除理财师关系
	 * @param mobile
	 * @return
	 */
	public int deleteRelForCustomer(String mobile);
}
