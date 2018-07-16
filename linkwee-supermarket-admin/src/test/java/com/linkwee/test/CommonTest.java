package com.linkwee.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.web.service.CimOrgFeeRecordLogService;

public class CommonTest extends TestSupport {
    
	@Resource
	private CimOrgFeeRecordLogService cimOrgFeeRecordLogService;

	@Test
	public void test(){
		start();
		cimOrgFeeRecordLogService.insertLog("OPEN_HEXINDAI_WEB", "aaa", "sssss");;
		end();
	}
}
