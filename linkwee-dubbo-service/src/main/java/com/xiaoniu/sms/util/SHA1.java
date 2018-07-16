package com.xiaoniu.sms.util;

import java.beans.PropertyDescriptor;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SHA1加密
 * @author 颜彩云
 *
 */
public class SHA1 {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SHA1.class);
	/**
	 * 使用SHA1加密字符串
	 * @param decript 需要加密的字符串
	 * @return
	 */
	public static String encrypt(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes("UTF-8"));
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 遍历TreeMap中所有非空值并拼接为 key1=value1&key2=value2的字符串，并在最后append密钥，然后使用SHA1加密
	 * @param treeMap 参数TreeMap
	 * @param secretKey 密钥
	 * @return
	 */
	public static String addSign(TreeMap<String, String> treeMap, String secretKey) {
		StringBuilder signSb = new StringBuilder();
		
		Iterator<String> it = treeMap.keySet().iterator();
		while(it.hasNext()){
			String mapKey = it.next();
			if(StringUtils.isEmpty(treeMap.get(mapKey))) {
				continue;
			}
			if(StringUtils.isEmpty(signSb.toString())) {
				signSb.append(mapKey + "=" + treeMap.get(mapKey));
			} else {
				signSb.append("&" + mapKey + "=" + treeMap.get(mapKey));
			}
		}
		LOGGER.debug("添加签名串之前:{}",signSb.toString());
		signSb.append(secretKey);
		LOGGER.info("添加签名串之后:{}",signSb.toString());
		
		return encrypt(signSb.toString());
	}
	
	
	/**
	 * @param obj	对象包含sign,除此之外全部不为空的属性值都要加签 
	 * @param key 签名的key
	 * @return
	 */
	public static String addSignByObj(Object obj, String key) {
		if(obj == null) return null;
		TreeMap<String, String> treeMap = beanToSortMap(obj);
		treeMap.remove("signature");
		return addSign(treeMap, key);
	}

	public static TreeMap<String, String> beanToSortMap(Object obj) { 
		TreeMap<String, String> params = new TreeMap<String, String>(); 
        try { 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                if (!StringUtils.equals(name, "class")) {
                	Object o = propertyUtilsBean.getNestedProperty(obj, name);
                	if(o != null)
                		params.put(name, o.toString()); 
                } 
            } 
        } catch (Exception e) { 
        	e.printStackTrace(); 
        } 
        return params; 
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("count=6&expire=15&ip=127.0.0.1&mobile=18617161373&moduleId=RESETLOGIN&partnerId=LHLC&send=1&type=0&updateCache=06ebZFfgdBKfIkhsjS4y3iF1KqsPmd8r3"));
	}
}
