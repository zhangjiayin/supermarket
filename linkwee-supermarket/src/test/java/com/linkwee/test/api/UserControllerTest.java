package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.acc.TwoPwdRequest;
import com.linkwee.api.request.crm.CheckMobileRequest;
import com.linkwee.api.request.crm.ChooseBackgroundRequest;
import com.linkwee.api.request.crm.EasemobIdRequest;
import com.linkwee.api.request.crm.PwdRequest;
import com.linkwee.api.request.crm.RegisterRequest;
import com.linkwee.api.request.crm.ResetLoginPwdRequest;
import com.linkwee.api.response.crm.BrandPostersResponse;
import com.linkwee.api.response.crm.CfplannerPersonCenterResponse;
import com.linkwee.api.response.crm.CfplannerUserInfoResponse;
import com.linkwee.api.response.crm.CheckMobileResponse;
import com.linkwee.api.response.crm.InvitationCfpPageListResponse;
import com.linkwee.api.response.crm.InvitationInvestorPageListResponse;
import com.linkwee.api.response.crm.InvitationNumResponse;
import com.linkwee.api.response.crm.LoginResponse;
import com.linkwee.api.response.crm.SmBrandPostersTypeResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.request.DeviceInfoRequest;
import com.linkwee.web.request.SendVcodeRequest;

public class UserControllerTest extends BaseTest{
	
	@Test//V4.5.3海报推广
	public void brandPostersTest() throws Exception {
		ChooseBackgroundRequest req = new ChooseBackgroundRequest();
		req.setType("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/brandPosters",this.getToken(),BrandPostersResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//V4.5.3海报类型
	public void SmBrandPostersTypeTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/postersType",this.getToken(),SmBrandPostersTypeResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//V4.0邀请记录-统计数量
	public void invitationNum() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/invitationNum",this.getToken(),InvitationNumResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//V4.0邀请记录--切换背景
	public void brandPromotion() throws Exception {
		ChooseBackgroundRequest req = new ChooseBackgroundRequest();
		req.setType("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/brandPromotion",this.getToken(),null,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//V4.0邀请记录--切换背景
	public void chooseBackground() throws Exception {
		ChooseBackgroundRequest req = new ChooseBackgroundRequest();
		req.setType("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/chooseBackground",this.getToken(),null,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//V4.0邀请记录--邀请客户 
	public void querInvitationInvestorList() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/invitationInvestor",this.getToken(),InvitationInvestorPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//V4.0邀请记录--推荐理财师
	public void queryinvitationCfpList() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/invitationCfp",this.getToken(),InvitationCfpPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testQueryCfpUserInfo() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/user/userInfo", this.getToken(), CfplannerUserInfoResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testPersonalCenter() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/user/personalCenter", this.getToken(), CfplannerPersonCenterResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testIsNew() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/user/isNew", this.getToken(), BaseResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 发送验证码
	 */
	@Test
	public void testSendVcode() throws Exception {
		SendVcodeRequest params = new SendVcodeRequest();
		params.setMobile("13088883333");
		params.setType(MsgModuleEnum.REGISTER.getKey());
		params.setVcode("g5px");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(PathEnum.LOCALHOST),"/api/user/sendVcode",null,null,params);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 注册
	 */
	@Test
	public void testRegister() throws Exception {
		RegisterRequest params = new RegisterRequest();
		params.setMobile("13088883333");
		params.setPassword("123456.");
		params.setRecommendCode("15220203390");
		params.setVcode("0288");
		params.setFromUrl("aaa");
		params.setAccessUrl("bbb");
		params.setSaleOrgCode("6cf4a086b66348a5b49b7a1b4b5c9011");//销售机构编码
		DeviceInfoRequest params2 = new DeviceInfoRequest();
		params2.setDeviceId("909E8EDD-59D8-46EA-A80E-62790499BF32");
		params2.setDeviceModel("iPhone");
		params2.setSystemVersion("9.0");
		params2.setResolution("640x1136");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/user/register",null,LoginResponse.class,params,params2);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 获取用户信息
	 */
	@Test
	public void testGetUserInfo() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/getUserInfo",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 根据环信帐号获取用户信息
	 */
	@Test
	public void testGetUserInfoByEasemob() throws Exception {
		EasemobIdRequest req = new EasemobIdRequest();
		req.setEasemobAcct("cfpb99c4c62fe0a4abe879d69b651d3045f");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/user/getUserInfoByEasemob",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 检查号码是否可以注册
	 */
	@Test
	public void testCheckMobile() throws Exception {
		CheckMobileRequest params = new CheckMobileRequest();
		params.setMobile("18111111176");
		params.setRecommendCode("18111111120");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/checkMobile",null,CheckMobileResponse.class,params);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 修改登录密码
	 */
	@Test
	public void testModifyLoginPwd() throws Exception {
		TwoPwdRequest params = new TwoPwdRequest();
		params.setOldPwd("123456.");
		params.setNewPwd("123456.");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(PathEnum.LOCALHOST),"/api/user/modifyLoginPwd",this.getToken(),null,params);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 重置登录密码
	 */
	@Test
	public void testResetLoginPwd() throws Exception {
		ResetLoginPwdRequest params = new ResetLoginPwdRequest();
		params.setMobile("18111111120");
		params.setVcode("0027");
		params.setNewPwd("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/resetLoginPwd",null,null,params);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 校验登录密码
	 */
	@Test
	public void testVerifyLoginPwd() throws Exception {
		PwdRequest params = new PwdRequest();
		params.setPwd("12345622");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/user/verifyLoginPwd",this.getToken(),Map.class,params);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 我的理财师
	 * @throws Exception
	 */
	@Test
	public void testMycfp() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/user/mycfp",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	
}
