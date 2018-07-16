package com.linkwee.api.controller.act;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.act.ActPersonAddfeeTicketRequest;
import com.linkwee.api.response.act.ActPersonAddfeeTicketExtendsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.web.service.ActPersonAddfeeTicketService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ActPersonAddfeeTicketController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 17:24:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping("api/personaddfeeticket")
@RequestLogging("个人加拥券")
public class ActPersonAddfeeTicketController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActPersonAddfeeTicketController.class);

	@Resource
	private ActPersonAddfeeTicketService actPersonAddfeeTicketService;
	
	@RequestMapping("/myaddfeeticket")
	@ResponseBody
	@RequestLogging("个人加拥券分页列表")
	public BaseResponse myAddFeeTicket(ActPersonAddfeeTicketRequest actPersonAddfeeTicketRequest, AppRequestHead head){
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			if(AppUtils.isChannelApp(head.getOrgNumber())){//理财师端
				actPersonAddfeeTicketRequest.setUserId(userId);
				LOGGER.info("获取个人加拥券分页列表  ActPersonAddfeeTicketRequest={}",JSONObject.toJSONString(actPersonAddfeeTicketRequest));
				PaginatorResponse<ActPersonAddfeeTicketExtendsResponse> responseList = actPersonAddfeeTicketService.myAddFeeTicket(actPersonAddfeeTicketRequest);
				return AppResponseUtil.getSuccessResponse(responseList);
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("person add fee ticket pageList exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}	
}
