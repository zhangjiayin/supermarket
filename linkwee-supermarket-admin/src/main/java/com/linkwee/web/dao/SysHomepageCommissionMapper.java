package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SysHomepageCommission;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年06月21日 10:34:25
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysHomepageCommissionMapper extends GenericDao<SysHomepageCommission,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysHomepageCommission> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 查询最新的首页佣金
	 * @return
	 */
	SysHomepageCommission selectNewest();
}
