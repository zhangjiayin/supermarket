package com.linkwee.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.helper.PushMessageHelper;

@Component
public class AdvancePaymentReminderJob extends AbstractSimpleElasticJob{
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	@Resource
	private SysMsgService sysMsgService;
		
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		pushMessageHelper.advancePaymentReminder();
	}
	
}
