/*************************************************************************
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY                                                      
 *
 *                COPYRIGHT (C) XIAONIU66 CORPORATION 2016                                                      
 *    ALL RIGHTS RESERVED BY XIAONIU66 CORPORATION. THIS PROGRAM    
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY   
 * XIAONIU66 CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN       
 * PERMISSION OF XIAONIU66 CORPORATION. USE OF COPYRIGHT NOTICE   
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.                       
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY        
 *************************************************************************/

package com.xiaoniu.account.domain;

import com.xiaoniu.account.domain.base.PageListReq;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 回款分页查询请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/19 17:56
 */
public class SearchReturnRecordPageReq extends PageListReq {

	private static final long serialVersionUID = -3889605354869686478L;

	/** 业务编号  */
	private String partnerId;

	/** 用户编号 */
	private String userId;

	/**  投资流水号 */
	private Long investRecordNo;

	/**  回款流水号 */
	private Long returnRecordNo;

	/** 产品代码  */
	private String productCode;

	/** 产品代码  */
	private String productName;

	/** 回款状态 */
	private String status;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getInvestRecordNo() {
		return investRecordNo;
	}

	public void setInvestRecordNo(Long investRecordNo) {
		this.investRecordNo = investRecordNo;
	}

	public Long getReturnRecordNo() {
		return returnRecordNo;
	}

	public void setReturnRecordNo(Long returnRecordNo) {
		this.returnRecordNo = returnRecordNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
