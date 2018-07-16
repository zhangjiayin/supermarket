package com.linkwee.activity.model;

import java.util.Date;

import com.linkwee.web.request.RedPacketInfoRequest;

public class UseRuleContext {
	
	private RedPacketInfoRequest req;
	private Activity activity;
	private RedpacketType redpacketType;
	private Date date;
	
	public UseRuleContext() {}
	
	
	public UseRuleContext(RedPacketInfoRequest req,Activity activity,RedpacketType redpacketType,Date date) {
		this.req=req;
		this.activity=activity;
		this.redpacketType=redpacketType;
		this.date=date;
	}


	public RedPacketInfoRequest getReq() {
		return req;
	}


	public Activity getActivity() {
		return activity;
	}


	public RedpacketType getRedpacketType() {
		return redpacketType;
	}


	public Date getDate() {
		return date;
	}
	
	
}
