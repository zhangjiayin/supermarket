package com.linkwee.web.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.AuditStatusEnum;
import com.linkwee.web.enums.ProductTypeEnums;
import com.linkwee.web.enums.StatusEnum;
import com.linkwee.web.model.CimProduct;

public class ProductsListResponse extends CimProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 上架时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date auditPassTime;
	/**
     * 产品类型Text
     */
	private String productTypeText;
    /**
     * 产品状态Text
     */
	private String statusText;
	/**
	 * 审核状态Text
	 */
	private String auditStatusText;
	/**
	 * 期限\天Text
	 */
	private String deadLineValueText;
	/**
	 * 产品利率Text
	 */
	private String productRateText;
	
	public Date getAuditPassTime() {
		if(getAuditStatus() == 1){
			auditPassTime = getAuditTime();
		}
		return auditPassTime;
	}
	public void setAuditPassTime(Date auditPassTime) {
		this.auditPassTime = auditPassTime;
	}
	public String getProductTypeText() {
		
    	productTypeText = EnumUtils.getValueByKeyNull(getProductType(), ProductTypeEnums.values());
		return productTypeText;
	}
	public void setProductTypeText(String productTypeText) {
		this.productTypeText = productTypeText;
	}
	public String getStatusText() {	
    	statusText = EnumUtils.getValueByKeyNull(getStatus(), StatusEnum.values());
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getAuditStatusText() {
    	auditStatusText = EnumUtils.getValueByKeyNull(getAuditStatus(), AuditStatusEnum.values());
		return auditStatusText;
	}
	public void setAuditStatusText(String auditStatusText) {
		this.auditStatusText = auditStatusText;
	}
	public String getDeadLineValueText() {
		if (getIsFixedDeadline() == 1){
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天";
			}
		} else {
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(getDeadLineMaxSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined()+"~"+getDeadLineMaxSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天~"+getDeadLineMaxValue()+"天";
			}
		}
		return deadLineValueText;
	}
	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}
	public String getProductRateText() {
		if(getIsFlow() == 1){
			productRateText = getFlowMinRate()+"%";
		} else if(getIsFlow() == 2){
			productRateText = getFlowMinRate()+"%~"+getFlowMaxRate()+"%";
		}
		return productRateText;
	}
	public void setProductRateText(String productRateText) {
		this.productRateText = productRateText;
	}
}
