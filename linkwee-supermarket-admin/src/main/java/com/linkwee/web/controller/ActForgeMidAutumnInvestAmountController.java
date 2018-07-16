package com.linkwee.web.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActForgeMidautumInvestAmount;
import com.linkwee.web.service.ActForgeMidautumInvestAmountService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： ActForgeInvestAmountController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年08月25日 10:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "act/actforgemidautuminvestamount")
@RequestLogging("ActForgeMidAutumnInvestAmountController控制器")
public class ActForgeMidAutumnInvestAmountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActForgeMidAutumnInvestAmountController.class);

	@Resource
	private ActForgeMidautumInvestAmountService actForgeMidautumInvestAmountService;
	
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
	
    /**
     * 查看列表
     */
    @RequestMapping(value="/list")
    @RequestLogging("查看列表页面")
    public String actForgeInvestAmount(Model model) {
    	return "actforgemidautuminvestamount/actforgeinvestamount-list";
    }
    
    /**
     * 列表数据
     * @param newsRequest
     * @param dataTable
     * @return
     * @throws Exception
     */
    @RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn forgeinvestamountListAjax(ActForgeMidautumInvestAmount actForgeMidautumInvestAmount, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn = actForgeMidautumInvestAmountService.findForgeinvestamountList(actForgeMidautumInvestAmount,dataTable);
		return dataTableReturn;
	}

    /**
	 * 删除
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public Object del(@RequestParam String id ){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		if(StringUtils.isNotBlank(id)){
			ReturnCode returnCode = actForgeMidautumInvestAmountService.deleteForgeinvestamountList(Integer.parseInt(id));
			if(returnCode.getCode() == 0){
				result = new ResponseResult(true, "删除成功");
				logsb.append("actForgeMidautumInvestAmountService deleteForgeinvestamountList success");
				LOGGER.info(logsb.toString());
			}else{
				result = new ResponseResult(false, "删除失败");
				
			}
		}
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
	
	/**
	 * 转新增页
	 * @return
	 */
	@RequestMapping("tosave")
	public ModelAndView toSave(Long id){
		ModelAndView modelAndView = new ModelAndView("actforgemidautuminvestamount/actforgeinvestamountDtl");
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		if(null !=id && id>0){
			ActForgeMidautumInvestAmount actForgeMidautumInvestAmount = actForgeMidautumInvestAmountService.selectById(id);
			actForgeMidautumInvestAmount.setHeadImage(systemConfigService.getImageUrl(actForgeMidautumInvestAmount.getHeadImage()));
			modelAndView.addObject("actForgeMidautumInvestAmount",actForgeMidautumInvestAmount);
		}
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(ActForgeMidautumInvestAmount actForgeMidautumInvestAmount,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		ReturnCode returnCode =null;
		String message = "添加成功";
		if(actForgeMidautumInvestAmount.getId()!=null && actForgeMidautumInvestAmount.getId()>0){
			message = "更新成功";
			returnCode = actForgeMidautumInvestAmountService.updateActForgeInvestAmount(convert(actForgeMidautumInvestAmount));
		}
		else{
			returnCode = actForgeMidautumInvestAmountService.saveActForgeInvestAmount(convert(actForgeMidautumInvestAmount));
		}
			
		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, message);
			logsb.append("actForgeInvestAmountService save success");
			LOGGER.info(logsb.toString());
		}else{
			result = new ResponseResult(false, "操作失败");
		}
	
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
	
	private ActForgeMidautumInvestAmount convert(ActForgeMidautumInvestAmount actForgeInvestAmount){
		Subject currentUser = SecurityUtils.getSubject();
		actForgeInvestAmount.setUpdater(currentUser.getPrincipal().toString());
		actForgeInvestAmount.setUpdateTime(DateUtils.getCurrentDate());
		return actForgeInvestAmount;
	}
	
}
