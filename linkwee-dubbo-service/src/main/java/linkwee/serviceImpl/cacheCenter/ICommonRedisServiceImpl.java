package linkwee.serviceImpl.cacheCenter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import linkwee.config.CacheCenterConfig;
import linkwee.helper.CommonHelper;
import cn.xn.cache.service.ICommonRedisService;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version="1.0")
public class ICommonRedisServiceImpl implements ICommonRedisService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private CacheCenterConfig cacheCenterConfig;
	
	@Override
	public void set(String bid, String key, String value, int expire)throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("bid", bid);
		parameterMap.put("key", key);
		parameterMap.put("value", value);
		parameterMap.put("expire", expire);
		commonHelper.cacheCenterHttpRequest(cacheCenterConfig.getSet(),null,parameterMap);
	}

	@Override
	public void del(String bid, String key) throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("bid", bid);
		parameterMap.put("key", key);
		commonHelper.cacheCenterHttpRequest(cacheCenterConfig.getDel(),null,parameterMap);
	}

	@Override
	public String get(String bid, String key) throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("bid", bid);
		parameterMap.put("key", key);
		return commonHelper.cacheCenterHttpRequest(cacheCenterConfig.getGet(),String.class,parameterMap);
	}

	public String testIndex() throws Exception {
		return commonHelper.cacheCenterHttpRequest(cacheCenterConfig.getTestIndex(),null);
	}
}
