package com.linkwee.test.api.atc;

import org.junit.Test;

import com.linkwee.api.response.act.ActPersonAddfeeTicketExtendsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class ActPersonAddfeeTicketControllerTest extends BaseTest{
	
	@Test
	public void queryRankListDataTest() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.PRERELEASE),"/api/personaddfeeticket/myaddfeeticket",this.getToken(),ActPersonAddfeeTicketExtendsResponse.class);
		LOGGER.debug(baseResponse.toString());
	} 

}
