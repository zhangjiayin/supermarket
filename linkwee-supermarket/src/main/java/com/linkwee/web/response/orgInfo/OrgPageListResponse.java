package com.linkwee.web.response.orgInfo;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;


public class OrgPageListResponse{

    /**
     *机构标签(多个以英文逗号分隔)
     */
	private String orgTag;
    /**
     *列表推荐，是否列表推荐 0-不推荐、1-推荐
     */
	private Integer listRecommend;
    /**
     *机构亮点介绍(多个以英文逗号分隔)
     */
	private String orgAdvantage;
    /**
     *安全评级 B,BB,BBB,A,AA,AAA
     */
	private String grade;
    /**
     *平台列表logo
     */
	private String platformlistIco;
    /**
     *	机构产品佣金率
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal orgFeeRatio;	
	/**
	 * 平台下可投的产品数量
	 */
	private Integer usableProductNums;
	/**
	 * 平台近3个月平均年化收益率
	 */
	private String averageRate;
	/**
	 * 机构编码
	 */
	private String orgNumber;
	/**
	 * 机构名称
	 */
	private String name;
	
	public String getOrgTag() {
		return orgTag;
	}
	public void setOrgTag(String orgTag) {
		this.orgTag = orgTag;
	}
	public Integer getListRecommend() {
		return listRecommend;
	}
	public void setListRecommend(Integer listRecommend) {
		this.listRecommend = listRecommend;
	}
	public String getOrgAdvantage() {
		return orgAdvantage;
	}
	public void setOrgAdvantage(String orgAdvantage) {
		this.orgAdvantage = orgAdvantage;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPlatformlistIco() {
		return platformlistIco;
	}
	public void setPlatformlistIco(String platformlistIco) {
		this.platformlistIco = platformlistIco;
	}
	public BigDecimal getOrgFeeRatio() {
		return orgFeeRatio;
	}
	public void setOrgFeeRatio(BigDecimal orgFeeRatio) {
		this.orgFeeRatio = orgFeeRatio;
	}
	public Integer getUsableProductNums() {
		return usableProductNums;
	}
	public void setUsableProductNums(Integer usableProductNums) {
		this.usableProductNums = usableProductNums;
	}
	public String getAverageRate() {
		return averageRate;
	}
	public void setAverageRate(String averageRate) {
		this.averageRate = averageRate;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
