package com.linkwee.api.activity.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ReceivingAddressRequest {

	 /**
     *收货人姓名
     */
	private String receivingUserName;
	
    /**
     *手机号码
     */
	@NotNull(message="手机号码不能为空")
	@Pattern(regexp="^1\\d{10}$",message="手机号码格式不对")
	private String mobile;
	
    /**
     *省份名称
     */
	private String provinceName;
	
    /**
     *城市名字
     */
	private String cityName;
	
    /**
     *收货人详细地址
     */
	private String receivingAddress;
	
    /**
     *第三方账户
     */
	private String thirdAccount;
	
    /**
     *地址类型(1:邮寄地址|2:爱奇艺账户)
     */
	@NotNull(message="地址类型不能为空")
	private Integer type;
	
    /**
     *地址类型名称
     */
	private String typeName;

	public String getReceivingUserName() {
		return receivingUserName;
	}

	public void setReceivingUserName(String receivingUserName) {
		this.receivingUserName = receivingUserName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getReceivingAddress() {
		return receivingAddress;
	}

	public void setReceivingAddress(String receivingAddress) {
		this.receivingAddress = receivingAddress;
	}

	public String getThirdAccount() {
		return thirdAccount;
	}

	public void setThirdAccount(String thirdAccount) {
		this.thirdAccount = thirdAccount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
