package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.GrayRelease;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2016年05月26日 15:30:33
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface GrayReleaseDao extends BasePageDao<GrayRelease>{
	
	

	/**
     * 根据手机号码查询灰度用户列表
     */
	List<GrayRelease> selectListByMobile(@Param("mobile")String mobile, RowBounds page);
	
	/**
     * 根据id查询
     */
	GrayRelease selectByPrimaryKey(Integer id);
	
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
