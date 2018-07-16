package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.response.CfpLevelWarningResp;

public class PartnerControllerTest extends BaseTest {
	
	@Test
	public void jobGradeTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/partner/jobGrade",this.getToken(),CfpLevelWarningResp.class);
		LOGGER.debug(baseResponse.toString());
	}
}
