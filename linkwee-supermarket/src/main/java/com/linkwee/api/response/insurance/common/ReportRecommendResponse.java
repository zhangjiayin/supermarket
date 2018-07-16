package com.linkwee.api.response.insurance.common;

import java.util.List;

import com.linkwee.web.model.CimInsuranceProductExtends;

public class ReportRecommendResponse {

	/**
	 * 保险分类名称
	 */
	private String categoryName;
	/**
	 * 推荐保额
	 */
	private Integer coverage;
	
	/**
	 * 保险列表
	 */
	private List<CimInsuranceProductExtends> cimInsuranceProductExtendsList;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCoverage() {
		return coverage;
	}

	public void setCoverage(Integer coverage) {
		this.coverage = coverage;
	}

	public List<CimInsuranceProductExtends> getCimInsuranceProductExtendsList() {
		return cimInsuranceProductExtendsList;
	}

	public void setCimInsuranceProductExtendsList(
			List<CimInsuranceProductExtends> cimInsuranceProductExtendsList) {
		this.cimInsuranceProductExtendsList = cimInsuranceProductExtendsList;
	}
}
