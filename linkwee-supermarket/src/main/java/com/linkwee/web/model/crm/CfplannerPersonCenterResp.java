package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 投资者端 个人中心 首页
 * 
 * @Date 2016年1月20日 下午5:36:22
 */
public class CfplannerPersonCenterResp extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 记账本
	 */
	private String accountBook;
	/**
	 * 优惠券
	 */
	private String coupon;
	/**
	 * 	客户成员
	 */
	private String customerMember; 
	/**
	 * 职级
	 */
	private String grade;
	/**
	 * 职级特权
	 */
	private String gradePrivi;
	/**
	 * 头像图片
	 */
	private String headImage;
	/**
	 * 保险
	 */
	private String insurance;
	/**
	 * 本月收入
	 */
	private String monthIncome;
	/**
	 * 未读消息数量
	 */
	private String msgCount;
	/**
	 * 	网贷
	 */
	private String netLoan;
	/**
	 * 是否有新的回款记录
	 */
//	private Boolean newPaymentRecordStatus = false;
	
	/**
	 * 是否有新的交易记录
	 */
	private Boolean newTranRecordStatus = false;
	/**
	 * 回款日历
	 */
	private String paymentDate;
	/**
	 * 理财师团队成员
	 */
	private String teamMember;
	/**
	 * 猎财余额
	 */
	private String totalAmount;
	/**
	 * 累计收入
	 */
	private String totalIncome;
	/**
	 * 交易记录
	 */
	private String tranRecordDate;
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
	public String getAccountBook() {
		return accountBook;
	}
	public void setAccountBook(String accountBook) {
		this.accountBook = accountBook;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getCustomerMember() {
		return customerMember;
	}
	public void setCustomerMember(String customerMember) {
		this.customerMember = customerMember;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGradePrivi() {
		return gradePrivi;
	}
	public void setGradePrivi(String gradePrivi) {
		this.gradePrivi = gradePrivi;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}
	public String getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(String msgCount) {
		this.msgCount = msgCount;
	}
	public String getNetLoan() {
		return netLoan;
	}
	public void setNetLoan(String netLoan) {
		this.netLoan = netLoan;
	}
	public Boolean getNewTranRecordStatus() {
		return newTranRecordStatus;
	}
	public void setNewTranRecordStatus(Boolean newTranRecordStatus) {
		this.newTranRecordStatus = newTranRecordStatus;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
	public String getTranRecordDate() {
		return tranRecordDate;
	}
	public void setTranRecordDate(String tranRecordDate) {
		this.tranRecordDate = tranRecordDate;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
