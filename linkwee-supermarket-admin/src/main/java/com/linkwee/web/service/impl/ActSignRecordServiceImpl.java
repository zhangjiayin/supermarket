package com.linkwee.web.service.impl;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.ActSignRecordMapper;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.model.ActSignRecord;
import com.linkwee.web.service.ActSignInfoService;
import com.linkwee.web.service.ActSignRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


/**
*
* @描述：ActSignRecordService 服务实现类
*
* @创建人： Mignet
*
* @创建时间：2017年11月13日 16:59:18
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("actSignRecordService")
public class ActSignRecordServiceImpl extends GenericServiceImpl<ActSignRecord, Long> implements ActSignRecordService{

   private static final Logger LOGGER = LoggerFactory.getLogger(ActSignRecordServiceImpl.class);

   @Resource
   private ActSignRecordMapper actSignRecordMapper;
   @Resource
   private ActSignInfoService signInfoService;

   @Override
   public GenericDao<ActSignRecord, Long> getDao() {
       return actSignRecordMapper;
   }

   @Override
   public void sendBounty(String userId, Integer userType, BigDecimal amount,int bountyType) {
       ActSignRecord signRecord = new ActSignRecord();
       signRecord.setBountyType(bountyType);
       signRecord.setSignAmount(amount);
       signRecord.setUserId(userId);
       signRecord.setUserType(userType);
       ActSignInfo signInfo = new ActSignInfo();
       signInfo.setUserId(userId);
       signInfo.setUserType(userType);
       signInfo.setSignAmount(amount);
       signInfoService.updateSignInfo(signInfo,2);
       insert(signRecord);
   }

    @Override
    public boolean hasSignedToday(String userId, Integer appType) {
        ActSignRecord signRecord = new ActSignRecord();
        signRecord.setUserId(userId);
        signRecord.setUserType(appType);
        signRecord.setSignTime(new Date());
        signRecord = actSignRecordMapper.todaySign(signRecord);
        boolean result = false;
        if(signRecord != null){
            result = true;
        }
        return result;
    }


}
