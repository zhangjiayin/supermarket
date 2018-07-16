package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SysThirdkeyConfig;
 /**
 * 
 * @描述： SysThirdkeyConfigService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 11:16:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysThirdkeyConfigService extends GenericService<SysThirdkeyConfig,Long>{

	/**
	 * 查询SysThirdkeyConfig列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 根据机构编码查询机构配置信息
	 * @param orgNumber
	 * @return
	 */
	SysThirdkeyConfig selectOneByOrgNumber(String orgNumber);
}
