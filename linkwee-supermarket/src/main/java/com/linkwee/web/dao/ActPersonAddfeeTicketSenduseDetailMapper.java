package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActPersonAddfeeTicketSenduseDetail;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 16:02:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActPersonAddfeeTicketSenduseDetailMapper extends GenericDao<ActPersonAddfeeTicketSenduseDetail,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActPersonAddfeeTicketSenduseDetail> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据投资记录ID查询已使用的加佣券明细
	 * @param investId
	 * @return
	 */
	ActPersonAddfeeTicketExtends queryPersonUseAddfeeTicketByInvestId(String investId);
}
