package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.GrayRelease;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年06月27日 15:52:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface GrayReleaseMapper extends GenericDao<GrayRelease,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<GrayRelease> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
     * 根据手机号码查询灰度用户
     */
	GrayRelease selectByMobile(@Param("mobile")String mobile);
	
	/**
	 * 红包白名单用户
	 * @author yalin 
	 * @date 2016年6月28日 上午11:19:23  
	 * @return
	 */
	List<GrayRelease> queryRedpaperWhiteListUser();

}
