package com.linkwee.web.request;

import com.linkwee.web.model.CimOrgFeeStrategyA;
import com.linkwee.web.model.cim.CimOrgFeeInterval;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
*
* @描述： 收费模式
*
* @创建人： Mignet
*
* @创建时间：2016年07月11日 16:27:03
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class CimOrgAFeeStrategyRequest implements Serializable {

    /**
    *
    */
   private static final long serialVersionUID = 3636370398152543751L;

   /**
    *主键，自增长
    */
   private Integer id;

   /**
    *机构编码
    */
   private String orgNumber;

   /**
    * cpa收费类型类别
    * 1-首投固定猎财返现
    * 2-首投金额固定返现比例
    * 3-首投期限固定返现比例
    */
   private Integer cpaFeeAttr;

   /**
    *收费额度
    */
   private BigDecimal fixedMoney;

   /**
    *收费比例
    */
   private BigDecimal fixedMoneyRatio;

   /**
    *修改人
    */
   private String updater;

   /**
    * 期限区间
    */
   private List<CimOrgFeeStrategyA> orgFeeRecords;

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

    public Integer getCpaFeeAttr() {
        return cpaFeeAttr;
    }

    public void setCpaFeeAttr(Integer cpaFeeAttr) {
        this.cpaFeeAttr = cpaFeeAttr;
    }

    public BigDecimal getFixedMoney() {
        return fixedMoney;
    }

    public void setFixedMoney(BigDecimal fixedMoney) {
        this.fixedMoney = fixedMoney;
    }

    public BigDecimal getFixedMoneyRatio() {
        return fixedMoneyRatio;
    }

    public void setFixedMoneyRatio(BigDecimal fixedMoneyRatio) {
        this.fixedMoneyRatio = fixedMoneyRatio;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<CimOrgFeeStrategyA> getOrgFeeRecords() {
        return orgFeeRecords;
    }

    public void setOrgFeeRecords(List<CimOrgFeeStrategyA> orgFeeRecords) {
        this.orgFeeRecords = orgFeeRecords;
    }
}

