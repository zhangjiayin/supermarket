package com.linkwee.api.controller.cim;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.AllAssessRequest;
import com.linkwee.api.request.cim.OrgMoneyDataRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.service.CimOrgRiskManageSynthesizeService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimOrgRiskManageSynthesizeController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 14:14:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "api/cimorgriskmanagesynthesize")
@RequestLogging("CimOrgRiskManageSynthesizeController控制器")
public class CimOrgRiskManageSynthesizeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRiskManageSynthesizeController.class);

	@Resource
	private CimOrgRiskManageSynthesizeService cimOrgRiskManageSynthesizeService;

	
	/**
	 * 机构风险综合评定
	 * @param head
	 * @param allAssessRequest
	 * @return
	 */
	@ResponseBody
	@RequestLogging("机构风险综合评定")
	@RequestMapping("/allassess")
	public BaseResponse allAssess(AppRequestHead head,AllAssessRequest allAssessRequest){
		LOGGER.info("查询机构综合评定, allAssessRequest={}", JSONObject.toJSONString(allAssessRequest));
		if(StringUtils.isNotBlank(allAssessRequest.getOrgNo())){
			return AppResponseUtil.getSuccessResponse(cimOrgRiskManageSynthesizeService.getAllAssess(allAssessRequest));
		} else {
			return AppResponseUtil.getErrorData("-1", "机构编码不能为空");
		}
	}
	
	
	/**
	 * 平台数据
	 * @param head
	 * @param allAssessRequest
	 * @return
	 */
	@ResponseBody
	@RequestLogging("平台数据")
	@RequestMapping("/orgdata")
	public BaseResponse orgdata(AppRequestHead head,OrgMoneyDataRequest orgMoneyDataRequest){
		LOGGER.info("平台数据查询, orgMoneyDataRequest={}", JSONObject.toJSONString(orgMoneyDataRequest));
		if(StringUtils.isNotBlank(orgMoneyDataRequest.getOrgNo())){
			return AppResponseUtil.getSuccessResponse(cimOrgRiskManageSynthesizeService.orgdata(orgMoneyDataRequest));
		} else {
			return AppResponseUtil.getErrorData("-1", "机构编码不能为空");
		}
	}
}
