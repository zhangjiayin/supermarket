package com.linkwee.api.response.funds.ifast;

import java.util.List;

import com.linkwee.web.model.CimFundBaseDefined;

public class BaseDefinedResponse {

	/**
	 * 基金类型
	 */
	private List<CimFundBaseDefined> fundTypeList;
	
	/**
	 * 批量基金排序
	 */
	private List<CimFundBaseDefined> periodList;
	
	/**
	 * 默认基金类型
	 */
	private String defaultFundType = "MM";
	
	/**
	 * 默认基金排序
	 */
	private String defaultPeriod = "sinceLaunch";

	public List<CimFundBaseDefined> getFundTypeList() {
		return fundTypeList;
	}

	public void setFundTypeList(List<CimFundBaseDefined> fundTypeList) {
		this.fundTypeList = fundTypeList;
	}

	public List<CimFundBaseDefined> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<CimFundBaseDefined> periodList) {
		this.periodList = periodList;
	}

	public String getDefaultFundType() {
		return defaultFundType;
	}

	public void setDefaultFundType(String defaultFundType) {
		this.defaultFundType = defaultFundType;
	}

	public String getDefaultPeriod() {
		return defaultPeriod;
	}

	public void setDefaultPeriod(String defaultPeriod) {
		this.defaultPeriod = defaultPeriod;
	}
}
