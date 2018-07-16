package com.linkwee.web.request;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.datatable.DataTable;

public class EveryDayRedPacketCalRequest extends DataTable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7490334549827671603L;
	/**
	 * base info
	 */
	
	private Date date;//时间
	
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
