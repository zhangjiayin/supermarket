package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.IfastFundService;

@Component
public class UpdateIfastFundsGetOrderListJob   extends AbstractSimpleElasticJob{

	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateIfastFundsGetOrderListJob.class);
	
	@Resource
	private IfastFundService ifastFundService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		// TODO Auto-generated method stub
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>拉取基金订单列表状态更新start");
//	    try {
//	    	ifastFundService.updateOrderList();
//		} catch (Exception e) {
//			LOGGER.error(">>>>>>>>>>>>>>拉取基金订单列表状态更新异常{}",e);
//			e.printStackTrace();
//		}		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>拉取基金订单列表状态更新end");
	}

}
