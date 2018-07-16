package com.linkwee.xoss.thirdsdk.xiaoying.open;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
public class XYLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XYLogger.class);

    public static void info(String msg) {
    	LOGGER.info(msg);
    }

    public static void warning(String msg) {
    	LOGGER.warn(msg);
    }

    public static void error(String msg) {
    	LOGGER.error(msg);
    }

}
