package com.jobtest;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.TestSupport;
import com.linkwee.web.pull.PlatformDataPull;
import com.linkwee.web.push.PlatformDataPush;

public class InvestRecordPullJobTest extends TestSupport{

	private static final Logger LOGGER = LoggerFactory.getLogger(RepaymentRecordJobTest.class);
	
	@Autowired
	private List<PlatformDataPull> platformDataPulls;
	
	@Autowired
	private PlatformDataPush platformDataPush;

	@Test
	public void pullInvestRecordTest() {
		for (PlatformDataPull platformDataPull : platformDataPulls) {
			try{
				platformDataPull.pullInvestRecord("OPEN_LEXIANGBAO_WEB","2017-11-30 09:52:05","2018-01-09 18:32:05");
			}catch(Throwable e){
				LOGGER.error("pullInvestRecord Throwable platformDataPull={}",platformDataPull,e);
			}
		}
		try{
			//platformDataPush.pushInvestRecord();
		}catch(Throwable e){
			LOGGER.error("pushInvestRecord Throwable ",e);
		}
	}

}
