package com.linkwee.job;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.pull.ProductDataPull;
import com.linkwee.web.push.ProductDataPush;

@Component
public class PullProductSimpleJob extends AbstractSimpleElasticJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(PullProductSimpleJob.class);
	
	@Resource
	private List<ProductDataPull> ProductDatePullList;
	
	@Resource
	private ProductDataPush productDataPush;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {

		for (ProductDataPull productDatePull : ProductDatePullList) {
			try{
				productDatePull.pullProductCurrentSale(null,null,null);
			}catch(Throwable e){
				LOGGER.error("拉取产品定时任务异常",e);
				continue;
			}
		}
		
		try {
			productDataPush.pushProductData();
		} catch (Exception e) {
			LOGGER.error("推送产品定时任务异常 ",e);
		}
	}
}
