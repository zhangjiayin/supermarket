package cn.xn.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CustomerInfoRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3928350223301860801L;

	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 登录密码
	 */
	private String loginPwd;
	/**
	 * 支付密码
	 */
	private String payPwd;
	/**
	 * 二维码code
	 */
	private String qrCode;
	/**
	 * 用户编码
	 */
	private String memberNo;
	/**
	 * 用户名称
	 */
	private String memberName;
	/**
	 * 用户手机号
	 */
	private String mobile;
	/**
	 * 证件类型
	 */
	private String certType;
	/**
	 * 证件号
	 */
	private String certNo;
	/**
	 * 实名认证标识
	 */
	private String nameAuthFlag;
	/**
	 * 头像URL
	 */
	private String headUrl;
	/**
	 * 推荐人手机号
	 */
	private String refereeNo;
	/**
	 * 会员级别
	 */
	private int memberLevel;
	
	/**
	 * '第一次升级提示标识，1为非第一次升级，0为第一次升级
	 */
	private boolean memberLevelFlag; 
	/**
	 * 注册时间
	 */
	private Date createDate;
	
	/**
	 * 用户来源渠道：微信、腾讯、新浪、百度, 如果没有获取到，则为""
	 */
	private String channel;	
	/**
	 * 搜索关键字，如没有获取到，则为""
	 */
	private String keyword;
	
	private String faceFlag;
	

	public boolean isMemberLevelFlag() {
		return memberLevelFlag;
	}

	public void setMemberLevelFlag(boolean memberLevelFlag) {
		this.memberLevelFlag = memberLevelFlag;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getNameAuthFlag() {
		return nameAuthFlag;
	}

	public void setNameAuthFlag(String nameAuthFlag) {
		this.nameAuthFlag = nameAuthFlag;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getRefereeNo() {
		return refereeNo;
	}

	public void setRefereeNo(String refereeNo) {
		this.refereeNo = refereeNo;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFaceFlag() {
		return faceFlag;
	}

	public void setFaceFlag(String faceFlag) {
		this.faceFlag = faceFlag;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
