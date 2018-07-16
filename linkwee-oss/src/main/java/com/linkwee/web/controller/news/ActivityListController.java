package com.linkwee.web.controller.news;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

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

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.interceptors.DateConvertEditor;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.request.ActivityListRequest;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.util.RequestLogging;
import com.linkwee.web.util.ResponseUtil;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月28日 16:08:06
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cms/activitylist")
@RequestLogging("活动列表管理")
public class ActivityListController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityListController.class);

	@Resource
	private ActivityListService activityListService;
	
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
     * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    public String activityList(Model model) {
    	return "news/activitylist-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("活动列表")
	public DataTableReturn getActivityLists(DataTable dataTable,String actitityName) {
		DataTableReturn tableReturn = activityListService.selectByDatatables(dataTable, actitityName);
		return tableReturn;
	}

    /**
     * 新增页面
     * @return
     */
    @RequestMapping("toSave")
	public String toSave(){
    	return "news/activitylistDtl";
	}
    
    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/toEdit",   method=RequestMethod.GET)
    public String toEdit(@RequestParam String id, Model model) {
    	if(StringUtils.isNotBlank(id)){
    		Long idInt = Long.parseLong(id);
    		ActivityList ActivityList = activityListService.selectById(idInt);
			model.addAttribute("dtl", ActivityList);
			model.addAttribute("actionType","edit");
		}
    	return "news/activitylistDtl";
    }
    
    
    /**
	 * 修改
	 * @param mobile
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	@RequestLogging("编辑活动列表")
	public Object update(@Valid ActivityListRequest act,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		try {
			ActivityList bo = new ActivityList();
			bo.setId(act.getId());
			bo.setActivityCode(act.getActivityCode());
			bo.setActivityEndImg(act.getActivityEndImg());
			bo.setActivityName(act.getActivityName());
			bo.setActivityImg(act.getActivityImg());
			bo.setActivityType(act.getActivityType());
			bo.setAppType(act.getAppType());
			bo.setEndDate(act.getEndDate());
			bo.setLinkUrl(act.getLinkUrl());
			bo.setShareDesc(act.getShareDesc());
			bo.setShareIcon(act.getShareIcon());
			bo.setShareLink(act.getShareLink());
			bo.setShareTitle(act.getShareTitle());
			bo.setStartDate(act.getStartDate());
			bo.setStatus(act.getStatus());
			activityListService.update(bo);
			result = new ResponseResult(true, "编辑成功");
			logsb.append("activityListService update success");
			LOGGER.info(logsb.toString());
		} catch (Exception e) {
			LOGGER.error("编辑活动列表失败" + e);
			result = new ResponseResult(false, "编辑失败");
		}
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
	
	 /**
	 * 添加
	 * @param mobile
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	@RequestLogging("新增活动列表")
	public Object save(@Valid ActivityListRequest act,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		try {
			ActivityList bo = new ActivityList();
			bo.setActivityCode(act.getActivityCode());
			bo.setActivityEndImg(act.getActivityEndImg());
			bo.setActivityName(act.getActivityName());
			bo.setActivityImg(act.getActivityImg());
			bo.setActivityType(act.getActivityType());
			bo.setAppType(act.getAppType());
			bo.setEndDate(act.getEndDate());
			bo.setLinkUrl(act.getLinkUrl());
			bo.setShareDesc(act.getShareDesc());
			bo.setShareIcon(act.getShareIcon());
			bo.setShareLink(act.getShareLink());
			bo.setShareTitle(act.getShareTitle());
			bo.setStartDate(act.getStartDate());
			bo.setStatus(act.getStatus());
			activityListService.insert(bo);
			result = new ResponseResult(true, "添加成功");
			logsb.append("activityListService save success");
			LOGGER.info(logsb.toString());
		} catch (Exception e) {
			LOGGER.error("添加活动列表失败" + e);
			result = new ResponseResult(false, "添加失败");
		}
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		LOGGER.info(logsb.toString());
		return result;
	}
	
}
