package com.linkwee.web.service.impl;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.api.request.funds.ifast.BatchGetFundExtendsRequest;
import com.linkwee.api.request.funds.ifast.BatchGetFundRequest;
import com.linkwee.api.request.funds.ifast.FundDetailGotoRequest;
import com.linkwee.api.request.funds.ifast.GotoBaseRequest;
import com.linkwee.api.request.funds.ifast.HoldingsStatisticRequest;
import com.linkwee.api.request.funds.ifast.IfastBaseRequest;
import com.linkwee.api.response.funds.ifast.BaseDefinedResponse;
import com.linkwee.api.response.funds.ifast.BatchGetFundResponse;
import com.linkwee.api.response.funds.ifast.FundDetailGotoResponse;
import com.linkwee.api.response.funds.ifast.GotoBaseResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.web.model.CimFundBaseDefined;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActMidautumnTaskService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CimFundBaseDefinedService;
import com.linkwee.web.service.CimFundInfoService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.web.service.IfastFundService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.funds.sdk.ifast.Client;
import com.linkwee.xoss.funds.sdk.ifast.Request;
import com.linkwee.xoss.funds.sdk.ifast.base.IfastBaseRespons;
import com.linkwee.xoss.funds.sdk.ifast.constant.RequestPath;
import com.linkwee.xoss.funds.sdk.ifast.enums.Method;
import com.linkwee.xoss.funds.sdk.ifast.helper.IfastEncryptSignHelper;
import com.linkwee.xoss.funds.sdk.ifast.model.HoldingsStatistic;
import com.linkwee.xoss.funds.sdk.ifast.model.ProductFundInfoExtends;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.OpenHttpUtils;


