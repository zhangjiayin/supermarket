package com.linkwee.web.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.linkwee.web.model.SystemConfig;
import com.linkwee.web.service.SystemConfigService;

@Component
public class ConfigHelper {

	
	private static List<SystemConfig> configs = null;
	
	private Map<String,Map<String,Map<String, SystemConfig>>> appConfig= new HashMap<String,Map<String,Map<String, SystemConfig>>>();
	
	@Resource
	private SystemConfigService systemConfigService;

	@PostConstruct
	public void init() throws Exception {
		configs = systemConfigService.getSystemConfigs();
		for(SystemConfig config:configs){
			String appType = config.getAppType().toString();
			String type = config.getType();
			if(StringUtils.isBlank(type)){
				type = "nullType";
			}
			String key = config.getKey();
			Map<String,Map<String, SystemConfig>> typeConfig = appConfig.get(appType);
			if(typeConfig==null){
				typeConfig = new HashMap<String,Map<String, SystemConfig>>();
				appConfig.put(appType, typeConfig);
			}
			Map<String, SystemConfig> keyConfig = typeConfig.get(type);
			if(keyConfig==null){
				keyConfig = new HashMap<String,SystemConfig>();
				typeConfig.put(type,keyConfig);
			}
			keyConfig.put(key,config);
		}
	}

	/**
	 * 获取配置值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return getValue(null,null,key);
	}
	
	/**
	 * 获取配置值
	 * @param key
	 * @return
	 */
	public static String getValue(Integer appType,String key) {
		return getValue(appType,null,key);
	}
	
	
	/**
	 * 获取配置值
	 * @param appType
	 * @param type
	 * @param key
	 * @return
	 */
	public static String getValue(Integer appType,String type,String key) {
		if(StringUtils.isBlank(key)){
			return null;
		}
		for(SystemConfig config:configs){
			if((appType==null||config.getAppType().toString().equals(appType.toString()))
			&&(StringUtils.isBlank(type)||(config.getType()!=null&&type.equals(config.getType().toString())))
			&&config.getKey().toString().equals(key)){
				return config.getValue();
			}
		}
		return null;
	}

	/**
	 * 描述：根据类别获取配置
	 * 
	 * @param type 类别
	 * @return
	 */
	public Map<String, SystemConfig> getSystemConfigsByType(String type) {
		return getSystemConfigsByType(null,type);
	}
	
	/**
	 * 根据类别获取配置
	 * @param appType 应用类别
	 * @param type 类别
	 * @return
	 */
	public Map<String, SystemConfig> getSystemConfigsByType(Integer appType,String type) {
		if(StringUtils.isBlank(type)){
			return null;
		}
		if(appType==null){
			for(String tmpAppType:appConfig.keySet()){
				Map<String,Map<String, SystemConfig>> typeConfig = appConfig.get(tmpAppType);
				Map<String, SystemConfig> data = typeConfig.get(type);
				if(data!=null&&data.keySet().size()>0){
					return data;
				}
			}
		}else{
			Map<String,Map<String, SystemConfig>> typeConfig = appConfig.get(appType.toString());
			Map<String, SystemConfig> data = typeConfig.get(type);
			if(data!=null&&data.keySet().size()>0){
				return data;
			}
		}
		return null;
	}
	
	/**
	 * 描述：根据类别获取配置值
	 * 
	 * @param type 类别
	 * @return
	 */
	public Map<String,String> getValuesByType(String type) {
		return getValuesByType(null,type);
	}
	/**
	 * 根据类别获取配置值
	 * @param appType 应用类别
	 * @param type 类别
	 * @return
	 */
	public Map<String,String> getValuesByType(Integer appType,String type) {
		Map<String, SystemConfig> data = getSystemConfigsByType(appType,type);
		Map<String,String> ret = new HashMap<String,String>();
		for(String key:data.keySet()){
			ret.put(key, data.get(key).getValue());
		}
		return ret;
	}
}
