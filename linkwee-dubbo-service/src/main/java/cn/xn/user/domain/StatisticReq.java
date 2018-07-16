package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class StatisticReq extends BaseReq{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 528227070651137241L;
	
	@NotNullField
	private int expireMinute = 10; // 数据默认过期时间（分钟）

	public int getExpireMinute() {
		return expireMinute;
	}

	public void setExpireMinute(int expireMinute) {
		this.expireMinute = expireMinute;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
