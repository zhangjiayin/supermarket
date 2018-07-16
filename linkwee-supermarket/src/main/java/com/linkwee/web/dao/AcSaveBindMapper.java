package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.api.request.acc.AcSaveBind;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年06月29日 09:51:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcSaveBindMapper extends GenericDao<AcSaveBind,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcSaveBind> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
