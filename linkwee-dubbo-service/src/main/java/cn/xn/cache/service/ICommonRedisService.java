package cn.xn.cache.service;


public interface ICommonRedisService {
	
	/**
	 * 
	 * @param bid 	业务ID
	 * @param key 	缓存的key值
	 * @param value 缓存的值
	 * @param expire 过期时间，单位为分钟
	 * @throws Exception
	 */
	public void set(String bid, String key, String value, int expire) throws Exception;
	
	/**
	 * 
	 * @param bid	业务ID
	 * @param key	缓存的key值
	 * @throws Exception
	 */
	public void del(String bid, String key) throws Exception;
	
	/**
	 * 
	 * @param bid	业务ID
	 * @param key	缓存的key值
	 * @return
	 * @throws Exception
	 */
	public String get(String bid, String key) throws Exception;
	
}
