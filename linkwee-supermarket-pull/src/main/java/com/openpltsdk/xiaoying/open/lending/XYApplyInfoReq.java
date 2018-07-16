package com.openpltsdk.xiaoying.open.lending;

import lombok.Getter;
import lombok.Setter;

import com.openpltsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
@Getter @Setter
public class XYApplyInfoReq extends XYRequest {

    private int loanOrderId; //借款订单ID

    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/loan/apply-info";
    }
}
