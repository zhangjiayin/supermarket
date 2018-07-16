package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.WxAccessToken;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月08日 17:14:05
 * 
 * Copyright (c) 深圳米格网络科技有限公司-版权所有
 */
public interface WxAccessTokenMapper extends GenericDao<WxAccessToken,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<WxAccessToken> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 获取最新的微信AccessToken
	 * @return
	 */
	WxAccessToken selectNewAccessToken(@Param("appType")int appType);
}
