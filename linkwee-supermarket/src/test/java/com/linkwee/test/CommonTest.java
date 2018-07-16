package com.linkwee.test;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.web.response.CfpLevelWarningResp;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.PartnerService;
import com.linkwee.xoss.util.OpenResponseUtil;

public class CommonTest extends TestSupport {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonTest.class);
    
	@Resource
	private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
	@Resource
	private PartnerService partnerService;

	@Test
	public void test(){
		start();
		CfpLevelWarningResp cfpLevelWarningResp = crmCfpLevelRecordTempService.cfpLevelWarning("0198b3c428a64bf5af0eb4bb0b185621");
	//	PartnerJobGradeResponse partnerJobGradeResponse = partnerService.jobGrade("4ae43985a736463a892f3db7b998ecd6");
		LOGGER.info("请求结果,result={}",JSONObject.toJSONString(cfpLevelWarningResp));
		end();
	}
	
	public static void main(String[] args) {
		LOGGER.info("请求结果,result={}",JSONObject.toJSONString(OpenResponseUtil.getSuccessResponse(new ArrayList<String>())));
	}
}
