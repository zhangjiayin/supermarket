package demo.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestClient {
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(HttpRequestClient.class);

	public static String invokePostHttp(String url, List<NameValuePair> parameters, String charSet) throws Exception {
		HttpClient httpClient =  null;
		if(url.startsWith("https")){
			httpClient = HttpsClient.newHttpsClient();
		} else {
			httpClient = new DefaultHttpClient();
		}
		HttpPost httpPost = new HttpPost(url);
		if(CollectionUtils.isNotEmpty(parameters)){	
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSet);
			formEntity.setContentEncoding(charSet);
			httpPost.setEntity(formEntity);
		}
		LOGGER.info("HTTP POST请求地址: " + url);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		String data = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
		return data;
	}
	
	public static String invokeGetHttp(String reqURL,List<NameValuePair> parameters,String charSet) throws Exception {
		HttpClient httpClient =  null;
		if(reqURL.startsWith("https")){
			httpClient = HttpsClient.newHttpsClient();//HttpsClient.newHttpsClient();
		} else {
			httpClient = new DefaultHttpClient();
		}
		
		if(CollectionUtils.isNotEmpty(parameters)){
			StringBuilder requestURL = new StringBuilder();
			if (!reqURL.contains("?")) {
				requestURL.append(reqURL).append("?").append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
			} else {
				requestURL.append(reqURL).append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
			}
			reqURL = requestURL.toString();
		}
		HttpGet httpGet = new HttpGet(reqURL);
		LOGGER.info("HTTP GET请求地址: " + reqURL);
		HttpResponse httpResponse = httpClient.execute(httpGet); //执行GET请求
		String data = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
		return data;
	}
}
