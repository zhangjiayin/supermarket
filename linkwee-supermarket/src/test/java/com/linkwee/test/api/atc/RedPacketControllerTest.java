package com.linkwee.test.api.atc;

import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.request.act.RedpacketRequest;
import com.linkwee.api.request.act.SendRedPacketRequest;
import com.linkwee.api.response.act.AddFeeCouponResponse;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class RedPacketControllerTest extends BaseTest{

	
	@Test
	public void queryRedPacketTest() throws Exception{
		RedpacketRequest request = new RedpacketRequest();
		request.setType(1);;
		System.out.println(JSON.toJSONString(request));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/redPacket/queryRedPacket",this.getToken(),BaseResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	} 
	
	@Test
	public void queryAvailableRedPacket() throws Exception{
		RedpacketRequest request = new RedpacketRequest();
		request.setType(2);
		request.setPatform("OPEN_JIANGONGEDAI_WEB");
		request.setPageIndex(1);
		request.setPageSize(30);
		request.setModel(2);
		request.setDeadline(720);
		request.setProductId("AAF948BAE41940B0B63D9BC5065B3956");
		System.out.println(JSON.toJSONString(request));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/redPacket/queryAvailableRedPacket",this.getToken(),RedpacketResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	} 
	
	@Test
	public void sendRedPacketTest() throws Exception{
		SendRedPacketRequest request = new SendRedPacketRequest();
		request.setRid("123");
		request.setUserMobiles("18111111124");
		System.out.println(JSON.toJSONString(request));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/redPacket/sendRedPacket",this.getToken(),BaseResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询红包列表4.0
	 * @throws Exception
	 */
	@Test
	public void queryRedPacket4Test() throws Exception{
		RedpacketRequest redpacketRequest = new RedpacketRequest();
		redpacketRequest.setType(4);//1=投资红包 2=派发红包 3=历史投资红包 4=历史派发红包
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/redPacket/queryRedPacket/4.0",this.getToken(),RedpacketResponse.class,redpacketRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询红包列表数量4.0
	 * @throws Exception
	 */
	@Test
	public void queryRedPacketCount4Test() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/redPacket/queryRedPacketCount/4.0",this.getToken(),RedpacketResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void sendRedPacket5Test() throws Exception{
		SendRedPacketRequest request = new SendRedPacketRequest();
		request.setRid("dc5ccadd438e431c9ff27a0f1897068b");
		request.setUserMobiles("15915473248");
		System.out.println(JSON.toJSONString(request));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/redPacket/sendRedPacketToCfp/4.5.0",this.getToken(),BaseResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询红包列表4.5.0
	 * @throws Exception
	 */
	@Test
	public void queryRedPacket5Test() throws Exception{
		PaginatorRequest redpacketRequest = new PaginatorRequest();
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/redPacket/queryRedPacket/4.5.0",this.getToken(),RedpacketResponse.class,redpacketRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询红包列表数量4.5.0
	 * @throws Exception
	 */
	@Test
	public void queryCouponCountTest() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/redPacket/queryCouponCount/4.5.0",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询加拥券列表4.5.0
	 * @throws Exception
	 */
	@Test
	public void queryAddFeeCouponpageListTest() throws Exception{
		PaginatorRequest request = new PaginatorRequest();
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/addFeeCoupon/pageList/4.5.0",this.getToken(),AddFeeCouponResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	}
}
