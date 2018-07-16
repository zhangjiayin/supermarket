package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RecordRlt implements Serializable {

	private static final long serialVersionUID = -4427702597639470903L;
	
	/** 记录ID */
	private String recordNo;
	
	
	public RecordRlt(String recordNo) {
		this.recordNo = recordNo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

}
