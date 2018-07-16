package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CustomerInvest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CustomerInvestMapper extends GenericDao<CustomerInvest,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CustomerInvest> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 查用户使用红包总额
	 * @param userId
	 * @return
	 */
	Double queryTotalUsedHongbao(String customerId);

}
