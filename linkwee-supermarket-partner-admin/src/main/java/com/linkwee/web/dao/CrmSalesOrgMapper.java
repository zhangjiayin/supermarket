package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.CustomerStatisticalListResponse;
import com.linkwee.web.response.LcsStatisticalListResponse;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.response.TeamStatisticalListResponse;
import com.linkwee.web.response.TeamStatisticalResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年11月07日 11:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmSalesOrgMapper extends GenericDao<CrmSalesOrg,Long>{
	
	List<Map<String, String>> getPlatfroms();
	
	/**
	 * 获取存量
	 * @param salesOrgId
	 * @param req
	 * @return
	 */
	Map<String, BigDecimal> getStockYearpurAmt(@Param("salesOrgId")String salesOrgId,@Param("req")TeamStatisticalRequest req);
	
	LcsStatisticalResponse getLcsStatistical(@Param("salesOrgId")String salesOrgId);
	
	List<LcsStatisticalListResponse> getLcsStatisticalList(@Param("salesOrgId")String salesOrgId,@Param("nameOrMobile")String nameOrMobile,@Param("city")String city,RowBounds page);
	
	List<CustomerStatisticalListResponse> getCustomerStatisticalList(@Param("salesOrgId")String salesOrgId,@Param("mobile")String mobile,@Param("nameOrMobile")String nameOrMobile,RowBounds page);
	
	TeamStatisticalResponse getTeamStatistical(@Param("salesOrgId")String salesOrgId,@Param("req")TeamStatisticalRequest req);
	
	List<TeamStatisticalListResponse> getTeamStatisticalList(@Param("salesOrgId")String salesOrgId,@Param("req")TeamStatisticalRequest req,RowBounds page);
}
