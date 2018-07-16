package com.linkwee.web.enums;


/**
 * 
 * @描述：月饼节活动任务
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum MidAutumnTaskEnum 
{
	FUND_REGISTER("FUND_REGISTER","月饼节活动任务注册基金"),
	INVITE_CFPLANNER("INVITE_CFPLANNER","月饼节活动任务邀请理财师"),
	INVEST_STATUS("INVEST_STATUS","月饼节活动任务出单状态");
	
	MidAutumnTaskEnum(String code,String message){
		this.code = code;
		this.message = message;
	}

	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
