package linkwee.helper;

import java.util.TreeMap;

import linkwee.utils.CommonUtils;
import linkwee.utils.HttpClientUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.xn.user.utils.RequestSignUtils;

import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.account.utils.SignUtils;
import com.xiaoniu.sms.util.SHA1;

@Component
public class CommonHelper {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CommonHelper.class);
	
	@Value("${userCenterHttpUrl}")
	private String userCenterHttpUrl;
	
	@Value("${cacheCenterHttpUrl}")
	private String cacheCenterHttpUrl;
	
	@Value("${cacheSignKey}")
	private String cacheSignKey;
	
	@Value("${smsCenterHttpUrl}")
	private String smsCenterHttpUrl;
	
	@Value("${smsSignKeyLHLC}")
	private String smsSignKeyLHLC;
	
	@Value("${smsSignKeyLHJF}")
	private String smsSignKeyLHJF;
	
	@Value("${accountCenterHttpUrl}")
	private String accountCenterHttpUrl;
	
	@Value("${accountSignKey}")
	private String accountSignKey;
	
	/**
	 * 用户中心HTTP请求
	 * @param parameterUrl  请求参数地址
	 * @param returnClazz	请求返回对象Class
	 * @param objectParams   请求不定长参数
	 * @return
	 */
	public <T> T userCenterHttpRequest(String parameterUrl,Class<T> returnClazz,Object... objectParams){
		LOGGER.debug("用户中心HTTP请求");
		String returnJsonString = null;
		returnJsonString = HttpClientUtils.invokePost(userCenterHttpUrl+parameterUrl, null, objectParams);
		return parseObject(returnJsonString,returnClazz);
	}
	
	/**
	 * 缓存中心HTTP请求
	 * @param parameterUrl  请求参数地址
	 * @param returnClazz	请求返回对象Class
	 * @param objectParams   请求不定长参数
	 * @return
	 */
	public <T> T cacheCenterHttpRequest(String parameterUrl,Class<T> returnClazz,Object... objectParams){
		/**
		 * http调用方式以下参数为必填
		 * 1:  systemType，业务id，用来区分钱罐子、领会等     (这里特殊处理，全部当成LHLC处理,已和技术平台沟通)
		 * 2:sign，签名，签名方式与用户中心一致
		 */
		LOGGER.debug("缓存中心HTTP请求");
		TreeMap<String,String> paramsMap = CommonUtils.getTreeMapPairList(objectParams);
		paramsMap.put("systemType", "LHLC");
		paramsMap.put("sign", RequestSignUtils.addSign(paramsMap,cacheSignKey));
		String returnJsonString = null;
		returnJsonString = HttpClientUtils.invokePost(cacheCenterHttpUrl+parameterUrl, null, paramsMap);
		return parseObject(returnJsonString,returnClazz);
	}
	
	/**
	 * 消息中心HTTP请求
	 * @param parameterUrl  请求参数地址
	 * @param returnClazz	请求返回对象Class
	 * @param objectParams   请求不定长参数
	 * @return
	 */
	public <T> T smsCenterHttpRequest(String parameterUrl,Class<T> returnClazz,Object... objectParams){
		/**
		 * 签名，所有http接口都需要验证签名, dubbo接口不需要验证此参数
		 * LHLC和LHJF含有不同的签名密钥
		 * signature不参与计算签名
		 */
		LOGGER.debug("消息中心HTTP请求");
		TreeMap<String,String> paramsMap = CommonUtils.getTreeMapPairList(objectParams);
		paramsMap.remove("signature");
		String sign = null;
		if("LHLC".equalsIgnoreCase(paramsMap.get("partnerId"))){
			 sign = SHA1.addSign(paramsMap,smsSignKeyLHLC);
		} else {
			 sign = SHA1.addSign(paramsMap,smsSignKeyLHJF);
		}
        paramsMap.put("signature", sign);
		String returnJsonString = null;
		returnJsonString = HttpClientUtils.invokePost(smsCenterHttpUrl+parameterUrl, null, paramsMap);
		return parseObject(returnJsonString,returnClazz);
	}
	
	/**
	 * 账户中心HTTP请求
	 * @param parameterUrl  请求参数地址
	 * @param returnClazz	请求返回对象Class
	 * @param objectParams   请求不定长参数
	 * @return
	 */
	public <T> T accountCenterHttpRequest(String parameterUrl,Class<T> returnClazz,Object... objectParams){
		/**
		 * http请求时
		 * 支付密码服务添加签名（原dubbo服务下未添加签名）
		 */
		LOGGER.debug("账户中心HTTP请求");
		TreeMap<String,String> paramsMap = CommonUtils.getTreeMapPairList(objectParams);
		if(!SignUtils.isPassSign(paramsMap, "UTF-8", accountSignKey)){
			paramsMap.put("charset","UTF-8");
			paramsMap.put("sign",SignUtils.addSign(paramsMap,"UTF-8",accountSignKey));
		}
		String returnJsonString = null;
		returnJsonString = HttpClientUtils.invokePost(accountCenterHttpUrl+parameterUrl, null, paramsMap);
		return parseObject(returnJsonString,returnClazz);
	}
	
	private <T> T parseObject(String returnJsonString,Class<T> returnClazz){
		if(null == returnClazz){
			return null;
		} else {
			return JSONObject.parseObject(returnJsonString,returnClazz);
		}
	}
	
	
}
