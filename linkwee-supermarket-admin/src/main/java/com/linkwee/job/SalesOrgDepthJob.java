package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.CrmSalesOrgService;
/**
 * 合伙人团队及等级确认定时任务
 * */
@Component
public class SalesOrgDepthJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrgDepthJob.class);
	
	@Resource
	private CrmSalesOrgService salesOrgService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>合伙人团队及等级确认定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>合伙人团队及等级确认定时任务start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>合伙人团队及等级确认定时任务start");
	    try {
	    	salesOrgService.salesOrgDepthJob();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>合伙人团队及等级确认定时任务异常{}",e);
			e.printStackTrace();
		}
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>合伙人团队及等级确认定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>合伙人团队及等级确认定时任务end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>合伙人团队及等级确认定时任务end");
	}
	
}
