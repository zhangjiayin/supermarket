package com.linkwee.web.controller.customer;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.interceptors.DateConvertEditor;
import com.linkwee.web.rbac.PermissionSign;
import com.linkwee.web.service.FreindsInfoService;
import com.linkwee.web.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@RequestMapping("freindsInfo")
@Controller
@RequestLogging("邀请的好友管理")
public class FreindsInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreindsInfoController.class);

	@Resource
	private FreindsInfoService freindsInfoService;
	
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

    /**
     * 查看列表.
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequiresPermissions(value = PermissionSign.INVESTOR_DETAIL_READ)
    @RequestLogging("邀请的好友列表")
    public String investorUserInfo(Model model, @RequestParam String userId) {
    	model.addAttribute("userId", userId);
    	return "investor/freindsInfo-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
	public DataTableReturn getInvestorUserInfos(@RequestParam String  _dt_json, @RequestParam String userId) {
		LOGGER.debug("InvestorUserInfo list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = freindsInfoService.selectByDatatables(dataTable ,userId);
		return tableReturn;
	}


	
}
