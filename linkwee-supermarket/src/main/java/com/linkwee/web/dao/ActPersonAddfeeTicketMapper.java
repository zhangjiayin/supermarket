package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.act.ActPersonAddfeeTicketRequest;
import com.linkwee.api.response.act.ActPersonAddfeeTicketExtendsResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 16:02:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActPersonAddfeeTicketMapper extends GenericDao<ActPersonAddfeeTicket,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActPersonAddfeeTicket> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 我的加佣券
	 * @param actPersonAddfeeTicketRequest
	 * @param page
	 * @return
	 */
	List<ActPersonAddfeeTicketExtendsResponse> myAddFeeTicket(ActPersonAddfeeTicketRequest actPersonAddfeeTicketRequest,Page<ActPersonAddfeeTicketExtendsResponse> page);

	/**
	 * 查询客户持有的个人加佣券
	 * @param userId
	 * @param queryType  1：未过期  2：已过期 3：已使用
	 * @return
	 */
	List<ActPersonAddfeeTicketExtends> queryPersonAddfeeTicket(@Param("userId")String userId,@Param("queryType")Integer queryType);
}
