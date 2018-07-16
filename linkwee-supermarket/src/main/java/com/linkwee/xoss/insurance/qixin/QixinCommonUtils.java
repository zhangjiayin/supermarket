package com.linkwee.xoss.insurance.qixin;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.linkwee.core.util.MD5Utils;
import com.linkwee.web.model.CimInsuranceInfo;

public class QixinCommonUtils {
	
	protected  final static Logger LOGGER = LoggerFactory.getLogger(QixinCommonUtils.class);
	
	/**
	 * T呗platform转化齐欣平台类型
	 * @param appType
	 * @return
	 *  T呗platform android,ios,wechat,web,wap
	 */
	public static Integer platformChangeTb(String platform){
		Integer platformQX = null;
		if("web".equals(platform)){
			platformQX = 2;
		} else if("ios".equals(platform)){
			platformQX = 3;
		} else if("android".equals(platform)){
			platformQX = 4;
		} else {
			platformQX = 1;
		}
		return platformQX;
	}

	/**
	 * 齐欣签名   md5(apiKey + partnerId )
	 * @param cimInsuranceInfo
	 * @return
	 */
	public static String getSign(CimInsuranceInfo cimInsuranceInfo) {
		// TODO Auto-generated method stub
		return MD5Utils.md5(cimInsuranceInfo.getPartnerPrivateKey()+cimInsuranceInfo.getPartnerId(), "utf-8");
	}
	
	/**
	 * 通知返回成功
	 * @return
	 */
	public static Map<String, Boolean> getNotifySuccess(){
		return ImmutableMap.of("state", true);
	}

	/**
	 * 通知返回失败
	 * @return
	 */
	public static QixinNotifyBaseResponse getNotifyFailed(String returnMsg){
		return new QixinNotifyBaseResponse(false, returnMsg);
	}

	/**
	 * 齐欣通知签名
	 * @param selectByOrgNumber
	 * @param jsonData
	 * @return
	 */
	public static String getNotifySign(CimInsuranceInfo cimInsuranceInfo,String jsonData) {
		// TODO Auto-generated method stub
		LOGGER.info("齐欣通知签名  key={}  data={} ",cimInsuranceInfo.getPartnerPrivateKey(),jsonData);
		return MD5Utils.md5(cimInsuranceInfo.getPartnerPrivateKey()+jsonData, "utf-8");
	}
}
