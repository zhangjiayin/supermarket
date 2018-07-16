package com.linkwee.web.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.api.PaginatorRequest;

public class HandbookRequest extends PaginatorRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974423154404684303L;
	
	/**
	 * 理财师等级
	 */
	private String cfpLevel;
	
	/**
     *流水号
     */
	private Integer id;
	
    /**
     *应用端口（1：理财师2：T呗）
     */
	private Integer appType;
	
    /**
     *类型编码
     */
	private String typeCode;
	
    /**
     *类型名称
     */
	private String typeName;
	
    /**
     *图片
     */
	private String img;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *简介
     */
	private String summary;
	
    /**
     *链接地址
     */
	private String linkUrl;
	
    /**
     *正文
     */
	private String content;
	
    /**
     *阅读量
     */
	private Integer readingAmount;
	
    /**
     *状态:0无效,1有效
     */
	private Integer status;
	
    /**
     *创建者
     */
	private String creator;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date crtTime;
	
    /**
     *显示排序
     */
	private Integer showInx;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validBegin;
	
    /**
     *结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validEnd;
	
    /**
     *修改时间
     */ 
	private String modifiyTime;
	
    /**
     *默认为0，0=不置顶,1=置顶
     */
	private Short isStick;
	
    /**
     *分享图标
     */
	private String shareIcon;
	
    /**
     *扩张字段1
     */
	private String extends1;
	
    /**
     *扩张字段2
     */
	private String extends2;
	
    /**
     *扩张字段3
     */
	private String extends3;
	
	/**
	 * 来源
	 */
	private String source;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCfpLevel() {
		return cfpLevel;
	}

	public void setCfpLevel(String cfpLevel) {
		this.cfpLevel = cfpLevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReadingAmount() {
		return readingAmount;
	}

	public void setReadingAmount(Integer readingAmount) {
		this.readingAmount = readingAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public Integer getShowInx() {
		return showInx;
	}

	public void setShowInx(Integer showInx) {
		this.showInx = showInx;
	}

	public Date getValidBegin() {
		return validBegin;
	}

	public void setValidBegin(Date validBegin) {
		this.validBegin = validBegin;
	}

	public Date getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
	}

	public Short getIsStick() {
		return isStick;
	}

	public void setIsStick(Short isStick) {
		this.isStick = isStick;
	}

	public String getShareIcon() {
		return shareIcon;
	}

	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}

	public String getExtends1() {
		return extends1;
	}

	public void setExtends1(String extends1) {
		this.extends1 = extends1;
	}

	public String getExtends2() {
		return extends2;
	}

	public void setExtends2(String extends2) {
		this.extends2 = extends2;
	}

	public String getExtends3() {
		return extends3;
	}

	public void setExtends3(String extends3) {
		this.extends3 = extends3;
	}

	public String getModifiyTime() {
		return modifiyTime;
	}

	public void setModifiyTime(String modifiyTime) {
		this.modifiyTime = modifiyTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
