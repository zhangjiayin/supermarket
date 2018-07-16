package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.tc.CfpFeeInfoResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimFee;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月19日 16:14:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimFeeMapper extends GenericDao<CimFee,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimFee> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 伪造的佣金发放记录
	 * @return
	 */
	List<CfpFeeInfoResponse> virtualFeeList();
}
