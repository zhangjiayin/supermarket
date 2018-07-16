package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.activity.response.BigWheelDrawResponse;
import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.ActCfpDoubleElevenActivity;
import com.linkwee.web.model.ActMidautumnTask;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.request.ActivityPageListRequest;

public class WheelofFortuneControllerTest extends BaseTest{
	
	/**
	 * 抽奖次数
	 * @throws Exception
	 */
	@Test
	public void testPrizeTimes() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/valentine/wheel/prize/times",this.getToken(),Map.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 抽一次
	 * @throws Exception
	 */
	@Test
	public void testPrizeOne() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/valentine/wheel/prize/one",this.getToken(),BigWheelDrawResponse.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 抽十次
	 * @throws Exception
	 */
	@Test
	public void testPrizeTen() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/valentine/wheel/prize/ten",this.getToken(),BigWheelDrawResponse.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 所有抽奖记录
	 * @throws Exception
	 */
	@Test
	public void testPrizeRecordAll() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/valentine/wheel/prize/record/all",this.getToken(),ActWheelWinningRecord.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 用户抽奖记录
	 * @throws Exception
	 */
	@Test
	public void testPrizeRecordUser() throws Exception {
		PaginatorRequest paginatorRequest = new PaginatorRequest();
		paginatorRequest.setPageIndex(1);
		paginatorRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/valentine/wheel/prize/record/user/pageList",this.getToken(),ActWheelWinningRecord.class,paginatorRequest);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void rankingListPageList() throws Exception {
		LOGGER.info("开始时间："+System.currentTimeMillis());
		PaginatorRequest paginatorRequest = new PaginatorRequest();
		paginatorRequest.setPageIndex(1);
		paginatorRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/valentine/wheel/rankingList/pageList",this.getToken(),InvestRankingListResponse.class,paginatorRequest);		
		LOGGER.info("结束时间："+System.currentTimeMillis());
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void rankingListMyRank() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/valentine/wheel/rankingList/myRank",this.getToken(),InvestRankingListResponse.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void midautumnRankingListPageList() throws Exception {
		LOGGER.info("开始时间："+System.currentTimeMillis());
		PaginatorRequest paginatorRequest = new PaginatorRequest();
		paginatorRequest.setPageIndex(1);
		paginatorRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/midautumnactivity/rankingList/pageList",this.getToken(),InvestRankingListResponse.class,paginatorRequest);		
		LOGGER.info("结束时间："+System.currentTimeMillis());
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void midautumnFinishStatus() throws Exception {
		LOGGER.info("开始时间："+System.currentTimeMillis());
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/midautumnactivity/finishStatus",this.getToken(),ActMidautumnTask.class);		
		LOGGER.info("结束时间："+System.currentTimeMillis());
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void midautumnSubList() throws Exception {
		LOGGER.info("开始时间："+System.currentTimeMillis());
		ActivityPageListRequest request = new ActivityPageListRequest();
		request.setActivityPlatform("猎财大师");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/midautumnactivity/subactivity/list",this.getToken(),ActivityList.class);		
		LOGGER.info("结束时间："+System.currentTimeMillis());
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void elevenFinishStatus() throws Exception {
		LOGGER.info("开始时间："+System.currentTimeMillis());
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/doubleEleven/finishStatus/4.5.0",this.getToken(),ActCfpDoubleElevenActivity.class);		
		LOGGER.info("结束时间："+System.currentTimeMillis());
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void hasNewDoubleEleven() throws Exception {
		LOGGER.info("开始时间："+System.currentTimeMillis());
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/doubleEleven/hasNewDoubleEleven/4.5.0",this.getToken(),Map.class);		
		LOGGER.info("结束时间："+System.currentTimeMillis());
		LOGGER.debug(baseResponse.toString());
	}
	
}
