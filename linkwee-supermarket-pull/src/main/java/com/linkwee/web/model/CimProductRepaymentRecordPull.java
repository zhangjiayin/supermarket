package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月14日 18:11:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductRepaymentRecordPull implements Serializable {
	
	private static final long serialVersionUID = 5707978531301901408L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *回款编号
     */
	private String repaymentId;
	
    /**
     *用户编号
     */
	private String userId;
	
    /**
     *投资编号
     */
	private String investId;
	
    /**
     *第三方机构产品编码
     */
	private String productId;
	
    /**
     *产品回款日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date repaymentTime;
	
    /**
     *回款金额
     */
	private BigDecimal repaymentAmount;
	
    /**
     *精准收益
     */
	private BigDecimal profit;
	
    /**
     *回款状态(2=回款中|3=回款成功|4=提前赎回部分本金)
     */
	private Integer status;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *回款记录执行添加状态(0-已执行添加|1-待执行添加)
     */
	private Integer updateStatus;
	
    /**
     *执行结果
     */
	private String message;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRepaymentId(String repaymentId){
		this.repaymentId = repaymentId;
	}
	
	public String getRepaymentId(){
		return repaymentId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setRepaymentTime(Date repaymentTime){
		this.repaymentTime = repaymentTime;
	}
	
	public Date getRepaymentTime(){
		return repaymentTime;
	}
	
	public void setRepaymentAmount(BigDecimal repaymentAmount){
		this.repaymentAmount = repaymentAmount;
	}
	
	public BigDecimal getRepaymentAmount(){
		return repaymentAmount;
	}
	
	public void setProfit(BigDecimal profit){
		this.profit = profit;
	}
	
	public BigDecimal getProfit(){
		return profit;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateStatus(Integer updateStatus){
		this.updateStatus = updateStatus;
	}
	
	public Integer getUpdateStatus(){
		return updateStatus;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

