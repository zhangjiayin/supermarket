package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimInsuranceFee;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月01日 17:03:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimInsuranceFeeMapper extends GenericDao<CimInsuranceFee,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceFee> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 判断佣金记录是否存在
	 * @param billId
	 * @param profitCfplannerId
	 * @param feetype
	 * @return
	 */
	boolean isExitFee(@Param("billId")String billId, @Param("profitCfplannerId")String profitCfplannerId,@Param("feeType") String feetype);

	/**
	 * 更新佣金记录
	 * @param cimInsuranceFee
	 */
	void updateFee(CimInsuranceFee cimInsuranceFee);
}
