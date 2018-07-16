package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 用户借贷资产信息
 * @author 颜彩云
 * @date 2016年5月19日
 *
 */
public class UserLoanRlt implements Serializable {

	private static final long serialVersionUID = -1901537490283470862L;

	/** 用户ID */
	private String userId;

	/** 状态 1:正常，2暂停 */
	private Integer status;
	/** 业务id */
	private String partnerId;
	/** 总借款金额 */
	private Long totalLoanAmount;
	/** 待还款金额 */
	private Long leftLoanAmount;
	/** 可用金额 */
	private Long usableAmount;
	/** 冻结资产 */
	private Long blockedAmount;
	/** 管理费总金额 */
	private Long managementAmount;
	/** 逾期总金额 */
	private Long overdueAmount;
	/** 逾期罚息总金额 */
	private Long penaltyAmount;
	/** 还款总金额 */
	private Long totalRepaymentAmount;
	/** 还款总本金 */
	private Long totalPrincipalAmount;
	/** 还款总利息 */
	private Long interestAmount;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getBlockedAmount() {
		return blockedAmount;
	}

	public void setBlockedAmount(Long blockedAmount) {
		this.blockedAmount = blockedAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Long getTotalLoanAmount() {
		return totalLoanAmount;
	}

	public void setTotalLoanAmount(Long totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}

	public Long getLeftLoanAmount() {
		return leftLoanAmount;
	}

	public void setLeftLoanAmount(Long leftLoanAmount) {
		this.leftLoanAmount = leftLoanAmount;
	}

	public Long getUsableAmount() {
		return usableAmount;
	}

	public void setUsableAmount(Long usableAmount) {
		this.usableAmount = usableAmount;
	}

	public Long getManagementAmount() {
		return managementAmount;
	}

	public void setManagementAmount(Long managementAmount) {
		this.managementAmount = managementAmount;
	}

	public Long getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(Long overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public Long getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(Long interestAmount) {
		this.interestAmount = interestAmount;
	}

	public Long getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(Long penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public Long getTotalRepaymentAmount() {
		return totalRepaymentAmount;
	}

	public void setTotalRepaymentAmount(Long totalRepaymentAmount) {
		this.totalRepaymentAmount = totalRepaymentAmount;
	}

	public Long getTotalPrincipalAmount() {
		return totalPrincipalAmount;
	}

	public void setTotalPrincipalAmount(Long totalPrincipalAmount) {
		this.totalPrincipalAmount = totalPrincipalAmount;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	
	
}
