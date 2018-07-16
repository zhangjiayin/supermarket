package com.linkwee.openplatform.xyb.helper;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.util.HttpsClient;
import com.openpltsdk.xyb.entity.Message;
import com.openpltsdk.xyb.entity.ServiceData;
import com.openpltsdk.xyb.security.MessageCrypt;

@Component
public class HttpRequestClientXyb {
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(HttpRequestClientXyb.class);
	
	@Resource
	private ConfigHelper configHelper;
	
	private MessageCrypt messageCrypt = null;
	
	@PostConstruct
	public void init(){
		Map<String, String> configMap = configHelper.getValuesByType(0, "XYB");
	    String appId = configMap.get("appId");
	    String aesKey = configMap.get("aesKey");
	    String token = configMap.get("token");
		messageCrypt = new MessageCrypt(appId,aesKey,token);
	}
	
	public ServiceData invokePostHttp(String url,ServiceData requestServiceData,String charSet) throws Throwable {
         
		if(StringUtils.isEmpty(charSet)){
			charSet = "UTF-8";
		}
		
		Gson gson = new Gson();
		Message message = messageCrypt.encryptMsg(requestServiceData);
		LOGGER.info("信用宝    HTTP POST请求地址={} 请求参数-加密前={},请求参数-加密后={}",new String[]{url,gson.toJson(requestServiceData),JSONObject.toJSONString(message)});
		
		HttpClient httpClient =  null;
		if(url.startsWith("https")){
			httpClient = HttpsClient.newHttpsClient();
		} else {
			httpClient = new DefaultHttpClient();
		}
		HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(JSONObject.toJSONString(message),charSet);//解决中文乱码问题    
        entity.setContentEncoding(charSet);    
        entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		String returnData = EntityUtils.toString(httpResponse.getEntity(),charSet);
        /*解密*/
        Message messageRt = gson.fromJson(returnData, Message.class);
        ServiceData returnServiceData= messageCrypt.decryptMsg(messageRt);
        LOGGER.debug("信用宝     HTTP POST请求返回,加密结果={},解密结果={}",returnData,gson.toJson(returnServiceData));
        
		return returnServiceData;
	}
	
	public String invokeGetHttp(String reqURL,List<NameValuePair> parameters,String charSet) throws Exception {
		HttpClient httpClient =  null;
		if(reqURL.startsWith("https")){
			httpClient = HttpsClient.newHttpsClient();//HttpsClient.newHttpsClient();
		} else {
			httpClient = new DefaultHttpClient();
		}
        StringBuilder requestURL = new StringBuilder();
        if (!reqURL.contains("?")) {
        	requestURL.append(reqURL).append("?").append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
        } else {
        	requestURL.append(reqURL).append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
        }		   
		HttpGet httpGet = new HttpGet(requestURL.toString());
		LOGGER.debug("HTTP GET请求地址: " + httpGet.getURI());
		HttpResponse httpResponse = httpClient.execute(httpGet); //执行GET请求
		String data = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
		return data;
	}
}
