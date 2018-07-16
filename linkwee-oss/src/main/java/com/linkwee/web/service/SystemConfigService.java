package com.linkwee.web.service;

import com.linkwee.web.model.SystemConfig;

import java.util.List;

/**
 * 
 * @描述：系统配置接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年07月30日 17:04:42
 * 
 *                   Copyright (c) 深圳市小牛科技有限公司-版权所有
 */

public interface SystemConfigService {

	/**
	 * 查询所有的配置
	 * 
	 * @return
	 */
	public List<SystemConfig> getSystemConfigs();

	public List<SystemConfig> querySystemConfigsByConditon(String string);

	public String getValuesByKey(String key);

	public String getImageUrl(String imgpath);

	public String getValuesByKey(String key, Integer appType);

}
