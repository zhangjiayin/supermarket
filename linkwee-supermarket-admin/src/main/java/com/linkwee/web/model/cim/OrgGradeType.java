package com.linkwee.web.model.cim;

import java.math.BigDecimal;

import com.linkwee.web.model.CimOrgFresGradeType;

public class OrgGradeType extends CimOrgFresGradeType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *指标得分
     */
	private BigDecimal fresScore;
	
    /**
     *指标得分明细
     */
	private String scoreDetail;

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public BigDecimal getFresScore() {
		return fresScore;
	}

	public void setFresScore(BigDecimal fresScore) {
		this.fresScore = fresScore;
	}

	public String getScoreDetail() {
		return scoreDetail;
	}

	public void setScoreDetail(String scoreDetail) {
		this.scoreDetail = scoreDetail;
	}
}
