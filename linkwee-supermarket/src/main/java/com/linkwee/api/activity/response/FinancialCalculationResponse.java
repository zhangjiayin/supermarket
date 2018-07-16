package com.linkwee.api.activity.response;

public class FinancialCalculationResponse {
	/**
     *标签
     */
	private String flag;	
    /**
     *财运
     */
	private String financial;
	/**
	 * 是否已测算
	 */
	private boolean hasCalculated;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFinancial() {
		return financial;
	}
	public void setFinancial(String financial) {
		this.financial = financial;
	}
	public boolean isHasCalculated() {
		return hasCalculated;
	}
	public void setHasCalculated(boolean hasCalculated) {
		this.hasCalculated = hasCalculated;
	}

}
