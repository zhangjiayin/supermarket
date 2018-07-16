package com.xiaoniu.sms.util;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.xiaoniu.sms.enums.SmsCodeEnum;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DataGridResult<T> extends SmsResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalRecord;
	
	public DataGridResult(){
		
	}
	
	public DataGridResult(SmsCodeEnum codeEnum){
		super.setReturnCode(codeEnum.getReturnCode());
		super.setReturnMsg(codeEnum.getReturnMsg());
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}



}
