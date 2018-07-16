package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.crm.NewcomerTaskRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;

public class CrmCfpNewcomerTaskControllerTest extends BaseTest{

	
	
	/**
	 * 新手任务首页
	 */
	@Test
	public void testHomePage() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/cfpnewcomertask/homepage",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 新手任务首页
	 */
	@Test
	public void testHomePageNew() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/cfpnewcomertask/homepage/2.2",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void testfinishTask() throws Exception {
		NewcomerTaskRequest req = new NewcomerTaskRequest();
		req.setTaskType("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/cfpnewcomertask/finishTask",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 领取奖励
	 */
	@Test
	public void testreceiveTaskReward() throws Exception {
		NewcomerTaskRequest req = new NewcomerTaskRequest();
		req.setTaskType("3");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/cfpnewcomertask/receiveTaskReward",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	/**
	 * 投资者新手福利
	 */
	@Test
	public void testInvestorHomePage() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/investornewcomertask/newcomerWelfare",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财师新手福利
	 */
	@Test
	public void testCfpNewcomerWelfare() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/cfpnewcomertask/newcomerWelfare",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
}
