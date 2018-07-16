package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.FeedbackRequest;
import com.linkwee.api.request.NewsDetailRequest;
import com.linkwee.api.request.NewsPageListRequest;
import com.linkwee.api.request.SysConfigRequest;
import com.linkwee.api.response.NewsDtlResponse;
import com.linkwee.api.response.NewsListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.StringUtils;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.SmAppVersion;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.response.AppVersionResponse;
import com.linkwee.web.response.DownloadAppListResponse;

public class AppControllerTest extends BaseTest{
	
	/**
	 * 添加反馈信息测试
	 * @throws Exception
	 */
	@Test
	public void testFeedbackSuggestion() throws Exception {
		FeedbackRequest feedbackRequest = new FeedbackRequest();
		feedbackRequest.setContent("改变apptype,测试一下，是否成功！！！！！！！！！！！！！！！");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/app/suggestion",this.getToken(),Object.class,feedbackRequest);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询版本情况
	 * @throws Exception
	 */
	@Test
	public void testAppversion() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/app/appVersion", this.getToken(), AppVersionResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询最新可供下载的版本
	 * @throws Exception
	 */
	@Test
	public void downloadAppList() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/app/downloadAppList", this.getToken(), DownloadAppListResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void downloadAppList2() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appType", "investor");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.PRERELEASE), "/api/app/downloadAppList/diffByAppType", null, DownloadAppListResponse.class,map);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void appLogList() throws Exception {
		String specialOrgNumber = "App_channel_ios";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("specialOrgNumber", specialOrgNumber);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/app/appLogList/2.0.3", this.getToken(), SmAppVersion.class,map);
		LOGGER.debug(baseResponse.toString());  
	}
	
	/**
	 * 根据应用类别查询系统配置
	 * @throws Exception
	 */
	@Test
	public void testSelectByAppType() throws Exception {
		SysConfigRequest sysConfigRequest = new SysConfigRequest();
		sysConfigRequest.setAppType(2);
		sysConfigRequest.setPageIndex(1);
		sysConfigRequest.setPageSize(20);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(), "/api/app/sys-config/appType", this.getToken(), SysConfig.class, sysConfigRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 资讯分页
	 * @throws Exception
	 */
	@Test
	public void testNewsPageList() throws Exception {
		NewsPageListRequest newsPageListRequest = new NewsPageListRequest();
		//newsPageListRequest.setTypeCode("3");
		newsPageListRequest.setPageIndex(1);
		newsPageListRequest.setPageSize(50);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, this.getUrl(), "/api/app/newsPageList", this.getToken(), NewsListResponse.class, newsPageListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test
	public void testNewsTop() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, this.getUrl(PathEnum.LOCALHOST), "/api/app/newsTop/4.0.0", this.getToken(), NewsListResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 资讯详情
	 * @throws Exception
	 */
	@Test
	public void testNewsDetail() throws Exception {
		NewsDetailRequest newsDetailRequest = new NewsDetailRequest();
		newsDetailRequest.setNewsId("27");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(), "/api/app/pageList/detail", this.getToken(), NewsDtlResponse.class, newsDetailRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询系统配置
	 * @throws Exception
	 */
	@Test
	public void testDefaultConfig() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/app/default-config", this.getToken(), Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * IOS补丁
	 * @throws Exception
	 */
	@Test
	public void testIosPatchData() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, getUrl(), "/api/app/iosPatchData",null,null);
		LOGGER.debug(baseResponse.toString());
	}
		
	/**
	 * 是否有未读的资讯
	 * @throws Exception
	 */
	@Test
	public void testHasReaded() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, getUrl(), "/api/app/news/readed/2.0.3",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	/**
	 * 是否有未读的资讯或活动
	 * @throws Exception
	 */
	@Test
	public void testNewsAndActivityReaded() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, getUrl(PathEnum.PRERELEASE), "/api/app/newsandactivity/readed/2.0.2",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	public static void main(String[] args){
		System.out.println(StringUtils.getUUID());
	}

	/**
	 * 是否有未读的资讯
	 * @throws Exception
	 */
	@Test
	public void getSysConfigTest() throws Exception {
		SysConfig sysConfig = new SysConfig();
		sysConfig.setConfigType("android_hotfix");
		sysConfig.setConfigKey("android_hotfix_patch_version");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, getUrl(PathEnum.LOCALHOST), "/api/app/sysConfig/config",this.getToken(),SysConfig.class,sysConfig);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testStickNewsTop() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS, this.getUrl(PathEnum.LOCALHOST), "/api/app/newsTop/4.6.0", this.getToken(), NewsListResponse.class);
		LOGGER.debug(baseResponse.toString());
	}

}
