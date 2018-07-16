package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.response.crm.CrmCfpNewcomerWelfareTaskResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class CrmCfpNewComerWelfareTaskControllerTest extends BaseTest{
	
	/**
	 * 新手福利六连送任务完成状态
	 * @throws Exception
	 */
	@Test
	public void testFinishStatus() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/cfpnewcomerwelfaretask/finishStatus/4.3.0",this.getToken(),CrmCfpNewcomerWelfareTaskResponse.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 终极大奖
	 * @throws Exception
	 */
	@Test
	public void testSendFinalPrize() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/cfpnewcomerwelfaretask/sendFinalPrize/4.3.0",this.getToken(),BaseResponse.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
}
