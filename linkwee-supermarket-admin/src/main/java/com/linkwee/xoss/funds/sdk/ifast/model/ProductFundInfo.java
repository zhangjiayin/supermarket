package com.linkwee.xoss.funds.sdk.ifast.model;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.linkwee.core.jackson.TimestampStringSerializer;

public class ProductFundInfo {

	/**
	 * 累计净值，精确到4位小数，如1.0001 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double accumulateNav;
	/**
	 * 日涨幅。精确到2位小数，单位1%，如1.02，即1.02% 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double day1Performance;
	/**
	 * 货币型基金的最新万份收益。精确到4位小数 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double earningsPer10000;
	/**
	 * 基金代码 
	 */
	private	String fundCode;
	/**
	 * 基金全称 
	 */
	private	String fundFullName;
	/**
	 * 基金公司名称 
	 */
	private	String fundHouse;
	/**
	 * 基金公司代码 
	 */
	private	Integer fundHouseCode;
	/**
	 * 基金经理列表，格式{基金经理代码：基金经理名称,...} 
	 */
	private	Map<String, String> fundManagers;
	/**
	 * 基金简称 
	 */
	private	String fundName;
	/**
	 * 基金最新规模，单位为亿 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double fundSize;
	/**
	 * 基金状态。0=募集期基金；1=申购期基金；2=封闭期基金；3=已清盘的基金。该接口不返回其他状态的基金 
	 */
	private	String fundStatus;
	/**
	 * 基金类型代码。MM：货币型；BOND：债券型；MIXED：混合型；CP：保本型；EQ：股票型；AI：另类型；INDEX：指数型；ST：分级型；UNKNOWN：其他 
	 */
	private	String fundType;
	/**
	 * 地理属性 
	 */
	private	String geographicalSector;
	/**
	 * 是否可以购买。0=可以购买，1=不能购买 
	 */
	private	String isBuyEnable;
	/**
	 * 是否是货币型基金。0=货币型基金，1=非货币型基金 
	 */
	private	String isMMFund;
	/**
	 * 是否是QDII基金。0=QDII，1=非QDII 
	 */
	private	String isQDII;
	/**
	 * 是否是奕丰推荐基金。0=奕丰推荐基金，0=非奕丰推荐基金 
	 */
	private	String isRecommended;
	/**
	 * 近3个月的收益 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double month3;
	/**
	 * 最新净值，精确到4位小数，如1.0001 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double nav;
	/**
	 * 最新净值日期 
	 */
	@JsonSerialize(using = TimestampStringSerializer.class)
	private	String navDate;
	/**
	 * 基金的风险等级。1=低风险；2=中低风险；3=中风险；4=中高风险；5=高风险 
	 */
	private	Integer riskRate;
	/**
	 * 货币型基金的七日年化收益。单位为1%，如2.5001，即为2.5001% 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double sevenDaysAnnualizedYield;
	/**
	 * 成立以来的收益 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double sinceLaunch;
	/**
	 * 行业属性 
	 */
	private	String specializeSector;
	/**
	 * 近1年的收益 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double year1;
	/**
	 * 近3年的收益 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double year3;
	/**
	 * 近5年的收益 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private	double year5;
	
	public double getAccumulateNav() {
		return accumulateNav;
	}
	public void setAccumulateNav(double accumulateNav) {
		this.accumulateNav = accumulateNav;
	}
	public double getDay1Performance() {
		return day1Performance;
	}
	public void setDay1Performance(double day1Performance) {
		this.day1Performance = day1Performance;
	}
	public double getEarningsPer10000() {
		return earningsPer10000;
	}
	public void setEarningsPer10000(double earningsPer10000) {
		this.earningsPer10000 = earningsPer10000;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundFullName() {
		return fundFullName;
	}
	public void setFundFullName(String fundFullName) {
		this.fundFullName = fundFullName;
	}
	public String getFundHouse() {
		return fundHouse;
	}
	public void setFundHouse(String fundHouse) {
		this.fundHouse = fundHouse;
	}
	public Integer getFundHouseCode() {
		return fundHouseCode;
	}
	public void setFundHouseCode(Integer fundHouseCode) {
		this.fundHouseCode = fundHouseCode;
	}
	public Map<String, String> getFundManagers() {
		return fundManagers;
	}
	public void setFundManagers(Map<String, String> fundManagers) {
		this.fundManagers = fundManagers;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public double getFundSize() {
		return fundSize;
	}
	public void setFundSize(double fundSize) {
		this.fundSize = fundSize;
	}
	public String getFundStatus() {
		return fundStatus;
	}
	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public String getGeographicalSector() {
		return geographicalSector;
	}
	public void setGeographicalSector(String geographicalSector) {
		this.geographicalSector = geographicalSector;
	}
	public String getIsBuyEnable() {
		return isBuyEnable;
	}
	public void setIsBuyEnable(String isBuyEnable) {
		this.isBuyEnable = isBuyEnable;
	}
	public String getIsMMFund() {
		return isMMFund;
	}
	public void setIsMMFund(String isMMFund) {
		this.isMMFund = isMMFund;
	}
	public String getIsQDII() {
		return isQDII;
	}
	public void setIsQDII(String isQDII) {
		this.isQDII = isQDII;
	}
	public String getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(String isRecommended) {
		this.isRecommended = isRecommended;
	}
	public double getMonth3() {
		return month3;
	}
	public void setMonth3(double month3) {
		this.month3 = month3;
	}
	public double getNav() {
		return nav;
	}
	public void setNav(double nav) {
		this.nav = nav;
	}
	public String getNavDate() {
		return navDate;
	}
	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}
	public Integer getRiskRate() {
		return riskRate;
	}
	public void setRiskRate(Integer riskRate) {
		this.riskRate = riskRate;
	}
	public double getSevenDaysAnnualizedYield() {
		return sevenDaysAnnualizedYield;
	}
	public void setSevenDaysAnnualizedYield(double sevenDaysAnnualizedYield) {
		this.sevenDaysAnnualizedYield = sevenDaysAnnualizedYield;
	}
	public double getSinceLaunch() {
		return sinceLaunch;
	}
	public void setSinceLaunch(double sinceLaunch) {
		this.sinceLaunch = sinceLaunch;
	}
	public String getSpecializeSector() {
		return specializeSector;
	}
	public void setSpecializeSector(String specializeSector) {
		this.specializeSector = specializeSector;
	}
	public double getYear1() {
		return year1;
	}
	public void setYear1(double year1) {
		this.year1 = year1;
	}
	public double getYear3() {
		return year3;
	}
	public void setYear3(double year3) {
		this.year3 = year3;
	}
	public double getYear5() {
		return year5;
	}
	public void setYear5(double year5) {
		this.year5 = year5;
	}

}
