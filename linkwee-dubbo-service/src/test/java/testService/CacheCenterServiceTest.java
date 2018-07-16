package testService;

import linkwee.serviceImpl.cacheCenter.ICommonRedisServiceImpl;

import org.junit.Test;

import test.BaseTest;
import cn.xn.cache.service.ICommonRedisService;

import com.alibaba.fastjson.JSONObject;

public class CacheCenterServiceTest extends BaseTest{

	@Test
	public void getValuesByType() throws Exception{
		ICommonRedisService iCommonRedisService = this.getService("ICommonRedisServiceImpl", ICommonRedisService.class);
		iCommonRedisService.set("CHANNEL", "liqimoon", "水木灵云", 1);
		logger.info(JSONObject.toJSONString(iCommonRedisService.get("CHANNEL", "liqimoon")));
	}
	
	@Test
	public void getValuesByType2() throws Exception{
		ICommonRedisServiceImpl iCommonRedisServiceImpl = this.getService("ICommonRedisServiceImpl", ICommonRedisServiceImpl.class);
		logger.info(JSONObject.toJSONString(iCommonRedisServiceImpl.testIndex()));
	}
}
