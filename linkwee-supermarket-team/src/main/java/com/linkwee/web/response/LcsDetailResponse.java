package com.linkwee.web.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class LcsDetailResponse {

	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 手机号
	 */
	private String secretMobile;
	/**
	 * 职级
	 */
	private String level;
	/**
	 * 上级理财师姓名
	 */
	private String parentUserName;
	/**
	 * 注册时间
	 */
	private String regTime;
	/**
	 * 团队层级
	 */
	private String teamDepth;
	/**
	 * 上级手机号
	 */
	private String parentMobile;
	/**
	 * 首投时间
	 */
	private String firstInvestTime;
	/**
	 * 最近登录时间
	 */
	private String lastLoginTime;
	/**
	 * 在投金额
	 */
	private BigDecimal investingAmount;
	/**
	 * 关注状态
	 */
	private int fouseStatus;
	/**
	 * 关注状态描述
	 */
	private String fouseStatusDesc;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {	
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLevel() {
		if(level.equals("TA")){
			return "见习";
		}else if(level.equals("SM1")){
			return "顾问";
		}else if(level.equals("SM2")){
			return "经理";
		}else if(level.equals("SM3")){
			return "总监";
		}else{
			return level; 
		}	
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getParentUserName() {
		return parentUserName;
	}
	public void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getParentMobile() {
		return parentMobile.substring(0,3)+"****"+parentMobile.substring(7);
	}
	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getFirstInvestTime() {
		return firstInvestTime;
	}
	public void setFirstInvestTime(String firstInvestTime) {
		this.firstInvestTime = firstInvestTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public BigDecimal getInvestingAmount() {		
		return investingAmount.setScale(2, BigDecimal.ROUND_DOWN);
	}
	public void setInvestingAmount(BigDecimal investingAmount) {
		this.investingAmount = investingAmount;
	}
	public String getTeamDepth() {
		if(teamDepth.equals("1")){
			return "直推";
		}else{
			return teamDepth+"级"; 
		}
	}
	public void setTeamDepth(String teamDepth) {
		this.teamDepth = teamDepth;
	}
	public int getFouseStatus() {
		return fouseStatus;
	}
	public void setFouseStatus(int fouseStatus) {
		this.fouseStatus = fouseStatus;
	}
	public String getFouseStatusDesc() {
		if(fouseStatus == 1){
			return "加入关注";
		}else{
			return "取消关注";
		}
	}
	public void setFouseStatusDesc(String fouseStatusDesc) {
		this.fouseStatusDesc = fouseStatusDesc;
	}
	public String getSecretMobile() {
		return mobile.substring(0,3)+"****"+mobile.substring(7);
	}
	public void setSecretMobile(String secretMobile) {
		this.secretMobile = secretMobile;
	}
}
