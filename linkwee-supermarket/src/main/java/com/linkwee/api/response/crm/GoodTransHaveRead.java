package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * @描述：V4.1.1是否有未读喜报
 */
public class GoodTransHaveRead extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;
	
	public GoodTransHaveRead() {}
	
	
	/**
	 * 	是否有未读 0无1有
	 */
	private String haveRead = "0";

	public String getHaveRead() {
		return haveRead;
	}


	public void setHaveRead(String haveRead) {
		this.haveRead = haveRead;
	}

}
