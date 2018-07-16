package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.impl.AcBankCardInfoServiceImpl;

@Component
public class CalculateCfpLevelDayJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcBankCardInfoServiceImpl.class);
	
	@Resource
	private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		try {
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>开始计算每天理财师职级>>>>>>>>>>>>>>>>>>>>>>>");
			crmCfpLevelRecordTempService.calculateCfpLevel();
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>计算每天理财师职级结束>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			LOGGER.error("每天计算理财师职级异常",e);
		}		
	}
}