package com.xiaoniu.sms.util;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

/**
 * Json和对象直接转换类（使用jackson）
 * @author 颜彩云
 *
 */
public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	static{
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * 对象转换成json
	 * @param obj 需要转换的对象
	 * @return
	 */
	public static String objectToJson(Object obj){
		String json = null;
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * json转换成对象
	 * @param json json字符串
	 * @param clazz 对象的class信息
	 * @return
	 */
	public static <T>T jsonToObject(String json,Class<T> clazz){
		T obj = null;
		try {
			obj = objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * json 转换成List 列表
	 * @param json json字符串
	 * @param type 
	 * @return
	 */
	public static <T>T jsonToObject(String json, TypeReference<T> type){
		T obj = null;
		try {
			obj = objectMapper.readValue(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void main(String[] args) {

	}
	
}
