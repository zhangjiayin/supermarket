package com.linkwee.xoss.thirdsdk.xiaoying.open.repay;

import lombok.Getter;
import lombok.Setter;

import com.linkwee.xoss.thirdsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
@Getter @Setter
public class XYRepayDetailReq extends XYRequest{

    private int loanOrderId; // 借款订单号	123456	通过借款申请接口返回
    private int repayType;   //还款类型		0:还至当期；1:只还逾期；2:提前还款；（提前还款为还清剩余借款金额，请谨慎。默认“正常还款”）

    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/repay/detail";
    }
}
