package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmCfpNewcomerWelfareTask;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月13日 14:46:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpNewcomerWelfareTaskMapper extends GenericDao<CrmCfpNewcomerWelfareTask,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmCfpNewcomerWelfareTask> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 是否存在的新注册用户
	 * @param userId
	 * @return
	 */
	boolean isExistUser(@Param("userId")String userId);
}
