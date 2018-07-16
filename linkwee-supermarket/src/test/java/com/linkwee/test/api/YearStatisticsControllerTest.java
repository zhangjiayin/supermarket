package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.activity.response.FinancialCalculationResponse;
import com.linkwee.api.activity.response.YearStaPersonAchiResponse;
import com.linkwee.api.activity.response.YearStaTeamAchiResponse;
import com.linkwee.api.request.act.SignCalendarRequest;
import com.linkwee.api.response.act.SignRecordResponse;
import com.linkwee.api.response.act.SignShareResponse;
import com.linkwee.api.response.act.SignStatisticsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class YearStatisticsControllerTest extends BaseTest{
	
	@Test
	public void personAchievement() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/yearStatistics/personAchievement",this.getToken(),YearStaPersonAchiResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void teamAchievement() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/yearStatistics/teamAchievement",this.getToken(),YearStaTeamAchiResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void liecaiAchievement() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/yearStatistics/liecaiAchievement",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void financialCalculation() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/yearStatistics/financial/calculation",this.getToken(),FinancialCalculationResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void financialCalculate() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", "赚钱大咖");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/yearStatistics/financial/calculate",this.getToken(),FinancialCalculationResponse.class,map);
		LOGGER.debug(baseResponse.toString());
	}
	
}
