package com.linkwee.web.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.tc.fee.model.TCFeeSummary;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2017年04月19日 10:44:58
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeesummaryMapper extends GenericDao<TCFeeSummary,Long>{
	
	/**
	 * 更新统计状态
	 * @param bizId 业务编号
	 * @param operator 操作人
	 * @return
	 */
	int updateFeesummaryTypeBybizId(@Param("bizId")String bizId,@Param("operator")String operator);
}
