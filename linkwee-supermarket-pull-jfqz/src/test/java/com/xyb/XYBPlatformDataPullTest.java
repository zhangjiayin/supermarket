package com.xyb;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.TestSupport;
import com.linkwee.web.pull.PlatformDataPull;
import com.linkwee.web.push.PlatformDataPush;

public class XYBPlatformDataPullTest extends TestSupport{
	
	@Resource(name = "xybPlatformDataPull")
	private PlatformDataPull xybPlatformDataPull;
	
	@Autowired
	private PlatformDataPush platformDataPush;
	
	@Test
	public void pullInvestRecordTest() throws Throwable{
		xybPlatformDataPull.pullInvestRecord(null,null,null);
	}
	@Test
	public void pullRepaymentRecordTest() throws Throwable{
		xybPlatformDataPull.pullRepaymentRecord(null,null,null);
	}
	
	@Test
	public void pushInvestRecordTest() throws Throwable{
		platformDataPush.pushInvestRecord();
	}
	
	@Test
	public void pushRepaymentRecordTest() throws Throwable{
		platformDataPush.pushRepaymentRecord();
	}

}
