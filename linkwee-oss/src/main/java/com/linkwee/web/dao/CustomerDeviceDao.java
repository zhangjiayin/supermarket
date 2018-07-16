package com.linkwee.web.dao;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.CustomerDevice;

public interface CustomerDeviceDao extends BasePageDao<CustomerDevice>{

	int updateCustomerDevice(CustomerDevice customerDevice);

	void delete(CustomerDevice customerDevice);

}
