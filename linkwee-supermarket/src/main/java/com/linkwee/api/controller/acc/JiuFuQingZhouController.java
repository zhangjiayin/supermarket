package com.linkwee.api.controller.acc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.response.acc.JFQZtokenReponse;
import com.linkwee.api.response.acc.TokenResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.HttpClientUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述：账户相关接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月19日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/jfqz")
@RequestLogging("玖富轻舟相关接口")
public class JiuFuQingZhouController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JiuFuQingZhouController.class);
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private CimOrginfoService cimOrgInfoService;
	
	/**
	 * 玖富轻舟获取token接口
	 * @param result
	 * @param head
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/getToken")
	@ResponseBody
	public BaseResponse getToken(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		String getTokenUrl = sysConfigService.getValuesByKey("getTokenUrl");
		String appId = sysConfigService.getValuesByKey("jfqz_appId");
		String appSecret = sysConfigService.getValuesByKey("jfqz_appSecret");
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appId);
		params.put("appSecret", appSecret);
		params.put("userId", userId);
		AcAccountBind bind = accountbindService.selectBindAcctByUserId(userId);
		if(bind!=null&&bind.getStatus()!=null&&"1".equals(bind.getStatus().toString())){
			params.put("mobile", bind.getMobile());
			params.put("realName", bind.getUserName());
			params.put("idCardNo", bind.getIdCard());
			params.put("bankCardNo", bind.getBankCard());
			
		}
		String res =null;
		try {
			res = HttpClientUtil.httpPost(getTokenUrl, params);
		} catch (Exception e) {
			LOGGER.error("获取产品列表，获取玖富轻舟token异常！");
		}
		JSONObject json = JSONObject.parseObject(res);
		TokenResponse ver = null;
 		try {
 			ver = json.toJavaObject(json, TokenResponse.class);
		} catch (Exception e) {
		}
		String token = ver.getData().getToken();
		JFQZtokenReponse rlt = new JFQZtokenReponse();
		rlt.setToken(token);
		if(!cimOrgInfoService.isBindOrgAcct(userId, "OPEN_JIUFUQINGZHOU_WEB")){
			CrmOrgAcctRel bo = new CrmOrgAcctRel();
			bo.setUserId(userId);
			bo.setOrgNumber("OPEN_JIUFUQINGZHOU_WEB");
			bo.setOrgAccount(bind!=null?bind.getMobile():userId);
			bo.setIsInvested(0);
			bo.setIsNewUser(1);
			bo.setOrgAccountType(1);
			bo.setUpdateTime(new Date());
			bo.setCreatTime(new Date());
			cimOrgInfoService.bindOrgAcct(bo);
		}
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	
	
	
	
}
