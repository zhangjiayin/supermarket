package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.SysConfigService;
/**
 * 劳动节热爱劳动人数
 * */
@Component
public class LaborDayLoveWorkNumJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LaborDayLoveWorkNumJob.class);
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>劳动节热爱劳动人数定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>劳动节热爱劳动人数定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>劳动节热爱劳动人数定时任务start");
	    try {
	    	sysConfigService.updateLoveWorkNum();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>注册三天内没有投资提醒定时任务异常{}",e);
			e.printStackTrace();
		}
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>劳动节热爱劳动人数定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>劳动节热爱劳动人数定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>劳动节热爱劳动人数定时任务end");
	}
	
}
