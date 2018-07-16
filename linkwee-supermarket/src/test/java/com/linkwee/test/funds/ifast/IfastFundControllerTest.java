package com.linkwee.test.funds.ifast;

import org.junit.Test;

import com.linkwee.api.request.funds.ifast.BatchGetFundExtendsRequest;
import com.linkwee.api.request.funds.ifast.FundDetailGotoRequest;
import com.linkwee.api.request.funds.ifast.QuickCustomerMigrationRequest;
import com.linkwee.api.response.funds.ifast.BaseDefinedResponse;
import com.linkwee.api.response.funds.ifast.GotoBaseResponse;
import com.linkwee.api.response.funds.ifast.IfRegisterResponse;
import com.linkwee.api.response.funds.ifast.IfastAccountBaseResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;


public class IfastFundControllerTest extends BaseTest{
	
	@Test
	public void fundSiftTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/funds/ifast/fundSift",null,Object.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void batchGetFundInfoTest() throws Exception {
		BatchGetFundExtendsRequest batchGetFundExtendsRequest = new BatchGetFundExtendsRequest();
//		batchGetFundExtendsRequest.setIsMMFund("0");
//		batchGetFundExtendsRequest.setIsRecommended("0");
//		batchGetFundExtendsRequest.setPageIndex("2");
//		batchGetFundExtendsRequest.setPeriod("month3");
//		batchGetFundExtendsRequest.setSort("DESC");
//		batchGetFundExtendsRequest.setFundType("MM,BOND");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.RELEASE),"/api/funds/ifast/batchGetFundInfo",null,Object.class,batchGetFundExtendsRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void getHoldingsStatisticTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/funds/ifast/getHoldingsStatistic",this.getToken(),String.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void getInvestorHoldingsTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/funds/ifast/getInvestorHoldings",this.getToken(),String.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void getInvestorHoldingsNewTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/funds/ifast/getInvestorHoldingsNew",this.getToken(),String.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void getOrderListTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/funds/ifast/getOrderList",this.getToken(),String.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void getInvestorOrderInfoTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/funds/ifast/getInvestorOrderInfo",this.getToken(),String.class);
		LOGGER.info(baseResponse.toString());
	}
	
	
	@Test
	public void quickCustomerMigrationTest() throws Exception {
		QuickCustomerMigrationRequest quickCustomerMigrationRequest = new QuickCustomerMigrationRequest();
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/funds/ifast/quickCustomerMigration",this.getToken(),IfastAccountBaseResponse.class,quickCustomerMigrationRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void ifRegisterTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/funds/ifast/ifRegister",this.getToken(),IfRegisterResponse.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void gotoIndexTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/funds/ifast/gotoIndex",this.getToken(),GotoBaseResponse.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void gotoFundDetailTest() throws Exception {
		FundDetailGotoRequest fundDetailGotoRequest = new FundDetailGotoRequest();
		fundDetailGotoRequest.setProductCode("482002");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/funds/ifast/gotoFundDetail",this.getToken(),IfRegisterResponse.class,fundDetailGotoRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void gotoAccountTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/funds/ifast/gotoAccount",this.getToken(),GotoBaseResponse.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void baseDefinedTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.RELEASE),"/api/funds/ifast/baseDefined",this.getToken(),BaseDefinedResponse.class);
		LOGGER.info(baseResponse.toString());
	}
}
