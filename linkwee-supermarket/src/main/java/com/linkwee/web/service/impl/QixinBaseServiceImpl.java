package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.insurance.qixin.QixinGotoBaseRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceListRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceSiftRequest;
import com.linkwee.api.request.insurance.qixin.QixinProductDetailGotoRequest;
import com.linkwee.api.response.insurance.common.ReportRecommendResponse;
import com.linkwee.api.response.insurance.qixin.GotoPersonInsureListResponse;
import com.linkwee.api.response.insurance.qixin.GotoProductDetailResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceInfo;
import com.linkwee.web.model.CimInsuranceProductExtends;
import com.linkwee.web.model.cim.CimInsuranceQuestionSummary;
import com.linkwee.web.service.CimInsuranceInfoService;
import com.linkwee.web.service.CimInsuranceProductService;
import com.linkwee.web.service.CimInsuranceQuestionSummaryService;
import com.linkwee.web.service.CimInsuranceRecommendService;
import com.linkwee.web.service.QixinBaseService;
import com.linkwee.xoss.insurance.qixin.QixinCommonUtils;

@Service("qixinBaseService")
public class QixinBaseServiceImpl implements QixinBaseService {
	
	protected  final Logger LOGGER = LoggerFactory.getLogger(QixinBaseServiceImpl.class);
	
	@Resource
	private CimInsuranceInfoService cimInsuranceInfoService;
	@Resource
	private CimInsuranceProductService cimInsuranceProductService;
	@Resource
	private CimInsuranceQuestionSummaryService cimInsuranceQuestionSummaryService;
	@Resource
	private CimInsuranceRecommendService cimInsuranceRecommendService;

	@Override
	public CimInsuranceProductExtends insuranceSift(QixinInsuranceSiftRequest qixinInsuranceSiftRequest) {
		CimInsuranceProductExtends cimInsuranceProductExtends = new CimInsuranceProductExtends();
		cimInsuranceProductExtends = cimInsuranceProductService.selectinsuranceSift(qixinInsuranceSiftRequest);
		return cimInsuranceProductExtends;
	}
	
	@Override
	public PaginatorResponse<CimInsuranceProductExtends> insuranceList(QixinInsuranceListRequest qixinInsuranceListRequest,Page<CimInsuranceProductExtends> page) {	
		PaginatorResponse<CimInsuranceProductExtends> paginatorResponse = new PaginatorResponse<CimInsuranceProductExtends>();
		List<CimInsuranceProductExtends> insuranceSiftPageListResponseList = cimInsuranceProductService.insuranceList(qixinInsuranceListRequest,page);
		paginatorResponse.setDatas(insuranceSiftPageListResponseList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public GotoProductDetailResponse gotoProductDetail(QixinProductDetailGotoRequest qixinProductDetailGotoRequest) {
		
		GotoProductDetailResponse gotoProductDetailResponse = new GotoProductDetailResponse();
		
		CimInsuranceInfo cimInsuranceInfo = cimInsuranceInfoService.selectByOrgNumber(qixinProductDetailGotoRequest.getOrgCode());//获取保险机构信息
		gotoProductDetailResponse.setRequestUrl(cimInsuranceInfo.getProductDetailUrl());
		gotoProductDetailResponse.setPartnerId(cimInsuranceInfo.getPartnerId());
		gotoProductDetailResponse.setPartnerUniqKey(qixinProductDetailGotoRequest.getUserId());
		gotoProductDetailResponse.setPlatform(QixinCommonUtils.platformChangeTb(qixinProductDetailGotoRequest.getPlatform()));
		gotoProductDetailResponse.setCaseCode(qixinProductDetailGotoRequest.getCaseCode());
		gotoProductDetailResponse.setSign(QixinCommonUtils.getSign(cimInsuranceInfo));
		LOGGER.info("【齐欣云服】跳转保险产品详情页面返回	gotoProductDetailResponse={}",JSONObject.toJSONString(gotoProductDetailResponse));
		return gotoProductDetailResponse;
	}

	@Override
	public GotoPersonInsureListResponse gotoPersonInsureList(QixinGotoBaseRequest qixinGotoBaseRequest) {
		GotoPersonInsureListResponse gotoPersonInsureListResponse = new GotoPersonInsureListResponse();
		CimInsuranceInfo cimInsuranceInfo = cimInsuranceInfoService.selectByOrgNumber(qixinGotoBaseRequest.getOrgCode());//获取保险机构信息
		gotoPersonInsureListResponse.setRequestUrl(cimInsuranceInfo.getInsureListUrl());
		gotoPersonInsureListResponse.setPartnerId(cimInsuranceInfo.getPartnerId());
		gotoPersonInsureListResponse.setPartnerUniqKey(qixinGotoBaseRequest.getUserId());
		gotoPersonInsureListResponse.setPlatform(QixinCommonUtils.platformChangeTb(qixinGotoBaseRequest.getPlatform()));
		gotoPersonInsureListResponse.setSign(QixinCommonUtils.getSign(cimInsuranceInfo));
		LOGGER.info("【齐欣云服】跳转保险个人保单页面返回	gotoPersonInsureListResponse={}",JSONObject.toJSONString(gotoPersonInsureListResponse));
		return gotoPersonInsureListResponse;
	}

	@Override
	public List<CimInsuranceProductExtends> insuranceSelect() {
		// TODO Auto-generated method stub
		List<CimInsuranceProductExtends> cimInsuranceProductExtendsList =  new ArrayList<CimInsuranceProductExtends>();
		List<CimInsuranceProductExtends> cimInsuranceProductExtendsListNew = cimInsuranceProductService.insuranceSelect();
		if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendsListNew)){
			cimInsuranceProductExtendsList.add(cimInsuranceProductExtendsListNew.get(0));
		}
		return cimInsuranceProductExtendsList;
	}

