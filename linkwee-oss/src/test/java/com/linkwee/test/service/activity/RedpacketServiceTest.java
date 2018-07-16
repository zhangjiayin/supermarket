package com.linkwee.test.service.activity;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkwee.activity.service.RedpacketService;
import com.linkwee.test.TestSupport;
import com.linkwee.web.request.RedPacketInfoRequest;

public class RedpacketServiceTest extends TestSupport{
	
	@Autowired
	private RedpacketService redpacketService;
	
	@Test
	public void insertRedpacketTest() throws Exception{
		RedPacketInfoRequest redPacketInfoRequest = new RedPacketInfoRequest();
		redPacketInfoRequest.setName("再红包测试一把");
		redPacketInfoRequest.setRemark("90天期以上,10000元以上");
		redPacketInfoRequest.setMoney(50d);
		redPacketInfoRequest.setValidDate(DateUtils.addDays(new Date(), 3));
		redPacketInfoRequest.setLimitInvestProduct(2);
		redPacketInfoRequest.setPids("1bd14ddc-2d66-4863-9383-dd2e0371ac10,2b427fb6-fd1d-4157-9ae7-f4036902c89f,1dc3a878-f078-4f09-8a18-541cfbdd5612,211106a6-ca5a-43f0-88b2-6bbd3f8fb447,81452c89-8b55-4a06-9f8d-f76a73a0e5fd");
		redPacketInfoRequest.setDeadline(90);
		redPacketInfoRequest.setLimitInvestUser(0);
		redPacketInfoRequest.setLimitMoney(1);
		redPacketInfoRequest.setMaxMoney(50000d);
		redPacketInfoRequest.setMaxMoney(10000d);
		//redpacketService.insertRedpacket(redPacketInfoRequest);
	}

}
