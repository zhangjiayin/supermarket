package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.crm.DirectCfpJobGrade;
import com.linkwee.api.request.crm.GoodTransRequest;
import com.linkwee.api.request.crm.IconRequest;
import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.request.crm.MyCustomerInvestRecordRequest;
import com.linkwee.api.request.crm.PartnerDetailRequest;
import com.linkwee.api.request.crm.PartnerMonthSaleRequest;
import com.linkwee.api.request.crm.PartnerPageListRequest;
import com.linkwee.api.request.crm.RePaymentCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RepamentCalendarRequest;
import com.linkwee.api.request.crm.UserTypeRequest;
import com.linkwee.api.response.crm.GoodTransHaveRead;
import com.linkwee.api.response.crm.InsuranceInvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InvestCalendarResponse;
import com.linkwee.api.response.crm.InvestCalendarStatisticsResponse;
import com.linkwee.api.response.crm.RepamentCalendarResponse;
import com.linkwee.api.response.crm.RepaymentCalendarStatisticsResponse;
import com.linkwee.api.response.tc.GoodTransResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;


public class PersonCenterControllerTest extends BaseTest{
	
	/**
	 * V4.1.1是否有未读喜报
	 */
	@Test
	public void testHaveGoodTransNoRead() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/haveGoodTransNoRead",this.getToken(),GoodTransHaveRead.class);
		LOGGER.debug(baseResponse.toString());
	}

	
	/**
	 * V4.1.1出单喜报
	 */
	@Test
	public void testGoodTrans() throws Exception {
		GoodTransRequest req = new GoodTransRequest();
//		req.setBillId("c867b01aa7d14c9b89004a98b2655a89");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/goodTrans",this.getToken(),GoodTransResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	/**
	 * V4.1.1往期喜报
	 */
	@Test
	public void testQueryOldGoodTrans() throws Exception {
		PartnerPageListRequest req = new PartnerPageListRequest();
		//req.setName("18576651144");
		req.setSort(2);
//		req.setOrder(1);
		req.setPageIndex(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/personcenter/queryOldGoodTrans",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());	   																     																		
	}
	
	/**
	 * 团队列表
	 */
	@Test
	public void testPartnerPageList() throws Exception {
		PartnerPageListRequest req = new PartnerPageListRequest();
		//req.setName("18576651144");
		req.setSort(2);
		req.setOrder(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/pageList",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队统计
	 */
	@Test
	public void testPartner() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	/**
	 * 个人中心首页
	 */
	@Test
	public void testPersoncenterHome() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/homepage",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队成员详情
	 */
	@Test
	public void testPartnerDetail() throws Exception {
		PartnerDetailRequest req = new PartnerDetailRequest();
		req.setUserId("dfaedfbbf40b44e0a6ad2659471569b4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/detail",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队成员销售记录
	 */
	@Test
	public void testPartnerSalesRecordPageList() throws Exception {
		PartnerDetailRequest req = new PartnerDetailRequest();
		req.setUserId("822b71784d6f497cb891626fac538a14");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/salesRecordList",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队成员销售记录3.0
	 */
	@Test
	public void testPartnerSalesRecordPageListNew() throws Exception {
		PartnerDetailRequest req = new PartnerDetailRequest();
		req.setUserId("822b71784d6f497cb891626fac538a14");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/salesRecordList/3.0",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 上传头像
	 * @throws Exception
	 */
	@Test
	public void testUploadIcon() throws Exception{
		IconRequest iconRequest = new IconRequest();
		iconRequest.setImage("4cb3bb1dd4a689c9f843e417d7d683a7");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/personcenter/icon",this.getToken(),Object.class,iconRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队月份销售统计
	 */
	@Test
	public void testpartnerMonthSaleStatistics() throws Exception {
		PartnerMonthSaleRequest req = new PartnerMonthSaleRequest();
		req.setDate("2017-3-01");
		req.setDateType("3");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/monthSaleStatistics",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队月份销售列表
	 */
	@Test
	public void testpartnerMonthSaleList() throws Exception {
		PartnerMonthSaleRequest req = new PartnerMonthSaleRequest();
		req.setDate("2017-03-01");
		req.setDateType("3");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/monthSaleList",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队月份销售统计3.0
	 */
	@Test
	public void testpartnerMonthSaleStatisticsNew() throws Exception {
		PartnerMonthSaleRequest req = new PartnerMonthSaleRequest();
		req.setDate("2017-04-01");
		req.setDateType("3");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/monthSaleStatistics/3.0",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 团队月份销售列表3.0
	 */
	@Test
	public void testpartnerMonthSaleListNew() throws Exception {
		PartnerMonthSaleRequest req = new PartnerMonthSaleRequest();
		req.setDate("2017-04-01");
		req.setDateType("3");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/partner/monthSaleList/3.0",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 在投金额
	 */
	@Test
	public void testmyCurrInvestAmount() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(),"/api/personcenter/myCurrInvestAmount",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 未完成新手任务
	 * @throws Exception
	 */
	@Test
	public void testunFinishNewerTask() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/unFinishNewerTask/2.2.0",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 批量给老用户数据设置头像
	 * @throws Exception
	 */
	@Test
	public void testbacthSetHeadImage() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/personcenter/bacthSetHeadImage",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	/**
	 * 职级3.0
	 */
	@Test
	public void testJobGrade() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/partner/jobGrade",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 回款日历
	 */
	@Test
	public void testRepamentCalendar() throws Exception {
		RepamentCalendarRequest repamentCalendarRequest = new RepamentCalendarRequest();
		repamentCalendarRequest.setRepamentType(0);//0-待回款 1-已回款
		//repamentCalendarRequest.setRepamentTime("2016-11-15");
		repamentCalendarRequest.setPageIndex(2);
		repamentCalendarRequest.setQueryType(0);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/repamentCalendar",this.getToken(),RepamentCalendarResponse.class,repamentCalendarRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 回款日历统计
	 */
	@Test
	public void testRepamentCalendarStatistics() throws Exception {
		RePaymentCalendarStatisticsRequest rePaymentCalendarStatisticsRequest = new RePaymentCalendarStatisticsRequest();
		rePaymentCalendarStatisticsRequest.setRePaymentMonth("2017-01");
		rePaymentCalendarStatisticsRequest.setQueryType(0);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/repamentCalendarStatistics",this.getToken(),RepaymentCalendarStatisticsResponse.class,rePaymentCalendarStatisticsRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 交易日历
	 */
	@Test
	public void testInvestCalendar() throws Exception {
		InvestCalendarRequest investCalendarRequest = new InvestCalendarRequest();
		investCalendarRequest.setInvestTime("2016-11-15");
		investCalendarRequest.setPageIndex(2);
		investCalendarRequest.setQueryType(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/investCalendar",this.getToken(),InvestCalendarResponse.class,investCalendarRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 交易日历统计
	 */
	@Test
	public void testInvestCalendarStatistics() throws Exception {
		InvestCalendarStatisticsRequest investCalendarStatisticsRequest = new InvestCalendarStatisticsRequest();
		investCalendarStatisticsRequest.setInvestMonth("2017-09");
		investCalendarStatisticsRequest.setQueryType(0);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/investCalendarStatistics",this.getToken(),InvestCalendarStatisticsResponse.class,investCalendarStatisticsRequest);
		LOGGER.info(baseResponse.toString());
	}	
	
	/**
	 * 交易详情
	 */
	@Test
	public void testInvestCalendarDetail() throws Exception {
		InvestCalendarDetailRequest investCalendarDetailRequest = new InvestCalendarDetailRequest();
		investCalendarDetailRequest.setInvestId("b700faa9404340a78ffb0dd176a3f16d");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/investCalendarDetail",this.getToken(),InvestCalendarDetailResponse.class,investCalendarDetailRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 保险交易日历
	 */
	@Test
	public void testInsuranceCalendar() throws Exception {
		InvestCalendarRequest investCalendarRequest = new InvestCalendarRequest();
//		investCalendarRequest.setInvestTime("2016-11-15");
		investCalendarRequest.setPageIndex(1);
		investCalendarRequest.setQueryType(0);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/insuranceCalendar",this.getToken(),InvestCalendarResponse.class,investCalendarRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 保险交易详情
	 */
	@Test
	public void testInsuranceInvestCalendarDetail() throws Exception {
		InvestCalendarDetailRequest investCalendarDetailRequest = new InvestCalendarDetailRequest();
		investCalendarDetailRequest.setInvestId("2895bf4b85b74a6db14a5ef9d010a174");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/insuranceInvestCalendarDetail",this.getToken(),InsuranceInvestCalendarDetailResponse.class,investCalendarDetailRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	
	/**
	 * 4.5.0我的-整页-显示职级等
	 */
	@Test
	public void testpersonInfo() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/personInfo",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}

	/**
	 * 4.5.0我的-客户成员/理财师团队成员
	 */
	@Test
	public void testCustomerCfpmember() throws Exception {
		UserTypeRequest req = new UserTypeRequest();
		req.setType("2");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/customerCfpmember",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	/**
	 * 4.5.0我的-客户成员/理财师团队成员分页
	 */
	@Test
	public void testCustomerCfpmemberPage() throws Exception {
		UserTypeRequest req = new UserTypeRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		req.setAttenInvestType(2);
		req.setType("2");
//		req.setNameOrMobile("成国峰");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/customerCfpmemberPage",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	/**
	 * 4.5.0我的-整页-基金
	 */
	@Test
	public void testPersonInfoFund() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/personInfoFund",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
	/**
	 * 4.5.0客户详情
	 */
	@Test
	public void testCustomerDetail() throws Exception {
		DirectCfpJobGrade req = new DirectCfpJobGrade();
		req.setUserId("822b71784d6f497cb891626fac538a14");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/customerDetail",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	/**
	 * 4.5.0我的-客户成员详情-投资记录
	 */
	@Test
	public void testCustomerInvestRecordPage() throws Exception {
		MyCustomerInvestRecordRequest req = new MyCustomerInvestRecordRequest();
		req.setUserId("822b71784d6f497cb891626fac538a14");
		req.setType("1");
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/personcenter/customerInvestRecord",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 4.5.0理财师团队成员详情
	 */
	@Test
	public void testCfplannerDetail() throws Exception {
		DirectCfpJobGrade req = new DirectCfpJobGrade();
		req.setUserId("83889096d52a4d0d9c6ca388d6c12ff4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/cfplannerDetail",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 4.5.0我的-理财师成员
	 */
	@Test
	public void testCfplannerMemberPage() throws Exception {
		UserTypeRequest req = new UserTypeRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/cfplannerMemberPage",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	/**
	 * 4.5.0直接下级理财职级特权
	 */
	@Test
	public void testDirectCfpJobGrade() throws Exception {
		DirectCfpJobGrade req = new DirectCfpJobGrade();
		req.setUserId("822b71784d6f497cb891626fac538a14");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/directCfpJobGrade",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 4.5.0职级体验券列表 
	 */
	@Test
	public void testJobGradeVoucherPage() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/partner/jobGradeVoucherPage",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 4.5.0职级体验券弹出框
	 */
	@Test
	public void testJobGradeVoucherPopup() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/partner/jobGradeVoucherPopup",this.getToken(),Map.class);
		LOGGER.debug(baseResponse.toString());
	}
}
