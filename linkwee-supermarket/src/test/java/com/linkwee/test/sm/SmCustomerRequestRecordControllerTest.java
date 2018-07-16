package com.linkwee.test.sm;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.sm.AddRequestRecordRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class SmCustomerRequestRecordControllerTest extends BaseTest{

	
	@Test
	public void testQueryLatestOrg() throws Exception {
		AddRequestRecordRequest addRequestRecordRequest = new AddRequestRecordRequest();
		addRequestRecordRequest.setRequestUrl("www.baidu.com");
		addRequestRecordRequest.setRequestUrlRemark("测试");
		LOGGER.info("请求参数 addRequestRecordRequest={}",JSONObject.toJSONString(addRequestRecordRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/trace/add",this.getToken(),BaseResponse.class,addRequestRecordRequest);
		LOGGER.info(baseResponse.toString());
	}
}
