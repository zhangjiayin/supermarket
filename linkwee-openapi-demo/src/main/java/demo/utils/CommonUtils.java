package demo.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import demo.enums.OrgEnum;
import demo.enums.RequestTypeEnums;

public class CommonUtils {
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
	
	/**
	 * POST请求获取结果
	 * @param orgEnum  请求来源机构枚举  机构类型
	 * @param requestTypeEnums  请求路径枚举   POST GET
	 * @param requestUrl  请求路径
	 * @param objectParams 请求参数,不定长参数，可以为多个
	 * @return
	 * @throws Exception
	 */
	public static String httpRequest(OrgEnum orgEnum,RequestTypeEnums requestTypeEnums,String requestUrl,Object... objectParams){
		
		/**
		 * 将请求的参数转化成Map<String,String> 对象
		 */
		Map<String,String> paramsMap = new HashMap<String,String>();
		if(objectParams!=null && objectParams.length>0){
			for(Object obj:objectParams){
				Map<String,String> map = obj2Map(obj);
				paramsMap.putAll(map);
			}
		}
		
        /**
         * 添加签名
         */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("orgNumber", orgEnum.getOrgNumber());//机构编码
		paramsMap.put("orgKey",orgEnum.getOrgKey());//机构明钥
		paramsMap.put("timestamp",simpleDateFormat.format(new Date()));
        String sign = SignUtils.sign(paramsMap,orgEnum.getOrgSecret());//机构私钥
        paramsMap.put("sign", sign);
		
        /**
         * 将所有的参数转化成post请求 所需要的对象
         */
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for(String key:paramsMap.keySet()){
        	formparams.add(new BasicNameValuePair(key,paramsMap.get(key)));  
        }
        
        /**
         * POST请求得到结果
         */
		String reslult = null;
		try {
			LOGGER.info("请求参数：requestUrl={},formparams={}",requestUrl,JSONObject.toJSONString(formparams));
			if("post".equalsIgnoreCase(requestTypeEnums.getValue()) || requestTypeEnums == null){
				reslult = HttpRequestClient.invokePostHttp(requestUrl, formparams, "UTF-8");
			} else {
				reslult = HttpRequestClient.invokeGetHttp(requestUrl, formparams, "UTF-8");				
			}	
		} catch (Exception e) {
			LOGGER.info("请求得到结果异常：{}",e);
		}
		
		LOGGER.info("请求返回报文：{}",reslult);
		return reslult;
	}
	
	/**
	 * 将object对象转化成Map<String,String>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static Map<String,String> obj2Map(Object obj){
		 Map<String,String> ret = new HashMap<String,String>();
		 if(obj==null){
			return ret;
		 }
		 if(obj instanceof Map){
			 return (Map<String,String>)obj;
		 }
		 Class<?> clazz = obj.getClass();
		 while(clazz!=null){
			 Field[] fields = clazz.getDeclaredFields();
			 for(Field field:fields){
				 String name = field.getName();
				 field.setAccessible(true);
				 Object value = null;
				try {
					value = field.get(obj);
				} catch (Exception e) {
					LOGGER.info("将object对象转化成Map<String,String>异常：{}",e);
				}
				 field.setAccessible(false);
				 if(value!=null){
					 ret.put(name, value.toString());
				 }
			 }
			 clazz = clazz.getSuperclass();
		 }
		 return ret;
	}
}
