package com.linkwee.xoss.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.linkwee.web.model.SysConfig;
import com.linkwee.web.service.SysConfigService;

@Component
public class ConfigHelper {

	
	private static List<SysConfig> configs = null;
	
	private Map<String,Map<String,Map<String, SysConfig>>> appConfig= new HashMap<String,Map<String,Map<String, SysConfig>>>();
	
	@Resource
	private SysConfigService sysConfigService;

	@PostConstruct
	public void init() throws Exception {
		configs = sysConfigService.getSystemConfigs();
		for(SysConfig config:configs){
			String appType = config.getAppType().toString();
			String type = config.getConfigType();
			if(StringUtils.isBlank(type)){
				type = "nullType";
			}
			String key = config.getConfigKey();
			Map<String,Map<String, SysConfig>> typeConfig = appConfig.get(appType);
			if(typeConfig==null){
				typeConfig = new HashMap<String,Map<String, SysConfig>>();
				appConfig.put(appType, typeConfig);
			}
			Map<String, SysConfig> keyConfig = typeConfig.get(type);
			if(keyConfig==null){
				keyConfig = new HashMap<String,SysConfig>();
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
		for(SysConfig config:configs){
			if((appType==null||config.getAppType().toString().equals(appType.toString()))
			&&(StringUtils.isBlank(type)||(config.getConfigType()!=null&&type.equals(config.getConfigType().toString())))
			&&config.getConfigKey().toString().equals(key)){
				return config.getConfigValue();
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
	public Map<String, SysConfig> getSysConfigsByType(String type) {
		return getSysConfigsByType(null,type);
	}
	
	/**
	 * 根据类别获取配置
	 * @param appType 应用类别
	 * @param type 类别
	 * @return
	 */
	public Map<String, SysConfig> getSysConfigsByType(Integer appType,String type) {
		if(StringUtils.isBlank(type)){
			return null;
		}
		if(appType==null){
			for(String tmpAppType:appConfig.keySet()){
				Map<String,Map<String, SysConfig>> typeConfig = appConfig.get(tmpAppType);
				Map<String, SysConfig> data = typeConfig.get(type);
				if(data!=null&&data.keySet().size()>0){
					return data;
				}
			}
		}else{
			Map<String,Map<String, SysConfig>> typeConfig = appConfig.get(appType.toString());
			Map<String, SysConfig> data = typeConfig.get(type);
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
		Map<String, SysConfig> data = getSysConfigsByType(appType,type);
		Map<String,String> ret = new HashMap<String,String>();
		for(String key:data.keySet()){
			ret.put(key, data.get(key).getConfigValue());
		}
		return ret;
	}
}
