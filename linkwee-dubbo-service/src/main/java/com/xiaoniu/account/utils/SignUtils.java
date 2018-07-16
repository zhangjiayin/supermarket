package com.xiaoniu.account.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SignUtils {

	private static Log logger = LogFactory.getLog(SignUtils.class);
	
	public static String addSign(TreeMap<String, String> treeMap, String charset, String key) {
		//logger.info("--------addSign-------treeMap:" + treeMap);
		//遍历签名参数
		StringBuilder sign_sb = new StringBuilder();
		//遍历签名参数
		List<String> signPropertyList = new ArrayList<String>();
		
		Iterator<String> it = treeMap.keySet().iterator();
		while(it.hasNext()){
			String mapKey = it.next();
			if(StringUtils.isEmpty(treeMap.get(mapKey))) 
				continue;
			if(StringUtils.isEmpty(sign_sb.toString())) {
				sign_sb.append(mapKey + "=" + treeMap.get(mapKey));
			} else {
				sign_sb.append("&" + mapKey + "=" + treeMap.get(mapKey));
			}
			signPropertyList.add(mapKey);
		}
		sign_sb.append("&key=" + key);
		signPropertyList.add("key");
		
		//logger.info("----addSign---sign_sb:" + sign_sb);
		//logger.info("==== addSign ==== Sign properties:" + signPropertyList.toString());
		return MD5.md5(sign_sb.toString(), charset);
	}
	
	
	/**
	 * @param obj	对象包含sign,除此之外全部不为空的属性值都要加签 
	 * @param charset 签名编码
	 * @param key 签名的key
	 * @return
	 */
	public static String addSign(Object obj, String charset, String key) {
		if(obj == null) return null;
		TreeMap<String, String> treeMap = BeanUtil.beanToSortMap(obj);
		
		return addSign(treeMap, charset, key);
	}
	
	/**
	 * 
	 * @param obj  对象包含sign,除此之外全部不为空的属性值都要加签 
	 * @param charset 签名编码
	 * @param key	签名的key
	 * @return
	 */
	public static boolean isPassSign(Object obj, String charset, String key) {
		if(obj == null) return false;
		TreeMap<String, String> treeMap = BeanUtil.beanToSortMap(obj);
		String sign = treeMap.remove("sign");
		//logger.info("----isPassSign----sign:"+ sign);
		
		String addSign = addSign(treeMap, charset, key);
		
		//logger.info("----isPassSign----addSign:" + addSign);
		if(StringUtils.isNotEmpty(addSign) && addSign.equals(sign)) {
			return true;
		}
		logger.warn("==== addSign ==== signatures do not match, req sign:" + sign + ",server sign:" + addSign);
		return false;
		
	}
	
	
	/**
	 * 签名验证
	 * @param treeMap
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isPassSign(TreeMap<String, String> treeMap, String charset, String key) {
		TreeMap<String, String> tempMap = (TreeMap<String, String>)treeMap.clone();
		String sign = tempMap.remove("sign");
		//logger.info("----isPassSign----sign:"+ sign);
		
		String addSign = addSign(tempMap, charset, key);
		
		//logger.info("----isPassSign----addSign:" + addSign);
		if(StringUtils.isNotEmpty(addSign) && addSign.equals(sign)) {
			return true;
		}
		logger.warn("==== addSign ==== signatures do not match, req sign:" + sign + ",server sign:" + addSign);
		return false;
	}
	

	
	
	

}
