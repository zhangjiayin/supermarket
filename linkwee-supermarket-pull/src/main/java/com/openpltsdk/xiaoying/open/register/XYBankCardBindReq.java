package com.openpltsdk.xiaoying.open.register;

import lombok.Getter;
import lombok.Setter;

import com.openpltsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
@Getter @Setter
final public class XYBankCardBindReq extends XYRequest {
    private long loanOrderId;  //借款订单号
    private String openId;  //注册返回的openId
    private String verifiedData;  //预校验返回的数据
    private String smsCode;  //手机收到的验证码

    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/card/bind";
    }
}
