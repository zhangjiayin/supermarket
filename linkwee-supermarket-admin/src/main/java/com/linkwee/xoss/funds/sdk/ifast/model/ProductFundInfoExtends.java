package com.linkwee.xoss.funds.sdk.ifast.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.Double4Serializer;
import com.linkwee.core.jackson.DoubleFundSerializer;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.xoss.funds.sdk.ifast.enums.FundStatusEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.FundTypeEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.IsBuyEnableEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.IsMMFundEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.IsQDIIEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.IsRecommendedEnums;
import com.linkwee.xoss.funds.sdk.ifast.enums.RiskRateEnums;

public class ProductFundInfoExtends extends ProductFundInfo {

	/**
	 * 基金状态信息
	 */
	private String fundStatusMsg;

	/**
	 * 基金类型代码信息
	 */
	private String fundTypeMsg;

	/**
	 * 是否可以购买信息
	 */
	private String isBuyEnableMsg;

	/**
	 * 是否是货币型基金信息
	 */
	private String isMMFundMsg;

	/**
	 * 是否是QDII基金信息
	 */
	private String isQDIIMsg;
	
	/**
	 * 	是否是奕丰推荐基金信息
	 */
	private String isRecommendedMsg;
	
	/**
	 * 基金的风险等级信息
	 */
	private String riskRateMsg;
	/**
	 * 近3个月的收益 
	 */
	@JsonSerialize(using = DoubleFundSerializer.class)
	private	double month3Msg;
	/**
	 * 成立以来的收益 
	 */
	@JsonSerialize(using = DoubleFundSerializer.class)
	private	double sinceLaunchMsg;
	/**
	 * 近1年的收益 
	 */
	@JsonSerialize(using = DoubleFundSerializer.class)
	private	double year1Msg;
	/**
	 * 近3年的收益 
	 */
	@JsonSerialize(using = DoubleFundSerializer.class)
	private	double year3Msg;
	/**
	 * 近5年的收益 
	 */
	@JsonSerialize(using = DoubleFundSerializer.class)
	private	double year5Msg;
	/**
	 * 最新净值，精确到4位小数，如1.0001 
	 */
	@JsonSerialize(using = DoubleFundSerializer.class)
	private	double navMsg;
	/**
	 * 货币型基金的最新万份收益。精确到4位小数 
	 */
	@JsonSerialize(using = Double4Serializer.class)
	private	double earningsPer10000Msg;
	
	public String getFundStatusMsg() {
		fundStatusMsg = EnumUtils.getMsgByKvmEnumValue(getFundStatus(), FundStatusEnums.values());
		return fundStatusMsg;
	}

	public void setFundStatusMsg(String fundStatusMsg) {
		this.fundStatusMsg = fundStatusMsg;
	}

	public String getFundTypeMsg() {
		fundTypeMsg = EnumUtils.getMsgByKvmEnumValue(getFundType(), FundTypeEnums.values());
		return fundTypeMsg;
	}

	public void setFundTypeMsg(String fundTypeMsg) {
		this.fundTypeMsg = fundTypeMsg;
	}

	public String getIsBuyEnableMsg() {
		isBuyEnableMsg = EnumUtils.getMsgByKvmEnumValue(getIsBuyEnable(), IsBuyEnableEnums.values());
		return isBuyEnableMsg;
	}

	public void setIsBuyEnableMsg(String isBuyEnableMsg) {
		this.isBuyEnableMsg = isBuyEnableMsg;
	}

	public String getIsMMFundMsg() {
		isMMFundMsg = EnumUtils.getMsgByKvmEnumValue(getIsMMFund(), IsMMFundEnums.values());
		return isMMFundMsg;
	}

	public void setIsMMFundMsg(String isMMFundMsg) {
		this.isMMFundMsg = isMMFundMsg;
	}

	public String getIsQDIIMsg() {
		isQDIIMsg = EnumUtils.getMsgByKvmEnumValue(getIsQDII(), IsQDIIEnums.values());
		return isQDIIMsg;
	}

	public void setIsQDIIMsg(String isQDIIMsg) {
		this.isQDIIMsg = isQDIIMsg;
	}

	public String getIsRecommendedMsg() {
		isRecommendedMsg = EnumUtils.getMsgByKvmEnumValue(getIsRecommended(), IsRecommendedEnums.values());
		return isRecommendedMsg;
	}

	public void setIsRecommendedMsg(String isRecommendedMsg) {
		this.isRecommendedMsg = isRecommendedMsg;
	}

	public String getRiskRateMsg() {
		riskRateMsg = EnumUtils.getMsgByKvmEnumValue(String.valueOf(getRiskRate()), RiskRateEnums.values());
		return riskRateMsg;
	}

	public void setRiskRateMsg(String riskRateMsg) {
		this.riskRateMsg = riskRateMsg;
	}

	public double getMonth3Msg() {
		month3Msg = getMonth3()*100;
		return month3Msg;
	}

	public void setMonth3Msg(double month3Msg) {
		this.month3Msg = month3Msg;
	}

	public double getSinceLaunchMsg() {
		sinceLaunchMsg = getSinceLaunch()*100;
		return sinceLaunchMsg;
	}

	public void setSinceLaunchMsg(double sinceLaunchMsg) {
		this.sinceLaunchMsg = sinceLaunchMsg;
	}

	public double getYear1Msg() {
		year1Msg = getYear1()*100;
		return year1Msg;
	}

	public void setYear1Msg(double year1Msg) {
		this.year1Msg = year1Msg;
	}

	public double getYear3Msg() {
		year3Msg = getYear3()*100;
		return year3Msg;
	}

	public void setYear3Msg(double year3Msg) {
		this.year3Msg = year3Msg;
	}

	public double getYear5Msg() {
		year5Msg = getYear5()*100;
		return year5Msg;
	}

	public void setYear5Msg(double year5Msg) {
		this.year5Msg = year5Msg;
	}

	public double getNavMsg() {
		navMsg = getNav();
		return navMsg;
	}

	public void setNavMsg(double navMsg) {
		this.navMsg = navMsg;
	}

	public double getEarningsPer10000Msg() {
		earningsPer10000Msg = getEarningsPer10000();
		return earningsPer10000Msg;
	}

	public void setEarningsPer10000Msg(double earningsPer10000Msg) {
		this.earningsPer10000Msg = earningsPer10000Msg;
	}
	
}
