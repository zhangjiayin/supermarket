package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.SysErrorLog;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月22日 19:16:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysErrorLogMapper extends GenericDao<SysErrorLog,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SysErrorLog> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
