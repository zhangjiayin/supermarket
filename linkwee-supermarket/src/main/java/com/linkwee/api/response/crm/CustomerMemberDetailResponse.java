package com.linkwee.api.response.crm;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.crm.OrgSimpleResp;

/**
 * 我的 首页 基金
 * 
 * @Date 2017年10月17日 下午5:36:22
 */
public class CustomerMemberDetailResponse extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	
	/**
	 * 在投金额(元)
	 */
	private String currInvestAmt;
	
	/**
	 * 首投时间
	 */
	private String firstInvestTime;			
    
	/**
	 * 关注
	 */
	private Boolean follow;			
	
	/**
	 * 头像	
	 */
	private String headImage;		
	
	/**
	 * 最近一次登录时间	
	 */
	private String loginTime;		
	
	/**
	 * 手机
	 */
	private String mobile;			
	
	/**
	 * 注册时间		
	 */
	private String registTime;	
    
	/**
	 * 已注册平台列表
	 */
	private List<OrgSimpleResp> registeredOrgList;
       
	/**
	 * 平台图标
	 */
	private String orgLogo;			
    
	/**
	 * 本月投资金额(元)
	 */
	private String thisMonthInvestAmt;			
    
	/**
	 *	本月贡献收入(元)
	 */
	private String thisMonthProfit;		
    
	/**
	 * 	累计投资金额(元)
	 */
	private String totalInvestAmt;		
    
	/**
	 * 累计贡献收入(元)
	 */
	private String totalProfit;			
    
	/**
	 * 姓名
	 */
	private String userName;	

	public String getCurrInvestAmt() {
		return currInvestAmt;
	}

	public void setCurrInvestAmt(String currInvestAmt) {
		this.currInvestAmt = currInvestAmt;
	}

	public String getFirstInvestTime() {
		return firstInvestTime;
	}

	public void setFirstInvestTime(String firstInvestTime) {
		this.firstInvestTime = firstInvestTime;
	}

	public Boolean getFollow() {
		return follow;
	}

	public void setFollow(Boolean follow) {
		this.follow = follow;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public List<OrgSimpleResp> getRegisteredOrgList() {
		return registeredOrgList;
	}

	public void setRegisteredOrgList(List<OrgSimpleResp> registeredOrgList) {
		this.registeredOrgList = registeredOrgList;
	}

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}

	public String getThisMonthInvestAmt() {
		return thisMonthInvestAmt;
	}

	public void setThisMonthInvestAmt(String thisMonthInvestAmt) {
		this.thisMonthInvestAmt = thisMonthInvestAmt;
	}

	public String getThisMonthProfit() {
		return thisMonthProfit;
	}

	public void setThisMonthProfit(String thisMonthProfit) {
		this.thisMonthProfit = thisMonthProfit;
	}

	public String getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(String totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
