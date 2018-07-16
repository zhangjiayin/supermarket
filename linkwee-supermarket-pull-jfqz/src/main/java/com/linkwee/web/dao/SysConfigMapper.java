package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SysConfig;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月08日 16:50:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysConfigMapper extends GenericDao<SysConfig,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysConfig> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	List<SysConfig> selectByName(SysConfig sysConfig);
	
	/**
	 * 根据key更新
	 * @param sysConfig
	 */
	void updateSysConfigByKey(SysConfig sysConfig);

}
