package com.linkwee.xoss.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

public class CommonUtils {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
	
	/**
	 * 文件上传
	 * @param imgServer  图片服务器地址
	 * @param upload  文件
	 * @return  MD5
	 */
	public static String uploadFiles(String imgServer,MultipartFile file){
		String returnContent = null;
		try {
		     HttpPost httppost = new HttpPost(imgServer);
		     httppost.addHeader("Content-Type", file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
		     httppost.setEntity(new ByteArrayEntity(file.getBytes()));     
		     HttpResponse  httpResponse = HttpClients.createDefault().execute(httppost);
		     HttpEntity httpEntity =  httpResponse.getEntity();
		     String returnStr = EntityUtils.toString(httpEntity);
		     JSONObject jsonObj = JSONObject.parseObject(returnStr);
		     returnContent = jsonObj.getJSONObject("info").getString("md5");
		     LOGGER.info("文件上传返回  returnStr ={} returnContent={}",returnStr,returnContent);
		} catch (Exception e) {
			LOGGER.error("文件上传异常",e);
		}
		return returnContent;
	}
}
