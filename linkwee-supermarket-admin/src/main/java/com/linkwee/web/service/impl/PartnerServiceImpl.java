package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CrmUserInfoMapper;
import com.linkwee.web.dao.PartnerMapper;
import com.linkwee.web.enums.CfpLevelEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.PartnerDetailResp;
import com.linkwee.web.model.crm.PartnerListResp;
import com.linkwee.web.model.crm.PartnerSaleRecordResp;
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
	 * 未读团队成员数量
	 * @param userId
	 * @param date
	 * @return
	 */
	@Override
	public Integer queryNewPartnerCount(String userId, Date date) {
		return partnerMapper.queryNewPartnerCount(userId, date);
	}


	
	

	
	
}
