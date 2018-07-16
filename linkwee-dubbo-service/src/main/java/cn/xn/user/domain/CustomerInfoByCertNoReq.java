package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

/**
 * 根据证件号码（身份证）获取用户信息
 * @author 颜彩云
 *
 */
public class CustomerInfoByCertNoReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -795341733730815823L;

	/**
	 * 证件号码
	 */
	@NotNullField
	private String certNo;

	
	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
