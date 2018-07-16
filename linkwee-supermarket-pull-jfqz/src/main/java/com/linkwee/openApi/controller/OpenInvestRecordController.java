package com.linkwee.openApi.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.exception.ServiceException;
import com.linkwee.openApi.request.JfqzInvestPushReq;
import com.linkwee.web.model.SysErrorLog;
import com.linkwee.web.service.CimProductInvestRecordPushService;
import com.linkwee.web.service.SysErrorLogService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.OpenResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 开放投资记录接口
 * 
 * @创建人： chen
 * 
 * @创建时间：2018-03-20 15:56:48
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/jfqz")
@RequestLogging("投资记录")
public class OpenInvestRecordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenInvestRecordController.class);

	@Resource
	private CimProductInvestRecordPushService pushService;
	
	@Resource
	private SysErrorLogService sysErrorLogService;
	
	/**
	 * 转换器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	/**
	 * 推送投资记录
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping("/pushInvestRecord")
//	@ResponseBody
	@RequestLogging("推送投资记录")
	public void investRecor(JfqzInvestPushReq req,HttpServletResponse response) throws IOException {
		LOGGER.info("JFQZ============【{}】===============>>>>>>>>start",req.getCode());
		LOGGER.info("JFQZ============【{}】===============>>>>>>>>start",req.getCode());
		LOGGER.info("JFQZ============【{}】===============>>>>>>>>start",req.getCode());
		LOGGER.info("JFQZ============【{}】===============>>>>>>>>  JfqzInvestPushReq={}",req.getCode(), req);
		LOGGER.info("JFQZ============【{}】===============>>>>>>>>end",req.getCode());
		LOGGER.info("JFQZ============【{}】===============>>>>>>>>end",req.getCode());
		LOGGER.info("JFQZ============【{}】===============>>>>>>>>end",req.getCode());
		String result ="SUCCESS";
		try{
			if("001".equals(req.getCode())){
				pushService.createOrderJFQZ(req);
			}else if("002".equals(req.getCode())){
				pushService.createPayOrderJFQZ(req);
			}else if("003".equals(req.getCode())){ 
				pushService.createRepaymentOrderJFQZ(req);
			}else if("011".equals(req.getCode())){
				pushService.createRedemptionTimeJFQZ(req);
			}
		}catch(ServiceException e){
			errorRecord(req,"ServiceException",e);
			LOGGER.warn("JFQZ SERVICEEXCEPTION JfqzInvestPushReq={},exception={}", req,e);
			result = "FAILED";
		}catch(Exception e){
			errorRecord(req,"Exception",e);
			LOGGER.error("JFQZ EXCEPTION JfqzInvestPushReq={},exception={}", req,e);
			result = "FAILED";
		}
		response.setCharacterEncoding("utf-8");
        response.getWriter().write(result);
        response.getWriter().close();
	}
	
	public void errorRecord(JfqzInvestPushReq req,String str,Object e){
		SysErrorLog sysError = new SysErrorLog();
		sysError.setCreateTime(new Date());
		sysError.setMethod("investRecor_"+str);
		sysError.setOrderNo(req.getJfqzInvestRecord().getOrderNo());
		sysError.setReqParm(req.toString());
		sysError.setCause(e.toString());
		sysError.setRemark(req.getCode());
		sysErrorLogService.insert(sysError);
	}
	
	/**
	 * 玖富轻舟获取token接口
	 * @param result
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping("/getToken")
	@ResponseBody
	public String getToken() {
		System.out.println("===========================>>>>>>>>>>>玖富轻舟获取token接口");
		System.out.println("===========================>>>>>>>>>>>玖富轻舟获取token接口");
		System.out.println("===========================>>>>>>>>>>>玖富轻舟获取token接口");
		System.out.println("===========================>>>>>>>>>>>玖富轻舟获取token接口");
		return OpenResponseUtil.getSuccessResponse();
	}
	
}
