package com.linkwee.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.activity.model.Activity;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;

public interface ActivityMapper extends GenericDao<Activity, Integer> {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

	List<Activity> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	
	Activity getActivity(@Param("ativityId") String ativityId);

}