package com.linkwee.xoss.thirdsdk.xiaoying.open.register;

import lombok.Getter;
import lombok.Setter;

import com.linkwee.xoss.thirdsdk.xiaoying.open.XYRequest;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
@Getter @Setter
final public class XYRegisterReq extends XYRequest {
    private String mobile;  //手机号
    private String name;  //姓名
    private int idType;  //证件类型 默认1 二代身份证（目前仅支持个人身份证)
    private String idNo;  //证件号
    private IdPictures idPictures;  //证件照片 参数详看2.1
    private String email;  //用户邮箱 用于推送小赢消息，如保单/账单等


    @Getter @Setter
    final public class IdPictures {
        private String front;  //证件正面照片 用户证件照片正面
        private String back;   //证件反面照片 用户证件照片反面
    }

    @Override
    public String getUrl() {
        return "https://api.xiaoying.com/user/register?appid=100053&ver=v1.0";
    }
}
