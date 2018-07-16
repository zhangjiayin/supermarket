package com.linkwee.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;

public class CrmCfpLevelTest extends TestSupport {

	@Resource
	private CrmCfpLevelRecordService crmCfpLevelRecordService;
	@Resource
	private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
	
	@Test
	public void calculateYearpurAmountTest(){
		start();
		crmCfpLevelRecordService.calculateCfpLevel();
		end();
	}
	
	@Test
	public void calculateYearpurAmountTempTest(){
		start();
		crmCfpLevelRecordTempService.calculateCfpLevel();
		end();
	}
	
	@Test
	public void sendCfpLevelMsg() throws InterruptedException{
		start();
		CrmCfpLevelRecord crmCfpLevelRecord = new CrmCfpLevelRecord();
		crmCfpLevelRecord.setUserId("822b71784d6f497cb891626fac538a14");
		crmCfpLevelRecord.setMonth(201704);
		crmCfpLevelRecord.setYearpurAmount(new BigDecimal(0));
		crmCfpLevelRecord.setCurLevel(CfpJobGradeEnum.SM2.getValue());//当前职级预归为TA  后期再进行计算
		crmCfpLevelRecord.setCurLevelWeight(10);//对于已经确定为TA的  直接写写死其权重
		crmCfpLevelRecord.setPreLevel(CfpJobGradeEnum.TA.getValue());
		crmCfpLevelRecord.setStatus(1);
		crmCfpLevelRecord.setCreateTime(new Date());
		crmCfpLevelRecordService.sendCfpLevelMsg(crmCfpLevelRecord);
		end();
		Thread.sleep(21000000000l);
	}
}
