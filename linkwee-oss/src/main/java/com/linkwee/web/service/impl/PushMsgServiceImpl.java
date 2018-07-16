package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.constant.Constants;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.dao.MsgDao;
import com.linkwee.web.dao.SaleUserInfoDao;
import com.linkwee.web.dao.SystemConfigDao;
import com.linkwee.web.dao.UsercustomerrelDao;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgPartnerIdEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CustomerDevice;
import com.linkwee.web.model.Msg;
import com.linkwee.web.model.PushMessageInfo;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.SystemConfig;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.service.CustomerDeviceService;
import com.linkwee.web.service.PushMessageService;
import com.linkwee.web.service.PushMsgService;
import com.xiaoniu.sms.enums.MobileOsTypeEnum;
import com.xiaoniu.sms.req.PushMessageReq;
import com.xiaoniu.sms.service.IPushMessageService;
import com.xiaoniu.sms.util.SmsResult;

@Service("pushMsgService")
public class PushMsgServiceImpl implements PushMsgService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IPushMessageService p2pPushMessageService;
	
	@Resource
	private PushMessageService pushMessageService;

	@Resource
	private CustomerDeviceService customerDeviceService;
	@Autowired
	private UsercustomerrelDao usercustomerrelDao;
	@Autowired
	private SaleUserInfoDao saleUserInfoDao;
	
	@Autowired
	private MsgDao msgDao;
	
	@Autowired
	private SystemConfigDao systemConfigDao;

	/**
	 * 定期产品到期回款 推送消息
	 */
	@Override
	public void pushMSgRepayment(String customerId, String bizTime, String productName, double amount) {
		Usercustomerrel usercustomerrel = new Usercustomerrel();
		usercustomerrel.setCustomerid(customerId);
		usercustomerrel = usercustomerrelDao.query(usercustomerrel);
		if (null != usercustomerrel) {
			String customerName = usercustomerrel.getCustomername();
			String customerMobile = usercustomerrel.getCustomermobile();
			String content ="";
			String fomat = getconfig(AppTypeEnum.INVESTOR.getKey(),SysConfigConstant.PUSHMESSAGE_CFIXEDRETURN);
			if(StringUtils.isNotBlank(fomat)){
				content =  String.format(fomat, bizTime
						,productName);
			}			
			// 金服 推送消息
			pushMessage(AppTypeEnum.INVESTOR.getKey(), SmsTypeEnum.CFIXEDRETURN.getKey(), customerId,
					bizTime + "," + productName, 0,content,0,null);
			SaleUserInfo saleUserInfo = new SaleUserInfo();
			saleUserInfo.setNumber(usercustomerrel.getCurrsaleuser());
			saleUserInfo = saleUserInfoDao.query(saleUserInfo);
			String saleUserId = saleUserInfo.getCustomerId();
			if (null != saleUserInfo) {
				// 理财师 推送消息
				String param = "";
				if (StringUtils.isNotBlank(customerName)) {
					if (StringUtils.isNumeric(customerName)) {
						param = customerName.substring(customerName.length() - 4);
					} else {
						param = customerName;
						if (StringUtils.isNotBlank(customerMobile)) {
							param = param + "*" + customerMobile.substring(customerMobile.length() - 4);
						}
					}
				} else {
					if (StringUtils.isNotBlank(customerMobile)) {
						param = customerMobile.substring(customerMobile.length() - 4);
					}
				}
				param += ","+productName+ ","+ amount;
				
				String lcontent = "";
				
				fomat = getconfig(AppTypeEnum.CHANNEL.getKey(), SysConfigConstant.PUSHMESSAGE_LCUSTOMERFIXEDRETURN);
				if(StringUtils.isNotBlank(fomat)){
					lcontent =  String.format(fomat, StringUtils
							.isBlank(customerName)?"":customerName
									+"*"+ customerMobile.substring(customerMobile.length() - 4),productName,amount);
				}	
				Map map = new HashMap<String, Object>();
				map.put("customerId", customerId);
				pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.LCUSTOMERFIXEDRETURN.getKey(), saleUserId, param,
						0,lcontent,0,map);
			}
		}
	}

	/**
	 * 推送消息
	 * @Auther liqi
	 * @Date 2016年5月27日 下午21:08:20
	 * @param appType app类型
	 * @param type 消息类型
	 * @param userId 用户id
	 * @param values 消息参数，用,分隔
	 * @param isTimingTask 是否需要定时发送  1-需要      0-不需要
	 * @param content 添加个人消息内容
	 * @param pushType 推送方式：0-消息栏+个人消息  1-消息栏 2-个人消息
	 * @return
	 */
	public boolean pushMessage(int appType, int type, String userId, String values, int isTimingTask, String content,int pushType,Map<String, Object> urlparam) {
		if (StringUtils.isNotBlank(content) && pushType != 1) {
			logger.info("个人消息推送：pushType-{},消息内容-{}",pushType,content);
			Msg msg = new Msg();
			msg.setAppType(appType);
			msg.setStatus(0);
			msg.setType(1);
			msg.setUserNumber(userId);
			msg.setContent(content);
			msgDao.add(msg);
		}

		if (pushType != 2) {
			CustomerDevice customerDevice = customerDeviceService.queryCustomerDevice(appType, userId);
			logger.info("消息栏消息推送：用户设备信息-{}",JSONObject.toJSONString(customerDevice));
			if (null != customerDevice  && StringUtils.isNotBlank(customerDevice.getToken())   && customerDevice.getIsSendplatformnotice() ==0  && (customerDevice.getDeviceType().equals(PlatformEnum.ANDROID.getValue())
					|| customerDevice.getDeviceType().equals(PlatformEnum.IOS.getValue()))) {
				PushMessageReq pushMessageDto = new PushMessageReq();
				if (customerDevice.getDeviceType().equals("ios")) {
					pushMessageDto.setOsType(MobileOsTypeEnum.IOS.getOldType());
				} else {
					pushMessageDto.setOsType(MobileOsTypeEnum.ANDROID.getOldType());
				}
				pushMessageDto.setPartnerId(EnumUtils.getValueByKey(appType, MsgPartnerIdEnum.values()));// (commonHelper.getSysConfigValue(appType,SysConfigConstant.PUSHMESSAGE_PARTNERID));
				pushMessageDto.setModuleId(EnumUtils.getValueByKvmEnumKey(type, SmsTypeEnum.values()));
				pushMessageDto.setUserId(userId);
				pushMessageDto.setValue(values);
				pushMessageDto.setInputPushToken(true);// 是否需要传入推送的token,默认为false
				pushMessageDto.setPushToken(customerDevice.getToken());

				if(StringUtils.isNotBlank(EnumUtils.getMsgByKvmEnumKey(type, SmsTypeEnum.values())) && null != urlparam){
					JSONObject jsonObject = new JSONObject(urlparam);
					pushMessageDto.setCustomContent(jsonObject.toJSONString());
				}
				SmsResult<Object> rlt = p2pPushMessageService.pushMessage(pushMessageDto);
				logger.info("消息推送(pushMessageDto:{},rlt:{})",pushMessageDto,rlt);
				if (rlt.getReturnCode() == Constants.SUCCESS) {
					logger.info("消息栏消息推送成功");
					return true;
				} else {
					logger.info("消息栏消息推送失败");
					logger.info("失败原因(pushMessageDto:{},rlt:{})", pushMessageDto, rlt);
					PushMessageInfo pushMessageInfo = new PushMessageInfo();
					pushMessageInfo.setAppType(appType);
					pushMessageInfo.setChgTime(new Date());
					pushMessageInfo.setDelstatus(0);// 正常
					pushMessageInfo.setErrorInfo(rlt.getReturnMsg());
					pushMessageInfo.setIsTimingTask(isTimingTask);
					pushMessageInfo.setMsgParam(pushMessageDto.toString());
					pushMessageInfo.setMsgType(EnumUtils.getValueByKvmEnumKey(type, SmsTypeEnum.values()));
					// pushMessageInfo.setSendTime(sendTime);
					pushMessageInfo.setStatus(-1);// 失败
					pushMessageInfo.setUserId(userId);
					pushMessageService.add(pushMessageInfo);
					return false;
				}
			} else {
				logger.info("消息栏消息推送失败,查询用户设备信息为空或者不满足条件appType={},userId={}",appType,userId);
				return false;
			}

		} else {
			return true;
		}
	}
	
	

	/**
	 * 定期产品在投资额超（含）10万元，且3天后到期回款 推送消息
	 */
	@Override
	public void willRepaymentPushMsg(String userId, String productName, Double investAmt) {
		Usercustomerrel usercustomerrel = new Usercustomerrel();
		usercustomerrel.setCustomerid(userId);
		usercustomerrel = usercustomerrelDao.query(usercustomerrel);
		if (null != usercustomerrel) {
			String customerName = usercustomerrel.getCustomername();
			String customerMobile = usercustomerrel.getCustomermobile();
			String format = getconfig(AppTypeEnum.INVESTOR.getKey(),SysConfigConstant.PUSHMESSAGE_CFIXEDBIGAMOUNTRETURN);
			Map map = new HashMap<Object, Object>();
			map.put("customerId", userId);
			
			String content = String.format(format, productName);
			// 金服 推送消息
			pushMessage(AppTypeEnum.INVESTOR.getKey(), SmsTypeEnum.CFIXEDBIGAMOUNTRETURN.getKey(), userId,productName, 0,content
					,0,map);
			SaleUserInfo saleUserInfo = new SaleUserInfo();
			saleUserInfo.setNumber(usercustomerrel.getCurrsaleuser());
			saleUserInfo = saleUserInfoDao.query(saleUserInfo);
			String saleUserId = saleUserInfo.getCustomerId();
			if (null != saleUserInfo) {
				// 理财师 推送消息
				String param = "";
				if (StringUtils.isNotBlank(customerName)) {
					if (StringUtils.isNumeric(customerName)) {
						param = customerName.substring(customerName.length() - 4);
					} else {
						param = customerName;
						if (StringUtils.isNotBlank(customerMobile)) {
							param = param + "*" + customerMobile.substring(customerMobile.length() - 4);
						}
					}
				} else {
					customerName = "";
					if (StringUtils.isNotBlank(customerMobile)) {
						param = "*" +customerMobile.substring(customerMobile.length() - 4);
					}
				}
				param += ","+productName+"," + investAmt;
				
				String lformat = getconfig(AppTypeEnum.CHANNEL.getKey(),SysConfigConstant.PUSHMESSAGE_LCUSTOMERBIGAMOUNTRETURN);
				
				String lcontent = String.format(lformat, StringUtils.isNotBlank(customerName)?customerName
						:""+"*" + customerMobile.substring(customerMobile.length() - 4),productName,investAmt);
				pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.LCUSTOMERBIGAMOUNTRETURN.getKey(), saleUserId, param,
						0,lcontent,0,map);
			}
		}
		
	}
	
	
	
	private String getconfig(int appType,String key){
		SystemConfig config = new SystemConfig();
		config.setAppType(appType);
		config.setKey(key);
		config = systemConfigDao.query(config);
		if(null != config){
			return config.getValue();
		}else{
			return "";
		}
	}

	
}
