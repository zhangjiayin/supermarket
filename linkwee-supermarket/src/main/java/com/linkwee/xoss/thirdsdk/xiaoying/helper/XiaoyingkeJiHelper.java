package com.linkwee.xoss.thirdsdk.xiaoying.helper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.thirdsdk.xiaoying.open.XYOpenSDK;

@Component
public class XiaoyingkeJiHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XiaoyingkeJiHelper.class);
	
	@Resource
	private ConfigHelper configHelper;
	@Value("${XYKJ_KEYPATH}")
	private String keyPath = "/data/certificate/xiaoying/client058.p12";
	@Value("${XYKJ_CERTPATH}")
	private String certPath = "/data/certificate/xiaoying/server.keystore";
	
	/**
	 * 初始化小赢科技基础数据
	 * AppId = 100053
	 */
    @PostConstruct  
    public void init(){	    
        String keypwd = "open";
        String certPwd = "kLStEz";
        LOGGER.info("初始化小赢科技基础数据  keyPath={} certPath={}",keyPath,certPath);
        XYOpenSDK.sharedInstance().init(keyPath, keypwd, certPath, certPwd);
        XYOpenSDK.sharedInstance().setAppId("100053"); 
    }
    
	public String getRequestResult(String requestUrl, Object requestObj) {
        LOGGER.info("小赢科技发起API请求  requestUrl={} requestObj={}",requestUrl,JSONObject.toJSONString(requestObj));
		return XYOpenSDK.sharedInstance().apiRequestNew(requestUrl,requestObj);
	}
}
