package com.linkwee.web.dao;

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
	 * 拉取基金订单列表状态更新
	 * 
	 * 终态
     * failure：支付失败； 
     * completed：交易成功；
 	 * canceled：已撤单；
 	 * void：交易失败
 	 * 
 	 * 以下为非终态
	 * received：下单成功； 
	 * priced：确认成功； 
	 * ipo.processing：认购确认成功； 
	 * pending.payment：等待付款；
	 * canceling：撤单中；
	 * pending.void：等待退款；
	 * payment：支付过程中的过渡状态
	 * @return
	 */
	List<CimFundOrder> selectUpdateOrderList();
}
