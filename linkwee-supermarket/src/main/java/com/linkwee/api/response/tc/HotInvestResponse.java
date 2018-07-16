package com.linkwee.api.response.tc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

public class HotInvestResponse {

	/**
	 * 金额
	 */
	private int amount;
	/**
	 * 保险产品名
	 */
	private String productName;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 期限
	 */
	private int term;
	/**
	 * 时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;
	/**
	 * 时间描述
	 */
	private String timeDesc;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 类型   1：网贷   2：保险
	 */
	private int type;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTimeDesc() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createTime);
		int minute = calendar.get(Calendar.MINUTE);
		if(minute == 0){
			int second = calendar.get(Calendar.SECOND);
			return second+"秒前";
		}else {
			return minute+"分钟前";
		}		
	}
	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}
	public String getUserName() {
		if(StringUtils.isNotBlank(userName)){
			return userName.replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1" + createAsterisk(userName.length() - 1));
		}else if(StringUtils.isNotBlank(mobile)){
			return mobile.substring(0,3)+"****"+mobile.substring(7);
		}else{
			return "钱**";
		}
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

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String createAsterisk(int length) {  
	    StringBuffer stringBuffer = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        stringBuffer.append("*");  
	    }  
	    return stringBuffer.toString();  
	} 
}
