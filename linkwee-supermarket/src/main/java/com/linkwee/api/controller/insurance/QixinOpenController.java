package com.linkwee.api.controller.insurance;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.web.service.CimInsuranceInfoService;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.insurance.qixin.QixinCommonUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping(value = "openthird/openqixin")
@RequestLogging("齐欣云服开放服务")
public class QixinOpenController extends BaseController{
	
	@Resource
	private CimInsuranceInfoService	cimInsuranceInfoService;
	
	@ResponseBody
	@RequestMapping("/notify")
	@RequestLogging("齐欣云服消息通知")
	public Object notify(HttpServletRequest request){
		try {	
			//接收json
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			
			String preJsonString = sb.toString();
			LOGGER.info("接收齐欣云服消息通知  原始json串 preJsonString={}",preJsonString);
			if(StringUtils.isEmpty(preJsonString)){
				return QixinCommonUtils.getNotifyFailed("领会接收齐欣云服消息通知为空");
			} else {
				return cimInsuranceInfoService.notify(preJsonString);
			}
		} catch (Exception e) {
			LOGGER.error("领会接收齐欣云服消息通知异常",e);
			return QixinCommonUtils.getNotifyFailed("领会接收齐欣云服消息通知异常");
		}
	}
}
