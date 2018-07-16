package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.xoss.util.WebUtil;

/**
 * 4.5.0我的 理财师团队成员详情
 * 
 * @Date 2017年10月17日 下午5:36:22
 */
public class CfplannerMemberResponse extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;
	
	public CfplannerMemberResponse() {
	}

	public CfplannerMemberResponse(CfplannerMemberResponse obj){
		WebUtil.initObj(this, obj);
		this.setHeadImage(obj.getHeadImage()!=null?obj.getHeadImage():"2af903bfde4e1b7b671ef9668b960fb5");
		this.setMobile(obj.getMobile());
		this.setUserName(obj.getUserName()==null?"未认证":obj.getUserName());
		this.setRegistTime(obj.getRegistTime()+" 注册");
		this.setTeamMemberCount(obj.getTeamMemberCount());
		this.setUserId(obj.getUserId());
	}
	

	/**
	 * 姓名
	 */
	private String userName;
	
	/**
	 * 头像	
	 */
	private String headImage;		
	
	/**
	 * 手机
	 */
	private String mobile;			
	
	/**
	 * 注册时间		
	 */
	private String registTime;	
    
	/**
	 * 团队成员数
	 */
	private String teamMemberCount;
	
	/**
	 * 用户id
	 */
	private String userId;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
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

	public String getTeamMemberCount() {
		return teamMemberCount;
	}

	public void setTeamMemberCount(String teamMemberCount) {
		this.teamMemberCount = teamMemberCount;
	}
	
}
