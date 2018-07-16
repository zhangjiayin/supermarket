package com.linkwee.activity.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.activity.model.RedpacketAccount;
import com.linkwee.core.generic.GenericDao;

public interface RedpacketAccountMapper extends GenericDao<RedpacketAccount, Long>{
	
	/**
	 * 查询单个活动已派发金额 
	 * @param activityId
	 * @return
	 */
	public RedpacketAccount getSingleSendMoney(String activityId);
	/**
	 * 更新已派发金额 
	 * @param redeemMoney
	 * @param fid
	 */
	public void updateSendMoney(@Param("sendMoney")Double sendMoney,@Param("activityId")String activityId);

}
