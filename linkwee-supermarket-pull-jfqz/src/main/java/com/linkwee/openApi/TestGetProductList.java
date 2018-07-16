package com.linkwee.openApi;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.openApi.response.TokenResponse;
import com.linkwee.xoss.util.HttpClientUtil;

public class TestGetProductList {

	public static void main(String[] args) {
		String getTokenUrl = "http://opentest.9f.cn/openweb/getToken";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", "TezqnSOXG2FF50pt");
		params.put("appSecret", "3054faa9d6d5b1c75f257ae353027cd9");
		params.put("userId", "d94e51ce631f46bcb471726b2d8b392d");
		String res =null;
		try {
			res = HttpClientUtil.httpPost(getTokenUrl, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
		JSONObject json = JSONObject.parseObject(res);
		TokenResponse ver = null;
 		try {
 			ver = json.toJavaObject(json, TokenResponse.class);
		} catch (Exception e) {
		}
	    //获取产品的接口
		//获取产品的接口
	
		String reqUrl = "http://opentest.9f.cn/openweb/p/getProductList?token=";
//		Map<String, String> paramsp = new HashMap<String, String>();
//		paramsp.put("appId", "TezqnSOXG2FF50pt");
//		paramsp.put("appSecret", "3054faa9d6d5b1c75f257ae353027cd9");
//		paramsp.put("userId", "d94e51ce631f46bcb471726b2d8b392d");
		String resProduct =null;
		try {
			resProduct = HttpClientUtil.httpsGet(reqUrl+ver.getData().getToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//{"message":"成功","data":{"token":"54365dd0d99769d064dfad1aaac01f3e"},"code":"SUCCESS"}
		System.out.println(resProduct);
		
	}

}
