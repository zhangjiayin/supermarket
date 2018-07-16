package com.linkwee.api.response.cim;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrgAUserInfoResponse implements Serializable {

    /**
     * 姓名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 总返现
     */
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal totalCashBack;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public BigDecimal getTotalCashBack() {
        return totalCashBack;
    }

    public void setTotalCashBack(BigDecimal totalCashBack) {
        this.totalCashBack = totalCashBack;
    }
}
