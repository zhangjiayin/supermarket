package com.linkwee.web.response.act;

import com.linkwee.web.model.ActAddFeeCoupon;

public class AddFeeCouponResponse extends ActAddFeeCoupon{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9194712658486117258L;
	
	/**
	 * 加拥券编辑权限
	 */
	private String addFeeEditPermission;

	public String getAddFeeEditPermission() {
		return addFeeEditPermission;
	}

	public void setAddFeeEditPermission(String addFeeEditPermission) {
		this.addFeeEditPermission = addFeeEditPermission;
	}

}
