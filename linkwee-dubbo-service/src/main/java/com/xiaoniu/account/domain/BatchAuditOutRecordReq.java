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

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 批量审核接口参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/8/19 15:34
 */
public class BatchAuditOutRecordReq implements Serializable {

	private static final long serialVersionUID = 6723297284982866637L;

	/** 开始时间*/
	@NotNull
	private Date bisBeginTime;

	/** 结束时间*/
	@NotNull
	private Date bisEndTime;

	/** 业务编码 */
	@NotNull
	private String partnerId;

	/** 签名编码 */
	@NotNull
	private String charset;

	/** 签名 */
	@NotNull
	private String sign;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Date getBisBeginTime() {
		return bisBeginTime;
	}

	public void setBisBeginTime(Date bisBeginTime) {
		this.bisBeginTime = bisBeginTime;
	}

	public Date getBisEndTime() {
		return bisEndTime;
	}

	public void setBisEndTime(Date bisEndTime) {
		this.bisEndTime = bisEndTime;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
}
