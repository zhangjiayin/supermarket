package com.linkwee.api.controller.cim;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.MyInvestrecordRequest;
import com.linkwee.api.response.cim.MyInvestrecordResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimProductInvestRecordController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年06月23日 14:36:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "api/productinvestrecord")
@RequestLogging("产品投资记录")
public class ProductInvestRecordController extends BaseController {

	@Resource
	private CimProductInvestRecordService cimProductInvestRecordService;

	/**
	 * 我的投资记录v4.0
	 * @param appRequestHead
	 * @param myInvestrecordRequest
	 * @return
	 */
	@ResponseBody
	@RequestLogging("我的投资记录v4.0")
	@RequestMapping("/myInvestrecord")
	public BaseResponse myInvestrecord(AppRequestHead appRequestHead,MyInvestrecordRequest myInvestrecordRequest){
		LOGGER.info("查询我的投资记录v4.0, myInvestrecordRequest={}", JSONObject.toJSONString(myInvestrecordRequest));
		PaginatorResponse<MyInvestrecordResponse> rlt = cimProductInvestRecordService.myInvestrecord(appRequestHead,myInvestrecordRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
}
