package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.activity.ActHelpRaiseRateDetail;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年03月02日 10:39:20
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActHelpRaiseRateDetailMapper extends GenericDao<ActHelpRaiseRateDetail,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActHelpRaiseRateDetail> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	
	
	List<ActHelpRaiseRateDetail> queryHelpDetailList(String userId);



	List<ActHelpRaiseRateDetail> queryForUpdate(@Param("userId")String userId, @Param("openid")String openid);


	
	
	
	
}
