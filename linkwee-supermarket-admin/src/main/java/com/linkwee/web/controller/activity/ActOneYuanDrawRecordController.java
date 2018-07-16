package com.linkwee.web.controller.activity;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActOneYuanDrawRecord;
import com.linkwee.web.model.ActOneYuanDrawVirtualAddfourtuneRecord;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.request.PrizeSendRequest;
import com.linkwee.web.service.ActOneYuanDrawRecordService;
import com.linkwee.web.service.ActOneYuanDrawVirtualAddfourtuneRecordService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ActOneYuanDrawRecordController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月08日 13:46:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "act/actoneyuandrawrecord")
@RequestLogging("ActOneYuanDrawRecordController控制器")
public class ActOneYuanDrawRecordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActOneYuanDrawRecordController.class);

	@Resource
	private ActOneYuanDrawRecordService actOneYuanDrawRecordService;
	@Resource
	private ActivityListService activityListService; 
	@Resource
	private ActOneYuanDrawVirtualAddfourtuneRecordService addfourtuneRecordService;
	
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
    @RequestMapping("/list")
    @RequestLogging("查看列表页面")
    public String prizeList(Model model) {
    	return "actoneyuandrawrecord/actoneyuandrawrecord-list";
    }

    @RequestMapping("/list_ajax")
	@ResponseBody
	@RequestLogging("发放奖品列表")
	public DataTableReturn needSendPrizeListAjax(PrizeSendRequest prizeSendRequest, DataTable dataTable) throws Exception{ 	
    	ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null){
			prizeSendRequest.setActivityId(activity.getId().toString());
		}else{
			return null;
		}
    	DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn = actOneYuanDrawRecordService.queryPrizeSendList(prizeSendRequest,dataTable);
		return dataTableReturn;
	}
    
    /**
     * 发放
     * @param orderId
     * @return
     */
    @RequestMapping("/sendPrize")
    @RequestLogging("发放")
    @ResponseBody
    public ResponseResult sendPrize(Integer prizeId,Integer typeId) {
    	ResponseResult result = new ResponseResult();
    	
    	Subject currentUser = ThreadContext.getSubject();
    	String operater = currentUser != null?JSON.toJSONString(currentUser.getPrincipal()):null;
    	
		if(typeId == 1){
			try {
				//发放幸运奖品
				actOneYuanDrawRecordService.sendFortunePrize(prizeId,operater);
				result.setIsFlag(true);
				result.setMsg("发货成功");
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
				result.setIsFlag(false);
				result.setMsg("发货失败，请重试！");
			}
		}else if(typeId == 0){
			try {
				//发放普通奖品
				ActOneYuanDrawRecord record = new ActOneYuanDrawRecord();
				record.setId(prizeId);
				record.setIssued(1);
				record.setSendOperator(operater);
				actOneYuanDrawRecordService.update(record);
				result.setIsFlag(true);
				result.setMsg("发放成功");
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
				result.setIsFlag(false);
				result.setMsg("发放失败，请重试！");
			}
		}
		
		return result;
    }
    
    /**
     * 增加幸运积分页面
     */
    @RequestMapping("/toaddfourtune")
    @RequestLogging("增加幸运积值页面")
    public String toaddfourtune(Model model) {
    	return "actoneyuandrawrecord/fourtuneAdd";
    }

    @RequestMapping("/addfourtune")
	@ResponseBody
	@RequestLogging("增加幸运值")
	public ResponseResult addfourtune(Integer fourtuneAmount) throws Exception{ 	
    	ResponseResult result = new ResponseResult();
    	
    	Subject currentUser = ThreadContext.getSubject();
    	String operator = currentUser != null?JSON.toJSONString(currentUser.getPrincipal()):null;
    	String bizId = StringUtils.getUUID();
    	ActOneYuanDrawVirtualAddfourtuneRecord record = new ActOneYuanDrawVirtualAddfourtuneRecord();
    	record.setAddFourtune(fourtuneAmount);
    	record.setOperator(operator);
    	record.setCreateTime(new Date());
    	record.setBizId(bizId);
    	
    	List<BaseLottery> baseLotteryList = OneYuanDrawUtil.generateAwards(fourtuneAmount);
    	String userId = "xxxxxxxxxxxx";
    	Integer drawType = fourtuneAmount;
    	ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("one_yuan_draw");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity != null){
			try {
				//增加幸运值
				actOneYuanDrawRecordService.addfourtune(baseLotteryList,bizId,userId,drawType,activity.getId().toString());
				//记录增加幸运积值历史
				addfourtuneRecordService.insert(record);
				result.setIsFlag(true);
				result.setMsg("增加成功");
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
				result.setIsFlag(false);
				result.setMsg("增加失败，请重试！");
			}
			
		}else{
			result.setIsFlag(false);
			result.setMsg("活动不存在，请先录入活动！");
		}
    	
		return result;
	}
    
    @RequestMapping("/addFourtuneHistory")
	@ResponseBody
	@RequestLogging("增加幸运值历史列表")
	public DataTableReturn addFourtuneHistory(PrizeSendRequest prizeSendRequest, DataTable dataTable) throws Exception{ 	
    	DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn = addfourtuneRecordService.addFourtuneHistory(prizeSendRequest,dataTable);
		return dataTableReturn;
	}
	
}
