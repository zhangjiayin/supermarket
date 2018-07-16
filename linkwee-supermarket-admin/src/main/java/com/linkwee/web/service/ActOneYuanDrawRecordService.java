package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.controller.activity.BaseLottery;
import com.linkwee.web.model.ActOneYuanDrawRecord;
import com.linkwee.web.request.PrizeSendRequest;
import com.linkwee.web.service.ActOneYuanDrawRecordService;
 /**
 * 
 * @描述： ActOneYuanDrawRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月08日 13:46:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActOneYuanDrawRecordService extends GenericService<ActOneYuanDrawRecord,Long>{

	/**
	 * 查询ActOneYuanDrawRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 查询需要人工发放的奖品
	 * @param prizeSendRequest
	 * @param dataTable
	 * @return
	 */
	DataTableReturn queryPrizeSendList(PrizeSendRequest prizeSendRequest,DataTable dataTable);
	
	/**
	 * 标记幸运奖为发放
	 * @param prizeId
	 */
	public void sendFortunePrize(Integer prizeId,String sendOperater);

	/**
	 * 增加幸运积分
	 * @param baseLotteryList
	 * @param bizId
	 * @param userId
	 * @param drawType 
	 */
	void addfourtune(List<BaseLottery> baseLotteryList, String bizId,String userId,Integer drawType,String activityId);

}
