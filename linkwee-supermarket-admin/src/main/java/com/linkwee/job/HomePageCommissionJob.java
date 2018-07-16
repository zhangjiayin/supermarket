package com.linkwee.job;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.web.model.SysHomepageCommission;
import com.linkwee.web.service.SysHomepageCommissionService;

@Component
public class HomePageCommissionJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageCommissionJob.class);
	
	@Resource
	private SysHomepageCommissionService sysHomepageCommissionService;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>首页累计发放佣金start");
	    try {
	    	SysHomepageCommission sysHomepageCommission = sysHomepageCommissionService.selectNewest();
	    	if(sysHomepageCommission == null){
	    		SysHomepageCommission temp = new SysHomepageCommission();
	    		temp.setAmount(new BigDecimal("20328567.13"));
	    		temp.setCreateTime(new Date());
	    		sysHomepageCommissionService.insert(temp);
	    	}else{
	    		SysHomepageCommission temp = new SysHomepageCommission();
	    		BigDecimal amount = sysHomepageCommission.getAmount();
	    		BigDecimal amountResult = amount.add(new BigDecimal((2000000 + Math.round(Math.random() * 1000000))/100.00+""));
	    		temp.setAmount(amountResult);
	    		temp.setCreateTime(new Date());
	    		sysHomepageCommissionService.insert(temp);
	    	}
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>首页累计发放佣金定时任务异常{}",e);
			e.printStackTrace();
		}		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>首页累计发放佣金end");
	}
	
}
