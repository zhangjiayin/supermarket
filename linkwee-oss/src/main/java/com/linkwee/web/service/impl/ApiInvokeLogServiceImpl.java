package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.ApiInvokeLogDao;
import com.linkwee.web.model.ApiInvokeLog;
import com.linkwee.web.service.ApiInvokeLogService;



 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月16日 17:39:46
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("apiInvokeLogService")
public class ApiInvokeLogServiceImpl implements ApiInvokeLogService{
	
	@Autowired
	private ApiInvokeLogDao apiInvokeLogDao;
	
	public void updateApiInvokeLog(String apiName,String userId,Integer appType){
		ApiInvokeLog apiInvokeLog = queryApiInvokeLog(apiName,userId,appType);
		if(apiInvokeLog==null){
			apiInvokeLog = new ApiInvokeLog();
			apiInvokeLog.setUserId(userId);
			apiInvokeLog.setApiName(apiName);
			apiInvokeLog.setAppType(appType);
			apiInvokeLogDao.add(apiInvokeLog);
		}else{
			apiInvokeLog.setAccessCount(apiInvokeLog.getAccessCount() + 1);
			apiInvokeLog.setChgTime(new Date());
			apiInvokeLogDao.update(apiInvokeLog);
		}
	}
	/**
	 * 调用api中具体数据信息
	 * @param apiName
	 * @param contentId
	 * @param userId
	 * @param appType
	 */
	public void updateApiInvokeLog(String apiName,String contentId,String userId,Integer appType){
		String key = apiName;
		if(StringUtils.isNotBlank(contentId)){
			key+=apiName+"!"+contentId;
		}
		ApiInvokeLog apiInvokeLog = queryApiInvokeLog(key,userId,appType);
		if(apiInvokeLog==null){
			apiInvokeLog = new ApiInvokeLog();
			apiInvokeLog.setUserId(userId);
			apiInvokeLog.setApiName(key);
			apiInvokeLog.setAppType(appType);
			apiInvokeLogDao.add(apiInvokeLog);
		}else{
			apiInvokeLog.setAccessCount(apiInvokeLog.getAccessCount() + 1);
			apiInvokeLog.setChgTime(new Date());
			apiInvokeLogDao.update(apiInvokeLog);
		}
	}
	
	public ApiInvokeLog queryApiInvokeLog(String apiName,String userId,Integer appType){
		ApiInvokeLog apiInvokeLog = new ApiInvokeLog();
		apiInvokeLog.setUserId(userId);
		apiInvokeLog.setApiName(apiName);
		apiInvokeLog.setAppType(appType);
		List<ApiInvokeLog> list = apiInvokeLogDao.list(apiInvokeLog);
		if(list==null||list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

}
