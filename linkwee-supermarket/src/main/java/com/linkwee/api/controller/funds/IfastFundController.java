package com.linkwee.api.controller.funds;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.funds.ifast.BatchGetFundExtendsRequest;
import com.linkwee.api.request.funds.ifast.FundDetailGotoRequest;
import com.linkwee.api.request.funds.ifast.GetInvestorHoldingsRequest;
import com.linkwee.api.request.funds.ifast.GetInvestorOrderInfoRequest;
import com.linkwee.api.request.funds.ifast.GetOrderListRequest;
import com.linkwee.api.request.funds.ifast.GotoBaseRequest;
import com.linkwee.api.request.funds.ifast.HoldingsStatisticRequest;
import com.linkwee.api.request.funds.ifast.IfRegisterRequest;
import com.linkwee.api.request.funds.ifast.QuickCustomerMigrationRequest;
import com.linkwee.api.response.funds.ifast.IfRegisterResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.web.service.IfastFundService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping(value = "/api/funds/ifast")
@RequestLogging("奕丰金融")
public class IfastFundController extends BaseController{
	
	private static Logger LOGGER = LoggerFactory.getLogger(IfastFundController.class);
	
	@Resource
	private IfastFundService ifastFundService;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private SysConfigService sysConfigService;
	
	@ResponseBody
	@RequestMapping("/fundSift")
	@RequestLogging("精选基金")
	public BaseResponse fundSift(BatchGetFundExtendsRequest batchGetFundExtendsRequest){
		
		String fundSiftCode = sysConfigService.getValuesByKey("fund_list_code");//可多个，基金代码直接以逗号分隔，如fundCodes=482002,219003
		if(StringUtils.isNotBlank(fundSiftCode)){
			batchGetFundExtendsRequest.setFundCodes(fundSiftCode.trim());
			batchGetFundExtendsRequest.setPeriod("sevenDaysAnnualizedYield");//七日年化
			batchGetFundExtendsRequest.setSort("DESC");//DESC:降序，默认；ASC:升序
			batchGetFundExtendsRequest.setPageSize("1");//只查询1条
			batchGetFundExtendsRequest.setPageIndex("1");
		} else {
			batchGetFundExtendsRequest.setIsMMFund("0");//0-货币型基金 1-非货币型基金
			batchGetFundExtendsRequest.setIsRecommended("0");//0-奕丰推荐 1-非奕丰推荐
			batchGetFundExtendsRequest.setPeriod("sevenDaysAnnualizedYield");//七日年化
			batchGetFundExtendsRequest.setSort("DESC");//DESC:降序，默认；ASC:升序
			batchGetFundExtendsRequest.setPageSize("1");//只查询1条
			batchGetFundExtendsRequest.setPageIndex("1");
		}
		LOGGER.info("【奕丰金融】获取精选基金信息	batchGetFundExtendsRequest={}",JSONObject.toJSONString(batchGetFundExtendsRequest));
        return ifastFundService.fundSift(batchGetFundExtendsRequest);
	}
	
	@ResponseBody
	@RequestMapping("/batchGetFundInfo")
	@RequestLogging("批量获取基金信息")
	public BaseResponse batchGetFundInfo(BatchGetFundExtendsRequest batchGetFundExtendsRequest){
		LOGGER.info("【奕丰金融】批量获取基金信息	batchGetFundExtendsRequest={}",JSONObject.toJSONString(batchGetFundExtendsRequest));
		
		String fundListCode = sysConfigService.getValuesByKey("fund_list_code");//可多个，基金代码直接以逗号分隔，如fundCodes=482002,219003
		if(StringUtils.isNotBlank(fundListCode)){
			batchGetFundExtendsRequest.setFundType(null);
			batchGetFundExtendsRequest.setFundCodes(fundListCode.trim());
		} else {
			//判断是基金名称查询还是基金code查询
			batchGetFundExtendsRequest.setSearch(batchGetFundExtendsRequest.getQueryCodeOrName());
		}
        return ifastFundService.batchGetFundInfo(batchGetFundExtendsRequest);
	}
	
