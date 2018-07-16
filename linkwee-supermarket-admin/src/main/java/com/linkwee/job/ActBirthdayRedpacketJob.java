package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.act.redpacket.service.ActRedpacketService;
/**
 * 生日红包定时任务
 * */
@Component
public class ActBirthdayRedpacketJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActBirthdayRedpacketJob.class);
	
	@Resource
	private ActRedpacketService actRedpacketService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日红包定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日红包定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日红包定时任务start");
	    try {
	    	actRedpacketService.synActBirthdayRedpacketJob();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>生日红包定时任务异常{}",e);
			e.printStackTrace();
		}
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日红包定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日红包定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日红包定时任务end");
	}
	
}
