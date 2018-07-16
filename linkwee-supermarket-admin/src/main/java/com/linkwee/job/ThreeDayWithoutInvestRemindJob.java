package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CimProductUnrecordInvestService;
/**
 * 注册三天内没有投资消息提醒
 * */
@Component
public class ThreeDayWithoutInvestRemindJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThreeDayWithoutInvestRemindJob.class);
	
	@Resource
	private AcAccountBindService acAccountBindService;
	@Resource
	private CimProductUnrecordInvestService cimProductUnrecordInvestService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>注册三天内没有投资提醒定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>注册三天内没有投资提醒定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>注册三天内没有投资提醒定时任务start");
	    try {
	    	acAccountBindService.threeDayWithoutInvestRemind();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>注册三天内没有投资提醒定时任务异常{}",e);
			e.printStackTrace();
		}
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>注册三天内没有投资提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>注册三天内没有投资提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>注册三天内没有投资提醒定时任务end");
		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生日提醒定时任务end");
		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>投资返现回款提醒定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>投资返现回款提醒定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>投资返现回款提醒定时任务start");
	    try {
	    	cimProductUnrecordInvestService.sendMessage();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>投资返现回款提醒定时任务异常{}",e);
			e.printStackTrace();
		}
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>投资返现回款提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>投资返现回款提醒定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>投资返现回款提醒定时任务end");
	}
	
}
