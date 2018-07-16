package com.linkwee.web.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.Tproduct;

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月27日 19:07:06
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface TproductDao extends BasePageDao<Tproduct>{
	
	public Integer updateByFid(Tproduct tproduct);
	public Tproduct getByFid(@Param("fid")String fid);
	public int getTproductId();
}
