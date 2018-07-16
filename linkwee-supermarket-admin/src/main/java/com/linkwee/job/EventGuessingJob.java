package com.linkwee.job;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.ActActivityEventGuessingService;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.impl.AcBankCardInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EventGuessingJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventGuessingJob.class);
	
	@Resource
	private ActActivityEventGuessingService eventGuessingService;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		try {
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>开始更新赛事竞猜投票数>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			eventGuessingService.updateEventGuessingVotes();
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>更新赛事竞猜投票数结束>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			LOGGER.error("更新赛事竞猜投票数异常",e);
		}		
	}
}
