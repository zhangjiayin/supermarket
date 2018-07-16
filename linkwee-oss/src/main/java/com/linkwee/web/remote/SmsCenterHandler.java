package com.linkwee.web.remote;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.constant.Constants;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.api.RequestHead;
import com.linkwee.web.dao.MsgDao;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.enums.SmsVcodeTypeEnum;
import com.linkwee.web.helper.ConfigHelper;
import com.linkwee.web.helper.FinalConfigHelper;
import com.linkwee.web.model.CustomerDevice;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.Msg;
import com.linkwee.web.model.PushMessageInfo;
import com.linkwee.web.request.SendVcodeRequest;
import com.linkwee.web.service.CustomerDeviceService;
import com.linkwee.web.service.InvestorUserInfoService;
import com.linkwee.web.service.PushMessageService;
import com.linkwee.web.service.SystemConfigService;
import com.linkwee.web.util.AppUtils;
import com.linkwee.web.util.WebUtil;
import com.xiaoniu.sms.enums.MobileOsTypeEnum;
import com.xiaoniu.sms.req.CheckCodeReq;
import com.xiaoniu.sms.req.GenerateCodeReq;
import com.xiaoniu.sms.req.PushMessageReq;
import com.xiaoniu.sms.service.IPushMessageService;
import com.xiaoniu.sms.service.IRandomCodeService;
import com.xiaoniu.sms.service.ISmsService;
import com.xiaoniu.sms.util.SmsResult;

@Component
public class SmsCenterHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private FinalConfigHelper finalConfigHelper;

	@Resource
	private IRandomCodeService p2pRandomCodeService;

	@Resource
	private IPushMessageService p2pPushMessageService;

	@Resource
	private ISmsService p2pSmsService;

	@Resource
	private PushMessageService pushMessageService;

	@Resource
	private CustomerDeviceService customerDeviceService;

	@Resource
	private MsgDao msgDao;
