package com.linkwee.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：我的团队-分页
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerListResp extends BaseEntity {

	private static final long serialVersionUID = 5753516043009306844L;

	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 直接收益
	 */
	private Double allowance;

	/**
	 * 间接收益
	 */
	private Double childrenAllowance;

	/**
	 * 下级人数
	 */
	private Integer childrenCount;
	
	/**
	 * 是否已读
	 */
	private Integer isRead;
	
	/**
	 * 新下级成员数量
	 */
	private Integer newSubordinateCount;
	
	/**
	 * 头像
	 */
	private String headImage;
	
	/**
	 * 首单时间
	 */
	private Date firstRcpDate;
	
	/**
	 * 直接管理津贴
	 */
	private Double directAllowance;
	/**
	 * 下下级人数
	 */
	private Integer grandChildrenCount;
	/**
	 * 职级
	 */
	private String jobGrade;
	/**
	 * 下下级新增人数
	 */
	private Integer newGrandChildrenCount;
	/**
	 * 团队管理津贴
	 */
	private Double teamAllowance;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public Integer getNewSubordinateCount() {
		return newSubordinateCount;
	}

	public void setNewSubordinateCount(Integer newSubordinateCount) {
		this.newSubordinateCount = newSubordinateCount;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	public Double getChildrenAllowance() {
		return childrenAllowance;
	}

	public void setChildrenAllowance(Double childrenAllowance) {
		this.childrenAllowance = childrenAllowance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Date getFirstRcpDate() {
		return firstRcpDate;
	}

	public void setFirstRcpDate(Date firstRcpDate) {
		this.firstRcpDate = firstRcpDate;
	}

	public Double getDirectAllowance() {
		return directAllowance;
	}

	public void setDirectAllowance(Double directAllowance) {
		this.directAllowance = directAllowance;
	}

	public Integer getGrandChildrenCount() {
		return grandChildrenCount;
	}

	public void setGrandChildrenCount(Integer grandChildrenCount) {
		this.grandChildrenCount = grandChildrenCount;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public Integer getNewGrandChildrenCount() {
		return newGrandChildrenCount;
	}

	public void setNewGrandChildrenCount(Integer newGrandChildrenCount) {
		this.newGrandChildrenCount = newGrandChildrenCount;
	}

	public Double getTeamAllowance() {
		return teamAllowance;
	}

	public void setTeamAllowance(Double teamAllowance) {
		this.teamAllowance = teamAllowance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
