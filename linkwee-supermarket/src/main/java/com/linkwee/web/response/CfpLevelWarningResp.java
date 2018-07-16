package com.linkwee.web.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.DoubleSerializer;

public class CfpLevelWarningResp  extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 标题
	 */
	private String cfpLevelTitle;
	/**
	 * 标题2
	 */
	private String cfpLevelTitle2;
	/**
	 * 职级内容
	 */
	private String cfpLevelContent;
	/**
	 * 职级详情
	 */
	private String cfpLevelDetail;
	/**
	 * 系统当前时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date now;
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
	@JsonSerialize(using = DoubleSerializer.class)
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
	
	public String getCfpLevelTitle() {
		return cfpLevelTitle;
	}
	public void setCfpLevelTitle(String cfpLevelTitle) {
		this.cfpLevelTitle = cfpLevelTitle;
	}
	public String getCfpLevelTitle2() {
		return cfpLevelTitle2;
	}
	public void setCfpLevelTitle2(String cfpLevelTitle2) {
		this.cfpLevelTitle2 = cfpLevelTitle2;
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
	public Date getNow() {
		return new Date();
	}
	public void setNow(Date now) {
		this.now = now;
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
