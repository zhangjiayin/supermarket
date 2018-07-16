package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.request.cim.MyInvestrecordRequest;
import com.linkwee.api.response.cim.MyInvestrecordResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class ProductInvestRecordControllerTest  extends BaseTest{

	/**
	 * 我的投资记录v4.0
	 * @throws Exception
	 */
	@Test
	public void testMyInvestrecord() throws Exception {
		MyInvestrecordRequest myInvestrecordRequest = new MyInvestrecordRequest();
		myInvestrecordRequest.setInvestType(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/productinvestrecord/myInvestrecord",this.getToken(),MyInvestrecordResponse.class,myInvestrecordRequest);
		LOGGER.debug(baseResponse.toString());
	}
}
