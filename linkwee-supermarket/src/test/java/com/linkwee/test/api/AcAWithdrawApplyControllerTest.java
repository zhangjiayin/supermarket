package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.response.acc.AcAccountTypeReponse;
import com.linkwee.api.response.acc.JFQZtokenReponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class AcAWithdrawApplyControllerTest extends BaseTest{
	
	
	@Test//审核提现
	public void getApproveWithdraw() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/jfqz/getToken",this.getToken(),JFQZtokenReponse	.class);
		LOGGER.debug(baseResponse.toString());
	}
	
//	@Test
//	public void getToken() throws Exception {
//		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/jfqz/getToken",this.getToken(),JFQZtokenReponse	.class,null);
//		LOGGER.debug(baseResponse.toString());
//	}
	
//	@Test//审核提现
//	public void getApproveWithdraw() throws Exception {
//		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/acwithdrawapply/approveWithdraw",this.getToken(),AcAccountTypeReponse.class);
//		LOGGER.debug(baseResponse.toString());
//	}
}
