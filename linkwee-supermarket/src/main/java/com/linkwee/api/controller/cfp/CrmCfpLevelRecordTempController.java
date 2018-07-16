package com.linkwee.api.controller.cfp;

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

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.response.CfpLevelWarningResp;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CrmCfpLevelRecordTempController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年04月10日 13:51:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/cim/crmcfplevelrecordtemp")
@RequestLogging("CrmCfpLevelRecordTempController控制器")
public class CrmCfpLevelRecordTempController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLevelRecordTempController.class);

	@Resource
	private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
	
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
    public String crmCfpLevelRecordTemp(Model model) {
    	return "crmcfplevelrecordtemp/crmcfplevelrecordtemp-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCrmCfpLevelRecordTemps(@RequestParam String  _dt_json) {
		LOGGER.debug("CrmCfpLevelRecordTemp list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = crmCfpLevelRecordTempService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,CrmCfpLevelRecordTemp> map =  (Map<String, CrmCfpLevelRecordTemp>) df.getData();
    	DataResult dr = new DataResult();
    	List<CrmCfpLevelRecordTemp> datas = new ArrayList<CrmCfpLevelRecordTemp>();
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
					CrmCfpLevelRecordTemp r = new CrmCfpLevelRecordTemp();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CrmCfpLevelRecordTemp>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CrmCfpLevelRecordTemp> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.crmCfpLevelRecordTempService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					CrmCfpLevelRecordTemp r = new CrmCfpLevelRecordTemp();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<CrmCfpLevelRecordTemp>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CrmCfpLevelRecordTemp> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.crmCfpLevelRecordTempService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.crmCfpLevelRecordTempService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
	/**
	 * 理财师职级提醒
	 * @param appRequestHead
	 * @return
	 */
    @RequestMapping(value="/cfpLevelWarning")
    @ResponseBody
    @RequestLogging("理财师职级提醒")
	public BaseResponse cfpLevelWarning(AppRequestHead appRequestHead) {
    	CfpLevelWarningResp cfpLevelWarningResp = new CfpLevelWarningResp();
    	String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//查询userId
    	cfpLevelWarningResp= crmCfpLevelRecordTempService.cfpLevelWarning(userId);
    	return AppResponseUtil.getSuccessResponse(cfpLevelWarningResp);
	}
}
