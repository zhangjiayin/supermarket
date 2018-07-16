package com.linkwee.xoss.thirdsdk.xiaoying.open;

import java.io.Serializable;
import java.lang.reflect.Field;

import okhttp3.FormBody;

import com.alibaba.fastjson.JSON;


/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
public abstract class XYRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * 该请求所在的url
     * @return
     */
    public abstract String getUrl();

    public FormBody.Builder formBodyBuilder() {
        Field[] fields = this.getClass().getDeclaredFields();

        FormBody.Builder builder = new FormBody.Builder();

        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (fields[i].getType() == String.class) {
                    builder.add(fields[i].getName(), (String)fields[i].get(this));
                } else if (fields[i].getType() == long.class) {
                    builder.add(fields[i].getName(), Long.toString(fields[i].getLong(this)));
                } else if (fields[i].getType() == int.class) {
                    builder.add(fields[i].getName(), Integer.toString(fields[i].getInt(this)));
                } else {
                    builder.add(fields[i].getName(), JSON.toJSONString(fields[i].get(this)));
                }
            }

        } catch (Exception e) {
            return null;
        }

        return builder;
    }
}
