package com.linkwee.web.controller.acc;



import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ActMessageConfigController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月02日 15:13:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "messageConfit")
@RequestLogging("ActMessageConfigController控制器")
public class ActMessageConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActMessageConfigController.class);

	@Resource
	private SysConfigService sysConfigService;
	
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
    @RequestMapping(value="page")
    public String initPage(ModelMap model) {
    	model.addAttribute("messageVal",sysConfigService.getValuesByKey("change_message_channel"));
    	return "acaccountbind/acmessageconfig-page";
    }

    
    /**
     * 设置消息通道
     */
    @RequestMapping(value="setMessagePlatform")
    @ResponseBody
    public Object setMainPlatform(@RequestParam("messageId")String messageId,HttpSession session) {
    	try {
    		sysConfigService.updateSysConfigByKey(SysConfigConstant.CHANGE_MESSAGE_CHANNEL, messageId, new Date());
			
		} catch (Exception e) {
			LOGGER.info("设置消息通道失败!");
			return new ResponseResult(false,"设置失败");
		}
		return new ResponseResult(true,"设置成功");
    }
    
    
}
