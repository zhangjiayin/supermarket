package com.linkwee.api.request.cim;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CimProductUnrecordInvestRequest {

  /**
    *理财师编号
    */
   private String cfplannerId;

   /**
    *用户编号
    */
   private String userId;

   /**
    *用户手机
    */
   private String userMobile;

   /**
    *用户姓名
    */
   private String userName;

   /**
    *平台编号
    */
   @NotNull(message="平台编号不能为空")
   private String platfrom;

   /**
    *平台名称
    */
   private String platfromName;

   /**
    *产品期限类型 : 0=老数据|1=天|2=月|3=年
    */
   private Integer productDeadLineType = 1;

   /**
    *产品期限（天数）
    */
   @NotNull(message="产品期限不能为空")
   @Min(value=0, message="产品期限大于等于0")
   private Integer productDeadLineValue;

   /**
    *产品期限
    */
   private String productDeadLine;

   /**
    *佣金率
    */
   private BigDecimal feeRate;

   /**
    *佣金
    */
   private BigDecimal feeAmt;

   /**
    *投资编号
    */
   private String investId;

   /**
    *投资金额
    */
   @NotNull(message="投资金额不能为空")
   @Min(value=0, message="投资金额大于等于0")
   private BigDecimal investAmt;

   /**
    *投资时间
    */
   @NotNull(message="投资时间不能为空")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date investTime;

   /**
    *回款时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date endTime;

   /**
    *投资截图
    */
   @NotNull(message="投资截图不能为空")
   private String investImg;

   /**
    *状态 0=未审核|1=审核通过|2=审核不通过
    */
   private Integer status = 0;

   /**
    *描述
    */
   private String remark;

   /**
    *创建时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date createTime;

   /**
    *状态 0=未晒单|1=已晒单
    */
   @NotNull(message="是否晒单不能为空")
   @Range(min=0,max=1,message="是否晒单必需在0-1之间")
   private Integer shareStatus;

	public String getCfplannerId() {
		return cfplannerId;
	}

	public void setCfplannerId(String cfplannerId) {
		this.cfplannerId = cfplannerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlatfrom() {
		return platfrom;
	}

	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}

	public String getPlatfromName() {
		return platfromName;
	}

	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}

	public Integer getProductDeadLineType() {
		return productDeadLineType;
	}

	public void setProductDeadLineType(Integer productDeadLineType) {
		this.productDeadLineType = productDeadLineType;
	}

	public Integer getProductDeadLineValue() {
		return productDeadLineValue;
	}

	public void setProductDeadLineValue(Integer productDeadLineValue) {
		this.productDeadLineValue = productDeadLineValue;
	}

	public String getProductDeadLine() {
		return productDeadLine;
	}

	public void setProductDeadLine(String productDeadLine) {
		this.productDeadLine = productDeadLine;
	}

	public BigDecimal getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public BigDecimal getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}

	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInvestImg() {
		return investImg;
	}

	public void setInvestImg(String investImg) {
		this.investImg = investImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(Integer shareStatus) {
		this.shareStatus = shareStatus;
	}

}
