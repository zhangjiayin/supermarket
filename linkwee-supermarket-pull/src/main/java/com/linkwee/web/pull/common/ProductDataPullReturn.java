package com.linkwee.web.pull.common;

import java.util.List;

import com.linkwee.web.enums.OrgEnum;

public class ProductDataPullReturn<R> {

	private OrgEnum orgEnum;
	
	private List<R> rList;

	public OrgEnum getOrgEnum() {
		return orgEnum;
	}

	public void setOrgEnum(OrgEnum orgEnum) {
		this.orgEnum = orgEnum;
	}

	public List<R> getrList() {
		return rList;
	}

	public void setrList(List<R> rList) {
		this.rList = rList;
	}
}
