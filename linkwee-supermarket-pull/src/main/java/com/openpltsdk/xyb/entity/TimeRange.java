package com.openpltsdk.xyb.entity;


/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：TimeRange
 * 类描述：时间参数实体
 * @author zenggang
 * @date 创建时间：2017年1月11日 上午11:22:03
 * 修改人：zenggang
 * 修改时间：2017年1月11日 上午11:22:03
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class TimeRange
{
    /**
     * startTime:开始时间
     * @author zenggang
     * @date 创建时间：2017年1月11日 上午9:35:12
     * @version V1.3.1
     */
    private String startTime;
    /**
     * endTime:结束时间
     * @author zenggang
     * @date 创建时间：2017年1月11日 上午9:35:25
     * @version V1.3.1
     */
    private String endTime;
    
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}