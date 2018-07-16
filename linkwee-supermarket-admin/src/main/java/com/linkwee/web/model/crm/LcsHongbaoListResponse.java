package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.xoss.util.WebUtil;

public class LcsHongbaoListResponse extends BaseEntity {

	private static final long serialVersionUID = 1840107652864359699L;
	public LcsHongbaoListResponse() {

	}

	public LcsHongbaoListResponse(LcsHongbaoListResp obj) {
		WebUtil.initObj(this,obj,DateUtils.FORMAT_LONG);
		if(obj.getAmountLimit() == 0) {
			this.setInvestLimit("不限");
		} else {
			this.setInvestLimit(obj.getAmount() + "起");
		} 
		
		if(obj.getFirstInvestLimit() == 0 ) {
			this.setFirstInvestLimit("不限");
		} else if (obj.getFirstInvestLimit() == 1) {
			this.setFirstInvestLimit("用户首投");
		} else if (obj.getFirstInvestLimit() == 2) {
			this.setFirstInvestLimit("平台首投");
		} else {
			this.setFirstInvestLimit("--");
		}
		
		if(obj.getProductLimit() == 1000) {
			this.setProduct("不限");
		} else if (obj.getProductLimit() == 1002) {
			this.setProduct("等于" + obj.getProductDeadline() + "天");
		} else if (obj.getProductLimit() == 1003) {
			this.setProduct("大于" + obj.getProductDeadline() + "天");
		}
		
		this.setExpireTime(DateUtils.format(obj.getExpireTime(), DateUtils.FORMAT_MM));
		
	}
	/**
	 * ID
	 */
	private int id;
	/**
	 * 红包金额
	 */
	private Double hongbaoAmount;
	/**
	 * 投资金额限制
	 */
	private String investLimit;
	/**
	 * 适用平台
	 */
	private String platform;
	/**
	 * 适用产品
	 */
	private String product;
	/**
	 * 首投限制
	 */
	private String firstInvestLimit;
	/**
	 * 过期时间
	 */
	private String expireTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getHongbaoAmount() {
		return hongbaoAmount;
	}
	public void setHongbaoAmount(Double hongbaoAmount) {
		this.hongbaoAmount = hongbaoAmount;
	}
	public String getInvestLimit() {
		return investLimit;
	}
	public void setInvestLimit(String investLimit) {
		this.investLimit = investLimit;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getFirstInvestLimit() {
		return firstInvestLimit;
	}
	public void setFirstInvestLimit(String firstInvestLimit) {
		this.firstInvestLimit = firstInvestLimit;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
