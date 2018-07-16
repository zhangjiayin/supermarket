package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.crm.CrmLineUserInfo;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年05月19日 19:36:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmLineUserInfoMapper extends GenericDao<CrmLineUserInfo,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CrmLineUserInfo> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 线下活动-理财师邀请记录
	 * */
	List<CrmLineUserInfo> queryInvitationRecord(Page<CrmLineUserInfo> page, Map<String, Object> conditions);

}
