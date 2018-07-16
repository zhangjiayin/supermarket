package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 津贴明细
 * @author ZhongLing
 *
 */
public class MonthSaleListAllowanceList extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 类型描述
	 */
	private String typeDesc;
	/**
	 * 津贴金额
	 */
	private Double amount;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
