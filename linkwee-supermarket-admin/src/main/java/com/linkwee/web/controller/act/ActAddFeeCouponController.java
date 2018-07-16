package com.linkwee.web.controller.act;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.User;
import com.linkwee.web.request.act.AddFeeCouponInfoRequest;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： ActAddFeeCouponController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月20日 17:11:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "act/actaddfeecoupon")
@RequestLogging("ActAddFeeCouponController控制器")
public class ActAddFeeCouponController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActAddFeeCouponController.class);

	@Resource
	private ActAddFeeCouponService actAddFeeCouponService;
	@Resource
	private CimOrginfoService cimOrginfoService;
	
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
    @RequestMapping(value="initPage")
    public String initPage(Model model) {
    	return "actaddfeecoupon/addfeecoupon-page";
    }
    
    @RequestMapping(value="list")
    @ResponseBody
    @RequestLogging("查看列表")
	public Object getAddFeeCouponList(@RequestParam String  _dt_json) {
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		return actAddFeeCouponService.getAddFeeCouponList(dataTable);
	}
    
    /**
     * 添加页面
     */
    @RequestMapping(value="addPage")
    public String getAddFeeCouponAddPage(Model model) {
    	CimOrginfo req = new CimOrginfo();
  		req.setStatus(1);
		model.addAttribute("platformList",cimOrginfoService.selectListByCondition(req));
    	return "actaddfeecoupon/addfeecoupon-add-page";
    }
    
    /**
     * 添加加拥券
     * @param addFeeCouponInfo
     * @param bindResult
     * @param session
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    @RequestLogging("添加加拥券")
	public Object addFeeCouponAdd(@Valid AddFeeCouponInfoRequest addFeeCouponInfo,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		User user = (User) session.getAttribute("userInfo"); 
    		addFeeCouponInfo.setOperator(user.getUsername());
    		actAddFeeCouponService.insertAddFeeCoupon(addFeeCouponInfo);
			return new ResponseResult(true,"添加成功");
		} catch (Exception e) {
			LOGGER.error("addFeeCouponAdd exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"添加失败");
	}
    
    /**
     * 修改页面
     */
    @RequestMapping(value="{couponId}/editPage")
    public String getAddFeeCouponEditPage(@PathVariable("couponId")String couponId,Model model) {
    	try {
    		if(StringUtils.isNotBlank(couponId)){
    			ActAddFeeCoupon temp = new ActAddFeeCoupon();
    			temp.setCouponId(couponId);
    			temp = actAddFeeCouponService.selectOne(temp);
    			CimOrginfo req = new CimOrginfo();
    	  		req.setStatus(1);
    			model.addAttribute("platformList",cimOrginfoService.selectListByCondition(req));
    			model.addAttribute("couponId", couponId);
    			model.addAttribute("addFeeCoupon", temp);
    		}
			
		}catch (ServiceException e) {
			LOGGER.error("addFeeCouponEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs",e.getMessage());
		}  catch (Exception e) {
			LOGGER.error("addFeeCouponEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs","查询加拥券失败");
		}
    	return "actaddfeecoupon/addfeecoupon-edit-page";
    }
    
    @RequestMapping(value="edit")
    @ResponseBody
    @RequestLogging("编辑加拥券")
	public Object redpacketEdit(@Valid AddFeeCouponInfoRequest addFeeCouponInfo,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		if(StringUtils.isBlank(addFeeCouponInfo.getCouponId()))return new ResponseResult(true,"不存在的加拥券");  		
			User user = (User) session.getAttribute("userInfo"); 
			addFeeCouponInfo.setOperator(user.getUsername());
			actAddFeeCouponService.updateAddFeeCoupon(addFeeCouponInfo);
			return new ResponseResult(true,"更新成功");
		}catch (ServiceException e) {
			LOGGER.error("addFeeCouponEdit exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("addFeeCouponEdit exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"更新失败");
	}
	
}
