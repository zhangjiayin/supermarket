package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.crm.CrmCfpLevelMonth;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLevelRecordMapper extends GenericDao<CrmCfpLevelRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfpLevelRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据时间查询理财师对应的职级
	 * @param date
	 * @return
	 */
	CrmCfpLevelRecord selectCrmCfpLevelRecordByTime(String date);

	/**
	 * 查询理财师每月定级
	 * @param crmCfpLevelMonth
	 * @return
	 */
	CrmCfpLevelRecord selectMonthCfpLevel(CrmCfpLevelMonth crmCfpLevelMonth);
}
