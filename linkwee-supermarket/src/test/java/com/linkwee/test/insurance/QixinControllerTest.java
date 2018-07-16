package com.linkwee.test.insurance;

import org.junit.Test;

import com.linkwee.api.request.cim.CimInsuranceQuestionSummaryRequest;
import com.linkwee.api.request.insurance.qixin.QixinGotoBaseRequest;
import com.linkwee.api.request.insurance.qixin.QixinInsuranceListRequest;
import com.linkwee.api.request.insurance.qixin.QixinProductDetailGotoRequest;
import com.linkwee.api.response.insurance.qixin.GotoProductDetailResponse;
import com.linkwee.api.response.insurance.qixin.InsuranceQuestionResultReponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.CimInsuranceCategory;

public class QixinControllerTest extends BaseTest {

	@Test
	public void insuranceSiftTest() throws Exception {
		QixinInsuranceListRequest qixinInsuranceListRequest = new QixinInsuranceListRequest();
		qixinInsuranceListRequest.setPageSize(2);
		qixinInsuranceListRequest.setPageIndex(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/insurance/qixin/insuranceSift",null,Object.class,qixinInsuranceListRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void insuranceSelectTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASEDOMAIN),"/api/insurance/qixin/insuranceSelect",null,Object.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void fundSiftTest() throws Exception {
		QixinInsuranceListRequest qixinInsuranceListRequest = new QixinInsuranceListRequest();
		qixinInsuranceListRequest.setInsuranceCategory("2");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/insurance/qixin/insuranceList",null,Object.class,qixinInsuranceListRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void gotoProductDetailTest() throws Exception {
		QixinProductDetailGotoRequest qixinProductDetailGotoRequest = new QixinProductDetailGotoRequest();
		qixinProductDetailGotoRequest.setCaseCode("QX000000002602");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/insurance/qixin/gotoProductDetail",this.getToken(),GotoProductDetailResponse.class,qixinProductDetailGotoRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void gotoPersonInsureListTest() throws Exception {
		QixinGotoBaseRequest qixinGotoBaseRequest = new QixinGotoBaseRequest();
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/insurance/qixin/gotoPersonInsureList",this.getToken(),GotoProductDetailResponse.class,qixinGotoBaseRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void insuranceCategoryTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/insurance/qixin/insuranceCategory",null,CimInsuranceCategory.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void gotoQuestionSummaryTest() throws Exception {
		
		CimInsuranceQuestionSummaryRequest req = new CimInsuranceQuestionSummaryRequest();
		req.setAge("30");
		req.setFamilyEnsure("0");
		req.setFamilyLoan("4");
		req.setFamilyMember("1,3");
		req.setSex("男");
		req.setYearIncome("1,3,4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/insurance/qixin/questionSummary",this.getToken(),GotoProductDetailResponse.class,req);
		LOGGER.info(baseResponse.toString());
		
	}
	
	@Test
	public void queryQquestionResultTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/insurance/qixin/queryQquestionResult",this.getToken(),InsuranceQuestionResultReponse.class);
		LOGGER.info(baseResponse.toString());
	}
}
