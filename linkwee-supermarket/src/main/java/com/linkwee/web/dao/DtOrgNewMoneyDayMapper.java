package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.cim.OrgMoneyDataRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.DtOrgNewMoneyDay;
import com.linkwee.web.model.cim.OrgMoneyDataDetail;

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
public interface DtOrgNewMoneyDayMapper extends GenericDao<DtOrgNewMoneyDay,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<DtOrgNewMoneyDay> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查询机构每日成交量
	 * @param orgMoneyDataRequest
	 * @return
	 */
	List<OrgMoneyDataDetail> queryOrgdata(OrgMoneyDataRequest orgMoneyDataRequest);
}
