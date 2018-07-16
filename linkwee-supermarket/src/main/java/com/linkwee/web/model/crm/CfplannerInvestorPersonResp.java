package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 投资者端 个人中心 首页
 * 
 * @Date 2016年1月20日 下午5:36:22
 */
public class CfplannerInvestorPersonResp extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	/**
	 * 直推理财师人数
	 */
	private String directRecomNum;
	/**
	 * 我关注的客户/我关注的直推理财师
	 */
	private String myAttention;
	/**
	 * 未投资的客户/未出单的直推理财师
	 */
	private String noInvest;
	/**
	 * 二级理财师人数
	 */
	private String secondLevelNum;
	/**
	 * 	三级理财师人数
	 */
	private String threeLevelNum;
	/**
	 * 	我的客户
	 */
	private String myCustomerNum;
	public String getDirectRecomNum() {
		return directRecomNum;
	}
	public void setDirectRecomNum(String directRecomNum) {
		this.directRecomNum = directRecomNum;
	}
	public String getMyAttention() {
		return myAttention;
	}
	public void setMyAttention(String myAttention) {
		this.myAttention = myAttention;
	}
	public String getNoInvest() {
		return noInvest;
	}
	public void setNoInvest(String noInvest) {
		this.noInvest = noInvest;
	}
	public String getSecondLevelNum() {
		return secondLevelNum;
	}
	public void setSecondLevelNum(String secondLevelNum) {
		this.secondLevelNum = secondLevelNum;
	}
	public String getThreeLevelNum() {
		return threeLevelNum;
	}
	public void setThreeLevelNum(String threeLevelNum) {
		this.threeLevelNum = threeLevelNum;
	}
	public String getMyCustomerNum() {
		return myCustomerNum;
	}
	public void setMyCustomerNum(String myCustomerNum) {
		this.myCustomerNum = myCustomerNum;
	} 

}
