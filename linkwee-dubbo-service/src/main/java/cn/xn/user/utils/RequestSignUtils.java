package cn.xn.user.utils;

import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求计算签名/验证签名接口
 * @author 颜彩云
 *
 */
public class RequestSignUtils {
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(RequestSignUtils.class);

	private static final String CHARSET = "UTF-8";
	
	public static String addSign(TreeMap<String, String> treeMap, String key) {
		//遍历签名参数
		StringBuilder sign_sb = new StringBuilder();
		
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
		}
		
		LOGGER.debug("拼接签名字符串前:{},	签名key={}",sign_sb.toString(),key);
		sign_sb.append("&key=" + key);
		LOGGER.debug("拼接签名字符串后 :{}",sign_sb.toString());
		
		String sign = MD5.md5(sign_sb.toString(), CHARSET);
		LOGGER.debug("MD5生成最终签名:{}",sign);
		return sign;
	}
	
	
	/**
	 * @param obj	对象包含sign,除此之外全部不为空的属性值都要加签 
	 * @param key 签名的key
	 * @return
	 */
	public static String addSign(Object obj, String key) {
		if(obj == null) return null;
		TreeMap<String, String> treeMap = beanToSortMap(obj);
		treeMap.remove("sign");
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
	
}
