package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @描述： 理财师相关查询
 * 
 * @创建人：ch
 * 
 * @创建时间：2016年04月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface LcsDataViewDao{
	
	/**
	 * 查询理财师总数
	 * @param start
	 * @param end
	 * @return
	 */
	public int getLcsDateStaticTotal(Map<String, Object> map);
	
	/**
	 * 查询理财师数据
	 * @return
	 */
	public Map<String, Object> getLcsDateStaticCount();
	
	/**
	 * 根据日期查询理财师数据
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> getLcsDateStatic(Map<String, Object> map);
	
	
	/**
	 * 查询有效理财师总数
	 * @param start
	 * @param end
	 * @return
	 */
	public int getValidLcsDateStaticTotal(Map<String, Object> map);
	
	/**
	 * 查询有效理财师数据
	 * @return
	 */
	public Map<String, Object> getValidLcsDateStaticCount();
	
	/**
	 * 根据日期查询有效理财师数据
	 * @param start
	 * @param end
	 * @return
	 */
	public  List<Map<String, Object>> getValidLcsDateStatic(Map<String, Object> map);
}
