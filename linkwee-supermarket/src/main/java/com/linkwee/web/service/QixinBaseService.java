package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.insurance.qixin.QixinGotoBaseRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceListRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceSiftRequest;
import com.linkwee.api.request.insurance.qixin.QixinProductDetailGotoRequest;
import com.linkwee.api.response.insurance.common.ReportRecommendResponse;
import com.linkwee.api.response.insurance.qixin.GotoPersonInsureListResponse;
import com.linkwee.api.response.insurance.qixin.GotoProductDetailResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceProductExtends;
import com.linkwee.web.model.cim.CimInsuranceQuestionSummary;


public interface QixinBaseService {

	/**
	 * 精选保险
	 * @param qixinInsuranceSiftRequest
	 * @return
	 */
	CimInsuranceProductExtends insuranceSift(QixinInsuranceSiftRequest qixinInsuranceSiftRequest);
	
	/**
	 * 保险列表
	 * @param qixinInsuranceListRequest
	 * @param page
	 * @return
	 */
	PaginatorResponse<CimInsuranceProductExtends> insuranceList(QixinInsuranceListRequest qixinInsuranceListRequest,Page<CimInsuranceProductExtends> page);

	/**
	 * 产品详情跳转
	 * @param qixinGotoBaseRequest
	 * @return
	 */
	GotoProductDetailResponse gotoProductDetail(QixinProductDetailGotoRequest qixinProductDetailGotoRequest);

	/**
	 * 个人保单列表跳转
	 * @param qixinGotoBaseRequest
	 * @return
	 */
	GotoPersonInsureListResponse gotoPersonInsureList(QixinGotoBaseRequest qixinGotoBaseRequest);

	/**
	 * 甄选保险
	 * @return
	 */
	List<CimInsuranceProductExtends> insuranceSelect();

	/**
	 * 保险评测汇总
	 * @param userId
	 * @return
	 */
	CimInsuranceQuestionSummary testReportResult(String userId);
	
	/**
	 * 保险推荐列表
	 * @param queryType
	 * @return
	 */
	List<ReportRecommendResponse> testReportRecommend(String queryType);
}
