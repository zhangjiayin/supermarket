package com.linkwee.web.service;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActSignInfo;
 /**
 * 
 * @描述： ActSignInfoService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月13日 18:49:26
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActSignInfoService extends GenericService<ActSignInfo,Long>{

	/**
	 * 连续签到天数
	 * @param userId
	 * @param appType
	 * @return
	 */
	int consecutiveDays(String userId, Integer appType);

	/**
	 * 更新签到汇总信息
	 * @param signInfo
	 * @param updateType 1:签到 2：其他
	 */
	void updateSignInfo(ActSignInfo signInfo,int updateType);

	/**
	 * 根据用户userId查询奖励金信息
	 * @param userId
	 * @return
	 */
	ActSignInfo queryInfoByUserId(String userId);
}
