package com.linkwee.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.service.IfastFundService;

@Component
public class IfastFundsGetOrderListJob  extends AbstractSimpleElasticJob{

	private static final Logger LOGGER = LoggerFactory.getLogger(IfastFundsGetOrderListJob.class);
	
	@Resource
	private IfastFundService ifastFundService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>基金订单列表拉取start");
//	    try {
//	    	GetOrderListRequest getOrderListRequest =  new GetOrderListRequest();
//	    	
//	    	//查询时间间隔一天
//	    	Date now = new Date();
//	    	getOrderListRequest.setOrderDateStart(DateUtils.format(DateUtils.subDay(now, 1)));
//	    	getOrderListRequest.setOrderDateEnd(DateUtils.format(now));
//	    	
//	    	ifastFundService.pullOrderList(getOrderListRequest);
//		} catch (Exception e) {
//			LOGGER.error(">>>>>>>>>>>>>>基金订单列表拉取定时任务异常{}",e);
//			e.printStackTrace();
//		}		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>基金订单列表拉取end");
	}

}
