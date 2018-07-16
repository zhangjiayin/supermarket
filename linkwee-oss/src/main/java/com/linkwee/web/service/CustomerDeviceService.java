package com.linkwee.web.service;

import com.linkwee.web.model.CustomerDevice;

/**
 * 用户设备信息服务
 * 
 * @author xuzhao
 * @Date 2016年3月10日 下午5:14:30
 */
public interface CustomerDeviceService {

	/**
	 * 记录用户设备信息
	 * @Auther xuzhao
	 * @Date 2016年3月11日 上午11:01:00
	 * @param customerDevice
	 */
	void doDeviceInfo(CustomerDevice customerDevice);

	/**
	 * 查询用户设备信息
	 * @Auther xuzhao
	 * @Date 2016年3月11日 下午1:43:16
	 * @param appType
	 * @param userId
	 * @return
	 */
	CustomerDevice queryCustomerDevice(int appType, String userId);

	/**
	 * 删除设备信息
	 * @Auther xuzhao
	 * @Date 2016年3月25日 下午2:22:54
	 * @param customerDevice
	 */
	void delete(CustomerDevice customerDevice);

}
