package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.crm.JobGradeVoucherResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.model.crm.JobGradeVoucherPopupResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月27日 18:00:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActJobGradeVoucherMapper extends GenericDao<ActJobGradeVoucher,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActJobGradeVoucher> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 4.5.0职级体验券列表
	 *  */
	List<JobGradeVoucherResponse> jobGradeVoucherPage(Page<JobGradeVoucherResponse> page,
			Map<String, Object> conditions);

	/**
	 * 4.5.0职级体验券弹出框-获取最近发放券的时间
	 *  */
	JobGradeVoucherPopupResponse queryNewJobGradeVoucherPopupDate(String userId);

	/**
	 * 4.5.0可以使用的职级体验券数量
	 *  */
	int queryCanUserJobGradeVoucher(String userId);
}
