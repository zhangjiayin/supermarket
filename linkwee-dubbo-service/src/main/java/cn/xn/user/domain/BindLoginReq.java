package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class BindLoginReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7026896635428563507L;

	/**
	 * 绑定的外部系统
	 */
	@NotNullField
	private String sysSource;

	/**
	 * 外部绑定的唯一编码
	 */
	@NotNullField
	private String fUnionid;


	public String getSysSource() {
		return sysSource;
	}

	public void setSysSource(String sysSource) {
		this.sysSource = sysSource;
	}

	public String getfUnionid() {
		return fUnionid;
	}

	public void setfUnionid(String fUnionid) {
		this.fUnionid = fUnionid;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
