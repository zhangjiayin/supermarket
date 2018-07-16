package com.linkwee.web.service;

import com.linkwee.web.model.ApiInvokeLog;

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
public interface ApiInvokeLogService{

	public void updateApiInvokeLog(String apiName,String userId,Integer appType);
	
	public ApiInvokeLog queryApiInvokeLog(String apiName,String userId,Integer appType);
	
	/**
	 * 调用api中具体数据信息
	 * @param apiName
	 * @param contentId
	 * @param userId
	 * @param appType
	 */
	public void updateApiInvokeLog(String apiName,String contentId,String userId,Integer appType);
	
}
