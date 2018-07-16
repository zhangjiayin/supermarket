package com.xiaoniu.account.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户资产统计请求
 * @author 颜彩云
 *
 */
public class UserAssetStatisticReq implements Serializable {

	private static final long serialVersionUID = 121781158882363837L;
	
	private int currPage = 1;//当前页
	private int pageSize = 20;//每页显示的数量
	private String partnerId;//交易/账户中心业务id
	private String systemType;//用户中心的业务id
	private Long startAmount;//开始金额（毫）
	private Long endAmount;//结束金额（毫）
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Long getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(Long startAmount) {
		this.startAmount = startAmount;
	}

	public Long getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(Long endAmount) {
		this.endAmount = endAmount;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
