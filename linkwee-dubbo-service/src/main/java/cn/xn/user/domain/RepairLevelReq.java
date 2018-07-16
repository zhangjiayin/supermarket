package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class RepairLevelReq extends BaseReq{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 528227070651137241L;
	
	@NotNullField
	private int level = 0; // 需要修复的等级，只修复等级为0
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
