package com.linkwee.web.service.impl;

import com.linkwee.core.util.StringUtils;
import com.linkwee.web.constant.ConfigurationConstant;
import com.linkwee.web.dao.SystemConfigDao;
import com.linkwee.web.model.SystemConfig;
import com.linkwee.web.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


 /**
 * 
 * @描述： 系统配置服务类
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年07月30日 17:04:42
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService{
	
	@Autowired
	private SystemConfigDao systemConfigDao;
	
	/**
	 * 查询所有的配置
	 * @return
	 */
	public List<SystemConfig> getSystemConfigs(){
		SystemConfig t = new SystemConfig();
		return systemConfigDao.list(t);
	}

	/**
	 * 根据条件查询相应配置
	 * @Auther xuzhao
	 * @Date 2016年1月19日 下午2:06:26
	 * @return
	 */
	public List<SystemConfig> querySystemConfigsByConditon(String type){
		SystemConfig t = new SystemConfig();
		t.setType(type);
		return systemConfigDao.list(t);
	}
	
	public String getValuesByKey(String key,Integer appType) {
		SystemConfig t = new SystemConfig();
		t.setKey(key);
		t.setAppType(appType);
		List<SystemConfig> list = systemConfigDao.list(t);
		if(list!=null &&list.size()>0){
			return list.get(0).getValue();
		}else{
			return "";
		}
	}
	
	public String getValuesByKey(String key) {
		SystemConfig t = new SystemConfig();
		t.setKey(key);
		List<SystemConfig> list = systemConfigDao.list(t);
		if(list!=null &&list.size()>0){
			return list.get(0).getValue();
		}else{
			return "";
		}
	}

	 /**
	  * 获得图片路径
	  * @param imgpath
	  * @return
      */
	 @Override
	 public String getImageUrl(String imgpath) {
		 if(StringUtils.isNotBlank(imgpath)){
			 if(imgpath.indexOf("http") !=-1){
				 return imgpath;
			 }

			 String imgServerUrl = getValuesByKey(ConfigurationConstant.IMAGE_SERVER_URL);
			 if(imgServerUrl.lastIndexOf("/")==-1){
				 imgServerUrl = imgServerUrl+"/";
			 }
			 return imgServerUrl+ imgpath;
		 }
		 return null;
	 }

 }
