package com.openpltsdk.xyb.entity;

import com.google.gson.JsonElement;

/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：ServiceData
 * 类描述：数据实体
 * @author zenggang
 * @date 创建时间：2017年1月11日 上午11:24:59
 * 修改人：zenggang
 * 修改时间：2017年1月11日 上午11:24:59
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class ServiceData {
	/**
	 * service:业务标识
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:24:42
	 * @version V1.3.1
	 */
	private String service;
	/**
	 * body:业务请求或响应
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:24:52
	 * @version V1.3.1
	 */
	private JsonElement body;
	
	public ServiceData(String service, JsonElement body) {
		this.service = service;
		this.body = (JsonElement) body;
	}
	
	public String getService() {
		return service;
	}
	
	public JsonElement getBody() {
		return body;
	}
}