	@ResponseBody
	@RequestMapping("/getHoldingsStatistic")
	@RequestLogging("获取账户持有总资产")
	public BaseResponse getHoldingsStatistic(AppRequestHead head,HoldingsStatisticRequest holdingsStatisticRequest){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
		if(!"undefined".equals(userId)){
			if(crmOrgAcctRelService.ifOrgAccountExist(userId, holdingsStatisticRequest.getOrgCode())){
				holdingsStatisticRequest.setUserId(userId);
				LOGGER.info("【奕丰金融】获取账户持有总资产	holdingsStatisticRequest={}",JSONObject.toJSONString(holdingsStatisticRequest));
				return ifastFundService.getHoldingsStatistic(holdingsStatisticRequest);
			} else {
				return AppResponseUtil.getErrorData("-200000", "奕丰基金账户不存在");
			}
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/getInvestorHoldings")
	@RequestLogging("获取账户持有资产")
	public BaseResponse getInvestorHoldings(AppRequestHead head,GetInvestorHoldingsRequest getInvestorHoldingsRequest){
//		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
//		if(!"undefined".equals(userId)){
//			if(crmOrgAcctRelService.ifOrgAccountExist(userId, getInvestorHoldingsRequest.getOrgCode())){		
//				getInvestorHoldingsRequest.setUserId(userId);
//				LOGGER.info("【奕丰金融】获取账户持有资产	getInvestorHoldingsRequest={}",JSONObject.toJSONString(getInvestorHoldingsRequest));
//				return ifastFundService.getInvestorHoldings(getInvestorHoldingsRequest);
//			} else {
//				return AppResponseUtil.getErrorData("-200000", "奕丰基金账户不存在");
//			}
//		} else {
//		    return AppResponseUtil.getErrorToken();
//		}
		return AppResponseUtil.getErrorData("-1", "基金信息暂时不可用哦,请稍后再试");
	}
	
	@ResponseBody
	@RequestMapping("/getInvestorHoldingsNew")
	@RequestLogging("获取账户持有资产-只返回持有list")
	public BaseResponse getInvestorHoldingsNew(AppRequestHead head,GetInvestorHoldingsRequest getInvestorHoldingsRequest){
//		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
//		if(!"undefined".equals(userId)){
//			if(crmOrgAcctRelService.ifOrgAccountExist(userId, getInvestorHoldingsRequest.getOrgCode())){		
//				getInvestorHoldingsRequest.setUserId(userId);
//				LOGGER.info("【奕丰金融】获取账户持有资产	getInvestorHoldingsRequest={}",JSONObject.toJSONString(getInvestorHoldingsRequest));
//				return ifastFundService.getInvestorHoldingsNew(getInvestorHoldingsRequest);
//			} else {
//				return AppResponseUtil.getErrorData("-200000", "奕丰基金账户不存在");
//			}
//		} else {
//		    return AppResponseUtil.getErrorToken();
//		}
		return AppResponseUtil.getErrorData("-1", "基金信息暂时不可用哦,请稍后再试");
	}
	
	@ResponseBody
	@RequestMapping("/getInvestorOrderInfo")
	@RequestLogging("获取用户的订单详情")
	public BaseResponse getInvestorOrderInfot(AppRequestHead head,GetInvestorOrderInfoRequest getInvestorOrderInfoRequest){
//		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
//		if(!"undefined".equals(userId)){		
//			if(crmOrgAcctRelService.ifOrgAccountExist(userId, getInvestorOrderInfoRequest.getOrgCode())){						
//				getInvestorOrderInfoRequest.setUserId(userId);
//				LOGGER.info("【奕丰金融】获取用户的订单详情	getInvestorOrderInfo={}",JSONObject.toJSONString(getInvestorOrderInfoRequest));
//		        return ifastFundService.getInvestorOrderInfo(getInvestorOrderInfoRequest);
//			} else {
//				return AppResponseUtil.getErrorData("-200000", "奕丰基金账户不存在");
//			}
//		} else {
//		    return AppResponseUtil.getErrorToken();
//		}
		return AppResponseUtil.getErrorData("-1", "基金信息暂时不可用哦,请稍后再试");
	}
	
	@ResponseBody
	@RequestMapping("/getOrderList")
	@RequestLogging("获取用户的订单列表")
	public BaseResponse getOrderList(AppRequestHead head,GetOrderListRequest getOrderListRequest){
//		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
//		if(!"undefined".equals(userId)){		
//			if(crmOrgAcctRelService.ifOrgAccountExist(userId, getOrderListRequest.getOrgCode())){						
//				getOrderListRequest.setUserId(userId);
//				LOGGER.info("【奕丰金融】获取用户的订单列表	getOrderListRequest={}",JSONObject.toJSONString(getOrderListRequest));
//		        return ifastFundService.getOrderList(getOrderListRequest);
//			} else {
//				return AppResponseUtil.getErrorData("-200000", "奕丰基金账户不存在");
//			}
//		} else {
//		    return AppResponseUtil.getErrorToken();
//		}
		return AppResponseUtil.getErrorData("-1", "基金信息暂时不可用哦,请稍后再试");
	}
	
	@ResponseBody
	@RequestMapping("/quickCustomerMigration")
	@RequestLogging("奕丰基金注册")
	public BaseResponse quickCustomerMigration(AppRequestHead head,QuickCustomerMigrationRequest quickCustomerMigrationRequest){
//		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
//		if(!"undefined".equals(userId)){
//			quickCustomerMigrationRequest.setUserId(userId);
//			LOGGER.info("【奕丰金融】注册	quickCustomerMigrationRequest={}",JSONObject.toJSONString(quickCustomerMigrationRequest));
//	        return ifastFundService.quickCustomerMigration(quickCustomerMigrationRequest);
//		} else {
//		    return AppResponseUtil.getErrorToken();
//		}
		return AppResponseUtil.getErrorData("-1", "基金信息暂时不可用哦,请稍后再试");
	}
	
	@ResponseBody
	@RequestMapping("/ifRegister")
	@RequestLogging("奕丰基金是否注册")
	public BaseResponse ifRegister(AppRequestHead head,IfRegisterRequest ifRegisterRequest){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
		if(!"undefined".equals(userId)){
			//判断用户是否存在
			crmOrgAcctRelService.ifOrgAccountExist(userId, ifRegisterRequest.getOrgCode());
			return AppResponseUtil.getSuccessResponse(new IfRegisterResponse(true));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/gotoIndex")
	@RequestLogging("奕丰基金首页跳转")
	public BaseResponse gotoIndex(AppRequestHead head,GotoBaseRequest gotoBaseRequest){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
		if(!"undefined".equals(userId)){
			crmOrgAcctRelService.ifOrgAccountExist(userId, gotoBaseRequest.getOrgCode());
			gotoBaseRequest.setUserId(userId);
			LOGGER.info("【奕丰金融】首页跳转		gotoIndexRequest={}",JSONObject.toJSONString(gotoBaseRequest));
			return AppResponseUtil.getSuccessResponse(ifastFundService.gotoIndex(gotoBaseRequest));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/gotoFundDetail")
	@RequestLogging("奕丰基金详情页跳转")
	public BaseResponse gotoFundDetail(AppRequestHead head,FundDetailGotoRequest fundDetailGotoRequest){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
		if(!"undefined".equals(userId)){
			crmOrgAcctRelService.ifOrgAccountExist(userId, fundDetailGotoRequest.getOrgCode());					
			fundDetailGotoRequest.setUserId(userId);
			LOGGER.info("【奕丰金融】基金详情页跳转	fundDetailGotoRequest={}",JSONObject.toJSONString(fundDetailGotoRequest));
			return AppResponseUtil.getSuccessResponse(ifastFundService.gotoFundDetail(fundDetailGotoRequest));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/gotoAccount")
	@RequestLogging("奕丰基金个人资产页跳转")
	public BaseResponse gotoAccount(AppRequestHead head,GotoBaseRequest gotoBaseRequest){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId        
		if(!"undefined".equals(userId)){			
			crmOrgAcctRelService.ifOrgAccountExist(userId, gotoBaseRequest.getOrgCode());					
			gotoBaseRequest.setUserId(userId);
			LOGGER.info("【奕丰金融】个人资产页跳转		gotoIndexRequest={}",JSONObject.toJSONString(gotoBaseRequest));
			return AppResponseUtil.getSuccessResponse(ifastFundService.gotoAccount(gotoBaseRequest));
		} else {
		    return AppResponseUtil.getErrorToken();
		}
	}
	
	@ResponseBody
	@RequestMapping("/baseDefined")
	@RequestLogging("奕丰基金基础定义")
	public BaseResponse baseDefined(AppRequestHead head){    
		return AppResponseUtil.getSuccessResponse(ifastFundService.baseDefined(head));
	}	
}
