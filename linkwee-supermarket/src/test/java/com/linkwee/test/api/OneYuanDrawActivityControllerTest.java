package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.activity.request.ReceivingAddressRequest;
import com.linkwee.api.activity.response.BigWheelDrawResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.util.StringUtils;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.CrmUserActivityReceivingAddress;

public class OneYuanDrawActivityControllerTest extends BaseTest{
	
	/**
	 * 抽奖次数
	 * @throws Exception
	 */
	@Test
	public void testPrizeTimes() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/prize/times",this.getToken(),Map.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 抽一次
	 * @throws Exception
	 */
	@Test
	public void testPrizeOne() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/prize/one",this.getToken(),BigWheelDrawResponse.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 抽十次
	 * @throws Exception
	 */
	@Test
	public void testPrizeTen() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/prize/ten",this.getToken(),BigWheelDrawResponse.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 所有抽奖记录
	 * @throws Exception
	 */
	@Test
	public void testPrizeRecordAll() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/prize/record/all",this.getToken(),ActWheelWinningRecord.class);		
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
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("isfortunePrize", 0+"");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/prize/record/user/pageList",this.getToken(),ActWheelWinningRecord.class,paginatorRequest,requestMap);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testFortunePrizeRecord() throws Exception {
		PaginatorRequest paginatorRequest = new PaginatorRequest();
		paginatorRequest.setPageIndex(1);
		paginatorRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/prize/record/fortune",this.getToken(),ActWheelWinningRecord.class,paginatorRequest);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testReceivingAddress() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/receiving/address",this.getToken(),CrmUserActivityReceivingAddress.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testUpdateReceivingAddress() throws Exception {
		ReceivingAddressRequest request = new ReceivingAddressRequest();
		request.setProvinceName("湖北");
		request.setCityName("武汉");
		request.setMobile("15222222222");
		request.setReceivingAddress("长江大桥三号桥洞");
		request.setReceivingUserName("先生");
		request.setType(1);

		/*request.setMobile("15222223333");
		request.setReceivingUserName("先生");
		request.setThirdAccount("xxxxxxxxxxxxx");
		request.setType(2);*/
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/activity/oneyuandraw/receiving/address/update",this.getToken(),BaseResponse.class,request);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testGenerateUUID() throws Exception {
		System.out.println(StringUtils.getUUID());
	}
	
}
