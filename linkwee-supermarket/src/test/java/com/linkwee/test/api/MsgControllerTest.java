package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.DynamicNewsPageListRequest;
import com.linkwee.api.request.NetloanNewsPageListRequest;
import com.linkwee.api.request.mc.CustomerDeviceRequest;
import com.linkwee.api.request.mc.MsgDelRequest;
import com.linkwee.api.request.mc.MsgDetailRequest;
import com.linkwee.api.response.NetloanNewsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.SmDynamicNews;

public class MsgControllerTest extends BaseTest {

	/**
	 * 首页-热门产品
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHotProduct() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/bulletin/pageList", this.getToken(), Map.class, pageRequest);
		LOGGER.debug(baseResponse.toString());
	}
		
	@Test
	public void testNoticeDtl() throws Exception {
		MsgDetailRequest msgReq = new MsgDetailRequest();
		msgReq.setMsgId("101");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/detail", this.getToken(), Map.class, msgReq);
		LOGGER.debug(baseResponse.toString());
	}

	/// person/pageList
	@Test
	public void testPersonList() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/pageList", this.getToken(), Map.class, pageRequest);
		LOGGER.debug(baseResponse.toString());
	}

	// @Test
	public void testPsersonDel() throws Exception {
		MsgDelRequest msgReq = new MsgDelRequest();
		msgReq.setMsgIds("27,28");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/del", this.getToken(), Map.class, msgReq);
		LOGGER.debug(baseResponse.toString());
	}

	// 
	@Test
	public void testunreadCount() throws Exception {

		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/unreadCount", this.getToken(), Map.class);
		LOGGER.debug(baseResponse.toString());
	}

	//
	// @Test
	public void testDetail() throws Exception {
		MsgDetailRequest dtl = new MsgDetailRequest();
		dtl.setMsgId("34");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/detail", this.getToken(), Map.class, dtl);
		LOGGER.debug(baseResponse.toString());
	}

	// @Test
	public void testSetMsgPush() throws Exception {
		CustomerDeviceRequest device = new CustomerDeviceRequest();
		device.setIssendNotice("0");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.PRERELEASE),
				"/api/msg/setMsgPush", this.getToken(), Map.class, device);
		LOGGER.debug(baseResponse.toString());
	}

	// queryMsgPushSet
	// @Test
	public void testQueryMsgPushSet() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.PRERELEASE),
				"/api/msg/queryMsgPushSet", this.getToken(), Map.class);
		LOGGER.debug(baseResponse.toString());
	}

	// @Test
	public void testPsersonReaded() throws Exception {
		MsgDelRequest msgReq = new MsgDelRequest();
		msgReq.setMsgIds("27,28");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/readed", this.getToken(), Map.class, msgReq);
		LOGGER.debug(baseResponse.toString());
	}

	// @Test
	public void testPsersonAllReaded() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/person/allReaded", this.getToken(), Map.class);
		LOGGER.debug(baseResponse.toString());
	}

	// @Test
	public void testNoticeReaded() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/allReaded", this.getToken(), Map.class);
		LOGGER.debug(baseResponse.toString());
	}

	@Test
	public void testDynamicNewsPageList() throws Exception {
		DynamicNewsPageListRequest req = new DynamicNewsPageListRequest();
		req.setTypeCode("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_WEB, this.getUrl(PathEnum.LOCALHOST),"/api/dynamicnews/pageList", null, SmDynamicNews.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testDynamicNewsDetail() throws Exception {
		MsgDetailRequest req = new MsgDetailRequest();
		req.setMsgId("69");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_WEB, this.getUrl(PathEnum.LOCALHOST),"/api/dynamicnews/detail", null, SmDynamicNews.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testDynamicNewsNearNews() throws Exception {
		MsgDetailRequest req = new MsgDetailRequest();
		req.setMsgId("72");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_WEB, this.getUrl(PathEnum.LOCALHOST),"/api/dynamicnews/nearNews", null, Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testnearNotice() throws Exception {
		MsgDetailRequest dtl = new MsgDetailRequest();
		dtl.setMsgId("101");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/msg/notice/nearNotices", this.getToken(), Map.class, dtl);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void testpersonalUnreadCount() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.RELEASE),"/api/msg/person/personalUnreadCount",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testnetloanNews() throws Exception {
		NetloanNewsPageListRequest dtl = new NetloanNewsPageListRequest();
		dtl.setTypeCode(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/dynamicnews/netloanNews/pageList", this.getToken(), NetloanNewsResponse.class, dtl);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void testnetloanNewsDetail() throws Exception {
		NetloanNewsPageListRequest dtl = new NetloanNewsPageListRequest();
		dtl.setId(4);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST),
				"/api/dynamicnews/netloanNews/detail", this.getToken(), NetloanNewsResponse.class, dtl);
		LOGGER.info(baseResponse.toString());
	}
	
}
