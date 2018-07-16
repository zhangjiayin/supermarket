package com.linkwee.web.model.cim;

import java.math.BigDecimal;
import java.util.List;

import com.linkwee.web.model.CimOrgRiskFresGrade;
import com.linkwee.web.model.User;

public class RiskHandleSave {
	
    /**
     *机构编码-不重复字段
     */
	private String orgNumber;

	/**
	 * 合规进度
	 */
	private String complianceProgress;
	
	/**
	 * FRES评分体系
	 */
	private List<CimOrgRiskFresGrade>  gradeTypes;
	
	/**
	 * 总得分
	 */
	private BigDecimal totalScore;
	
	/**
	 * 综合评级
	 */
	private String gradeStr;
	
	/**
	 * 操作人信息
	 */
	private User user;

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public String getComplianceProgress() {
		return complianceProgress;
	}

	public void setComplianceProgress(String complianceProgress) {
		this.complianceProgress = complianceProgress;
	}

	public List<CimOrgRiskFresGrade> getGradeTypes() {
		return gradeTypes;
	}

	public void setGradeTypes(List<CimOrgRiskFresGrade> gradeTypes) {
		this.gradeTypes = gradeTypes;
	}

	public BigDecimal getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}

	public String getGradeStr() {
		return gradeStr;
	}

	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
