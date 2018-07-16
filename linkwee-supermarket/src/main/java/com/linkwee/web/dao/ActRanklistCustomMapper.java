package com.linkwee.web.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.act.rankList.model.ActRanklistCustom;
import com.linkwee.core.generic.GenericDao;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2017年02月13日 10:54:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRanklistCustomMapper extends GenericDao<ActRanklistCustom,Long>{
	
	String getRanklistCustomValueByKey(@Param("rankListId")String rankListId,@Param("rankListKey")String rankListKey);
	
}
