package com.linkwee.api.response.crm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：职级特权
 *
 *
 */
public class PartnerJobGradeResponse extends BaseEntity {
	private static final long serialVersionUID = -5418637411733755341L;

	public PartnerJobGradeResponse() {

	}

	/**
	 * 职级
	 */
	private String jobGrade;
	
	/**
	 * 职级描述
	 */
	private String jobGradeDesc;
	/**
	 * 职级内容
	 */
	private String cfpLevelContent;
	/**
	 * 职级详情
	 */
	private String cfpLevelDetail;
	/**
	 * 新版本追加进度提醒-下月晋级xx进度
	 */
	private String cfpLevelTitleNew;
	/**
	 * 新版本追加进度提醒-年化业绩最大金额
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Integer yearpurAmountMaxNew;
	/**
	 * 新版本追加进度提醒-实际年化业绩
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Double yearpurAmountActualNew;
	/**
	 * 新版本追加进度提醒-直接下级理财师最大人数
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private int lowerLevelCfpMaxNew;
	/**
	 * 新版本追加进度提醒-实际下级理财师人数
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private int lowerLevelCfpActualNew;	
	/**
	 * 新版本追加进度提醒-下月职级评定时间
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone="GMT+8")
	private Date cfpLevelNextMonth;
	/**
	 * 新版本追加进度提醒-直接下级理财师职级
	 */
	private String lowerLevelCfp;
	
	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public String getJobGradeDesc() {
		return jobGradeDesc;
	}

	public void setJobGradeDesc(String jobGradeDesc) {
		this.jobGradeDesc = jobGradeDesc;
	}
	public String getCfpLevelContent() {
		return cfpLevelContent;
	}

	public void setCfpLevelContent(String cfpLevelContent) {
		this.cfpLevelContent = cfpLevelContent;
	}

	public String getCfpLevelDetail() {
		return cfpLevelDetail;
	}

	public void setCfpLevelDetail(String cfpLevelDetail) {
		this.cfpLevelDetail = cfpLevelDetail;
	}

	public String getCfpLevelTitleNew() {
		return cfpLevelTitleNew;
	}

	public void setCfpLevelTitleNew(String cfpLevelTitleNew) {
		this.cfpLevelTitleNew = cfpLevelTitleNew;
	}

	public Double getYearpurAmountActualNew() {
		return yearpurAmountActualNew;
	}

	public void setYearpurAmountActualNew(Double yearpurAmountActualNew) {
		this.yearpurAmountActualNew = yearpurAmountActualNew;
	}

	public int getLowerLevelCfpMaxNew() {
		return lowerLevelCfpMaxNew;
	}

	public void setLowerLevelCfpMaxNew(int lowerLevelCfpMaxNew) {
		this.lowerLevelCfpMaxNew = lowerLevelCfpMaxNew;
	}

	public int getLowerLevelCfpActualNew() {
		return lowerLevelCfpActualNew;
	}

	public void setLowerLevelCfpActualNew(int lowerLevelCfpActualNew) {
		this.lowerLevelCfpActualNew = lowerLevelCfpActualNew;
	}

	public Date getCfpLevelNextMonth() {
		return cfpLevelNextMonth;
	}

	public void setCfpLevelNextMonth(Date cfpLevelNextMonth) {
		this.cfpLevelNextMonth = cfpLevelNextMonth;
	}

	public String getLowerLevelCfp() {
		return lowerLevelCfp;
	}

	public void setLowerLevelCfp(String lowerLevelCfp) {
		this.lowerLevelCfp = lowerLevelCfp;
	}

	public Integer getYearpurAmountMaxNew() {
		return yearpurAmountMaxNew;
	}

	public void setYearpurAmountMaxNew(Integer yearpurAmountMaxNew) {
		this.yearpurAmountMaxNew = yearpurAmountMaxNew;
	}
}
