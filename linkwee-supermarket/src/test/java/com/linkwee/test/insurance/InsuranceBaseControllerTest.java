package com.linkwee.test.insurance;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.response.insurance.common.ReportRecommendResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.cim.CimInsuranceQuestionSummary;

public class InsuranceBaseControllerTest extends BaseTest {
	
	@Test
	public void testReportResultTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/insurance/base/testReportResult",this.getToken(),CimInsuranceQuestionSummary.class);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void testReportRecommendTest() throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("queryType", "1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/insurance/base/testReportRecommend",this.getToken(),ReportRecommendResponse.class,parameterMap);
		LOGGER.info(baseResponse.toString());
	}

}
