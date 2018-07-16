package com.linkwee.web.pull;

/**
 * 平台数据拉取接口
 * @author ch
 *
 */
public interface PlatformDataPull {

	/**
	 * 投资记录拉取	默认查询所有
	 * @param orgNumber  机构编码   为null时查询所有机构
	 * @param startTime  开始时间   为null时为当前时间减1天
	 * @param endTime    结束时间  为null时为当前时间
	 * @throws Throwable
	 */
	void pullInvestRecord(String orgNumber,String startTime,String endTime)throws Throwable;

	/**
	 * 回款记录拉取  
	 * @param orgNumber 机构编码   为null时查询所有机构
	 * @param startTime 开始时间   为null时为当前时间减1天
	 * @param endTime   结束时间   为null时为当前时间
	 * @throws Throwable
	 */
	void pullRepaymentRecord(String orgNumber,String startTime,String endTime)throws Throwable;
}
