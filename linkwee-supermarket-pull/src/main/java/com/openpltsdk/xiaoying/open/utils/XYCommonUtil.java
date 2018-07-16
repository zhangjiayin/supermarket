package com.openpltsdk.xiaoying.open.utils;

import java.util.Iterator;
import java.util.Map;

import com.linkwee.xoss.util.CommonUtils;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;

public class XYCommonUtil {

	/** 
     * 将请求参数 转化成Builder对象
     * @param BodyParams 
     * @return 
     */  
    public static Builder getRequestBuilder(Object objectParams){
    	Map<String, String> bodyParamsMap = CommonUtils.getMapPairList(objectParams);
    	Builder builder =new FormBody.Builder();  
        if(bodyParamsMap != null){  
            Iterator<String> iterator = bodyParamsMap.keySet().iterator();  
            String key = "";  
            while (iterator.hasNext()) {  
                key = iterator.next().toString();  
                builder.add(key, bodyParamsMap.get(key));
            }  
        }
        return builder;    
    }  
}
