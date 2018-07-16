package com.linkwee.web.model.crm;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.CrmInvestorOperation;

public class InvestorManagerDetailRespNew extends BaseEntity {

	private static final long serialVersionUID = 1840107652864359699L;

	/**
	 * ID
	 */
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 创建时间
	 */

	private Date createTime;
	/**
	 * 理财师名称
	 */
	private String cfplannerName;
	/**
	 * 理财师电话
	 */
	private String cfplannerMobile;
	
	/**
	 * 邀请数量
	 */
	private String freindsCount;
	
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 银行
	 */
	private String bankCard;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 身份证
	 */
	private String idCard;
	
	private int isfreeCustomer;
	
	/**
	 *理财师头像
	 */
	private String cfplannerHeadImage;
	
	private List<CrmInvestorOperation> changeLcsRecordList;
	
	/**
	 * 最后投资时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date rectInvestTime;
	/**
	 * 投资总额
	 */
	private Double totalInvestAmount;
	/**
	 * 在投金额
	 */
	private Double currInvestAmount;
	/**
	 * 投资笔数
	 */
	private int investCount;
	/**
	 * 收益总额
	 */
	private Double totalProfit;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCfplannerName() {
		return cfplannerName;
	}

	public void setCfplannerName(String cfplannerName) {
		this.cfplannerName = cfplannerName;
	}

	public String getCfplannerMobile() {
		return cfplannerMobile;
	}

	public void setCfplannerMobile(String cfplannerMobile) {
		this.cfplannerMobile = cfplannerMobile;
	}

	public String getFreindsCount() {
		return freindsCount;
	}

	public void setFreindsCount(String freindsCount) {
		this.freindsCount = freindsCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getIsfreeCustomer() {
		return isfreeCustomer;
	}

	public void setIsfreeCustomer(int isfreeCustomer) {
		this.isfreeCustomer = isfreeCustomer;
	}

	public List<CrmInvestorOperation> getChangeLcsRecordList() {
		return changeLcsRecordList;
	}

	public void setChangeLcsRecordList(List<CrmInvestorOperation> changeLcsRecordList) {
		this.changeLcsRecordList = changeLcsRecordList;
	}

	public String getCfplannerHeadImage() {
		return cfplannerHeadImage;
	}

	public void setCfplannerHeadImage(String cfplannerHeadImage) {
		this.cfplannerHeadImage = cfplannerHeadImage;
	}

	public Date getRectInvestTime() {
		return rectInvestTime;
	}

	public void setRectInvestTime(Date rectInvestTime) {
		this.rectInvestTime = rectInvestTime;
	}

	public Double getTotalInvestAmount() {
		return totalInvestAmount;
	}

	public void setTotalInvestAmount(Double totalInvestAmount) {
		this.totalInvestAmount = totalInvestAmount;
	}

	public Double getCurrInvestAmount() {
		return currInvestAmount;
	}

	public void setCurrInvestAmount(Double currInvestAmount) {
		this.currInvestAmount = currInvestAmount;
	}

	public int getInvestCount() {
		return investCount;
	}

	public void setInvestCount(int investCount) {
		this.investCount = investCount;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
