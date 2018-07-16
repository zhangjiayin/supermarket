package com.linkwee.web.request.act;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class AddFeeTicketRequest implements Serializable {

    @NotNull(message="加拥券类型不能为空")
    @Range(min=1,max=1,message="加拥券类型必须为1")
    private Integer type;//加拥券类型 1=个人加拥券
    @NotBlank(message="加拥券名称不能为空")
    private String name;//红包名称
    @NotNull(message="加拥比率不能为空")
    @Max(value=100,message="加拥比率最大为100")
    private BigDecimal rate;//加拥比率
    @NotBlank(message="加拥券描述不能为空")
    private String remark;//加拥券描述

    /**
     * use rule
     */
    @NotNull(message="平台限制不能为空")
    @Range(min=0,max=1,message="平台限制值必须为0~1之间")
    private Integer platformLimit;//平台限制
    private String platformLimitOrgNumber;//平台编号

    @NotNull(message="金额限制不能为空")
    @Range(min=0,max=2,message="金额限制值必须为0~2之间")
    private Integer amountLimit;//0=不限 | 1=大于金额 | 2=大于等于金额
    private BigDecimal amount;//投资金额  amountLimit=1或者2时有效

    @NotNull(message="用户投资限制不能为空")
    @Range(min=0,max=2,message="用户投资值必须为0~2之间")
    private Integer investLimit;//用户投资限制 0=不限|1=用户首投|2=平台首投

    @NotNull(message="投资期限限制不能为空")
    @Range(min=1000,max=1004,message="产品限制值必须为1000~1004之间")
    private Integer productLimit;//1002=等于|1003=大于等于
    private Integer productLimitDeadline;//投资期限（天）

    @NotNull(message="加拥天数限制不能为空")
    @Range(min=0,max=1,message="加拥天数限制值必须为0~1之间")
    private Integer addFeeLimit;//加拥天数限制
    private Integer addFeeLimitDay;//加拥天数

    private String operator;//操作人
    private String ticketId;//加拥券ID

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getType() {

        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPlatformLimit() {
        return platformLimit;
    }

    public void setPlatformLimit(Integer platformLimit) {
        this.platformLimit = platformLimit;
    }

    public String getPlatformLimitOrgNumber() {
        return platformLimitOrgNumber;
    }

    public void setPlatformLimitOrgNumber(String platformLimitOrgNumber) {
        this.platformLimitOrgNumber = platformLimitOrgNumber;
    }

    public Integer getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(Integer amountLimit) {
        this.amountLimit = amountLimit;
    }

    public Integer getInvestLimit() {
        return investLimit;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setInvestLimit(Integer investLimit) {
        this.investLimit = investLimit;
    }

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public Integer getProductLimitDeadline() {
        return productLimitDeadline;
    }

    public void setProductLimitDeadline(Integer productLimitDeadline) {
        this.productLimitDeadline = productLimitDeadline;
    }

    public Integer getAddFeeLimit() {
        return addFeeLimit;
    }

    public void setAddFeeLimit(Integer addFeeLimit) {
        this.addFeeLimit = addFeeLimit;
    }

    public Integer getAddFeeLimitDay() {
        return addFeeLimitDay;
    }

    public void setAddFeeLimitDay(Integer addFeeLimitDay) {
        this.addFeeLimitDay = addFeeLimitDay;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
