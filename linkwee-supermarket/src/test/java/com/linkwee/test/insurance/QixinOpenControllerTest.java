package com.linkwee.test.insurance;

import org.junit.Test;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class QixinOpenControllerTest extends BaseTest {

	@Test
	public void notifyTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/openqixin/notify",null,Object.class);
		LOGGER.info(baseResponse.toString());
	}
}
