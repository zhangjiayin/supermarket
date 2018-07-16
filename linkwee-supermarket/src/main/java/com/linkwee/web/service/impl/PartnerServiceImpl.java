package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.linkwee.api.response.crm.JobGradeVoucherResponse;
import com.linkwee.api.response.crm.MonthSaleListAllowanceListResponse;
import com.linkwee.api.response.crm.PartnerJobGradeResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActJobGradeVoucherMapper;
import com.linkwee.web.dao.CrmCfplannerMapper;
import com.linkwee.web.dao.CrmUserInfoMapper;
import com.linkwee.web.dao.PartnerMapper;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.enums.CfpLevelEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.JobGradeVoucherPopupResponse;
import com.linkwee.web.model.crm.MonthSaleListAllowanceList;
import com.linkwee.web.model.crm.MonthSaleListResp;
import com.linkwee.web.model.crm.MonthSaleStatisticsResp;
import com.linkwee.web.model.crm.PartnerDetailResp;
import com.linkwee.web.model.crm.PartnerListResp;
import com.linkwee.web.model.crm.PartnerSaleRecordNewResp;
import com.linkwee.web.model.crm.PartnerSaleRecordResp;
import com.linkwee.web.response.CfpLevelWarningResp;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.PartnerService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("partnerService")
public class PartnerServiceImpl implements PartnerService{
	
	@Resource
	private CrmUserInfoMapper crmUserInfoMapper;
	
	@Resource
	private PartnerMapper partnerMapper;
	
	@Resource
    private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private CrmCfplannerMapper  crmCfplannerMapper;
	
	@Resource
	private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
	
	@Resource
	private ActJobGradeVoucherMapper actJobGradeVoucherMapper;

	@Override
	public Integer queryMyRcCount(String userId) {
		return partnerMapper.queryMyRcCount(userId);
	}

	@Override
	public Integer queryChildrenRcCount(String userId) {
		return partnerMapper.queryChildrenRcCount(userId);
	}

