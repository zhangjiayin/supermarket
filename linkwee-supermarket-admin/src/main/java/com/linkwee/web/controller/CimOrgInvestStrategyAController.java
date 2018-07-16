package com.linkwee.web.controller;

import com.linkwee.core.datatable.*;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CimOrgInvestStrategyA;
import com.linkwee.web.service.CimOrgInvestStrategyAService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
*
* @描述： CimOrgInvestStrategyAController控制器
*
* @创建人： Hxb
*
* @创建时间：2018年05月09日 18:01:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Controller
@RequestMapping(value = "cim/cimorginveststrategya")
@RequestLogging("CimOrgInvestStrategyAController控制器")
public class CimOrgInvestStrategyAController {

   private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgInvestStrategyAController.class);

   @Resource
   private CimOrgInvestStrategyAService cimOrgInvestStrategyAService;

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
   public String cimOrgInvestStrategyA(Model model) {
       return "cimorginveststrategya/cimorginveststrategya-list";
   }

   /**
    * datatables<br>
    * @return
    */
   @RequestMapping(value="/list", method = RequestMethod.POST)
   @ResponseBody
   @RequestLogging("查看列表")
   public DataTableReturn getCimOrgInvestStrategyAs(@RequestParam String  _dt_json) {
       LOGGER.debug("CimOrgInvestStrategyA list _dt_json={}", _dt_json);
       DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
       dataTable.initOrders();
       DataTableReturn tableReturn = cimOrgInvestStrategyAService.selectByDatatables(dataTable);
       return tableReturn;
   }


   @RequestMapping(value = "/save", method = RequestMethod.POST)
   @ResponseBody
   @RequestLogging("CUD操作")
   public DataResult save(@RequestParam String rows) {
       DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class);
       @SuppressWarnings("unchecked")
       Map<String,CimOrgInvestStrategyA> map =  (Map<String, CimOrgInvestStrategyA>) df.getData();
       DataResult dr = new DataResult();
       List<CimOrgInvestStrategyA> datas = new ArrayList<CimOrgInvestStrategyA>();
       List<ErrorField> errors = new ArrayList<ErrorField>();
       ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       Validator validator = factory.getValidator();
       //下面用到bean属性copy，需要对日期进行转换
       DateConverter dateConverter = new DateConverter();
       dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
       ConvertUtils.register(dateConverter, Date.class);
       try {
           if(DataInfo.ACTION_CREATE.equals(df.getAction())){
               for (String key : map.keySet()) {
                   CimOrgInvestStrategyA r = new CimOrgInvestStrategyA();
                   BeanUtils.copyProperties(r, map.get(key));
                   datas.add(r);
                   Set<ConstraintViolation<CimOrgInvestStrategyA>> constraintViolations = validator.validate(r);
                   for (ConstraintViolation<CimOrgInvestStrategyA> constraintViolation : constraintViolations) {
                       errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
                       dr.setFieldErrors(errors);
                       return dr;
                   }
                   this.cimOrgInvestStrategyAService.insert(r);
               }
           }
           if(DataInfo.ACTION_EDIT.equals(df.getAction())){
               for (String key : map.keySet()) {
                   CimOrgInvestStrategyA r = new CimOrgInvestStrategyA();
                   BeanUtils.copyProperties(r, map.get(key));
                   datas.add(r);
                   Set<ConstraintViolation<CimOrgInvestStrategyA>> constraintViolations = validator.validate(r);
                   for (ConstraintViolation<CimOrgInvestStrategyA> constraintViolation : constraintViolations) {
                       errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
                       dr.setFieldErrors(errors);
                       return dr;
                   }
                   this.cimOrgInvestStrategyAService.update(r);
               }
           }
           if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
               for (String key : map.keySet()) {
                   this.cimOrgInvestStrategyAService.delete(Long.parseLong(key));
               }
           }
       } catch (Exception e) {
           dr.setError(e.getMessage());
       }
       dr.setData(datas);
       return dr;
   }

}
