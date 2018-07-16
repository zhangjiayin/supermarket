package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.SmBrandPromotion;
import com.linkwee.web.model.mc.SmClassroom;
import com.linkwee.web.service.SmBrandPromotionService;
 /**
 * 
 * @描述： SmBrandPromotionService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月28日 18:47:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmBrandPromotionService extends GenericService<SmBrandPromotion,Long>{

	/**
	 * 查询SmBrandPromotion列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	
	/**
	 * 查询SmClassroom列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(SmBrandPromotion smBrandPromotion,DataTable dataTable);

	/**
	 * 顶置-个人海报
	 */
	void overheadSmBrandPromotion();

	/**
	 * 顶置-个人名片
	 */
	void overheadbpSmBrandPromotion();
}
