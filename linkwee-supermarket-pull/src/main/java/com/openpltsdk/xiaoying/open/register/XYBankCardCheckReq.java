package com.openpltsdk.xiaoying.open.register;

import lombok.Getter;
import lombok.Setter;

import com.openpltsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
@Getter @Setter
final public class XYBankCardCheckReq extends XYRequest {
    private long   loanOrderId;  //借款订单号
    private String openId;  //注册返回的openId
    private String code;  //银行编号 由赢众通提供数据，参见2.6
    private String cardNo;  //银行卡号
    private String reservedMobile;  //办卡预留手机号
    private String province;  //开户省份 由赢众通提供数据
    private String city;  //开户城市 省份城市获取URL：

    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/card/check";
    }
}
