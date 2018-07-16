package com.linkwee.test.api.crm;

import org.junit.Test;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.CrmCfpLevel;

public class CrmCfpCommonControllerTest extends BaseTest {
	
	@Test
	public void feeCalLevelType() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/crm/cfpcommon/feeCalBaseData",this.getToken(),CrmCfpLevel.class);
		LOGGER.info(baseResponse.toString());
	}
}
