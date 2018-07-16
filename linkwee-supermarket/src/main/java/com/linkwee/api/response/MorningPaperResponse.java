package com.linkwee.api.response;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.mc.Classroom;

public class MorningPaperResponse extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3045257627294806760L;
	/**
	 * 头像
	 */
	private String headImg;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 签名
	 */
	private String autograph;
	/**
	 * 早报
	 */
	private Classroom morningPaper;
	
	private String qrcode;
	
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public Classroom getMorningPaper() {
		return morningPaper;
	}
	public void setMorningPaper(Classroom morningPaper) {
		this.morningPaper = morningPaper;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
}
