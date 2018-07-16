package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.SmBrandPromotionType;
import com.linkwee.web.service.SmBrandPromotionTypeService;
 /**
 * 
 * @描述： SmBrandPromotionTypeService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月28日 16:44:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmBrandPromotionTypeService extends GenericService<SmBrandPromotionType,Long>{

	/**
	 * 查询SmBrandPromotionType列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
