package com.linkwee.web.model.crm;

import com.linkwee.core.datatable.DataTable;

public class InvestorBindPlatformDatable extends DataTable {
	
	
	/**
	 * 查询条件(电话号码/姓名)
	 */
	private String searchValue;
	
	
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	
	
}
