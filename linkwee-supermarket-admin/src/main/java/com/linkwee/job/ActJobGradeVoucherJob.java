package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.ActJobGradeVoucherService;

@Component
public class ActJobGradeVoucherJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActJobGradeVoucherJob.class);
	
	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步职级体验券start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步职级体验券start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步职级体验券start");
	    try {
	    	actJobGradeVoucherService.synActJobGradeVoucher();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>同步职级体验券定时任务异常{}",e);
			e.printStackTrace();
		}
	    
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步职级体验券end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步职级体验券end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>同步职级体验券end");
	}
	
}
