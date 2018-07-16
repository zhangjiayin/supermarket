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
 * 充值记录分页查询请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/19 17:44
 */
public class SearchInRecordPageReq extends PageListReq {

	private static final long serialVersionUID = -6635047630967395244L;

	/**  充值流水号 */
	private Long inRecordNo;

	/** 业务编号  */
	private String partnerId;

	/** 用户编号 */
	private String userId;

	/** 充值类型 */
	private String bisType;

	/** 用户名称 */
	private String userName;

	/** 支付流水号 */
	private String payNo;

	/** 充值状态  {@link com.xiaoniu.account.utils.enums.InRecordStatus}*/
	private String status;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getInRecordNo() {
		return inRecordNo;
	}

	public void setInRecordNo(Long inRecordNo) {
		this.inRecordNo = inRecordNo;
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

	public String getBisType() {
		return bisType;
	}

	public void setBisType(String bisType) {
		this.bisType = bisType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
