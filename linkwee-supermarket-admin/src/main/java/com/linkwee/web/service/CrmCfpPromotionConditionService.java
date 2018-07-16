package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpPromotionCondition;
 /**
 * 
 * @描述： CrmCfpPromotionConditionService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpPromotionConditionService extends GenericService<CrmCfpPromotionCondition,Long>{

	/**
	 * 查询CrmCfpPromotionCondition列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询理财师晋升条件
	 * @return
	 */
	Map<String,CrmCfpPromotionCondition> queryCrmCfpPromotionCondition();
	
	/**
	 * 理财师职级考核标准
	 * @author yalin 
	 * @date 2017年4月24日 上午10:33:58  
	 * @return
	 */
	List<CrmCfpPromotionCondition> getCfpPromotionConditionList();
}
