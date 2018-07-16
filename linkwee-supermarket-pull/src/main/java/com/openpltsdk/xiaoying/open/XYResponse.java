package com.openpltsdk.xiaoying.open;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.JSON;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
@Getter @Setter
public class XYResponse implements Serializable {
    private int result;
    private JSON respData;
}
