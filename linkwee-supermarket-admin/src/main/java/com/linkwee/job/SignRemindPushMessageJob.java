package com.linkwee.job;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.xoss.helper.PushMessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignRemindPushMessageJob extends AbstractSimpleElasticJob{

	private static final Logger LOGGER = LoggerFactory.getLogger(SignRemindPushMessageJob.class);

	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {

		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>推送签到提醒start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>推送签到提醒start");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>推送签到提醒start");
		try {
			pushMessageHelper.pushSignRemind();
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>推送签到提醒定时任务异常{}",e);
			e.printStackTrace();
		}

		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>推送签到提醒end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>推送签到提醒end");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>推送签到提醒end");
	}
	
}
