package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.Cfplanner;

public interface CfplannerMapper extends GenericDao<Cfplanner, String>{
    int deleteByPrimaryKey(String fNumber);

    int insert(Cfplanner record);

    int insertSelective(Cfplanner record);

    Cfplanner selectByPrimaryKey(String fNumber);

    int updateByPrimaryKeySelective(Cfplanner record);

    int updateByPrimaryKeyWithBLOBs(Cfplanner record);

    int updateByPrimaryKey(Cfplanner record);

	List<Cfplanner> selectBySearchInfo(@Param("dt")DataTable dt);

	Integer countBySearchInfo(@Param("dt")DataTable dt);
}