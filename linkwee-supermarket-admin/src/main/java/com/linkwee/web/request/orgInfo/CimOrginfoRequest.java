package com.linkwee.web.request.orgInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.web.model.CimOrgMemberInfo;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrginfoRequest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5900072174289126880L;

	 /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *机构名称
     */
	private String orgName;
	
    /**
     *机构后台账户
     */
	private String orgAccount;
	
    /**
     *机构后台密码
     */
	private String orgPassword;
	
    /**
     *平台背景
     */
	private String context;
	
    /**
     *注册资本
     */
	private String capital;
	
    /**
     *上线时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date upTime;
	
    /**
     *所在城市
     */
	private String city;
	
    /**
     *icp备案
     */
	private String icpFiling;
	
    /**
     *法人代表
     */
	private String representative;
	
    /**
     *联系方式
     */
	private String contact;
	
    /**
     *主页平台logo
     */
	private String platformImg;
	
    /**
     *平台列表logo
     */
	private String platformListImg;
	
    /**
     *平台详情图片
     */
	private String platformDetailImg;
	
    /**
     *营业执照
     */
	private String businessLicenseImg;
	
    /**
     *首页推荐，是否首页推荐0-不推荐、1-推荐
     */
	private Integer recommend;
	
    /**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private String grade;
	
    /**
     *机构列表排名
     */
	private Integer top;
	
    /**
     *首页推荐机构排名
     */
	private Integer homepageSort;
	
    /**
     *合作状态.0-合作结束，1-合作中
     */
	private Integer status;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *更新时间
     */
	private Date updateTime;
	
    /**
     *最小年化收益
     */
	private BigDecimal minProfit;
	
    /**
     *最大年化收益
     */
	private BigDecimal maxProfit;
	
    /**
     *平台产品最小佣金率
     */
	private BigDecimal minFeeRatio;
	
    /**
     *平台产品最大佣金率
     */
	private BigDecimal maxFeeRatio;
	
    /**
     *最小产品期限
     */
	private Integer minDeadLine;
	
    /**
     *最大产品期限
     */
	private Integer maxDeadLine;
	
    /**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
    /**
     *机构官网的url
     */
	private String orgUrl;
	
    /**
     *平台产品跳转地址
     */
	private String orgProductUrl;
	
    /**
     *平台用户中心跳转地址
     */
	private String orgUsercenterUrl;
	
    /**
     *绑定用户地址
     */
	private String orgBindUserUrl;
	
    /**
     *用户资产余额查询接口（可选）
     */
	private String orgUserbalanceUrl;
	
	/**
     *查帐号是否存在于第三方平台url
     */
	private String orgUserExistUrl;
	
    /**
     *资金托管
     */
	private String trusteeship;
	
    /**
     *机构简介
     */
	private String orgProfile;
	
    /**
     *安全保障
     */
	private String orgSecurity;
	
    /**
     *机构佣金
     */
	private BigDecimal orgFeeRatio;
	
    /**
     *收费类型 ,1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费
     */
	private Integer orgFeeType;
	
    /**
     *金额限制(元)
     */
	private BigDecimal orgAmountLimit;
	
    /**
     *投资期限限制(天)
     */
	private Integer orgInvestdeadlineLimit;
	
	/**
	 * 团队信息
	 */
	private List<CimOrgMemberInfo> teams;
	
	/**
     *创建人
     */
	private String orgCreator;
	
    /**
     *修改人
     */
	private String orgUpdater;
	
	/**
     *是否虚拟机构 (1：是 ,0：否)
     */
	private Integer orgIsstaticproduct;
	
	/**
	 * pc端 优质平台广告
	 */
	private String orgAdvertisement;
	
	/**
	 * pc端 优质平台广告跳转地址
	 */
	private String orgAdvertisementUrl;
	
	/**
     *机构考查报告
     */
	private String orgInvestigationReport;
	
	/**
     *机构考查报告下载地址
     */
	private String orgInvestigationReportUrl;
	
    /**
     *机构缴纳的保证金
     */
	private BigDecimal orgMargin;
	
    /**
     *机构亮点介绍(多个以英文逗号分隔)
     */
	private String orgAdvantage;
	
    /**
     *机构自定义标签(多个以英文逗号分隔)
     */
	private String orgTag;
	
    /**
     *产品自定义标签(多个以英文逗号分隔)
     */
	private String orgProductTag;
	
	/**
     *投资攻略
     */
	private String orgInvestStrategy;
	
    /**
     *猎财攻略
     */
	private String orgPlannerStrategy;
	
	/**
	 * 投呗端平台自定义标签(多个以英文逗号分隔)
	 */
	private String orgInvestTag;
	
	/**
	 * 猎财端平台自定义标签(多个以英文逗号分隔)
	 */
	private String orgPlannerTag;
	
	/**
     * 猎财端产品自定义标签(多个以英文逗号分隔)
     */
	private String orgPlannerProductTag;
	
	/**
	 * 荣誉
	 */
	private String orgHonor;
	
	/**
	 * 机构灰度状态(0:否，1:是)
	 */
	private Integer orgGrayStatus;
	
	/**
	 * 最新入驻平台图片(PC端)
	 */
	private String orgNewestImg;
	
	/**
	 * 对接的机构类型
	 * (0:移动+PC端，1:移动端，2:PC端)
	 */
	private Integer orgJoinType;
	
	
	/**
	 * 办公环境照
	 */
	private String orgEnvironmentPicture;
	
	/**
	 * 其他资格证
	 */
	private String orgPaperPicture;
	
	/**
	 * 荣誉证书
	 */
	private String orgHonorPicture;
	
	/**
	 * 平台主页logo(PC端)
	 */
	private String pcPlatformImg;
	
	/**
	 * 平台列表logo(PC端)
	 */
	private String pcPlatformListImg;
	
	/**
	 * 平台详情图片(PC端)
	 */
	private String pcPlatformDetailImg;
	
	/**
     *债券转让(PC端)
     */
	private String orgDebentureTransfer;
	
    /**
     *投标保障(PC端)
     */
	private String orgBidSecurity;
	
    /**
     *保障模式(PC端)
     */
	private String orgSecurityMode;
	
    /**
     *网站备案详情(PC端)
     */
	private String orgWebsiteRecords;
	
    /**
     *机构联系方式详情(PC端)
     */
	private String orgContactDetails;
	
	/**
	 * PC端-办公环境照
	 */
	private String orgPcEnvironmentPicture;
	
	/**
	 * PC端-其他资格证
	 */
	private String orgPcPaperPicture;
	
	/**
	 * PC端-荣誉证书
	 */
	private String orgPcHonorPicture;
	
	/**
     *	理财师级差佣金率
     */
	private BigDecimal diffFeeRatio;
	
	/**
	 * 机构合作结束跳转地址
	 */
	private String cooperationEndUrl; 
	
	/**
	 * 机构佣金上限提示语
	 */
	private String orgFeeRatioLimit;
	
	/**
	 * 分享标题
	 */
	private String shareTitle;
	
	/**
	 * 分享链接
	 */
	private String shareLink;
	
	/**
	 * 分享图标
	 */
	private String shareIcon;
	
	/**
	 * 分享描述
	 */
	private String shareDesc;
	
	/**
     *发标时间
     */
	private String productReleaseTime;
	
    /**
     *充值限制(描述)
     */
	private String rechargeLimitDescription;
	
    /**
     *充值限制(标题)
     */
	private String rechargeLimitTitle;
	
    /**
     *充值限制(链接)
     */
	private String rechargeLimitLinkUrl;
	
    /**
     *起息时间
     */
	private String interestTime;
	
    /**
     *提现费用
     */
	private String withdrawalCharges;
	
    /**
     *提现到账时间
     */
	private String cashInTime;
	
    /**
     *其他（投资相关）
     */
	private String investOthers;
	
    /**
     *机构提现跳转地址
     */
	private String orgWithdrawDepositUrl;
	
    /**
     *机构充值跳转地址
     */
	private String orgRechargeUrl;	
	
    /**
     *机构首页链接地址
     */
	private String orgUrlSkipIndex;
	
    /**
     *机构产品购买记录链接
     */
	private String orgUrlSkipProductBuyRecord;
	
    /**
     *机构充值记录链接
     */
	private String orgUrlSkipRechargeRecord;
	
    /**
     *机构提现记录链接
     */
	private String orgUrlSkipWithdrawDepositRecord;
	
    /**
     *机构安全中心详情页链接
     */
	private String orgUrlSkipSafeCenter;
	
    /**
     *机构活动列表页面链接
     */
	private String orgUrlSkipActivity;
	
    /**
     *机构平台银行充值限额表链接
     */
	private String orgUrlSkipBankcardRechargeLimit;
	
    /**
     *机构官方公告列表链接
     */
	private String orgUrlSkipOfficialNotice;
	
    /**
     *机构产品购买后电子合同链接
     */
	private String orgUrlSkipProductBuyCompact;
	
    /**
     *机构红包链接
     */
	private String orgUrlSkipRedpackage;
	
    /**
     *机构优惠券链接
     */
	private String orgUrlSkipCoupon;
	
    /**
     *机构积分链接
     */
	private String orgUrlSkipConcessionScore;
	
    /**
     *平台详情跳转类型 0-直接跳转第三方  1-跳转本地机构详情页
     */
	private Integer orgUrlSkipJumpType;
	
    /**
     *平台产品跳转绑卡类型  0-跳转前不需要绑卡  1-跳转前需要绑卡
     */
	private Integer orgProductUrlSkipBindType;	
		
	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}

	public String getShareIcon() {
		return shareIcon;
	}

	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}

	public String getShareDesc() {
		return shareDesc;
	}

	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}

	public String getOrgFeeRatioLimit() {
		return orgFeeRatioLimit;
	}

	public void setOrgFeeRatioLimit(String orgFeeRatioLimit) {
		this.orgFeeRatioLimit = orgFeeRatioLimit;
	}

	public String getCooperationEndUrl() {
		return cooperationEndUrl;
	}

	public void setCooperationEndUrl(String cooperationEndUrl) {
		this.cooperationEndUrl = cooperationEndUrl;
	}

	public BigDecimal getDiffFeeRatio() {
		return diffFeeRatio;
	}

	public void setDiffFeeRatio(BigDecimal diffFeeRatio) {
		this.diffFeeRatio = diffFeeRatio;
	}

	public String getOrgPcEnvironmentPicture() {
		return orgPcEnvironmentPicture;
	}

	public void setOrgPcEnvironmentPicture(String orgPcEnvironmentPicture) {
		this.orgPcEnvironmentPicture = orgPcEnvironmentPicture;
	}

	public String getOrgPcPaperPicture() {
		return orgPcPaperPicture;
	}

	public void setOrgPcPaperPicture(String orgPcPaperPicture) {
		this.orgPcPaperPicture = orgPcPaperPicture;
	}

	public String getOrgPcHonorPicture() {
		return orgPcHonorPicture;
	}

	public void setOrgPcHonorPicture(String orgPcHonorPicture) {
		this.orgPcHonorPicture = orgPcHonorPicture;
	}

	public String getOrgInvestigationReportUrl() {
		return orgInvestigationReportUrl;
	}

	public void setOrgInvestigationReportUrl(String orgInvestigationReportUrl) {
		this.orgInvestigationReportUrl = orgInvestigationReportUrl;
	}

	public String getPcPlatformImg() {
		return pcPlatformImg;
	}

	public void setPcPlatformImg(String pcPlatformImg) {
		this.pcPlatformImg = pcPlatformImg;
	}

	public String getPcPlatformListImg() {
		return pcPlatformListImg;
	}

	public void setPcPlatformListImg(String pcPlatformListImg) {
		this.pcPlatformListImg = pcPlatformListImg;
	}

	public String getPcPlatformDetailImg() {
		return pcPlatformDetailImg;
	}

	public void setPcPlatformDetailImg(String pcPlatformDetailImg) {
		this.pcPlatformDetailImg = pcPlatformDetailImg;
	}

	public String getOrgDebentureTransfer() {
		return orgDebentureTransfer;
	}

	public void setOrgDebentureTransfer(String orgDebentureTransfer) {
		this.orgDebentureTransfer = orgDebentureTransfer;
	}

	public String getOrgBidSecurity() {
		return orgBidSecurity;
	}

	public void setOrgBidSecurity(String orgBidSecurity) {
		this.orgBidSecurity = orgBidSecurity;
	}

	public String getOrgSecurityMode() {
		return orgSecurityMode;
	}

	public void setOrgSecurityMode(String orgSecurityMode) {
		this.orgSecurityMode = orgSecurityMode;
	}

	public String getOrgWebsiteRecords() {
		return orgWebsiteRecords;
	}

	public void setOrgWebsiteRecords(String orgWebsiteRecords) {
		this.orgWebsiteRecords = orgWebsiteRecords;
	}

	public String getOrgContactDetails() {
		return orgContactDetails;
	}

	public void setOrgContactDetails(String orgContactDetails) {
		this.orgContactDetails = orgContactDetails;
	}

	public String getOrgEnvironmentPicture() {
		return orgEnvironmentPicture;
	}

	public void setOrgEnvironmentPicture(String orgEnvironmentPicture) {
		this.orgEnvironmentPicture = orgEnvironmentPicture;
	}

	public String getOrgPaperPicture() {
		return orgPaperPicture;
	}

	public void setOrgPaperPicture(String orgPaperPicture) {
		this.orgPaperPicture = orgPaperPicture;
	}

	public String getOrgHonorPicture() {
		return orgHonorPicture;
	}

	public void setOrgHonorPicture(String orgHonorPicture) {
		this.orgHonorPicture = orgHonorPicture;
	}

	public String getOrgAdvertisementUrl() {
		return orgAdvertisementUrl;
	}

	public void setOrgAdvertisementUrl(String orgAdvertisementUrl) {
		this.orgAdvertisementUrl = orgAdvertisementUrl;
	}

	public String getOrgNewestImg() {
		return orgNewestImg;
	}

	public void setOrgNewestImg(String orgNewestImg) {
		this.orgNewestImg = orgNewestImg;
	}

	public Integer getOrgJoinType() {
		return orgJoinType;
	}

	public void setOrgJoinType(Integer orgJoinType) {
		this.orgJoinType = orgJoinType;
	}

	public Integer getOrgGrayStatus() {
		return orgGrayStatus;
	}

	public void setOrgGrayStatus(Integer orgGrayStatus) {
		this.orgGrayStatus = orgGrayStatus;
	}

	public String getOrgHonor() {
		return orgHonor;
	}

	public void setOrgHonor(String orgHonor) {
		this.orgHonor = orgHonor;
	}

	public String getOrgInvestTag() {
		return orgInvestTag;
	}

	public void setOrgInvestTag(String orgInvestTag) {
		this.orgInvestTag = orgInvestTag;
	}

	public String getOrgPlannerTag() {
		return orgPlannerTag;
	}

	public void setOrgPlannerTag(String orgPlannerTag) {
		this.orgPlannerTag = orgPlannerTag;
	}

	public String getOrgPlannerProductTag() {
		return orgPlannerProductTag;
	}

	public void setOrgPlannerProductTag(String orgPlannerProductTag) {
		this.orgPlannerProductTag = orgPlannerProductTag;
	}

	public String getOrgInvestStrategy() {
		return orgInvestStrategy;
	}

	public void setOrgInvestStrategy(String orgInvestStrategy) {
		this.orgInvestStrategy = orgInvestStrategy;
	}

	public String getOrgPlannerStrategy() {
		return orgPlannerStrategy;
	}

	public void setOrgPlannerStrategy(String orgPlannerStrategy) {
		this.orgPlannerStrategy = orgPlannerStrategy;
	}


	public String getOrgAdvantage() {
		return orgAdvantage;
	}

	public void setOrgAdvantage(String orgAdvantage) {
		this.orgAdvantage = orgAdvantage;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public void setOrgTag(String orgTag) {
		this.orgTag = orgTag;
	}

	public String getOrgProductTag() {
		return orgProductTag;
	}

	public void setOrgProductTag(String orgProductTag) {
		this.orgProductTag = orgProductTag;
	}

	public String getOrgInvestigationReport() {
		return orgInvestigationReport;
	}

	public void setOrgInvestigationReport(String orgInvestigationReport) {
		this.orgInvestigationReport = orgInvestigationReport;
	}

	public BigDecimal getOrgMargin() {
		return orgMargin;
	}

	public void setOrgMargin(BigDecimal orgMargin) {
		this.orgMargin = orgMargin;
	}

	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}

	public String getOrgAdvertisement() {
		return orgAdvertisement;
	}

	public void setOrgAdvertisement(String orgAdvertisement) {
		this.orgAdvertisement = orgAdvertisement;
	}

	public String getOrgUserExistUrl() {
		return orgUserExistUrl;
	}

	public void setOrgUserExistUrl(String orgUserExistUrl) {
		this.orgUserExistUrl = orgUserExistUrl;
	}

	public List<CimOrgMemberInfo> getTeams() {
		return teams;
	}

	public void setTeams(List<CimOrgMemberInfo> teams) {
		this.teams = teams;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgAccount(String orgAccount){
		this.orgAccount = orgAccount;
	}
	
	public String getOrgAccount(){
		return orgAccount;
	}
	
	public void setOrgPassword(String orgPassword){
		this.orgPassword = orgPassword;
	}
	
	public String getOrgPassword(){
		return orgPassword;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public String getContext(){
		return context;
	}
	
	public void setCapital(String capital){
		this.capital = capital;
	}
	
	public String getCapital(){
		return capital;
	}
	
	public void setUpTime(Date upTime){
		this.upTime = upTime;
	}
	
	public Date getUpTime(){
		return upTime;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setIcpFiling(String icpFiling){
		this.icpFiling = icpFiling;
	}
	
	public String getIcpFiling(){
		return icpFiling;
	}
	
	public void setRepresentative(String representative){
		this.representative = representative;
	}
	
	public String getRepresentative(){
		return representative;
	}
	
	public void setContact(String contact){
		this.contact = contact;
	}
	
	public String getContact(){
		return contact;
	}
	
	
	public String getPlatformImg() {
		return platformImg;
	}

	public void setPlatformImg(String platformImg) {
		this.platformImg = platformImg;
	}

	public String getPlatformListImg() {
		return platformListImg;
	}

	public void setPlatformListImg(String platformListImg) {
		this.platformListImg = platformListImg;
	}

	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}

	public void setPlatformDetailImg(String platformDetailImg){
		this.platformDetailImg = platformDetailImg;
	}
	
	public String getPlatformDetailImg(){
		return platformDetailImg;
	}
	
	
	public void setRecommend(Integer recommend){
		this.recommend = recommend;
	}
	
	public Integer getRecommend(){
		return recommend;
	}
	
	public void setGrade(String grade){
		this.grade = grade;
	}
	
	public String getGrade(){
		return grade;
	}
	
	public void setTop(Integer top){
		this.top = top;
	}
	
	public Integer getTop(){
		return top;
	}
	
	public void setHomepageSort(Integer homepageSort){
		this.homepageSort = homepageSort;
	}
	
	public Integer getHomepageSort(){
		return homepageSort;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
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
	
	public void setMinProfit(BigDecimal minProfit){
		this.minProfit = minProfit;
	}
	
	public BigDecimal getMinProfit(){
		return minProfit;
	}
	
	public void setMaxProfit(BigDecimal maxProfit){
		this.maxProfit = maxProfit;
	}
	
	public BigDecimal getMaxProfit(){
		return maxProfit;
	}
	
	public void setMinFeeRatio(BigDecimal minFeeRatio){
		this.minFeeRatio = minFeeRatio;
	}
	
	public BigDecimal getMinFeeRatio(){
		return minFeeRatio;
	}
	
	public void setMaxFeeRatio(BigDecimal maxFeeRatio){
		this.maxFeeRatio = maxFeeRatio;
	}
	
	public BigDecimal getMaxFeeRatio(){
		return maxFeeRatio;
	}
	
	public void setMinDeadLine(Integer minDeadLine){
		this.minDeadLine = minDeadLine;
	}
	
	public Integer getMinDeadLine(){
		return minDeadLine;
	}
	
	public void setMaxDeadLine(Integer maxDeadLine){
		this.maxDeadLine = maxDeadLine;
	}
	
	public Integer getMaxDeadLine(){
		return maxDeadLine;
	}
	
	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined){
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}
	
	public String getDeadLineMinSelfDefined(){
		return deadLineMinSelfDefined;
	}
	
	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined){
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}
	
	public String getDeadLineMaxSelfDefined(){
		return deadLineMaxSelfDefined;
	}
	
	public void setOrgUrl(String orgUrl){
		this.orgUrl = orgUrl;
	}
	
	public String getOrgUrl(){
		return orgUrl;
	}
	
	public void setOrgProductUrl(String orgProductUrl){
		this.orgProductUrl = orgProductUrl;
	}
	
	public String getOrgProductUrl(){
		return orgProductUrl;
	}
	
	public void setOrgUsercenterUrl(String orgUsercenterUrl){
		this.orgUsercenterUrl = orgUsercenterUrl;
	}
	
	public String getOrgUsercenterUrl(){
		return orgUsercenterUrl;
	}
	
	public void setOrgBindUserUrl(String orgBindUserUrl){
		this.orgBindUserUrl = orgBindUserUrl;
	}
	
	public String getOrgBindUserUrl(){
		return orgBindUserUrl;
	}
	
	public void setOrgUserbalanceUrl(String orgUserbalanceUrl){
		this.orgUserbalanceUrl = orgUserbalanceUrl;
	}
	
	public String getOrgUserbalanceUrl(){
		return orgUserbalanceUrl;
	}
	
	public void setTrusteeship(String trusteeship){
		this.trusteeship = trusteeship;
	}
	
	public String getTrusteeship(){
		return trusteeship;
	}
	
	public void setOrgProfile(String orgProfile){
		this.orgProfile = orgProfile;
	}
	
	public String getOrgProfile(){
		return orgProfile;
	}
	
	public void setOrgSecurity(String orgSecurity){
		this.orgSecurity = orgSecurity;
	}
	
	public String getOrgSecurity(){
		return orgSecurity;
	}
	
	public void setOrgFeeRatio(BigDecimal orgFeeRatio){
		this.orgFeeRatio = orgFeeRatio;
	}
	
	public BigDecimal getOrgFeeRatio(){
		return orgFeeRatio;
	}
	
	public void setOrgFeeType(Integer orgFeeType){
		this.orgFeeType = orgFeeType;
	}
	
	public Integer getOrgFeeType(){
		return orgFeeType;
	}
	
	public void setOrgAmountLimit(BigDecimal orgAmountLimit){
		this.orgAmountLimit = orgAmountLimit;
	}
	
	public BigDecimal getOrgAmountLimit(){
		return orgAmountLimit;
	}
	
	public void setOrgInvestdeadlineLimit(Integer orgInvestdeadlineLimit){
		this.orgInvestdeadlineLimit = orgInvestdeadlineLimit;
	}
	
	public Integer getOrgInvestdeadlineLimit(){
		return orgInvestdeadlineLimit;
	}

	public String getOrgCreator() {
		return orgCreator;
	}

	public void setOrgCreator(String orgCreator) {
		this.orgCreator = orgCreator;
	}

	public String getOrgUpdater() {
		return orgUpdater;
	}

	public void setOrgUpdater(String orgUpdater) {
		this.orgUpdater = orgUpdater;
	}

	public String getProductReleaseTime() {
		return productReleaseTime;
	}

	public void setProductReleaseTime(String productReleaseTime) {
		this.productReleaseTime = productReleaseTime;
	}

	public String getRechargeLimitDescription() {
		return rechargeLimitDescription;
	}

	public void setRechargeLimitDescription(String rechargeLimitDescription) {
		this.rechargeLimitDescription = rechargeLimitDescription;
	}

	public String getRechargeLimitTitle() {
		return rechargeLimitTitle;
	}

	public void setRechargeLimitTitle(String rechargeLimitTitle) {
		this.rechargeLimitTitle = rechargeLimitTitle;
	}

	public String getRechargeLimitLinkUrl() {
		return rechargeLimitLinkUrl;
	}

	public void setRechargeLimitLinkUrl(String rechargeLimitLinkUrl) {
		this.rechargeLimitLinkUrl = rechargeLimitLinkUrl;
	}

	public String getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(String interestTime) {
		this.interestTime = interestTime;
	}

	public String getWithdrawalCharges() {
		return withdrawalCharges;
	}

	public void setWithdrawalCharges(String withdrawalCharges) {
		this.withdrawalCharges = withdrawalCharges;
	}

	public String getCashInTime() {
		return cashInTime;
	}

	public void setCashInTime(String cashInTime) {
		this.cashInTime = cashInTime;
	}

	public String getInvestOthers() {
		return investOthers;
	}

	public void setInvestOthers(String investOthers) {
		this.investOthers = investOthers;
	}

	public String getOrgWithdrawDepositUrl() {
		return orgWithdrawDepositUrl;
	}

	public void setOrgWithdrawDepositUrl(String orgWithdrawDepositUrl) {
		this.orgWithdrawDepositUrl = orgWithdrawDepositUrl;
	}

	public String getOrgRechargeUrl() {
		return orgRechargeUrl;
	}

	public void setOrgRechargeUrl(String orgRechargeUrl) {
		this.orgRechargeUrl = orgRechargeUrl;
	}

	public String getOrgUrlSkipIndex() {
		return orgUrlSkipIndex;
	}

	public void setOrgUrlSkipIndex(String orgUrlSkipIndex) {
		this.orgUrlSkipIndex = orgUrlSkipIndex;
	}

	public String getOrgUrlSkipProductBuyRecord() {
		return orgUrlSkipProductBuyRecord;
	}

	public void setOrgUrlSkipProductBuyRecord(String orgUrlSkipProductBuyRecord) {
		this.orgUrlSkipProductBuyRecord = orgUrlSkipProductBuyRecord;
	}

	public String getOrgUrlSkipRechargeRecord() {
		return orgUrlSkipRechargeRecord;
	}

	public void setOrgUrlSkipRechargeRecord(String orgUrlSkipRechargeRecord) {
		this.orgUrlSkipRechargeRecord = orgUrlSkipRechargeRecord;
	}

	public String getOrgUrlSkipWithdrawDepositRecord() {
		return orgUrlSkipWithdrawDepositRecord;
	}

	public void setOrgUrlSkipWithdrawDepositRecord(
			String orgUrlSkipWithdrawDepositRecord) {
		this.orgUrlSkipWithdrawDepositRecord = orgUrlSkipWithdrawDepositRecord;
	}

	public String getOrgUrlSkipSafeCenter() {
		return orgUrlSkipSafeCenter;
	}

	public void setOrgUrlSkipSafeCenter(String orgUrlSkipSafeCenter) {
		this.orgUrlSkipSafeCenter = orgUrlSkipSafeCenter;
	}

	public String getOrgUrlSkipActivity() {
		return orgUrlSkipActivity;
	}

	public void setOrgUrlSkipActivity(String orgUrlSkipActivity) {
		this.orgUrlSkipActivity = orgUrlSkipActivity;
	}

	public String getOrgUrlSkipBankcardRechargeLimit() {
		return orgUrlSkipBankcardRechargeLimit;
	}

	public void setOrgUrlSkipBankcardRechargeLimit(
			String orgUrlSkipBankcardRechargeLimit) {
		this.orgUrlSkipBankcardRechargeLimit = orgUrlSkipBankcardRechargeLimit;
	}

	public String getOrgUrlSkipOfficialNotice() {
		return orgUrlSkipOfficialNotice;
	}

	public void setOrgUrlSkipOfficialNotice(String orgUrlSkipOfficialNotice) {
		this.orgUrlSkipOfficialNotice = orgUrlSkipOfficialNotice;
	}

	public String getOrgUrlSkipProductBuyCompact() {
		return orgUrlSkipProductBuyCompact;
	}

	public void setOrgUrlSkipProductBuyCompact(String orgUrlSkipProductBuyCompact) {
		this.orgUrlSkipProductBuyCompact = orgUrlSkipProductBuyCompact;
	}

	public String getOrgUrlSkipRedpackage() {
		return orgUrlSkipRedpackage;
	}

	public void setOrgUrlSkipRedpackage(String orgUrlSkipRedpackage) {
		this.orgUrlSkipRedpackage = orgUrlSkipRedpackage;
	}

	public String getOrgUrlSkipCoupon() {
		return orgUrlSkipCoupon;
	}

	public void setOrgUrlSkipCoupon(String orgUrlSkipCoupon) {
		this.orgUrlSkipCoupon = orgUrlSkipCoupon;
	}

	public String getOrgUrlSkipConcessionScore() {
		return orgUrlSkipConcessionScore;
	}

	public void setOrgUrlSkipConcessionScore(String orgUrlSkipConcessionScore) {
		this.orgUrlSkipConcessionScore = orgUrlSkipConcessionScore;
	}

	public Integer getOrgUrlSkipJumpType() {
		return orgUrlSkipJumpType;
	}

	public void setOrgUrlSkipJumpType(Integer orgUrlSkipJumpType) {
		this.orgUrlSkipJumpType = orgUrlSkipJumpType;
	}

	public Integer getOrgProductUrlSkipBindType() {
		return orgProductUrlSkipBindType;
	}

	public void setOrgProductUrlSkipBindType(Integer orgProductUrlSkipBindType) {
		this.orgProductUrlSkipBindType = orgProductUrlSkipBindType;
	}
}