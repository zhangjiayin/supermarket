package cn.xn.cache.service;

import cn.xn.cache.domain.Param;

public interface IUserRedisService {

	public void set(Param p) throws Exception;

	public void del(Param p) throws Exception;

	public void deleteAllTokensByKey(Param p) throws Exception;
	
	public boolean get(Param p, int expire) throws Exception;
	
	public void delayToken(Param p) throws Exception;

}
