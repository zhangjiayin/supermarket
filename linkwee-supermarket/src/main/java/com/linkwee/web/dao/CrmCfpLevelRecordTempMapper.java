package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.model.crm.CrmCfpLevelTemp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年04月10日 13:51:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpLevelRecordTempMapper extends GenericDao<CrmCfpLevelRecordTemp,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfpLevelRecordTemp> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据userId查询
	 * @param userId
	 * @return
	 */
	CrmCfpLevelRecordTemp queryByUserId(String userId);

	/**
	 * 查询理财师每日定级
	 * @param crmCfpLevelTemp
	 * @return
	 */
	CrmCfpLevelRecordTemp selectTempCfpLevel(CrmCfpLevelTemp crmCfpLevelTemp);
}
