package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.act.AdvPageListRequest;
import com.linkwee.api.request.act.AdvertisementPageListRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.news.SmAdvertisement;

public class HomepageControllerTest extends BaseTest{

	/**
	 * 首页广告条
	 * @throws Exception
	 */
	@Test
	public void testBanners() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/banners",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testOpeningAdv() throws Exception {
		AdvertisementPageListRequest req = new AdvertisementPageListRequest();
		req.setAdvType("app_opening");
		req.setAppType(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/opening",this.getToken(),SmAdvertisement.class);
		LOGGER.debug(baseResponse.toString());
	}
	/**
	 * 根据端口（理财师，投资端）和广告位置查询配置广告
	 * @throws Exception
	 */
	@Test
	public void testAdvs() throws Exception {
		AdvPageListRequest req = new AdvPageListRequest();
		req.setAdvPlacement("liecai_banner");
		req.setAppType(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/advs",null,SmAdvertisement.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductOpeningAdv() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/homepage/product/opening/2.1.0",this.getToken(),SmAdvertisement.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testSysCfpHomepageInfo() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/cfp/sysInfo/4.0.0",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testLcsAchievement() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/lcs/achievement/4.3.0",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testhasNewRedPacket() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/hasNewRedPacket/4.5.0",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testhasNewAddFeeCoupon() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/homepage/hasNewAddFeeCoupon/4.5.0",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testhasNewAddFee() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/hasNewAddFee/4.5.0",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testCfpFeeInfo() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/cfp/feeInfo/4.5.4",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testCfpInviteRegInfo() throws Exception {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("mobile", new String("15915473248"));
 		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/homepage/cfp/inviteRegInfo/4.5.4",null,Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
}
