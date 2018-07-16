package com.xiaoniu.account.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 
 * @Description: 查询用户资立流水记录参数
 * @author 周锋恒
 * @date 2015年7月29日
 *
 */
public class QueryBalanceReq implements Serializable {

	private static final long serialVersionUID = 12178115888236583L;

	/** 业务编号  */
	@NotNull
	private String partnerId;
	
	/** 用户编号 */
	@NotNull
	private String userId;
	
	/** 交易类型 see TransTypeEnum */
	private Integer tradeType;
	
	/** 交易类型    支持多个 交易类型查询  */
	private List<Integer> transTypes;
	
	/** 分页当前页 */
	@NotNull
	private Integer currentPage;
	
	/** 分页行数 */
	@NotNull
	private Integer pageSize;
	
	
	/** 开始时间      yyyy-MM-dd  */
	private String beginTime;
	
	/** 结束时间   yyyy-MM-dd  */
	private String endTime;
	
	
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCharset() {
		return charset;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<Integer> getTransTypes() {
		return transTypes;
	}

	public void setTransTypes(List<Integer> transTypes) {
		this.transTypes = transTypes;
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
