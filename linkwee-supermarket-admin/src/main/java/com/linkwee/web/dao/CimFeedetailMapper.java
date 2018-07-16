package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimFeedetail;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 14:33:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFeedetailMapper extends GenericDao<CimFeedetail,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimFeedetail> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 根据投资记录编号查询最初佣金,推荐津贴,直接管理津贴,团队管理津贴,计算明细     用于浮动期产品过了最小日期 每天津贴计算
	 * feeType 1001  10002  1005  最多只会有一条
	 * @param billId
	 * @return
	 */
	List<CimFeedetail> queryEveryDayCalcFeeDetailMapByBillId(String billId);
	
	/**
	 * 根据投资记录编号查询最初佣金,推荐津贴,直接管理津贴,团队管理津贴,计算明细     用于浮动期产品过了最小日期 每天津贴计算
	 * feeType 1006  最多有两条
	 * @param billId
	 * @return
	 */
	List<CimFeedetail> queryEveryDayCalcFeeDetail1006MapByBillId(String billId);
}
