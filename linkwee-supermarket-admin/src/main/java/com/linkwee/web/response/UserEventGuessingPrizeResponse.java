package com.linkwee.web.response;

import java.math.BigDecimal;

public class UserEventGuessingPrizeResponse {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 金额
     */
    private BigDecimal amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
