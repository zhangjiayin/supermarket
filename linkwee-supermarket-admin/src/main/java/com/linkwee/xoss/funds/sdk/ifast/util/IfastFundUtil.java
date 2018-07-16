package com.linkwee.xoss.funds.sdk.ifast.util;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.xoss.util.AppResponseUtil;

public class IfastFundUtil {

	public static BaseResponse converResponseBody(String responseBodyStr){
		BaseResponse baseResponse =  new BaseResponse();
		if(StringUtils.isNotBlank(responseBodyStr)){
			JSONObject responseBodyStrJ = JSONObject.parseObject(responseBodyStr);
			if(responseBodyStrJ.getString("code").equals("0000")){
				baseResponse = AppResponseUtil.getSuccessResponse(responseBodyStrJ.get("data")); 
			} else if(StringUtils.isBlank(responseBodyStrJ.getString("code"))){
				baseResponse = AppResponseUtil.getErrorData("-1", "查询奕丰基金无返回");
			} else {
				baseResponse = AppResponseUtil.getErrorData(responseBodyStrJ.getString("code"), "【奕丰金融】"+responseBodyStrJ.getString("message")); 
			}
		} else {
			baseResponse = AppResponseUtil.getErrorData("-1", "查询奕丰基金结果返回为空");
		}
		return baseResponse;
	}
}
