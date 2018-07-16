package com.linkwee.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.FixedInvestRecord;
import com.linkwee.web.request.InvestRecordRequest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface FixedInvestRecordMapper extends GenericDao<FixedInvestRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<FixedInvestRecord> selectBySearchInfo(@Param("query")InvestRecordRequest investRecordRequest, RowBounds page);

}
