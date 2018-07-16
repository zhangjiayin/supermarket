package com.linkwee.activity.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GenerateRuleContext {
	
	private Activity activity;
	private RedpacketType redpacketType;
	private Date validDate;
	private Date date;
	private Integer rtype;
	
	public GenerateRuleContext() {}
	
	
	public GenerateRuleContext(Activity activity,RedpacketType redpacketType,Integer rtype, Date validDate,Date date) {
		this.activity=activity;
		this.redpacketType=redpacketType;
		this.rtype=rtype;
		this.validDate=validDate;
		this.date=date;
	}

	public Activity getActivity() {
		return activity;
	}

	public RedpacketType getRedpacketType() {
		return redpacketType;
	}
	
	

	public Integer getRtype() {
		return rtype;
	}


	public Date getValidDate() {
		return validDate;
	}

	public Date getDate() {
		return date;
	}



	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
