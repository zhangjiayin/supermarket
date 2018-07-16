package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimFundOrder;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月24日 17:36:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFundOrderMapper extends GenericDao<CimFundOrder,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimFundOrder> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据userId查询客户购买基金额总金额
	 * @param userId
	 * @return
	 */
	BigDecimal getTransactionAmountByUserId(String userId);
}
