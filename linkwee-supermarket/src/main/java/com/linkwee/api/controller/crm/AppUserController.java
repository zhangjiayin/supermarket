package com.linkwee.api.controller.crm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.act.redpacket.model.SendContext;
import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.acc.TwoPwdRequest;
import com.linkwee.api.request.crm.CheckMobileRequest;
import com.linkwee.api.request.crm.ChooseBackgroundRequest;
import com.linkwee.api.request.crm.DeviceInfoRequest;
import com.linkwee.api.request.crm.EasemobIdRequest;
import com.linkwee.api.request.crm.GetWelfareRequest;
import com.linkwee.api.request.crm.PwdRequest;
import com.linkwee.api.request.crm.RegisterRequest;
import com.linkwee.api.request.crm.RegisterSevReq;
import com.linkwee.api.request.crm.ResetLoginPwdRequest;
import com.linkwee.api.request.crm.ShareUserRequest;
import com.linkwee.api.request.crm.WechatBindRequest;
import com.linkwee.api.request.crm.WeiXinGetWelfareRequest;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.api.response.activity.CfpInfoRespspne;
import com.linkwee.api.response.crm.BrandPostersResponse;
import com.linkwee.api.response.crm.BrandPromotionImageResponse;
import com.linkwee.api.response.crm.BrandPromotionResponse;
import com.linkwee.api.response.crm.CfplannerPersonCenterResponse;
import com.linkwee.api.response.crm.CfplannerResponse;
import com.linkwee.api.response.crm.CfplannerUserInfoResponse;
import com.linkwee.api.response.crm.CheckMobileResponse;
import com.linkwee.api.response.crm.ChooseBackgroundResponse;
import com.linkwee.api.response.crm.InvestorResponse;
import com.linkwee.api.response.crm.InvestorUserInfoResponse;
import com.linkwee.api.response.crm.InvitationCfpPageListResponse;
import com.linkwee.api.response.crm.InvitationInvestorPageListResponse;
import com.linkwee.api.response.crm.InvitationNumResponse;
import com.linkwee.api.response.crm.InvitationRecordResponse;
import com.linkwee.api.response.crm.MyCfpResponse;
import com.linkwee.api.response.crm.SmBrandPostersTypeResponse;
import com.linkwee.api.response.crm.UserInfoResponse;
import com.linkwee.api.response.crm.WeiXinMsgResponse;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.enums.MidAutumnTaskEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.RegSourceEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmCfpLoginLog;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmInvestorLoginLog;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.model.crm.CrmLineUserInfo;
import com.linkwee.web.model.crm.CrmShareUserList;
import com.linkwee.web.model.crm.SmBrandPostersType;
import com.linkwee.web.model.crm.SmBrandPromotion;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.share.ShareContent;
import com.linkwee.web.model.vo.SingleImgInfo;
import com.linkwee.web.request.SendVcodeRequest;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActMidautumnTaskService;
import com.linkwee.web.service.CimLeaderFeeService;
import com.linkwee.web.service.CrmCfpLoginLogService;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorLoginLogService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmLineUserInfoService;
import com.linkwee.web.service.CrmShareUserListService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.EasemobService;
import com.linkwee.web.service.SmBrandPromotionService;
import com.linkwee.web.service.SmCustomerDeviceService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.helper.CommonHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.MessageHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.QRCodeUtil;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.HttpClientUtil;
import com.linkwee.xoss.util.MD5;

