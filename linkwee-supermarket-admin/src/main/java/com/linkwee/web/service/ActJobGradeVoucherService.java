package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.service.ActJobGradeVoucherService;
 /**
 * 
 * @描述： ActJobGradeVoucherService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月27日 18:00:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActJobGradeVoucherService extends GenericService<ActJobGradeVoucher,Long>{

	/**
	 * 查询ActJobGradeVoucher列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 新增职级体验券
	 * @throws Exception 
	 * */
	void insertJobGradeVoucher(ActJobGradeVoucher voucher) throws Exception;

	/**
	 * 新增职级体验券
	 * @throws Exception 
	 * */
	void insertJobGradeVoucherList(List<ActJobGradeVoucher> gradeList) throws Exception;

	/**
	 * 同步职级体验券状态(使用)
	 * @throws Exception 
	 * */
	void synActJobGradeVoucher() throws Exception ;
	
	/**
	 * 同步职级体验券状态(过期)
	 * @throws Exception 
	 * */
	void synActExpiresTimeJobGradeVoucher();
}
