package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年12月01日 18:42:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductUpdatePull implements Serializable {
	
	private static final long serialVersionUID = 2165635736289586748L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *产品描述
     */
	private String productDesc;
	
    /**
     *产品类型(1=P2P|2=信托 |3=资管|4=基金|401=公募基金|402=阳光私募|403=股权基金|5=保险|6=众筹|999=其他)
     */
	private Integer productType;
	
    /**
     *还本付息方式(1=一次性到期|101=一次性按日|102=一次性按月|103=一次性按季|2=等额本息|3=等额本金|4=先息后本)
     */
	private Integer repaymentWay;
	
    /**
     *产品销售开始时间
     */
	private String saleStartTime;
	
    /**
     *产品销售结束时间
     */
	private String saleEndTime;
	
    /**
     *是否浮动利率(1=固定利率|2=浮动利率)
     */
	private Integer isFlow;
	
    /**
     *浮动最小利率
     */
	private BigDecimal flowMinRate;
	
    /**
     *浮动最大利率
     */
	private BigDecimal flowMaxRate;
	
    /**
     *加息利率
     */
	private BigDecimal addRate;
	
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
	
    /**
     *产品最小期限天数
     */
	private Integer deadLineMinValue;
	
    /**
     *产品最大期限天数
     */
	private Integer deadLineMaxValue;
	
    /**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
	private Integer isCollect;
	
    /**
     *募集开始时间|格式yyyy-mm-ddhh:mm:ss
     */
	private String collectBeginTime;
	
    /**
     *募集截止时间可以为nul格式yyyy-mm-ddhh:mm:ss
     */
	private String collectEndTime;
	
    /**
     *起息方式(1=购买当日|2=购买次日|3=募集完成当日|4=募集完成次日|5=指定日期)
     */
	private Integer interestWay;
	
    /**
     *产品起息日格式yyyy-mm-dd
     */
	private String validBeginDate;
	
    /**
     *产品到期日格式yyyy-mm-dd
     */
	private String validEndDate;
	
    /**
     *产品单笔购买最小额度
     */
	private BigDecimal buyMinMoney;
	
    /**
     *产品单笔购买最大额度
     */
	private BigDecimal buyMaxMoney;
	
    /**
     *产品总额度
     */
	private BigDecimal buyTotalMoney;
	
    /**
     *是否拥有产品进度(0=有|1=没有)
     */
	private Integer isHaveProgress;
	
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
     */
	private Integer isRedemption;
	
    /**
     *可赎回天数
     */
	private Integer redemptionTime;
	
    /**
     *可转让天数
     */
	private Integer assignmentTime;
	
    /**
     *货币类型(1=rmb|2=港币|3=美元)
     */
	private Integer moneyType;
	
    /**
     *风控类型(1=抵押|2=担保|3=信贷)
     */
	private Integer riskControlType;
	
    /**
     *风险级别(1=一般|2=重要|3=紧急|4=非常紧急)
     */
	private Integer riskLevel;
	
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
	
    /**
     *创建者用户名
     */
	private String creator;
	
    /**
     *创建时间
     */
	private String createTime;
	
    /**
     *最后一次修改者用户名
     */
	private String updater;
	
    /**
     *最后一次修改时间
     */
	private String updateTime;
	
    /**
     *修改或审核操作的说明
     */
	private String remark;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *外部产品ID
     */
	private String thirdProductId;
	
    /**
     *'是否限额产品。1-限额、2-不限额'
     */
	private Integer isQuota;
	
    /**
     *购买递增金额
     */
	private BigDecimal buyIncreaseMoney;
	
    /**
     *产品被投资总额
     */
	private BigDecimal buyedTotalMoney;
	
    /**
     *产品已投资人数
     */
	private Integer buyedTotalPeople;
	
    /**
     *是否是新手标(1=新手标|2=非新手标)
     */
	private Integer ifRookie;
	
    /**
     *产品执行更新状态(0-已执行更新|1-待执行更新)
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
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductDesc(String productDesc){
		this.productDesc = productDesc;
	}
	
	public String getProductDesc(){
		return productDesc;
	}
	
	public void setProductType(Integer productType){
		this.productType = productType;
	}
	
	public Integer getProductType(){
		return productType;
	}
	
	public void setRepaymentWay(Integer repaymentWay){
		this.repaymentWay = repaymentWay;
	}
	
	public Integer getRepaymentWay(){
		return repaymentWay;
	}
	
	public void setSaleStartTime(String saleStartTime){
		this.saleStartTime = saleStartTime;
	}
	
	public String getSaleStartTime(){
		return saleStartTime;
	}
	
	public void setSaleEndTime(String saleEndTime){
		this.saleEndTime = saleEndTime;
	}
	
	public String getSaleEndTime(){
		return saleEndTime;
	}
	
	public void setIsFlow(Integer isFlow){
		this.isFlow = isFlow;
	}
	
	public Integer getIsFlow(){
		return isFlow;
	}
	
	public void setFlowMinRate(BigDecimal flowMinRate){
		this.flowMinRate = flowMinRate;
	}
	
	public BigDecimal getFlowMinRate(){
		return flowMinRate;
	}
	
	public void setFlowMaxRate(BigDecimal flowMaxRate){
		this.flowMaxRate = flowMaxRate;
	}
	
	public BigDecimal getFlowMaxRate(){
		return flowMaxRate;
	}
	
	public void setAddRate(BigDecimal addRate){
		this.addRate = addRate;
	}
	
	public BigDecimal getAddRate(){
		return addRate;
	}
	
	public void setIsFixedDeadline(Integer isFixedDeadline){
		this.isFixedDeadline = isFixedDeadline;
	}
	
	public Integer getIsFixedDeadline(){
		return isFixedDeadline;
	}
	
	public void setDeadLineMinValue(Integer deadLineMinValue){
		this.deadLineMinValue = deadLineMinValue;
	}
	
	public Integer getDeadLineMinValue(){
		return deadLineMinValue;
	}
	
	public void setDeadLineMaxValue(Integer deadLineMaxValue){
		this.deadLineMaxValue = deadLineMaxValue;
	}
	
	public Integer getDeadLineMaxValue(){
		return deadLineMaxValue;
	}
	
	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined){
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}
	
	public String getDeadLineMinSelfDefined(){
		return deadLineMinSelfDefined;
	}
	
	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined){
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}
	
	public String getDeadLineMaxSelfDefined(){
		return deadLineMaxSelfDefined;
	}
	
	public void setIsCollect(Integer isCollect){
		this.isCollect = isCollect;
	}
	
	public Integer getIsCollect(){
		return isCollect;
	}
	
	public void setCollectBeginTime(String collectBeginTime){
		this.collectBeginTime = collectBeginTime;
	}
	
	public String getCollectBeginTime(){
		return collectBeginTime;
	}
	
	public void setCollectEndTime(String collectEndTime){
		this.collectEndTime = collectEndTime;
	}
	
	public String getCollectEndTime(){
		return collectEndTime;
	}
	
	public void setInterestWay(Integer interestWay){
		this.interestWay = interestWay;
	}
	
	public Integer getInterestWay(){
		return interestWay;
	}
	
	public void setValidBeginDate(String validBeginDate){
		this.validBeginDate = validBeginDate;
	}
	
	public String getValidBeginDate(){
		return validBeginDate;
	}
	
	public void setValidEndDate(String validEndDate){
		this.validEndDate = validEndDate;
	}
	
	public String getValidEndDate(){
		return validEndDate;
	}
	
	public void setBuyMinMoney(BigDecimal buyMinMoney){
		this.buyMinMoney = buyMinMoney;
	}
	
	public BigDecimal getBuyMinMoney(){
		return buyMinMoney;
	}
	
	public void setBuyMaxMoney(BigDecimal buyMaxMoney){
		this.buyMaxMoney = buyMaxMoney;
	}
	
	public BigDecimal getBuyMaxMoney(){
		return buyMaxMoney;
	}
	
	public void setBuyTotalMoney(BigDecimal buyTotalMoney){
		this.buyTotalMoney = buyTotalMoney;
	}
	
	public BigDecimal getBuyTotalMoney(){
		return buyTotalMoney;
	}
	
	public void setIsHaveProgress(Integer isHaveProgress){
		this.isHaveProgress = isHaveProgress;
	}
	
	public Integer getIsHaveProgress(){
		return isHaveProgress;
	}
	
	public void setIsRedemption(Integer isRedemption){
		this.isRedemption = isRedemption;
	}
	
	public Integer getIsRedemption(){
		return isRedemption;
	}
	
	public void setRedemptionTime(Integer redemptionTime){
		this.redemptionTime = redemptionTime;
	}
	
	public Integer getRedemptionTime(){
		return redemptionTime;
	}
	
	public void setAssignmentTime(Integer assignmentTime){
		this.assignmentTime = assignmentTime;
	}
	
	public Integer getAssignmentTime(){
		return assignmentTime;
	}
	
	public void setMoneyType(Integer moneyType){
		this.moneyType = moneyType;
	}
	
	public Integer getMoneyType(){
		return moneyType;
	}
	
	public void setRiskControlType(Integer riskControlType){
		this.riskControlType = riskControlType;
	}
	
	public Integer getRiskControlType(){
		return riskControlType;
	}
	
	public void setRiskLevel(Integer riskLevel){
		this.riskLevel = riskLevel;
	}
	
	public Integer getRiskLevel(){
		return riskLevel;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	
	public String getCreateTime(){
		return createTime;
	}
	
	public void setUpdater(String updater){
		this.updater = updater;
	}
	
	public String getUpdater(){
		return updater;
	}
	
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}
	
	public String getUpdateTime(){
		return updateTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setThirdProductId(String thirdProductId){
		this.thirdProductId = thirdProductId;
	}
	
	public String getThirdProductId(){
		return thirdProductId;
	}
	
	public void setIsQuota(Integer isQuota){
		this.isQuota = isQuota;
	}
	
	public Integer getIsQuota(){
		return isQuota;
	}
	
	public void setBuyIncreaseMoney(BigDecimal buyIncreaseMoney){
		this.buyIncreaseMoney = buyIncreaseMoney;
	}
	
	public BigDecimal getBuyIncreaseMoney(){
		return buyIncreaseMoney;
	}
	
	public void setBuyedTotalMoney(BigDecimal buyedTotalMoney){
		this.buyedTotalMoney = buyedTotalMoney;
	}
	
	public BigDecimal getBuyedTotalMoney(){
		return buyedTotalMoney;
	}
	
	public void setBuyedTotalPeople(Integer buyedTotalPeople){
		this.buyedTotalPeople = buyedTotalPeople;
	}
	
	public Integer getBuyedTotalPeople(){
		return buyedTotalPeople;
	}
	
	public void setIfRookie(Integer ifRookie){
		this.ifRookie = ifRookie;
	}
	
	public Integer getIfRookie(){
		return ifRookie;
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

