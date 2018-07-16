package com.linkwee.api.controller.act;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.act.RedpacketRequest;
import com.linkwee.api.request.act.SendRedPacketRequest;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.ActPersonAddfeeTicketService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("api/redPacket")
@RequestLogging("红包")
public class RedpacketController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedpacketController.class);
	
	@Autowired
	private RedPacketService redPacketService;
	@Resource
	private ActAddFeeCouponService addFeeCouponService;
	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;
	@Resource
	private ActPersonAddfeeTicketService actPersonAddfeeTicketService;
	
	
	@RequestMapping("queryRedPacket")
	@ResponseBody
	@RequestLogging("查询红包")
	public Object queryRedPacket(@Valid RedpacketRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			if(AppUtils.isChannelApp(head.getOrgNumber())){
				 return AppResponseUtil.getSuccessResponse(redPacketService.queryCfplannerRedPacket(userId, req));
			}
			else if(AppUtils.isInvestorApp(head.getOrgNumber())){
				return  AppResponseUtil.getSuccessResponse(redPacketService.queryInvestorRedPacket(userId, req));
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("queryRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	@RequestMapping("queryAvailableRedPacket")
	@ResponseBody
	@RequestLogging("查询可用红包")
	public Object queryAvailableRedPacket(@Valid RedpacketRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());			
			Page<RedpacketResponse> page  = new Page<RedpacketResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
			PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
			List<RedpacketResponse> redpackets = Collections.emptyList();
			if(ObjectUtils.equals(req.getType(), 1))
				redpackets = redPacketService.patformRedPacket(userId, req, page);
			else if(ObjectUtils.equals(req.getType(), 2))
				redpackets = redPacketService.productRedPacket(userId, req, page);
			paginatorResponse.setDatas(redpackets);
			paginatorResponse.setValuesByPage(page);
			return  AppResponseUtil.getSuccessResponse(paginatorResponse);			
		}
		catch(Exception e){
			LOGGER.error("queryAvailableRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	
	@RequestMapping("sendRedPacket")
	@ResponseBody
	@RequestLogging("发放红包")
	public Object sendRedPacket(@Valid SendRedPacketRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) return AppResponseUtil.getErrorParams(result);
		try{
			return (AppUtils.isChannelApp(head.getOrgNumber()) ? redPacketService.sendRedPacket(JsonWebTokenHepler.getUserIdByToken(head.getToken()), req) :AppResponseUtil.getErrorBusi("100002","请使用正确的app类型"));
		}
		catch(Exception e){
			LOGGER.error("sendRedPacket exception", e);
		}
		return new BaseResponse(-1,"发放失败，请联系客服");
	}
	
	@RequestMapping("queryRedPacket/4.0")
	@ResponseBody
	@RequestLogging("查询红包列表4.0")
	public BaseResponse queryRedPacket4(RedpacketRequest redpacketRequest,AppRequestHead head){
		LOGGER.info("查询红包列表4.0,redpacketRequest={}",JSONObject.toJSONString(redpacketRequest));
		try{
			redpacketRequest.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
			if(AppUtils.isChannelApp(head.getOrgNumber())){//理财师端
				 return AppResponseUtil.getSuccessResponse(redPacketService.queryCfplannerRedPacket4(redpacketRequest));
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("queryRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}

	@RequestMapping("queryRedPacketCount/4.0")
	@ResponseBody
	@RequestLogging("查询红包列表数量4.0")
	public BaseResponse queryRedPacketCount4(AppRequestHead head){
		LOGGER.info("查询红包列表数量4.0.1");
		try{
			RedpacketRequest redpacketRequest = new RedpacketRequest();
			redpacketRequest.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
			if(AppUtils.isChannelApp(head.getOrgNumber())){//理财师端
				 Map<String, Integer> returnMap = new HashMap<String, Integer>();
				 redpacketRequest.setType(1);
				 returnMap.put("investRedPacketCount", redPacketService.queryRedPacketCount4(redpacketRequest));
				 redpacketRequest.setType(2);
				 returnMap.put("sendRedPacketCount", redPacketService.queryRedPacketCount4(redpacketRequest));
				 return AppResponseUtil.getSuccessResponse(returnMap);
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("queryRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	@RequestMapping("sendRedPacketToCfp/4.5.0")
	@ResponseBody
	@RequestLogging("转发红包4.5.0")
	public Object sendRedPacket5(@Valid SendRedPacketRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) return AppResponseUtil.getErrorParams(result);
		try{
			return (AppUtils.isChannelApp(head.getOrgNumber()) ? redPacketService.sendRedPacketToCfp(JsonWebTokenHepler.getUserIdByToken(head.getToken()), req) :AppResponseUtil.getErrorBusi("100002","请使用正确的app类型"));
		}
		catch(Exception e){
			LOGGER.error("sendRedPacket exception", e);
		}
		return new BaseResponse(-1,"发放失败，请联系客服");
	}
	
	@RequestMapping("queryRedPacket/4.5.0")
	@ResponseBody
	@RequestLogging("查询红包列表4.5.0")
	public BaseResponse queryRedPacket5(PaginatorRequest request, AppRequestHead head){
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			if(AppUtils.isChannelApp(head.getOrgNumber())){//理财师端
				 return AppResponseUtil.getSuccessResponse(redPacketService.queryCfplannerRedPacket5(request,userId));
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("queryRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
	
	@RequestMapping("queryCouponCount/4.5.0")
	@ResponseBody
	@RequestLogging("查询可使用优惠券数量4.5.0")
	public BaseResponse queryCouponCount(AppRequestHead head){
		LOGGER.info("查询可使用优惠券数量4.5.0");
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			if(AppUtils.isChannelApp(head.getOrgNumber())){//理财师端
				 Map<String, Integer> returnMap = new HashMap<String, Integer>();
				 int useableRedPacketCount = redPacketService.queryRedPacketCount5(userId);
				 returnMap.put("useableRedPacketCount", useableRedPacketCount);
				 int useableAddFeeCouponCount = addFeeCouponService.queryAddFeeCouponCount();
				 returnMap.put("useableAddFeeCouponCount", useableAddFeeCouponCount);
				 int useablePersonAddfeeTicketCount = actPersonAddfeeTicketService.queryPersonAddfeeTicket(userId, 1).size();
				 returnMap.put("useablePersonAddfeeTicketCount", useablePersonAddfeeTicketCount);
				 int useableJobGradeCouponCount = actJobGradeVoucherService.queryCanUserJobGradeVoucher(userId);
				 returnMap.put("useableJobGradeCouponCount", useableJobGradeCouponCount);
				 return AppResponseUtil.getSuccessResponse(returnMap);
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("queryRedPacket exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
}
