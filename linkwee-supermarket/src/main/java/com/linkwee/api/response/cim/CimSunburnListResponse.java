package com.linkwee.api.response.cim;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2018年01月29日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class CimSunburnListResponse extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
    private String platfromName;//平台名称
	
	private String platfromIco;//平台logo
	
	private String investAmt;//投资金额
	
	private String status;//报单状态
	
	private String productDeadLine;//产品期限
	
	private String feeAmt;//返现金额
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date investTime;//投资时间
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date recordTime;//报单时间
	
	private String shareStatus;//报单状态：0=未晒单|1=已晒单
	
	private String thumbsTimes;//点赞次数
	
	private String mobile;
	
	private String id;
	
	private String investImage;
	
	private String headImage;
	
	private String feeStrategy;
	
	
	public String getFeeStrategy() {
		return feeStrategy;
	}

	public void setFeeStrategy(String feeStrategy) {
		this.feeStrategy = feeStrategy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updateTime;
	
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvestImage() {
		return investImage;
	}

	public void setInvestImage(String investImage) {
		this.investImage = investImage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getThumbsTimes() {
		return thumbsTimes;
	}

	public void setThumbsTimes(String thumbsTimes) {
		this.thumbsTimes = thumbsTimes;
	}

	public String getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}
	
	public String getPlatfromName() {
		return platfromName;
	}

	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}

	public String getPlatfromIco() {
		return platfromIco;
	}

	public void setPlatfromIco(String platfromIco) {
		this.platfromIco = platfromIco;
	}

	public String getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(String investAmt) {
		this.investAmt = investAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductDeadLine() {
		return productDeadLine;
	}

	public void setProductDeadLine(String productDeadLine) {
		this.productDeadLine = productDeadLine;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}