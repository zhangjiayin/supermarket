package com.linkwee.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.CustomerDeviceDao;
import com.linkwee.web.model.CustomerDevice;
import com.linkwee.web.service.CustomerDeviceService;
/**
 * 用户设备信息
 * 
 * @author xuzhao
 * @Date 2016年3月10日 下午5:15:40
 */
@Service("customerDeviceService")
public class CustomerDeviceServiceImpl implements CustomerDeviceService {
	@Autowired
	private CustomerDeviceDao customerDeviceDao;

	/**
	 * 保存用户登录设备信息
	 */
	@Override
	public void doDeviceInfo(CustomerDevice customerDevice) {
		int num = customerDeviceDao.updateCustomerDevice(customerDevice);
		if (num == 0) {
			customerDeviceDao.add(customerDevice);
		}
	}

	/**
	 * 查询用户设备信息
	 */
	@Override
	public CustomerDevice queryCustomerDevice(int appType, String userId) {
		CustomerDevice customerDevice = new CustomerDevice();
		customerDevice.setAppType(appType);
		customerDevice.setMemberNo(userId);
		return customerDeviceDao.query(customerDevice);
	}

	@Override
	public void delete(CustomerDevice customerDevice) {
		customerDeviceDao.delete(customerDevice);
		
	}

}
