package com.linkwee.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.core.util.DateUtils;
import com.linkwee.web.request.funds.ifast.GetOrderListRequest;
import com.linkwee.web.request.funds.ifast.GetOrderUserInfoRequest;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.IfastFundService;

public class IfastFundTest  extends TestSupport {

	@Resource
	private IfastFundService ifastFundService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Test
	public void pullOrderListTest(){
		start();
    	GetOrderListRequest getOrderListRequest =  new GetOrderListRequest();
    	
    	//查询时间间隔一天
    	Date now = new Date();
    	getOrderListRequest.setOrderDateStart(DateUtils.format(DateUtils.subDay(now, 100)));
    	getOrderListRequest.setOrderDateEnd(DateUtils.format(now));
    	
    	ifastFundService.pullOrderList(getOrderListRequest);
		end();
	}
	
//	@Test
//	public void getUserInfoByFundOrderTimeTest(){
//		start();
//		GetOrderUserInfoRequest getOrderUserInfoRequest  = new GetOrderUserInfoRequest();
//		Date now = new Date();
//		getOrderUserInfoRequest.setStartTime(DateUtils.subDay(now, 3));
//		getOrderUserInfoRequest.setEndTime(now);
//		crmUserInfoService.getUserInfoByFundOrderTime(getOrderUserInfoRequest);
//		end();
//	}
//	
//	@Test
//	public void updateOrderListTest(){
//		start();
//		ifastFundService.updateOrderList();
//		end();
//	}
	
}
