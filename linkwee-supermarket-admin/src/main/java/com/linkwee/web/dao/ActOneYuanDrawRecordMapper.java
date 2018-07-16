package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActOneYuanDrawRecord;
import com.linkwee.web.request.PrizeSendRequest;
import com.linkwee.web.response.PrizeSendResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月08日 13:46:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActOneYuanDrawRecordMapper extends GenericDao<ActOneYuanDrawRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActOneYuanDrawRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 需要人工发放的奖品列表
	 * @param prizeSendRequest
	 * @param page
	 * @return
	 */
	List<PrizeSendResponse> queryPrizeSendList(PrizeSendRequest prizeSendRequest, RowBounds page);

	/**
	 * 标记幸运奖状态为发放
	 * @param prizeId
	 */
	void sendFortunePrize(@Param("prizeId")Integer prizeId,@Param("sendOperater")String sendOperater);
}
