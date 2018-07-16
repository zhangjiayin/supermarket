package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimInsuranceFeedetail;
import com.linkwee.web.model.CimInsuranceFeedetailExtends;

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
public interface CimInsuranceFeedetailMapper extends GenericDao<CimInsuranceFeedetail,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimInsuranceFeedetail> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 批量写入保险佣金明细表
	 * @param cimInsuranceFeedetailList
	 */
	void inserts(@Param("insuranceFeedetails")List<CimInsuranceFeedetail> cimInsuranceFeedetailList);

	/**
	 * 根据投资记录编号查询最初佣金,推荐津贴,直接管理津贴
	 * feeType 1001  10002  1005  最多只会有一条
	 * @param billId
	 * @return
	 */
	List<CimInsuranceFeedetailExtends> queryInitInsuranceFeeDetailByBillId1005(String billId);

	/**
	 * 根据投资记录编号查询最初佣金,团队管理津贴
	 * feeType 1006  最多有两条
	 * @param billId
	 * @return
	 */
	List<CimInsuranceFeedetailExtends> queryInitInsuranceFeeDetailByBillId1006(String billId);

	/**
	 * 根据理财师编号与佣金类型 批量更新佣金明细结算状态
	 * @param cfplannerIds
	 * @param feeType
	 * @param balanceMaps
	 * @param balanceStatus
	 * @param begintime
	 * @param endTime
	 * @return
	 */
	int batchUpdateBalanceStatusBycfplannerIdAndFeeType(@Param("cfplannerIds")List<String> cfplannerIds,@Param("feeType")String feeType,@Param("balanceMaps") List<Map<String, String>> balanceMaps, @Param("balanceStatus")int balanceStatus,@Param("beginTime")String begintime,@Param("endTime")String endTime);
}
