package com.linkwee.web.model.customer;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：客户详情
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class CustomerDetailResp extends BaseEntity {
	private static final long serialVersionUID = -226463495944568640L;
	
	/**
	 * 最小投资记录时间
	 */
	private Date minTime;

	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 会员等级
	 */
	private Integer memberLevel;
	/**
	 * 会员等级
	 */
	private String memberLevelDesc;
	/**
	 * 客户手机号码
	 */
	private String customerMobile;
	/**
	 * 注册时间
	 */
	private Date registerDate;
	/**
	 * 首单时间
	 */
	private Date firstRcpDate;
	/**
	 * 历史投资总额(单位元)
	 */
	private Double totalInvestAmt;
	/**
	 * 当前在投(单位元)
	 */
	private Double currInvestAmt;
	/**
	 * 获取佣金(单位元)
	 */
	private Double feeAmt;
	
	/**
	 * 是否重要客户
	 */
	private Integer important;
	
	

	public Integer getImportant() {
		return important;
	}

	public void setImportant(Integer important) {
		this.important = important;
	}

	public Date getMinTime() {
		return minTime;
	}

	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getMemberLevelDesc() {
		return memberLevelDesc;
	}

	public void setMemberLevelDesc(String memberLevelDesc) {
		this.memberLevelDesc = memberLevelDesc;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getFirstRcpDate() {
		return firstRcpDate;
	}

	public void setFirstRcpDate(Date firstRcpDate) {
		this.firstRcpDate = firstRcpDate;
	}

	public Double getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(Double totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public Double getCurrInvestAmt() {
		return currInvestAmt;
	}

	public void setCurrInvestAmt(Double currInvestAmt) {
		this.currInvestAmt = currInvestAmt;
	}

	public Double getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(Double feeAmt) {
		this.feeAmt = feeAmt;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
