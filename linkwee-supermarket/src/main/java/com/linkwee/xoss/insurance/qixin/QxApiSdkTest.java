/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.linkwee.xoss.insurance.qixin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huize.qixin.api.req.product.ProductDetailReq;
import com.huize.qixin.api.req.product.ProductInsureAttrReq;
import com.huize.qixin.api.req.trial.DefaultTrialReq;
import com.huize.qixin.api.resp.product.ProductDetailResp;
import com.huize.qixin.api.resp.product.ProductInsureAttrResp;
import com.huize.qixin.api.resp.trial.TrialResp;
import com.qixin.openapi.client.OpenApiRemoteOperation;
import com.qixin.openapi.client.common.ProxyFactory;
import com.qixin.openapi.conf.Configure;
import com.qixin.openapi.model.common.CommonResult;

/**
 * 齐欣SDK测试
 * o
 * @author hz15051252
 * @date 2017年5月18日 下午5:34:43
 * @version
 */
public class QxApiSdkTest {

    public QxApiSdkTest() {

    	Configure.Channel.channelKey = "TNMjU1NjQwMGM4NDB1009128";

    	Configure.Request.baseUrl = "http://tuneapi.qixin18.com/api/";

    	Configure.Channel.partnerId = 1009128;
    }

    private static OpenApiRemoteOperation operation = ProxyFactory.create(OpenApiRemoteOperation.class);

    ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {

	new QxApiSdkTest().testPorductDetail();

    }

    public void testPorductDetail() throws JsonProcessingException {

	ProductDetailReq req = new ProductDetailReq();

	req.setTransNo("HZ201705180010021111");

	req.setCaseCode("QX000000002602");

	req.setPartnerId(1009128);

	CommonResult<ProductDetailResp> res = operation.productDetail(req);

	System.out.println(objectMapper.writeValueAsString(res));
    }

    public void testProductInsureAttr() throws JsonProcessingException {
	ProductInsureAttrReq req = new ProductInsureAttrReq();
	req.setPartnerId(1000125);
	req.setCaseCode("QX000000100335");
	req.setTransNo("2017000001");
	CommonResult<ProductInsureAttrResp> result = operation.productInsuredAttr(req);
	System.out.println(objectMapper.writeValueAsString(result));
    }

    public void testDefaultTrial() throws JsonProcessingException {
	DefaultTrialReq req = new DefaultTrialReq();
	req.setPartnerId(1002422);
	req.setTransNo("INSY2017052321642");
	req.setCaseCode("QX000000002215");
	CommonResult<TrialResp> result = operation.defaultTrial(req);
	System.out.println(objectMapper.writeValueAsString(result));
    }

}
