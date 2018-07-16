package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableEditorOption;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimInsuranceCate;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月09日 14:10:36
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceCateMapper extends GenericDao<CimInsuranceCate,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceCate> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询datatable editor 选项
	 * @return
	 */
	List<DataTableEditorOption> getDataTableEditorOptions();
}
