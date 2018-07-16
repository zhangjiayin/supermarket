package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.AccountBookStatisticResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmUserAccountBook;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月14日 15:31:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmUserAccountBookMapper extends GenericDao<CrmUserAccountBook,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmUserAccountBook> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 记账本统计
	 * @param userId
	 * @return
	 */
	AccountBookStatisticResponse statistics(String userId);

	/**
	 * 在投列表
	 * @param userId
	 * @param page
	 * @return
	 */
	List<CrmUserAccountBook> investingList(@Param("userId")String userId,RowBounds page);
}
