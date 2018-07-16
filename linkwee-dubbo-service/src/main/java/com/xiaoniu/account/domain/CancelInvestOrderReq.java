package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 取消投资请求参数
 * @author 周锋恒
 * 
 * @Date 2015年12月2日
 */
public class CancelInvestOrderReq extends BaseReq {
	
	private static final long serialVersionUID = 2206082162544274664L;	
	
	/** 手续费 */
	@NotNull
	private Long fee;

	private String remark;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
