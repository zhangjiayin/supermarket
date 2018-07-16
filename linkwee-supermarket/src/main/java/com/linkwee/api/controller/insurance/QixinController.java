package com.linkwee.api.controller.insurance;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.CimInsuranceQuestionSummaryRequest;
import com.linkwee.api.request.insurance.qixin.QixinGotoBaseRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceListRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceSiftRequest;
import com.linkwee.api.request.insurance.qixin.QixinProductDetailGotoRequest;
import com.linkwee.api.response.insurance.qixin.InsuranceQuestionRecom;
import com.linkwee.api.response.insurance.qixin.InsuranceQuestionResultReponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CimInsuranceProductExtends;
import com.linkwee.web.model.cim.CimInsuranceQuestionSummary;
import com.linkwee.web.service.CimInsuranceCateService;
import com.linkwee.web.service.CimInsuranceQuestionSummaryService;
import com.linkwee.web.service.QixinBaseService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping(value = "/api/insurance/qixin")
@RequestLogging("齐欣云服")
public class QixinController extends BaseController{

	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private QixinBaseService qixinBaseService;
	@Resource
	private CimInsuranceQuestionSummaryService cimInsuranceQuestionSummaryService;
	@Resource
	private CimInsuranceCateService cimInsuranceCateService;
	
	@ResponseBody
	@RequestMapping("/insuranceSift")
	@RequestLogging("精选保险")
	public BaseResponse insuranceSift(QixinInsuranceSiftRequest qixinInsuranceSiftRequest){
		
		String caseCodes = sysConfigService.getValuesByKey("insurance_sift_code");//精选保险方案代码
		qixinInsuranceSiftRequest.setCaseCode(caseCodes);
		LOGGER.info("【齐欣云服】获取精选保险信息	qixinInsuranceSiftRequest={}",JSONObject.toJSONString(qixinInsuranceSiftRequest));
		CimInsuranceProductExtends cimInsuranceProduct = qixinBaseService.insuranceSift(qixinInsuranceSiftRequest);
		return AppResponseUtil.getSuccessResponse(cimInsuranceProduct);
	}
	
	@ResponseBody
	@RequestMapping("/insuranceSelect")
	@RequestLogging("甄选保险")
	public BaseResponse insuranceSelect(){
		List<CimInsuranceProductExtends> cimInsuranceProductExtendsList = qixinBaseService.insuranceSelect();
		return AppResponseUtil.getSuccessResponse(cimInsuranceProductExtendsList);
	}
	
	@ResponseBody
	@RequestMapping("/insuranceList")
	@RequestLogging("保险列表")
	public BaseResponse insuranceList(QixinInsuranceListRequest qixinInsuranceListRequest){
		LOGGER.info("【齐欣云服】获取保险列表信息	qixinInsuranceListRequest={}",JSONObject.toJSONString(qixinInsuranceListRequest));
		Page<CimInsuranceProductExtends> page  = new Page<CimInsuranceProductExtends>(qixinInsuranceListRequest.getPageIndex(),qixinInsuranceListRequest.getPageSize());
		PaginatorResponse<CimInsuranceProductExtends> rlt = qixinBaseService.insuranceList(qixinInsuranceListRequest,page);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	@ResponseBody
	@RequestMapping("/gotoProductDetail")
	@RequestLogging("跳转保险产品详情页面")
	public BaseResponse gotoProductDetail(AppRequestHead head,QixinProductDetailGotoRequest qixinProductDetailGotoRequest){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
		if(!"undefined".equals(userId)){
			qixinProductDetailGotoRequest.setUserId(userId);
			qixinProductDetailGotoRequest.setPlatform(AppUtils.getPlatform(head.getOrgNumber()).getValue());//设置平台类型
			LOGGER.info("【齐欣云服】跳转保险产品详情页面	qixinProductDetailGotoRequest={}",JSONObject.toJSONString(qixinProductDetailGotoRequest));
			return AppResponseUtil.getSuccessResponse(qixinBaseService.gotoProductDetail(qixinProductDetailGotoRequest));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/gotoPersonInsureList")
	@RequestLogging("跳转保险个人保单页面")
	public BaseResponse gotoPersonInsureList(AppRequestHead head,QixinGotoBaseRequest qixinGotoBaseRequest){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
		if(!"undefined".equals(userId)){
			qixinGotoBaseRequest.setUserId(userId);
			qixinGotoBaseRequest.setPlatform(AppUtils.getPlatform(head.getOrgNumber()).getValue());//设置平台类型
			LOGGER.info("【齐欣云服】跳转保险个人保单页面	qixinGotoBaseRequest={}",JSONObject.toJSONString(qixinGotoBaseRequest));
			return AppResponseUtil.getSuccessResponse(qixinBaseService.gotoPersonInsureList(qixinGotoBaseRequest));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/insuranceCategory")
	@RequestLogging("保险种类")
	public BaseResponse insuranceCategory(){
		return AppResponseUtil.getSuccessResponse(cimInsuranceCateService.getCategoryList());
	}
	
	/**
	 * 保险评测接口(questionSummary)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/questionSummary")
	@ResponseBody
	public BaseResponse questionSummary(@Valid CimInsuranceQuestionSummaryRequest req,BindingResult result,AppRequestHead head) throws Exception {
		LOGGER.info("保险评测接口  CimInsuranceQuestionSummaryRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse("-1","token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		try {
			cimInsuranceQuestionSummaryService.insertCimInsuranceQuestionSummary(userId,req);
			
		} catch (Exception e) {
			return  new BaseResponse("-1","保险评测接口保存失败");
		}
		return AppResponseUtil.getSuccessResponse();
		
	}
	
	/**
	 * 保险评测结果(queryQquestionResult)
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/queryQquestionResult")
	@ResponseBody
	public BaseResponse queryQquestionResult(AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse("-1","token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CimInsuranceQuestionSummary  summary = null;
		InsuranceQuestionResultReponse  rlt = new InsuranceQuestionResultReponse();
		try {
			summary = cimInsuranceQuestionSummaryService.queryQquestionResult(userId);
		} catch (Exception e) {
			return  new BaseResponse("-1","查询保险评测结果失败");
		}
		rlt.setTotalScore(summary==null||summary.getTotalScore()==null?"0":summary.getTotalScore()+"");
		rlt.setRiskGrade(summary==null?"":summary.getRiskGrade());
		rlt.setRecomList(summary==null?new ArrayList<InsuranceQuestionRecom>():summary.getRecomList());
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	
}
