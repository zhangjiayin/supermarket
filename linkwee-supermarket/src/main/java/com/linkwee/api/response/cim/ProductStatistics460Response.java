package com.linkwee.api.response.cim;

import com.linkwee.core.base.BaseEntity;

public class ProductStatistics460Response extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
     *分类id
     */
	private Integer cateId;
	
	 /**
     *浮动最小利率统计
     */
	private String flowMinRateStatistics;
	
    /**
     *浮动最大利率统计
     */
	private String flowMaxRateStatistics;

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getFlowMinRateStatistics() {
		return flowMinRateStatistics;
	}

	public void setFlowMinRateStatistics(String flowMinRateStatistics) {
		this.flowMinRateStatistics = flowMinRateStatistics;
	}

	public String getFlowMaxRateStatistics() {
		return flowMaxRateStatistics;
	}

	public void setFlowMaxRateStatistics(String flowMaxRateStatistics) {
		this.flowMaxRateStatistics = flowMaxRateStatistics;
	}

}
