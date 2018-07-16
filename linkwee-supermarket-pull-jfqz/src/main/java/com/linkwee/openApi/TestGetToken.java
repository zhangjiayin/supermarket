package com.linkwee.openApi;

import java.util.HashMap;
import java.util.Map;

import com.linkwee.xoss.util.HttpClientUtil;

public class TestGetToken {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//获取token接口
		//获取token接口
		
		String reqUrl = "http://opentest.9f.cn/openweb/getToken";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", "TezqnSOXG2FF50pt");
		params.put("appSecret", "3054faa9d6d5b1c75f257ae353027cd9");
		params.put("userId", "d94e51ce631f46bcb471726b2d8b392d");
		String res =null;
		try {
			res = HttpClientUtil.httpPost(reqUrl, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//{"message":"成功","data":{"token":"54365dd0d99769d064dfad1aaac01f3e"},"code":"SUCCESS"}
		System.out.println(res);
		
		//获取用户的资金信息接口
		//获取用户的资金信息接口
		
		/*String reqUrl = "http://opentest.9f.cn/openweb/api/member/queryAccountInfo?token=";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", "TezqnSOXG2FF50pt");
		params.put("appSecret", "3054faa9d6d5b1c75f257ae353027cd9");
		params.put("userId", "d94e51ce631f46bcb471726b2d8b392d");
		String res =null;
		try {
			res = HttpClientUtil.httpsGet(reqUrl+"0572909c8f411fda53f3cdd4bb609012");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//{"message":"成功","data":{"token":"54365dd0d99769d064dfad1aaac01f3e"},"code":"SUCCESS"}
		System.out.println(res);*/
		
	    //获取产品的接口
		//获取产品的接口
		
	/*	String reqUrl = "http://opentest.9f.cn/openweb/p/getProductList?token=";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", "TezqnSOXG2FF50pt");
		params.put("appSecret", "3054faa9d6d5b1c75f257ae353027cd9");
		params.put("userId", "d94e51ce631f46bcb471726b2d8b392d");
		String res =null;
		try {
			res = HttpClientUtil.httpsGet(reqUrl+"492a4c435a7b8360e51d1251e25d07be");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//{"message":"成功","data":{"token":"54365dd0d99769d064dfad1aaac01f3e"},"code":"SUCCESS"}
		System.out.println(res);*/
		
	}

}
