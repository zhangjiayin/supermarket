package com.openpltsdk.xyb.entity;

import java.util.Date;

/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：UserInfo
 * 类描述：用户信息
 * @author zenggang
 * @date 创建时间：2017年1月11日 上午11:22:19
 * 修改人：zenggang
 * 修改时间：2017年1月11日 上午11:22:19
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class UserInfo {
	/**
	 * username:三方平台用户名
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:22:29
	 * @version V1.3.1
	 */
	private String username;
	
	/**
	 * usernamep:信用宝用户名
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:22:39
	 * @version V1.3.1
	 */
	private String usernamep;
	
	/**
	 * registerAt:用户在平台的注册时间
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:22:55
	 * @version V1.3.1
	 */
	private Date registerAt;
	
	/**
	 * bindAt:平台用户和三方用户绑定时间
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:23:00
	 * @version V1.3.1
	 */
	private Date bindAt;
	
	/**
	 * bindType:绑定类型，0:表示三方带来的新用户，1:表示平台已有用户
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:23:09
	 * @version V1.3.1
	 */
	private int bindType;
	
	/**
	 * assets:用户资产信息
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:23:14
	 * @version V1.3.1
	 */
	private Assets assets;
	
	/**
	 * tags:标签
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:23:27
	 * @version V1.3.1
	 */
	private String[] tags;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernamep() {
		return usernamep;
	}

	public void setUsernamep(String usernamep) {
		this.usernamep = usernamep;
	}

	public Date getRegisterAt() {
		return registerAt;
	}

	public void setRegisterAt(Date registerAt) {
		this.registerAt = registerAt;
	}

	public Date getBindAt() {
		return bindAt;
	}

	public void setBindAt(Date bindAt) {
		this.bindAt = bindAt;
	}

	public int getBindType() {
		return bindType;
	}

	public void setBindType(int bindType) {
		this.bindType = bindType;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
}
