package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.crm.MonthSaleListAllowanceList;
import com.linkwee.xoss.util.WebUtil;

/**
 * 津贴明细
 * @author ZhongLing
 *
 */
public class MonthSaleListAllowanceListResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public MonthSaleListAllowanceListResponse() {
	}

	public MonthSaleListAllowanceListResponse(MonthSaleListAllowanceList obj) {
		WebUtil.initObj(this, obj);
	}
	
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 类型描述
	 */
	private String typeDesc;
	/**
	 * 津贴金额
	 */
	private String amount;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
