package com.linkwee.api.response.act;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年08月24日 11:17:46
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class InvestRankingListResponse implements Serializable {
	
	private static final long serialVersionUID = 3224037362205897904L;
	
    /**
     *排行序号
     */
	private Integer rownum;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *投资额度
     */
	private String investAmt;
	
	/**
	 * 头像
	 */
	private String headImage;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(String investAmt) {
		this.investAmt = investAmt;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

}

