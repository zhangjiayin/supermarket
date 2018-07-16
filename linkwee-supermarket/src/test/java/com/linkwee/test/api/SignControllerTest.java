package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.acc.TwoPwdRequest;
import com.linkwee.api.request.act.SignCalendarRequest;
import com.linkwee.api.request.crm.CheckMobileRequest;
import com.linkwee.api.request.crm.ChooseBackgroundRequest;
import com.linkwee.api.request.crm.EasemobIdRequest;
import com.linkwee.api.request.crm.PwdRequest;
import com.linkwee.api.request.crm.RegisterRequest;
import com.linkwee.api.request.crm.ResetLoginPwdRequest;
import com.linkwee.api.response.act.BountyDetailResponse;
import com.linkwee.api.response.act.SignRecordResponse;
import com.linkwee.api.response.act.SignResponse;
import com.linkwee.api.response.act.SignShareResponse;
import com.linkwee.api.response.act.SignStatisticsResponse;
import com.linkwee.api.response.crm.CfplannerPersonCenterResponse;
import com.linkwee.api.response.crm.CfplannerUserInfoResponse;
import com.linkwee.api.response.crm.CheckMobileResponse;
import com.linkwee.api.response.crm.InvitationCfpPageListResponse;
import com.linkwee.api.response.crm.InvitationInvestorPageListResponse;
import com.linkwee.api.response.crm.InvitationNumResponse;
import com.linkwee.api.response.crm.LoginResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.request.DeviceInfoRequest;
import com.linkwee.web.request.SendVcodeRequest;

public class SignControllerTest extends BaseTest{
	
	@Test
	public void info() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/sign/info/4.5.1",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void sign() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/sign/sign/4.5.1",this.getToken(),SignResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void shareInfo() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/sign/share/info/4.5.1",this.getToken(),null);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void share() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/sign/share/prize/4.5.1",this.getToken(),SignShareResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void statistics() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/sign/statistics/4.5.1",this.getToken(),SignStatisticsResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void bounsTransfer() throws Exception {		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/sign/bouns/transfer/4.5.1", this.getToken(), null);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void recordPageList() throws Exception {		
		PaginatorRequest request = new PaginatorRequest();
		request.setPageIndex(1);
		request.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/sign/records/pageList/4.5.1", this.getToken(), SignRecordResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void calendar() throws Exception {		
		SignCalendarRequest request = new SignCalendarRequest();
		request.setSignTime("2018-4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/sign/calendar/4.5.1", this.getToken(), String.class,request);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void bountyDetailPageList() throws Exception {		
		PaginatorRequest request = new PaginatorRequest();
		request.setPageIndex(1);
		request.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID, this.getUrl(PathEnum.LOCALHOST), "/api/sign/bounty/detail/4.6.0", this.getToken(), BountyDetailResponse.class,request);
		LOGGER.debug(baseResponse.toString());
	}
}
