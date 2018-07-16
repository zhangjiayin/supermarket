package com.linkwee.web.model.cim;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.RateSerializer;
import com.linkwee.web.model.CimOrgRiskFresGrade;

public class CimOrgRiskFresGradeExtends extends CimOrgRiskFresGrade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *类型排名
     */
	private Integer fresSort;
	
    /**
     *类型满分
     */
	@JsonSerialize(using=RateSerializer.class)
	private BigDecimal fresFullScore;

	public Integer getFresSort() {
		return fresSort;
	}

	public void setFresSort(Integer fresSort) {
		this.fresSort = fresSort;
	}

	public BigDecimal getFresFullScore() {
		return fresFullScore;
	}

	public void setFresFullScore(BigDecimal fresFullScore) {
		this.fresFullScore = fresFullScore;
	}
}
