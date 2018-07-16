package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class UpdateScanFlagReq extends BaseReq {

	private static final long serialVersionUID = -6078038213683153569L;

	/**
	 * 用户id
	 */
	@NotNullField
	private String memberNo;
	
	/**
	 * 人脸识别标识，1/人脸识别成功,2/人脸识别失败
	 */
	@NotNullField
	private String scanFlag;
	
	/**
	 * 人脸识别时间，请以yyyyMMddHHmmss格式传入
	 */
	private String scanDate;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getScanFlag() {
		return scanFlag;
	}

	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
