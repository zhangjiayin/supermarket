package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.act.rankList.model.ActRanklist;
import com.linkwee.act.rankList.model.MyRank;
import com.linkwee.act.rankList.model.Ranklist;
import com.linkwee.core.generic.GenericDao;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2017年02月13日 10:52:40
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRanklistMapper extends GenericDao<ActRanklist,Long>{
	
	List<Ranklist> getCommonRankListsBySql(@Param("sql")String sql,RowBounds page);
	
	MyRank getCommonRankListBySql(@Param("sql")String sql);
	
	
	String queryPriftRankListNo1(@Param("feeMonth")Integer feeMonth);
	
/*	List<Map<String, Object>> exectRanklistSqlByPage(@Param("sql")String sql,RowBounds page);
	
	
	
	
	Map<String, Object> exectRanklistSql(@Param("sql")String sql);*/
}
