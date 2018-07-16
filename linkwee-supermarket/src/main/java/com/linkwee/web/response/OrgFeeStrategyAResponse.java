package com.linkwee.web.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

import java.math.BigDecimal;

public class OrgFeeStrategyAResponse {
    /**
     *主键，自增长
     */
    private Integer id;

    /**
     *机构编码
     */
    private String orgNumber;

    /**
     *佣金计算规则 (1：固定金额 2：固定比例 3：按期限固定比例)
     */
    private Integer feeStrategy;

    /**
     *收费比例
     */
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal feeRatio;

    /**
     *收费额度
     */
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal feeVal;

    /**
     *区间最小值
     */
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal intervalMinVal;

    /**
     *区间最大值
     */
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal intervalMaxVal;

    /**
     *区间单位,首投金额元,产品期限天,月销售额万
     */
    private String intervalUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public Integer getFeeStrategy() {
        return feeStrategy;
    }

    public void setFeeStrategy(Integer feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }

    public BigDecimal getFeeVal() {
        return feeVal;
    }

    public void setFeeVal(BigDecimal feeVal) {
        this.feeVal = feeVal;
    }

    public BigDecimal getIntervalMinVal() {
        return intervalMinVal;
    }

    public void setIntervalMinVal(BigDecimal intervalMinVal) {
        this.intervalMinVal = intervalMinVal;
    }

    public BigDecimal getIntervalMaxVal() {
        return intervalMaxVal;
    }

    public void setIntervalMaxVal(BigDecimal intervalMaxVal) {
        this.intervalMaxVal = intervalMaxVal;
    }

    public String getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }
}
