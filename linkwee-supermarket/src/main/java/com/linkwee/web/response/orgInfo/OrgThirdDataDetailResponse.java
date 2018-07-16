package com.linkwee.web.response.orgInfo;


import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.OrgSkipData;

public class OrgThirdDataDetailResponse {
	
	/**
	 * 机构信息
	 */
	private CimOrginfo cimOrginfo;

	/**
	 * 可购买产品数量
	 */
	private Integer productCount;
	
	/**
	 * 总资产
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal totalAssets = new BigDecimal(0);
	
	/**
	 * 必填项List
	 */
	private List<OrgSkipData> needList;
	
	/**
	 * 可选项List
	 */
	private List<OrgSkipData> chooseList;

	public CimOrginfo getCimOrginfo() {
		return cimOrginfo;
	}

	public void setCimOrginfo(CimOrginfo cimOrginfo) {
		this.cimOrginfo = cimOrginfo;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}

	public List<OrgSkipData> getNeedList() {
		return needList;
	}

	public void setNeedList(List<OrgSkipData> needList) {
		this.needList = needList;
	}

	public List<OrgSkipData> getChooseList() {
		return chooseList;
	}

	public void setChooseList(List<OrgSkipData> chooseList) {
		this.chooseList = chooseList;
	}
}
