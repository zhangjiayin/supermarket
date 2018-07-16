package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.linkwee.api.response.cim.OrgAUserInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.CimProductUnrecordInvestRequest;
import com.linkwee.api.request.cim.CimSunburnPageListRequest;
import com.linkwee.api.request.tc.UnRecordInvestRequest;
import com.linkwee.api.response.cim.CimSunburnListResponse;
import com.linkwee.api.response.tc.CfplannerUnrecordInvestResponse;
import com.linkwee.api.response.tc.CustomerUnrecordInvestResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.CimProductUnrecordInvestMapper;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CimProductUnrecordInvest;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.cim.CimUnrecordInvestListResp;
import com.linkwee.web.request.tc.UnrecordInvestRequest;
import com.linkwee.web.response.tc.FeeDetailRecordResponse;
import com.linkwee.web.service.ActSignRecordService;
import com.linkwee.web.service.CimOrgFeeTimetaskService;
import com.linkwee.web.service.CimOrgInfoAService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductUnrecordInvestService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;


 /**
 * 
 * @描述：CimProductUnrecordInvestService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductUnrecordInvestService")
public class CimProductUnrecordInvestServiceImpl extends GenericServiceImpl<CimProductUnrecordInvest, Long> implements CimProductUnrecordInvestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductUnrecordInvestServiceImpl.class);
	
	@Autowired
	private CimProductUnrecordInvestMapper unrecordInvestMapper;
	
	@Autowired
	private CrmInvestorService  investorService ;
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@Autowired
	private CimOrgFeeTimetaskService cimOrgFeeTimetaskService;
	
	@Autowired
	private CimOrgInfoAService cimOrgInfoAService;
	
	@Resource
	private ActSignRecordService signRecordService;
	
	@Override
    public GenericDao<CimProductUnrecordInvest, Long> getDao() {
        return unrecordInvestMapper;
    }


	@Override
	public BaseResponse inserUnrecordInvest(UnRecordInvestRequest req, String cfpId) {
		try{
			Date date = new Date(); 
			Date investTime = DateUtils.parse(req.getInvestTime());
			CrmInvestor investor  = investorService.queryInvestorByMobile(req.getMobile());
			CimProductUnrecordInvest unrecordInvest = new CimProductUnrecordInvest();
			if(null!=investor)unrecordInvest.setUserId(investor.getUserId());
			CimOrginfo orginfo = new CimOrginfo();
			orginfo.setOrgNumber(req.getPlatfrom());
			orginfo.setOrgIsstaticproduct(1);
			orginfo = 	orginfoService.selectOne(orginfo);
			
			if(orginfo==null)return AppResponseUtil.getErrorBusi("100002", "平台不存在 ");
			
			unrecordInvest.setCfplannerId(cfpId);
			unrecordInvest.setUserMobile(req.getMobile());
			unrecordInvest.setUserName(req.getName());
			unrecordInvest.setPlatfrom(orginfo.getOrgNumber());
			unrecordInvest.setPlatfromName(orginfo.getName());
			unrecordInvest.setFeeRate(cimOrgFeeTimetaskService.queryOrgCurrentBuyDateFee(orginfo.getOrgNumber(), investTime));
			unrecordInvest.setProductName(req.getProductName());
			unrecordInvest.setProductDeadLineType(req.getProductDeadLineType());
			unrecordInvest.setProductDeadLine(String.valueOf(req.getProductDeadLine()));
			int deadLineValue = 0 ;
			switch (req.getProductDeadLineType()) {
			case 1:
				deadLineValue = req.getProductDeadLine(); break;
			case 2:
				deadLineValue = req.getProductDeadLine() * 30; break;
			case 3:
				deadLineValue = req.getProductDeadLine() * 360; break;
			default:
				break;
			}
			unrecordInvest.setProductDeadLineValue(deadLineValue);
			unrecordInvest.setInvestAmt(req.getInvestAmt());
			unrecordInvest.setInvestTime(investTime);
			unrecordInvest.setInvestImg(req.getInvestImg());
			unrecordInvest.setCreateTime(date);
			unrecordInvest.setUpdateTime(date);
			if(insert(unrecordInvest)>0 )
				return AppResponseUtil.getSuccessResponse();
		}catch (Exception e) {
			LOGGER.error("inserUnrecordInvest Exception UnRecordInvestRequest={},req={},exception={}",new Object[]{req,cfpId,e});
		}
		return AppResponseUtil.getErrorBusi("100002", "保存记录失败,请重试");
	}

	@Override
	public DataTableReturn getUnrecordInvestList(UnrecordInvestRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<FeeDetailRecordResponse> page = new Page<FeeDetailRecordResponse>(req.getStart()/req.getLength()+1,req.getLength());
		tableReturn.setData(unrecordInvestMapper.getUnrecordInvestList(req.getMobile(), req.getStatus(), page));
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<CustomerUnrecordInvestResponse> getCustomerUnrecordInvest(String userId, Page<CustomerUnrecordInvestResponse> page) {
		PaginatorResponse<CustomerUnrecordInvestResponse> paginatorResponse = new PaginatorResponse<CustomerUnrecordInvestResponse>();
		paginatorResponse.setDatas(unrecordInvestMapper.getCustomerUnrecordInvest(userId, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<CfplannerUnrecordInvestResponse> getCfplannerUnrecordInvest(String userId, Page<CfplannerUnrecordInvestResponse> page) {
		PaginatorResponse<CfplannerUnrecordInvestResponse> paginatorResponse = new PaginatorResponse<CfplannerUnrecordInvestResponse>();
		paginatorResponse.setDatas(unrecordInvestMapper.getCfplannerUnrecordInvest(userId, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}


	@Override
	public int getCfplannerUnrecordInvestCount(String userId) {
		return unrecordInvestMapper.getCfplannerUnrecordInvestCount(userId);
	}


	@Override
	public PaginatorResponse<CimUnrecordInvestListResp> unrecordPageList(Integer order,String userId,
			Page<CimUnrecordInvestListResp> page) {
		PaginatorResponse<CimUnrecordInvestListResp> paginatorResponse = new PaginatorResponse<CimUnrecordInvestListResp>();
		List<CimUnrecordInvestListResp> list = unrecordInvestMapper.unrecordPageList(order,userId,page);
		paginatorResponse.setDatas(list);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}


	@Override
	public BaseResponse reportRecord(CimProductUnrecordInvestRequest cimProductUnrecordInvestRequest) {
		
		try {
			LOGGER.info("客户app手动报单  CimProductUnrecordInvestRequest={}",JSONObject.toJSONString(cimProductUnrecordInvestRequest));
			CimProductUnrecordInvest cimProductUnrecordInvest = new CimProductUnrecordInvest();
			BeanUtils.copyProperties(cimProductUnrecordInvestRequest, cimProductUnrecordInvest);
			
			//获取投资人相关信息
			CrmInvestor crmInvestor = investorService.queryInvestorByUserId(cimProductUnrecordInvestRequest.getUserId());
			cimProductUnrecordInvest.setCfplannerId(crmInvestor.getCfplanner());
			cimProductUnrecordInvest.setUserMobile(crmInvestor.getMobile());
			cimProductUnrecordInvest.setUserName(crmInvestor.getUserName());

			//查询A平台机构信息
			CimOrgInfoA cimOrgInfoA = cimOrgInfoAService.queryOrginfoa(cimProductUnrecordInvestRequest.getPlatfrom());
			cimProductUnrecordInvest.setPlatfromName(cimOrgInfoA.getName());
			cimProductUnrecordInvest.setProductDeadLineType(1);//默认为天
			cimProductUnrecordInvest.setProductDeadLine(cimProductUnrecordInvest.getProductDeadLineValue()+"天");
			cimProductUnrecordInvest.setFeeRate(cimOrgInfoA.getOrgFeeRatio());
			cimProductUnrecordInvest.setFeeAmt(cimProductUnrecordInvestRequest.getInvestAmt().multiply(cimOrgInfoA.getOrgFeeRatio()).divide(new BigDecimal(100)));
			cimProductUnrecordInvest.setEndTime(DateUtils.addDay(cimProductUnrecordInvestRequest.getInvestTime(), cimProductUnrecordInvest.getProductDeadLineValue()));
			
			cimProductUnrecordInvest.setStatus(0);//未审核
			cimProductUnrecordInvest.setRemark("客户app手动报单");
			cimProductUnrecordInvest.setCreateTime(new Date());
			cimProductUnrecordInvest.setUpdateTime(new Date());
			unrecordInvestMapper.insertSelective(cimProductUnrecordInvest);
			return AppResponseUtil.getSuccessResponse();
		} catch (Exception e) {
			LOGGER.error("客户app手动报单异常",e);
			return AppResponseUtil.getErrorData("100001", "报单异常,请稍后再试");
		}
	}


	@Override
	public PaginatorResponse<CimSunburnListResponse> sunburnPageList(AppRequestHead appRequestHead, CimSunburnPageListRequest request) {
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());
		request.setUserId(userId);		
		PaginatorResponse<CimSunburnListResponse> paginatorResponse = new PaginatorResponse<CimSunburnListResponse>();
		Page<CimSunburnListResponse> page = new Page<CimSunburnListResponse>(request.getPageIndex(), request.getPageSize());
		List<CimSunburnListResponse> productPageListResponses = unrecordInvestMapper.sunburnPageList(request,page);
		paginatorResponse.setDatas(productPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}


	@Override
	public void thumbsUp(int id) {
		unrecordInvestMapper.thumbsUp(id);
	}

	@Override
	public void sunburn(int id,String userId,String image) {
		CimProductUnrecordInvest req = new CimProductUnrecordInvest();
		req.setId(id);
		req.setShareStatus(1);
		req.setUpdateTime(new Date());
		if(image!=null){
			req.setInvestImg(image);
		}
		unrecordInvestMapper.updateByPrimaryKeySelective(req);
		
		int bountyType = 4;
		signRecordService.sendBounty(userId,1,new BigDecimal(0.2),bountyType);
		
	}


	@Override
	public Double orgAtotalAmount(String userId) {
		return unrecordInvestMapper.orgAtotalAmount(userId);
	}

	@Override
	public CimUnrecordInvestListResp sunburnDetail(String id) {
		return unrecordInvestMapper.sunburnDetail(id);
	}

     @Override
     public OrgAUserInfoResponse orgAUserInfo(String userId) {
		return unrecordInvestMapper.orgAUserInfo(userId);
     }
 }
