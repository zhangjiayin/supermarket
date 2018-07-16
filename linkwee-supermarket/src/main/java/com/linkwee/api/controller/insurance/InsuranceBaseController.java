package com.linkwee.api.controller.insurance;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.web.service.CimInsuranceQuestionSummaryService;
import com.linkwee.web.service.QixinBaseService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping(value = "/api/insurance/base")
@RequestLogging("保险")
public class InsuranceBaseController  extends BaseController{

	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private QixinBaseService qixinBaseService;
	@Resource
	private CimInsuranceQuestionSummaryService cimInsuranceQuestionSummaryService;
	
	@ResponseBody
	@RequestMapping("/testReportResult")
	@RequestLogging("测试报告结果")
	public BaseResponse testReportResult(AppRequestHead head){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
		if(!"undefined".equals(userId)){
			return AppResponseUtil.getSuccessResponse(qixinBaseService.testReportResult(userId));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/testReportRecommend")
	@RequestLogging("测试报告保险推荐")
	public BaseResponse testReportRecommend(String queryType){
		LOGGER.info("测试报告保险推荐  queryType={}",queryType);      
		if(queryType != null){
			return AppResponseUtil.getSuccessResponse(qixinBaseService.testReportRecommend(queryType));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
}
