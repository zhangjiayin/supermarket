package com.linkwee.api.controller.acc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.api.request.acc.AddBankCardRequest;
import com.linkwee.api.request.acc.InitPayPwdRequest;
import com.linkwee.api.request.acc.InputVcodeRequest;
import com.linkwee.api.request.acc.MonthProfixDetailRequest;
import com.linkwee.api.request.acc.MonthProfixDetailRequestNew;
import com.linkwee.api.request.acc.MonthProfixStatisticsRequest;
import com.linkwee.api.request.acc.MyAccountPageListRequest;
import com.linkwee.api.request.acc.ResetPayPwdRequest;
import com.linkwee.api.request.acc.TwoPwdRequest;
import com.linkwee.api.request.acc.UserWithdrawRequest;
import com.linkwee.api.request.acc.VerifyPayPwdRequest;
import com.linkwee.api.request.acc.VerityIdCardRequest;
import com.linkwee.api.response.acc.AcAccountBindReponse;
import com.linkwee.api.response.acc.AcAccountBindSuccessReponse;
import com.linkwee.api.response.acc.AcAccountTypeReponse;
import com.linkwee.api.response.acc.AcBankCodeResponse;
import com.linkwee.api.response.acc.AcBankImageResponse;
import com.linkwee.api.response.acc.AccountBalanceDetailResponse;
import com.linkwee.api.response.acc.AccountBalanceListResponse;
import com.linkwee.api.response.acc.CityInfoResponse;
import com.linkwee.api.response.acc.LieCainAccountBalance;
import com.linkwee.api.response.acc.MonthProfixDetailListResponse;
import com.linkwee.api.response.acc.MonthProfixDetailListResponseNew;
import com.linkwee.api.response.acc.MonthProfixStatisticsResponse;
import com.linkwee.api.response.acc.MonthProfixStatisticsResponseNew;
import com.linkwee.api.response.acc.MonthProfixTotalListResponse;
import com.linkwee.api.response.acc.MyAccount;
import com.linkwee.api.response.acc.MyAccountPageListResponse;
import com.linkwee.api.response.acc.MyBalanceRecordListResponse;
import com.linkwee.api.response.acc.ProfixTypeListRespsone;
import com.linkwee.api.response.acc.ProvinceInfoResponse;
import com.linkwee.api.response.acc.QueryAllWithdrawResponse;
import com.linkwee.api.response.acc.QueryRewardDetailPageListResponse;
import com.linkwee.api.response.acc.TBeiAccountBalance;
import com.linkwee.api.response.acc.WithdrawApplyPageListResponse;
import com.linkwee.api.response.acc.WithdrawBankCardResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.model.TCFeedetail;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.tc.fee.service.TCFeeService;
import com.linkwee.web.enums.AcVerifyRealNameCodeEnum;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.acc.AcWithdrawApply;
import com.linkwee.web.model.acc.AccountBalanceDetailResp;
import com.linkwee.web.model.acc.AccountBalanceListResp;
import com.linkwee.web.model.acc.MonthProfixDetailListResp;
import com.linkwee.web.model.acc.MonthProfixTotalListResp;
import com.linkwee.web.model.share.ShareContent;
import com.linkwee.web.model.vo.SingleImgInfo;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.AcBalanceRecordService;
import com.linkwee.web.service.AcBankCardInfoService;
import com.linkwee.web.service.AcWithdrawApplyService;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.constant.TimeSetConstants;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.helper.CommonHelper;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.QRCodeUtil;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.AcAccountUtils;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.MD5;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;
import com.linkwee.xoss.util.WebUtil;

 /**
 * 
 * @描述：账户相关接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/account")
@RequestLogging("账户相关接口")
public class AcAccountBindController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AcAccountBindController.class);
	
	//存放(重置密码token)
	private static Map<String, Long> verifyCodeMap = new HashMap<String, Long>();
	
	private String errorCode = "-1";
	
	private String bindErrorCode = "100006";
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private AcBalanceRecordService acBalanceRecordService;
	
	@Resource
	private AcBankCardInfoService acBankCardInfoService;
	
	@Resource
	private AcWithdrawApplyService acWithdrawApplyService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private TCFeeService feeService;
	
	@Resource
	private TCFeeDetailService feeDetailService;
	
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private ActRedpacketService actRedpacketService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private CrmCfpNewcomerWelfareTaskService crmCfpNewcomerWelfareTaskService;
	
	@Resource
	private CommonHelper commonHelper;
	
	@Resource
    private CrmInvestorService crmInvestorService;
	
	/**
	 * V2.1.0_提现记录(account.queryAllWithdraw)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryAllWithdraw")
	@RequestLogging("V2.1.0_提现记录")
	@ResponseBody
	public BaseResponse queryAllWithdraw(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		conditions.put("userId",userId);
		Page<AcWithdrawApply> page  = new Page<AcWithdrawApply>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<AcWithdrawApply> datas =  null;
		try {
			datas = acWithdrawApplyService.queryWithdrawLog(page,conditions);
		} catch (Exception e) {
			LOGGER.error("V2.1.0_提现记录异常", e);
			return  new BaseResponse(errorCode,"V2.1.0_提现记录失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,QueryAllWithdrawResponse.class);
	}
	
	/**
	 * T呗奖励明细(收入、支出)(account.queryRewardDetail)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryRewardDetail")
	@RequestLogging("T呗奖励明细(收入、支出)")
	@ResponseBody
	public BaseResponse queryRewardDetail(@Valid MyAccountPageListRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		conditions.put("userId",userId);
		conditions.put("typeValue",req.getTypeValue());
		if("0".equals(req.getTypeValue())){
			Page<AcBalanceRecord> page  = new Page<AcBalanceRecord>(req.getPageIndex(),req.getPageSize());
			PaginatorResponse<AcBalanceRecord> datas =  null;
			try {
					datas = acBalanceRecordService.queryAllAccountDetails(page,conditions);
				} catch (Exception e) {
					LOGGER.error("T呗奖励全部明细异常", e);
					return  new BaseResponse(errorCode,"T呗奖励全部明细异常");
				}
			return AppResponseUtil.getSuccessResponse(datas,MyBalanceRecordListResponse.class);
		}else if("1".equals(req.getTypeValue())){
			Page<AcBalanceRecord> page  = new Page<AcBalanceRecord>(req.getPageIndex(),req.getPageSize());
			PaginatorResponse<AcBalanceRecord> datas =  null;
			try {
					datas = acBalanceRecordService.queryMyAccountDetails(page,conditions);
				} catch (Exception e) {
					LOGGER.error("T呗奖励明细(收入)异常", e);
					return  new BaseResponse(errorCode,"T呗奖励明细(收入)异常");
				}
			return AppResponseUtil.getSuccessResponse(datas,MyBalanceRecordListResponse.class);
		}else{
			Page<AcWithdrawApply> page  = new Page<AcWithdrawApply>(req.getPageIndex(),req.getPageSize());
			PaginatorResponse<AcWithdrawApply> datas =  null;
			try {
				datas = acWithdrawApplyService.queryExpenditure(page,conditions);
			} catch (Exception e) {
				LOGGER.error("T呗奖励明细(支出)异常", e);
				return  new BaseResponse(errorCode,"T呗奖励明细(支出)失败");
			}
			return AppResponseUtil.getSuccessResponse(datas,QueryRewardDetailPageListResponse.class);
		}
		
	}
	
	/**
	 * 猎财大师收支明细(account.queryIncomeAndOutDetail)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryIncomeAndOutDetail")
	@RequestLogging("猎财大师收支明细")
	@ResponseBody
	public BaseResponse queryIncomeAndOutDetail(@Valid MyAccountPageListRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		conditions.put("userId",userId);
		conditions.put("typeValue",req.getTypeValue());
		if("0".equals(req.getTypeValue())){
			Page<AcBalanceRecord> page  = new Page<AcBalanceRecord>(req.getPageIndex(),req.getPageSize());
			PaginatorResponse<AcBalanceRecord> datas =  null;
			try {
					datas = acBalanceRecordService.queryAllAccountDetails(page,conditions);
				} catch (Exception e) {
					LOGGER.error("猎财大师收支明细异常", e);
					return  new BaseResponse(errorCode,"猎财大师收支明细异常");
				}
			return AppResponseUtil.getSuccessResponse(datas,MyBalanceRecordListResponse.class);
		}else if("1".equals(req.getTypeValue())){
			Page<AcBalanceRecord> page  = new Page<AcBalanceRecord>(req.getPageIndex(),req.getPageSize());
			PaginatorResponse<AcBalanceRecord> datas =  null;
			try {
					datas = acBalanceRecordService.queryMyAccountDetails(page,conditions);
				} catch (Exception e) {
					LOGGER.error("猎财大师收支明细异常", e);
					return  new BaseResponse(errorCode,"猎财大师收支明细异常");
				}
			return AppResponseUtil.getSuccessResponse(datas,MyBalanceRecordListResponse.class);
		}else{
			Page<AcWithdrawApply> page  = new Page<AcWithdrawApply>(req.getPageIndex(),req.getPageSize());
			PaginatorResponse<AcWithdrawApply> datas =  null;
			try {
				datas = acWithdrawApplyService.queryExpenditure(page,conditions);
			} catch (Exception e) {
				LOGGER.error("T呗奖励明细(支出)异常", e);
				return  new BaseResponse(errorCode,"T呗奖励明细(支出)失败");
			}
			return AppResponseUtil.getSuccessResponse(datas,QueryRewardDetailPageListResponse.class);
		}
		
	}
	
	/**
	 * 提现记录明细(account.queryWithdrawLog)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryWithdrawLog")
	@RequestLogging("提现记录明细")
	@ResponseBody
	public BaseResponse queryWithdrawLog(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
//		if(StringUtils.isNotBlank(head.getAppKind())){
//			conditions.put("userType","investor".equals(head.getAppKind())?2:1);//1理财师，2投资者
//		}
		conditions.put("userId",userId);
		Page<AcWithdrawApply> page  = new Page<AcWithdrawApply>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<AcWithdrawApply> datas =  null;
		try {
			datas = acWithdrawApplyService.queryWithdrawLog(page,conditions);
		} catch (Exception e) {
			LOGGER.error("查询提现记录异常", e);
			return  new BaseResponse(errorCode,"查询提现记录失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,WithdrawApplyPageListResponse.class);
	}
	
	
	/**
	 * 提现请求(account.userWithdrawRequest)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/userWithdrawRequest")
	@RequestLogging("提现请求")
	@ResponseBody
	public BaseResponse userWithdrawRequest(@Valid UserWithdrawRequest req,BindingResult result,AppRequestHead head) throws Exception {
		LOGGER.info("提现请求  UserWithdrawRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		if(StringUtils.isNotBlank(head.getAppKind())){
			req.setUserType("investor".equals(head.getAppKind())?2:1);//1理财师，2投资者
		}
		BigDecimal amount = new BigDecimal(req.getAmount().replaceAll(" ", "")); 
		if(amount.compareTo(new BigDecimal(19))!=1){//提现金额不小于20
			return AppResponseUtil.getErrorBusi("faileWithdraw","最低提现金额为20元");
		}
		final String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		//先校验客户是否已经绑卡
		AcAccountBind rebind = accountbindService.selectAccountByUserId(userId);

		if(rebind!=null&&!"1".equals(rebind.getStatus().toString())){
			return AppResponseUtil.getErrorBusi("not_bindCard","用户未绑卡");
		}
		
		Map<String,String> resultStr = null;
		try {
			 resultStr = accountbindService.userWithdrawApply(req);
		} catch (Exception e) {
			LOGGER.error("提现请求异常", e);
			return  new BaseResponse(errorCode,"提现请求失败");
		}
		// 申请处理成功 推送消息
		if(resultStr!=null&&resultStr.get("code")!=null&& "00".equals(resultStr.get("code"))){
				final String platformName = req.getUserType() == 1 ? "猎财" : "T呗";
				final String withdrawAmount = req.getAmount();
				final int appType = req.getUserType();
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						AppTypeEnum appTypeEnum = (AppTypeEnum)EnumUtils.getEnumByKey(appType, AppTypeEnum.values());
						//通知栏
						String content = messageQueueService.queryMessageTemplate(MsgModuleEnum.WITHDRAWALAPPLY,appTypeEnum, DateUtils.format(new Date(), DateUtils.FORMAT_SHORT_CN_MM),withdrawAmount,platformName);
						if(appType==2){//猎财APP未定义提现记录页面
						pushMessageHelper.pushMessage(appTypeEnum, SmsTypeEnum.WITHDRAWRECORD, userId, "提现提醒", content, null, false);
						}
						//短信
						messageQueueService.sendMessageAndSysMsg(null,userId,appTypeEnum,MsgModuleEnum.WITHDRAWALAPPLY,true,DateUtils.format(new Date(), DateUtils.FORMAT_SHORT_CN_MM),withdrawAmount,platformName);
					}
				});
		}
		if(resultStr!=null&&resultStr.get("code")!=null&&!"00".equals(resultStr.get("code"))){
			return AppResponseUtil.getErrorBusi("withdraw_error",resultStr.get("code"));
		}
		
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 提现查询银行卡信息(account.getWithdrawBankCard)
	 * @param result
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping("/getWithdrawBankCard")
	@ResponseBody
	public BaseResponse getWithdrawBankCard(AppRequestHead head,
			HttpServletRequest request) {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("getWithdrawBankCard|head=").append(head.toString());
		try {
			WithdrawBankCardResponse rlt = accountbindService.queryWithdrawBankCard(userId);
			if (rlt!=null) {
				return AppResponseUtil.getSuccessResponse(rlt);
			} else {
				return AppResponseUtil.getErrorBusi("not_bindCard","用户未绑卡");
			}
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			LOGGER.info(logsb.toString());
		}
	}
	
	
	/**
	 * 累计提现金额(account.getWithdrawSummary)
	 * @param result
	 * @param head
	 * @param request
	 * @return
	 */
	@RequestMapping("/getWithdrawSummary")
	@ResponseBody
	public BaseResponse getWithdrawSummary(AppRequestHead head) {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Map<String,String> map = new HashMap<String,String>();
		Double amout = accountbindService.queryWithdrawSummary(userId);
		map.put("outTotalAmount", amout==null?"0.00":NumberUtils.getFormat(amout, "0.00"));
		
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 账户明细 (account.myaccountDetail.pageList)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/myaccountDetail/pageList")
	@RequestLogging("/myaccountDetail/pageList(账户明细)")
	@ResponseBody
	public BaseResponse getMyAccountDetail(@Valid MyAccountPageListRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		
		Page<AcBalanceRecord> page  = new Page<AcBalanceRecord>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<AcBalanceRecord> datas =  null;
		
		if(req.getTypeValue()==null){
			req.setTypeValue("1");//空值默认查询全部
		}
		
		//提现用的TypeValue都是2
		if(StringUtils.isNotBlank(head.getAppKind())&&!"2".equals(req.getTypeValue())){
			conditions.put("userType","investor".equals(head.getAppKind())?2:1);//1理财师，2投资者
		}
		conditions.put("userId",userId);
		//查询(全部明细)时transType设为null
		if(StringUtils.isNotBlank(req.getTypeValue())){
			if("1".equals(req.getTypeValue())){
				try {
					datas = acBalanceRecordService.queryMyAccountDetails2(page,conditions);
				} catch (Exception e) {
					LOGGER.error("查询账户明细异常", e);
					return  new BaseResponse(errorCode,"查询账户明细失败");
				}
			}else{
				conditions.put("transType",req.getTypeValue());
				try {
					datas = acBalanceRecordService.queryMyAccountDetails(page,conditions);
				} catch (Exception e) {
					LOGGER.error("查询账户明细异常", e);
					return  new BaseResponse(errorCode,"查询账户明细失败");
				}
			}
		}
		
		return AppResponseUtil.getSuccessResponse(datas,MyAccountPageListResponse.class);
	}
	
	/**
	 * 查询账户类型(account.queryAccountType)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryAccountType")
	@ResponseBody
	public BaseResponse queryAccountType(AppRequestHead head) throws Exception {
		
		String userType= "investor".equals(head.getAppKind())?"2":"1";

		try {
			List<AcAccountTypeReponse> typeList = accountbindService.queryAllAccountType(userType);
			return AppResponseUtil.getSuccessResponse(typeList);
		}catch (Exception e) {
			LOGGER.error("查询账户类型异常", e);
			return  new BaseResponse(errorCode,"查询账户类型失败");
		}
	}
	
	/**
	 * 我的账户(account.myaccount)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/myaccount")
	@ResponseBody
	public BaseResponse getMyAccount(AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		try {
				AcAccountBind querybind = new AcAccountBind();
				querybind.setUserId(userId);
				AcAccountBind returnbind = accountbindService.selectOne(querybind);
				MyAccount rlt = new MyAccount();
				//用户是否是理财师
				CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
				if(cfp!=null){
					rlt.setUserType("0");
				}else{
					rlt.setUserType("2");
				}
				//累计收益
				Double totalIncome = 0.0;
				totalIncome = acBalanceRecordService.queryTotalIncome(userId);
				rlt.setTotalIncome(NumberUtils.getFormat(totalIncome, "0.00"));
				if(returnbind!=null){
					rlt.setTotalAmount(NumberUtils.getFormat(Double.parseDouble(returnbind.getTotalAmount()), "0.00"));
					return AppResponseUtil.getSuccessResponse(rlt);
				}else{
					rlt.setTotalAmount("0.00");
					return AppResponseUtil.getSuccessResponse(rlt);
				}
		}catch (Exception e) {
			LOGGER.error("我的账户异常", e);
			return  new BaseResponse(errorCode,"查询失败");
		}
	}
	
	/**
	 * T呗账户余额(account.getAccountBalance)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getAccountBalance")
	@ResponseBody
	public BaseResponse getAccountBalance(AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		try {
			AcAccountBind querybind = new AcAccountBind();
			querybind.setUserId(userId);
			AcAccountBind account = accountbindService.selectOne(querybind);
			TBeiAccountBalance rlt = new TBeiAccountBalance();
			rlt.setRewardBalance(NumberUtils.getFormat(Double.parseDouble(account.getTotalAmount()), "0.00"));
			//奖励收入
			Double rewardIncome = acBalanceRecordService.queryRewardIncome(userId);
			rlt.setRewardIncome(NumberUtils.getFormat(rewardIncome, "0.00"));
			//奖励收入
			Double rewardOut = acBalanceRecordService.queryRewardOut(userId);
			rlt.setRewardOut(NumberUtils.getFormat(rewardOut, "0.00"));
			return AppResponseUtil.getSuccessResponse(rlt);
		}catch (Exception e) {
			LOGGER.error("T呗账户余额异常", e);
			return  new BaseResponse(errorCode,"查询失败");
		}
	}
	
	/**
	 * V2.1.0_猎财大师资金明细_账户余额(account.getLieCaiBalance)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getLieCaiBalance")
	@ResponseBody
	public BaseResponse getLieCaiBalance(AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		try {
			AcAccountBind querybind = new AcAccountBind();
			querybind.setUserId(userId);
			AcAccountBind account = accountbindService.selectOne(querybind);
			LieCainAccountBalance rlt = new LieCainAccountBalance();
			rlt.setAccountBalance(NumberUtils.getFormat(Double.parseDouble(account.getTotalAmount()), "0.00"));
			//累计收入
			Double rewardIncome = acBalanceRecordService.queryRewardIncome(userId);
			rlt.setIncomeSummary(NumberUtils.getFormat(rewardIncome, "0.00"));
			//累计收入
			Double rewardOut = acBalanceRecordService.queryRewardOut(userId);
			rlt.setOutSummary(NumberUtils.getFormat(rewardOut, "0.00"));
			return AppResponseUtil.getSuccessResponse(rlt);
		}catch (Exception e) {
			LOGGER.error("猎财账户余额异常", e);
			return  new BaseResponse(errorCode,"猎财账户余额查询失败");
		}
	}
	
	/**
	 * 查询绑卡信息(account.getUserBindCard)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getUserBindCard")
	@ResponseBody
	public BaseResponse getUserBindCard(AppRequestHead head) throws Exception {
		if(org.springframework.util.StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		try {
				AcAccountBind querybind = new AcAccountBind();
				querybind.setUserId(userId);
				AcAccountBind rebind = accountbindService.selectOne(querybind);
				if(rebind!=null){
					AcBankImageResponse resp = accountbindService.selectBankImage(rebind.getBankCode());
					if(resp!=null){
						rebind.setImage(resp.getImage());
						rebind.setIco(resp.getIco());
					}else{
						rebind.setImage(sysConfigService.getValuesByKey("acc_bank_image"));
						rebind.setIco(sysConfigService.getValuesByKey("acc_bank_ico"));
					}
					return AppResponseUtil.getSuccessResponse(rebind,AcAccountBindReponse.class);
				}
				return  new BaseResponse(errorCode,"没有绑卡信息");
		}catch (Exception e) {
			LOGGER.error("查询绑卡信息异常", e);
			return  new BaseResponse(errorCode,"查询绑卡信息失败");
		}
	}

	/**
	 * 添加银行卡(account.addBankCard)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addBankCard")
	@ResponseBody
	public BaseResponse addBankCard(@Valid AddBankCardRequest req,BindingResult result,AppRequestHead head) throws Exception {
		LOGGER.info("添加银行卡  AddBankCardRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		//身份证英文转换为大写
        req.setIdCard(req.getIdCard().toUpperCase());
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
		req.setUserType("investor".equals(head.getAppKind())?2:1);//1理财师，2投资者
		//添加校验用户是否已经绑卡
		AcAccountBind bind = accountbindService.selectAccountByUserId(userId);
		if(bind!=null&&"1".equals(bind.getStatus().toString())){
			return AppResponseUtil.getErrorBusi("already_bind","用户已经绑卡");
		}
		String bankCard = bind!=null?bind.getBankCard():null;
		String idCard = bind!=null?bind.getIdCard():null;
		//判断身份证的唯一性
		List<AcAccountBind> idCardList = accountbindService.checkIdCardOnly(req.getIdCard());
		
		if(idCardList.size()>0){
			return AppResponseUtil.getErrorBusi("bankcheck_error","身份证已绑定其他账号,请更换");
		}
		
		//获取当日日期
		String today = DateUtils.getNow(DateUtils.FORMAT_SHORT);
		//匹配数据库设置绑卡次数
		String times = sysConfigService.getValuesByKey(SysConfigConstant.LIMIT_ACCOUNT_BIND_TIMES);
		if(times!=null&&Integer.parseInt(times)<=bind.getBindTimes()&&today.equals(bind.getBindDate())){
			LOGGER.error("手机号【{}】绑卡超过当日最大次数【{}次】",req.getMobile(),times);
			return  new BaseResponse(bindErrorCode,String.format("今日已连续%s次绑卡失败，请明日尝试或联系客服解决",times));
//			return AppResponseUtil.getErrorBusi("bankcheck_error",String.format("绑卡错误超过%s次,请24小时后再操作或联系客服",times));
		}
		//更新每日绑卡次数
		if(!today.equals(bind.getBindDate())){
			bind.setBindDate(today);
			bind.setBindTimes(1);
			accountbindService.updateBindTimes(bind);
		}else{
			bind.setBindTimes(bind.getBindTimes()+1);
			accountbindService.updateBindTimes(bind);
		}
		
		//先检查银行卡(排除信用卡)
		Map<String,String> nature = null;
		try {
			nature = acBankCardInfoService.checkBankCardAndBankName(req);
		} catch (Exception e) {
			LOGGER.error("银行卡属性接口检查异常",e);
			return AppResponseUtil.getErrorBusi("bankcheck_error","银行卡属性接口检查异常");
		} 
		if(nature!=null&&nature.get("code")!=null&&!"00".equals(nature.get("code"))){
			return AppResponseUtil.getErrorBusi("nature_error",nature.get("code"));
		}
		
		//再进行实名验证，再保存银行卡信息
		Map<String,String> resultStr = null;
		try {
			resultStr = acBankCardInfoService.verifyRealName(req);
		} catch (Exception e) {
			LOGGER.error("实名验证异常",e);
			return AppResponseUtil.getErrorBusi("savecard_error","实名验证异常");
		} 
		if(resultStr!=null&&resultStr.get("code")!=null&&!"00".equals(resultStr.get("code"))){
			String errorMsg = EnumUtils.getValueByKey(Integer.parseInt(resultStr.get("code")),AcVerifyRealNameCodeEnum.values());
			CrmUserInfo crm = crmUserInfoService.queryUserInfoByUserId(userId);
			LOGGER.error("华付绑卡失败,手机号：【{}】,原因：【{}】",crm.getMobile(),errorMsg);
			if("98".equals(resultStr.get("code"))||"05".equals(resultStr.get("code"))||"06".equals(resultStr.get("code"))){
				return  new BaseResponse(bindErrorCode,String.format("银行卡或身份认证未通过，请尝试更换银行卡、身份证或联系客服解决【错误码%s】=还可以尝试%s次",resultStr.get("code"),Integer.parseInt(times)-bind.getBindTimes()));
			}else if("07".equals(resultStr.get("code"))){
				return  new BaseResponse(bindErrorCode,String.format("银行卡未开通无卡支付或在线支付功能，请更换银行卡再次尝试【错误码%s】=还可以尝试%s次",resultStr.get("code"),Integer.parseInt(times)-bind.getBindTimes()));
			}else{
				if(errorMsg==null||"".equals(errorMsg)){
					return AppResponseUtil.getErrorBusi("verify_error","银行卡或身份认证未通过,还可以尝试"+(Integer.parseInt(times)-bind.getBindTimes())+"次");
				}else{
					return AppResponseUtil.getErrorBusi("verify_error",errorMsg+",还可以尝试"+(Integer.parseInt(times)-bind.getBindTimes())+"次");
				}
			}
			
		}else if(resultStr!=null&&resultStr.get("code")==null){
			return AppResponseUtil.getErrorBusi("code_null","实名验证异常(code为空)");
		}
		String bankname = AcAccountUtils.matchbankName(nature.get("bankName"));
		req.setBankName(bankname);
		AcBankCodeResponse bank = accountbindService.queryBankByBankName(bankname);
		if(bank!=null){
			req.setBankCode(bank.getBankCode());
			req.setBankId(bank.getBankId());
		}
		//保存银行卡信息
		try {
			acBankCardInfoService.insertBankCard(req);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("保存银行卡信息失败");
			return AppResponseUtil.getErrorBusi("savecard_error","保存银行卡信息失败");
		}
		
		//TODO 绑卡认证新手任务红包
		CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(userId);
		try {
			LOGGER.info("新手福利绑卡认证 红包： userInfo = {}" , userInfo );
//			actRedpacketService.customerTaskRedPacekt(InvestorNewcomerTaskEnum.BIND_CARD, userInfo);
			CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
			if(cfp != null && bankCard==null && idCard==null) {
				boolean isNewComer = crmCfpNewcomerWelfareTaskService.isExistUser(userId);
				if(!isNewComer){
					actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.CFPLANNER_NEWCOMERWELFARE_BIND_CARD, userInfo);
				}else{
					//新手福利六连送--绑卡
					crmCfpNewcomerWelfareTaskService.sendTaskReward(userId, CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_BINDCARD);
				}	
			}
		} catch (Exception e) {
			LOGGER.warn("newcomer welfare exception userInfo={}" ,userInfo, e.getMessage());
		}
		
		req.setHaveBind(bankCard==null?"0":"1");
		//T呗、猎财重新生成二维码
		creatNewRcCode(userInfo,userId);
		return AppResponseUtil.getSuccessResponse(req,AcAccountBindSuccessReponse.class);
		
	}
	
	private void creatNewRcCode(final CrmUserInfo userInfo,final String userId) throws IOException {
		ThreadpoolService.execute(new Runnable() {			
			@Override
			public void run() {
				//T呗
				org.springframework.core.io.Resource cures = new ClassPathResource("icons/invest.jpg");
				String culogoPath = null;
				try {
					culogoPath = cures.getFile().getPath();
				} catch (IOException e) {
					LOGGER.info("T呗实名验证获取二维码路径异常!");
				}
				ShareContent cuContent = commonHelper.getWechatShareLcsRcCus(userInfo.getMobile(),userInfo.getUserName());
				String cuqrcode = creatRcCode(culogoPath,cuContent.getShareLink());						
				crmInvestorService.updateInvQrByUserId(userId,cuqrcode);
				//猎财
				org.springframework.core.io.Resource res = new ClassPathResource("icons/lcs.jpg");
				String logoPath = null;
				try {
					logoPath = res.getFile().getPath();
				} catch (IOException e) {
					LOGGER.info("猎财实名验证获取二维码路径异常!");
				}
				ShareContent shareContent = commonHelper.getWechatShareRclcs(userInfo.getMobile(), userInfo.getUserName());
				String qrcode = creatRcCode(logoPath,shareContent.getShareLink());	
				crmCfplannerService.updateCfpQrByUserId(userId,qrcode);
			}
		});
	}

	/**
	 * 查询支付机构支持的银行(account.queryAllBank)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryAllBank")
	@ResponseBody
	public BaseResponse queryAllBank(AppRequestHead head) throws Exception {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("queryAllBank").append("|token=").append(head.getToken());
		try {
				List<AcBankCodeResponse> rlt = accountbindService.queryAllBank();
				if(rlt!=null){
					return AppResponseUtil.getSuccessResponse(rlt);
				}
				return  new BaseResponse(errorCode,"数据为空");
		}catch (Exception e) {
			LOGGER.error("查询银行列表异常", e);
			return  new BaseResponse(errorCode,"查询银行列表失败");
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			LOGGER.info(logsb.toString());
		}
	}
	
	
	/**
	 * 查询所有省份(account.queryAllProvince)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryAllProvince")
	@ResponseBody
	public BaseResponse queryAllProvince(AppRequestHead head) throws Exception {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("queryAllProvince").append("|token=").append(head.getToken());
		try {
				List<ProvinceInfoResponse> rlt = accountbindService.queryAllProvince();
				if(rlt!=null){
					return AppResponseUtil.getSuccessResponse(rlt);
				}
				return  new BaseResponse(errorCode,"数据为空");
		}catch (Exception e) {
			LOGGER.error("查询所有省份异常", e);
			return  new BaseResponse(errorCode,"查询省份失败");
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			LOGGER.info(logsb.toString());
		}
	}
	
	/**
	 * 查询城市(account.queryCityByProvince)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryCityByProvince")
	@ResponseBody
	public BaseResponse queryCityByProvince(String token,String  provinceId,HttpServletResponse response) {
		if(org.springframework.util.StringUtils.isEmpty(token)){
			return  new BaseResponse(errorCode,"token不能为空");
		}else if(org.springframework.util.StringUtils.isEmpty( provinceId)){
			return  new BaseResponse(errorCode,"省份Id不能为空");
		}
		long start = System.currentTimeMillis();
		String userId = JsonWebTokenHepler.getUserIdByToken(token);
		StringBuilder logsb = new StringBuilder();
		logsb.append("queryCityByProvince|userId=").append(userId)
				.append("|token=").append(token).append("|provinceId=").append( provinceId);
		try {
			List<CityInfoResponse> rlt = accountbindService.queryCityByProvince( provinceId);
			if(rlt!=null){
				return AppResponseUtil.getSuccessResponse(rlt);
			}
			return  new BaseResponse(errorCode,"数据为空");
		}catch (Exception e) {
			LOGGER.error("查询城市异常", e);
			return  new BaseResponse(errorCode,"查询城市失败");
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			LOGGER.info(logsb.toString());
		}
	}
	
	/**
	 * 检查用户是否设置支付密码(account.verifyPayPwdState)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/verifyPayPwdState")
	@ResponseBody
	public BaseResponse verifyPayPwdState(AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("rlt", accountbindService.doVerifyPayPwdState(head));
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 验证原密码(account.verifyPayPwd)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/verifyPayPwd")
	@ResponseBody
	public BaseResponse verifyPayPwd(@Valid VerifyPayPwdRequest req, BindingResult result,AppRequestHead head) throws Exception {
		LOGGER.info("验证原密码  VerifyPayPwdRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		AcAccountBind rebind = null;
		try {
			rebind = accountbindService.selectAccountByUserId(userId);
		} catch (Exception e) {
			LOGGER.error("根据用户Id获取绑定信息失败-",e);
			return new BaseResponse(errorCode,"用户获取绑定信息失败");
		}
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		if(rebind!=null&&MD5.crypt(req.getPwd()).equals(rebind.getTranPwd())){
			map.put("rlt", true);
		}else{
			map.put("rlt", false);
		}
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 重置支付密码-身份证验证(account.verifyIdCard)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/verifyIdCard")
	@ResponseBody
	public BaseResponse verifyIdCard(@Valid VerityIdCardRequest req, BindingResult result,AppRequestHead head) throws Exception {
		LOGGER.info("重置支付密码-身份证验证 VerityIdCardRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		AcAccountBind rebind = null;
		try {
			rebind = accountbindService.selectAccountByUserId(userId);
		} catch (Exception e) {
			LOGGER.error("根据用户Id获取绑定信息失败-",e);
			return new BaseResponse(errorCode,"用户获取绑定信息失败");
		}
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		if(rebind!=null){
			if(!req.getName().equals(rebind.getUserName())){
				return AppResponseUtil.getErrorBusi("name_error","姓名错误");
			}else if(!req.getIdCardNo().equals(rebind.getIdCard())){
				return AppResponseUtil.getErrorBusi("idcard_error","身份证错误");
			}
		}else{
			map.put("rlt", false);
			return AppResponseUtil.getSuccessResponse(map);
		}
		map.put("rlt", true);
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 重置支付密码-点击手机发送验证码(account.sendVcode)
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/sendVcode")
	@ResponseBody
	public BaseResponse sendVcode(AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		boolean flag = false;
		CrmUserInfo crm = crmUserInfoService.queryUserInfoByUserId(userId);
		if(crm!=null&&crm.getMobile()==null){
			return AppResponseUtil.getErrorBusi("mobile_error","用户手机号码为空");
			
		}else if(crm!=null&&crm.getMobile()!=null&&crm.getMobile().length()>0){
			try {
				flag = accountbindService.sendVcode(crm.getMobile(),head);
			} catch (Exception e) {
				return AppResponseUtil.getErrorBusi("send_error","发送验证码异常");
			}
		} 
		if(!flag){
			return AppResponseUtil.getErrorBusi("send_error","发送验证码失败");
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 重置支付密码-输入手机验证(account.inputVcode)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/inputVcode")
	@ResponseBody
	public BaseResponse verifyVcode(@Valid 	InputVcodeRequest req, BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		boolean flag = false;
		CrmUserInfo crm = crmUserInfoService.queryUserInfoByUserId(userId);
		
		if(crm!=null&&crm.getMobile()==null){
			return AppResponseUtil.getErrorBusi("mobile_error","用户手机号码为空");
			
		}else if(crm!=null&&crm.getMobile()!=null&&crm.getMobile().length()>0){
			try {
				
				if(req.getMobile()!=null){
					crm.setMobile(req.getMobile());
					flag = accountbindService.checkVerifyCode2(crm.getMobile(),req.getVcode());
				}else{
					flag = accountbindService.checkVerifyCode(crm.getMobile(),req.getVcode());
				}
				
			} catch (Exception e) {
				return AppResponseUtil.getErrorBusi("send_error","校验验证码异常");
			}
		} 
		String resetPayPwdToken = null;
		if(!flag){
			return AppResponseUtil.getErrorBusi("send_error","验证码不正确");
		}else{
			resetPayPwdToken =generatePayPwdToken(crm.getMobile(),AppUtils.getAppType(head.getOrgNumber()));
		}
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("resetPayPwdToken", resetPayPwdToken);
		map.put("result", "true");
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	
	/**
	 * 重置支付密码(account.resetPayPwd)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/resetPayPwd")
	@ResponseBody
	public BaseResponse resetPayPwd(@Valid ResetPayPwdRequest req, BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		CrmUserInfo crm = crmUserInfoService.queryUserInfoByUserId(userId);
		//校验token
		boolean flag = checkVerifyCode(crm.getMobile(),req.getResetPayPwdToken());
		if(flag){
			accountbindService.resetPayPwd(req);
		}else{
			return AppResponseUtil.getErrorBusi("resetPayPwdToken_error","重置密码token校验错误");
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	
	/**
	 * 初始化支付密码(account.initPayPwd)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/initPayPwd")
	@ResponseBody
	public BaseResponse initPayPwd(@Valid InitPayPwdRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		AcAccountBind bind = accountbindService.selectAccountByUserId(userId);
		if(bind!=null&&bind.getTranPwd()!=null&&bind.getTranPwd().length()>0){
			return AppResponseUtil.getErrorBusi("payPwd_exists","交易密码已设置");
		}else if(bind!=null&&bind.getStatus()!=1){
			return AppResponseUtil.getErrorBusi("nobind_error","未绑卡不能初始化支付密码");
		}else{
			AcAccountBind acbind = new AcAccountBind();
			acbind.setUserId(userId);
			acbind.setTranPwd(MD5.crypt(req.getPwd()));
			int in = accountbindService.update(acbind);
			if(in>0){
				return AppResponseUtil.getSuccessResponse();
			}else{
				return new BaseResponse(errorCode,"更新失败，请联系客服");
			}
		}
	}
	
	/**
	 * 修改交易密码(account.modifyPayPwd)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/modifyPayPwd")
	@ResponseBody
	public BaseResponse modifyPayPwd(@Valid TwoPwdRequest req,BindingResult result,AppRequestHead head) {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		//校验原密码是否一致
		boolean flag = accountbindService.doVerifyPayPwdSame(req,head);
		if(flag){
			AcAccountBind bind = new AcAccountBind();
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			bind.setUserId(userId);
			bind.setTranPwd(MD5.crypt(req.getNewPwd()));
			int in = accountbindService.update(bind);
			if(in>0){
				//短信提示
				/*CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);
				final String mobile = crmUserInfo.getMobile();
				final AppTypeEnum appTyp =  AppUtils.getAppType(head.getOrgNumber());
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						messageQueueService.sendSingleMessage(mobile, appTyp, MsgModuleEnum.UPDATEPAYPWDBYOLDPWD,DateUtils.format(new Date(), DateUtils.FORMAT_LONG_CN));
					}
				});*/
				return AppResponseUtil.getSuccessResponse();
			}else{
				return new BaseResponse(errorCode,"更新失败，请联系客服");
			}
		}else{
			return AppResponseUtil.getErrorBusi("payPwd_noSame","原密码不一致");
		}
	}
	
	
	/**
	 * 个人设置(account.personcenter.setting)
	 * @param head
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("personcenter/setting")
	@ResponseBody
	public BaseResponse setting(AppRequestHead head) throws Exception {
		if(StringUtils.isEmpty(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		AcAccountBind bind = accountbindService.selectAccountByUserId(userId);
		try {
			boolean auth = accountbindService.queryAuthentication(userId);
			Map<String,Object> rlt = new HashMap<String,Object>();
			if(bind!=null&&bind.getBankCard()!=null&&bind.getIdCard()!=null){
				rlt.put("onceMoreBindCard", true);//解绑的
			}else{
				rlt.put("onceMoreBindCard", false);//未绑过卡
			}
			if(auth){
				rlt.put("bundBankcard", true);//已绑卡
				return AppResponseUtil.getSuccessResponse(rlt);
			}else{
				rlt.put("bundBankcard", false);//未绑卡
				return AppResponseUtil.getSuccessResponse(rlt);
			}
		} catch (Exception e) {
			return new BaseResponse(errorCode,"更新失败，请联系客服");
		}
	}
	
	/**
	 * 查询银行名称(account.queryBankName)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryBankName")
	@ResponseBody
	public BaseResponse queryBankName(String token,String bankCard) {
		if(org.springframework.util.StringUtils.isEmpty(token)){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		long start = System.currentTimeMillis();
		String userId = JsonWebTokenHepler.getUserIdByToken(token);
		StringBuilder logsb = new StringBuilder();
		logsb.append("queryBankName|userId=").append(userId)
				.append("|token=").append(token).append("|bankCard=").append( bankCard);
		try {
			
			Map<String,String> returnStr = acBankCardInfoService.queryBankName(bankCard);
			if(returnStr.get("bankName")!=null){
				String bankname = AcAccountUtils.matchbankName(returnStr.get("bankName"));
				AcBankCodeResponse bankCode = accountbindService.queryBankByBankName(bankname);
				return AppResponseUtil.getSuccessResponse(bankCode);
			}
			return  new BaseResponse(errorCode,"数据为空");
		}catch (Exception e) {
			LOGGER.error("查询银行名称异常", e);
			return  new BaseResponse(errorCode,"查询银行名称失败");
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			LOGGER.info(logsb.toString());
		}
	}
	
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
	 * 重置密码-随机生成resetPayPwdToken验证码
	 * @param @param resetPayPwdToken验证码长度
	 * @param binder
	 */
	public static String getRandomString(int length) { 
	    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }  
	

	
	/**
	 * 生成重置密码token
	 * @param mobile	手机号码
	 * @param appType	短信模板ID
	 */
	public String generatePayPwdToken(String mobile,AppTypeEnum appType){
		//生成15位数字的验证码
		String resetPayPwdToken = getRandomString(15);
		if(verifyCodeMap.size() >= 10000){
			//将过期的验证码清除
			for(Map.Entry<String, Long> entry: verifyCodeMap.entrySet()){
				if(System.currentTimeMillis() > entry.getValue()){
					verifyCodeMap.remove(entry.getKey());
				}
			}
		}
		//将电话号码，短信模板,验证码拼接成待存储的key
		String toSaveKey = mobile+"#"+MsgModuleEnum.UPDATETRADEPWD.getValue()+"#"+resetPayPwdToken;
		verifyCodeMap.put(toSaveKey, System.currentTimeMillis()+TimeSetConstants.MSGVERIFYCODE_VALID_DATE); //有效期
		
		return resetPayPwdToken;

	}
	
	
	/**
	 * 校验重置密码token是否正确
	 * @param resetPayPwdToken	验证码
	 * @return
	 */
	public boolean checkVerifyCode(String userMobile, String resetPayPwdToken){
		String toSaveKey = userMobile+"#"+MsgModuleEnum.UPDATETRADEPWD.getValue()+"#"+resetPayPwdToken;
		if(verifyCodeMap.containsKey(toSaveKey) && verifyCodeMap.get(toSaveKey) >= System.currentTimeMillis()){
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 账户余额
	 */
	@RequestMapping("/accountBalance")
	@ResponseBody
	public BaseResponse accountBalance(AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		AccountBalanceDetailResp rlt = new AccountBalanceDetailResp();
		rlt.setAccountBalance(accountbindService.queryAccountBalance(userId));
		
		Double fee = feeService.queryFeeByUserId(userId, null);
		Double allowance = feeService.queryAllowanceByUserId(userId, null);
		Double activityReward = accountbindService.queryCfpActivityReward(userId, null);
		Double leaderReward = feeService.queryLeaderRewardByUserId(userId, null);
		
		List<ProfixTypeListRespsone> list = new ArrayList<ProfixTypeListRespsone>();
		ProfixTypeListRespsone re1 = new ProfixTypeListRespsone();
		re1.setAmount(WebUtil.getDefaultFormat(fee));
		re1.setProfixType("1");
		re1.setProfixTypeName("客户销售佣金");
		
		ProfixTypeListRespsone re2 = new ProfixTypeListRespsone();
		re2.setAmount(WebUtil.getDefaultFormat(allowance));
		re2.setProfixType("2");
		re2.setProfixTypeName("理财师推荐奖励");
		
		ProfixTypeListRespsone re3 = new ProfixTypeListRespsone();
		re3.setAmount(WebUtil.getDefaultFormat(activityReward));
		re3.setProfixType("3");
		re3.setProfixTypeName("活动奖励");
		
		ProfixTypeListRespsone re4 = new ProfixTypeListRespsone();
		re4.setAmount(WebUtil.getDefaultFormat(leaderReward));
		re4.setProfixType("4");
		re4.setProfixTypeName("leader奖励");
		
		list.add(re1);
		list.add(re2);
		list.add(re3);
		list.add(re4);
		
		rlt.setProfixList(list);
		Double totalProfix = fee + allowance + activityReward + leaderReward;
		rlt.setTotalProfix(totalProfix);
		return AppResponseUtil.getSuccessResponse(rlt, AccountBalanceDetailResponse.class);
		
	}
	
	/**
	 * 账户余额月份收益总计列表
	 */
	@RequestMapping("/monthProfixTotalList")
	@ResponseBody
	public BaseResponse monthProfixTotalList(MonthProfixDetailRequest req, AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
		Page<MonthProfixTotalListResp> page  = new Page<MonthProfixTotalListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<MonthProfixTotalListResp> rlt = acBalanceRecordService.queryProfixTotalList(req,page);
		return AppResponseUtil.getSuccessResponse(rlt, MonthProfixTotalListResponse.class);	
		
	}
	
	/**
	 * 月度收益统计
	 */
	@RequestMapping("/monthProfixStatistics")
	@ResponseBody
	public BaseResponse monthProfixStatistics(@Valid MonthProfixStatisticsRequest req, BindingResult result, AppRequestHead head) throws Exception {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		MonthProfixStatisticsResponse rlt = new MonthProfixStatisticsResponse();
		
		TCFeedetail detail = feeDetailService.queryFeedetailByUserIdAndMonthLimitOne(userId, req.getMonth());
		
		if(detail == null) {
			rlt.setIsGrant("0");
			rlt.setGrantDesc("");
		} else {
			boolean flag = feeDetailService.isGrantFeeByMonth(req.getMonth().replaceAll("-", ""));
			if(flag) {
				rlt.setIsGrant("1");
				rlt.setGrantDesc("已发放");
			} else {
		        String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
		        Calendar c = Calendar.getInstance();
			    c.add(Calendar.MONTH, -1);
			    String lastMonth = new SimpleDateFormat("yyyy-MM").format(c.getTime());
				if(thisMonth.equals(req.getMonth().trim())) {
					rlt.setGrantDesc("下月15号发放");
				} else if(lastMonth.equals(req.getMonth().trim())) {
					rlt.setGrantDesc("本月15号发放");
				} else {
					rlt.setGrantDesc("");
				}
				rlt.setIsGrant("0");
			}
		}
		
		Double fee = feeService.queryFeeByUserId(userId, req.getMonth());
		Double allowance = feeService.queryAllowanceByUserId(userId, req.getMonth());
		Double activityReward = accountbindService.queryCfpActivityReward(userId, req.getMonth());
		Double leaderReward = feeService.queryLeaderRewardByUserId(userId, req.getMonth());
		
		List<ProfixTypeListRespsone> list = new ArrayList<ProfixTypeListRespsone>();
		ProfixTypeListRespsone re1 = new ProfixTypeListRespsone();
		re1.setAmount(WebUtil.getDefaultFormat(fee));
		re1.setProfixType("1");
		re1.setProfixTypeName("客户销售佣金");
		
		ProfixTypeListRespsone re2 = new ProfixTypeListRespsone();
		re2.setAmount(WebUtil.getDefaultFormat(allowance));
		re2.setProfixType("2");
		re2.setProfixTypeName("理财师推荐奖励");
		
		ProfixTypeListRespsone re3 = new ProfixTypeListRespsone();
		re3.setAmount(WebUtil.getDefaultFormat(activityReward));
		re3.setProfixType("3");
		re3.setProfixTypeName("活动奖励");
		
		ProfixTypeListRespsone re4 = new ProfixTypeListRespsone();
		re4.setAmount(WebUtil.getDefaultFormat(leaderReward));
		re4.setProfixType("4");
		re4.setProfixTypeName("leader奖励");
		
		list.add(re1);
		list.add(re2);
		list.add(re3);
		list.add(re4);
		
		rlt.setProfixList(list);
		
		Double totalProfix = fee + allowance + activityReward + leaderReward;
		rlt.setTotalProfix(WebUtil.getDefaultFormat(totalProfix));
		return AppResponseUtil.getSuccessResponse(rlt);
		
	}
	
	/**
	 * 月度收益明细列表
	 */
	@RequestMapping("/monthProfixDetailList")
	@ResponseBody
	public BaseResponse monthProfixDetailList(@Valid MonthProfixDetailRequest req, BindingResult result, AppRequestHead head) throws Exception {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
		Page<MonthProfixDetailListResp> page  = new Page<MonthProfixDetailListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<MonthProfixDetailListResp> rlt = acBalanceRecordService.queryMonthProfixDetailList(req,page);
		return AppResponseUtil.getSuccessResponse(rlt,MonthProfixDetailListResponse.class);		
		
	}
	
	/**
	 * 月度收益明细列表v2.1
	 */
	@RequestMapping("/monthProfixDetailList/2.1")
	@ResponseBody
	public BaseResponse monthProfixDetailListNew(@Valid MonthProfixDetailRequestNew req, BindingResult result, AppRequestHead head) throws Exception {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
		Page<MonthProfixDetailListResp> page  = new Page<MonthProfixDetailListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<MonthProfixDetailListResp> rlt = acBalanceRecordService.queryMonthProfixDetailListNew(req,page);
		return AppResponseUtil.getSuccessResponse(rlt,MonthProfixDetailListResponseNew.class);		
		
	}
	
	/**
	 * 月度收益统计v2.1
	 */
	@RequestMapping("/monthProfixStatistics/2.1")
	@ResponseBody
	public BaseResponse monthProfixStatisticsNew(@Valid MonthProfixStatisticsRequest req, BindingResult result, AppRequestHead head) throws Exception {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		MonthProfixStatisticsResponseNew rlt = new MonthProfixStatisticsResponseNew();
		
		
		Double fee = feeService.queryFeeByUserId(userId, req.getMonth()) + feeService.queryOfflineFeeByUserId(userId, req.getMonth());
		Double allowance = feeService.queryAllowanceByUserId(userId, req.getMonth());
		Double activityReward = accountbindService.queryCfpActivityRewardNew(userId, req.getMonth());
		Double leaderReward = feeService.queryLeaderRewardByUserIdNew(userId, req.getMonth());
		Double hongbao = accountbindService.queryHongbaoProfit(userId, req.getMonth());
		
		Double grantedAmount = accountbindService.queryGrantedAmount(userId, req.getMonth());
		Double waitGrantAmount = accountbindService.queryWaitGrantAmount(userId, req.getMonth());
		
		List<ProfixTypeListRespsone> list = new ArrayList<ProfixTypeListRespsone>();
		ProfixTypeListRespsone re1 = new ProfixTypeListRespsone();
		re1.setAmount(WebUtil.getDefaultFormat(fee));
		re1.setProfixType("1");
		re1.setProfixTypeName("客户销售佣金");
		
		ProfixTypeListRespsone re2 = new ProfixTypeListRespsone();
		re2.setAmount(WebUtil.getDefaultFormat(allowance));
		re2.setProfixType("2");
		re2.setProfixTypeName("理财师推荐奖励");
		
		ProfixTypeListRespsone re3 = new ProfixTypeListRespsone();
		re3.setAmount(WebUtil.getDefaultFormat(activityReward));
		re3.setProfixType("3");
		re3.setProfixTypeName("活动奖励");
		
		ProfixTypeListRespsone re4 = new ProfixTypeListRespsone();
		re4.setAmount(WebUtil.getDefaultFormat(leaderReward));
		re4.setProfixType("4");
		re4.setProfixTypeName("leader奖励");
		
		ProfixTypeListRespsone re5 = new ProfixTypeListRespsone();
		re5.setAmount(WebUtil.getDefaultFormat(hongbao));
		re5.setProfixType("5");
		re5.setProfixTypeName("红包");
		
		list.add(re1);
		list.add(re2);
		list.add(re3);
		list.add(re4);
		list.add(re5);
		
		rlt.setProfixList(list);
		
		Double totalProfix = fee + allowance + activityReward + leaderReward + hongbao;
		rlt.setTotalProfix(WebUtil.getDefaultFormat(totalProfix));
		rlt.setGrantedAmount(WebUtil.getDefaultFormat(grantedAmount));
		rlt.setWaitGrantAmount(WebUtil.getDefaultFormat(waitGrantAmount));
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 月度收益统计v3.0
	 */
	@RequestMapping("/monthProfixStatistics/3.0")
	@ResponseBody
	public BaseResponse monthProfixStatisticsNew3(@Valid MonthProfixStatisticsRequest req, BindingResult result, AppRequestHead head) throws Exception {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		MonthProfixStatisticsResponseNew rlt = new MonthProfixStatisticsResponseNew();
		
		
		Double fee = feeService.queryFeeByUserId(userId, req.getMonth()) + feeService.queryOfflineFeeByUserId(userId, req.getMonth());
		Double allowance = feeService.queryAllowanceByUserId(userId, req.getMonth());
		Double activityReward = accountbindService.queryCfpActivityRewardNew(userId, req.getMonth());
		Double leaderReward = feeService.queryLeaderRewardByUserIdNew(userId, req.getMonth());
		Double hongbao = accountbindService.queryHongbaoProfit(userId, req.getMonth());
		Double directAllowance = feeService.queryDirectAllowanceByUserId(userId, req.getMonth());
		Double teamAllowance = feeService.queryTeamAllowanceByUserId(userId, req.getMonth());
		
		Double grantedAmount = accountbindService.queryGrantedAmount(userId, req.getMonth());//已发放收益汇总
		Double waitGrantAmount = accountbindService.queryWaitGrantAmount(userId, req.getMonth());//待发放收益汇总
		
		List<ProfixTypeListRespsone> list = new ArrayList<ProfixTypeListRespsone>();
		ProfixTypeListRespsone re1 = new ProfixTypeListRespsone();
		re1.setAmount(WebUtil.getDefaultFormat(fee));
		re1.setProfixType("1");
		re1.setProfixTypeName("客户销售佣金");
		
		ProfixTypeListRespsone re2 = new ProfixTypeListRespsone();
		re2.setAmount(WebUtil.getDefaultFormat(allowance));
		re2.setProfixType("2");
		re2.setProfixTypeName("理财师推荐奖励");
		
		ProfixTypeListRespsone re3 = new ProfixTypeListRespsone();
		re3.setAmount(WebUtil.getDefaultFormat(activityReward));
		re3.setProfixType("3");
		re3.setProfixTypeName("活动奖励");
		
		ProfixTypeListRespsone re4 = new ProfixTypeListRespsone();
		re4.setAmount(WebUtil.getDefaultFormat(leaderReward));
		re4.setProfixType("4");
		re4.setProfixTypeName("leader奖励");
		
		ProfixTypeListRespsone re5 = new ProfixTypeListRespsone();
		re5.setAmount(WebUtil.getDefaultFormat(hongbao));
		re5.setProfixType("5");
		re5.setProfixTypeName("红包");
		
		ProfixTypeListRespsone re7 = new ProfixTypeListRespsone();
		re7.setAmount(WebUtil.getDefaultFormat(directAllowance));
		re7.setProfixType("7");
		re7.setProfixTypeName("直接管理津贴");
		
		ProfixTypeListRespsone re8 = new ProfixTypeListRespsone();
		re8.setAmount(WebUtil.getDefaultFormat(teamAllowance));
		re8.setProfixType("8");
		re8.setProfixTypeName("团队管理津贴");
		
		list.add(re1);
		list.add(re2);
		list.add(re3);
		list.add(re4);
		list.add(re5);
		list.add(re7);
		list.add(re8);
		
		rlt.setProfixList(list);
		
		Double totalProfix = fee + allowance + activityReward + leaderReward + hongbao + directAllowance + teamAllowance;
		rlt.setTotalProfix(WebUtil.getDefaultFormat(totalProfix));
		rlt.setGrantedAmount(WebUtil.getDefaultFormat(grantedAmount));
		rlt.setWaitGrantAmount(WebUtil.getDefaultFormat(waitGrantAmount));
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * 账户余额月份收益总计列表2.1
	 */
	@RequestMapping("/monthProfixTotalList/2.1")
	@ResponseBody
	public BaseResponse monthProfixTotalListNew(MonthProfixDetailRequest req, AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		req.setUserId(userId);
		Page<MonthProfixTotalListResp> page  = new Page<MonthProfixTotalListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<MonthProfixTotalListResp> rlt = acBalanceRecordService.queryProfixTotalListNew(req,page);
		return AppResponseUtil.getSuccessResponse(rlt, MonthProfixTotalListResponse.class);	
		
	}
	
	/**
	 * 创建二维码并上传到图片服务器
	 * @param request
	 * @param content
	 * @return
	 */
	private String creatRcCode(String logoPath,String content){
		CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
			QRCodeUtil.createQR(outStream,logoPath,content);			
			//准备上传图片数据
            byte[] buffer = null;              
            outStream.close();  
            buffer = outStream.toByteArray(); 
            //开始上传
            HttpPost httppost = new HttpPost(sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL));
            
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(buffer);
            httppost.setEntity(byteArrayEntity);
            httppost.addHeader("Content-Type", "jpeg");
            
            response = httpclient.execute(httppost);
            LOGGER.debug(response.getStatusLine().toString());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String resp = EntityUtils.toString(resEntity);
                SingleImgInfo o = JsonUtils.fromJsonToObject(resp, SingleImgInfo.class);
                if(o==null || o.getInfo()==null){
                    return null;
                }
                return  o.getInfo().getMd5();
            }
            EntityUtils.consume(resEntity);
		} catch (Exception e) {
			LOGGER.error("生成图片失败!",e);
		}finally {
            try {
                if(response!=null){
                    response.close();
                    response = null;
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
	}
	
	
	/**
	 * 账户明细4.5.4版本
	 */
	@RequestMapping("/accountBalanceDetails")
	@ResponseBody
	public BaseResponse accountBalanceDetails(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Page<AccountBalanceListResp> page  = new Page<AccountBalanceListResp>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<AccountBalanceListResp> rlt = acBalanceRecordService.accountBalanceDetails(userId,page);
		return AppResponseUtil.getSuccessResponse(rlt, AccountBalanceListResponse.class);	
		
	}
}
