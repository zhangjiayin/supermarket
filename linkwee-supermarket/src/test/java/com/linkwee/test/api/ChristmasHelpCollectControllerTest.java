package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.activity.response.BigWheelDrawResponse;
import com.linkwee.api.activity.response.ChristmasHomePageResponse;
import com.linkwee.api.request.act.SocksCollectHelpRequest;
import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.ActCfpDoubleElevenActivity;
import com.linkwee.web.model.ActChristmasAwardingRecord;
import com.linkwee.web.model.ActChristmasSocksDetail;
import com.linkwee.web.model.ActMidautumnTask;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.request.ActivityPageListRequest;

public class ChristmasHelpCollectControllerTest extends BaseTest{
	
	/**
	 * 首页数据
	 * @throws Exception
	 */
	@Test
	public void testHomepage() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openId", "nicai255");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/activity/christmas/homepage",this.getToken(),ChristmasHomePageResponse.class,map);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 助力记录
	 * @throws Exception
	 */
	@Test
	public void testHelpRecord() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/activity/christmas/help/record",this.getToken(),ActChristmasSocksDetail.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 兑奖记录
	 * @throws Exception
	 */
	@Test
	public void testAwardingRecord() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/activity/christmas/awarding/record",this.getToken(),ActChristmasAwardingRecord.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 助力
	 * @throws Exception
	 */
	@Test
	public void testHelp() throws Exception {
		SocksCollectHelpRequest request = new SocksCollectHelpRequest();
		request.setOpenid("nicaixx"+0);
		request.setWeixinIcoUrl("xxxxx");
		request.setWeixinNickname("xinxin");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/activity/christmas/help",this.getToken(),Map.class,request);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 兑奖
	 * @throws Exception
	 */
	@Test
	public void testAward() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prizeId", "1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.PRERELEASE),"/api/activity/christmas/award",this.getToken(),null,map);		
		LOGGER.debug(baseResponse.toString());
	}
	
	public static void main(String[] args) throws Exception{
		ChristmasHelpCollectControllerTest test = new ChristmasHelpCollectControllerTest();
		for(int i = 0; i < 1000; i++){
			test.testHelp();
		}
	}
	
}
