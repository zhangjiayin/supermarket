package com.linkwee.web.controller.customer;

import java.util.Date;

import javax.annotation.Resource;

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
import com.linkwee.web.request.InvestRecordRequest;
import com.linkwee.web.service.CustomerInvestService;
import com.linkwee.web.service.FixedInvestRecordService;
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
@Controller
@RequestMapping(value = "customerInvest")
@RequestLogging("客户投资收益管理")
public class CustomerInvestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerInvestController.class);

	@Resource
	private CustomerInvestService customerInvestService;
	
	@Resource
	private FixedInvestRecordService fixedInvestRecordService;
	
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
    @RequestLogging("客户投资与收益管理列表")
    public String investorUserInfo(Model model) {
    	return "investor/customerInvest-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
	public DataTableReturn getInvestorUserInfos(@RequestParam String  _dt_json) {
		LOGGER.debug("InvestorUserInfo list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = customerInvestService.selectByDatatables(dataTable );
		return tableReturn;
	}
    
    
    /**
     * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
     */
    @RequestMapping(value="/investRecordlist",   method=RequestMethod.GET)
    @RequestLogging("客户投资记录列表")
    public String fixedInvestRecord(Model model, @RequestParam String customerId) {
    	model.addAttribute("customerId", customerId);
    	return "investor/fixedInvestRecord-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/investRecordlist",   method=RequestMethod.POST)
    @ResponseBody
	public DataTableReturn getFixedInvestRecords(InvestRecordRequest investRecordRequest, DataTable dataTable) {
		DataTableReturn tableReturn = fixedInvestRecordService.selectByDatatables(dataTable,investRecordRequest);
		return tableReturn;
	}


	
}
