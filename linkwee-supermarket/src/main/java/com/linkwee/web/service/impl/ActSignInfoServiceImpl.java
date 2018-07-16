package com.linkwee.web.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.ActSignInfoMapper;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.model.ActSignRecord;
import com.linkwee.web.service.ActSignInfoService;
import com.linkwee.web.service.ActSignRecordService;


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
	@Resource
	private ActSignRecordService signRecordService;
	
	@Override
    public GenericDao<ActSignInfo, Long> getDao() {
        return actSignInfoMapper;
    }

	@Override
	public int consecutiveDays(String userId, Integer appType) {
		ActSignRecord signRecord = signRecordService.queryNewestSign(userId,appType);
		ActSignInfo signInfo = new ActSignInfo();
		signInfo.setUserId(userId);
		signInfo.setUserType(appType);
		signInfo = selectOne(signInfo);		
		if(signInfo != null && signRecord != null){
			int days = DateUtils.countDays(DateUtils.format(signRecord.getSignTime(), DateUtils.FORMAT_SHORT)+" 00:00:00");
			if(days == 1 || days == 0){
				return signInfo.getConsecutiveDays();		
			}else{
				signInfo.setConsecutiveDays(0);
				update(signInfo);
			}			
		}
		return 0;
	}

	@Override
	public void updateSignInfo(ActSignInfo signInfo,int updateType) {
		ActSignRecord signRecord = signRecordService.queryNewestSign(signInfo.getUserId(),signInfo.getUserType());		
		ActSignInfo signInfoTemp = new ActSignInfo();
		signInfoTemp.setUserId(signInfo.getUserId());
		signInfoTemp.setUserType(signInfo.getUserType());
		signInfoTemp = selectOne(signInfoTemp);
		if(signInfoTemp == null){
			if(updateType == 1){//签到 更新连续签到天数
				signInfo.setConsecutiveDays(1);
			}else{
				signInfo.setConsecutiveDays(0);
			}		
			insert(signInfo);
		}else {
			if(signRecord != null ){
				if(updateType == 1){
					int days = DateUtils.countDays(DateUtils.format(signRecord.getSignTime(), DateUtils.FORMAT_SHORT)+" 00:00:00");
					if(days == 1){
						signInfoTemp.setConsecutiveDays(signInfoTemp.getConsecutiveDays()+1);
					}else if(days != 0){
						signInfoTemp.setConsecutiveDays(1);
					}
				}	
			}else{
				signInfoTemp.setConsecutiveDays(0);
			}
			
			if(signInfo.getSignAmount() != null){
				signInfoTemp.setSignAmount(signInfoTemp.getSignAmount().add(signInfo.getSignAmount()));
			}
			if(signInfo.getRedpacketCount() != null){
				signInfoTemp.setRedpacketCount(signInfoTemp.getRedpacketCount()+signInfo.getRedpacketCount());
			}
			if(signInfo.getTransferAmount() != null){
				if(signInfoTemp.getTransferAmount().add(signInfo.getTransferAmount()).compareTo(signInfoTemp.getSignAmount()) > 0){
					throw new RuntimeException("奖励金转出金额大于可转金额");
				}else {
					signInfoTemp.setTransferAmount(signInfoTemp.getTransferAmount().add(signInfo.getTransferAmount()));
				}		
			}
			signInfoTemp.setUpdateTime(new Date());
			update(signInfoTemp);
		}
	}

	@Override
	public ActSignInfo queryInfoByUserId(String userId) {
		ActSignInfo signInfo = new ActSignInfo();
		signInfo.setUserId(userId);
		signInfo = selectOne(signInfo);
		return signInfo;
	}
	
}
