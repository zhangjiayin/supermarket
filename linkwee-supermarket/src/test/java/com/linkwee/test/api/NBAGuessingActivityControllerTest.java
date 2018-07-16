package com.linkwee.test.api;

import com.linkwee.api.request.activity.VoteRequest;
import com.linkwee.api.response.activity.NBAEventGuessingResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import org.junit.Test;

public class NBAGuessingActivityControllerTest extends BaseTest{
	
	@Test
	public void info() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/eventguessing/info",this.getToken(),NBAEventGuessingResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void vote() throws Exception {
		VoteRequest request = new VoteRequest();
		request.setGuessId(1);
		request.setSupportVote("b");
		request.setVoteNumber(6);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/eventguessing/vote",this.getToken(),BaseResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	}

}
