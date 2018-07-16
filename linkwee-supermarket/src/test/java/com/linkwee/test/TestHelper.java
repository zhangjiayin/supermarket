package com.linkwee.test;


import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.SignUtils;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.OrgEnum;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.WebUtil;

public class TestHelper {
	protected final static Logger logger = LoggerFactory.getLogger(TestHelper.class);
	
	private static void initSign(AppEnum appEnum,Map<String,String> params){
		
		//系统参数
		String orgNumber = appEnum.getKey();
		String appSecret = appEnum.getValue();
		
		AppTypeEnum appTypeEnum = AppUtils.getAppType(orgNumber);
		PlatformEnum platformEnum = AppUtils.getPlatform(orgNumber);
        
        params.put("orgNumber", orgNumber);
        params.put("appKind",appTypeEnum.getValue());
        params.put("appClient", platformEnum.getValue());
        params.put("appVersion", "1.2.0");
        params.put("v", "1.0.0");
        params.put("timestamp", DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
        
        String sign = SignUtils.sign(params, appSecret);
        params.put("sign", sign);
	}
	
	private static void initOpenSign(OrgEnum openOrgEnum,Map<String,String> params){
		
		//系统参数
        params.put("orgNumber",openOrgEnum.getOrgNumber());
        params.put("orgKey",openOrgEnum.getOrgKey());
        params.put("timestamp", DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
        
        String sign = SignUtils.sign(params,openOrgEnum.getOrgSecret());
        params.put("sign", sign);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>BaseResponse remote(AppEnum appEnum,String url,String method,String token,Class<T> retClass,Object... params) throws Exception {
		Map<String,String> paramsMap = new HashMap<String,String>();
		List<File> files = new ArrayList<File>();
		if(params!=null && params.length>0){
			for(Object obj:params){
				if(obj instanceof File){
					files.add((File)obj);
				}else{
					Map<String,String> map = obj2Map(obj);
					paramsMap.putAll(map);
				}
			}
		}
		if(token!=null){
			paramsMap.put("token", token);
		}
		
		String str = null;
	    if(files.size()>0){
	    	//str = httpUpload(url,method,paramsMap,files.get(0));
	    }else{
	    	str = httpPost(appEnum,url,method,paramsMap);
	    }
		logger.info(str);
		if("".equals(str)){
			logger.error("httpPost请求返回结果为空");
			return new ErrorResponse("00000", "httpPost请求返回结果为空");
		}
		BaseResponse ret = null;
		try{
			ret = JSONObject.parseObject(str, BaseResponse.class);
			if(ret.getCode().equals("0")){
				SuccessResponse succ = JSONObject.parseObject(str,SuccessResponse.class);
				if("".equals(succ.getData())){
					return ret;
				}
				String data = JSONObject.toJSONString(succ.getData());
				if(data !=null){
					if(data.indexOf("pageSize") != -1){
						PaginatorResponse page = JSONObject.parseObject(data, PaginatorResponse.class);
						succ.setData(page);
						logger.info("请求结果返回PaginatorResponse	data={}",JSONObject.toJSONString(page.getDatas()));
						page.setDatas(JSONObject.parseArray(JSONObject.toJSONString(page.getDatas()), retClass));
					}else if(data.indexOf("datas") != -1){
						logger.info("请求结果返回List  data={}",data);
						JSONObject jSONObject = JSONObject.parseObject(data);
						Map<String,Object> retMap = new HashMap<String,Object>();
						retMap.put("datas", JSONObject.parseArray(jSONObject.getString("datas"), retClass));
						succ.setData(retMap);
					}else if(data.indexOf("data") != -1){
						logger.info("请求结果返回String  data={}",data);
						JSONObject jSONObject = JSONObject.parseObject(data);
						Map<String,Object> retMap = new HashMap<String,Object>();
						retMap.put("data", JSONObject.parseArray(jSONObject.getString("data"), String.class));
						succ.setData(retMap);
					} else{
						logger.info("请求结果返回Object 	succ.getData={}",JSONObject.toJSONString(succ.getData()));
						succ.setData(JSONObject.parseObject(data, retClass));
					}
				}
				return succ;
			}else{
				ErrorResponse error = operateError(str, ret);
				return error;
			}
		}catch(Exception e){
			logger.error("转化结果异常",e);
			ErrorResponse error = operateError(str, ret);
			return error;
		}
	}
	
	public static <T>BaseResponse remote(String url,String method,Class<T> retClass,Object... params) throws Exception{
		OrgEnum orgEnum = OrgEnum.OPEN_LUJINSUO_WEB;
		return remote(orgEnum,url,method,retClass,params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>BaseResponse remote(OrgEnum orgEnum,String url,String method,Class<T> retClass,Object... params) throws Exception {
		Map<String,String> paramsMap = new HashMap<String,String>();
		List<File> files = new ArrayList<File>();
		if(params!=null && params.length>0){
			for(Object obj:params){
				if(obj instanceof File){
					files.add((File)obj);
				}else{
					Map<String,String> map = obj2Map(obj);
					paramsMap.putAll(map);
				}
			}
		}
		
		String str = null;
	    if(files.size()>0){
	    	//str = httpUpload(url,method,paramsMap,files.get(0));
	    }else{
	    	str = httpPost(orgEnum,url,method,paramsMap);
	    }
		logger.info(str);
		if("".equals(str)){
			logger.error("httpPost请求返回结果为空");
			return new ErrorResponse("00000", "httpPost请求返回结果为空");
		}
		BaseResponse ret = null;
		try{
			ret = JSONObject.parseObject(str, BaseResponse.class);
			if(ret.getCode().equals("0")){
				SuccessResponse succ = JSONObject.parseObject(str,SuccessResponse.class);
				if("".equals(succ.getData())){
					return ret;
				}
				JSONObject data = (JSONObject)succ.getData();
				if(data!=null){
					if(data.containsKey("pageSize")){
						PaginatorResponse page = JSONObject.parseObject(data.toJSONString(), PaginatorResponse.class);
						succ.setData(page);
						List dataList = page.getDatas();
						if(dataList!=null&&dataList.size()>0){
							List<T> list = new ArrayList<T>(dataList.size());
							for(Object obj:dataList){
								JSONObject objStr = (JSONObject)obj;
								T jsonObj = JSONObject.parseObject(objStr.toJSONString(),retClass);
								list.add(jsonObj);
							}
							page.setDatas(list);
						}
					}else{
						succ.setData(JSONObject.toJavaObject(data, retClass));
					}
				}
				return succ;
			}else{
				ErrorResponse error = operateError(str, ret);
				return error;
			}
		}catch(Exception e){
			ErrorResponse error = operateError(str, ret);
			return error;
		}
	}

	private static ErrorResponse operateError(String str, BaseResponse ret) {
		try{
			ErrorResponse error = JSONObject.parseObject(str,ErrorResponse.class);
			if(Integer.parseInt(ret.getCode())!=140005){
				fail(error.toString());
			}
			return error;
		}catch(Exception e){
			logger.error("",e);
			fail(str);
		}
		return null;
	}
	
	private static SSLConnectionSocketFactory getSSLConnectionSocketFactory() throws Exception{
		//创建一个证书库，并将证书导入证书库  
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream instream = new FileInputStream(new File("D:/server.cer"));
		try {
			 CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
		     Certificate cer = cerFactory.generateCertificate(instream);  
		     keyStore.load(null, null);  
			 keyStore.setCertificateEntry("trust", cer);  
		} finally {
			instream.close();
		}
		SSLContext sslContext = SSLContexts.custom()  
		        .useTLS()  
		        .loadTrustMaterial(keyStore)  
		        .build();  
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext); 
		return sslsf;
	}
	
	private static String httpPost(OrgEnum openOrgEnum,String url,String method,Map<String,String> params) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			 httpclient = HttpClientBuilder.create().setSSLSocketFactory(getSSLConnectionSocketFactory()).build();
		}else{
			httpclient = HttpClients.createDefault();  
		}
        // 创建httppost
		//取消原有的method方式  采用rest风格  取消原有method方式  采用rest风格 则需要拼接字符串
		url = url + method;
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        //初始化签名
        initOpenSign(openOrgEnum,params);
        
        for(String key:params.keySet()){
        	formparams.add(new BasicNameValuePair(key,params.get(key)));  
        }
        logger.debug(WebUtil.creatUrl(url, params));
        UrlEncodedFormEntity uefEntity;
        logger.info("发送POST请求：url={},params={}",url,JSONObject.toJSONString(formparams));
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }  
	
	
	private static String httpPost(AppEnum appEnum,String url,String method,Map<String,String> params) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			 httpclient = HttpClientBuilder.create().setSSLSocketFactory(getSSLConnectionSocketFactory()).build();
			 //httpclient = HttpClients.createDefault();  
		}else{
			httpclient = HttpClients.createDefault();  
		}
        // 创建httppost
		//取消原有的method方式  采用rest风格  取消原有method方式  采用rest风格 则需要拼接字符串
		url = url + method;
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        //初始化签名
        initSign(appEnum,params);
        
        for(String key:params.keySet()){
        	formparams.add(new BasicNameValuePair(key,params.get(key)));  
        }
        logger.debug(WebUtil.creatUrl(url, params));
        UrlEncodedFormEntity uefEntity;
        logger.info("发送POST请求：url={},params={}",url,JSONObject.toJSONString(formparams));
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }  
	
	@SuppressWarnings("unchecked")
	private static Map<String,String>  obj2Map(Object obj) throws Exception{
		 Map<String,String> ret = new HashMap<String,String>();
		 if(obj==null){
			return ret;
		 }
		 if(obj instanceof Map){
			 return (Map<String,String>)obj;
		 }
		 Class<?> clazz = obj.getClass();
		 while(clazz!=null){
			 Field[] fields = clazz.getDeclaredFields();
			 for(Field field:fields){
				 String name = field.getName();
				 field.setAccessible(true);
				 Object value = field.get(obj);
				 field.setAccessible(false);
				 if(value!=null&&!"serialVersionUID".equals(name)&&!(value instanceof MultipartFile)){
					 ret.put(name, value.toString());
				 }
			 }
			 clazz = clazz.getSuperclass();
		 }
		 return ret;
	}
	
}
