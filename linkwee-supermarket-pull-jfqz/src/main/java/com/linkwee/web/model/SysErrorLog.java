package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月22日 19:16:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysErrorLog implements Serializable {
	
	private static final long serialVersionUID = -237540285877290999L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *批次号
     */
	private String orderNo;
	
    /**
     *方法名
     */
	private String method;
	
    /**
     *请求参数
     */
	private String reqParm;
	
    /**
     *异常原因
     */
	private String cause;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *描述
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	
	public String getOrderNo(){
		return orderNo;
	}
	
	public void setMethod(String method){
		this.method = method;
	}
	
	public String getMethod(){
		return method;
	}
	
	public void setReqParm(String reqParm){
		this.reqParm = reqParm;
	}
	
	public String getReqParm(){
		return reqParm;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}

