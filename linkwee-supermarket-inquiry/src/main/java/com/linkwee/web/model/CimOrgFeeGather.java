package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Byte;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 17:06:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeGather implements Serializable {
	
	private static final long serialVersionUID = -3431755315219001099L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *投资订单编号
     */
	private String investId;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date investTime;
	
    /**
     *产品所属机构编号
     */
	private String productOrgId;
	
    /**
     *产品编号
     */
	private String productId;
	
    /**
     *购买产品金额
     */
	private BigDecimal productAmount;
	
    /**
     *购买产品期限（天）
     */
	private BigDecimal productDeadline;
	
    /**
     *年化金额
     */
	private BigDecimal yearpurAmount;
	
    /**
     *状态（1为首投，2为否）
     */
	private Integer isFirstInvest;
	
    /**
     *单用户首投固定费用
     */
	private BigDecimal fixedAmount;
	
    /**
     *单用户首投浮动费用
     */
	private BigDecimal floatFixedAmount;
	
    /**
     *首投金额收费比例
     */
	private BigDecimal proportionAmount;
	
    /**
     *年化投资收费比例
     */
	private BigDecimal yearProportionAmount;
	
    /**
     *销售费用总额
     */
	private BigDecimal feeAmount;
	
    /**
     *结算状态：0=未结算|1=结算中|2=结算成功|3=结算失败
     */
	private Byte balanceStatus;
	
    /**
     *结算单号
     */
	private String balanceNumber;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
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
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setInvestTime(Date investTime){
		this.investTime = investTime;
	}
	
	public Date getInvestTime(){
		return investTime;
	}
	
	public void setProductOrgId(String productOrgId){
		this.productOrgId = productOrgId;
	}
	
	public String getProductOrgId(){
		return productOrgId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setProductAmount(BigDecimal productAmount){
		this.productAmount = productAmount;
	}
	
	public BigDecimal getProductAmount(){
		return productAmount;
	}
	
	public void setProductDeadline(BigDecimal productDeadline){
		this.productDeadline = productDeadline;
	}
	
	public BigDecimal getProductDeadline(){
		return productDeadline;
	}
	
	public void setYearpurAmount(BigDecimal yearpurAmount){
		this.yearpurAmount = yearpurAmount;
	}
	
	public BigDecimal getYearpurAmount(){
		return yearpurAmount;
	}
	
	public void setIsFirstInvest(Integer isFirstInvest){
		this.isFirstInvest = isFirstInvest;
	}
	
	public Integer getIsFirstInvest(){
		return isFirstInvest;
	}
	
	public void setFixedAmount(BigDecimal fixedAmount){
		this.fixedAmount = fixedAmount;
	}
	
	public BigDecimal getFixedAmount(){
		return fixedAmount;
	}
	
	public void setFloatFixedAmount(BigDecimal floatFixedAmount){
		this.floatFixedAmount = floatFixedAmount;
	}
	
	public BigDecimal getFloatFixedAmount(){
		return floatFixedAmount;
	}
	
	public void setProportionAmount(BigDecimal proportionAmount){
		this.proportionAmount = proportionAmount;
	}
	
	public BigDecimal getProportionAmount(){
		return proportionAmount;
	}
	
	public void setYearProportionAmount(BigDecimal yearProportionAmount){
		this.yearProportionAmount = yearProportionAmount;
	}
	
	public BigDecimal getYearProportionAmount(){
		return yearProportionAmount;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
	}
	
	public void setBalanceStatus(Byte balanceStatus){
		this.balanceStatus = balanceStatus;
	}
	
	public Byte getBalanceStatus(){
		return balanceStatus;
	}
	
	public void setBalanceNumber(String balanceNumber){
		this.balanceNumber = balanceNumber;
	}
	
	public String getBalanceNumber(){
		return balanceNumber;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

