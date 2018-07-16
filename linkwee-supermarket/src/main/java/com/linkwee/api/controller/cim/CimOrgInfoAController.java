package com.linkwee.api.controller.cim;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.linkwee.api.response.cim.*;
import com.linkwee.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.CimProductUnrecordInvestRequest;
import com.linkwee.api.request.cim.CimSunburnPageListRequest;
import com.linkwee.api.request.cim.CimThumbsUpRequest;
import com.linkwee.api.request.cim.OrginfoaDetailRequest;
import com.linkwee.api.request.cim.OrginfoaListRequest;
import com.linkwee.api.request.cim.OrginfoaPageListRequest;
import com.linkwee.api.request.crm.BindOrgAcctRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.enums.RequestTypeEnums;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.model.CrmOrgAcctRelA;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.model.cim.CimUnrecordInvestListResp;
import com.linkwee.web.request.orgInfo.OrgUrlSkipParameterRequest;
import com.linkwee.web.service.CimOrgInfoAService;
import com.linkwee.web.service.CimProductUnrecordInvestService;
import com.linkwee.web.service.CrmOrgAcctRelAService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysGrayReleaseService;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.OpenHttpUtils;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimOrgInfoAController控制器
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月09日 16:44:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "api/orginfoa")
@RequestLogging("A平台机构")
public class CimOrgInfoAController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgInfoAController.class);

	@Resource
	private CimOrgInfoAService cimOrgInfoAService;
	@Resource
	private SysGrayReleaseService sysGrayReleaseService;
	@Resource
	private CimProductUnrecordInvestService cimProductUnrecordInvestService;
    @Resource
	private CrmOrgAcctRelAService orgAcctRelAService;
    @Resource
    private SysThirdkeyConfigService sysThirdkeyConfigService;
    @Resource
    private CrmUserInfoService crmUserInfoService;
	
	@ResponseBody
	@RequestLogging("机构列表")
	@RequestMapping("/pageList")
	public BaseResponse orginfoaPageList(AppRequestHead appRequestHead,OrginfoaPageListRequest orginfoaPageListRequest){
		orginfoaPageListRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()), "0,1"));
		LOGGER.info("查询A平台机构列表  orginfoaPageListRequest={}",JSONObject.toJSONString(orginfoaPageListRequest));
		PaginatorResponse<OrginfoaPageListResponse> rlt = cimOrgInfoAService.queryPageList(orginfoaPageListRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	@ResponseBody
	@RequestLogging("机构详情")
	@RequestMapping("/orginfoaDetail")
	public BaseResponse orginfoaDetail(AppRequestHead appRequestHead,OrginfoaDetailRequest orginfoaDetailRequest){
		LOGGER.info("查询A平台机构详情  orginfoaDetailRequest={}",JSONObject.toJSONString(orginfoaDetailRequest));
		orginfoaDetailRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()), "0,1"));
		OrginfoaDetailResponse orginfoaDetailResponse = cimOrgInfoAService.queryOrginfoaDetail(orginfoaDetailRequest);
		return AppResponseUtil.getSuccessResponse(orginfoaDetailResponse);
	}
	
	@ResponseBody
	@RequestLogging("所有机构")
	@RequestMapping("/orginfoaList")
	public BaseResponse queryOrginfoaList(AppRequestHead appRequestHead,OrginfoaListRequest orginfoaListRequest){
		orginfoaListRequest.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()), "0,1"));
		LOGGER.info("查询A平台机构  orginfoaListRequest={}",JSONObject.toJSONString(orginfoaListRequest));
		List<CimOrgInfoA> cimOrgInfoAList = cimOrgInfoAService.queryOrginfoaList(orginfoaListRequest);
		return AppResponseUtil.getSuccessResponse(cimOrgInfoAList);
	}
	
	/**
	 * 报单明细
	 * @param result
	 * @param head
	 * @param
	 * @return
	 */
	@RequestMapping("/unrecordPageList")
	@ResponseBody
	public BaseResponse unrecordPageList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Page<CimUnrecordInvestListResp> page  = new Page<CimUnrecordInvestListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CimUnrecordInvestListResp> rlt = cimProductUnrecordInvestService.unrecordPageList(req.getOrder(),userId,page);
		return AppResponseUtil.getSuccessResponse(rlt, CimUnrecordInvestListResponse.class);	
	}
	
	/**
	 * 报单
	 * @param result
	 * @param head
	 * @param
	 * @return
	 */
	@RequestMapping("/reportRecord")
	@ResponseBody
	public BaseResponse reportRecord(@Valid CimProductUnrecordInvestRequest cimProductUnrecordInvestRequest,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		cimProductUnrecordInvestRequest.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		return cimProductUnrecordInvestService.reportRecord(cimProductUnrecordInvestRequest);
	}
	
   /**
    * 4.6.3晒单列表
	* type 0所以报单列表  1个人报单详情列表 
    * @param
    * @return
    */
	@RequestMapping("/sunburnPageList")
	@ResponseBody
	public BaseResponse sunburnPageList(AppRequestHead appRequestHead,CimSunburnPageListRequest request){
		PaginatorResponse<CimSunburnListResponse> rlt = cimProductUnrecordInvestService.sunburnPageList(appRequestHead,request);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
    * 点赞次数
    * @return
    */
	@RequestMapping("/thumbsUp")
	@ResponseBody
	public BaseResponse thumbsUp(CimThumbsUpRequest req){
		cimProductUnrecordInvestService.thumbsUp(req.getId());
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
    * 晒单
    * @return
    */
	@RequestMapping("/sunburn")
	@ResponseBody
	public BaseResponse sunburn(CimThumbsUpRequest req,AppRequestHead head){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		cimProductUnrecordInvestService.sunburn(req.getId(),userId,req.getImage());
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 获取A专区累计返现金额
	 */
	@RequestMapping("/orgAtotalAmt")
	@ResponseBody
	public BaseResponse orgAtotalAmount(AppRequestHead head) throws Exception {
		CimSunburnResponse rlt = new CimSunburnResponse();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Double rewardIncome = cimProductUnrecordInvestService.orgAtotalAmount(userId);
		rlt.setTotalAmt(NumberUtils.getFormat(rewardIncome, "0.00"));
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
    * 4.6.5晒单详情
    * @return
    */
	@RequestMapping("/sunburnDetail")
	@ResponseBody
	public BaseResponse sunburnDetail(CimThumbsUpRequest req){
		CimUnrecordInvestListResp rlt = cimProductUnrecordInvestService.sunburnDetail(req.getId()+"");
		return AppResponseUtil.getSuccessResponse(rlt);
	}

	 /**
	  * 是否绑定机构帐号
	  */
	 @RequestMapping("isBindOrgAcct/4.6.6")
	 @ResponseBody
	 @RequestLogging("是否绑定A专区机构帐号")
	 public BaseResponse isBindOrgAcct(@Valid BindOrgAcctRequest req, BindingResult result, AppRequestHead head) throws Exception {
		 if (AppResponseUtil.existsParamsError(result)) {
			 return AppResponseUtil.getErrorParams(result);
		 }
		 boolean flag = orgAcctRelAService.isBindOrgAcct(JsonWebTokenHepler.getUserIdByToken(head.getToken()),req.getPlatFromNumber());
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("isBind", flag);
		 return AppResponseUtil.getSuccessResponse(map);
	 }

	 /**
	  * 帐号是否存在于第三方平台
	  */
	 @RequestMapping("isExistInPlatform/4.6.6")
	 @ResponseBody
	 @RequestLogging("帐号是否存在于A专区平台")
	 public BaseResponse isExistInPlatform(@Valid BindOrgAcctRequest req, BindingResult result, AppRequestHead head) throws Exception {
		 if (AppResponseUtil.existsParamsError(result)) {
			 return AppResponseUtil.getErrorParams(result);
		 }
		 String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
         CimOrgInfoA org = new CimOrgInfoA();
		 org.setOrgNumber(req.getPlatFromNumber());
		 org = cimOrgInfoAService.selectOne(org);
		 SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		 sysThirdkeyConfig.setOrgNumber(req.getPlatFromNumber());
		 sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
		 if(sysThirdkeyConfig == null || org == null){
			 LOGGER.info("帐号是否存在于A专区平台配置数据错误");
			 return AppResponseUtil.getErrorBusi("dataError","第三方配置数据异常");
		 }
		 Map<String, Object> map = new HashMap<String, Object>();
		 if(org.getOrgUserExistUrl() == null || "".equals(org.getOrgUserExistUrl())) {
			 map.put("isExist", false);
			 return AppResponseUtil.getSuccessResponse(map);
		 }
		 CrmUserInfo user = crmUserInfoService.queryUserInfoByUserId(userId);

		 LOGGER.debug("帐号是否存在于第三方平台 : url = {}" , org.getOrgUserExistUrl());
		 Map<String , String> param = new HashMap<String , String>();
		 param.put("mobile", user.getMobile());
		 param.put("userId", user.getUserId());

		 String rlt =OpenHttpUtils.httpRequest(sysThirdkeyConfig, RequestTypeEnums.POST, org.getOrgUserExistUrl(), param);
		 LOGGER.info("帐号是否存在于第三方平台返回数据" + rlt);
		 try {
			 @SuppressWarnings("unchecked")
			 SuccessResponse<Map<String ,Object >> jb = JSONObject.toJavaObject(JSONObject.parseObject(rlt), SuccessResponse.class);
			 if(jb == null || jb.getData() == null) {
				 return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
			 }
			 if("0".equals(jb.getCode()) ){
				 if("Y".equals(jb.getData().get("isExist"))){
					 map.put("isExist", true);
				 }else {
					 map.put("isExist", false);
				 }
			 } else {
				 return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
			 }
		 } catch (Exception e) {
			 return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
		 }
		 return AppResponseUtil.getSuccessResponse(map);
	 }

	 /**
	  * 绑定A专区机构帐号
	  */
	 @RequestMapping("bindOrgAcct/4.6.6")
	 @ResponseBody
	 @RequestLogging("绑定A专区机构帐号")
	 public BaseResponse bindOrgAcct(@Valid BindOrgAcctRequest req, BindingResult result, AppRequestHead head) throws Exception {
		 if (AppResponseUtil.existsParamsError(result)) {
			 return AppResponseUtil.getErrorParams(result);
		 }
		 final String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		 if(orgAcctRelAService.isBindOrgAcct(userId, req.getPlatFromNumber())){
			 return AppResponseUtil.getErrorBusi("orgAccountExist","用户已绑定此第三方平台");
		 }
		 String rlt = null;
		 try {
			 CimOrgInfoA org = new CimOrgInfoA();
			 org.setOrgNumber(req.getPlatFromNumber());
			 org = cimOrgInfoAService.selectOne(org);
			 SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
			 sysThirdkeyConfig.setOrgNumber(req.getPlatFromNumber());
			 sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
			 if(sysThirdkeyConfig == null || org == null || org.getOrgBindUserUrl() == null){
				 LOGGER.info("绑定第三方平台第三方配置数据不存在: platFormNumber = {}; userId = {}" , req.getPlatFromNumber(), userId);
				 return AppResponseUtil.getErrorBusi("dataError","第三方配置数据异常，请联系管理员");
			 }
			 CrmUserInfo user = crmUserInfoService.queryUserInfoByUserId(userId);

			 LOGGER.info("绑定第三方平台请求参数 : url = {}" , org.getOrgBindUserUrl());
			 Map<String , String> param = new HashMap<String , String>();
			 param.put("userId", userId);
			 param.put("mobile", user.getMobile());

			 rlt = OpenHttpUtils.httpRequest(sysThirdkeyConfig, RequestTypeEnums.GET, org.getOrgBindUserUrl(), param);

			 LOGGER.info("绑定第三方平台返回数据" + rlt);
			 @SuppressWarnings("unchecked")
			 SuccessResponse<Map<String ,Object >> jb = JSONObject.toJavaObject(JSONObject.parseObject(rlt), SuccessResponse.class);
			 if(jb == null) {
				 LOGGER.error("绑定第三方平台第三方返回数据异常: platFormNumber = {}; userId = {}" , req.getPlatFromNumber(), userId);
				 return AppResponseUtil.getErrorBusi("dataError","第三方返回数据异常，请联系管理员");
			 }
			 if("0".equals(jb.getCode())){
				 if(jb.getData().get("isNewUser").toString().equals("N")) {
					 //老用户
					 return AppResponseUtil.getErrorBusi("old_user_error", "您是该平台老用户，通过T呗投资不能享受红包等奖励，建议购买其他平台产品");
				 }
				 CrmOrgAcctRelA bo = new CrmOrgAcctRelA();
				 bo.setUserId(userId);
				 bo.setOrgNumber(req.getPlatFromNumber());
				 bo.setOrgAccount(jb.getData().get("orgAccount").toString());
				 bo.setIsInvested(jb.getData().get("isInvested").toString().equals("Y") ? 1 : 0);
				 bo.setIsNewUser(jb.getData().get("isNewUser").toString().equals("Y") ? 1 : 0);
				 bo.setOrgAccountType(1);
				 bo.setUpdateTime(new Date());
				 bo.setCreatTime(new Date());
				 orgAcctRelAService.insert(bo);

			 } else {
				 LOGGER.info("绑定第三方平台失败: {}" ,jb.getMsg());
				 return AppResponseUtil.getErrorBusi("system_error", jb.getMsg());
			 }
		 } catch (Exception e) {
			 LOGGER.error("绑定机构帐号异常: req={},resp={}", new Object[]{req,rlt,e});
			 return AppResponseUtil.getErrorBusi("system_error","绑定失败，请联系管理员");
		 }
		 return AppResponseUtil.getSuccessResponse();
	 }

	 /**
	  * A专区机构跳转
	  * @param orgUrlSkipParameterRequest
	  * @param result
	  * @param head
	  * @return
	  */
	 @RequestMapping("/getOrgJumpUrl/4.6.6")
	 @ResponseBody
	 @RequestLogging("A专区机构跳转地址")
	 public BaseResponse getOrgJumpUrl(@Valid OrgUrlSkipParameterRequest orgUrlSkipParameterRequest,BindingResult result,AppRequestHead head){
		 if (AppResponseUtil.existsParamsError(result)) {
			 return AppResponseUtil.getErrorParams(result);
		 }

		 orgUrlSkipParameterRequest.setUrlSkipType(2);//用户中心
		 Map<String,String> paramsMap = cimOrgInfoAService.getOrgUrlSkipParameter(orgUrlSkipParameterRequest,head);

		 LOGGER.info("A专区机构跳转地址最终参数列表={}",JSONObject.toJSONString(paramsMap));
		 return AppResponseUtil.getSuccessResponse(paramsMap);
	 }

	 @RequestMapping("/userInfo/4.6.7")
	 @ResponseBody
	 @RequestLogging("A专区用户信息")
	 public BaseResponse orgAUserInfo(AppRequestHead head){
		 String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		 if(userId.equals("undefined") || StringUtils.isBlank(userId)){
		 	return AppResponseUtil.getErrorToken();
		 }
		 OrgAUserInfoResponse response = cimProductUnrecordInvestService.orgAUserInfo(userId);
		 return AppResponseUtil.getSuccessResponse(response);
	 }
	
}
