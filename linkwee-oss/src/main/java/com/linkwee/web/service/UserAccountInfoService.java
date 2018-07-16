package com.linkwee.web.service;

import java.util.List;

import com.linkwee.web.model.UserAccountInfo;

 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年08月15日 11:01:51
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface UserAccountInfoService{

	Integer addBatch(List<UserAccountInfo> list);
	
}
