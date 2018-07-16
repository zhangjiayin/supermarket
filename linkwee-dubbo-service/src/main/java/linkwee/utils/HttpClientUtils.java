package linkwee.utils;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtils {
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

	public static String invokePost(String reqURL,String charSet,Object... objectParams){
		reqURL = reqURL.trim();
		if(charSet==null) charSet = "UTF-8";
		HttpClient httpClient =  null;
		String returnData = null;
		if(reqURL.startsWith("https")){
			httpClient = WebClientDevWrapper.wrapClient(new DefaultHttpClient());
		} else {
			httpClient = new DefaultHttpClient();
		}
		HttpPost httpPost = new HttpPost(reqURL);
		List<NameValuePair> parameters = CommonUtils.getNameValuePairList(objectParams);
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSet);
			formEntity.setContentEncoding(charSet);
			httpPost.setEntity(formEntity);
			LOGGER.info("HTTP POST请求地址={},请求参数={} ",reqURL,JSONObject.toJSONString(parameters));
			httpClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			returnData = EntityUtils.toString(httpResponse.getEntity(),charSet);
			LOGGER.info("HTTP POST请求结果={}",returnData);
		} catch (Exception e) {
			LOGGER.info("HTTP  POST请求 异常：请求地址={},请求参数={} ",reqURL,JSONObject.toJSONString(parameters));
			LOGGER.error("HTTP POST请求 异常", e);
		}
		return returnData;
	}
	
	public static String invokeGet(String reqURL,String charSet,Object... objectParams){
		reqURL = reqURL.trim();
		HttpClient httpClient =  null;
		String returnData = null;
		if(reqURL.startsWith("https")){
			httpClient = WebClientDevWrapper.wrapClient(new DefaultHttpClient());
		} else {
			httpClient = new DefaultHttpClient();
		}
		List<NameValuePair> parameters = CommonUtils.getNameValuePairList(objectParams);
        StringBuilder requestURL = new StringBuilder();
        try {			
        	if (!reqURL.contains("?")) {
        		requestURL.append(reqURL).append("?").append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
        	} else {
        		requestURL.append(reqURL).append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
        	}		   
        	HttpGet httpGet = new HttpGet(requestURL.toString());
        	LOGGER.info("HTTP GET请求地址={},请求参数={}",reqURL,JSONObject.toJSONString(parameters));
        	httpClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
        	HttpResponse httpResponse = httpClient.execute(httpGet); //执行GET请求
        	returnData = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
        	LOGGER.info("HTTP GET请求结果={}",returnData);
		} catch (Exception e) {
			LOGGER.info("HTTP  GET请求 异常：请求地址={},请求参数={} ",reqURL,JSONObject.toJSONString(parameters));
			LOGGER.error("HTTP GET请求 异常", e);
		}
		return returnData;
	}
}
