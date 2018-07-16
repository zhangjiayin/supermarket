package com.linkwee.web.dao;

import java.util.List;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.CfgLevel;


 /**
 * 
 * @描述：理财师等级 Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月08日 17:27:15
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface CfgLevelDao extends BasePageDao<CfgLevel>{
	
	
	/**
	 * 获取下一级理财师等级
	 * @param currLevelId 理财师id
	 * @return 
	 */
	public CfgLevel queryNextLevel(Integer currLevelId);
	
	/**
	 * 获取目标佣金大于当前季度佣金并且等级大于当前理财师等级的理财师
	 * @param currLevelId 理财师id
	 * @param quarterFee 季度佣金
	 * @return 
	 */
	public CfgLevel queryNextLevelByFee(Integer currLevelId,double quarterFee);

	/**
	 * 查询所有理财师等级
	 * @return
	 */
	public List<CfgLevel> queryAllCfgLevel();
}
