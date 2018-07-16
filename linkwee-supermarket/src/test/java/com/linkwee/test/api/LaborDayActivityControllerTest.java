package com.linkwee.test.api;

import java.util.Map;

import org.junit.Test;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.ActLaborDayChangeFaceRecord;

public class LaborDayActivityControllerTest extends BaseTest{
	
	/**
	 * 热爱劳动人数
	 * @throws Exception
	 */
	@Test
	public void testLoveWorkNum() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/labor/loveWorkNum",this.getToken(),Map.class);		
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 记录换脸成功的图片
	 * @throws Exception
	 */
	@Test
	public void testChangeFaceRecord() throws Exception {
		ActLaborDayChangeFaceRecord record = new ActLaborDayChangeFaceRecord();
		record.setHeadImage("xxxx");
		record.setOpenid("xxxxxxxxxxxxxxx");
		record.setWeixinNickname("Avial");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/labor/changeFace/record",this.getToken(),BaseResponse.class,record);		
		LOGGER.debug(baseResponse.toString());
	}
	
}
