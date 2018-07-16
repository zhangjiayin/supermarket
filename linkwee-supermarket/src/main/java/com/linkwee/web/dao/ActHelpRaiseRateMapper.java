package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.activity.ActHelpRaiseRate;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年03月02日 11:39:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActHelpRaiseRateMapper extends GenericDao<ActHelpRaiseRate,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActHelpRaiseRate> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	ActHelpRaiseRate queryByUserIdForUpdate(String userId);
	
}
