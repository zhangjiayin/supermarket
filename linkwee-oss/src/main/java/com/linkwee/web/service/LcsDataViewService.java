package com.linkwee.web.service;

import java.util.Map;

public interface LcsDataViewService {
	
	/**
	 * 查询数据统计信息
	 * @return
	 */
	public Map<String, Object> getLcsDateStaticCount();
	
	/**
	 * 根据日期查询lcs数据统计
	 * @param start
	 * @param end
	 * @return
	 */
	public Map<String, Object> getLcsDataStatic(Map<String, Object> map);

}
