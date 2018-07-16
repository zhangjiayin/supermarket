package com.linkwee.api.response.acc;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年12月21日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class IdCardRecoReponse implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
	
    /**
     *	身份证号码
     */
	private String idCard;
	 /**
     *	姓名
     */
	private String name;
	 /**
     *	错误代码
     */
	private String errorCode;
	/**
     *	错误信息
     */
	private String errorMsg;
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}

