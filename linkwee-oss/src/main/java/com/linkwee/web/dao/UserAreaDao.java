package com.linkwee.web.dao;

import java.util.HashMap;
import java.util.List;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.UserArea;


 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2016年03月09日 14:42:02
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface UserAreaDao extends BasePageDao<UserArea>{

	List<HashMap<String, String>> query100CustomerIdOfNotAreaInfoList();
	
	

}
