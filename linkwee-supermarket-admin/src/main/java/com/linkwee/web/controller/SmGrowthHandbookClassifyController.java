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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.model.SmNewsClassify;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.service.SmGrowthHandbookClassifyService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： SmGrowthHandbookClassifyController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "sm/smgrowthhandbookclassify")
@RequestLogging("SmGrowthHandbookClassifyController控制器")
public class SmGrowthHandbookClassifyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmGrowthHandbookClassifyController.class);

	@Resource
	private SmGrowthHandbookClassifyService smGrowthHandbookClassifyService;
	
	@Resource
	private SysConfigService systemConfigService;
	
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

	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("smgrowthhandbookclassify/smgrowthhandbookclassify-list");
		return modelAndView;
	}
	
	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn listAjax(SmGrowthHandbookClassify request, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		if(request==null){
			request = new SmGrowthHandbookClassify();
		}
		if(request.getAppType() == null || request.getAppType()<=0){
			request.setAppType(1);
		}
		dataTableReturn = smGrowthHandbookClassifyService.findhandbookClassifyList(request,dataTable);
		return dataTableReturn;
	}
	
	@RequestMapping("/tosave")
	public ModelAndView classifyToSave(Integer id){
		ModelAndView modelAndView = new ModelAndView("smgrowthhandbookclassify/smgrowthhandbookclassifyDtl");
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		if(null !=id && id>0){
			SmGrowthHandbookClassify classify = smGrowthHandbookClassifyService.selectById(Long.parseLong(String.valueOf(id)));
			modelAndView.addObject("classify",classify);
		}
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Object classifSave(SmGrowthHandbookClassify classify,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		if(classify.getAppType() == null || classify.getAppType()<=0){
			classify.setAppType(1);
		}
		ResponseResult result = null;
		ReturnCode returnCode =null;
		if(classify.getId()!=null && classify.getId()>0){
			returnCode = smGrowthHandbookClassifyService.updateHandbookClassify(classify);
		}
		else{
			returnCode = smGrowthHandbookClassifyService.saveHandbookClassify(classify);
		}
			
		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, "操作成功");
		}else{
			result = new ResponseResult(false, "操作失败");
		}
		return result;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Object classifyDel(@RequestParam String id ){
		ResponseResult result = null;
		if(StringUtils.isNotBlank(id)){
			ReturnCode returnCode = smGrowthHandbookClassifyService.deleteHandbookClassify(Integer.parseInt(id));
			if(returnCode.getCode() == 0){
				result = new ResponseResult(true, "删除成功");
			}else{
				result = new ResponseResult(false, "删除失败");				
			}
		}
		return result;
	}
	
}
