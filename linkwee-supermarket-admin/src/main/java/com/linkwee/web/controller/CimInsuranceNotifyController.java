package com.linkwee.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.User;
import com.linkwee.web.request.insurance.qixin.InsuranceAuditRequest;
import com.linkwee.web.request.insurance.qixin.InsuranceNotifyAuditRequest;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimInsuranceNotifyController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月30日 11:21:50
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/ciminsurancenotify")
@RequestLogging("CimInsuranceNotifyController控制器")
public class CimInsuranceNotifyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceNotifyController.class);

	@Resource
	private CimInsuranceNotifyService cimInsuranceNotifyService;
	
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
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String cimInsuranceNotify(Model model) {
    	return "ciminsurancenotify/ciminsurancenotify-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCimInsuranceNotifys(@RequestParam String  _dt_json) {
		LOGGER.debug("CimInsuranceNotify list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = cimInsuranceNotifyService.selectByDatatables(dataTable);
		return tableReturn;
	}

    
    /**
     * 保险订单审核列表<br>
     * @return
     */
    @RequestMapping(value="/auditList", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("管理后台-保险订单审核列表数据")
	public DataTableReturn getInsuranceNotify(@RequestBody InsuranceNotifyAuditRequest insuranceNotifyAuditRequest) {
    	LOGGER.info("管理后台-保险订单审核列表数据,InsuranceNotifyAuditRequest={}",JSONObject.toJSONString(insuranceNotifyAuditRequest));
    	insuranceNotifyAuditRequest.initOrdersOriginal();
		DataTableReturn tableReturn = cimInsuranceNotifyService.getInsuranceNotify(insuranceNotifyAuditRequest);
		return tableReturn;
	}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,CimInsuranceNotify> map =  (Map<String, CimInsuranceNotify>) df.getData();
    	DataResult dr = new DataResult();
    	List<CimInsuranceNotify> datas = new ArrayList<CimInsuranceNotify>();
    	List<ErrorField> errors = new ArrayList<ErrorField>();
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();    
        Validator validator = factory.getValidator();   
        //下面用到bean属性copy，需要对日期进行转换
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dateConverter, java.util.Date.class); 
    	try {
			if(DataInfo.ACTION_CREATE.equals(df.getAction())){
				for (String key : map.keySet()) {
					CimInsuranceNotify r = new CimInsuranceNotify();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CimInsuranceNotify>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimInsuranceNotify> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.cimInsuranceNotifyService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					CimInsuranceNotify r = new CimInsuranceNotify();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CimInsuranceNotify>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimInsuranceNotify> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.cimInsuranceNotifyService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.cimInsuranceNotifyService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
    /**
     * 保险订单审核<br>
     * @return
     */
    @RequestMapping(value="/audit", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("管理后台-保险订单审核")
	public String auditInsuranceNotify(InsuranceAuditRequest insuranceAuditRequest,HttpSession session) {
    	LOGGER.info("管理后台-保险订单审核,insuranceAuditRequest={}",JSONObject.toJSONString(insuranceAuditRequest));
    	User user = (User) session.getAttribute("userInfo"); 
    	insuranceAuditRequest.setUserName(user.getUsername());
    	String returnStr = null;
    	try {
    		synchronized(this){ 			
    			returnStr = cimInsuranceNotifyService.auditInsuranceNotify(insuranceAuditRequest);
    		}
		} catch (Exception e) {
			returnStr = "保险订单审核异常";
		}
    	LOGGER.info("管理后台-保险订单审核完成,returnStr={}",returnStr);
		return returnStr;
	}
	
}
