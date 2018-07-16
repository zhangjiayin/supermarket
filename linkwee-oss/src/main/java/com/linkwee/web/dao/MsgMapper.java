package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.Msg;

/**
 * 
 * @描述： Dao接口
 * 
 * @创建人： 陈佳良
 * 
 * @创建时间：2016年06月03日 17:34:00
 * 
 *                   Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface MsgMapper extends GenericDao<Msg, Long> {

	/**
	 * 封装DataTable对象查询
	 * 
	 * @param dt
	 * @param page
	 * @return
	 */
	List<Msg> selectBySearchInfo(@Param("dt") DataTable dt,
			@Param("type") Integer type, @Param("appType") Integer appType,
			RowBounds page);

	/**
	 * @param msg
	 * @return
	 */
	Integer add(Msg msg);

}
