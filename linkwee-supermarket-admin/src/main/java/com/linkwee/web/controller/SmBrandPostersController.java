package com.linkwee.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.SmBrandPosters;
import com.linkwee.web.model.User;
import com.linkwee.web.response.acc.SmBrandPosterResponse;
import com.linkwee.web.service.SmBrandPostersService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： SmBrandPostersController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年01月06日 15:13:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/smbrandposters")
@RequestLogging("SmBrandPostersController控制器")
public class SmBrandPostersController {


	@Resource
	private SmBrandPostersService smBrandPostersService;
	
	@Resource
	private SysConfigService systemConfigService; 
	
	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn getSmBrandPromotion(SmBrandPosters smBrandPromotion, DataTable dataTable) throws Exception{
		dataTable.initOrders();
		if(dataTable!=null&&dataTable.getLength()==0){
			dataTable.setLength(10);
			dataTable.setStart(0);
			dataTable.setDraw(1);
		}
		DataTableReturn tableReturn = smBrandPostersService.selectByDatatables(smBrandPromotion,dataTable);
		return tableReturn;
	}
	
    /**
	 * 转编辑页
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toUpdate(String id,Model model){
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		model.addAttribute("img_server",imgServerUrl);
		if(StringUtils.isNotBlank(id)){
			SmBrandPosters amb = smBrandPostersService.selectById(Long.parseLong(id));
			amb.setImage(systemConfigService.getImageUrl(amb.getImage()));	
			amb.setSmallImage(systemConfigService.getImageUrl(amb.getSmallImage()));
			model.addAttribute("smBrandPosters",amb);
			model.addAttribute("actionType","edit");
		}
		List<SmBrandPosterResponse> typeList = smBrandPostersService.selectBrandPosterList();
		model.addAttribute("typeList",typeList);
		return "smbrandposters/smbrandposters-edit";
	}
	
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
	
    
    @RequestMapping("init")
	public String smBrandPosters(){
		return "smbrandposters/smbrandposters-list";
	}

    @RequestMapping("save")
   	@ResponseBody
   	@RequestLogging("CUD操作")
   	public Object save(@Valid SmBrandPosters amb,BindingResult bindResult,HttpSession session) {
       	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
           }
       	User user = (User) session.getAttribute("userInfo");
       	amb.setCreator(user.getUsername());
       	ResponseResult result = null;
       	amb.setImage(amb.getImage());
       	amb.setCreateTime(new Date());
   		try {
   			if(amb.getShowInx()==1){
   				smBrandPostersService.overheadSmBrandPosters(amb.getTypeValue());
   			}
   			if(amb.getId()!=null && amb.getId().longValue()>0){
   				smBrandPostersService.update(amb);
   			}else{
   				smBrandPostersService.insert(amb);
   			}
   		} catch (Exception e) {
   			result = new ResponseResult(false, "操作失败");
   		}
   			
   		result = new ResponseResult(true, "操作成功");
   		
       	return result;
   	}


	 /**
	 * 删除
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public Object del(@RequestParam String id ){
		ResponseResult result = null;
		try {
			smBrandPostersService.delete(Long.parseLong(id));
		} catch (Exception e) {
			result = new ResponseResult(false, "删除失败");
		}
		result = new ResponseResult(true, "删除成功");
		return result;
	}
	
}
