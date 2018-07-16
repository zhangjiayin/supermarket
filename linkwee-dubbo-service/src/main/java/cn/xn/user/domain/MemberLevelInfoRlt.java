package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MemberLevelInfoRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5727800409507282799L;

	/**
	 * 用户编号
	 */
	private String memberNo;

	/**
	 * 用户以前会员等级
	 */
	private String loginName;

	/**
	 * 用户会员等级0,1,2,3,4,5 ....
	 */
	private int memberLevel;
	
	/**
	 * 会员等级描述
	 */
	private String memberLevelDesc;	

	/**
	 * 下一个会员等级,如果当前级别是最高级别，该值为空
	 */
	private String nextLevel;
	
	/**
	 * 下一个会员等级描述,如果当前级别是最高级别，该值为空
	 */
	private String nextLevelDesc;
	
	/**
	 * 需在投金额
	 */
	private long needInvest;
	
	/**
	 * 是否是微信用户
	 */
	private boolean isWXUser;
	
	/**
	 * 是否为第一次升级提示
	 */
	private boolean isFirstLevelUp;
	
	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public boolean isFirstLevelUp() {
		return isFirstLevelUp;
	}

	public void setFirstLevelUp(boolean isFirstLevelUp) {
		this.isFirstLevelUp = isFirstLevelUp;
	}

	public boolean isWXUser() {
		return isWXUser;
	}

	public void setWXUser(boolean isWXUser) {
		this.isWXUser = isWXUser;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getMemberLevelDesc() {
		return memberLevelDesc;
	}

	public void setMemberLevelDesc(String memberLevelDesc) {
		this.memberLevelDesc = memberLevelDesc;
	}

	public String getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(String nextLevel) {
		this.nextLevel = nextLevel;
	}

	public String getNextLevelDesc() {
		return nextLevelDesc;
	}

	public void setNextLevelDesc(String nextLevelDesc) {
		this.nextLevelDesc = nextLevelDesc;
	}

	public long getNeedInvest() {
		return needInvest;
	}

	public void setNeedInvest(long needInvest) {
		this.needInvest = needInvest;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
