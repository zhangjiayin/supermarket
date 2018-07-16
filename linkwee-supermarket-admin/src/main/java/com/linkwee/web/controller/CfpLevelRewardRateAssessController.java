package com.linkwee.web.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.model.CrmCfpPromotionCondition;
import com.linkwee.web.service.CrmCfpLevelRewardRateService;
import com.linkwee.web.service.CrmCfpPromotionConditionService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CrmCfpLevelRewardRateController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "crm/cfpLevelRewardRateAssess")
@RequestLogging("cfpLevelRewardRateAssess控制器")
public class CfpLevelRewardRateAssessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CfpLevelRewardRateAssessController.class);

	@Resource
	private CrmCfpLevelRewardRateService crmCfpLevelRewardRateService;
	@Resource
	private CrmCfpPromotionConditionService crmCfpPromotionConditionService;
	
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
    @RequestMapping(value="/view",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String acAccountBind(Model model) {
    	return "cfplanner/cfplevelrewardrate-assess";
    }
    
    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCfpPromotionConditionList(@RequestParam String  _dt_json) {
		LOGGER.debug("AcAccountBind list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = crmCfpPromotionConditionService.selectByDatatables(dataTable);
		return tableReturn;
	}


    /**
 	 * 更新理财师晋升条件
 	 * @param rows
 	 * @return
 	 * @throws Exception
 	 */
    @RequestMapping(value="/updateCfpLevelCondition")
 	@RequestLogging("更新理财师晋升条件")
    @ResponseBody
 	public ResponseResult updateCfpLevelCondition(@RequestParam String rows){
     	LOGGER.debug("更新理财师晋升条件-请求参数 CimOrginfoRequest = {}",JSON.toJSONString(rows));
     	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
     	@SuppressWarnings("unchecked")
		Map<String,CrmCfpLevelRewardRate> map =  (Map<String, CrmCfpLevelRewardRate>) df.getData();
    		long start = System.currentTimeMillis();
    		StringBuilder logsb = new StringBuilder();
    		ResponseResult result = null;
    		try {
    			for (String key : map.keySet()) {
    				CrmCfpPromotionCondition r = new CrmCfpPromotionCondition();
					BeanUtils.copyProperties(r, map.get(key));
//					r.setLevelCode(null);
					this.crmCfpPromotionConditionService.update(r);
				}
    			result = new ResponseResult(true, "更新理财师晋升条件成功！");
    			logsb.append("更新理财师晋升条件 success");
    		} catch (Exception e) {
    			logsb.append("更新理财师晋升条件 fail");
    			LOGGER.error("更新理财师晋升条件失败！", e);
    			result = new ResponseResult(false, "更新理财师晋升条件失败！");
    		}
    		long end = System.currentTimeMillis();
    		logsb.append("更新理财师晋升条件总耗时 |totaltime=").append(end - start).append("ms");
    		LOGGER.info(logsb.toString());
    		return result;
 	}
     
}
