package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 用户资产流水记录信息
 * @author 周锋恒
 * @date 2015年7月29日
 *
 */
public class LoanRecordRlt implements Serializable {

	private static final long serialVersionUID = -1901537490283470862L;

	private String userId;//用户id
	private String partnerId;//业务id，用来区毫钱罐子和众筹
	private Integer transType;//交易类型(见t_trans_type表)
	private String transName;//交易类型描述
	private String transRecordId;//交易记录id
	private Long transAmount;//交易金额(单位：毫)
	private Long beforeTotalLoanAmount;//交易前总借款金额(单位：毫)
	private Long afterTotalLoanAmount;//交易后总总借款金额(单位：毫)
	private String totalOp;//总借款金额交易操作符，= 表示总借款金额前后不变，+ 表示交易后总借款金额=交易前总借款金额+交易金额， - 表示交易后总借款金额=交易前总借款金额-交易金额
	private Long beforeLeftLoanAmount;//交易前待还款金额(单位：毫)
	private Long afterLeftLoanAmount;//交易后待还款金额(单位：毫)
	private String leftOp;// 待还款金额操作符，=， +， - 的描述和总借款金额一致
	private Long beforeUsableAmount;//交易前可用金额(单位：毫)
	private Long afterUsableAmount;//交易后可用金额(单位：毫)
	private String usableOp;// 可用金额操作符，=， +， - 的描述和总借款金额一致
	private Long beforeBlockedAmount;//交易前冻结资产(单位：毫)
	private Long afterBlockedAmount;//交易后冻结资产(单位：毫)
	private String blockedOp;// 冻结资产操作符，=， +， - 的描述和总借款金额一致
	private Long beforeManagementAmount;//交易前管理费总金额(单位：毫)
	private Long afterManagementAmount;//交易后管理费总金额(单位：毫)
	private String managementOp;// 管理费总金额操作符，=， +， - 的描述和总借款金额一致
	private Long beforePenaltyAmount;//交易前逾期罚息总金额(单位：毫)
	private Long afterPenaltyAmount;//交易前逾期罚息总金额(单位：毫)
	private String penaltyOp;// 逾期罚息总金额操作符，=， +， - 的描述和总借款金额一致
	private Long beforeTotalPrincipalAmount;//交易前还款本金总金额(单位：毫)
	private Long afterTotalPrincipalAmount;//交易前还款本金总金额(单位：毫)
	private String principalOp;// 还款本金总金额操作符，=， +， - 的描述和总借款金额一致
	private Long beforeInterestAmount;//交易前还款利息总金额(单位：毫)
	private Long afterInterestAmount;//交易后还款利息总金额(单位：毫)
	private String interestOp;// 还款利息总金额操作符，=， +， - 的描述和总借款金额一致
	private Date transDate;//交易日期
	private Date createTime;//创建时间
	private String remark;//备注
	private String extend1;//扩展字段1
	private String extend2;//扩展字段2
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getTransRecordId() {
		return transRecordId;
	}

	public void setTransRecordId(String transRecordId) {
		this.transRecordId = transRecordId;
	}

	public Long getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(Long transAmount) {
		this.transAmount = transAmount;
	}

	public Long getBeforeTotalLoanAmount() {
		return beforeTotalLoanAmount;
	}

	public void setBeforeTotalLoanAmount(Long beforeTotalLoanAmount) {
		this.beforeTotalLoanAmount = beforeTotalLoanAmount;
	}

	public Long getAfterTotalLoanAmount() {
		return afterTotalLoanAmount;
	}

	public void setAfterTotalLoanAmount(Long afterTotalLoanAmount) {
		this.afterTotalLoanAmount = afterTotalLoanAmount;
	}

	public String getTotalOp() {
		return totalOp;
	}

	public void setTotalOp(String totalOp) {
		this.totalOp = totalOp;
	}

	public Long getBeforeLeftLoanAmount() {
		return beforeLeftLoanAmount;
	}

	public void setBeforeLeftLoanAmount(Long beforeLeftLoanAmount) {
		this.beforeLeftLoanAmount = beforeLeftLoanAmount;
	}

