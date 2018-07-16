package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.linkwee.act.rankList.model.ActRanklist;
import com.linkwee.act.rankList.model.ActRanklistVirtualData;
import com.linkwee.act.rankList.model.ActZybRanklistDetail;
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
	
	
	List<Map<String, Object>> exectRanklistSql(@Param("sql")String sql);
	
	int updateZybRankStatus();
	
	int insetsZybRanks(@Param("zybRanklistDetail")Set<ActZybRanklistDetail> zybRanklistDetails);
	
	int updateRankListVirtualDataStatus(@Param("ranklist_id") String ranklist_id);
	
	int insetsVirtualData(@Param("tdjlRanklistData")List<ActRanklistVirtualData> tdjlRanklistData);
	
	List<ActZybRanklistDetail> queryZybRanklist(@Param("preWeekStart")String preWeekStart,@Param("preWeekEnd")String preWeekEnd);
}
