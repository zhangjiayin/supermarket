package com.linkwee.xoss.thirdsdk.xiaoying.open.register;

import lombok.Getter;
import lombok.Setter;

import com.linkwee.xoss.thirdsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */

@Getter @Setter
final public class XYRegisterStatusReq extends XYRequest {
    private String openId;

    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/user/verify-status";
    }
}
