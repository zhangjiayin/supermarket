package com.linkwee.web.model.cim;

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
 * @创建人： chenjl
 * 
 * @创建时间：2017年02月28日 10:46:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimLeaderFee implements Serializable {
	
	private static final long serialVersionUID = -7074211597694866207L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *投资记录id(系统产生的唯一标识)
     */
	private String investId;
	
    /**
     *平台返回投资流水号
     */
	private String investRecordNo;
	
    /**
     *所属月份
     */
	private String month;
	
    /**
     *是否发放0=未发放1=已发放
     */
	private Integer isPay;
	
    /**
     *投资用户id
     */
	private String userId;
	
    /**
     *投资用户直接理财师
     */
	private String cfplanner;
	
    /**
     *leader奖励的获得者
     */
	private String ownerId;
	
    /**
     *购买本金
     */
	private BigDecimal investAmt = new BigDecimal(0);
	
    /**
     *leader奖励
     */
	private BigDecimal leaderProfit = new BigDecimal(0);
	
    /**
     *推过来的时间业务日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date bizTime;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *外部产品编号
     */
	private String thirdProductId;
	
    /**
     *产品最小期限天数
     */
	private Integer productDays;
	
    /**
     *购买日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startTime;
	
    /**
     *回款日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date endTime;
	
    /**
     *机构编码
     */
	private String platfrom;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
	/**
	 * 头像
	 */
	private String headImage;
	
	/**
	 * 名字
	 */
	private String userName;
	
	/**
	 * 贡献奖励
	 */
	private BigDecimal contrProfit;
	
	/**
	 * 销售额
	 */
	private BigDecimal saleAmount;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * owner的直属下级
	 */
	private String sonOwner;
	

	public String getSonOwner() {
		return sonOwner;
	}

	public void setSonOwner(String sonOwner) {
		this.sonOwner = sonOwner;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getContrProfit() {
		return contrProfit;
	}

	public void setContrProfit(BigDecimal contrProfit) {
		this.contrProfit = contrProfit;
	}

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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
	
	public void setInvestRecordNo(String investRecordNo){
		this.investRecordNo = investRecordNo;
	}
	
	public String getInvestRecordNo(){
		return investRecordNo;
	}
	
	public void setMonth(String month){
		this.month = month;
	}
	
	public String getMonth(){
		return month;
	}
	
	public void setIsPay(Integer isPay){
		this.isPay = isPay;
	}
	
	public Integer getIsPay(){
		return isPay;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setCfplanner(String cfplanner){
		this.cfplanner = cfplanner;
	}
	
	public String getCfplanner(){
		return cfplanner;
	}
	
	public void setOwnerId(String ownerId){
		this.ownerId = ownerId;
	}
	
	public String getOwnerId(){
		return ownerId;
	}
	
	public void setInvestAmt(BigDecimal investAmt){
		this.investAmt = investAmt;
	}
	
	public BigDecimal getInvestAmt(){
		return investAmt;
	}
	
	public void setLeaderProfit(BigDecimal leaderProfit){
		this.leaderProfit = leaderProfit;
	}
	
	public BigDecimal getLeaderProfit(){
		return leaderProfit;
	}
	
	public void setBizTime(Date bizTime){
		this.bizTime = bizTime;
	}
	
	public Date getBizTime(){
		return bizTime;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setThirdProductId(String thirdProductId){
		this.thirdProductId = thirdProductId;
	}
	
	public String getThirdProductId(){
		return thirdProductId;
	}
	
	public void setProductDays(Integer productDays){
		this.productDays = productDays;
	}
	
	public Integer getProductDays(){
		return productDays;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setPlatfrom(String platfrom){
		this.platfrom = platfrom;
	}
	
	public String getPlatfrom(){
		return platfrom;
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
	
}

