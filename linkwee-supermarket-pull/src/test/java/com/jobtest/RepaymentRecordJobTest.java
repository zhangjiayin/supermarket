package com.jobtest;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.TestSupport;
import com.linkwee.web.pull.PlatformDataPull;
import com.linkwee.web.push.PlatformDataPush;

public class RepaymentRecordJobTest extends TestSupport{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RepaymentRecordJobTest.class);
	
	@Autowired
	private List<PlatformDataPull> platformDataPulls;
	
	@Autowired
	private PlatformDataPush platformDataPush;

	@Test
	public void platformDataPullTest() {
		for (PlatformDataPull platformDataPull : platformDataPulls) {
			try{
				platformDataPull.pullRepaymentRecord("OPEN_XINYONGBAO_WEB","2018-03-01 00:00:00","2018-03-8 17:15:05");
			}catch(Throwable e){
				LOGGER.error("pullRepaymentRecord Throwable platformDataPull={}",platformDataPull,e);
			}
		}
		
		try{
			//platformDataPush.pushRepaymentRecord();
		}catch(Throwable e){
			LOGGER.error("pushRepaymentRecord Throwable ",e);
		}
	}

}
