package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.act.ActPersonAddfeeTicketRequest;
import com.linkwee.api.request.cim.ProductAddfeeTicketRequest;
import com.linkwee.api.response.act.ActPersonAddfeeTicketExtendsResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;
import com.linkwee.web.model.vo.InvestRecordWrapper;
 /**
 * 
 * @描述： ActPersonAddfeeTicketService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 16:02:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActPersonAddfeeTicketService extends GenericService<ActPersonAddfeeTicket,Long>{

	/**
	 * 查询ActPersonAddfeeTicket列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 我的加佣券
	 * @param actPersonAddfeeTicketRequest
	 * @return
	 */
	PaginatorResponse<ActPersonAddfeeTicketExtendsResponse> myAddFeeTicket(ActPersonAddfeeTicketRequest actPersonAddfeeTicketRequest);

	/**
	 * 查询客户持有的个人加佣券
	 * @param userId
	 * @param queryType   1：未过期  2：已过期 3：已使用
	 * @return
	 */
	List<ActPersonAddfeeTicketExtends> queryPersonAddfeeTicket(String userId,Integer queryType);

	/**
	 * 匹配最优加佣券
	 * @param actPersonAddfeeTicketExtendsList
	 * @param investRecord
	 * @return
	 */
	ActPersonAddfeeTicketExtends matchePersonAddfeeTicket(List<ActPersonAddfeeTicketExtends> actPersonAddfeeTicketExtendsList,InvestRecordWrapper investRecord);
	
	/**
	 * 匹配加佣券
	 * @param actPersonAddfeeTicketExtendsList
	 * @param investRecord
	 * @return
	 */
	List<ActPersonAddfeeTicketExtends> matchePersonAddfeeTicketList(List<ActPersonAddfeeTicketExtends> actPersonAddfeeTicketExtendsList,InvestRecordWrapper investRecord);
	
	/**
	 * 查询当前用户对应产品可使用的加佣券
	 * @param productRedPacketRequest
	 * @return
	 */
	List<ActPersonAddfeeTicketExtends> productPersonAddfeeTicket(ProductAddfeeTicketRequest productAddfeeTicketRequest);
	
	/**
	 * 查询当前用户对应产品可使用的加佣券
	 * @param productRedPacketRequest
	 * @return
	 */
	Integer productPersonAddfeeTicketCount(ProductAddfeeTicketRequest productAddfeeTicketRequest);

	/**
	 * 查询用户所有个人加佣券数量
	 * @return
	 */
	int queryPersonAddfeeTicketCount(String userId);
}
