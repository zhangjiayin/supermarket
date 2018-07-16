package com.linkwee.api.controller.cdt;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linkwee.web.service.DtOrgMoneyInOutDayService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： DtOrgMoneyInOutDayController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/dtorgmoneyinoutday")
@RequestLogging("DtOrgMoneyInOutDayController控制器")
public class DtOrgMoneyInOutDayController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DtOrgMoneyInOutDayController.class);

	@Resource
	private DtOrgMoneyInOutDayService dtOrgMoneyInOutDayService;
	
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
