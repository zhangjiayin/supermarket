package com.linkwee.openapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.openapi.request.OmsAccountBindRequest;
import com.linkwee.openapi.request.OmsAccountExistRequest;
import com.linkwee.openapi.request.OmsExistAccountRequest;
import com.linkwee.openapi.request.OmsInvestmentListRedirectRequest;
import com.linkwee.openapi.request.OmsProductDetailRedirectRequest;
import com.linkwee.openapi.request.OmsProductRequest;
import com.linkwee.openapi.response.OmsProductResponse;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmInvestorOrgInfoToOurService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.OpenRequestHead;
import com.linkwee.xoss.constant.TimeSetConstants;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.OpenHttpUtils;
import com.linkwee.xoss.util.OpenResponseUtil;
import com.linkwee.xoss.util.RequestHeadUtil;
import com.linkwee.xoss.util.RequestLogging;

/**
 * 开放平台 - 平台接入
 * @author liqimoon
 *
 */
@Controller
@RequestMapping(value = "openapi/oms")
@RequestLogging("第三方平台接入")
public class OpenOmsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenOmsController.class);
	
	@Resource
	private CimProductService cimProductService;
	@Resource
	private AcAccountBindService acAccountBindService;
	@Resource
	private JsonWebTokenHepler jsonWebTokenHepler;
	@Resource
	private CrmInvestorService crmInvestorService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	@Resource
	private CrmInvestorOrgInfoToOurService crmInvestorOrgInfoToOurService;
	
    @RequestLogging("第三方查询领会产品列表")
    @RequestMapping("/product/list")
    @ResponseBody
	public BaseResponse productList(OpenRequestHead openRequestHead,@Valid OmsProductRequest omsProductRequest,BindingResult validResult) throws Exception{
    	LOGGER.info("【平台接入领会】查询理财产品列表,OrgNumber={} productOmsRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(omsProductRequest));
    	if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
    	omsProductRequest.setOrgNumber(openRequestHead.getOrgNumber());
		PaginatorResponse<OmsProductResponse> rlt = cimProductService.queryOmsProductList(omsProductRequest);
		return OpenResponseUtil.getSuccessResponse(rlt);
    }
    
    @RequestLogging("第三方查询用户是否在领会已存在")
    @RequestMapping("/account/exist")
    @ResponseBody
	public BaseResponse accountExist(OpenRequestHead openRequestHead,@Valid OmsAccountExistRequest omsAccountExistRequest,BindingResult validResult) throws Exception{
    	LOGGER.info("【平台接入领会】查询用户是否已存在,OrgNumber={} accountExistRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(omsAccountExistRequest));
    	if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
    	BaseResponse baseResponse = acAccountBindService.queryOmsaccountExist(openRequestHead,omsAccountExistRequest);
		return baseResponse;
    }
    
    @RequestLogging("绑定领会已存在老用户接口")
    @RequestMapping("/account/old_account_bind")
    @ResponseBody
	public BaseResponse existAccountBind(OpenRequestHead openRequestHead,@Valid OmsExistAccountRequest omsExistAccountRequest,BindingResult validResult) throws Exception{
    	LOGGER.info("【平台接入领会】绑定领会已存在老用户接口,OrgNumber={} omsExistAccountRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(omsExistAccountRequest));
    	if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
    	BaseResponse baseResponse = acAccountBindService.existAccountBind(openRequestHead,omsExistAccountRequest);
		return baseResponse;
    }
    
    @RequestLogging("第三方绑定领会平台新账户")
    @RequestMapping("/account/bind")
    @ResponseBody
	public BaseResponse accountBind(OpenRequestHead openRequestHead,@Valid OmsAccountBindRequest omsAccountBindRequest,BindingResult validResult) throws Exception{    	
    	LOGGER.info("【平台接入领会】绑定领会平台新账户,OrgNumber={} omsAccountBindRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(omsAccountBindRequest));
    	if (OpenResponseUtil.existsParamsError(validResult)) {
			return OpenResponseUtil.getErrorParams(validResult);
		}
    	omsAccountBindRequest.setOrgNumber(openRequestHead.getOrgNumber());
		return acAccountBindService.omsAccountBind(omsAccountBindRequest);
    }
    
    @RequestLogging("第三方产品详情页面跳转")
    @RequestMapping("/product/detail")
	public String productDetailRedirect(OpenRequestHead openRequestHead,@Valid OmsProductDetailRedirectRequest omsProductDetailRedirectRequest,BindingResult validResult,HttpServletResponse response) throws Exception{
    	LOGGER.info("【平台接入领会】产品详情页面跳转,OrgNumber={} omsAccountBindRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(omsProductDetailRedirectRequest));
    	if (OpenResponseUtil.existsParamsError(validResult)) {
    		RequestHeadUtil.responseOutWithJson(response,OpenResponseUtil.getErrorParams(validResult));
		}
    	
    	//自动登录
    	if(crmUserInfoService.queryUserInfoByUserId(omsProductDetailRedirectRequest.getUserId()) == null || !crmInvestorOrgInfoToOurService.ifExistBindAccount(openRequestHead.getOrgNumber(),omsProductDetailRedirectRequest.getUserId())){
    		RequestHeadUtil.responseOutWithJson(response,OpenResponseUtil.getErrorBusi("userid_not_exists","userid不存在或userid未绑定自动登录失败"));
    	}
    	String token = investorAutoLogin("investor",omsProductDetailRedirectRequest.getUserId(),response);
		
		//拼装最终跳转参数
    	String redirectUrl = sysConfigService.getValuesByKey("openapi_oms_config", "openapi_oms_product_detail_redirect_"+omsProductDetailRedirectRequest.getRequestFrom(), 0);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("token",token));
		nameValuePairList.add(new BasicNameValuePair("productId",omsProductDetailRedirectRequest.getProductId()));
		
    	return "redirect:"+OpenHttpUtils.getGetRequestUrl(redirectUrl, nameValuePairList);
    }
    
    @RequestLogging("第三方投资记录页面跳转")
    @RequestMapping("/investment/list")
	public String investmentListRedirect(OpenRequestHead openRequestHead,@Valid OmsInvestmentListRedirectRequest omsInvestmentListRedirectRequest,BindingResult validResult,HttpServletResponse response) throws Exception{
    	LOGGER.info("【平台接入领会】产品详情页面跳转,OrgNumber={} investmentListRedirectRequest={}",openRequestHead.getOrgNumber(),JSONObject.toJSONString(omsInvestmentListRedirectRequest));
    	if (OpenResponseUtil.existsParamsError(validResult)) {
    		RequestHeadUtil.responseOutWithJson(response,OpenResponseUtil.getErrorParams(validResult));
		}
    	
    	//自动登录
    	if(crmUserInfoService.queryUserInfoByUserId(omsInvestmentListRedirectRequest.getUserId()) == null || !crmInvestorOrgInfoToOurService.ifExistBindAccount(openRequestHead.getOrgNumber(),omsInvestmentListRedirectRequest.getUserId())){
    		RequestHeadUtil.responseOutWithJson(response,OpenResponseUtil.getErrorBusi("userid_not_exists","userid不存在或userid未绑定自动登录失败"));
    	}
    	String token = investorAutoLogin("investor",omsInvestmentListRedirectRequest.getUserId(),response);
    	//拼装最终跳转参数
    	String redirectUrl = sysConfigService.getValuesByKey("openapi_oms_config", "openapi_oms_investment_list_redirect_"+omsInvestmentListRedirectRequest.getRequestFrom(), 0);;
    	List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
    	nameValuePairList.add(new BasicNameValuePair("token",token));
		
    	return "redirect:"+OpenHttpUtils.getGetRequestUrl(redirectUrl, nameValuePairList);
    }
    
    /**
     * 投资人自动登录
     * @param appKind
     * @param userId
     * @return
     */
    private String investorAutoLogin(String appKind, String userId,HttpServletResponse response){
    		
    	//作为投资人的身份自动登录
    	String token = jsonWebTokenHepler.creatToken("investor",userId,TimeSetConstants.TOKEN_AUTO_LOGIN_VALID_DATE);
    	
		//更新访问时间
		CrmInvestor investorForUpdateVistTime = new CrmInvestor();
		investorForUpdateVistTime.setUserId(userId);
		investorForUpdateVistTime.setRectVisitTime(new Date());
		crmInvestorService.updateByUserId(investorForUpdateVistTime);
		
		return token;
    }
}
