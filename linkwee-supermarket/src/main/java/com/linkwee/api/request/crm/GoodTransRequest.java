package com.linkwee.api.request.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GoodTransRequest {
	
	/**
	 * 出单号
	 */
	private String billId;


	public String getBillId() {
		return billId;
	}


	public void setBillId(String billId) {
		this.billId = billId;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
