package com.linkwee.web.controller.crm;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.User;
import com.linkwee.web.model.crm.SmBrandPromotion;
import com.linkwee.web.service.SmBrandPromotionService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： SmBrandPromotionController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月28日 18:47:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/smbrandpromotion")
@RequestLogging("SmBrandPromotionController控制器")
public class SmBrandPromotionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmBrandPromotionController.class);

	@Resource
	private SmBrandPromotionService smBrandPromotionService;
	
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

	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn getSmBrandPromotion(SmBrandPromotion smBrandPromotion, DataTable dataTable) throws Exception{
		dataTable.initOrders();
		smBrandPromotion.setUseType("1");
		DataTableReturn tableReturn = smBrandPromotionService.selectByDatatables(smBrandPromotion,dataTable);
		return tableReturn;
	}
	
	@RequestMapping("/bplist_ajax")
	@ResponseBody
	public DataTableReturn getSmBrandPromotionbp(SmBrandPromotion smBrandPromotion, DataTable dataTable) throws Exception{
		dataTable.initOrders();
		smBrandPromotion.setUseType("2");
		DataTableReturn tableReturn = smBrandPromotionService.selectByDatatables(smBrandPromotion,dataTable);
		return tableReturn;
	}
	
    /**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String smBrandPromotion(Model model) {
    	return "smbrandpromotion/smbrandpromotion-list";
    }
    
    /**
     * 查看列表
     */
    @RequestMapping(value="/bplist",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String smBrandPromotionList(Model model) {
    	return "smbrandpromotion/smbrandpromotion-bplist";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getSmBrandPromotions(@RequestParam String  _dt_json) {
		LOGGER.debug("SmBrandPromotion list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = smBrandPromotionService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping("save")
	@ResponseBody
	@RequestLogging("CUD操作")
	public Object save(@Valid SmBrandPromotion amb,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	User user = (User) session.getAttribute("userInfo");
    	amb.setCreator(user.getUsername());
    	ResponseResult result = null;
    	amb.setImage(amb.getImage());
    	amb.setCreateTime(new Date());
    	amb.setUseType("1");
		try {
			if(amb.getShowInx()==1){
				smBrandPromotionService.overheadSmBrandPromotion();
			}
			if(amb.getId()!=null && amb.getId().longValue()>0){
				smBrandPromotionService.update(amb);
			}else{
				smBrandPromotionService.insert(amb);
			}
		} catch (Exception e) {
			result = new ResponseResult(false, "操作失败");
		}
			
		result = new ResponseResult(true, "操作成功");
		
    	return result;
	}
    
    @RequestMapping("bpsave")
	@ResponseBody
	@RequestLogging("CUD操作")
	public Object bpsave(@Valid SmBrandPromotion amb,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	User user = (User) session.getAttribute("userInfo");
    	amb.setCreator(user.getUsername());
    	ResponseResult result = null;
    	amb.setImage(amb.getImage());
    	amb.setCreateTime(new Date());
    	amb.setUseType("2");
		try {
			if(amb.getShowInx()==1){
				smBrandPromotionService.overheadbpSmBrandPromotion();
			}
			if(amb.getId()!=null && amb.getId().longValue()>0){
				smBrandPromotionService.update(amb);
			}else{
				smBrandPromotionService.insert(amb);
			}
		} catch (Exception e) {
			result = new ResponseResult(false, "操作失败");
		}
			
		result = new ResponseResult(true, "操作成功");
		
    	return result;
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
			SmBrandPromotion amb = smBrandPromotionService.selectById(Long.parseLong(id));
			amb.setImage(systemConfigService.getImageUrl(amb.getImage()));	
			amb.setSmallImage(systemConfigService.getImageUrl(amb.getSmallImage()));
			model.addAttribute("smbrandpromotion",amb);
			model.addAttribute("actionType","edit");
		}
		return "smbrandpromotion/smbrandpromotion-edit";
	}
	
   /**
	 * 转编辑页
	 * @return
	 */
	@RequestMapping("bptoEdit")
	public String tobpUpdate(String id,Model model){
		String imgServerUrl = systemConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);
		model.addAttribute("img_server",imgServerUrl);
		if(StringUtils.isNotBlank(id)){
			SmBrandPromotion amb = smBrandPromotionService.selectById(Long.parseLong(id));
			amb.setImage(systemConfigService.getImageUrl(amb.getImage()));		
			model.addAttribute("smbrandpromotion",amb);
			model.addAttribute("actionType","edit");
		}
		return "smbrandpromotion/smbrandpromotion-bpedit";
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
			smBrandPromotionService.delete(Long.parseLong(id));
		} catch (Exception e) {
			result = new ResponseResult(false, "删除失败");
		}
		result = new ResponseResult(true, "删除成功");
		return result;
	}
	
}
