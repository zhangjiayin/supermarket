package com.linkwee.xoss.thirdsdk.xiaoying.open.repay;

import lombok.Getter;
import lombok.Setter;

import com.linkwee.xoss.thirdsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved..
 */
@Getter @Setter
public class XYRepaySyncReq extends XYRequest {
    private int loanOrderId;  //借款订单号 借款申请时返回的进件ID
    private int period;  //还款期数 还款期数，表示当前要还的是第几期。若产品没有分期，如“到期还本付息”，则，填1
    private int planRepayDate;  //计划还款日 unix
    private int realRepayDate;  //实际还款日 unix
    private int planRepayAmount;  //计划还款金额 单位：分。指定期，计划还款的金额。
    private int realRepayAmount;  //实际还款金额 单位：分。指定期，用户实际还款的金额。
    private int repayType;  //还款类型 0：正常还款；1：提前还款；(默认为

    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/contract/view";
    }
}
