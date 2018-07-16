package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActivityProfit;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月24日 14:18:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActivityProfitMapper extends GenericDao<ActivityProfit,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActivityProfit> selectBySearchInfo(@Param("dt")DataTable dt,@Param("mobile") String mobile,RowBounds page);

}
