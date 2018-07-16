package com.linkwee.test.insurance;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.test.TestSupport;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.web.service.CimInsurancePolicyInfoService;

public class InsuranceFeeTest extends TestSupport{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceFeeTest.class);
	
	@Resource
	private CimInsurancePolicyInfoService cimInsurancePolicyInfoService;
	@Resource
	private CimInsuranceNotifyService cimInsuranceNotifyService;
	
	@Test
	public void insuranceSiftTest() throws Exception {
		start();
		try {
			String orgNumber = "OPEN_QIXIN_WEB"; 
			String insureNum = "20170923010452";
			CimInsuranceNotify  cimInsuranceNotify = cimInsuranceNotifyService.selectByOrgNumberInsureNum(orgNumber, insureNum);
			cimInsurancePolicyInfoService.handleInsuranceNotifyProcess(cimInsuranceNotify);
		} catch (Exception e) {
			LOGGER.error("计算保险订单佣金异常   不影响保险订单通知  ",e);
		}
		end();
		TimeUnit.MINUTES.sleep(5);
	}

}
