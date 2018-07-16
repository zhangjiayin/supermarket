package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActChristmasSocks;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:52:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActChristmasSocksMapper extends GenericDao<ActChristmasSocks,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActChristmasSocks> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
