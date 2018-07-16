package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.impl.AcBankCardInfoServiceImpl;

@Component
public class CalculateCfpLevelJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcBankCardInfoServiceImpl.class);
	
	@Resource
	private CrmCfpLevelRecordService crmCfpLevelRecordService;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		try {
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>开始计算每月理财师职级>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			crmCfpLevelRecordService.calculateCfpLevel();
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>计算每月理财师职级结束>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			LOGGER.error("每月计算理财师职级异常",e);
		}		
	}
}
