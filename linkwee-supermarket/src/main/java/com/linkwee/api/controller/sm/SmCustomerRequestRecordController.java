package com.linkwee.api.controller.sm;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.sm.AddRequestRecordRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.web.service.SmCustomerRequestRecordService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： SmCustomerRequestRecordController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月23日 09:46:16
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@RestController
@RequestMapping(value = "/api/trace")
@RequestLogging("SmCustomerRequestRecordController控制器")
public class SmCustomerRequestRecordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmCustomerRequestRecordController.class);

	@Resource
	private SmCustomerRequestRecordService smCustomerRequestRecordService;
	
	/**
	 * 添加请求记录
	 * @param appRequestHead
	 * @param myInvestrecordRequest
	 * @return
	 */
	@RequestLogging("添加请求记录")
	@RequestMapping("/add")
	public BaseResponse addRequestRecord(AppRequestHead appRequestHead,@Valid AddRequestRecordRequest addRequestRecordRequest,BindingResult result){
		LOGGER.info("添加请求记录, AddRequestRecordRequest={}", JSONObject.toJSONString(addRequestRecordRequest));
    	if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		BaseResponse baseResponse = smCustomerRequestRecordService.addRequestRecord(appRequestHead,addRequestRecordRequest);
		return baseResponse;
	}
}
