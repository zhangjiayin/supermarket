package com.linkwee.web.controller;

import java.lang.reflect.Type;
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

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections.CollectionUtils;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.model.CimInsuranceRecommend;
import com.linkwee.web.model.CimInsuranceRecommendName;
import com.linkwee.web.service.CimInsuranceProductService;
import com.linkwee.web.service.CimInsuranceRecommendService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimInsuranceProductController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月21日 13:51:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/ciminsuranceproduct")
@RequestLogging("CimInsuranceProductController控制器")
public class CimInsuranceProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceProductController.class);

	@Resource
	private CimInsuranceProductService cimInsuranceProductService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private CimInsuranceRecommendService cimInsuranceRecommendService;
	
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
    public String cimInsuranceProduct(Model model) {
    	model.addAttribute("img_server",sysConfigService.getValuesByKey("img_server_url"));
    	return "ciminsuranceproduct/ciminsuranceproduct-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCimInsuranceProducts(@RequestParam String  _dt_json) {
		LOGGER.debug("CimInsuranceProduct list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = cimInsuranceProductService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequestLogging("CUD操作")
	public DataResult save(@RequestParam String rows) {
    	Gson gson = new Gson();
		Type quickType = new TypeToken<DataInfo<CimInsuranceProduct>>(){}.getType();
		DataInfo<CimInsuranceProduct>  df = gson.fromJson(rows,quickType);
		Map<String,CimInsuranceProduct> map = df.getData();
    	DataResult dr = new DataResult();
    	List<CimInsuranceProduct> datas = new ArrayList<CimInsuranceProduct>();
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
					CimInsuranceProduct r = map.get(key);
					datas.add(r);
					Set<ConstraintViolation<CimInsuranceProduct>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimInsuranceProduct> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }
			        r.setCreatTime(new Date());
			        r.setProductId(ApplicationUtils.randomUUID(true,true));
					this.cimInsuranceProductService.insert(r);
					
					if(CollectionUtils.isNotEmpty(r.getCimInsuranceRecommendNameList())){
						for (CimInsuranceRecommendName cimInsuranceRecommendName : r.getCimInsuranceRecommendNameList()) {
							CimInsuranceRecommend cimInsuranceRecommend = new CimInsuranceRecommend();
							cimInsuranceRecommend.setProductId(r.getProductId());
							cimInsuranceRecommend.setRecommendType(cimInsuranceRecommendName.getRecommendType());
							cimInsuranceRecommendService.insert(cimInsuranceRecommend);
						}
					}
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					CimInsuranceProduct r = map.get(key);
					datas.add(r);
					Set<ConstraintViolation<CimInsuranceProduct>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<CimInsuranceProduct> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
			        r.setUpTime(new Date());
			        if(StringUtils.isBlank(r.getProductId())){
			        	r.setProductId(ApplicationUtils.randomUUID(true,true));
			        }
					this.cimInsuranceProductService.update(r);
					
					cimInsuranceRecommendService.deleteByProductId(r.getProductId());
					if(CollectionUtils.isNotEmpty(r.getCimInsuranceRecommendNameList())){
						for (CimInsuranceRecommendName cimInsuranceRecommendName : r.getCimInsuranceRecommendNameList()) {
							CimInsuranceRecommend cimInsuranceRecommend = new CimInsuranceRecommend();
							cimInsuranceRecommend.setProductId(r.getProductId());
							cimInsuranceRecommend.setRecommendType(cimInsuranceRecommendName.getRecommendType());
							cimInsuranceRecommendService.insert(cimInsuranceRecommend);
						}
					}
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					CimInsuranceProduct r = map.get(key);
					this.cimInsuranceProductService.delete(Long.parseLong(key));
					cimInsuranceRecommendService.deleteByProductId(r.getProductId());
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
}
