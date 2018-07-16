package com.jobtest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.TestSupport;
import com.linkwee.web.pull.ProductDataPull;
import com.linkwee.web.push.ProductDataPush;

public class PullProductSimpleJobTest extends TestSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(PullProductSimpleJobTest.class);
	
	@Resource
	private List<ProductDataPull> ProductDatePullList;
	
	@Resource
	private ProductDataPush productDataPush;

	@Test
	public void productDatePullTest(){

		for (ProductDataPull productDatePull : ProductDatePullList) {
			try{
				productDatePull.pullProductCurrentSale(null,null,null);
			}catch(Throwable e){
				LOGGER.error("拉取产品定时任务异常",e);
				continue;
			}
		}
		
		try {
			//productDataPush.pushProductData();
		} catch (Exception e) {
			LOGGER.error("推送产品定时任务异常 ",e);
		}
	}
}
