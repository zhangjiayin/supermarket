package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月08日 11:06:54
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceNotify implements Serializable {
	
	private static final long serialVersionUID = 4006441754018897408L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *投资记录id(系统产生的唯一标识)
     */
	private String billId;
	
    /**
     *保险机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *1-投保通知 2-支付通知 3-出单通知 4-退保通知 5-退保重出通知 9-电子保单生成成功通知
     */
	private Integer notifyType;
	
    /**
     *渠道用户标识(领会userid)
     */
	private String userId;
	
    /**
     *保险产品编码
     */
	private String productId;
	
    /**
     *投保单号
     */
	private String insureNum;
	
    /**
     *成功与否（1-成功、0-失败）
     */
	private Boolean state;
	
    /**
     *支付时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date payTime;
	
    /**
     *支付金额（单位：分）
     */
	private BigDecimal price;
	
    /**
     *在线支付网关标识 1：支付宝 3：银联 14：财付通 21:微信  -11：银行代扣
     */
	private String onlinePaymentId;
	
    /**
     *退保重出新单号（退保重出才会有信息）
     */
	private String newInsureNum;
	
    /**
     *退保的被保人(当按退保人退保时才会有信息)
     */
	private String cancelInsurants;
	
    /**
     *退保原因（暂时未返回退保原因）
     */
	private String cancelMsg;
	
    /**
     *失败原因
     */
	private String failMsg;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date creatTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date upTime;
	
    /**
     *产品佣金费率
     */
	private BigDecimal productFeeRate;
	
    /**
     *审核状态  0-待审核  1-系统审核通过 2-系统审核失败 3-管理员审核通过 4-管理员审核失败
     */
	private Integer auditStatus;
	
    /**
     *审核人
     */
	private String auditUser;
	
    /**
     *审核时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date auditTime;
	
    /**
     *保险审核佣金计算状态 0-待计算 1-计算成功 2-计算失败
     */
	private Integer clearingStatus;
	
    /**
     *结算时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date clearingTime;
	
    /**
     *备注
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBillId(String billId){
		this.billId = billId;
	}
	
	public String getBillId(){
		return billId;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setNotifyType(Integer notifyType){
		this.notifyType = notifyType;
	}
	
	public Integer getNotifyType(){
		return notifyType;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setInsureNum(String insureNum){
		this.insureNum = insureNum;
	}
	
	public String getInsureNum(){
		return insureNum;
	}
	
	public void setState(Boolean state){
		this.state = state;
	}
	
	public Boolean isState(){
		return state;
	}
	
	public void setPayTime(Date payTime){
		this.payTime = payTime;
	}
	
	public Date getPayTime(){
		return payTime;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
	public BigDecimal getPrice(){
		return price;
	}
	
	public void setOnlinePaymentId(String onlinePaymentId){
		this.onlinePaymentId = onlinePaymentId;
	}
	
	public String getOnlinePaymentId(){
		return onlinePaymentId;
	}
	
	public void setNewInsureNum(String newInsureNum){
		this.newInsureNum = newInsureNum;
	}
	
	public String getNewInsureNum(){
		return newInsureNum;
	}
	
	public void setCancelInsurants(String cancelInsurants){
		this.cancelInsurants = cancelInsurants;
	}
	
	public String getCancelInsurants(){
		return cancelInsurants;
	}
	
	public void setCancelMsg(String cancelMsg){
		this.cancelMsg = cancelMsg;
	}
	
	public String getCancelMsg(){
		return cancelMsg;
	}
	
	public void setFailMsg(String failMsg){
		this.failMsg = failMsg;
	}
	
	public String getFailMsg(){
		return failMsg;
	}
	
	public void setCreatTime(Date creatTime){
		this.creatTime = creatTime;
	}
	
	public Date getCreatTime(){
		return creatTime;
	}
	
	public void setUpTime(Date upTime){
		this.upTime = upTime;
	}
	
	public Date getUpTime(){
		return upTime;
	}
	
	public void setProductFeeRate(BigDecimal productFeeRate){
		this.productFeeRate = productFeeRate;
	}
	
	public BigDecimal getProductFeeRate(){
		return productFeeRate;
	}
	
	public void setAuditStatus(Integer auditStatus){
		this.auditStatus = auditStatus;
	}
	
	public Integer getAuditStatus(){
		return auditStatus;
	}
	
	public void setAuditUser(String auditUser){
		this.auditUser = auditUser;
	}
	
	public String getAuditUser(){
		return auditUser;
	}
	
	public void setAuditTime(Date auditTime){
		this.auditTime = auditTime;
	}
	
	public Date getAuditTime(){
		return auditTime;
	}
	
	public void setClearingStatus(Integer clearingStatus){
		this.clearingStatus = clearingStatus;
	}
	
	public Integer getClearingStatus(){
		return clearingStatus;
	}
	
	public void setClearingTime(Date clearingTime){
		this.clearingTime = clearingTime;
	}
	
	public Date getClearingTime(){
		return clearingTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

