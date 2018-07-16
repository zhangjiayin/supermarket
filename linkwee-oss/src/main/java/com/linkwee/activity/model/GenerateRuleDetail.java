package com.linkwee.activity.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GenerateRuleDetail {
	
	private Double min;//最小金额
	private Double max;//最大金额
	private List<RedpacketRule> redPaperType;//红包列表
	
	public GenerateRuleDetail() {}

	
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	

	public List<RedpacketRule> getRedPaperType() {
		return redPaperType;
	}

	public void setRedPaperType(List<RedpacketRule> redPaperType) {
		this.redPaperType = redPaperType;
	}


	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
