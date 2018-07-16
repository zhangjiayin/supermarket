package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.acc.AcWhiteCard;
 /**
 * 
 * @描述： AcWhiteCardService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月26日 17:45:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcWhiteCardService extends GenericService<AcWhiteCard,Long>{

	/**
	 * 查询AcWhiteCard列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询白名单
	 * */
	boolean queryAcWhiteCardByBankCard(String bankCard);
}