	@Override
	public CimInsuranceQuestionSummary testReportResult(String userId) {
		// TODO Auto-generated method stub
		CimInsuranceQuestionSummary cimInsuranceQuestionSummary = new CimInsuranceQuestionSummary();
		cimInsuranceQuestionSummary.setUserId(userId);
		cimInsuranceQuestionSummary = cimInsuranceQuestionSummaryService.selectOne(cimInsuranceQuestionSummary);
		return cimInsuranceQuestionSummary;
	}

	@Override
	public List<ReportRecommendResponse> testReportRecommend(String queryType) {
		// TODO Auto-generated method stub
		List<ReportRecommendResponse> reportRecommendResponseList = new ArrayList<ReportRecommendResponse>();
		
		if("1".equals(queryType)){//本人男或者配偶女
			//重疾险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist5 = cimInsuranceRecommendService.getInsuranceProductByType("5",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist5)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("重疾险");
				reportRecommendResponse.setCoverage(30);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist5);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//意外险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist1 = cimInsuranceRecommendService.getInsuranceProductByType("1",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist1)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("意外险");
				reportRecommendResponse.setCoverage(50);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist1);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//定期寿险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist7 = cimInsuranceRecommendService.getInsuranceProductByType("7",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist7)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("定期寿险");
				reportRecommendResponse.setCoverage(50);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist7);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//医疗险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist4 = cimInsuranceRecommendService.getInsuranceProductByType("4",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist4)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("医疗险");
				reportRecommendResponse.setCoverage(200);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist4);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//财产险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist3 = cimInsuranceRecommendService.getInsuranceProductByType("3",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist3)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("财产险");
				reportRecommendResponse.setCoverage(0);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist3);
				reportRecommendResponseList.add(reportRecommendResponse);
			}			
			
			
		} else if("2".equals(queryType)){//本人女或者配偶男
			
			//重疾险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist5 = cimInsuranceRecommendService.getInsuranceProductByType("5",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist5)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("重疾险");
				reportRecommendResponse.setCoverage(30);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist5);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//意外险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist1 = cimInsuranceRecommendService.getInsuranceProductByType("1",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist1)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("意外险");
				reportRecommendResponse.setCoverage(30);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist1);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//医疗险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist4 = cimInsuranceRecommendService.getInsuranceProductByType("4",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist4)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("医疗险");
				reportRecommendResponse.setCoverage(200);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist4);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
		} else if("3".equals(queryType)){//父亲,母亲,配偶父亲,配偶母亲
			
			//重疾险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist5 = cimInsuranceRecommendService.getInsuranceProductByType("5",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist5)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("重疾险");
				reportRecommendResponse.setCoverage(10);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist5);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//意外险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist1 = cimInsuranceRecommendService.getInsuranceProductByType("1",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist1)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("意外险");
				reportRecommendResponse.setCoverage(20);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist1);
				reportRecommendResponseList.add(reportRecommendResponse);
			}			
			
		} else if("4".equals(queryType)){//子女1,子女2
			
			//重疾险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist5 = cimInsuranceRecommendService.getInsuranceProductByType("5",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist5)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("重疾险");
				reportRecommendResponse.setCoverage(30);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist5);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//意外险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist1 = cimInsuranceRecommendService.getInsuranceProductByType("1",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist1)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("意外险");
				reportRecommendResponse.setCoverage(2);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist1);
				reportRecommendResponseList.add(reportRecommendResponse);
			}
			
			//医疗险
			List<CimInsuranceProductExtends> cimInsuranceProductExtendslist4 = cimInsuranceRecommendService.getInsuranceProductByType("4",queryType);
			if(CollectionUtils.isNotEmpty(cimInsuranceProductExtendslist4)){
				ReportRecommendResponse reportRecommendResponse = new ReportRecommendResponse();
				reportRecommendResponse.setCategoryName("医疗险");
				reportRecommendResponse.setCoverage(200);
				reportRecommendResponse.setCimInsuranceProductExtendsList(cimInsuranceProductExtendslist4);
				reportRecommendResponseList.add(reportRecommendResponse);
			}			
		}
		
		return reportRecommendResponseList;
	}
}
