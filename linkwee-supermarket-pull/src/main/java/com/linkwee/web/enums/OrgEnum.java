package com.linkwee.web.enums;

public enum OrgEnum {
	
	OPEN_XINYONGBAO_WEB("OPEN_XINYONGBAO_WEB");
	

	private OrgEnum(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	
	private String orgNumber;
	
	public String getOrgNumber() {
		return orgNumber;
	}
	
}
