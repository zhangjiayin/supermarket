package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.enums.MidAutumnTaskEnum;
import com.linkwee.web.model.ActMidautumnTask;
import com.linkwee.web.service.ActMidautumnTaskService;
 /**
 * 
 * @描述： ActMidautumnTaskService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 10:12:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActMidautumnTaskService extends GenericService<ActMidautumnTask,Long>{

	/**
	 * 月饼节活动--排行榜--榜单
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @param page
	 * @return
	 */
	PaginatorResponse<InvestRankingListResponse> investRankingList(String startDate,String endDate,List<String> platformList,Page<InvestRankingListResponse> page);

	/**
	 * 完成某项任务
	 * @param userId
	 * @param midAutumnTaskEnum
	 */
	void finishTask(String userId, MidAutumnTaskEnum midAutumnTaskEnum);

	/**
	 * 是否完成投资任务
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	boolean hasFinishInvestStatus(String userId, String startDate,String endDate,List<String> platformList);
}
