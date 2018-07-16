package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.AcAccountBindService;
/**
 * 生日短信提醒定时任务
 * */
@Component
public class ActBirthdayReminderJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActBirthdayReminderJob.class);
	
	@Resource
	private AcAccountBindService acAccountBindService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务start");
	    try {
	    	acAccountBindService.synActBirthdayReminder();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>生日提醒定时任务异常{}",e);
			e.printStackTrace();
		}
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务end");
	}
	
}
