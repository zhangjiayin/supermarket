package com.linkwee.tc.fee.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月11日 15:59:16
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class TCFeeSummary implements Serializable {
	
	private static final long serialVersionUID = -5740984997447688508L;
	
	
	/**
    *
    */
	private Integer id;
	
   /**
    *结算编号(年+月)
    */
	private String bizId;
	
   /**
    *发放类型:0=已发放|1=未发放
    */
	private Integer type;
	
   /**
    *年
    */
	private String year;
	
   /**
    *月
    */
	private String moth;
	
   /**
    *季度
    */
	private String quarter;
	
   /**
    *总人数
    */
	private Integer totalNumber;
	
   /**
    *总佣金
    */
	private BigDecimal totalProfit;
	
   /**
    *获取佣金人数
    */
	private Integer feeProfitNumber;
	
   /**
    *佣金
    */
	private BigDecimal feeProfit;
	
   /**
    *获取推荐奖励人数
    */
	private Integer recommendProfitNumer;
	
   /**
    *推荐津贴
    */
	private BigDecimal recommendProfit;
	
   /**
    *获取直接管理津贴人数
    */
	private Integer childManagementProfitNumber;
	
   /**
    *直接管理津贴
    */
	private BigDecimal childManagementProfit;
	
   /**
    *获取团队管理津贴人数
    */
	private Integer teamManagementProfitNumer;
	
   /**
    *团队管理津贴
    */
	private BigDecimal teamManagementProfit;
	
    /**
     *基础加拥(平台加佣)
     */
	private BigDecimal feeProfitAdd;
	
    /**
     *基础加拥人数(平台加佣)
     */
	private Integer feeProfitAddNumber;
	
    /**
     *获取推荐加拥人数(平台加佣)
     */
	private Integer recommendProfitAddNumer;
	
    /**
     *推荐加拥(平台加佣)
     */
	private BigDecimal recommendProfitAdd;
	
	/**
	 *基础加拥人数(个人加佣)
	 */
	private Integer personFeeAddNumber;
	
    /**
     *基础加拥(个人加佣)
     */
	private BigDecimal personFeeAdd;
	
	
    /**
     *保险获取佣金人数
     */
	private Integer insuranceFeeProfitNumber;
	
    /**
     *保险佣金
     */
	private BigDecimal insuranceFeeProfit;
	
    /**
     *保险获取推荐奖励人数
     */
	private Integer insuranceRecommendProfitNumer;
	
    /**
     *保险推荐津贴
     */
	private BigDecimal insuranceRecommendProfit;
	
    /**
     *保险获取直接管理津贴人数
     */
	private Integer insuranceChildManagementProfitNumber;
	
    /**
     *保险直接管理津贴
     */
	private BigDecimal insuranceChildManagementProfit;
	
    /**
     *保险获取团队管理津贴人数
     */
	private Integer insuranceTeamManagementProfitNumer;
	
    /**
     *保险团队管理津贴
     */
	private BigDecimal insuranceTeamManagementProfit;
	
   /**
    *月均佣金
    */
	private BigDecimal avgFeeProfit;
	
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
     *发放操作人
     */
	private String sendOperator;
	
	
	public TCFeeSummary() {}
	
	public TCFeeSummary(String bizId, Integer type, Date updateTime) {
		super();
		this.bizId = bizId;
		this.type = type;
		this.updateTime = updateTime;
	}

	public TCFeeSummary(String bizId,String year,
			String moth,Integer totalNumber,
			BigDecimal totalProfit, Integer feeProfitNumber,
			BigDecimal feeProfit, Integer recommendProfitNumer,
			BigDecimal recommendProfit, Integer childManagementProfitNumber,
			BigDecimal childManagementProfit,
			Integer teamManagementProfitNumer, BigDecimal teamManagementProfit,
			BigDecimal feeProfitAdd, Integer feeProfitAddNumber,
			Integer recommendProfitAddNumer, BigDecimal recommendProfitAdd,
			Integer personFeeAddNumber, BigDecimal personFeeAdd,
			Integer insuranceFeeProfitNumber, BigDecimal insuranceFeeProfit,
			Integer insuranceRecommendProfitNumer,
			BigDecimal insuranceRecommendProfit,
			Integer insuranceChildManagementProfitNumber,
			BigDecimal insuranceChildManagementProfit,
			Integer insuranceTeamManagementProfitNumer,
			BigDecimal insuranceTeamManagementProfit, BigDecimal avgFeeProfit,
			Date createTime, Date updateTime, String operator) {
		super();
		this.bizId = bizId;
		this.year = year;
		this.moth = moth;
		this.totalNumber = totalNumber;
		this.totalProfit = totalProfit;
		this.feeProfitNumber = feeProfitNumber;
		this.feeProfit = feeProfit;
		this.recommendProfitNumer = recommendProfitNumer;
		this.recommendProfit = recommendProfit;
		this.childManagementProfitNumber = childManagementProfitNumber;
		this.childManagementProfit = childManagementProfit;
		this.teamManagementProfitNumer = teamManagementProfitNumer;
		this.teamManagementProfit = teamManagementProfit;
		this.feeProfitAdd = feeProfitAdd;
		this.feeProfitAddNumber = feeProfitAddNumber;
		this.recommendProfitAddNumer = recommendProfitAddNumer;
		this.recommendProfitAdd = recommendProfitAdd;
		this.personFeeAddNumber = personFeeAddNumber;
		this.personFeeAdd = personFeeAdd;
		this.insuranceFeeProfitNumber = insuranceFeeProfitNumber;
		this.insuranceFeeProfit = insuranceFeeProfit;
		this.insuranceRecommendProfitNumer = insuranceRecommendProfitNumer;
		this.insuranceRecommendProfit = insuranceRecommendProfit;
		this.insuranceChildManagementProfitNumber = insuranceChildManagementProfitNumber;
		this.insuranceChildManagementProfit = insuranceChildManagementProfit;
		this.insuranceTeamManagementProfitNumer = insuranceTeamManagementProfitNumer;
		this.insuranceTeamManagementProfit = insuranceTeamManagementProfit;
		this.avgFeeProfit = avgFeeProfit;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.operator = operator;
	}

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
	
	
	
	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public void setYear(String year){
		this.year = year;
	}
	
	public String getYear(){
		return year;
	}
	
	public void setMoth(String moth){
		this.moth = moth;
	}
	
	public String getMoth(){
		return moth;
	}
	
	public void setQuarter(String quarter){
		this.quarter = quarter;
	}
	
	public String getQuarter(){
		return quarter;
	}
	
	public void setTotalNumber(Integer totalNumber){
		this.totalNumber = totalNumber;
	}
	
	public Integer getTotalNumber(){
		return totalNumber;
	}
	
	public void setTotalProfit(BigDecimal totalProfit){
		this.totalProfit = totalProfit;
	}
	
	public BigDecimal getTotalProfit(){
		return totalProfit;
	}
	
	public void setFeeProfitNumber(Integer feeProfitNumber){
		this.feeProfitNumber = feeProfitNumber;
	}
	
	public Integer getFeeProfitNumber(){
		return feeProfitNumber;
	}
	
	public void setFeeProfit(BigDecimal feeProfit){
		this.feeProfit = feeProfit;
	}
	
	public BigDecimal getFeeProfit(){
		return feeProfit;
	}
	
	public void setRecommendProfitNumer(Integer recommendProfitNumer){
		this.recommendProfitNumer = recommendProfitNumer;
	}
	
	public Integer getRecommendProfitNumer(){
		return recommendProfitNumer;
	}
	
	public void setRecommendProfit(BigDecimal recommendProfit){
		this.recommendProfit = recommendProfit;
	}
	
	public BigDecimal getRecommendProfit(){
		return recommendProfit;
	}
	
	public void setChildManagementProfitNumber(Integer childManagementProfitNumber){
		this.childManagementProfitNumber = childManagementProfitNumber;
	}
	
	public Integer getChildManagementProfitNumber(){
		return childManagementProfitNumber;
	}
	
	public void setChildManagementProfit(BigDecimal childManagementProfit){
		this.childManagementProfit = childManagementProfit;
	}
	
	public BigDecimal getChildManagementProfit(){
		return childManagementProfit;
	}
	
	public void setTeamManagementProfitNumer(Integer teamManagementProfitNumer){
		this.teamManagementProfitNumer = teamManagementProfitNumer;
	}
	
	public Integer getTeamManagementProfitNumer(){
		return teamManagementProfitNumer;
	}
	
	public void setTeamManagementProfit(BigDecimal teamManagementProfit){
		this.teamManagementProfit = teamManagementProfit;
	}
	
	public BigDecimal getTeamManagementProfit(){
		return teamManagementProfit;
	}
	
	public void setAvgFeeProfit(BigDecimal avgFeeProfit){
		this.avgFeeProfit = avgFeeProfit;
	}
	
	public BigDecimal getAvgFeeProfit(){
		return avgFeeProfit;
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
	
	
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}



	public BigDecimal getFeeProfitAdd() {
		return feeProfitAdd;
	}



	public void setFeeProfitAdd(BigDecimal feeProfitAdd) {
		this.feeProfitAdd = feeProfitAdd;
	}



	public Integer getFeeProfitAddNumber() {
		return feeProfitAddNumber;
	}



	public void setFeeProfitAddNumber(Integer feeProfitAddNumber) {
		this.feeProfitAddNumber = feeProfitAddNumber;
	}



	public Integer getRecommendProfitAddNumer() {
		return recommendProfitAddNumer;
	}



	public void setRecommendProfitAddNumer(Integer recommendProfitAddNumer) {
		this.recommendProfitAddNumer = recommendProfitAddNumer;
	}



	public BigDecimal getRecommendProfitAdd() {
		return recommendProfitAdd;
	}



	public void setRecommendProfitAdd(BigDecimal recommendProfitAdd) {
		this.recommendProfitAdd = recommendProfitAdd;
	}



	public BigDecimal getPersonFeeAdd() {
		return personFeeAdd;
	}

	public void setPersonFeeAdd(BigDecimal personFeeAdd) {
		this.personFeeAdd = personFeeAdd;
	}

	public Integer getPersonFeeAddNumber() {
		return personFeeAddNumber;
	}

	public void setPersonFeeAddNumber(Integer personFeeAddNumber) {
		this.personFeeAddNumber = personFeeAddNumber;
	}

	public Integer getInsuranceFeeProfitNumber() {
		return insuranceFeeProfitNumber;
	}



	public void setInsuranceFeeProfitNumber(Integer insuranceFeeProfitNumber) {
		this.insuranceFeeProfitNumber = insuranceFeeProfitNumber;
	}



	public BigDecimal getInsuranceFeeProfit() {
		return insuranceFeeProfit;
	}



	public void setInsuranceFeeProfit(BigDecimal insuranceFeeProfit) {
		this.insuranceFeeProfit = insuranceFeeProfit;
	}



	public Integer getInsuranceRecommendProfitNumer() {
		return insuranceRecommendProfitNumer;
	}



	public void setInsuranceRecommendProfitNumer(
			Integer insuranceRecommendProfitNumer) {
		this.insuranceRecommendProfitNumer = insuranceRecommendProfitNumer;
	}



	public BigDecimal getInsuranceRecommendProfit() {
		return insuranceRecommendProfit;
	}



	public void setInsuranceRecommendProfit(BigDecimal insuranceRecommendProfit) {
		this.insuranceRecommendProfit = insuranceRecommendProfit;
	}



	public Integer getInsuranceChildManagementProfitNumber() {
		return insuranceChildManagementProfitNumber;
	}



	public void setInsuranceChildManagementProfitNumber(
			Integer insuranceChildManagementProfitNumber) {
		this.insuranceChildManagementProfitNumber = insuranceChildManagementProfitNumber;
	}



	public BigDecimal getInsuranceChildManagementProfit() {
		return insuranceChildManagementProfit;
	}



	public void setInsuranceChildManagementProfit(
			BigDecimal insuranceChildManagementProfit) {
		this.insuranceChildManagementProfit = insuranceChildManagementProfit;
	}



	public Integer getInsuranceTeamManagementProfitNumer() {
		return insuranceTeamManagementProfitNumer;
	}



	public void setInsuranceTeamManagementProfitNumer(
			Integer insuranceTeamManagementProfitNumer) {
		this.insuranceTeamManagementProfitNumer = insuranceTeamManagementProfitNumer;
	}



	public BigDecimal getInsuranceTeamManagementProfit() {
		return insuranceTeamManagementProfit;
	}



	public void setInsuranceTeamManagementProfit(
			BigDecimal insuranceTeamManagementProfit) {
		this.insuranceTeamManagementProfit = insuranceTeamManagementProfit;
	}



	public String getSendOperator() {
		return sendOperator;
	}



	public void setSendOperator(String sendOperator) {
		this.sendOperator = sendOperator;
	}
	
}

