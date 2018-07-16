package com.linkwee.web.service;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActSignRecord;

import java.math.BigDecimal;

/**
*
* @描述： ActSignRecordService服务接口
*
* @创建人： Mignet
*
* @创建时间：2017年11月13日 16:59:18
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface ActSignRecordService extends GenericService<ActSignRecord,Long>{

   /**
    * 发放奖励金
    * @param userId
    * @param userType
    * @param amount
    * @param bountyType
    */
   void sendBounty(String userId, Integer userType, BigDecimal amount, int bountyType);

   /**
    * 今日是否已签到
    * @param userId
    * @param appType
    * @return
    */
   boolean hasSignedToday(String userId, Integer appType);

}