/**
 * 用户控制器
 *
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/user")
public class AppUserController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserController.class);
	
	private String errorCode = "-1";

    @Resource
    private CrmCfplannerService crmCfplannerService;
    
    @Resource
    private JsonWebTokenHepler jsonWebTokenHepler;

    @Resource
    private CrmInvestorService crmInvestorService;
    
    @Resource
    private CrmUserInfoService crmUserInfoService;
    
    @Resource
    private MessageHelper messageHelper;
    
    @Resource
    private CaptchaController captchaController;
    
    @Resource
	private SmCustomerDeviceService customerDeviceService;
    
    @Resource
    private EasemobService easemobService;
    
    @Resource
	private CrmCfpLoginLogService crmCfpLoginLogService;
	
	@Resource
	private CrmInvestorLoginLogService crmInvestorLoginLogService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private RedPacketService redPacketService;
	
	@Resource
	private ActRedpacketService actRedpacketService;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private SmMessageQueueService smMessageQueueService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Resource
	private CrmShareUserListService crmShareUserListService;
	
	@Resource
	private CimLeaderFeeService cimLeaderFeeService;
	
	@Resource
	private CrmLineUserInfoService crmLineUserInfoService;
	
	@Resource
	private SmBrandPromotionService smBrandPromotionService;
	
	@Resource
	private CommonHelper commonHelper;
	
	@Resource
	private CrmCfpNewcomerWelfareTaskService crmCfpNewcomerWelfareTaskService;
	
	@Resource
	private ActMidautumnTaskService actMidautumnTaskService;

	/**
	 * 描述：注册
	 * @param response
	 * @param mobile 手机号码
	 * @throws Exception 
	 */
	@RequestMapping("register")
	@ResponseBody
	public BaseResponse register(@Valid RegisterRequest req,BindingResult result,AppRequestHead head
			,DeviceInfoRequest deviceInfo,WechatBindRequest wechatBind) throws Exception {
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			return channelRegister(req,result,head,deviceInfo,wechatBind);
		}else{
			return investorRegister(req,result,head,deviceInfo,wechatBind);
		}
		
	}
	
	/**
	 * 投资者注册
	 */
	private BaseResponse investorRegister(RegisterRequest req,BindingResult result,AppRequestHead head,DeviceInfoRequest deviceInfo,WechatBindRequest wechatBind) throws Exception{
		boolean isRegUser = crmInvestorService.isExistsInvestor(req.getMobile());
		if(isRegUser){
			return AppResponseUtil.getErrorBusi("mobile_exists","手机号已注册");
		}
		String refUserId = null;
		CrmInvestor investorUserInfo = null;
		if(StringUtils.isNotBlank(req.getRecommendCode())){//推荐注册
			investorUserInfo = crmInvestorService.queryInvestorByMobile(req.getRecommendCode());
			if(investorUserInfo==null){
				return AppResponseUtil.getErrorBusi("recommendCode_notRt","无效的邀请人");
			}
			refUserId = investorUserInfo.getUserId();
		}
		
		//检查短信验证码
		
		if(StringUtils.isBlank(req.getVcode())){
			return AppResponseUtil.getErrorBusi("vcode_error","验证码不能为空");
		}
		if(!messageHelper.checkVerifyCode(req.getMobile(), MsgModuleEnum.REGISTER, req.getVcode())){
			return AppResponseUtil.getErrorBusi("vcode_error","验证码错误，请检查短信验证码重新输入");
		}
		String userId = null;
		try {
			userId = crmInvestorService.registerInvestor(req.getMobile(), req.getPassword(), refUserId,req.getFromUrl(),req.getAccessUrl());
		} catch (Exception e) {
			LOGGER.error("投资者注册异常 " , e);
			return AppResponseUtil.getErrorBusi(new BaseResponse("reg_error","注册失败"));
		}
		
		//注册送5元现金
		String swicth = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_SEND_CASH_SWICTH);
		if("ON".equals(swicth)){
			try {
				AcAccountRecharge recharge = new AcAccountRecharge();
				recharge.setRedpacketId(StringUtils.getUUID());
				recharge.setRemark("注册送5元现金");
				recharge.setTransAmount(BigDecimal.valueOf(5));
				recharge.setTransType(3);//活动奖励
				recharge.setUserId(userId);
				recharge.setUserType(2);
				accountbindService.accountRecharge(recharge);
			} catch (Exception e1) {
				LOGGER.error("注册送5元现金失败" , e1);
			}
		}
		
		CrmUserInfo userInfo =  new CrmUserInfo();
		userInfo.setUserId(userId);
		userInfo.setMobile(req.getMobile());
		//红包
		try {
			redPacketService.customerRegisterRedPacekt(userInfo);
		}catch(Exception e){
			LOGGER.warn("registerRedPacekt exception userInfo={}" ,userInfo, e.getMessage());
		}
		try {
			//如果邀请人首次邀请，给邀请人发红包
			if(investorUserInfo != null ){
				int cot = crmInvestorService.queryInvitationCount(investorUserInfo.getUserId());
				if(cot == 1) {
					//TODO
					CrmUserInfo refUserInfo = crmUserInfoService.queryUserInfoByUserId(investorUserInfo.getUserId());
					try {
						//投资者红包   90元红包
//						actRedpacketService.customerTaskRedPacekt(InvestorNewcomerTaskEnum.INVESTOR_INVITE_CUSTOMER, refUserInfo);
						//理财师红包
						CrmCfplanner refCfplanner = crmCfplannerService.queryCfplannerByUserId(investorUserInfo.getUserId());
						if(refCfplanner != null) {
							actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.CFPLANNER_NEWCOMERWELFARE_INVITE_CUSTOMER, refUserInfo);
							//给邀请理财师推送通知栏+个人中心消息
							String cont = sysConfigService.getValuesByKey(SysConfigConstant.SEND_SIX_ONCE_INVITATION_INVESTOR);
					    	pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.SEND_SIX_ONCE_INVITATION_INVESTOR, refUserId, "收到红包", cont, null, true);
						}
					} catch (Exception e) {
						LOGGER.warn("newcomer welfare exception userInfo={}" ,userInfo, e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("newcomer welfare exception userInfo={}" ,userInfo, e.getMessage());
		}
		
		pushMsgInvestorRegister(userId, "", req.getMobile(), refUserId);
		//注册成功后登录
		return doAfterRegister(req, result, head, deviceInfo, wechatBind);
	}
	
	/**
	 * 理财师注册
	 */
	private BaseResponse channelRegister(RegisterRequest req,BindingResult result,AppRequestHead head,DeviceInfoRequest deviceInfo,WechatBindRequest wechatBind){
		boolean isRegUser = crmCfplannerService.isExistsCfplanner(req.getMobile());
		if(isRegUser){
			return AppResponseUtil.getErrorBusi("mobile_exists","手机号已注册");
		}
		boolean isBaseUserInfo = crmUserInfoService.isExistsUserInfo(req.getMobile());
		if(!isBaseUserInfo){
			if(StringUtils.isBlank(req.getVcode())){
				return AppResponseUtil.getErrorBusi("vcode_error","验证码不能为空");
			}
			if(!messageHelper.checkVerifyCode(req.getMobile(), MsgModuleEnum.REGISTER, req.getVcode())){
				return AppResponseUtil.getErrorBusi("vcode_error","验证码错误，请检查短信验证码重新输入");
			}
		}else{
			if(!crmUserInfoService.docheckLoginPwd(req.getMobile(), req.getPassword())){//密码错误
				return AppResponseUtil.getErrorBusi("password_error","密码错误");
			}
		}
		
    	if(StringUtils.isBlank(req.getRecommendCode())){//非推荐注册
    		boolean needRc = crmCfplannerService.checkCfgNeedRc(req.getMobile());
        	if(needRc){
        		return AppResponseUtil.getErrorBusi("mobile_limit","您是理财师专属客户，需由您的理财师推荐您升级");
        	}
    		return freeRegister(req,result,head,deviceInfo,wechatBind);
    	}else{//推荐注册
    		return rcRegister(req,result,head,deviceInfo,wechatBind);
    	}
	}
	
	/**
	 * 自由客户注册
	 * @param req
	 * @param result
	 * @param head
	 * @param deviceInfo
	 * @param wechatBind
	 * @return
	 */
	private BaseResponse freeRegister(RegisterRequest req,BindingResult result,AppRequestHead head,DeviceInfoRequest deviceInfo,WechatBindRequest wechatBind){
		RegisterSevReq registerSevReq = new RegisterSevReq();
		registerSevReq.setMobile(req.getMobile());
		registerSevReq.setParentId(null);
		registerSevReq.setPassword(req.getPassword());
		registerSevReq.setFromUrl(req.getFromUrl());
		registerSevReq.setAccessUrl(req.getAccessUrl());
		registerSevReq.setSalesOrgId(req.getSaleOrgCode());
		try {
			CrmInvestor investor = crmInvestorService.queryInvestorByMobile(req.getMobile());//查投资用户是否存在
			ServiceResponse<Boolean> servResp = crmCfplannerService.registerLcs(registerSevReq);
			if(servResp.isSuccess()&&servResp.getData()){
				CrmUserInfo userInfo = crmUserInfoService.selectCrmUserInfoByMobile(req.getMobile());
				//发红包
				if(investor == null) {
					try {
						redPacketService.customerRegisterRedPacekt(userInfo);
					}catch(Exception e){
						LOGGER.warn("registerRedPacekt exception userInfo={}" ,userInfo, e.getMessage());
					}
				}
				//消息推送
				pushMsgChannelRegister(req.getMobile(), null);
				return doAfterRegister(req, result, head, deviceInfo, wechatBind);
			}else{
				return AppResponseUtil.getErrorBusi(new BaseResponse("reg_error","注册失败"));
			}
		} catch (Exception e) {
			LOGGER.error("理财师注册异常 ", e);
			return AppResponseUtil.getErrorBusi(new BaseResponse("reg_error","注册失败"));
		}
	}
	
	/**
	 * 推荐注册
	 * @param req
	 * @param result
	 * @param head
	 * @param deviceInfo
	 * @param wechatBind
	 * @return
	 */
	private BaseResponse rcRegister(RegisterRequest req,BindingResult result,AppRequestHead head,DeviceInfoRequest deviceInfo,WechatBindRequest wechatBind){
		CrmCfplanner saleUserInfo = crmCfplannerService.queryCfplannerByMobile(req.getRecommendCode());
		if(saleUserInfo == null) {
			return AppResponseUtil.getErrorBusi("recommendCode_notRt","无效的邀请人");
		}
		ServiceResponse<Boolean> checkRlt = crmCfplannerService.checkCfgRecommend(saleUserInfo.getUserId(),req.getMobile());
		if(checkRlt.isError(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_PROTECTED)){
			return AppResponseUtil.getErrorBusi("rcuser_protected","您是理财师专属客户，需由您的理财师推荐您升级");
		}
		if(checkRlt.isError(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_FROZEN)){
			return AppResponseUtil.getErrorBusi("rcuser_frozen","您处于回退限制期内，限制注册");
		}
		RegisterSevReq registerSevReq = new RegisterSevReq();
		registerSevReq.setMobile(req.getMobile());
		registerSevReq.setParentId(saleUserInfo.getUserId());
		registerSevReq.setSalesOrgId(saleUserInfo.getSalesOrgId());
		Integer depth = null;
		if(saleUserInfo.getSalesOrgDepth() != null){
			depth = saleUserInfo.getSalesOrgDepth()+1;
		}
		registerSevReq.setSalesOrgDepth(depth);
		registerSevReq.setPassword(req.getPassword());
		registerSevReq.setFromUrl(req.getFromUrl());
		registerSevReq.setAccessUrl(req.getAccessUrl());
		try {
			CrmInvestor investor = crmInvestorService.queryInvestorByMobile(req.getMobile());//查投资用户是否存在
			ServiceResponse<Boolean> servResp = crmCfplannerService.registerLcs(registerSevReq);
			if(servResp.isSuccess()&&servResp.getData()){
				//发红包
				if(investor == null) {
					CrmUserInfo userInfo = crmUserInfoService.selectCrmUserInfoByMobile(req.getMobile());
					try {
						redPacketService.customerRegisterRedPacekt(userInfo);
					}catch(Exception e){
						LOGGER.warn("registerRedPacekt exception userInfo={}" ,userInfo, e.getMessage());
					}
					
					// 情人节活动（2017214）推荐注册的理财师给新理财师发红包
					String redpacektIdAndSendId = sysConfigService.getValuesByKey(SysConfigConstant.LCS_RECOMMEND_REGISTER_SENDREDPACEKT_REDPACEKTIDANDSENDID); 
					if(StringUtils.isNotBlank(redpacektIdAndSendId)){
					String values[] =  org.apache.commons.lang.StringUtils.split(redpacektIdAndSendId,"_");
					try{
					actRedpacketService.lcsSystemRedpacekt(new SendContext(userInfo,SysConfigConstant.LCS_RECOMMEND_REGISTER_SENDREDPACEKT_SWICTH, new String[]{values[0]},new String[]{values[1]}));
					}catch(Exception e){
						LOGGER.error("recommend lcs registe lcsSystemRedpacekt exception userInfo={}" ,userInfo, e.getMessage());
					}
					
					}
				}
				
				//如果邀请人首次邀请，给邀请人发红包
				if(saleUserInfo != null ) {
					int teamMemberCount = crmCfplannerService.queryTeamMemberCount(saleUserInfo.getUserId());
					if(teamMemberCount == 1) {
						//TODO
						CrmUserInfo userInfo =  new CrmUserInfo();
						userInfo.setUserId(saleUserInfo.getUserId());
						userInfo.setMobile(saleUserInfo.getMobile());
						try {
							boolean isNewComer = crmCfpNewcomerWelfareTaskService.isExistUser(saleUserInfo.getUserId());
							if(!isNewComer){
								actRedpacketService.lcsTaskRedPacekt(CfpNewcomerTaskEnum.CFPLANNER_INEWCOMERWELFARE_NVITE_CFPLANNER, userInfo);
							}else{
								//新手福利六连送--邀请理财师
								crmCfpNewcomerWelfareTaskService.sendTaskReward(saleUserInfo.getUserId(), CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVITECFP);
							}
						} catch (Exception e) {
							LOGGER.warn("newcomer welfare exception userInfo={}" ,userInfo, e.getMessage());
						}
					}
				}
				
				//消息推送
				pushMsgChannelRegister(req.getMobile(), saleUserInfo.getUserId());
				
				/*final String mobile = req.getMobile();
				final String recommendCode = req.getRecommendCode();
				//邀请注册、变更leader奖励树
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						cimLeaderFeeService.changeLeaderTree(mobile,recommendCode);
					}
				});*/
				
				actMidautumnTaskService.finishTask(saleUserInfo.getUserId(),MidAutumnTaskEnum.INVITE_CFPLANNER);
				
				return doAfterRegister(req, result, head, deviceInfo, wechatBind);
			}else{
				return AppResponseUtil.getErrorBusi(new BaseResponse("reg_error","注册失败"));
			}
		} catch (Exception e) {
			LOGGER.error("理财师注册异常 ", e);
			return AppResponseUtil.getErrorBusi(new BaseResponse("reg_error","注册失败"));
		}
	}
	
	/**
	 * 注册成功后登录
	 */
	private BaseResponse doAfterRegister(RegisterRequest req, BindingResult result, AppRequestHead head, DeviceInfoRequest deviceInfoRequest, WechatBindRequest wechatBind) {
		CrmUserInfo crmUserInfo = crmUserInfoService.selectCrmUserInfoByMobile(req.getMobile());
		String token = jsonWebTokenHepler.creatToken(head.getAppKind(),crmUserInfo.getUserId());
		Map<String ,String > map = new HashMap<String ,String >();
		map.put("token", token);
		//保存设备信息
		try {
			customerDeviceService.recordCustomerDeviceInfo(crmUserInfo.getUserId(),AppUtils.getPlatform(head.getOrgNumber()).getValue(),AppUtils.getAppType(head.getOrgNumber()).getKey(), deviceInfoRequest);
		} catch (Exception e) {
			LOGGER.error("用户注册保存设备信息失败: " + e);
		}
		//登录日志
		try {
			if(AppUtils.isChannelApp(head.getOrgNumber())) {
				CrmCfpLoginLog log = new CrmCfpLoginLog();
				log.setLogId(StringUtils.getUUID());
				log.setAccessUrl(req.getAccessUrl());
				log.setAppVersion(head.getAppVersion());
				log.setCreateTime(new Date());
				log.setDeviceDetail(deviceInfoRequest.getDeviceModel());
				log.setDeviceId(deviceInfoRequest.getDeviceId());
				log.setDeviceResolution(deviceInfoRequest.getResolution());
				log.setDeviceType(AppUtils.getPlatform(head.getOrgNumber()).getValue());
				log.setFromUrl(req.getFromUrl());
				log.setLastUpdateTime(new Date());
				log.setSystemVersion(deviceInfoRequest.getSystemVersion());
				log.setUserId(crmUserInfo.getUserId());
				crmCfpLoginLogService.insert(log);
			} else {
				CrmInvestorLoginLog log = new CrmInvestorLoginLog();
				log.setLogId(StringUtils.getUUID());
				log.setAccessUrl(req.getAccessUrl());
				log.setAppVersion(head.getAppVersion());
				log.setCreateTime(new Date());
				log.setDeviceDetail(deviceInfoRequest.getDeviceModel());
				log.setDeviceId(deviceInfoRequest.getDeviceId());
				log.setDeviceResolution(deviceInfoRequest.getResolution());
				log.setDeviceType(AppUtils.getPlatform(head.getOrgNumber()).getValue());
				log.setFromUrl(req.getFromUrl());
				log.setLastUpdateTime(new Date());
				log.setSystemVersion(deviceInfoRequest.getSystemVersion());
				log.setUserId(crmUserInfo.getUserId());
				crmInvestorLoginLogService.insert(log);
			}
		} catch (Exception e) {
			LOGGER.error("用户注册写入登录日志失败: " + e);
		}
		//生成环信帐号
		try {
			easemobService.generateEasemobThread(crmUserInfo.getUserId());
		} catch (Exception e) {
			LOGGER.error("用户注册生成环信失败: " + e);
		}
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	
	/**
	 * 用户手机号码检测
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("checkMobile")
	@ResponseBody
	public BaseResponse checkMobile(@Valid CheckMobileRequest req,BindingResult result,AppRequestHead head) {
    	if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
    	boolean isRegUser = false;
    	Map<String,Object> retMap = new HashMap<String,Object>();
    	if(AppUtils.isChannelApp(head.getOrgNumber())){
    		isRegUser = crmCfplannerService.isExistsCfplanner(req.getMobile());
    		if(isRegUser){
    			return AppResponseUtil.getErrorBusi("user_exist","您已经注册，请直接登录");
    		}
    		boolean rcRegFlag = StringUtils.isBlank(req.getRecommendCode());
    		CrmCfplanner saleUser = crmCfplannerService.queryCfplannerByInvestMobile(req.getMobile());
    		String lcsName = "理财师";
    		if(saleUser != null) {
    			if(StringUtils.isNotBlank(saleUser.getUserName())) {
    				lcsName = saleUser.getUserName();
    			} else {
    				lcsName = saleUser.getMobile();
    			}
    		}
    		if(rcRegFlag){//非推荐注册
            	if(crmCfplannerService.checkCfgNeedRc(req.getMobile())){
            		return AppResponseUtil.getErrorBusi("mobile_limit","您是 " + lcsName + " 的专属客户，需由他推荐才能注册");
            	}
    		}else {//推荐注册
    			CrmCfplanner saleUserInfo = crmCfplannerService.queryCfplannerByMobile(req.getRecommendCode());
    			if(saleUserInfo == null || "".equals(saleUserInfo)) {
    				return AppResponseUtil.getErrorBusi("error","推荐人不存在");
    			}
        		ServiceResponse<Boolean> checkRlt = crmCfplannerService.checkCfgRecommend(saleUserInfo.getUserId(),req.getMobile());
        		if(checkRlt.isError(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_PROTECTED)){
        			return AppResponseUtil.getErrorBusi("rcuser_protected","您是 " + lcsName + " 的专属客户，需由他推荐才能注册");
        		}
        		if(checkRlt.isError(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_FROZEN)){
        			return AppResponseUtil.getErrorBusi("rcuser_frozen","您处于回退限制期内，限制注册");
        		}
    		}
    	}else{
    		isRegUser = crmInvestorService.isExistsInvestor(req.getMobile());
    	}
    	boolean isBaseUserInfo = crmUserInfoService.isExistsUserInfo(req.getMobile());
    	CheckMobileResponse resp = new CheckMobileResponse();
    	if(!isRegUser){//未注册
    		if(isBaseUserInfo){//用户基础数据表存在
     			resp.setRegFlag("1");//1未注册(基础用户表存在)
				resp.setRegSource(RegSourceEnum.INVESTOR.getMsg());//投资端用户
    		}else{
    			resp.setRegFlag("0");//0未注册(基础用户表不存在)
    			resp.setRegSource("");
    		}
    	}else{
    		resp.setRegFlag("2");//2已注册
    		resp.setRegSource("");
    	}
    	retMap.put("regFlag", resp.getRegFlag());
    	retMap.put("regSource", resp.getRegSource());
    	return AppResponseUtil.getSuccessResponse(retMap);
	}
	
	
	/**
	 * @描述：发送验证码
	 * @param response
	 * @param mobile 手机号码
	 */
	@RequestMapping("sendVcode")
	@ResponseBody
	public BaseResponse sendVCode(@Valid SendVcodeRequest req,BindingResult result,AppRequestHead head,HttpServletRequest request) {
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String mobile = req.getMobile();
		if(MsgModuleEnum.XIAOSHANGCHENG.getKey()==req.getType()||MsgModuleEnum.REGISTER.getKey()==req.getType()||MsgModuleEnum.UPDATELOGINPWD.getKey()==req.getType()||MsgModuleEnum.BINDCARD.getKey()==req.getType()){
			if(StringUtils.isBlank(mobile)){//注册、重置密码必须添加验证码,需要手机号
				List<BaseResponse> errors = new ArrayList<BaseResponse>();
				errors.add(new BaseResponse("mobile_notNull","mobile不能为空"));
				return AppResponseUtil.getErrorParams(errors);
			}
		}else{
			if(StringUtils.isBlank(head.getToken())){
				List<BaseResponse> errors = new ArrayList<BaseResponse>();
				errors.add(new BaseResponse("token_notNull","token不能为空"));
				return AppResponseUtil.getErrorParams(errors);
			}
			CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
			mobile = userInfo.getMobile();
		}
		//发送验证码
		return messageHelper.sendVerifyCodeBaseResponse(mobile,AppUtils.getAppType(head.getOrgNumber()),MsgModuleEnum.valueOf(EnumUtils.getValueByKey(req.getType(), MsgModuleEnum.values())));
	}
	
	/**
	 * 修改登录密码
	 * @param req
	 * @"rlt"
	 */
	@RequestMapping("modifyLoginPwd")
	@ResponseBody
	public BaseResponse modifyLoginPwd(@Valid TwoPwdRequest req,BindingResult result,AppRequestHead head) {
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		if(crmUserInfo == null ) {
			return AppResponseUtil.getErrorBusi("userNotExists","用户不存在");
		}
		if(!crmUserInfo.getPassword().equals(MD5.crypt(req.getOldPwd()))) {
			return AppResponseUtil.getErrorBusi("passwordNotRight","原始密码错误");
		}
		
		try {
			crmUserInfoService.updateLoginPassword(crmUserInfo.getUserId(), req.getNewPwd());
		} catch (Exception e) {
			LOGGER.error("修改登录密码失败 ", e);
			return AppResponseUtil.getErrorBusi("modifyLoginPwdError","修改密码失败");
		}
		//短信提示
		/*final String mobile = crmUserInfo.getMobile();
		final AppTypeEnum appTyp =  AppUtils.getAppType(head.getOrgNumber());
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				smMessageQueueService.sendSingleMessage(mobile, appTyp, MsgModuleEnum.UPDATEPWDBYOLDPWD,DateUtils.format(new Date(), DateUtils.FORMAT_LONG_CN));
			}
		});*/
		
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 重置登录密码
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("resetLoginPwd")
	@ResponseBody
	public BaseResponse resetLoginPwd(@Valid ResetLoginPwdRequest req,BindingResult result,AppRequestHead head) {
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		if(!messageHelper.checkVerifyCode(req.getMobile(), MsgModuleEnum.UPDATELOGINPWD, req.getVcode())){
			return AppResponseUtil.getErrorBusi("vcode_error","验证码错误，请检查短信验证码重新输入");
		}
		CrmUserInfo crmUserInfo = crmUserInfoService.selectCrmUserInfoByMobile(req.getMobile());
		if(crmUserInfo == null ) {
			return AppResponseUtil.getErrorBusi("userNotExists","用户不存在");
		}
		try {
			crmUserInfoService.updateLoginPassword(crmUserInfo.getUserId(), req.getNewPwd());
		} catch (Exception e) {
			LOGGER.error("修改登录密码失败 ", e);
			return AppResponseUtil.getErrorBusi("ResetLoginPwdError","重置密码失败");
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 验证登录密码是否正确
	 * @param reqs
	 * @return
	 */
	@RequestMapping("verifyLoginPwd")
	@ResponseBody
	public BaseResponse verifyLoginPwd(@Valid PwdRequest req,BindingResult result,AppRequestHead head) {
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		boolean rlt = false;
		if(crmUserInfo != null && crmUserInfo.getPassword().equals(MD5.crypt(req.getPwd()))) {
			rlt = true;
		}
		Map<String,Boolean> ret = new HashMap<String,Boolean>();
		ret.put("rlt", rlt);
		return AppResponseUtil.getSuccessResponse(ret);
	}
	
	/**
	 * @描述：查当前用户信息
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public BaseResponse getUserInfo(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		LOGGER.debug("查当前用户信息: userId = " + userId);
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			CrmCfplanner crmCfplanner =  crmCfplannerService.queryCfplannerByUserId(userId);
			if(crmCfplanner != null && crmCfplanner.getEasemobRegStatus() != 1) {
				easemobService.generateEasemob(userId);
			}
			return AppResponseUtil.getSuccessResponse(crmCfplanner,CfplannerResponse.class);
		}else{
			CrmInvestor crmInvestor = crmInvestorService.queryInvestorByUserId(userId);
			if(crmCfplannerService.queryCfplannerByUserId(userId) != null){
				crmInvestor.setCfp(true);
			}
			if(crmInvestor != null && crmInvestor.getEasemobRegStatus() != 1) {
				easemobService.generateEasemob(userId);
			}
			return AppResponseUtil.getSuccessResponse(crmInvestor,InvestorResponse.class);
		}
	}
	
	/**
	 * 根据环信帐号查用户信息
	 */
	@RequestMapping("getUserInfoByEasemob")
	@ResponseBody
	public BaseResponse getUserInfoByEasemob(@Valid EasemobIdRequest req, BindingResult result, AppRequestHead head) {
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		List<String> easemobAcctList = Arrays.asList(req.getEasemobAcct().split(","));
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			List<CrmInvestor> investorList = crmInvestorService.queryInvestorByEasemob(easemobAcctList);
			return AppResponseUtil.getSuccessResponse(investorList,UserInfoResponse.class);
		} else {
			List<CrmCfplanner> cfplannerList = crmCfplannerService.queryCfplannerByEasemob(easemobAcctList);
			return AppResponseUtil.getSuccessResponse(cfplannerList,UserInfoResponse.class);
		}
	}
	
	/**
	 * 我的理财师
	 * @param head
	 * @return
	 */
	@RequestMapping("mycfp")
	@ResponseBody
	public BaseResponse mycfp(AppRequestHead head) {
		CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByInvestor(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		if (crmCfplanner == null) {
			crmCfplanner = new CrmCfplanner();
		}
		MyCfpResponse myCfp = new MyCfpResponse(crmCfplanner);
		if(JsonWebTokenHepler.getUserIdByToken(head.getToken()).equals(crmCfplanner.getUserId())) {
			myCfp.setCfpIsSelf("1");
		} else {
			myCfp.setCfpIsSelf("0");
		}
		return AppResponseUtil.getSuccessResponse(myCfp);
	}
	
	/**
	 * 线下活动-理财师邀请记录
	 */
	@RequestMapping("/queryInvitationRecord")
	@ResponseBody
	public BaseResponse queryInvitationRecord(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
		if(cfp == null) {
			return AppResponseUtil.getErrorBusi("user is not exist", "该理财师不存在");
		}
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("cfpMobile",cfp.getMobile());
		Page<CrmLineUserInfo> page  = new Page<CrmLineUserInfo>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CrmLineUserInfo> rlt =  null;
		try {
			rlt = crmLineUserInfoService.queryInvitationRecord(page,conditions);
		} catch (Exception e) {
			return AppResponseUtil.getErrorBusi("queryInvitationRecord is not exist", "网络繁忙");
		}
		return AppResponseUtil.getSuccessResponse(rlt,InvitationRecordResponse.class);
	}
	
	/**
	 * 线下活动-查询理财师基本信息服务
	 */
	@RequestMapping("/queryCfpInfo")
    @ResponseBody
    public BaseResponse queryCfpInfo(@Valid CheckMobileRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		CrmCfplanner cfp = null;
		if(req.getMobile() != null && !"".equals(req.getMobile())) {
			cfp = crmCfplannerService.queryCfplannerByMobile(req.getMobile());
			if(cfp == null) {
				return AppResponseUtil.getErrorBusi("user is not exist", "该理财师不存在");
			}
		} 
		CfpInfoRespspne  rlt = new CfpInfoRespspne();
		rlt.setMobile(cfp.getMobile());
		rlt.setUserName(cfp.getUserName());
		rlt.setPlatform("猎财大师");
		rlt.setPosition("理财师");
		rlt.setHeadImage(cfp.getHeadImage());
		return AppResponseUtil.getSuccessResponse(rlt);
		
    }
	
	/**
	 * 线下活动-判断当前微信用户是否领取过福利
	 */
	@RequestMapping("/haveGetWelfare")
    @ResponseBody
    public BaseResponse haveGetWelfare(@Valid WeiXinGetWelfareRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,String> map = new HashMap<String,String>();
		CrmLineUserInfo info = new CrmLineUserInfo();
		info.setOpenId(req.getOpenId());
		//OpenId是否有领取过福利
		List<CrmLineUserInfo> lineUser = crmLineUserInfoService.selectListByCondition(info);
		if(lineUser.size()>0){
			map.put("mobile", lineUser.get(0).getCfpMobile());
			map.put("userName", lineUser.get(0).getCfpName());
		}
		map.put("isWelfare", lineUser.size()==0?"0":"1");
		return AppResponseUtil.getSuccessResponse(map);
    }
	
	
	/**
	 * 线下活动-领取福利
	 */
	@RequestMapping("/getWelfare")
    @ResponseBody
    public BaseResponse getWelfare(@Valid GetWelfareRequest req,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		if(req.getMobile()!=null&&req.getMobile().equals(req.getCfpMobile())){
			return AppResponseUtil.getErrorBusi("cfpmobile mobile is repeat", "邀请人跟被邀请人不能是同一个手机号码");
		}
		CrmLineUserInfo info = new CrmLineUserInfo();
		info.setMobile(req.getMobile());
		//判断领取福利的手机号mobile是否有领取过
		CrmLineUserInfo lineUser = crmLineUserInfoService.selectOne(info);
		if(lineUser != null) {
			return AppResponseUtil.getErrorBusi("mobile is repeat", "该手机号码已经领取过福利");
		}
		
		CrmCfplanner cfp = null;
		if(req.getCfpMobile() != null && !"".equals(req.getCfpMobile())) {
			cfp = crmCfplannerService.queryCfplannerByMobile(req.getCfpMobile());
			if(cfp == null) {
				return AppResponseUtil.getErrorBusi("cfp is not exist", "该邀请理财师不存在");
			}
			req.setCfpUserId(cfp.getUserId());
			req.setCfpName(cfp.getUserName());
		} 
		try {
			crmLineUserInfoService.insetWelfare(req);
		} catch (Exception e) {
			LOGGER.error("领取福利异常",e);
			return AppResponseUtil.getErrorBusi("CrmLineUserInfo is not exist", "网络繁忙");
		}
		return AppResponseUtil.getSuccessResponse();
    }
	
	/**
	 * 投资者注册完成后推送消息
	 */
	private void pushMsgInvestorRegister(String userId,String customerName,String customerMobile,String refUser){
		if(customerName == null){
			customerName = "";
		}
		try {
			//推送消息
			//投资端 用户注册成功
			String content = sysConfigService.getValuesByKey(SysConfigConstant.PUSHMESSAGE_CREGISTER, AppTypeEnum.INVESTOR.getKey());
			SysMsg msg = new SysMsg();
			msg.setAppType(AppTypeEnum.INVESTOR.getKey());
			msg.setContent(content);
			msg.setCrtTime(new Date());
			msg.setModifyTime(new Date());
			msg.setStatus(0);// 发布
			msg.setUserNumber(userId);
			msg.setReadStatus(0);// 未读
			msg.setTypeName(PersonalMsgTypeEnum.CREGISTER.getValue());
			sysMsgService.addMsg(msg);
			
			if(StringUtils.isNotBlank(refUser)) {
				CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByInvestor(refUser);
				if(null != crmCfplanner){
					//理财师   客户注册完成
					String lcontent = String.format(sysConfigService.getValuesByKey(SysConfigConstant.PUSHMESSAGE_LCUSTOMERREGIST,AppTypeEnum.CHANNEL.getKey()),							
							customerName+customerMobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("customerId", userId);
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.LCUSTOMERREGIST,
							crmCfplanner.getUserId(), "新增客户", 
							lcontent , map, true);
				}
				/**
				 * 如果是客户邀请客户 给客户推送消息
				 */
				CrmCfplanner refCfp =  crmCfplannerService.queryCfplannerByUserId(refUser);
				if(refCfp == null){//不是理财师 则为客户推荐客户
					String invContent =  String.format(sysConfigService.getValuesByKey(SysConfigConstant.PUSHMESSAGE_CUM_IVIT_CUM,AppTypeEnum.INVESTOR.getKey()),							
							customerName+customerMobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
					LOGGER.info("邀请客户注册成功消息提醒：邀请客户userId:{},被邀请客户手机号：{} ",refUser,customerMobile);
					List<String> userIds = Lists.newArrayList();
					userIds.add(refUser);
					pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.INVITATIONRECORD_INC, userIds, "邀请客户注册成功", invContent, null, true, PersonalMsgTypeEnum.INVITATIONINV);
				}
				
			}
		} catch (Exception e) {
			LOGGER.error("投资用户注册推送消息失败 : " + e);
		}
	}
	
	/**
	 * 理财师注册成功推送消息
	 */
	private void pushMsgChannelRegister(String mobile, String refUserId) {
		CrmCfplanner saleUserInfo = crmCfplannerService.queryCfplannerByMobile(mobile);
		try {
			if (null != saleUserInfo) {
				// 理财师 用户（理财师自己）注册成功
				String content = String.format(sysConfigService.getValuesByKey(SysConfigConstant.PUSHMESSAGE_LREGISTER), AppTypeEnum.CHANNEL.getKey());
				pushMessageHelper.pushMessageAsyn(AppTypeEnum.CHANNEL, SmsTypeEnum.LREGISTER,
						saleUserInfo.getUserId(), "", content, null,true);
				if (StringUtils.isNotBlank(refUserId)) {// 推荐注册 理财师 团队一级成员注册完成
					String svalues = StringUtils.isNotBlank(saleUserInfo.getUserName()) ? saleUserInfo.getUserName() : "" +saleUserInfo.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
					
					String lcontent = String.format(sysConfigService.getValuesByKey(
							SysConfigConstant.PUSHMESSAGE_LGRADEONEREGISTER), svalues);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userNumber", saleUserInfo.getNumber());
					pushMessageHelper.pushMessageAsyn(AppTypeEnum.CHANNEL, SmsTypeEnum.LGRADEONEREGISTER,
							refUserId, "新增团队成员", lcontent,map,true);
				}
			}
		} catch (Exception e) {
			LOGGER.error("理财师用户注册推送消息失败 : " + e);
		}
	}
	
	
	/**
	 * 保存用户微信openId
	 */
	@RequestMapping("saveWeiXinOpenId")
	@ResponseBody
	public BaseResponse saveWeiXinOpenId(@Valid WeiXinMsgRequest req, BindingResult result, AppRequestHead head) {
		LOGGER.info("保存用户微信openId  WeiXinMsgRequest {} ",JSON.toJSONString(req));
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		
		if(StringUtils.isNotBlank(head.getAppKind())){
			req.setUseType("investor".equals(head.getAppKind())?2:1);//1理财师，2投资者
		}
		
		String appID = sysConfigService.getValuesByKey(req.getUseType()==2?SysConfigConstant.TOOBEI_APPID:SysConfigConstant.LIECAI_APPID).trim();
		String appSecret = sysConfigService.getValuesByKey(req.getUseType()==2?SysConfigConstant.TOOBEI_APPSECRET:SysConfigConstant.LIECAI_APPSECRET).trim();
		String url = sysConfigService.getValuesByKey(req.getUseType()==2?SysConfigConstant.TOOBEI_QUERY_OPENID_URL:SysConfigConstant.LIECAI_QUERY_OPENID_URL).trim();
		//根据微信code获得用户的openId
		String ret = HttpClientUtil.httpsGet(String.format(url, appID, appSecret, req.getCode()));
		LOGGER.info("根据微信code获得用户的openId【{}】",ret);
		WeiXinMsgResponse wx = (WeiXinMsgResponse) JSON.parseObject(ret, WeiXinMsgResponse.class);
		
		if(wx!=null&&wx.getOpenid()!=null){
			
			if("1".equals(req.getIsPush())||"2".equals(req.getIsPush())){//推送微信注册信息
				WeiXinMsgRequest weixinreq = new WeiXinMsgRequest();
				weixinreq.setUseId(userId);
				weixinreq.setUseType(req.getUseType());
				weixinreq.setTemkey(SysConfigConstant.REGISTER_SUCCESS);//注册成功通知
				weiXinMsgService.sendWeiXinMsgCommon(weixinreq);
			}
			CrmInvestor crm = crmInvestorService.queryInvestorByUserId(userId);//被推荐人
			if("2".equals(req.getIsPush())&&crm!=null&&crm.getWeiXinOpenId()==null){//推送微信注册信息
				WeiXinMsgRequest weixinreq = new WeiXinMsgRequest();
				weixinreq.setTemkey(SysConfigConstant.INVITATION_REGISTER_SUCCESS);//邀请注册成功通知
				if(crm!=null&&crm.getRefUser()!=null){
					CrmInvestor recrm = crmInvestorService.queryInvestorByUserId(crm.getRefUser());
					if(recrm!=null&&recrm.getUserId()!=null){
						weixinreq.setUseId(recrm.getUserId());//发给邀请人
						weixinreq.setUseType(req.getUseType());
						weixinreq.setRecommendUserName(crm.getUserName());
						weixinreq.setRecommendMobile(crm.getMobile());
						weiXinMsgService.sendWeiXinMsgCommon(weixinreq);
					}
				}
				//理财师邀请别人注册理财师   团队一级成员注册完成
				CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
				if(cfp!=null&&cfp.getParentId()!=null){
					CrmCfplanner parentCfp = crmCfplannerService.queryCfplannerByUserId(userId);
					if(parentCfp!=null){
						WeiXinMsgRequest wxq = new WeiXinMsgRequest();
						wxq.setUseId(cfp.getParentId());//发给上级推荐的理财师
						wxq.setUseType(1);
						wxq.setTemkey(SysConfigConstant.RECOMMEND_SUCCESS);//团队一级成员注册完成
						wxq.setCfpMobile(cfp.getMobile());
						wxq.setRegisterTime(DateUtils.format(new Date(), DateUtils.FORMAT_LONG));
						weiXinMsgService.sendWeiXinMsgCommon(wxq);
					}
				}
			}
			
			if(AppUtils.isChannelApp(head.getOrgNumber())){//理财师
				CrmCfplanner cf = new  CrmCfplanner();
				cf.setUserId(userId);
				cf.setWeiXinOpenId(wx.getOpenid());
				crmCfplannerService.updateByUserId(cf);
				LOGGER.info("更新理财师WeiXinOpenId成功！userId={}",userId);
			} else {
				CrmInvestor inv = new CrmInvestor();
				inv.setUserId(userId);
				inv.setWeiXinOpenId(wx.getOpenid());
				crmInvestorService.updateByUserId(inv);
				LOGGER.info("更新投呗WeiXinOpenId成功！userId={}",userId);
			}
			
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 保存活动分享用户
	 */
	@RequestMapping("saveShareUser")
	@ResponseBody
	public BaseResponse saveShareUser(@Valid ShareUserRequest req, BindingResult result, AppRequestHead head) {
		LOGGER.info("保存活动分享用户  ShareUserRequest {} ",JSON.toJSONString(req));
		if(AppResponseUtil.existsParamsError(result)) {
	    	return AppResponseUtil.getErrorParams(result);
        }
		CrmShareUserList shareUser = new CrmShareUserList();
		shareUser.setActivityType(Integer.parseInt(req.getActivityType()));
		shareUser.setUserName(req.getUserName());
		shareUser.setMobile(req.getMobile());
		crmShareUserListService.insert(shareUser);
		
		return AppResponseUtil.getSuccessResponse();
	}
	
	
	/**
	 * 是否新用户
	 */
	@RequestMapping("isNew")
	@ResponseBody
	public BaseResponse isNew(AppRequestHead head) {
		if(!AppUtils.isChannelApp(head.getOrgNumber()))return AppResponseUtil.getErrorBusi("100002", "请使用正确的app类型");
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Map<String, Boolean> result = Maps.newHashMap();
		result.put("isNew", crmCfplannerService.isNew(userId));
		return AppResponseUtil.getSuccessResponse(result);
	}
	
	/**
	 * V4.0个人中心(user.personalCenter)
	 * @param head
	 */
	@RequestMapping("/personalCenter")
	@ResponseBody
	public BaseResponse personalCenter(AppRequestHead head) {
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());

		CrmCfplanner rlt = crmCfplannerService.personalCenter(userId);
		boolean auth = accountbindService.queryAuthentication(userId);
		if(!auth){
			rlt.setBankCard(null);
			rlt.setUserName(null);
		}
		return AppResponseUtil.getSuccessResponse(rlt,CfplannerPersonCenterResponse.class);
	}
	/**
	 * V4.0个人名片--个人信息(user.userInfo)
	 * @param head
	 * @throws IOException 
	 */
	@RequestMapping("/userInfo")
	@ResponseBody
	public BaseResponse queryCfpUserInfo(@Valid ChooseBackgroundRequest req,BindingResult result,AppRequestHead head) throws IOException {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());

		if("1".equals(req.getType())){
			CrmCfplanner rlt = crmCfplannerService.queryCfpUserInfo(userId);
			rlt.setQrcode(createQrCode(req.getType(),userId));
			return AppResponseUtil.getSuccessResponse(rlt,CfplannerUserInfoResponse.class);
		}else{
			CrmInvestor inv = crmInvestorService.queryInvestorByUserId(userId);
			inv.setQrcode(createQrCode(req.getType(),userId));
			return AppResponseUtil.getSuccessResponse(inv,InvestorUserInfoResponse.class);
		}
		
	}
	
	/**
	 * V4.0邀请记录--推荐理财师(user.invitationCfp)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/invitationCfp")
	@ResponseBody
	public BaseResponse invitationCfpList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userId",userId);
		Page<CrmCfplanner> page  = new Page<CrmCfplanner>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CrmCfplanner> datas =  null;
		try {
			datas = crmCfplannerService.invitationCfpList(page,conditions);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询推荐理财师失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,InvitationCfpPageListResponse.class);
	}
	
	/**
	 * V4.0邀请记录--邀请客户(user.invitationInvestor)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/invitationInvestor")
	@ResponseBody
	public BaseResponse invitationInvestorList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userId",userId);
		Page<CrmCfplanner> page  = new Page<CrmCfplanner>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CrmCfplanner> datas =  null;
		try {
			datas = crmCfplannerService.invitationInvestorList(page,conditions);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询邀请客户失败");
		}
		return AppResponseUtil.getSuccessResponse(datas,InvitationInvestorPageListResponse.class);
	}
	
	/**
	 * V4.0个人名片--切换背景(user.chooseBackground)
	 * @param head
	 */
	@RequestMapping("/chooseBackground")
	@ResponseBody
	public BaseResponse chooseBackground(@Valid ChooseBackgroundRequest req,BindingResult result,AppRequestHead head) {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		} 
		List<SmBrandPromotion> list = new ArrayList<SmBrandPromotion>();
		SmBrandPromotion smBr = new SmBrandPromotion();
		smBr.setAppType(req.getType());
		smBr.setUseType("2");
		smBr.setStatus(0);
		try {
			list = smBrandPromotionService.selectListByCondition(smBr);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询个人名片背景图片失败");
		}

		List<String> backlist = new ArrayList<String>();
		for(SmBrandPromotion smb:list){
			backlist.add(smb.getImage());
		}
		ChooseBackgroundResponse rlt = new ChooseBackgroundResponse();
		rlt.setType(req.getType());
		rlt.setBackgroundImg(backlist);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * V4.0个人品牌推广(user.brandPromotion)
	 * @param head
	 * @throws IOException 
	 */
	@RequestMapping("/brandPromotion")
	@ResponseBody
	public BaseResponse brandPromotion(@Valid ChooseBackgroundRequest req,BindingResult result,AppRequestHead head,HttpServletRequest request) throws IOException {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		} 
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		List<SmBrandPromotion> list = new ArrayList<SmBrandPromotion>();
		SmBrandPromotion smBr = new SmBrandPromotion();
		smBr.setAppType(req.getType());
		smBr.setUseType("1");
		smBr.setStatus(0);
		try {
			list = smBrandPromotionService.selectAllListByCondition(smBr);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询个人海报图片失败");
		}
		BrandPromotionResponse rlt = new BrandPromotionResponse();
		
		List<BrandPromotionImageResponse> hotList = new ArrayList<BrandPromotionImageResponse>();
		List<BrandPromotionImageResponse> recList = new ArrayList<BrandPromotionImageResponse>();
		for(SmBrandPromotion smb:list){
			if("2".equals(smb.getTypeValue())){//2:热点海报
				BrandPromotionImageResponse hotImage = new BrandPromotionImageResponse();
				rlt.setHotContent(smb.getContent());
				hotImage.setImage(smb.getImage());
				hotImage.setSmallImage(smb.getSmallImage());
				hotList.add(hotImage);
			}else if("3".equals(smb.getTypeValue())){//3:精品推荐
				BrandPromotionImageResponse recImage = new BrandPromotionImageResponse();
				rlt.setRecomContent(smb.getContent());
				recImage.setImage(smb.getImage());
				recImage.setSmallImage(smb.getSmallImage());
				recList.add(recImage);
			}
		}

		rlt.setHotPosterList(hotList);
		rlt.setRecommenList(recList);
		rlt.setQrcode(createQrCode(req.getType(),userId));
		
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	
	public String createQrCode(String type,String userId) throws IOException{
		String qrcode = null;
        CrmInvestor inv = crmInvestorService.queryInvestorByUserId(userId);
        
        ShareContent shareContent = new ShareContent();
        
		if("2".equals(type)){
			shareContent = commonHelper.getWechatShareLcsRcCus(inv.getMobile(),inv.getUserName());
		}else{
			shareContent = commonHelper.getWechatShareRclcs(inv.getMobile(),inv.getUserName());
		}
		
		if("1".equals(type)){
			CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
			qrcode = cfp.getQrcode();
			if(StringUtils.isBlank(cfp.getQrcode())){		
				org.springframework.core.io.Resource res = new ClassPathResource("icons/lcs.jpg");
				String logoPath = res.getFile().getPath();
				qrcode = creatRcCode(null,logoPath,shareContent.getShareLink());						
				crmCfplannerService.updateCfpQrByUserId(userId,qrcode);
			}
		}else{
			qrcode = inv.getQrcode();
			if(StringUtils.isBlank(inv.getQrcode())){		
				org.springframework.core.io.Resource res = new ClassPathResource("icons/invest.jpg");
				String logoPath = res.getFile().getPath();
				qrcode = creatRcCode(null,logoPath,shareContent.getShareLink());						
				crmInvestorService.updateInvQrByUserId(userId,qrcode);
			}
		}
		
		return qrcode;
		
	}
	
	/**
	 * 创建二维码并上传到图片服务器
	 * @param request
	 * @param content
	 * @return
	 */
	private String creatRcCode(HttpServletRequest request,String logoPath,String content){
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
            
            LOGGER.debug("executing request " + httppost.getRequestLine());
//            Header[] headers =  httppost.getAllHeaders();
            response = httpclient.execute(httppost);
            
            LOGGER.debug("----------------Response-----------------------");
            LOGGER.debug(response.getStatusLine().toString());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String resp = EntityUtils.toString(resEntity);
                LOGGER.debug("Response content length: " + resEntity.getContentLength());
                LOGGER.debug("Response content : " + resp);
                //resp = "{\"ret\":true,\"info\":[{\"md5\":\"9452f7a016c638061cb6f954c8dbff66\",\"size\":16807}]}";
                SingleImgInfo o = JsonUtils.fromJsonToObject(resp, SingleImgInfo.class);
                if(o==null || o.getInfo()==null){
                    return null;
                }
                return  o.getInfo().getMd5();
                //根据图片上传返回的结果获取第一个md5值
                /*String md5 = resp.split("\"md5\":\"")[1].split("\",")[0];
                return md5;*/
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
	 * V4.0邀请记录-统计数量(user.invitationNum)
	 * @param head
	 */
	@RequestMapping("/invitationNum")
	@ResponseBody
	public BaseResponse invitationNum(AppRequestHead head) {
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		} 
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("userId",userId);
		Page<CrmCfplanner> page  = new Page<CrmCfplanner>(1,10);
		PaginatorResponse<CrmCfplanner> cfpdatas =  null;
		PaginatorResponse<CrmCfplanner> invdatas =  null;
		try {
			cfpdatas = crmCfplannerService.invitationCfpList(page,conditions);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"统计数量查询推荐理财师失败");
		}
        
		try {
			invdatas = crmCfplannerService.invitationInvestorList(page,conditions);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"统计数量查询邀请客户失败");
		}
        InvitationNumResponse rlt = new InvitationNumResponse();
        rlt.setCfpNum(cfpdatas.getTotalCount());
		rlt.setInvestorNum(invdatas.getTotalCount());
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * V4.5.3理财师推广海报类型(user.postersType)
	 * @param head
	 * @throws IOException 
	 */
	@RequestMapping("/postersType")
	@ResponseBody
	public BaseResponse postersType(AppRequestHead head) throws IOException {
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		} 
		List<SmBrandPostersType> list = new ArrayList<SmBrandPostersType>();
		try {
			list = smBrandPromotionService.selectAllBrandPostersTypeList();
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询理财师推广海报类型失败");
		}
		SmBrandPostersTypeResponse rlt = new SmBrandPostersTypeResponse();
		rlt.setTypeList(list);
		
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	/**
	 * V4.5.3推广海报(user.brandPosters)
	 * @param head
	 * @throws IOException 
	 */
	@RequestMapping("/brandPosters")
	@ResponseBody
	public BaseResponse brandPosters(@Valid ChooseBackgroundRequest req,BindingResult result,AppRequestHead head,HttpServletRequest request) throws IOException {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		} 
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		List<SmBrandPromotion> list = new ArrayList<SmBrandPromotion>();
		SmBrandPromotion smBr = new SmBrandPromotion();
		smBr.setAppType(req.getType());
		try {
			list = smBrandPromotionService.selectBrandPostersList(smBr);
		} catch (Exception e) {
			return  new BaseResponse(errorCode,"查询推广海报失败");
		}
		BrandPostersResponse rlt = new BrandPostersResponse();
		
		List<BrandPromotionImageResponse> postlist = new ArrayList<BrandPromotionImageResponse>();
        for(SmBrandPromotion smBrand : list){
        	BrandPromotionImageResponse image = new BrandPromotionImageResponse();
        	image.setImage(smBrand.getImage());
        	image.setSmallImage(smBrand.getSmallImage());
        	postlist.add(image);
        }
		rlt.setPosterList(postlist);
		rlt.setQrcode(createQrCode("1",userId));
		
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	
}
