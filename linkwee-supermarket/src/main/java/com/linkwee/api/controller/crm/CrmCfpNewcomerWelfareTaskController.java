package com.linkwee.api.controller.crm;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.response.crm.CrmCfpNewcomerWelfareTaskResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.model.CrmCfpNewcomerWelfareTask;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CrmCfpNewcomerWelfareTaskController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月13日 14:46:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/cfpnewcomerwelfaretask")
@RequestLogging("猎财大师新手福利六连送")
public class CrmCfpNewcomerWelfareTaskController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpNewcomerWelfareTaskController.class);

	@Resource
	private CrmCfpNewcomerWelfareTaskService crmCfpNewcomerWelfareTaskService;
	
	/**
	 * 新手福利六连送任务完成状态
	 * @param head
	 * @return
	 */
	@RequestLogging("新手福利六连送任务完成状态")
	@RequestMapping("finishStatus/4.3.0")
	@ResponseBody
	public BaseResponse finishStatus(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmCfpNewcomerWelfareTask req = new CrmCfpNewcomerWelfareTask();
		req.setUserId(userId);
		CrmCfpNewcomerWelfareTask rlt = crmCfpNewcomerWelfareTaskService.selectOne(req);
		if(rlt == null) {
			LOGGER.info("不是新手福利六连送上线后的用户");
			return AppResponseUtil.getErrorBusi("13800", "不是新手福利六连送上线后的用户");
		}
		return AppResponseUtil.getSuccessResponse(rlt,CrmCfpNewcomerWelfareTaskResponse.class);
	}
	
	@RequestLogging("新手福利六连送任务终极大奖")
	@RequestMapping("sendFinalPrize/4.3.0")
	@ResponseBody
	public BaseResponse sendFinalPrize(AppRequestHead head) {
		try {
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			CrmCfpNewcomerWelfareTask temp = new CrmCfpNewcomerWelfareTask();
			temp.setUserId(userId);
			temp.setRegStatus(1);
			temp.setBindcardStatus(1);
			temp.setInvestStatus(1);
			temp.setInviteCfpStatus(1);
			temp.setInviteCfpInvestStatus(1);
			CrmCfpNewcomerWelfareTask hasFinishOtherTask = crmCfpNewcomerWelfareTaskService.selectOne(temp);
			if(hasFinishOtherTask != null){
				crmCfpNewcomerWelfareTaskService.sendTaskReward(userId, CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_ALL);
				return AppResponseUtil.getSuccessResponse();
			}else{
				return AppResponseUtil.getErrorBusi("100011", "请先完成其他新手福利任务");
			}
		} catch (Exception e) {
			LOGGER.info("领取新手福利六连送任务终极大奖异常 ： " + e);
			return AppResponseUtil.getErrorBusi(e.getMessage(), "网络繁忙，请联系客服");
		}
	}
	
}
