package com.linkwee.web.response;

public class PrizeSendResponse {

	private Integer id;
	/**
	 * 中奖客户手机号
	 */
	private String mobile;
	/**
	 * 奖品
	 */
	private String orderDesc;
	/**
	 * 是否发放
	 */
	private Integer issued;
	/**
	 * 是否幸运奖
	 */
	private Integer isFourtune;
	/**
	 * 收货人姓名
	 */
	private String receivingUserName;
	/**
	 * 收货人电话
	 */
	private String receivingMobile;
	/**
	 * 收货地址
	 */
	private String receivingAddress;
	/**
	 * 第三方账号
	 */
	private String thirdAccount;
	/**
	 * 类型 1：收货地址 2：爱奇艺账号等
	 */
	private String typeName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public Integer getIssued() {
		return issued;
	}
	public void setIssued(Integer issued) {
		this.issued = issued;
	}
	public Integer getIsFourtune() {
		return isFourtune;
	}
	public void setIsFourtune(Integer isFourtune) {
		this.isFourtune = isFourtune;
	}
	public String getReceivingUserName() {
		return receivingUserName;
	}
	public void setReceivingUserName(String receivingUserName) {
		this.receivingUserName = receivingUserName;
	}
	public String getReceivingMobile() {
		return receivingMobile;
	}
	public void setReceivingMobile(String receivingMobile) {
		this.receivingMobile = receivingMobile;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
