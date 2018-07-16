package com.linkwee.api.response.crm;

import java.util.List;

import com.linkwee.web.model.CrmCfpLevel;

public class FeeCalBaseDataResponse {

	/**
	 * 理财师职级信息
	 */
	private List<CrmCfpLevel> crmCfpLevelList;
	/**
	 * 理财师佣金信息
	 */
	private List<String> feeTypeList;
	
	public List<CrmCfpLevel> getCrmCfpLevelList() {
		return crmCfpLevelList;
	}
	public void setCrmCfpLevelList(List<CrmCfpLevel> crmCfpLevelList) {
		this.crmCfpLevelList = crmCfpLevelList;
	}
	public List<String> getFeeTypeList() {
		return feeTypeList;
	}
	public void setFeeTypeList(List<String> feeTypeList) {
		this.feeTypeList = feeTypeList;
	}
}