	public Long getAfterLeftLoanAmount() {
		return afterLeftLoanAmount;
	}

	public void setAfterLeftLoanAmount(Long afterLeftLoanAmount) {
		this.afterLeftLoanAmount = afterLeftLoanAmount;
	}

	public String getLeftOp() {
		return leftOp;
	}

	public void setLeftOp(String leftOp) {
		this.leftOp = leftOp;
	}

	public Long getBeforeUsableAmount() {
		return beforeUsableAmount;
	}

	public void setBeforeUsableAmount(Long beforeUsableAmount) {
		this.beforeUsableAmount = beforeUsableAmount;
	}

	public Long getAfterUsableAmount() {
		return afterUsableAmount;
	}

	public void setAfterUsableAmount(Long afterUsableAmount) {
		this.afterUsableAmount = afterUsableAmount;
	}

	public String getUsableOp() {
		return usableOp;
	}

	public void setUsableOp(String usableOp) {
		this.usableOp = usableOp;
	}

	public Long getBeforeBlockedAmount() {
		return beforeBlockedAmount;
	}

	public void setBeforeBlockedAmount(Long beforeBlockedAmount) {
		this.beforeBlockedAmount = beforeBlockedAmount;
	}

	public Long getAfterBlockedAmount() {
		return afterBlockedAmount;
	}

	public void setAfterBlockedAmount(Long afterBlockedAmount) {
		this.afterBlockedAmount = afterBlockedAmount;
	}

	public String getBlockedOp() {
		return blockedOp;
	}

	public void setBlockedOp(String blockedOp) {
		this.blockedOp = blockedOp;
	}

	public Long getBeforeManagementAmount() {
		return beforeManagementAmount;
	}

	public void setBeforeManagementAmount(Long beforeManagementAmount) {
		this.beforeManagementAmount = beforeManagementAmount;
	}

	public Long getAfterManagementAmount() {
		return afterManagementAmount;
	}

	public void setAfterManagementAmount(Long afterManagementAmount) {
		this.afterManagementAmount = afterManagementAmount;
	}

	public String getManagementOp() {
		return managementOp;
	}

	public void setManagementOp(String managementOp) {
		this.managementOp = managementOp;
	}

	public Long getBeforePenaltyAmount() {
		return beforePenaltyAmount;
	}

	public void setBeforePenaltyAmount(Long beforePenaltyAmount) {
		this.beforePenaltyAmount = beforePenaltyAmount;
	}

	public Long getAfterPenaltyAmount() {
		return afterPenaltyAmount;
	}

	public void setAfterPenaltyAmount(Long afterPenaltyAmount) {
		this.afterPenaltyAmount = afterPenaltyAmount;
	}

	public String getPenaltyOp() {
		return penaltyOp;
	}

	public void setPenaltyOp(String penaltyOp) {
		this.penaltyOp = penaltyOp;
	}

	public Long getBeforeTotalPrincipalAmount() {
		return beforeTotalPrincipalAmount;
	}

	public void setBeforeTotalPrincipalAmount(Long beforeTotalPrincipalAmount) {
		this.beforeTotalPrincipalAmount = beforeTotalPrincipalAmount;
	}

	public Long getAfterTotalPrincipalAmount() {
		return afterTotalPrincipalAmount;
	}

	public void setAfterTotalPrincipalAmount(Long afterTotalPrincipalAmount) {
		this.afterTotalPrincipalAmount = afterTotalPrincipalAmount;
	}

	public String getPrincipalOp() {
		return principalOp;
	}

	public void setPrincipalOp(String principalOp) {
		this.principalOp = principalOp;
	}

	public Long getBeforeInterestAmount() {
		return beforeInterestAmount;
	}

	public void setBeforeInterestAmount(Long beforeInterestAmount) {
		this.beforeInterestAmount = beforeInterestAmount;
	}

	public Long getAfterInterestAmount() {
		return afterInterestAmount;
	}

	public void setAfterInterestAmount(Long afterInterestAmount) {
		this.afterInterestAmount = afterInterestAmount;
	}

	public String getInterestOp() {
		return interestOp;
	}

	public void setInterestOp(String interestOp) {
		this.interestOp = interestOp;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
