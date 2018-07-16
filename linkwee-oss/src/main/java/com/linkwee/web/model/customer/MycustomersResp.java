package com.linkwee.web.model.customer;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：我的客户列表
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class MycustomersResp extends BaseEntity {

	private static final long serialVersionUID = -608452619418393092L;

	public MycustomersResp() {

	}

	/**
	 * 客户id
	 */
	private String customerId;
	/**
	 * 客户名称
	 */
	private String customerName;

	/**
	 * 客户手机号码
	 */
	private String customerMobile;

	/**
	 * 最近投资
	 */
	private Double nearInvestAmt;
	
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 最近投资日期
	 */
	private Date nearInvestDate;

	/**
	 * 到期日期
	 */
	private Date nearEndDate;
	/**
	 * 在投总额
	 */
	private Double currInvestAmt;
	/**
	 * 投资笔数
	 */
	private Integer totalInvestCount;

	/**
	 * 是否总要客户
	 */
	private Integer important;
	
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	/**
	 * 环信密码
	 */
	private String easemobPassword;
	
	/**
	 * 是否自由客户 1-是 0-否
	 */
	private Integer freecustomer;
	
	/**
	 * 是否新客户 
	 */
	private boolean isNewRegist;

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getImportant() {
		return important;
	}

	public void setImportant(Integer important) {
		this.important = important;
	}

	public Date getNearInvestDate() {
		return nearInvestDate;
	}

	public void setNearInvestDate(Date nearInvestDate) {
		this.nearInvestDate = nearInvestDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Double getNearInvestAmt() {
		return nearInvestAmt;
	}

	public void setNearInvestAmt(Double nearInvestAmt) {
		this.nearInvestAmt = nearInvestAmt;
	}

	public Date getNearEndDate() {
		return nearEndDate;
	}

	public void setNearEndDate(Date nearEndDate) {
		this.nearEndDate = nearEndDate;
	}

	public Double getCurrInvestAmt() {
		return currInvestAmt;
	}

	public void setCurrInvestAmt(Double currInvestAmt) {
		this.currInvestAmt = currInvestAmt;
	}

	public Integer getTotalInvestCount() {
		return totalInvestCount;
	}

	public void setTotalInvestCount(Integer totalInvestCount) {
		this.totalInvestCount = totalInvestCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getEasemobAcct() {
		return easemobAcct;
	}

	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}

	public String getEasemobPassword() {
		return easemobPassword;
	}

	public void setEasemobPassword(String easemobPassword) {
		this.easemobPassword = easemobPassword;
	}

	public Integer getFreecustomer() {
		return freecustomer;
	}

	public void setFreecustomer(Integer freecustomer) {
		this.freecustomer = freecustomer;
	}

	public boolean isNewRegist() {
		return isNewRegist;
	}

	public void setNewRegist(boolean isNewRegist) {
		this.isNewRegist = isNewRegist;
	}
	
	
}
