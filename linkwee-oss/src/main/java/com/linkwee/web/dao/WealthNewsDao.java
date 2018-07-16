package com.linkwee.web.dao;

import java.util.List;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.WealthNews;


 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月24日 18:01:31
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface WealthNewsDao extends BasePageDao<WealthNews>{
	
	public List<WealthNews> getWealthNewsTypes();

}
