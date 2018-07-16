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

import com.xiaoniu.account.utils.ReqValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 提现统计请求参数.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/12 9:36
 */
public class WithdrawStatsReq implements Serializable {

	private static final long serialVersionUID = -6531493382014787773L;

	/** 业务方编码 */
	@NotNull
	private String partnerId;

	/** 业务开始时间 */
	private String bisBeginTime;

	/** 业务结束时间 */
	private String bisEndTime;

	/** 通知开始时间 */
	private String noticeBeginTime;

	/** 通知结束时间 */
	private String noticeEndTime;

	/** 签名编码 utf-8 */
	@NotNull
	private String charset;

	/** 签名 */
	@NotNull
	private String sign;

	public WithdrawStatsReq() {
	}

	/**
	 * 校验日期参数
	 * @return
	 */
	public boolean verifyDateParams(){
		if (StringUtils.isNotEmpty(this.getBisBeginTime()) && !ReqValidateUtil.isValidDate(this.getBisBeginTime())) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.getBisEndTime()) && !ReqValidateUtil.isValidDate(this.getBisEndTime())) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.getNoticeBeginTime()) && !ReqValidateUtil.isValidDate(this.getNoticeBeginTime())) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.getNoticeEndTime()) && !ReqValidateUtil.isValidDate(this.getNoticeEndTime())) {
			return false;
		}
		return true;
	}

	/**
	 * 设置日期参数
	 * @return
	 */
	public void initQueryDateParams(){
		if (StringUtils.isNotEmpty(this.getBisBeginTime())) {
			this.setBisBeginTime(this.getBisBeginTime() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(this.getBisEndTime())) {
			this.setBisEndTime(this.getBisEndTime() + " 23:59:59");
		}
		if (StringUtils.isNotEmpty(this.getNoticeBeginTime())) {
			this.setNoticeBeginTime(this.getNoticeBeginTime() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(this.getNoticeEndTime())) {
			this.setNoticeEndTime(this.getNoticeEndTime() + " 23:59:59");
		}
	}

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

	public String getBisBeginTime() {
		return bisBeginTime;
	}

	public void setBisBeginTime(String bisBeginTime) {
		this.bisBeginTime = bisBeginTime;
	}

	public String getBisEndTime() {
		return bisEndTime;
	}

	public void setBisEndTime(String bisEndTime) {
		this.bisEndTime = bisEndTime;
	}

	public String getNoticeBeginTime() {
		return noticeBeginTime;
	}

	public void setNoticeBeginTime(String noticeBeginTime) {
		this.noticeBeginTime = noticeBeginTime;
	}

	public String getNoticeEndTime() {
		return noticeEndTime;
	}

	public void setNoticeEndTime(String noticeEndTime) {
		this.noticeEndTime = noticeEndTime;
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
}
