package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActOneYuanDrawVirtualAddfourtuneRecord;
import com.linkwee.web.request.PrizeSendRequest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月09日 14:19:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActOneYuanDrawVirtualAddfourtuneRecordMapper extends GenericDao<ActOneYuanDrawVirtualAddfourtuneRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActOneYuanDrawVirtualAddfourtuneRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 增加幸运积分历史
	 * @param prizeSendRequest
	 * @param page
	 * @return
	 */
	List<ActOneYuanDrawVirtualAddfourtuneRecord> addFourtuneHistory(PrizeSendRequest prizeSendRequest,RowBounds page);
}
