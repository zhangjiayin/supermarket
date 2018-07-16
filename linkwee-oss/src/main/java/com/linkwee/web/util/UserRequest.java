package com.linkwee.web.util;

import com.linkwee.core.base.BaseEntity;

public class UserRequest extends BaseEntity {
	private String number; //编码
	private String loginName; //登录名
	private String name;  //真实姓名
	private String password; //密码
	private String sessionId; //登录会话ID
	private String oldPwd;// 旧密码
	private String newPwd;// 新密码
	private String compwd;// 确认密码
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getCompwd() {
		return compwd;
	}
	public void setCompwd(String compwd) {
		this.compwd = compwd;
	}
}