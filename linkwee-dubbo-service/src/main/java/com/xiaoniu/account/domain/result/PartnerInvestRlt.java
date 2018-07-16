package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户资产统计请求
 * @author 颜彩云
 */
public class PartnerInvestRlt implements Serializable {
    
    private static final long serialVersionUID = 121781158882363837L;
    
    private Date registerDate;// 注册日期
    
    private String source;// 一级渠道
    
    private String channel;// 二级渠道
    
    private Integer registerCount;// 注册人数
    
    private Integer registerInvestCount;// 当日注册投资人数
    
    private Long registerInvestAmount;// 当日注册投资金额
    
    private Integer investCount;// 首次投资人数
    
    private Long investAmount;// 首次投资金额（总）
    
    private Integer nameAuthCount;// 实名人数
    
    public Date getRegisterDate() {
        return registerDate;
    }
    
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    public Integer getRegisterCount() {
        return registerCount;
    }
    
    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }
    
    public Integer getInvestCount() {
        return investCount;
    }
    
    public void setInvestCount(Integer investCount) {
        this.investCount = investCount;
    }
    
    public Long getInvestAmount() {
        return investAmount;
    }
    
    public void setInvestAmount(Long investAmount) {
        this.investAmount = investAmount;
    }
    
    public Integer getNameAuthCount() {
        return nameAuthCount;
    }
    
    public void setNameAuthCount(Integer nameAuthCount) {
        this.nameAuthCount = nameAuthCount;
    }
    
    public Integer getRegisterInvestCount() {
        return registerInvestCount;
    }
    
    public void setRegisterInvestCount(Integer registerInvestCount) {
        this.registerInvestCount = registerInvestCount;
    }
    
    public Long getRegisterInvestAmount() {
        return registerInvestAmount;
    }
    
    public void setRegisterInvestAmount(Long registerInvestAmount) {
        this.registerInvestAmount = registerInvestAmount;
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
