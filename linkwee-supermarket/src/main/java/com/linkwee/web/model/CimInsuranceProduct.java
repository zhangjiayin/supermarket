package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年01月11日 18:19:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceProduct implements Serializable {
	
	private static final long serialVersionUID = 3827738308301535773L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *保险机构编码
     */
	private String orgNumber;
	
    /**
     *方案代码
     */
	private String caseCode;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *产品详情、特色
     */
	private String fullDescription;
	
    /**
     *一级分类   1-意外险  2-旅游险 3-家财险  4-医疗险 5-重疾险 6-年金险 7-寿险 8-少儿险 9-老年险
     */
	private String fristCategory;
	
    /**
     *二级分类
     */
	private String secondCategory;
	
    /**
     *保险公司名称
     */
	private String companyName;
	
    /**
     *产品状态 0：待审 1：上架 2：下架 3：测试 4：停售
     */
	private Integer state;
	
    /**
     *参考价格（单位：分）
     */
	private Integer price;
	
    /**
     *保险背景图片
     */
	private String productBakimg;
	
    /**
     *排序
     */
	private Integer orderSort;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date creatTime;
	
    /**
     *上线时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date upTime;
	
    /**
     *佣金率
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal feeRatio;
	
    /**
     *犹豫期(单位:天)
     */
	private Integer hesitateDate;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setCaseCode(String caseCode){
		this.caseCode = caseCode;
	}
	
	public String getCaseCode(){
		return caseCode;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setFullDescription(String fullDescription){
		this.fullDescription = fullDescription;
	}
	
	public String getFullDescription(){
		return fullDescription;
	}
	
	public void setFristCategory(String fristCategory){
		this.fristCategory = fristCategory;
	}
	
	public String getFristCategory(){
		return fristCategory;
	}
	
	public void setSecondCategory(String secondCategory){
		this.secondCategory = secondCategory;
	}
	
	public String getSecondCategory(){
		return secondCategory;
	}
	
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	
	public String getCompanyName(){
		return companyName;
	}
	
	public void setState(Integer state){
		this.state = state;
	}
	
	public Integer getState(){
		return state;
	}
	
	public void setPrice(Integer price){
		this.price = price;
	}
	
	public Integer getPrice(){
		return price;
	}
	
	public void setProductBakimg(String productBakimg){
		this.productBakimg = productBakimg;
	}
	
	public String getProductBakimg(){
		return productBakimg;
	}
	
	public void setOrderSort(Integer orderSort){
		this.orderSort = orderSort;
	}
	
	public Integer getOrderSort(){
		return orderSort;
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
	
	public void setFeeRatio(BigDecimal feeRatio){
		this.feeRatio = feeRatio;
	}
	
	public BigDecimal getFeeRatio(){
		return feeRatio;
	}
	
	public void setHesitateDate(Integer hesitateDate){
		this.hesitateDate = hesitateDate;
	}
	
	public Integer getHesitateDate(){
		return hesitateDate;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

