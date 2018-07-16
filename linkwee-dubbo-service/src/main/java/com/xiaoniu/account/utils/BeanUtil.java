package com.xiaoniu.account.utils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;

public class BeanUtil {

	/**
	 *  将javabean转为map类型，然后返回一个map类型的值
	 * @param obj
	 * @return
	 */
	public static Map<String, String> beanToMap(Object obj) { 
	        Map<String, String> params = new HashMap<String, String>(0); 
	        try { 
	            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
	            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
	            for (int i = 0; i < descriptors.length; i++) { 
	                String name = descriptors[i].getName(); 
	                if (!StringUtils.equals(name, "class")) { 
	                    params.put(name, (String)propertyUtilsBean.getNestedProperty(obj, name)); 
	                } 
	            } 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	        return params; 
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
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> mapToSortMap(Map<String, String> map) {
		
		Map<String, String> tree = new TreeMap<String, String>();
		
		Set<String> keys = map.keySet();
		for (String key : keys) {
			tree.put(key, map.get(key));
		}
		
		return tree;
	}
}
