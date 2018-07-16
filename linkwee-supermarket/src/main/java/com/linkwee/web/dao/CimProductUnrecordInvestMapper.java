package com.linkwee.web.dao;

import java.util.List;

import com.linkwee.api.response.cim.OrgAUserInfoResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.cim.CimSunburnPageListRequest;
import com.linkwee.api.response.cim.CimSunburnListResponse;
import com.linkwee.api.response.tc.CfplannerUnrecordInvestResponse;
import com.linkwee.api.response.tc.CustomerUnrecordInvestResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimProductUnrecordInvest;
import com.linkwee.web.model.cim.CimUnrecordInvestListResp;
import com.linkwee.web.response.tc.UnrecordInvestListResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductUnrecordInvestMapper extends GenericDao<CimProductUnrecordInvest,Long>{
	
	/**
	 * 获取客户审核通过报单记录
	 * @param userId
	 * @param page
	 * @return
	 */
	List<CustomerUnrecordInvestResponse> getCustomerUnrecordInvest(@Param("userId")String userId,RowBounds page);
	
	
	
	int getCfplannerUnrecordInvestCount(@Param("userId")String userId);
	
	/**
	 * 获取理财师报单
	 * @param userId
	 * @param page
	 * @return
	 */
	List<CfplannerUnrecordInvestResponse> getCfplannerUnrecordInvest(@Param("userId")String userId,RowBounds page);
	
	List<UnrecordInvestListResponse> getUnrecordInvestList(@Param("mobile")String mobile,@Param("status")Integer status,RowBounds page);

	List<CimUnrecordInvestListResp> unrecordPageList(@Param("order")Integer order,@Param("userId")String userId, Page<CimUnrecordInvestListResp> page);

	List<CimSunburnListResponse> sunburnPageList(CimSunburnPageListRequest request,Page<CimSunburnListResponse> page);

	void thumbsUp(@Param("id")int id);

	Double orgAtotalAmount(@Param("userId")String userId);

	CimUnrecordInvestListResp sunburnDetail(@Param("id")String id);

	 /**
	  * A专区用户信息
	  * @param userId
	  * @return
	  */
	OrgAUserInfoResponse orgAUserInfo(@Param("userId")String userId);
 }
