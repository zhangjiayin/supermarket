package com.linkwee.test.api.cim;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.AllAssessRequest;
import com.linkwee.api.request.cim.OrgMoneyDataRequest;
import com.linkwee.api.response.cim.AllAssessResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.cim.OrgMoneyDataDetail;

public class CimOrgRiskManageSynthesizeControllerTest  extends BaseTest{
	
	@Test
	public void allAssessTest() throws Exception {
		AllAssessRequest allAssessRequest = new AllAssessRequest();
		allAssessRequest.setOrgNo("OPEN_DONGFANGHUI_WEB");
		LOGGER.info("请求参数 allAssessRequest={}",JSONObject.toJSONString(allAssessRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/cimorgriskmanagesynthesize/allassess",null,AllAssessResponse.class,allAssessRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void orgdataTest() throws Exception {
		OrgMoneyDataRequest orgMoneyDataRequest = new OrgMoneyDataRequest();
		orgMoneyDataRequest.setOrgNo("OPEN_YINCHENGPAI_WEB");
		orgMoneyDataRequest.setQueryType(1);
		orgMoneyDataRequest.setTimeType(1);
		orgMoneyDataRequest.setDataNumber(5);
		LOGGER.info("请求参数 allAssessRequest={}",JSONObject.toJSONString(orgMoneyDataRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/cimorgriskmanagesynthesize/orgdata",null,OrgMoneyDataDetail.class,orgMoneyDataRequest);
		LOGGER.debug(baseResponse.toString());
	}

}
