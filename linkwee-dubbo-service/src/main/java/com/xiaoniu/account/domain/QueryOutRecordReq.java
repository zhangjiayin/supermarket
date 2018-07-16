package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 查询提现记录
 * @author 周锋恒
 * @date 2015年8月12日
 *
 */
public class QueryOutRecordReq implements Serializable {

	private static final long serialVersionUID = 12178115888236383L;

	/** 业务编号  */
	private String partnerId;
	
	/** 用户编号 */
	private String userId;
	
	/**  提现流水号 */
	private Long outRecordNo;
	
	/** 分页当前页 */
	@NotNull
	private Integer currentPage;
	
	/** 分页行数 */
	@NotNull
	private Integer pageSize;
	
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
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return userId;
	}

	public Long getOutRecordNo() {
		return outRecordNo;
	}

	public void setOutRecordNo(Long outRecordNo) {
		this.outRecordNo = outRecordNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
