package com.linkwee.web.response;

import com.linkwee.web.model.SmGrowthHandbook;

public class SmGrowthHandbookResponse extends SmGrowthHandbook{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3660036898668928100L;
	
	private String cfpLevel;

	public String getCfpLevel() {
		return cfpLevel;
	}

	public void setCfpLevel(String cfpLevel) {
		this.cfpLevel = cfpLevel;
	}

}
