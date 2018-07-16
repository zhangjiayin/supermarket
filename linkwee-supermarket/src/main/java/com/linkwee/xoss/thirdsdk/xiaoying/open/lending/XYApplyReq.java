package com.linkwee.xoss.thirdsdk.xiaoying.open.lending;


import lombok.Getter;
import lombok.Setter;

import com.linkwee.xoss.thirdsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved..
 */
@Getter @Setter
public class XYApplyReq extends XYRequest {
    private String partnerOrderId;  //合作方订单号 合作方借款订单号，用于防重录
    private int type;  //借款类型 小赢分配
    private int amount;  //借款金额 单位：分
    private int arrivalAmount;  //到账金额 单位：分
    private int partnerServiceRate;  //合作方服务费率 单位：利率*10000。该字段选填，若有，小赢放款时会根据该费率分账到合作方账户。小赢会根据该费率校验到账金额，具体策略及值域范围视产品而定。
    private int interestType;  //计息方式 1:按日计息；2:按月计息；
    private int periodDays;  //借款天数 按日计息，填具体借款天数；按月计息不填；
    private int periodMonths;  //借款月数 按月计息，填具体借款期数；按日计息不填
    private int repayType;  //还款方式 1:先息后本；2:到期还本付息；3:等额本息；
    private UserInfo userInfo;  //借款人信息 参见2.1
    private String riskInfo;  //风控信息 参见2.2

    final public class UserInfo {
        private String mobile;  //手机号
        private int idType;  //证件类型 1：大陆居民二代身份证
        private String idNo;  //证件号
        private String name;  //姓名
        private IdPictures idPictures;  //证件照片 证件照必须提供。否则影响进一步操作。参数详看2.1.1
        private CardInfo cardInfo;  //用户银行卡信息 参数详看2.1.2
        private String email;  //用户邮箱 用于推送小赢消息，如保单/账单等
    }

    @Getter @Setter
    final public class IdPictures {
        private String front;  //证件正面照片 用户证件照片正面
        private String back;  //证件反面照片 用户证件照片反面
    }

    @Getter @Setter
    final public class CardInfo {
        private String ip;  //用户IP 用户ip地址
        private String code;  //银行编码 详看文档最后附录
        private String cardNo;  //银行卡号
        private String reservedMobile;  //办卡预留手机号 注意确认银行预留信息
        private String province;  //开户省份 省份城市获取地址：
        private String city;  //开户城市 参考省份。（以下银行必填：中国邮储银行、上海银行、北京银行）
    }


    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/loan/apply";
    }

}
