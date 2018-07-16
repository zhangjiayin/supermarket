package com.openpltsdk.xyb.entity;

/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：QueryReq
 * 类描述：查询公用类
 * @author zenggang
 * @date 创建时间：2017年1月11日 上午11:02:28
 * 修改人：zenggang
 * 修改时间：2017年1月11日 上午11:02:28
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class QueryReq {
	private TimeRange timeRange;
	
	private Index index;
	
	public TimeRange getDateRange() {
		return timeRange;
	}
	
	public void setDateRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}
	
	public Index getIndex() {
		return index;
	}
	
	public void setIndex(Index index) {
		this.index = index;
	}
	
}
