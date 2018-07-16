package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.activity.response.NewYearHelpStatusResponse;
import com.linkwee.api.activity.response.NewYearRankingListResponse;
import com.linkwee.api.activity.response.NewYearRewardResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class NewYearActivityControllerTest extends BaseTest{
	
	@Test
	public void rewardList() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/newYear/reward/list",this.getToken(),NewYearRewardResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void helpStatus() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/newYear/help/status",this.getToken(),NewYearHelpStatusResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void rankingList() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/newYear/rankingList",this.getToken(),NewYearRankingListResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
}
