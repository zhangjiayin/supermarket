package com.linkwee.tc.fee.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeeConfig {

	
	@Value("${Second_Ratio}")
	private Double second_ratio; //二级理财师佣金比例
	
	@Value("${Third_Ratio}")
	private Double third_ratio; //三级理财师佣金比率
	

	

	public Double getSecond_ratio() {
		return second_ratio;
	}

	public void setSecond_ratio(Double second_ratio) {
		this.second_ratio = second_ratio;
	}

	public Double getThird_ratio() {
		return third_ratio;
	}

	public void setThird_ratio(Double third_ratio) {
		this.third_ratio = third_ratio;
	}
	
	
}
