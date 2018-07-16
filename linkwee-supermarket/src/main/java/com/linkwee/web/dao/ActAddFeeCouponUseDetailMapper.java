package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActAddFeeCouponUseDetail;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月17日 18:42:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActAddFeeCouponUseDetailMapper extends GenericDao<ActAddFeeCouponUseDetail,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActAddFeeCouponUseDetail> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 加拥券最新加拥
	 * @param userId
	 * @return
	 */
	ActAddFeeCouponUseDetail queryNewestAddFee(String userId);
}
