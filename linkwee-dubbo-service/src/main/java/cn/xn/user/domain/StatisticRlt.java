package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StatisticRlt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7856427299294174148L;
	
	private long registerCount;//注册人数

	public long getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(long registerCount) {
		this.registerCount = registerCount;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
