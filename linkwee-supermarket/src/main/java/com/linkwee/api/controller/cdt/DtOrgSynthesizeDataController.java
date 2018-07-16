package com.linkwee.api.controller.cdt;

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

import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.DtOrgSynthesizeData;
import com.linkwee.web.service.DtOrgSynthesizeDataService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： DtOrgSynthesizeDataController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月06日 16:49:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/dtorgsynthesizedata")
@RequestLogging("DtOrgSynthesizeDataController控制器")
public class DtOrgSynthesizeDataController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DtOrgSynthesizeDataController.class);

	@Resource
	private DtOrgSynthesizeDataService dtOrgSynthesizeDataService;
	
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
    public String dtOrgSynthesizeData(Model model) {
    	return "dtorgsynthesizedata/dtorgsynthesizedata-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getDtOrgSynthesizeDatas(@RequestParam String  _dt_json) {
		LOGGER.debug("DtOrgSynthesizeData list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = dtOrgSynthesizeDataService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,DtOrgSynthesizeData> map =  (Map<String, DtOrgSynthesizeData>) df.getData();
    	DataResult dr = new DataResult();
    	List<DtOrgSynthesizeData> datas = new ArrayList<DtOrgSynthesizeData>();
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
					DtOrgSynthesizeData r = new DtOrgSynthesizeData();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<DtOrgSynthesizeData>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<DtOrgSynthesizeData> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.dtOrgSynthesizeDataService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					DtOrgSynthesizeData r = new DtOrgSynthesizeData();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<DtOrgSynthesizeData>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<DtOrgSynthesizeData> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.dtOrgSynthesizeDataService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.dtOrgSynthesizeDataService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
	
}