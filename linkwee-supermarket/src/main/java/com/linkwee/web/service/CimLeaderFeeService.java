package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.cim.CimLeaderFee;
 /**
 * 
 * @描述： CimLeaderFeeService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年02月28日 10:46:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimLeaderFeeService extends GenericService<CimLeaderFee,Long>{

	/**
	 * 查询CimLeaderFee列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 判断是否符合5+1 leader奖励条件
	 * 
	 * */
	boolean isFiveAddOneCondition(String userId);

	
	/**
	 * 直属理财师贡献leader奖励明细-分页
	 * 
	 * */
	PaginatorSevResp<CimLeaderFee> querycontribuPageList(Map<String, Object> query, Page<CimLeaderFee> page);

	/**
	 * 本月leader奖励
	 * */
	Double queryMonthProfit(String userId);

	/**
	 * 间接理财师人数
	 * */
	Integer queryIndirectCfpNumbers(String userId);
	
	/**
	 * 下级有独立核算的理财师
	 * */
	boolean haveUnderCfpIndependent(String userId);
	
	/**
	 * 统计累计收益
	 * */
	Double queryTotalProfit(String userId);
	
	/**
	 * 间接理财师贡献奖励
	 * */
	Double queryContrProfit(String userId);
	
	String queryLeaderPriftRankListNo1();

	/**
	 * 邀请注册、变更leader奖励树
	 * */
	void changeLeaderTree(String mobile, String recommendCode);

}
