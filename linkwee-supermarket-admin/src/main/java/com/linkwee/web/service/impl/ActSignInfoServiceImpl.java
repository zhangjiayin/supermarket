package com.linkwee.web.service.impl;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.ActSignInfoMapper;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.service.ActSignInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
*
* @描述：ActSignInfoService 服务实现类
*
* @创建人： Mignet
*
* @创建时间：2017年11月13日 18:49:26
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("actSignInfoService")
public class ActSignInfoServiceImpl extends GenericServiceImpl<ActSignInfo, Long> implements ActSignInfoService{

   private static final Logger LOGGER = LoggerFactory.getLogger(ActSignInfoServiceImpl.class);

   @Resource
   private ActSignInfoMapper actSignInfoMapper;

   @Override
   public GenericDao<ActSignInfo, Long> getDao() {
       return actSignInfoMapper;
   }

   @Override
   public void updateSignInfo(ActSignInfo signInfo,int updateType) {
       ActSignInfo signInfoTemp = new ActSignInfo();
       signInfoTemp.setUserId(signInfo.getUserId());
       signInfoTemp.setUserType(signInfo.getUserType());
       signInfoTemp = selectOne(signInfoTemp);
       if(signInfoTemp == null){
           signInfo.setConsecutiveDays(0);
           insert(signInfo);
       }else{
           if(signInfo.getSignAmount() != null){
               signInfoTemp.setSignAmount(signInfoTemp.getSignAmount().add(signInfo.getSignAmount()));
           }
           signInfoTemp.setUpdateTime(new Date());
           update(signInfoTemp);
       }
   }

}
