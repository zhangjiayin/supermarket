package com.linkwee.web.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.ChangeParentRecord;
import com.linkwee.web.service.LcsListService.CfgLevel;

import java.util.Date;
import java.util.List;


public class LcsDetailResp extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1840107652864359699L;
	
	private String number;//理财师编号
	private String name;//理财师名称
	private String mobile;//理财师手机号码
	private String idcard;//理财师身份证
	private String department;//理财师所属机构

	private String searchText;

	/**
	 * 理财师头像
	 */
	private String image;
	/**
	 * 当前所属机构父机构
	 */
	private String departmentParentId;
	/**
	 * 当前所属机构
	 */
	private String departmentId;

	private String cfplevel;//理财师等级
	private Integer team;//理财师团队
	private Integer customer;//理财师客户
	private Date regTime;//理财师注册时间
	private Date disabledLoginTime;//禁止登录90天开始时间
	private Date disabledLoginEndTime;//禁止登录90天结束时间
	private String parentName;//上级姓名
	private String parentMobile;//上级电话
	private String parentId;
	private List<ChangeParentRecord> changeParentRecordList;//更换上级记录
	private String userRegTime;
	/**
	 * 经验值
	 */
	private Integer experience=0;

	/**
	 * 是否新财富理财师
	 */
	private Integer isNew=0;
	/**
	 * 理财师信息ID
	 */
	private String  customerId;
	
	public LcsDetailResp() {
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCfplevel() {
		return CfgLevel.getCfgLevel(cfplevel);
	}
	public void setCfplevel(String cfplevel) {
		this.cfplevel = cfplevel;
	}
	
	public Integer getTeam() {
		return team;
	}
	public void setTeam(Integer team) {
		this.team = team;
	}
	

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getRegTime() {
		return regTime;
	}
	
	public String getRegTimeStr() {
		return regTime==null?"-":DateUtils.format(regTime, DateUtils.FORMAT_LONG);
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}


	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getDisabledLoginTime() {
		return disabledLoginTime;
	}

	public void setDisabledLoginTime(Date disabledLoginTime) {
		this.disabledLoginTime = disabledLoginTime;
	}

	public Date getDisabledLoginEndTime() {
		return disabledLoginEndTime;
	}

	public void setDisabledLoginEndTime(Date disabledLoginEndTime) {
		this.disabledLoginEndTime = disabledLoginEndTime;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<ChangeParentRecord> getChangeParentRecordList() {
		return changeParentRecordList;
	}

	public void setChangeParentRecordList(List<ChangeParentRecord> changeParentRecordList) {
		this.changeParentRecordList = changeParentRecordList;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getDepartmentParentId() {
		return departmentParentId;
	}

	public void setDepartmentParentId(String departmentParentId) {
		this.departmentParentId = departmentParentId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserRegTime() {
		return userRegTime;
	}

	public void setUserRegTime(String userRegTime) {
		this.userRegTime = userRegTime;
	}



	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