//	private MsgService msgService;

	@Resource
	private InvestorUserInfoService investorUserInfoService;

	@Resource
	private SystemConfigService systemConfigService;

	/**
	 * 检测手机验证码是否正确
	 * 
	 * @param module
	 * @param head
	 * @param mobile
	 * @param vcode
	 * @return
	 */
	public boolean checkCode(int type, RequestHead head, String mobile,
			String vcode) {
		// 验证码校验
		CheckCodeReq checkCodeDto = new CheckCodeReq();
		checkCodeDto.setMobile(mobile);
		/************** 供203上测试使用 ********************/
		// checkCodeDto.setPartnerId(ConfigHelper.getValue(1,SysConfigConstant.SEND_VCODE_PARTNERID));

		/*************** 预发布和线上使用配置 ******************************/
		checkCodeDto.setPartnerId(ConfigHelper.getValue(
				AppUtils.getAppType(head.getApp_key()).getKey(),
				SysConfigConstant.SEND_VCODE_PARTNERID));// (ConfigHelper.getValue(AppUtils.getAppType(head.getApp_key()).getKey()==0?1:SysConfigConstant.SEND_VCODE_PARTNERID));

		checkCodeDto.setModuleId(EnumUtils.getValueByKey(type,
				SmsVcodeTypeEnum.values()));
		checkCodeDto.setCode(vcode);
		SmsResult<Object> smsResult = p2pRandomCodeService
				.checkCode(checkCodeDto);
		logger.info("UserService修改登录密码,checkCodeDto={},result={}",
				checkCodeDto, smsResult);
		return smsResult.getReturnCode() == Constants.SUCCESS;
	}

	/**
	 * 发送手机验证码
	 * 
	 * @param module
	 * @param head
	 * @param mobile
	 * @return
	 */
	public boolean sendVcode(int type, String mobile) {
		GenerateCodeReq genCodeDto = new GenerateCodeReq();
		genCodeDto.setPartnerId(ConfigHelper
				.getValue(SysConfigConstant.SEND_VCODE_PARTNERID));
		genCodeDto.setModuleId(EnumUtils.getValueByKey(type,
				SmsVcodeTypeEnum.values()));
		genCodeDto.setMobile(mobile);
		genCodeDto.setCount(6);
		if (!finalConfigHelper.isSendMsg()) {
			genCodeDto.setSend(1);
		}
		SmsResult<String> smsResult = p2pRandomCodeService
				.generateCode(genCodeDto);
		logger.info("UserService发送手机验证码,checkCodeDto={},result={}", genCodeDto,
				smsResult);
		if (smsResult.getReturnCode() == Constants.SUCCESS) {
			logger.debug("验证码为:" + smsResult.getData());
			return true;
		} else {
			// logger.error("失败原因(code:{},msg:{})",smsResult.getReturnCode(),smsResult.getReturnMsg());
			logger.error("失败原因(genCodeDto:{},smsResult:{})", genCodeDto,
					smsResult);
			return false;
		}
	}

	/**
	 * 发送手机验证码
	 * 
	 * @param module
	 * @param head
	 * @param mobile
	 * @return
	 */
	public boolean sendVcode(SendVcodeRequest req, RequestHead head) {
		GenerateCodeReq genCodeDto = new GenerateCodeReq();
		/**
		 * 根据key和apptye 确定SysConfigValue
		 */
		/************** 供203上测试使用 ********************/
		// genCodeDto.setPartnerId(ConfigHelper.getValue(1,SysConfigConstant.SEND_VCODE_PARTNERID));
		/*************** 预发布和线上使用配置 ******************************/
		genCodeDto.setPartnerId(ConfigHelper.getValue(
				AppUtils.getAppType(head.getApp_key()).getKey() == 0 ? 1
						: AppUtils.getAppType(head.getApp_key()).getKey(),
				SysConfigConstant.SEND_VCODE_PARTNERID));

		genCodeDto.setModuleId(EnumUtils.getValueByKey(req.getType(),
				SmsVcodeTypeEnum.values()));
		if (StringUtils.isBlank(req.getMobile())) {
			if (StringUtils.isBlank(head.getToken())) {
				logger.error("失败原因:手机号为空，用户登录token也为空");
				return false;
			}
			logger.debug("发送手机号,checkCodeDto={},userId={}", genCodeDto,
					WebUtil.getUserId(head.getToken()));
			InvestorUserInfo info = investorUserInfoService
					.queryInvestorUserInfo(WebUtil.getUserId(head.getToken()));
			genCodeDto.setMobile(info.getMobile());
		} else {
			genCodeDto.setMobile(req.getMobile());
		}

		genCodeDto.setCount(6);
		if (!finalConfigHelper.isSendMsg()) {
			genCodeDto.setSend(1);
		}
		SmsResult<String> smsResult = p2pRandomCodeService
				.generateCode(genCodeDto);
		logger.debug("UserService发送手机验证码,checkCodeDto={},result={}",
				genCodeDto, smsResult);
		if (smsResult.getReturnCode() == Constants.SUCCESS) {
			logger.debug("验证码为:" + smsResult.getData());
			return true;
		} else {
			logger.error("失败原因(genCodeDto:{},smsResult:{})", genCodeDto,
					smsResult);
			return false;
		}
	}

	/**
	 * 佣金到帐消息推送
	 * 
	 * @param userId
	 *            用户ID
	 * @param moduleId
	 * @param sysConfigKey
	 * @param sysConfigappType
	 * @param month
	 * @throws Exception
	 */
	public void pushMessage(Map<String, Object> paramsMap) throws Exception {
		if (paramsMap == null) {
			logger.error("佣金到账消息推送:参数列表paramsMap为空");
			return;
		}
		String userId = null;
		String moduleId = null;
		String month = null;
		String sysConfigKey = null;
		int sysConfigappType = 0;
		if (StringUtils.isEmpty(String.valueOf(paramsMap.get("userId")))) {
			logger.error("佣金到账消息推送:userId参数为空");
			return;
		} else {
			userId = String.valueOf(paramsMap.get("userId"));
		}
		if (StringUtils.isEmpty(String.valueOf(paramsMap.get("moduleId")))) {
			logger.error("佣金到账消息推送:moduleId参数为空");
			return;
		} else {
			moduleId = String.valueOf(paramsMap.get("moduleId"));
		}
		if (StringUtils.isEmpty(String.valueOf(paramsMap.get("month")))) {
			logger.error("佣金到账消息推送:month参数为空");
			return;
		} else {
			month = String.valueOf(paramsMap.get("month"));
		}
		if (StringUtils.isEmpty(String.valueOf(paramsMap.get("sysConfigKey")))) {
			logger.error("佣金到账消息推送:sysConfigKey参数为空");
			return;
		} else {
			sysConfigKey = String.valueOf(paramsMap.get("sysConfigKey"));
		}
		if (StringUtils.isEmpty(String.valueOf(paramsMap
				.get("sysConfigappType")))) {
			logger.error("佣金到账消息推送:sysConfigappType参数为空");
			return;
		} else {
			sysConfigappType = Integer.valueOf(String.valueOf(paramsMap
					.get("sysConfigappType")));
		}

		String content = String.format(
				findMsgContent(sysConfigKey, sysConfigappType), month);

		Msg msg = new Msg();
		msg.setAppType(sysConfigappType);
		msg.setStatus(0);
		msg.setType(1);
		msg.setUserNumber(userId);
		msg.setContent(content);
		msgDao.add(msg);

		CustomerDevice customerDevice = customerDeviceService
				.queryCustomerDevice(sysConfigappType, userId);
		if (null != customerDevice
				&& StringUtils.isNotBlank(customerDevice.getToken())
				&& customerDevice.getIsSendplatformnotice() == 0
				&& (customerDevice.getDeviceType().equals(
						PlatformEnum.ANDROID.getValue()) || customerDevice
						.getDeviceType().equals(PlatformEnum.IOS.getValue()))) {
			PushMessageReq pushMsgReq = new PushMessageReq();
			if (customerDevice.getDeviceType().equals("ios")) {
				pushMsgReq.setOsType(MobileOsTypeEnum.IOS.getOldType());
			} else {
				pushMsgReq.setOsType(MobileOsTypeEnum.ANDROID.getOldType());
			}
			Map<String, Object> urlparam = new HashMap<String, Object>();
			urlparam.put("customerId", userId);
			JSONObject jsonObject = new JSONObject(urlparam);
			pushMsgReq.setCustomContent(jsonObject.toJSONString());

			pushMsgReq.setPartnerId("LHLC");
			pushMsgReq.setModuleId(moduleId);
			pushMsgReq.setUserId(userId);
			pushMsgReq.setValue(content);
			pushMsgReq.setInputPushToken(true);// 是否需要传入推送的token,默认为false
			pushMsgReq.setPushToken(customerDevice.getToken());

			SmsResult<Object> smsRlt = p2pPushMessageService
					.pushMessage(pushMsgReq);
			if (smsRlt.getReturnCode() != 0) {
				logger.error("|invoke p2pPushMessageService.pushMessage|失败原因:FeePayEntity(userId:"
						+ userId
						+ ",month:"
						+ month
						+ ")PushMessageReq:"
						+ pushMsgReq.toString()
						+ "smsRlt:"
						+ smsRlt.getReturnMsg());
				PushMessageInfo pushMessageInfo = new PushMessageInfo();
				pushMessageInfo.setAppType(sysConfigappType);
				pushMessageInfo.setChgTime(new Date());
				pushMessageInfo.setDelstatus(0);// 正常
				pushMessageInfo.setErrorInfo(smsRlt.getReturnMsg());
				pushMessageInfo.setIsTimingTask(0);
				pushMessageInfo.setMsgType(String.valueOf(0));// 通知栏+个人消息
				pushMessageInfo.setSendTime(new Date());
				pushMessageInfo.setStatus(-1);// 失败
				pushMessageInfo.setUserId(userId);
				pushMessageInfo.setMsgParam(pushMsgReq.toString());
				pushMessageService.add(pushMessageInfo);
			}
		}

	}

	/**
	 * 获取t_system_config_new 里配置消息推送信息
	 * 
	 * @param sysConfigKey
	 * @param sysConfigAppType
	 * @return
	 * @throws Exception
	 */
	private String findMsgContent(String sysConfigKey, int sysConfigAppType)
			throws Exception {
		return systemConfigService.getValuesByKey(sysConfigKey,
				sysConfigAppType);
	}
}
