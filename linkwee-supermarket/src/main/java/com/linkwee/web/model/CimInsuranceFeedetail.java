package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Byte;
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
 * @创建时间：2017年12月05日 14:28:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceFeedetail implements Serializable {
	
	private static final long serialVersionUID = 6057883717227794411L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *佣金计算编号
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
     *获利理财师类型(0=默认|1=上1级|2=上2级|上3级)
     */
	private String profitCfplannerIdLowType;
	
    /**
     *原始理财师上3级理财师编号
     */
	private String originCfplannerParent3Id;
	
    /**
     *原始理财师上2级理财师编号
     */
	private String originCfplannerParent2Id;
	
    /**
     *原始理财师上1级理财师编号
     */
	private String originCfplannerParent1Id;
	
    /**
     *原始理财师编号
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
     *成功描述
     */
	private String succeedRemark;
	
    /**
     *产品佣金费率
     */
	private BigDecimal productFeeRate;
	
    /**
     *计算佣金费率
     */
	private BigDecimal feeRate;
	
    /**
     *佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *佣金类型：1001=佣金|1002=推荐津贴|1005=直接管理津贴|1006=团队管理津贴
     */
	private String feeType;
	
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
	
	public void setProfitCfplannerIdLowType(String profitCfplannerIdLowType){
		this.profitCfplannerIdLowType = profitCfplannerIdLowType;
	}
	
	public String getProfitCfplannerIdLowType(){
		return profitCfplannerIdLowType;
	}
	
	public void setOriginCfplannerParent3Id(String originCfplannerParent3Id){
		this.originCfplannerParent3Id = originCfplannerParent3Id;
	}
	
	public String getOriginCfplannerParent3Id(){
		return originCfplannerParent3Id;
	}
	
	public void setOriginCfplannerParent2Id(String originCfplannerParent2Id){
		this.originCfplannerParent2Id = originCfplannerParent2Id;
	}
	
	public String getOriginCfplannerParent2Id(){
		return originCfplannerParent2Id;
	}
	
	public void setOriginCfplannerParent1Id(String originCfplannerParent1Id){
		this.originCfplannerParent1Id = originCfplannerParent1Id;
	}
	
	public String getOriginCfplannerParent1Id(){
		return originCfplannerParent1Id;
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
	
	public void setSucceedRemark(String succeedRemark){
		this.succeedRemark = succeedRemark;
	}
	
	public String getSucceedRemark(){
		return succeedRemark;
	}
	
	public void setProductFeeRate(BigDecimal productFeeRate){
		this.productFeeRate = productFeeRate;
	}
	
	public BigDecimal getProductFeeRate(){
		return productFeeRate;
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
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