	/**
	 * 团队列表
	 * @param pageRequest
	 * @return
	 */
	@Override
	public PaginatorSevResp<PartnerListResp> queryPartnerList(Map<String, Object> query, Page<PartnerListResp> page) {
		PaginatorSevResp<PartnerListResp> paginatorResponse = new PaginatorSevResp<PartnerListResp>();
		paginatorResponse.setDatas(partnerMapper.queryPartnerList(query, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	/**
	 * 团队成员详情
	 * @param userId
	 * @return
	 */
	@Override
	public PartnerDetailResp queryDetailResp(String userId) {
		PartnerDetailResp rlt = new PartnerDetailResp();
		//团队成员
		CrmCfplanner cfplanner = crmCfplannerService.queryCfplannerByUserId(userId);
		rlt.setUserName(cfplanner.getUserName());
		rlt.setRegisterDate(cfplanner.getCfpRegTime());
		rlt.setMobile(cfplanner.getMobile());
		rlt.setCfgLevelName(CfpLevelEnum.valueOf(cfplanner.getCfpLevel()).getMsg());
		//首单时间
		rlt.setFirstRcpDate(partnerMapper.queryFirstRcpDate(cfplanner.getParentId(),userId));
		//下级人数
		rlt.setChildrenCount(queryMyRcCount(userId));
		//直接津贴
		rlt.setAllowance(partnerMapper.queryAllowance(cfplanner.getParentId(), userId));
		//间接津贴
		rlt.setChildrenAllowance(partnerMapper.queryChildrenAllowance(cfplanner.getParentId(), userId));
		rlt.setHeadImage(cfplanner.getHeadImage());
		return rlt;
	}

	/**
	 * 团队成员销售记录
	 * @param query
	 * @param page
	 * @return
	 */
	@Override
	public PaginatorSevResp<PartnerSaleRecordResp> queryPartnerSaleRecord(Map<String, Object> query,
			Page<PartnerSaleRecordResp> page) {
		PaginatorSevResp<PartnerSaleRecordResp> paginatorResponse = new PaginatorSevResp<PartnerSaleRecordResp>();
		paginatorResponse.setDatas(partnerMapper.queryPartnerSaleRecord(query, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	/**
	 * 团队成员销售记录3.0
	 * @param query
	 * @param page
	 * @return
	 */
	@Override
	public PaginatorSevResp<PartnerSaleRecordNewResp> queryPartnerSaleRecordNew(Map<String, Object> query,
			Page<PartnerSaleRecordNewResp> page) {
		PaginatorSevResp<PartnerSaleRecordNewResp> paginatorResponse = new PaginatorSevResp<PartnerSaleRecordNewResp>();
		List<PartnerSaleRecordNewResp> list = partnerMapper.queryPartnerSaleRecordNew(query, page);
		String userId = (String) query.get("userId");
		String parentId = (String) query.get("parentId");
		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
		String userName = "";
		String mobile = "";
		if(cfp != null ) {
			userName = cfp.getUserName();
			mobile = cfp.getMobile();
		}
		for (int i = 0; i < list.size(); i++) {
			PartnerSaleRecordNewResp  monthSaleList = list.get(i);
			List<MonthSaleListAllowanceListResponse> allowanceList = new ArrayList<MonthSaleListAllowanceListResponse>();
			List<MonthSaleListAllowanceList> allowanceListData = partnerMapper.queryAllwanceListByBillId(monthSaleList.getBillId(), parentId);
			for (int j = 0; j < allowanceListData.size(); j++) {
				MonthSaleListAllowanceListResponse re = new MonthSaleListAllowanceListResponse(allowanceListData.get(j));
				allowanceList.add(re);
			}
			
			list.get(i).setAllowanceList(allowanceList);
			list.get(i).setMobile(mobile);
			list.get(i).setUserName(userName);
		}
		paginatorResponse.setDatas(list);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	/**
	 * 未读团队成员数量
	 * @param userId
	 * @param date
	 * @return
	 */
	@Override
	public Integer queryNewPartnerCount(String userId, Date date) {
		return partnerMapper.queryNewPartnerCount(userId, date);
	}

	@Override
	public Double queryThisMonthAllowance(String userId) {
		return partnerMapper.queryThisMonthAllowance(userId);
	}

	@Override
	public Double queryThisMonthTeamSaleAmount(String userId) {
		return partnerMapper.queryThisMonthTeamSaleAmount(userId);
	}

	@Override
	public MonthSaleStatisticsResp queryPartnerMonthSaleStatistics(Map<String, Object> query) {
		return partnerMapper.queryPartnerMonthSaleStatistics(query);
	}

	@Override
	public PaginatorSevResp<MonthSaleListResp> queryPartnerMonthSaleList(Map<String, Object> query,
			Page<MonthSaleListResp> page) {
		PaginatorSevResp<MonthSaleListResp> paginatorResponse = new PaginatorSevResp<MonthSaleListResp>();
		paginatorResponse.setDatas(partnerMapper.queryPartnerMonthSaleList(query, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	@Override
	public MonthSaleStatisticsResp queryPartnerMonthSaleStatisticsNew(Map<String, Object> query) {
		return partnerMapper.queryPartnerMonthSaleStatisticsNew(query);
	}
	
	@Override
	public PaginatorSevResp<MonthSaleListResp> queryPartnerMonthSaleListNew(Map<String, Object> query,
			Page<MonthSaleListResp> page) {
		PaginatorSevResp<MonthSaleListResp> paginatorResponse = new PaginatorSevResp<MonthSaleListResp>();
		List<MonthSaleListResp> list = partnerMapper.queryPartnerMonthSaleListNew(query, page);
		String userId =  (String) query.get("userId");
		for (int i = 0; i < list.size(); i++) {
			MonthSaleListResp  monthSaleList = list.get(i);
			List<MonthSaleListAllowanceListResponse> allowanceList = new ArrayList<MonthSaleListAllowanceListResponse>();
			List<MonthSaleListAllowanceList> allowanceListData = partnerMapper.queryAllwanceListByBillId(monthSaleList.getBillId(), userId);
			for (int j = 0; j < allowanceListData.size(); j++) {
				MonthSaleListAllowanceListResponse re = new MonthSaleListAllowanceListResponse(allowanceListData.get(j));
				allowanceList.add(re);
			}
			
			list.get(i).setAllowanceList(allowanceList);
		}
		
		paginatorResponse.setDatas(list);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorSevResp<CrmCfplanner> queryCfpList(Map<String, Object> query, Page<CrmCfplanner> page) {
		PaginatorSevResp<CrmCfplanner> paginatorResponse = new PaginatorSevResp<CrmCfplanner>();
		paginatorResponse.setDatas(crmCfplannerMapper.queryLowerLevelOneList(query, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryTeamSalesCount(Map<String, Object> query) {
		return partnerMapper.queryTeamSalesCount(query);
	}

	@Override
	public Double queryTeamSalesTotalAmount(Map<String, Object> query) {
		return partnerMapper.queryTeamSalesTotalAmount(query);
	}

	@Override
	public Integer queryGrandChildrenRcCount(String userId) {
		return partnerMapper.queryGrandChildrenRcCount(userId);
	}

	@Override
	public PartnerJobGradeResponse jobGrade(String userId) {
		PartnerJobGradeResponse re = new PartnerJobGradeResponse();	
		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfp != null){			
			CfpLevelWarningResp cfpLevelWarningResp = crmCfpLevelRecordTempService.cfpLevelWarning(userId);
			BeanUtils.copyProperties(cfpLevelWarningResp, re);
			re.setCfpLevelContent(cfpLevelWarningResp.getCfpLevelTitle2());
			
			String jobGrade = cfp.getJobGrade();
			re.setJobGrade(jobGrade);
			re.setJobGradeDesc(CfpJobGradeEnum.getCfpJobGradeEnumByKey(jobGrade).getMsg());
		}
		return re;
	}

	@Override
	public PaginatorResponse<JobGradeVoucherResponse> jobGradeVoucherPage(Page<JobGradeVoucherResponse> page,
			Map<String, Object> conditions) {
		PaginatorResponse<JobGradeVoucherResponse> response = new PaginatorResponse<JobGradeVoucherResponse>();
		List<JobGradeVoucherResponse> memberList = actJobGradeVoucherMapper.jobGradeVoucherPage(page,conditions);
		response.setDatas(memberList);
		response.setValuesByPage(page);
		return response;
	}

	@Override
	public JobGradeVoucherPopupResponse queryNewJobGradeVoucherPopupDate(String userId) {
		return actJobGradeVoucherMapper.queryNewJobGradeVoucherPopupDate(userId);
	}
}
