package com.openpltsdk.xiaoying.open;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
@lombok.Setter @lombok.Getter
public class XYResponseException extends Exception{
    private int httpCode;
    private String httpBody;

    XYResponseException(String message) {
        super(message);

        httpCode = -1;
        httpBody = "";
    }


}
