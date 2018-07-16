package com.linkwee.web.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年05月12日 15:48:29
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfplanner implements Serializable {
	
	private static final long serialVersionUID = -5990391574278126583L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *理财师编码
     */
	private String number;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *电子邮件
     */
	private String email;
	
    /**
     *上级理财师：理财师的user_id
     */
	private String parentId;
	
    /**
     *二维码
     */
	private String qrcode;
	
    /**
     *业务员工号
     */
	private String empNo;
	
    /**
     *业务员所属机构
     */
	private String department;
	
    /**
     *佣金入账方式 1：工资账户 2：理财账户
     */
	private Byte settlementType;
	
    /**
     *理财师等级
     */
	private String cfpLevel;
	
    /**
     *理财师注册时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cfpRegTime;
	
    /**
     *理财师转正时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cfpBenormalTime;
	
    /**
     *理财师更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cfpUpdateTime;
	
    /**
     *职级经验数值
     */
	private Integer levelExperience;
	
    /**
     *上传头像图片
     */
	private String headImage;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *最近访问时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date rectVisitTime;
	
    /**
     *禁止登录开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date disabledLoginTime;
	
    /**
     *来源地址
     */
	private String registerFromUrl;
	
    /**
     *受访地址
     */
	private String registerAccessUrl;
	
    /**
     *环信帐号
     */
	private String easemobAcct;
	
    /**
     *环信密码
     */
	private String easemobPassword;
	
    /**
     *环信注册状态 0未注册，1已注册
     */
	private Integer easemobRegStatus;
	
    /**
     *环信昵称设置状态 0未设置，1已设置
     */
	private Integer easemobNicknameStatus;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *是否锁定
     */
	private Byte isLocked;
	
    /**
     *是否启用
0：启用 1：禁用
     */
	private Byte enable;
	
    /**
     *删除状态 0：正常 1：删除
     */
	private Byte delStatus;
	
    /**
     *
     */
	private String salesOrgId;
	
    /**
     *微信用户OPENID
     */
	private String weixinOpenid;
	
    /**
     *职级
     */
	private String jobGrade;
	
    /**
     *职级权重
     */
	private Integer jobGradeWeight;
	
    /**
     *当前职级
     */
	private String jobGradeTemp;
	
    /**
     *当前职级权重
     */
	private Integer jobGradeTempWeight;
	/**
     *姓名
     */
	private String userName;
	/**
	 * 层级
	 */
	private Integer salesOrgDepth;

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
	public String getParentId(){
		return parentId;
	}
	
	public void setQrcode(String qrcode){
		this.qrcode = qrcode;
	}
	
	public String getQrcode(){
		return qrcode;
	}
	
	public void setEmpNo(String empNo){
		this.empNo = empNo;
	}
	
	public String getEmpNo(){
		return empNo;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public void setSettlementType(Byte settlementType){
		this.settlementType = settlementType;
	}
	
	public Byte getSettlementType(){
		return settlementType;
	}
	
	public void setCfpLevel(String cfpLevel){
		this.cfpLevel = cfpLevel;
	}
	
	public String getCfpLevel(){
		return cfpLevel;
	}
	
	public void setCfpRegTime(Date cfpRegTime){
		this.cfpRegTime = cfpRegTime;
	}
	
	public Date getCfpRegTime(){
		return cfpRegTime;
	}
	
	public void setCfpBenormalTime(Date cfpBenormalTime){
		this.cfpBenormalTime = cfpBenormalTime;
	}
	
	public Date getCfpBenormalTime(){
		return cfpBenormalTime;
	}
	
	public void setCfpUpdateTime(Date cfpUpdateTime){
		this.cfpUpdateTime = cfpUpdateTime;
	}
	
	public Date getCfpUpdateTime(){
		return cfpUpdateTime;
	}
	
	public void setLevelExperience(Integer levelExperience){
		this.levelExperience = levelExperience;
	}
	
	public Integer getLevelExperience(){
		return levelExperience;
	}
	
	public void setHeadImage(String headImage){
		this.headImage = headImage;
	}
	
	public String getHeadImage(){
		return headImage;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRectVisitTime(Date rectVisitTime){
		this.rectVisitTime = rectVisitTime;
	}
	
	public Date getRectVisitTime(){
		return rectVisitTime;
	}
	
	public void setDisabledLoginTime(Date disabledLoginTime){
		this.disabledLoginTime = disabledLoginTime;
	}
	
	public Date getDisabledLoginTime(){
		return disabledLoginTime;
	}
	
	public void setRegisterFromUrl(String registerFromUrl){
		this.registerFromUrl = registerFromUrl;
	}
	
	public String getRegisterFromUrl(){
		return registerFromUrl;
	}
	
	public void setRegisterAccessUrl(String registerAccessUrl){
		this.registerAccessUrl = registerAccessUrl;
	}
	
	public String getRegisterAccessUrl(){
		return registerAccessUrl;
	}
	
	public void setEasemobAcct(String easemobAcct){
		this.easemobAcct = easemobAcct;
	}
	
	public String getEasemobAcct(){
		return easemobAcct;
	}
	
	public void setEasemobPassword(String easemobPassword){
		this.easemobPassword = easemobPassword;
	}
	
	public String getEasemobPassword(){
		return easemobPassword;
	}
	
	public void setEasemobRegStatus(Integer easemobRegStatus){
		this.easemobRegStatus = easemobRegStatus;
	}
	
	public Integer getEasemobRegStatus(){
		return easemobRegStatus;
	}
	
	public void setEasemobNicknameStatus(Integer easemobNicknameStatus){
		this.easemobNicknameStatus = easemobNicknameStatus;
	}
	
	public Integer getEasemobNicknameStatus(){
		return easemobNicknameStatus;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setIsLocked(Byte isLocked){
		this.isLocked = isLocked;
	}
	
	public Byte getIsLocked(){
		return isLocked;
	}
	
	public void setEnable(Byte enable){
		this.enable = enable;
	}
	
	public Byte getEnable(){
		return enable;
	}
	
	public void setDelStatus(Byte delStatus){
		this.delStatus = delStatus;
	}
	
	public Byte getDelStatus(){
		return delStatus;
	}
	
	public void setSalesOrgId(String salesOrgId){
		this.salesOrgId = salesOrgId;
	}
	
	public String getSalesOrgId(){
		return salesOrgId;
	}
	
	public void setWeixinOpenid(String weixinOpenid){
		this.weixinOpenid = weixinOpenid;
	}
	
	public String getWeixinOpenid(){
		return weixinOpenid;
	}
	
	public void setJobGrade(String jobGrade){
		this.jobGrade = jobGrade;
	}
	
	public String getJobGrade(){
		return jobGrade;
	}
	
	public void setJobGradeWeight(Integer jobGradeWeight){
		this.jobGradeWeight = jobGradeWeight;
	}
	
	public Integer getJobGradeWeight(){
		return jobGradeWeight;
	}
	
	public void setJobGradeTemp(String jobGradeTemp){
		this.jobGradeTemp = jobGradeTemp;
	}
	
	public String getJobGradeTemp(){
		return jobGradeTemp;
	}
	
	public void setJobGradeTempWeight(Integer jobGradeTempWeight){
		this.jobGradeTempWeight = jobGradeTempWeight;
	}
	
	public Integer getJobGradeTempWeight(){
		return jobGradeTempWeight;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSalesOrgDepth() {
		return salesOrgDepth;
	}

	public void setSalesOrgDepth(Integer salesOrgDepth) {
		this.salesOrgDepth = salesOrgDepth;
	}
}

