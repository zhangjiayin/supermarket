package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class GetFriendReq extends BaseReq {

	private static final long serialVersionUID = -6078038213683153569L;

	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 用户id
	 */
	@NotNullField
	private String memberNo;

	/**
	 * 类型，0为获取全部，1为获取已注册，2为获取未注册
	 */
	@NotNullField
	private Integer type;
	/**
	 * 每页显示的数量，默认20
	 */
	@NotNullField
	private Integer pageSize = 20;
	/**
	 * 当前页码，默认1
	 */
	@NotNullField
	private Integer currPage = 1;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
