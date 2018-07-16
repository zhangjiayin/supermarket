package com.linkwee.web.response;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * 投资统计
 * @author yalin
 * @date 2016年9月5日 下午3:15:51 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class InvestStatisticsResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 列表平台logo
	 */
	private String platformLogo;
	
	/**
	 * 在投本金(格式化)
	 */
	private String investingAmt;
	
	/**
	 * 在投本金
	 */
	private String investAmt;
	
	/**
	 * 在投收益
	 */
	private String investingProfit;
	
	/**
	 * 平台个人中心跳转接口
	 */
	private String orgUsercenterUrl;
	
	/**
	 * 第三方平台账号
	 */
	private String orgAccount;
	
	/**
	 * 机构编号
	 */
	private String orgNumber;
	
	/**
	 * 机构公钥
	 */
	private String orgKey;
	
	/**
	 * 时间戳
	 */
	private String timestamp;
	
	/**
	 * 请求来源
	 */
	private String requestFrom;
	
	/**
	 * 签名
	 */
	private String sign;
	
	/**
	 * 机构名称
	 */
	private String orgName;
	
	/**
	 * 占总投资的比例
	 */
	private String totalPercent;
	
	/**
	 * 机构公钥
	 */
	private String orgSecret;
	
    /**
     *平台详情跳转类型 0-直接跳转第三方  1-跳转本地机构详情页
     */
	private Integer orgUrlSkipJumpType;
	
    /**
     *平台产品跳转绑卡类型  0-跳转前不需要绑卡  1-跳转前需要绑卡
     */
	private Integer orgProductUrlSkipBindType;

	public String getInvestingAmt() {
		return investingAmt;
	}

	public void setInvestingAmt(String investingAmt) {
		this.investingAmt = investingAmt;
	}

	public String getInvestingProfit() {
		return investingProfit;
	}

	public void setInvestingProfit(String investingProfit) {
		this.investingProfit = investingProfit;
	}

	public String getPlatformLogo() {
		return platformLogo;
	}

	public void setPlatformLogo(String platformLogo) {
		this.platformLogo = platformLogo;
	}

	public String getOrgUsercenterUrl() {
		return orgUsercenterUrl;
	}

	public void setOrgUsercenterUrl(String orgUsercenterUrl) {
		this.orgUsercenterUrl = orgUsercenterUrl;
	}

	public String getOrgAccount() {
		return orgAccount;
	}

	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public String getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTotalPercent() {
		return totalPercent;
	}

	public void setTotalPercent(String totalPercent) {
		this.totalPercent = totalPercent;
	}

	public String getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(String investAmt) {
		this.investAmt = investAmt;
	}

	public String getOrgSecret() {
		return orgSecret;
	}

	public void setOrgSecret(String orgSecret) {
		this.orgSecret = orgSecret;
	}

	public Integer getOrgUrlSkipJumpType() {
		return orgUrlSkipJumpType;
	}

	public void setOrgUrlSkipJumpType(Integer orgUrlSkipJumpType) {
		this.orgUrlSkipJumpType = orgUrlSkipJumpType;
	}

	public Integer getOrgProductUrlSkipBindType() {
		return orgProductUrlSkipBindType;
	}

	public void setOrgProductUrlSkipBindType(Integer orgProductUrlSkipBindType) {
		this.orgProductUrlSkipBindType = orgProductUrlSkipBindType;
	}

}
