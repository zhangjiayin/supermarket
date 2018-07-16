package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.model.CrmCfpNewcomerWelfareTask;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
 /**
 * 
 * @描述： CrmCfpNewcomerWelfareTaskService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月13日 14:46:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpNewcomerWelfareTaskService extends GenericService<CrmCfpNewcomerWelfareTask,Long>{

	/**
	 * 查询CrmCfpNewcomerWelfareTask列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 新手福利六连送发放奖励
	 * @param userId
	 * @param cfpNewcommerWelfareAll
	 */
	void sendTaskReward(String userId,CfpNewcomerTaskEnum cfpNewcommerWelfareAll) throws Exception;

	/**
	 * 是否存在的新注册用户
	 * @param userId
	 * @return
	 */
	boolean isExistUser(String userId);
}
