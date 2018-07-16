package com.linkwee.openapi.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.openapi.request.AccountBankRequest;
import com.linkwee.openapi.response.AccountBankReponse;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.xoss.api.OpenRequestHead;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.OpenResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述：账户信息接口
 * 
 * @创建人： 陈佳良
 * 
 * @创建时间：2016-08-04 10:11:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "openapi/account")
@RequestLogging("账户信息")
public class OpenAccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenAccountController.class);
	
	@Resource
	private AcAccountBindService accountbindService;
	
	/**
	 * 投资客户的银行账户查询
	 * @throws Exception 
	 */
	@RequestMapping("/bank")
	@ResponseBody
	public Object investRecor(@Valid AccountBankRequest req,BindingResult valid,OpenRequestHead requestHead) {
		if (OpenResponseUtil.existsParamsError(valid)) {
			return OpenResponseUtil.getErrorParams(valid);
		}
		AcAccountBind bind =  null;
		LOGGER.info("开放平台投资客户的银行账户查询, accountBankRequest={}",JSONObject.toJSONString(req));
		try{
			bind = accountbindService.selectAccountByUserId(req.getUserId());
		}catch(ServiceException e){
			LOGGER.error("OpenAccount serviceException AccountBankRequest={},exception={}", req,e);
			return OpenResponseUtil.getErrorBusi(new BaseResponse("error", e.getMessage()));
		}catch(Exception e){
			LOGGER.error("OpenAccount exception AccountBankRequest={},exception={}", req,e);
		}
		LOGGER.info("开放平台投资客户的银行账户查询返回绑卡信息, bind={}",JSONObject.toJSONString(bind));
		if(bind == null){
			return OpenResponseUtil.getErrorBusi(new BaseResponse("-1", "投资客户未绑定银行卡账户,绑定信息不存在"));
		} else {
			return OpenResponseUtil.getSuccessResponse(bind,AccountBankReponse.class);
		}
	}
	
	/**
	 * 转换器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
}
