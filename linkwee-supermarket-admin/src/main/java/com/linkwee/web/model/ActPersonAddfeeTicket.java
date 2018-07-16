package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.Boolean;
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
 * @创建时间：2018年04月12日 18:32:58
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActPersonAddfeeTicket implements Serializable {
	
	private static final long serialVersionUID = 6380436546216409562L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *加拥券编号
     */
	private String ticketId;
	
    /**
     *加拥券名称
     */
	private String name;
	
    /**
     *加拥来源
     */
	private String source;
	
    /**
     *加拥比率
     */
	private BigDecimal rate;
	
    /**
     *加拥券类型 1=个人加拥券
     */
	private Integer type;
	
    /**
     *平台限制 1=限制|0=不限制
     */
	private Boolean platformLimit;
	
    /**
     *平台编号 platform_limit=1时有效
     */
	private String platformLimitOrgNumber;
	
    /**
     *平台名称 platform_limit=1时有效
     */
	private String platformLimitOrgName;
	
    /**
     *产品限制 1000=不限|1001=产品编号|1002=等于产品期限|1003=大于等于产品期限|1004=产品类型
     */
	private Integer productLimit;
	
    /**
     *产品编号 product_limit=1001时有效
     */
	private String productLimitId;
	
    /**
     *产品名称 product_limit=1001时有效
     */
	private String productLimitName;
	
    /**
     *产品期限 product_limit=1002时有效
     */
	private Integer productLimitDeadline;
	
    /**
     *产品类型 product_limit=1004时有效
     */
	private Integer productLimitType;
	
    /**
     *用户投资限制 0=不限|1=用户首投|2=平台首投
     */
	private Integer investLimit;
	
    /**
     *金额限制 0=不限|1=大于|2=大于等于
     */
	private Integer amountLimit;
	
    /**
     *金额
     */
	private BigDecimal amount;
	
    /**
     *加佣天数限制 0=不限|1=限制
     */
	private Integer addFeeLimit;
	
    /**
     *加佣天数
     */
	private Integer addFeeLimitDay;
	
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
     *操作人
     */
	private String operator;
	
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
	
	public void setTicketId(String ticketId){
		this.ticketId = ticketId;
	}
	
	public String getTicketId(){
		return ticketId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSource(String source){
		this.source = source;
	}
	
	public String getSource(){
		return source;
	}
	
	public void setRate(BigDecimal rate){
		this.rate = rate;
	}
	
	public BigDecimal getRate(){
		return rate;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setPlatformLimit(Boolean platformLimit){
		this.platformLimit = platformLimit;
	}
	
	public Boolean isPlatformLimit(){
		return platformLimit;
	}
	
	public void setPlatformLimitOrgNumber(String platformLimitOrgNumber){
		this.platformLimitOrgNumber = platformLimitOrgNumber;
	}
	
	public String getPlatformLimitOrgNumber(){
		return platformLimitOrgNumber;
	}
	
	public void setPlatformLimitOrgName(String platformLimitOrgName){
		this.platformLimitOrgName = platformLimitOrgName;
	}
	
	public String getPlatformLimitOrgName(){
		return platformLimitOrgName;
	}
	
	public void setProductLimit(Integer productLimit){
		this.productLimit = productLimit;
	}
	
	public Integer getProductLimit(){
		return productLimit;
	}
	
	public void setProductLimitId(String productLimitId){
		this.productLimitId = productLimitId;
	}
	
	public String getProductLimitId(){
		return productLimitId;
	}
	
	public void setProductLimitName(String productLimitName){
		this.productLimitName = productLimitName;
	}
	
	public String getProductLimitName(){
		return productLimitName;
	}
	
	public void setProductLimitDeadline(Integer productLimitDeadline){
		this.productLimitDeadline = productLimitDeadline;
	}
	
	public Integer getProductLimitDeadline(){
		return productLimitDeadline;
	}
	
	public void setProductLimitType(Integer productLimitType){
		this.productLimitType = productLimitType;
	}
	
	public Integer getProductLimitType(){
		return productLimitType;
	}
	
	public void setInvestLimit(Integer investLimit){
		this.investLimit = investLimit;
	}
	
	public Integer getInvestLimit(){
		return investLimit;
	}
	
	public void setAmountLimit(Integer amountLimit){
		this.amountLimit = amountLimit;
	}
	
	public Integer getAmountLimit(){
		return amountLimit;
	}
	
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	
	public BigDecimal getAmount(){
		return amount;
	}
	
	public void setAddFeeLimit(Integer addFeeLimit){
		this.addFeeLimit = addFeeLimit;
	}
	
	public Integer getAddFeeLimit(){
		return addFeeLimit;
	}
	
	public void setAddFeeLimitDay(Integer addFeeLimitDay){
		this.addFeeLimitDay = addFeeLimitDay;
	}
	
	public Integer getAddFeeLimitDay(){
		return addFeeLimitDay;
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
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
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

