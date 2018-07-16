package com.linkwee.test.activity;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.act.HelpResultRequest;
import com.linkwee.api.request.act.MobileRequest;
import com.linkwee.api.request.act.WeixinInfoRequest;
import com.linkwee.api.request.crm.GetWelfareRequest;
import com.linkwee.api.response.crm.QueryInvitationRecordResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class HelpRaiseRateControllerTest extends BaseTest{
	
	@Test//线下活动-邀请记录
	public void queryInvitationRecord() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/user/queryInvitationRecord",this.getToken(),QueryInvitationRecordResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//线下活动-判断是否领取过福利
	public void testhaveGetWelfare() throws Exception {
		GetWelfareRequest req = new GetWelfareRequest();
		req.setOpenId("openid123465413215461");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/user/haveGetWelfare",this.getToken(),Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//线下活动-领取福利
	public void testGetWelfare() throws Exception {
		GetWelfareRequest req = new GetWelfareRequest();
		req.setOpenId("openid123465413215461");
		req.setNickName("容天");
		req.setHeadImgUrl("imgurl54614981321312132");
		req.setCfpMobile("15220203390");
		req.setMobile("18603027030");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.PRERELEASE),"/api/user/getWelfare",this.getToken(),Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testHomepage() throws Exception {
		MobileRequest req = new MobileRequest();
		req.setMobile("15220203390");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/helpRaiseRate/homepage",this.getToken(),Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testQueryCfpInfo() throws Exception {
		MobileRequest req = new MobileRequest();
		req.setMobile("15220203390");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/user/queryCfpInfo",this.getToken(),Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testHelp() throws Exception {
		MobileRequest req = new MobileRequest();
		req.setOpenid("aaa62");
		req.setMobile("15220203390");
		req.setWeixinNickname("李白");
		req.setWeixinIcoUrl("http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(),"/api/helpRaiseRate/help",null,Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testGetWeixinInfo() throws Exception {
		WeixinInfoRequest req = new WeixinInfoRequest();
		req.setCode("031ksE7p1qcFIm0oQm8p1JGn7p1ksE7F");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/helpRaiseRate/getWeixinInfo",null,Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void getLieCaiWeixinInfo() throws Exception {
		WeixinInfoRequest req = new WeixinInfoRequest();
		req.setCode("031ksE7p1qcFIm0oQm8p1JGn7p1ksE7F");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/helpRaiseRate/getLieCaiWeixinInfo",null,Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test
	public void testHelpResult() throws Exception {
		HelpResultRequest req = new HelpResultRequest();
		req.setOpenid("aaa21");
		req.setMobile("15220203390");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WECHAT,this.getUrl(),"/api/helpRaiseRate/helpResult",null,Map.class, req);		
		LOGGER.debug(baseResponse.toString());
	}
	
	
}
