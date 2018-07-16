package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 交易类型
 * @author 周锋恒
 * @date 2015年8月17日
 *
 */
public class TransTypeRlt implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 交易类型  */
	private Integer transType;		
	
	/** 交易名称 */
	private String transName;		
	
	/** 状态，1为正常，2为暂停 */
	private Integer status;		
	

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
