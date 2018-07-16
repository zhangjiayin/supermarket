package com.linkwee.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.model.SmGrowthHandbookCfplevelRelation;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.request.HandbookRequest;
import com.linkwee.web.service.SmGrowthHandbookCfplevelRelationService;
import com.linkwee.web.service.SmGrowthHandbookClassifyService;
import com.linkwee.web.service.SmGrowthHandbookService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.HtmlFilterUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： SmGrowthHandbookController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "sm/smgrowthhandbook")
@RequestLogging("SmGrowthHandbookController控制器")
public class SmGrowthHandbookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmGrowthHandbookController.class);

	@Resource
	private SmGrowthHandbookService smGrowthHandbookService;
	
	@Resource
	private SmGrowthHandbookClassifyService smGrowthHandbookClassifyService;
	
	@Resource
	private SmGrowthHandbookCfplevelRelationService smGrowthHandbookCfplevelRelationService;
	
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
	 * 成长手册列表页
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/list")
	public ModelAndView handbookList() throws Exception{
		ModelAndView modelAndView = new ModelAndView("smgrowthhandbook/smgrowthhandbook-list");
		List<SmGrowthHandbookClassify> growthHandbookClassifyList = smGrowthHandbookClassifyService.selectListByCondition(null);
		modelAndView.addObject("growthHandbookClassifyList",growthHandbookClassifyList);
		return modelAndView;
	}

	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn handbookListAjax(HandbookRequest handbookRequest, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();		
		dataTableReturn = smGrowthHandbookService.findHandbookList(handbookRequest,dataTable);
		return dataTableReturn;
	}
	
	/**
	 * 转新增页
	 * @return
	 */
	@RequestMapping("tosave")
	public ModelAndView toSave(Integer id){
		ModelAndView modelAndView = new ModelAndView("smgrowthhandbook/smgrowthhandbookDtl");
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		if(null !=id && id>0){			
			SmGrowthHandbook handbook = smGrowthHandbookService.selectById(Long.valueOf(id));
			handbook.setTitle(HtmlFilterUtil.filterHtml(handbook.getTitle()));
			handbook.setImg(handbook.getImg());
			modelAndView.addObject("handbook",handbook);
			SmGrowthHandbookCfplevelRelation temp =  new SmGrowthHandbookCfplevelRelation();
			temp.setGrowthHandbookId(handbook.getId());
			List<SmGrowthHandbookCfplevelRelation> cfplevelRelations = smGrowthHandbookCfplevelRelationService.selectListByCondition(temp);
			List<String> cfpLevels = new ArrayList<String>();
			for(SmGrowthHandbookCfplevelRelation relation : cfplevelRelations){
				cfpLevels.add(relation.getCfpLevelCode());
			}
			modelAndView.addObject("cfpLevels",cfpLevels);
		}
		List<SmGrowthHandbookClassify> growthHandbookClassifyList = smGrowthHandbookClassifyService.selectListByCondition(null);
		modelAndView.addObject("growthHandbookClassifyList",growthHandbookClassifyList);
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Object save(HandbookRequest handbook){
		if(handbook.getAppType() == null || handbook.getAppType()<=0){
			handbook.setAppType(1);
		}
		ResponseResult result = null;
		ReturnCode returnCode =null;
		handbook.setTitle(HtmlFilterUtil.filterHtml(handbook.getTitle()));
		
		String currentUser = JSON.toJSONString(SecurityUtils.getSubject().getPrincipal());		
		handbook.setCreator(currentUser);
		
		if(handbook.getId()!=null && handbook.getId()>0){
			returnCode = smGrowthHandbookService.updateHandbook(convertHandbook(handbook));
		}
		else{
			returnCode = smGrowthHandbookService.saveHandbook(convertHandbook(handbook));
		}
		
		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, "操作成功");
		}else{
			result = new ResponseResult(false, "操作失败");
		}
		return result;
	}
	
	@RequestMapping("updateStatus")
	@ResponseBody
	public Object updateStatus(@RequestParam String id, @RequestParam String status){
		ResponseResult result = null;
		ReturnCode returnCode = smGrowthHandbookService.updateStatus(id,status);
		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, "更新成功");
		}else{
			result = new ResponseResult(false, "更新失败");
			
		}
		return result;
	}
	
	private HandbookRequest convertHandbook(HandbookRequest handbookrequest){
		HandbookRequest ret = new HandbookRequest();
		if(handbookrequest!=null){
			ret.setAppType(handbookrequest.getAppType());
			ret.setTypeCode(handbookrequest.getTypeCode());
			SmGrowthHandbookClassify classify = smGrowthHandbookClassifyService.selectById(Long.parseLong(handbookrequest.getTypeCode()));
			ret.setTypeName(classify.getName());
			String prefix = "<section style=\"padding:0 20px;\">";
			String endfix = "</section>";
			ret.setCreator(handbookrequest.getCreator());
			ret.setTitle(handbookrequest.getTitle());
			if(handbookrequest.getContent() != null && !handbookrequest.getContent().startsWith(prefix)){
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(prefix);
				stringBuilder.append(handbookrequest.getContent());
				stringBuilder.append(endfix);
				handbookrequest.setContent(stringBuilder.toString());
			}
			ret.setContent(handbookrequest.getContent());
			ret.setImg(handbookrequest.getImg());
			ret.setModifiyTime(DateUtils.format(new Date(), DateUtils.FORMAT_LONG));
			ret.setId(handbookrequest.getId());
			ret.setSummary(handbookrequest.getSummary());
			if(StringUtils.isNotBlank(handbookrequest.getLinkUrl())){
				ret.setLinkUrl(handbookrequest.getLinkUrl());
			}
			ret.setShareIcon(handbookrequest.getShareIcon());
			ret.setCfpLevel(handbookrequest.getCfpLevel());
			if(StringUtils.isBlank(handbookrequest.getSource())){
				ret.setSource("猎财大师");
			}else{
				ret.setSource(handbookrequest.getSource());
			}			
			ret.setReadingAmount(handbookrequest.getReadingAmount());
			ret.setStatus(handbookrequest.getStatus());
		}
		return ret;
	}
	
	@RequestMapping("/ueditor_config")
	@ResponseBody
	public Object ueditorConfig(){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("imageActionName","zimg");
		result.put("imageFieldName","userfile");
		result.put("imageAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
		return result;
	}

}
