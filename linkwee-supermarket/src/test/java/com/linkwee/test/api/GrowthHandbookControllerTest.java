package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.GrowthHandbookClassifyRequest;
import com.linkwee.api.response.GrowthHandbookClassifyListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.model.SmGrowthHandbookClassify;

public class GrowthHandbookControllerTest extends BaseTest{
		
	/**
	 * 成长手册分类
	 * @throws Exception
	 */
	@Test
	public void testClassify() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, this.getUrl(PathEnum.LOCALHOST), "/api/growthHandbook/classify/4.1.1", this.getToken(), SmGrowthHandbookClassify.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 分类列表
	 * @throws Exception
	 */
	@Test
	public void testClassifyList() throws Exception {
		GrowthHandbookClassifyRequest request = new GrowthHandbookClassifyRequest();
		request.setTypeCode("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/growthHandbook/classifyList/4.1.1", this.getToken(), GrowthHandbookClassifyListResponse.class, request);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 个人定制
	 * @throws Exception
	 */
	@Test
	public void testPersonalCustomization() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.PRERELEASE), "/api/growthHandbook/personalCustomization/4.1.1", this.getToken(), SmGrowthHandbook.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 详情
	 * @throws Exception
	 */
	@Test
	public void testDetail() throws Exception {
		Map<String, String> request = new HashMap<String, String>();
		request.put("id", "1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/growthHandbook/detail/4.1.1", this.getToken(), SmGrowthHandbook.class,request);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testList() throws Exception {
		GrowthHandbookClassifyRequest request = new GrowthHandbookClassifyRequest();
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/growthHandbook/list/4.3.0", this.getToken(), GrowthHandbookClassifyListResponse.class, request);
		LOGGER.debug(baseResponse.toString());
	}
}