@Service("IfastFundService")
public class IfastFundServiceImpl implements IfastFundService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(IfastFundServiceImpl.class);
	
	@Resource
	private AcAccountBindService  acAccountBindService;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private CimFundInfoService cimFundInfoService;
	@Resource
	private IfastEncryptSignHelper ifastEncryptSignHelper;
	@Resource
	private CimFundBaseDefinedService cimFundBaseDefinedService;
	@Resource
	private ActMidautumnTaskService actMidautumnTaskService;
	@Resource
	private ActivityListService activityListService;

	@Override
	public BaseResponse fundSift(BatchGetFundExtendsRequest batchGetFundExtendsRequest) {
		
		BaseResponse baseResponse =  new BaseResponse();
		String responseBody = null;
		try {
			PaginatorResponse<ProductFundInfoExtends> paginatorResponse = new PaginatorResponse<ProductFundInfoExtends>();
			
			BatchGetFundRequest batchGetFundRequest = new BatchGetFundRequest();
			BeanUtils.copyProperties(batchGetFundExtendsRequest, batchGetFundRequest);
			//请求奕丰接口
			Gson gson = new Gson();
			Request request = new Request(Method.GET, RequestPath.BATCH_GET_FUND_INFO,cimFundInfoService.selectOneByOrgNumber(batchGetFundExtendsRequest.getOrgCode()));
			request.setQuerys(OpenHttpUtils.obj2Map(batchGetFundRequest));
			responseBody = Client.execute(request).getBody();
			LOGGER.debug("请求奕丰批量获取基金信息接口返回    responseBody={} ",responseBody);
			Type quickType = new TypeToken<IfastBaseRespons<BatchGetFundRequest, BatchGetFundResponse>>(){}.getType();
			IfastBaseRespons<BatchGetFundRequest, BatchGetFundResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
			
			if("0000".equals(ifastBaseRespons.getCode())){
				//计算翻页总数
				int pageCount = 0;
				if(ifastBaseRespons.getData().getSize()%Integer.parseInt(ifastBaseRespons.getRequest().getPageSize()) == 0){
					pageCount = ifastBaseRespons.getData().getSize()/Integer.parseInt(ifastBaseRespons.getRequest().getPageSize());
				} else {
					pageCount =ifastBaseRespons.getData().getSize()/Integer.parseInt(ifastBaseRespons.getRequest().getPageSize()) + 1;
				}			
				//拼装返回参数
				paginatorResponse.setDatas(ifastBaseRespons.getData().getData());
				paginatorResponse.setTotalCount(ifastBaseRespons.getData().getSize());
				paginatorResponse.setPageCount(pageCount);
				paginatorResponse.setPageSize(Integer.parseInt(ifastBaseRespons.getRequest().getPageSize()));
				paginatorResponse.setPageIndex(Integer.parseInt(ifastBaseRespons.getRequest().getPageIndex()));
				baseResponse = AppResponseUtil.getSuccessResponse(paginatorResponse);		
			} else {
				baseResponse = AppResponseUtil.getErrorData(ifastBaseRespons.getCode(), "【奕丰金融】"+ifastBaseRespons.getMessage());
			}		
		} catch (Exception e) {
			LOGGER.error("查询奕丰精选基金异常	BatchGetFundRequest={} responseBody={}",new Object[]{JSONObject.toJSONString(batchGetFundExtendsRequest),responseBody},e);
			baseResponse = AppResponseUtil.getErrorSystem();
		}
		return baseResponse;
	}

	@Override
	public BaseResponse batchGetFundInfo(BatchGetFundExtendsRequest batchGetFundExtendsRequest) {
		
		BaseResponse baseResponse =  new BaseResponse();
		String responseBody = null;
		List<ProductFundInfoExtends>  productFundInfoExtendsList = new ArrayList<ProductFundInfoExtends>();
		
		try {
			
			BatchGetFundRequest batchGetFundRequest = new BatchGetFundRequest();
			BeanUtils.copyProperties(batchGetFundExtendsRequest, batchGetFundRequest);
			
			PaginatorResponse<ProductFundInfoExtends> paginatorResponse = new PaginatorResponse<ProductFundInfoExtends>();
			
			//请求奕丰接口
			Gson gson = new Gson();
			Request request = new Request(Method.GET, RequestPath.BATCH_GET_FUND_INFO,cimFundInfoService.selectOneByOrgNumber(batchGetFundExtendsRequest.getOrgCode()));
			request.setQuerys(OpenHttpUtils.obj2Map(batchGetFundRequest));
			responseBody = Client.execute(request).getBody();
			LOGGER.debug("请求奕丰批量获取基金信息接口返回    responseBody={} ",responseBody);
			Type quickType = new TypeToken<IfastBaseRespons<BatchGetFundRequest, BatchGetFundResponse>>(){}.getType();
			IfastBaseRespons<BatchGetFundRequest, BatchGetFundResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
			
			if("0000".equals(ifastBaseRespons.getCode())){
				//计算翻页总数
				int pageCount = 0;
				if(ifastBaseRespons.getData().getSize()%Integer.parseInt(ifastBaseRespons.getRequest().getPageSize()) == 0){
					pageCount = ifastBaseRespons.getData().getSize()/Integer.parseInt(ifastBaseRespons.getRequest().getPageSize());
				} else {
					pageCount =ifastBaseRespons.getData().getSize()/Integer.parseInt(ifastBaseRespons.getRequest().getPageSize()) + 1;
				}
				if(ifastBaseRespons.getData() != null &&  CollectionUtils.isNotEmpty(ifastBaseRespons.getData().getData())){
					productFundInfoExtendsList = ifastBaseRespons.getData().getData();
				}
				//拼装返回参数
				paginatorResponse.setDatas(productFundInfoExtendsList);
				paginatorResponse.setTotalCount(ifastBaseRespons.getData().getSize());
				paginatorResponse.setPageCount(pageCount);
				paginatorResponse.setPageSize(Integer.parseInt(ifastBaseRespons.getRequest().getPageSize()));
				paginatorResponse.setPageIndex(Integer.parseInt(ifastBaseRespons.getRequest().getPageIndex()));
				baseResponse = AppResponseUtil.getSuccessResponse(paginatorResponse);
			} else {
				baseResponse = AppResponseUtil.getErrorData(ifastBaseRespons.getCode(), "【奕丰金融】"+ifastBaseRespons.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("奕丰批量获取基金信息异常  BatchGetFundRequest={}		responseBody={}",new Object[]{JSONObject.toJSONString(batchGetFundExtendsRequest),responseBody},e);
			baseResponse = AppResponseUtil.getErrorSystem();
		}
		return baseResponse;
	}

	@Override
	public BaseResponse getHoldingsStatistic(HoldingsStatisticRequest holdingsStatisticRequest) {
		
		BaseResponse baseResponse =  new BaseResponse();
		String responseBody = null;
		try {			
			IfastBaseRequest ifastBaseRequest = new IfastBaseRequest();
			BeanUtils.copyProperties(holdingsStatisticRequest, ifastBaseRequest);
			ifastBaseRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(holdingsStatisticRequest.getUserId(), holdingsStatisticRequest.getOrgCode()));
			
			//请求奕丰接口
			Gson gson = new Gson();
			Request request = new Request(Method.GET, RequestPath.GET_HOLDINGS_STATISTIC,cimFundInfoService.selectOneByOrgNumber(holdingsStatisticRequest.getOrgCode()));
			request.setQuerys(OpenHttpUtils.obj2Map(ifastBaseRequest));
			responseBody = Client.execute(request).getBody();
			LOGGER.debug("请求奕丰获取账户持有总资产接口返回    responseBody={} ",responseBody);
			Type quickType = new TypeToken<IfastBaseRespons<IfastBaseRequest, HoldingsStatistic>>(){}.getType();
			IfastBaseRespons<IfastBaseRequest, HoldingsStatistic>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
			
			if("0000".equals(ifastBaseRespons.getCode())){		
				baseResponse = AppResponseUtil.getSuccessResponse(ifastBaseRespons.getData());
			} else {
				baseResponse = AppResponseUtil.getErrorData(ifastBaseRespons.getCode(), "【奕丰金融】"+ifastBaseRespons.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("奕丰获取账户持有总资产异常	 HoldingsStatisticRequest={}		responseBody={}",new Object[]{JSONObject.toJSONString(holdingsStatisticRequest),responseBody},e);
			baseResponse = AppResponseUtil.getErrorSystem();
		}
		return baseResponse;
	}

//	@Override
//	public BaseResponse getInvestorHoldings(GetInvestorHoldingsRequest getInvestorHoldingsRequest) {
//		
//		BaseResponse baseResponse =  new BaseResponse();
//		String responseBody = null;
//		try {		
//			InvestorHoldingsRequest investorHoldingsRequest = new InvestorHoldingsRequest();
//			BeanUtils.copyProperties(getInvestorHoldingsRequest, investorHoldingsRequest);
//			investorHoldingsRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(getInvestorHoldingsRequest.getUserId(), getInvestorHoldingsRequest.getOrgCode()));
//			
//			//请求奕丰接口
//			Gson gson = new Gson();
//			Request request = new Request(Method.GET, RequestPath.GET_INVESTOR_HOLDINGS,cimFundInfoService.selectOneByOrgNumber(getInvestorHoldingsRequest.getOrgCode()));
//			request.setQuerys(OpenHttpUtils.obj2Map(investorHoldingsRequest));
//			responseBody = Client.execute(request).getBody();
//			LOGGER.debug("请求奕丰获取账户持有资产接口返回    responseBody={} ",responseBody);
//			Type quickType = new TypeToken<IfastBaseRespons<InvestorHoldingsRequest, InvestorholdingsResponse>>(){}.getType();
//			IfastBaseRespons<InvestorHoldingsRequest, InvestorholdingsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
//			
//			if("0000".equals(ifastBaseRespons.getCode())){		
//				baseResponse = AppResponseUtil.getSuccessResponse(ifastBaseRespons.getData());
//			} else {
//				baseResponse = AppResponseUtil.getErrorData(ifastBaseRespons.getCode(), "【奕丰金融】"+ifastBaseRespons.getMessage());
//			}
//		} catch (Exception e) {
//			LOGGER.error("奕丰获取账户持有资产异常    GetInvestorHoldingsRequest={}	responseBody={}",new Object[]{JSONObject.toJSONString(getInvestorHoldingsRequest),responseBody}, e);
//			baseResponse = AppResponseUtil.getErrorSystem();
//		}
//		
//		return baseResponse;
//	}
//	
//	@Override
//	public BaseResponse getInvestorHoldingsNew(GetInvestorHoldingsRequest getInvestorHoldingsRequest) {
//		
//		BaseResponse baseResponse =  new BaseResponse();
//		String responseBody = null;
//		List<FundHolding> fundHoldingList = new ArrayList<FundHolding>();
//		try {		
//			InvestorHoldingsRequest investorHoldingsRequest = new InvestorHoldingsRequest();
//			BeanUtils.copyProperties(getInvestorHoldingsRequest, investorHoldingsRequest);
//			investorHoldingsRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(getInvestorHoldingsRequest.getUserId(), getInvestorHoldingsRequest.getOrgCode()));
//			
//			//请求奕丰接口
//			Gson gson = new Gson();
//			Request request = new Request(Method.GET, RequestPath.GET_INVESTOR_HOLDINGS,cimFundInfoService.selectOneByOrgNumber(getInvestorHoldingsRequest.getOrgCode()));
//			request.setQuerys(OpenHttpUtils.obj2Map(investorHoldingsRequest));
//			responseBody = Client.execute(request).getBody();
//			LOGGER.debug("请求奕丰获取账户持有资产接口返回    responseBody={} ",responseBody);
//			Type quickType = new TypeToken<IfastBaseRespons<InvestorHoldingsRequest, InvestorholdingsResponse>>(){}.getType();
//			IfastBaseRespons<InvestorHoldingsRequest, InvestorholdingsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
//			
//			if("0000".equals(ifastBaseRespons.getCode())){
//				if(ifastBaseRespons.getData() != null && CollectionUtils.isNotEmpty(ifastBaseRespons.getData().getData())
//						&& ifastBaseRespons.getData().getData().get(0) != null && ifastBaseRespons.getData().getData().get(0).getFundHoldings() != null 
//						&&  CollectionUtils.isNotEmpty(ifastBaseRespons.getData().getData().get(0).getFundHoldings().getData())){
//					fundHoldingList = ifastBaseRespons.getData().getData().get(0).getFundHoldings().getData();
//				}
//				baseResponse = AppResponseUtil.getSuccessResponse(fundHoldingList);
//			} else {
//				baseResponse = AppResponseUtil.getErrorData(ifastBaseRespons.getCode(), "【奕丰金融】"+ifastBaseRespons.getMessage());
//			}
//		} catch (Exception e) {
//			LOGGER.error("奕丰获取账户持有资产异常    GetInvestorHoldingsRequest={}	responseBody={}",new Object[]{JSONObject.toJSONString(getInvestorHoldingsRequest),responseBody}, e);
//			baseResponse = AppResponseUtil.getErrorSystem();
//		}
//		
//		return baseResponse;
//	}
//
//	@Override
//	public BaseResponse getInvestorOrderInfo(GetInvestorOrderInfoRequest getInvestorOrderInfoRequest) {
//		
//		BaseResponse baseResponse =  new BaseResponse();
//		String responseBody = null;
//		try {		
//			PaginatorResponse<InvestorOrderInfoExtends> paginatorResponse = new PaginatorResponse<InvestorOrderInfoExtends>();
//			
//			//请求奕丰接口
//			Gson gson = new Gson();
//			InvestorOrderInfoRequest investorOrderInfoRequest = new InvestorOrderInfoRequest();
//			BeanUtils.copyProperties(getInvestorOrderInfoRequest, investorOrderInfoRequest);
//			investorOrderInfoRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(getInvestorOrderInfoRequest.getUserId(), getInvestorOrderInfoRequest.getOrgCode()));
//			
//			Request request = new Request(Method.GET, RequestPath.GET_INVESTOR_ORDER_INFO,cimFundInfoService.selectOneByOrgNumber(getInvestorOrderInfoRequest.getOrgCode()));
//			request.setQuerys(OpenHttpUtils.obj2Map(investorOrderInfoRequest));
//			responseBody = Client.execute(request).getBody();
//			LOGGER.debug("请求奕丰获取用户的订单详情接口返回    responseBody={} ",responseBody);
//			Type quickType = new TypeToken<IfastBaseRespons<InvestorOrderInfoRequest, InvestorOrderInfoExtendsResponse>>(){}.getType();
//			IfastBaseRespons<InvestorOrderInfoRequest, InvestorOrderInfoExtendsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
//			
//			if("0000".equals(ifastBaseRespons.getCode())){
//				//计算翻页总数
//				int pageCount = 0;
//				if(ifastBaseRespons.getData().getSize()%ifastBaseRespons.getRequest().getPageSize() == 0){
//					pageCount = ifastBaseRespons.getData().getSize()/ifastBaseRespons.getRequest().getPageSize();
//				} else {
//					pageCount =ifastBaseRespons.getData().getSize()/ifastBaseRespons.getRequest().getPageSize() + 1;
//				}
//				//拼装返回参数
//				paginatorResponse.setDatas(ifastBaseRespons.getData().getData());
//				paginatorResponse.setTotalCount(ifastBaseRespons.getData().getSize());
//				paginatorResponse.setPageCount(pageCount);
//				paginatorResponse.setPageSize(ifastBaseRespons.getRequest().getPageSize());
//				paginatorResponse.setPageIndex(ifastBaseRespons.getRequest().getPageIndex());
//				baseResponse = AppResponseUtil.getSuccessResponse(paginatorResponse);
//			} else {
//				baseResponse = AppResponseUtil.getErrorData(ifastBaseRespons.getCode(), "【奕丰金融】"+ifastBaseRespons.getMessage());
//			}
//		} catch (Exception e) {
//			LOGGER.error("奕丰获取用户的订单详情异常    GetInvestorOrderInfo={}	responseBody={}",new Object[]{JSONObject.toJSONString(getInvestorOrderInfoRequest),responseBody}, e);
//			baseResponse = AppResponseUtil.getErrorSystem();
//		}
//		
//		return baseResponse;
//	}
//
//	@Override
//	public BaseResponse quickCustomerMigration(QuickCustomerMigrationRequest quickCustomerMigrationRequest) {
//		
//		BaseResponse baseResponse = new BaseResponse();
//		String responseBody = null;
//		try {			
//			Gson gson = new Gson();
//			CustomerMigrationRequest customerMigrationRequest = new CustomerMigrationRequest();
//			//判断用户是否存在
//			if(crmOrgAcctRelService.ifOrgAccountExist(quickCustomerMigrationRequest.getUserId(), quickCustomerMigrationRequest.getOrgCode())){
//				return AppResponseUtil.getErrorData("-1", "客户已在奕丰基金注册");
//			}
//			
//			//获取用户的绑卡信息
//			AcAccountBind acAccountBind =  new AcAccountBind();
//			acAccountBind.setUserId(quickCustomerMigrationRequest.getUserId());
//			acAccountBind = acAccountBindService.selectOne(acAccountBind);
//			
//			if(acAccountBind == null){
//				return AppResponseUtil.getErrorData("-1", "客户绑卡信息不存在,请先完成银行卡信息绑定");
//			}
//			
//			//根据银行卡号获取银行代码,避免我们这边的银行代码和第三方不一致
//			Request requestBank = new Request(Method.GET, RequestPath.GET_BANK_CODE,cimFundInfoService.selectOneByOrgNumber(quickCustomerMigrationRequest.getOrgCode()));
//			Map<String, String> bankMap = ImmutableMap.of("bankNumber", acAccountBind.getBankCard());
//			requestBank.setQuerys(bankMap);
//			responseBody = Client.execute(requestBank).getBody();
//			LOGGER.debug("请求奕丰根据银行卡号获取银行代码接口返回    responseBody={} ",responseBody);
//			String bankCode = StringUtils.isNotBlank(responseBody)?JSONObject.parseObject(responseBody).getJSONObject("data").getString("bankCode"):acAccountBind.getBankCode();
//			
//			//拼装奕丰基金注册所需要的参数
//			customerMigrationRequest.setIdName(acAccountBind.getUserName());
//			customerMigrationRequest.setIdNumber(acAccountBind.getIdCard());
//			customerMigrationRequest.setIdType("0");//0=身份证，目前固定为0
//			customerMigrationRequest.setBankNumber(acAccountBind.getBankCard());
//			customerMigrationRequest.setBankCode(bankCode);
//			customerMigrationRequest.setTelephoneNumber(acAccountBind.getReserveMobile());
//			customerMigrationRequest.setAccountNumber(quickCustomerMigrationRequest.getUserId());
//			customerMigrationRequest.setIsNotify("true");//是否发送短信通知(true,false)
//			
//			Request request = new Request(Method.POST_JSON, RequestPath.QUICK_CUSTOMER_MIGRATION,cimFundInfoService.selectOneByOrgNumber(quickCustomerMigrationRequest.getOrgCode()));
//			request.setQuerys(OpenHttpUtils.obj2Map(customerMigrationRequest));
//			String responseQuickBody = Client.execute(request).getBody();
//			LOGGER.debug("请求奕丰客户迁移(注册)接口返回    responseBody={} ",responseQuickBody);
//			Type quickType = new TypeToken<IfastBaseRespons<CustomerMigrationRequest, CustomerMigrationResponse>>(){}.getType();
//			IfastBaseRespons<CustomerMigrationRequest, CustomerMigrationResponse>  migrationRespons = gson.fromJson(responseQuickBody,quickType);
//			
//			//处理注册返回结果
//			if("0000".equals(migrationRespons.getCode())){//注册成功			
//				CrmOrgAcctRel crmOrgAcctRel = new CrmOrgAcctRel();
//				crmOrgAcctRel.setUserId(customerMigrationRequest.getAccountNumber());
//				crmOrgAcctRel.setOrgAccountType(1);
//				crmOrgAcctRel.setOrgAccount(migrationRespons.getData().getAccountNumber());
//				crmOrgAcctRel.setOrgNumber(quickCustomerMigrationRequest.getOrgCode());
//				crmOrgAcctRel.setCreatTime(new Date());
//				crmOrgAcctRel.setOrgType(1);
//				crmOrgAcctRelService.insert(crmOrgAcctRel);
//				
//				//月饼节活动			
//				actMidautumnTaskService.finishTask(crmOrgAcctRel.getUserId(), MidAutumnTaskEnum.FUND_REGISTER);
//							
//				//返回前端注册账户
//				IfastAccountBaseResponse ifastAccountBaseResponse = new IfastAccountBaseResponse();
//				ifastAccountBaseResponse.setAccountNumber(migrationRespons.getData().getAccountNumber());
//				baseResponse = AppResponseUtil.getSuccessResponse(ifastAccountBaseResponse);
//			} else {
//				baseResponse = AppResponseUtil.getErrorData(migrationRespons.getCode(),"【奕丰金融】"+ migrationRespons.getMessage());
//			}
//		} catch (Exception e) {
//			LOGGER.error("奕丰基金注册异常    QuickCustomerMigrationRequest={}	responseBody={}",new Object[]{JSONObject.toJSONString(quickCustomerMigrationRequest),responseBody}, e);
//			baseResponse = AppResponseUtil.getErrorSystem();
//		}
//		
//		return baseResponse;
//	}

	@Override
	public GotoBaseResponse gotoIndex(GotoBaseRequest gotoBaseRequest) {
		
//		//获取机构对应的账号
//		gotoBaseRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(gotoBaseRequest.getUserId(), gotoBaseRequest.getOrgCode()));
		
		GotoBaseResponse gotoBaseResponse = new GotoBaseResponse();
		gotoBaseResponse.setRequestUrl(cimFundInfoService.selectOneByOrgNumber(gotoBaseRequest.getOrgCode()).getPlatformIndexUrl());
		gotoBaseResponse.setData(ifastEncryptSignHelper.encryptionBaseData(gotoBaseRequest.getUserId(), gotoBaseRequest.getOrgCode()));
		return gotoBaseResponse;
	}

	@Override
	public FundDetailGotoResponse gotoFundDetail(FundDetailGotoRequest fundDetailGotoRequest) {
		
//		//获取机构对应的账号
//		fundDetailGotoRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(fundDetailGotoRequest.getUserId(), fundDetailGotoRequest.getOrgCode()));
		
		FundDetailGotoResponse fundDetailGotoResponse = new FundDetailGotoResponse();
		fundDetailGotoResponse.setProductCode(fundDetailGotoRequest.getProductCode());
		fundDetailGotoResponse.setRequestUrl(cimFundInfoService.selectOneByOrgNumber(fundDetailGotoRequest.getOrgCode()).getFundDetailUrl());
		fundDetailGotoResponse.setData(ifastEncryptSignHelper.encryptionBaseData(fundDetailGotoRequest.getUserId(), fundDetailGotoRequest.getOrgCode()));
		return fundDetailGotoResponse;
	}

	@Override
	public GotoBaseResponse gotoAccount(GotoBaseRequest gotoBaseRequest) {
//		//获取机构对应的账号
//		gotoBaseRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(gotoBaseRequest.getUserId(), gotoBaseRequest.getOrgCode()));
		
		GotoBaseResponse gotoBaseResponse = new GotoBaseResponse();
		gotoBaseResponse.setRequestUrl(cimFundInfoService.selectOneByOrgNumber(gotoBaseRequest.getOrgCode()).getPersonAccountUrl());
		gotoBaseResponse.setData(ifastEncryptSignHelper.encryptionBaseData(gotoBaseRequest.getUserId(), gotoBaseRequest.getOrgCode()));
		return gotoBaseResponse;
	}

	@Override
	public BaseDefinedResponse baseDefined(AppRequestHead head) {
		BaseDefinedResponse baseDefinedResponse = new BaseDefinedResponse();
		CimFundBaseDefined cimFundBaseDefined = new CimFundBaseDefined();
		cimFundBaseDefined.setOrgNumber("OPEN_IFAST_WEB");
		cimFundBaseDefined.setDelStatus(0);//有效
				
		//基金类型
		cimFundBaseDefined.setFundType("fundType");
		baseDefinedResponse.setFundTypeList(cimFundBaseDefinedService.selectListByCondition(cimFundBaseDefined));
		
		//批量基金排序字段
		cimFundBaseDefined.setFundType("period");
		List<CimFundBaseDefined> cimFundBaseDefinedList = cimFundBaseDefinedService.selectListByCondition(cimFundBaseDefined);
		
		if(head.getAppVersion().indexOf("4.2") != -1){//兼容4.2版本  若是4.2版本  默认排序用year1  去掉七日年化收益
			baseDefinedResponse.setDefaultPeriod("year1");
			for (int i = 0; i < cimFundBaseDefinedList.size(); i++) {
				if("period".equals(cimFundBaseDefinedList.get(i).getFundType()) && "sevenDaysAnnualizedYield".equals(cimFundBaseDefinedList.get(i).getFundTypeKey())){
					cimFundBaseDefinedList.remove(i);
					break;
				}
			}
		}
		baseDefinedResponse.setPeriodList(cimFundBaseDefinedList);
		
		return baseDefinedResponse;
	}
//
//	@Override
//	public BaseResponse getOrderList(GetOrderListRequest getOrderListRequest) {
//		BaseResponse baseResponse =  new BaseResponse();
//		String responseBody = null;
//		try {		
//			PaginatorResponse<OrderListExtends> paginatorResponse = new PaginatorResponse<OrderListExtends>();
//			
//			//请求奕丰接口
//			Gson gson = new Gson();
//			OrderListRequest orderListRequest = new OrderListRequest();
//			BeanUtils.copyProperties(getOrderListRequest, orderListRequest);
//			orderListRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(getOrderListRequest.getUserId(), getOrderListRequest.getOrgCode()));
//			
//			Request request = new Request(Method.GET, RequestPath.GET_ORDER_LIST,cimFundInfoService.selectOneByOrgNumber(getOrderListRequest.getOrgCode()));
//			request.setQuerys(OpenHttpUtils.obj2Map(orderListRequest));
//			responseBody = Client.execute(request).getBody();
//			LOGGER.debug("请求奕丰获取用户的订单列表接口返回    responseBody={} ",responseBody);
//			Type quickType = new TypeToken<IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>>(){}.getType();
//			IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
//			
//			if("0000".equals(ifastBaseRespons.getCode())){
//				//计算翻页总数
//				int pageCount = 0;
//				if(ifastBaseRespons.getData().getSize()%ifastBaseRespons.getRequest().getPageSize() == 0){
//					pageCount = ifastBaseRespons.getData().getSize()/ifastBaseRespons.getRequest().getPageSize();
//				} else {
//					pageCount =ifastBaseRespons.getData().getSize()/ifastBaseRespons.getRequest().getPageSize() + 1;
//				}
//				//拼装返回参数
//				paginatorResponse.setDatas(ifastBaseRespons.getData().getData());
//				paginatorResponse.setTotalCount(ifastBaseRespons.getData().getSize());
//				paginatorResponse.setPageCount(pageCount);
//				paginatorResponse.setPageSize(ifastBaseRespons.getRequest().getPageSize());
//				paginatorResponse.setPageIndex(ifastBaseRespons.getRequest().getPageIndex());
//				baseResponse = AppResponseUtil.getSuccessResponse(paginatorResponse);
//			} else {
//				baseResponse = AppResponseUtil.getErrorData(ifastBaseRespons.getCode(), "【奕丰金融】"+ifastBaseRespons.getMessage());
//			}
//		} catch (Exception e) {
//			LOGGER.error("奕丰获取用户的订单列表异常    getOrderListRequest={}	responseBody={}",new Object[]{JSONObject.toJSONString(getOrderListRequest),responseBody}, e);
//			baseResponse = AppResponseUtil.getErrorSystem();
//		}
//		
//		return baseResponse;
//	}
//	
//	@Override
//	public boolean hasOrder(GetOrderListRequest getOrderListRequest) {
//		String responseBody = null;
//		try {			
//			//请求奕丰接口
//			Gson gson = new Gson();
//			OrderListRequest orderListRequest = new OrderListRequest();
//			BeanUtils.copyProperties(getOrderListRequest, orderListRequest);
//			orderListRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(getOrderListRequest.getUserId(), getOrderListRequest.getOrgCode()));
//			
//			Request request = new Request(Method.GET, RequestPath.GET_ORDER_LIST,cimFundInfoService.selectOneByOrgNumber(getOrderListRequest.getOrgCode()));
//			request.setQuerys(OpenHttpUtils.obj2Map(orderListRequest));
//			responseBody = Client.execute(request).getBody();
//			LOGGER.debug("请求奕丰获取用户的订单列表接口返回    responseBody={} ",responseBody);
//			Type quickType = new TypeToken<IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>>(){}.getType();
//			IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
//			
//			if("0000".equals(ifastBaseRespons.getCode())){	
//				
//				List<OrderListExtends> orderList = ifastBaseRespons.getData().getData();
//				ActivityList selectCondition = new ActivityList();
//				selectCondition.setActivityCode("double_eleven");
//				ActivityList activity = activityListService.selectActiveOne(selectCondition);
//				if(activity == null || orderList == null){
//					return false;
//				}
//				for(OrderListExtends order : orderList){
//					if(DateUtils.compareDate(DateUtils.parseTimestampStr(order.getOrderDate()), activity.getStartDate()) >= 0 
//							&& DateUtils.compareDate(DateUtils.parseTimestampStr(order.getOrderDate()), activity.getEndDate() ) <= 0 
//							&& order.getTransactionAmount() > 0
//							&& (order.getTransactionType().equals("rsp") || order.getTransactionType().equals("buy") || order.getTransactionType().equals("ipo"))
//							&& (order.getTransactionStatus().equals("completed") || order.getTransactionStatus().equals("priced"))){
//						return true;
//					}
//				}
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			LOGGER.error("奕丰获取用户的订单列表异常    getOrderListRequest={}	responseBody={}",new Object[]{JSONObject.toJSONString(getOrderListRequest),responseBody}, e);
//		}
//		
//		return false;
//	}

}