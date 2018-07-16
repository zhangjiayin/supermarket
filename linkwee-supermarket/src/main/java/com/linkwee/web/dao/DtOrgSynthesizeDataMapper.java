package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.DtOrgSynthesizeData;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface DtOrgSynthesizeDataMapper extends GenericDao<DtOrgSynthesizeData,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<DtOrgSynthesizeData> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 *  根据机构编码获取最新的机构综合数据
	 * @param orgNumber
	 * @return
	 */
	DtOrgSynthesizeData getNewestOrgData(String orgNumber);
}
