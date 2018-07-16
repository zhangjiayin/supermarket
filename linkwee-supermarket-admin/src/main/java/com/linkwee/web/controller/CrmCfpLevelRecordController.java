package com.linkwee.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
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

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.request.CfpLevelStatisticsRequest;
import com.linkwee.web.response.CfpLevelDataStatisticsListResp;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CrmCfpLevelRecordController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/crmcfplevelrecord")
@RequestLogging("CrmCfpLevelRecordController控制器")
public class CrmCfpLevelRecordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLevelRecordController.class);

	@Resource
	private CrmCfpLevelRecordService crmCfpLevelRecordService;
	
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
    public String crmCfpLevelRecord(Model model) {
    	return "crmcfplevelrecord/crmcfplevelrecord-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCrmCfpLevelRecords(@RequestParam String  _dt_json) {
		LOGGER.debug("CrmCfpLevelRecord list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = crmCfpLevelRecordService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,CrmCfpLevelRecord> map =  (Map<String, CrmCfpLevelRecord>) df.getData();
    	DataResult dr = new DataResult();
    	List<CrmCfpLevelRecord> datas = new ArrayList<CrmCfpLevelRecord>();
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
					CrmCfpLevelRecord r = new CrmCfpLevelRecord();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CrmCfpLevelRecord>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CrmCfpLevelRecord> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.crmCfpLevelRecordService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					CrmCfpLevelRecord r = new CrmCfpLevelRecord();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CrmCfpLevelRecord>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CrmCfpLevelRecord> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.crmCfpLevelRecordService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.crmCfpLevelRecordService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
	

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/queryStatisticsList", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("管理后台-数据统计-职级人数")
	public List<CfpLevelDataStatisticsListResp> queryStatisticsList(CfpLevelStatisticsRequest cfpLevelStatisticsRequest) {
    	if(StringUtils.isBlank(cfpLevelStatisticsRequest.getStartTime())){
    		cfpLevelStatisticsRequest.setStartTime("201701");
    	}
    	if(StringUtils.isBlank(cfpLevelStatisticsRequest.getEndTime())){
    		cfpLevelStatisticsRequest.setEndTime(DateUtils.getNow("yyyyMM"));
    	}
    	LOGGER.info("管理后台-查看列表数据,cfpLevelStatisticsRequest={}",JSONObject.toJSONString(cfpLevelStatisticsRequest));
		return crmCfpLevelRecordService.queryStatisticsList(cfpLevelStatisticsRequest);
	}
}
