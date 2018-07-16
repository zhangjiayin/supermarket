package com.linkwee.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.act.rankList.service.RankListService;
@Component
public class RankListJob extends AbstractSimpleElasticJob{
	
	@Autowired
	private RankListService rankListService;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		//rankListService.updateRanklistVirtualData();
	}

}
