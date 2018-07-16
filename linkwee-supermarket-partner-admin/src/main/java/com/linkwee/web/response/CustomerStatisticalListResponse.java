package com.linkwee.web.response;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.StringUtils;

public class CustomerStatisticalListResponse {
	private String name;
	private String mobile;
	private String idcard;
	private Integer source;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date time;
	private BigDecimal totalAmt;
	private Integer totalCount;
	private BigDecimal investAmt;
	private Integer investCount;
	private BigDecimal fee;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdcard() {
		if(StringUtils.isBlank(idcard))return "";
		String prefix = idcard.substring(0,6);
		String suffix = idcard.substring(idcard.length()-4);
		return org.apache.commons.lang.StringUtils.join(new Object[]{prefix,"******",suffix});
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getSource() {
		return  ObjectUtils.equals(source, 0) ? "邀请" : "分配";
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public BigDecimal getInvestAmt() {
		return investAmt.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public Integer getInvestCount() {
		return investCount;
	}
	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}
	public BigDecimal getFee() {
		return fee.setScale(2,BigDecimal.ROUND_DOWN);
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
