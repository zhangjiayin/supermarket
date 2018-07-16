package com.linkwee.api.response.act;

import java.math.BigDecimal;
import java.util.Date;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class RedpacketResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3791871733235249147L;
	
	/**
	 * 红包编号
	 */
	private String rid;
	/**
	 * 红包名称
	 */
	private String name;
	/**
	 * 红包描述
	 */
	private String remark;
	/**
	 * 	红包金额
	 */
	private BigDecimal money = new BigDecimal(0);
	/**
	 * 红包数量
	 */
	private Integer count;
	/**
	 * 红包状态     1=未派发|2=未使用|3=已使用|4=已过期
	 */
	private Integer status = 0;
	/**
	 * 用户手机
	 */
	private String userMobile;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户头像
	 */
	private String userImage;
	/**
	 * 过期时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	private Date expireDate;
	/**
	 * 理财师编号
	 */
	private String cfplannerId;
	/**
	 * 用户投资限制 0=不限|1=用户首投|2=平台首投
	 */
	private Integer investLimit;
	
	/**
	 * 平台限制 1=限制|0=不限制
	 */
	private Boolean platformLimit;
	
	/**
	 * 平台编号 platform_limit=1时有效
	 */
	private String platform;
	
	/**
	 * 产品限制 0=不限|1=限制产品编号|2=等于产品期限|3=大于等于产品期限
	 */
	private Integer productLimit = -1;
	
	/**
	 * 产品期限 	仅productLimit=2||3有效
	 */
	private Integer deadline;
	
	/**
	 * 产品名称 （仅productLimit=1时有效）
	 */
	private String productName;
	/**
	 * 理财师是否已派发红包(理财大师独有)  0=未派发  1=已派发
	 */
	private Integer cfpIfSend;
	/**
	 * 金额限制 0=不限|1=大于|2=大于等于
	 */
	private Integer amountLimit;
	/**
	 * 金额
	 */
	private String amount;
	
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRedpacketMoney(){
		return this.money.setScale(0,BigDecimal.ROUND_DOWN).toString();
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public String getRedpacketCount(){
		return count == null ? "" : count.toString();
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUseStatus(){
		if(status!=null){
			switch (status) {
			case 1:
				return "0";
			case 2:
				return "0";
			case 3:
				return "1";
			case 4:
				return "2";
			default:
				return "";
			}
		}
		return "";
	}
	
	public String getSendStatus(){
		if(status!=null){
			switch (status) {
			case 1:
				return "1";
			case 2:
				return "0";
			default:
				return "0";
			}
		}
		return "0";
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	public String getExpireTime() {
		return DateUtils.format(this.expireDate);
	}
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getRedpacketType(){
		return this.cfplannerId==null?"0":"1";
	}
	
	public void setCfplannerId(String cfplannerId) {
		this.cfplannerId = cfplannerId;
	}

	
	public Integer getInvestLimit() {
		return investLimit;
	}
	public void setInvestLimit(Integer investLimit) {
		this.investLimit = investLimit;
	}
	public Boolean getPlatformLimit() {
		return platformLimit;
	}
	
	public void setPlatformLimit(Boolean platformLimit) {
		this.platformLimit = platformLimit;
	}
	
	public String getPlatform() {
		return platform;
	}
	
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public Integer getProductLimit() {
		int result = 0;
		switch (productLimit) {
		case 1000:
			result = 0;
			break;
		case 1001:
			result = 1;
			break;
		case 1002:	
			result = 2;
			break;
		case 1003:
			result = 3;
			break;
		}
		return result;
	}
	
	public void setProductLimit(Integer productLimit) {
		
		this.productLimit = productLimit;
	}
	public Integer getDeadline() {
		return deadline;
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCfpIfSend() {
		return cfpIfSend;
	}
	public void setCfpIfSend(Integer cfpIfSend) {
		this.cfpIfSend = cfpIfSend;
	}
	public Integer getAmountLimit() {
		return amountLimit;
	}
	public void setAmountLimit(Integer amountLimit) {
		this.amountLimit = amountLimit;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
