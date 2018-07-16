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
 * @创建时间：2017年10月19日 16:14:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimFee implements Serializable {
	
	private static final long serialVersionUID = -3021339809452479814L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *佣金编号
     */
	private String bizId;
	
    /**
     *投资订单编号
     */
	private String billId;
	
    /**
     *投资用户编号
     */
	private String investorId;
	
    /**
     *获利理财师编号
     */
	private String profitCfplannerId;
	
    /**
     *原始理财师
     */
	private String originCfplannerId;
	
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
     *年化金额
     */
	private BigDecimal yearpurAmount;
	
    /**
     *描述
     */
	private String remark;
	
    /**
     *佣金费率
     */
	private BigDecimal feeRate;
	
    /**
     *佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *佣金类型：1001=佣金|1002=推荐津贴|1005=直接管理津贴|1006=团队管理津贴|1011=基础加拥|1012=推荐加拥
     */
	private String feeType;
	
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
     *下级理财师userID
     */
	private String lowUserId;
	
    /**
     *下级类型(0=默认|1=下级2=下下级)
     */
	private String lowType;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBizId(String bizId){
		this.bizId = bizId;
	}
	
	public String getBizId(){
		return bizId;
	}
	
	public void setBillId(String billId){
		this.billId = billId;
	}
	
	public String getBillId(){
		return billId;
	}
	
	public void setInvestorId(String investorId){
		this.investorId = investorId;
	}
	
	public String getInvestorId(){
		return investorId;
	}
	
	public void setProfitCfplannerId(String profitCfplannerId){
		this.profitCfplannerId = profitCfplannerId;
	}
	
	public String getProfitCfplannerId(){
		return profitCfplannerId;
	}
	
	public void setOriginCfplannerId(String originCfplannerId){
		this.originCfplannerId = originCfplannerId;
	}
	
	public String getOriginCfplannerId(){
		return originCfplannerId;
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
	
	public void setYearpurAmount(BigDecimal yearpurAmount){
		this.yearpurAmount = yearpurAmount;
	}
	
	public BigDecimal getYearpurAmount(){
		return yearpurAmount;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setFeeRate(BigDecimal feeRate){
		this.feeRate = feeRate;
	}
	
	public BigDecimal getFeeRate(){
		return feeRate;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
	}
	
	public void setFeeType(String feeType){
		this.feeType = feeType;
	}
	
	public String getFeeType(){
		return feeType;
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
	
	public void setLowUserId(String lowUserId){
		this.lowUserId = lowUserId;
	}
	
	public String getLowUserId(){
		return lowUserId;
	}
	
	public void setLowType(String lowType){
		this.lowType = lowType;
	}
	
	public String getLowType(){
		return lowType;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

