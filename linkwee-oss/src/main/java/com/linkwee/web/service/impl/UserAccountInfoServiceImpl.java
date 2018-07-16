package com.linkwee.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.UserAccountInfoDao;
import com.linkwee.web.model.UserAccountInfo;
import com.linkwee.web.service.UserAccountInfoService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年08月15日 11:01:51
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("userAccountInfoService")
public class UserAccountInfoServiceImpl implements UserAccountInfoService{
	
	@Autowired
	private UserAccountInfoDao userAccountInfoDao;

	@Override
	public Integer addBatch(List<UserAccountInfo> list) {
		return userAccountInfoDao.addBatch(list);
	}
	


}
