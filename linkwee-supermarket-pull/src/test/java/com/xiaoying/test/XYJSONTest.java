package com.xiaoying.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.TestSupport;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.request.PullInvestRecordRequest;
import com.linkwee.web.request.PullProductRequest;
import com.linkwee.web.request.PullRepaymentRecordRequest;
import com.openpltsdk.xiaoying.open.XYOpenSDK;
import com.openpltsdk.xiaoying.open.register.XYRegisterReq;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
public class XYJSONTest extends TestSupport{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XYJSONTest.class);

    @Before
    public void setup() throws Exception {
        String keyPath = "E:\\IdeaProjects\\xiaoying\\opensdk-java\\keys\\linghui\\client057.p12";
        String certPath = "E:\\IdeaProjects\\xiaoying\\opensdk-java\\keys\\linghui\\server.keystore";
        String keypwd = "open";
        String certPwd = "kLStEz";
        XYOpenSDK.sharedInstance().init(keyPath, keypwd, certPath, certPwd);
        XYOpenSDK.sharedInstance().setAppId("100053");
    }

    @Test
    public void XYLoanDetailJSONT() throws Exception {

        XYRegisterReq xyRegisterReq = new XYRegisterReq();
        xyRegisterReq.setMobile("13999999998");
        xyRegisterReq.setName("林x2");
        xyRegisterReq.setIdType(1);
        xyRegisterReq.setIdNo("44142619111111111113");
        xyRegisterReq.setEmail("27098838@qq.com");

        XYRegisterReq.IdPictures pictures = xyRegisterReq.new IdPictures();
        pictures.setFront("http://www.th7.cn/d/file/p/2015/06/18/f69d127454bae1b5a257854af6548512.png");
        pictures.setBack("http://www.th7.cn/d/file/p/2015/06/18/f69d127454bae1b5a257854af6548512.png");
        xyRegisterReq.setIdPictures(pictures);

        xyRegisterReq.formBodyBuilder();

        XYOpenSDK.sharedInstance().apiRequest(xyRegisterReq);
    }

	/**
	 * 5.1产品拉取接口
	 */
    @Test
    public void ProductListRequestTest() throws Exception {

       PullProductRequest pullProductRequest =  new PullProductRequest();

       String returnStr = XYOpenSDK.sharedInstance().apiRequestNew("https://api.xiaoying.com/invest/product-list?appid=100053&ver=v1.0",pullProductRequest); 
       LOGGER.info("小赢科技请求返回  returnStr={} ",returnStr);
    }
	
	/**
	 * 5.5投资记录拉取接口
	 */
	@Test
	public void pullInvestRecord(){
		
		PullInvestRecordRequest pullInvestRecordRequest = new PullInvestRecordRequest();
		pullInvestRecordRequest.setStartTime(DateUtils.format(DateUtils.subDay(new Date(), 1)));
		pullInvestRecordRequest.setEndTime(DateUtils.format(new Date()));
		LOGGER.info("投资记录拉取接口：pullInvestRecordRequest={}",JSONObject.toJSONString(pullInvestRecordRequest));
		
	    String returnStr = XYOpenSDK.sharedInstance().apiRequestNew("https://api.xiaoying.com/invest/query-invest-flow?appid=100053&ver=v1.0",pullInvestRecordRequest);
	    LOGGER.info("小赢科技请求返回  returnStr={} ",returnStr);
	}
	
	/**
	 * 5.6投资回款拉取接口
	 */
	@Test
	public void pullRepaymentRecord(){
		
		PullRepaymentRecordRequest pullRepaymentRecordRequest = new PullRepaymentRecordRequest();
		pullRepaymentRecordRequest.setStartTime(DateUtils.format(DateUtils.subDay(new Date(), 1)));
		pullRepaymentRecordRequest.setEndTime(DateUtils.format(new Date()));
		LOGGER.info("投资回款拉取接口：pullInvestRecordRequest={}",JSONObject.toJSONString(pullRepaymentRecordRequest));
		
	    String returnStr = XYOpenSDK.sharedInstance().apiRequestNew("https://api.xiaoying.com/invest/query-repay-flow?appid=100053&ver=v1.0",pullRepaymentRecordRequest);
	    LOGGER.info("小赢科技请求返回  returnStr={} ",returnStr);
	}
}
