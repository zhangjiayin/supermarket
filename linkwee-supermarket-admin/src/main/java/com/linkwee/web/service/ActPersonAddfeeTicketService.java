package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.web.request.act.AddFeeTicketRequest;
import com.linkwee.web.service.ActPersonAddfeeTicketService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

/**
 * 
 * @描述： ActPersonAddfeeTicketService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 17:24:11
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
	  * 获取个人加拥券列表
	  * @param dataTable
	  * @return
	  */
	DataTableReturn getPersonAddfeeTicketList(DataTable dataTable);

	 /**
	  * 添加加拥券
	  * @param ticketRequest
	  */
	void insertAddFeeTicket(AddFeeTicketRequest ticketRequest);

	 /**
	  * 编辑加拥券
	  * @param ticketRequest
	  */
	void updateAddFeeTicket(AddFeeTicketRequest ticketRequest);

	/**
	 * 发放个人加拥券
	 * @param file
	 * @param ticketId
	 * @param startDate
	 * @param endDate
	 * @param username
	 * @return
	 */
	Set<String> sendAddFeeTicket(MultipartFile file, String ticketId, Date startDate, Date endDate, String username, String remark) throws Exception;
}
