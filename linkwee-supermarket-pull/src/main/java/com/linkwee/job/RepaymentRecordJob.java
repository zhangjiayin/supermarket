package com.linkwee.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.pull.PlatformDataPull;
import com.linkwee.web.push.PlatformDataPush;

@Component
public class RepaymentRecordJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RepaymentRecordJob.class);
	
	@Autowired
	private List<PlatformDataPull> platformDataPulls;
	
	@Autowired
	private PlatformDataPush platformDataPush;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		for (PlatformDataPull platformDataPull : platformDataPulls) {
			try{
				platformDataPull.pullRepaymentRecord(null,null,null);
			}catch(Throwable e){
				LOGGER.error("pullRepaymentRecord Throwable platformDataPull={}",platformDataPull,e);
			}
		}
		
		try{
			platformDataPush.pushRepaymentRecord();
		}catch(Throwable e){
			LOGGER.error("pushRepaymentRecord Throwable ",e);
		}
	}

}
