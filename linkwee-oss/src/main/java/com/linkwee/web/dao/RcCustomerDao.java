package com.linkwee.web.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BaseDao;
import com.linkwee.web.model.customer.RcCustomersResp;
import com.linkwee.web.model.customer.RcLcsCustomersResp;
import com.linkwee.web.model.customer.UnRcLcsCustomersResp;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;

/**
 * 
 * @描述： 邀请理财师
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface RcCustomerDao extends BaseDao {

	
	/**
	 * 统计未被邀请的客户-分页总数
	 * @param params
	 * @return
	 */
	public int queryUnRcLcsCustomersCount(Map<String,Object> params);
	/**
	 * 统计未被邀请的客户-分页
	 * @param pageRequest
	 * @return
	 */
	public PageList<UnRcLcsCustomersResp> queryUnRcLcsCustomers(PageRequest pageRequest);

	/**
	 * 统计邀请人数
	 * @param userNumber 理财师编号
	 * @param types 类别名称
	 * @return
	 */
	public Map<String,String> statisticRcCustCount(@Param("userNumber") String userNumber,@Param("types") String[] types);
	
	
	/**
	 * 统计未被邀请的客户-分页
	 * @param pageRequest
	 * @return
	 */
	public PageList<RcLcsCustomersResp> queryRcLcsCustomers(PageRequest pageRequest);
	
	/**
	 * 邀请客户-客户列表
	 * @param request
	 * @return
	 */
	public PageList<RcCustomersResp> queryRcCustomers(PageRequest request);
	
	
}
