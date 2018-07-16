package com.linkwee.test.api.crm;

import org.junit.Test;

import com.linkwee.api.response.crm.CfplannerDataResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class CrmCfplannerControllerTest extends BaseTest {

	@Test
	public void feeCalLevelType() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/crm/crmCfplanner/cfplannerData",this.getToken(),CfplannerDataResponse.class);
		LOGGER.info(baseResponse.toString());
	}
}
