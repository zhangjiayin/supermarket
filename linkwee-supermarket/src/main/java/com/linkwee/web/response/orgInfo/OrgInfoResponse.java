package com.linkwee.web.response.orgInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimOrgAdvertises;
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.model.CimOrgMemberInfo;
import com.linkwee.web.model.CimOrgPicture;
import com.linkwee.web.model.cim.OrgInfo;

/**
 * 
 * 描述：移动端机构详情
 * 
 * @author yalin
 * @date 2016年7月22日 上午11:09:38 Copyright (c) 深圳市前海领会科技有限公司
 */
public class OrgInfoResponse extends BaseEntity {

	private static final long serialVersionUID = -694449523165089014L;
	
	public OrgInfoResponse(){
		
	}
	
	public OrgInfoResponse(OrgInfo orginfo){
		 WebUtil.initObj(this,orginfo);
		 //WebUtil.initObj(this,pcOrginfo,"yyyy-MM-dd hh:mm:ss");
		 this.setFeeRateMin(WebUtil.getDefaultFormat(orginfo.getFeeRateMin())); //最小年化收益
		 this.setFeeRateMax(WebUtil.getDefaultFormat(orginfo.getFeeRateMax())); //最大年化收益
		 this.setProDaysMin(WebUtil.getDefaultFormat(orginfo.getProDaysMin())); //最小产品期限
		 this.setProDaysMax(WebUtil.getDefaultFormat(orginfo.getProDaysMax())); //最大产品期限
		 if(orginfo.getOrgAmountLimit() != null){
			 this.setOrgAmountLimit(orginfo.getOrgAmountLimit().doubleValue()); //cpa - 投资金额限制
		 }
		 
		 this.setTeamInfos(orginfo.getTeamInfos()); //团队成员
		 this.setOrgActivitys(orginfo.getOrgActivitys()); //机构活动宣传图
		 this.setOrgPapersList(orginfo.getOrgPapersList()); //机构证件图片信息
		 this.setOrgEnvironmentList(orginfo.getOrgEnvironmentList()); //机构环境图片信息
		 this.setOrgDynamicList(orginfo.getOrgDynamicList()); //机构动态
		 this.setOrgHonorList(orginfo.getOrgHonorList()); //机构荣誉证书
		 this.setOrgAdvertises(orginfo.getOrgAdvertises()); //1.2版老接口
		 
		 this.setOrgFeeRatio(WebUtil.getDefaultFormat(orginfo.getOrgFeeRatio())); //机构佣金率
		 
		 this.orgCertificatesList = new ArrayList<CimOrgPicture>();
		 
		 if(orginfo.getOrgHonorList() != null && orginfo.getOrgHonorList().size() > 0){
			 for(CimOrgPicture tempPicture : orginfo.getOrgHonorList()){
				 this.orgCertificatesList.add(tempPicture);
			 }
		 }
		 if(orginfo.getOrgPapersList() != null && orginfo.getOrgPapersList().size() > 0){
			 for(CimOrgPicture tempPicture : orginfo.getOrgPapersList()){
				 this.orgCertificatesList.add(tempPicture);
			 }
		 }
	} 
	

	/***
	 * 平台编码
	 */
	private String orgNo;

	/**
	 * 平台logo
	 */
	private String platformIco;
	/**
	 * 平台详情logo
	 */
	private String orgLogo;
	/**
	 * 平台名称
	 */
	private String orgName;
	/**
	 * 平台级别
	 */
	private String orgLevel;
	/**
	 * 年化收益
	 */
	private String feeRateMin;
	private String feeRateMax;
	/**
	 * 产品期限
	 */
	private String proDaysMin;
	private String proDaysMax;
	/**
	 * 上线时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date upTime;
	/**
	 * 平台背景
	 */
	private String orgBack;
	/**
	 * 资金托管
	 */
	private String trusteeship;
	/**
	 * ICP备案
	 */
	private String icp;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 注册资金
	 */
	private String capital;

	/**
	 * 机构官网的url
	 */
	private String orgUrl;

	/**
	 * 平台简介
	 */
	private String orgProfile;

	/**
	 * 安全保障
	 */
	private String orgSecurity;

	/**
	 * 团队信息
	 */
	private List<CimOrgMemberInfo> teamInfos;

	/**
	 * 最小佣金利率
	 */
	private String minFeeRatio;
	
	/**
	 * 最大佣金利率
	 */
	private String maxFeeRatio;
	
	/**
	 * 收费类型 1:cpa-按投资人数量进行收费 2:cps-按投资金额进行收费
	 */
	private Integer orgFeeType;
	
	/**
	 * cpa-金额限制(元)
	 */
	private Double orgAmountLimit;

	/**
	 * cpa-投资期限限制(天)
	 */
	private Integer orgInvestdeadlineLimit;

	/**
	 * 产品最小期限天数 自定义显示
	 */
	private String deadLineMinSelfDefined;

	/**
	 * 产品最大期限天数 自定义显示
	 */
	private String deadLineMaxSelfDefined;

	/**
	 * 产品期限
	 */
	private String deadLineValueText;

	/**
	 * 是否静态产品 (1：是 ,0：否)
	 * 是否为对接机构(1:未对接，0:已对接)
	 */
	private String orgIsstaticproduct;

	/**
	 * 机构亮点介绍(多个以英文逗号分隔)
	 */
	private String orgAdvantage;

	/**
	 * 机构自定义标签(多个以英文逗号分隔)
	 */
	private String orgTag;

	/**
	 * 产品自定义标签(多个以英文逗号分隔)
	 */
	private String orgProductTag;

	/**
	 * 投资攻略
	 */
	private String orgInvestStrategy;

	/**
	 * 猎财攻略
	 */
	private String orgPlannerStrategy;

	
	/**
	 * 机构活动宣传图
	 */
	private List<ActivityList> orgActivitys;
	
	/**
	 * 机构证件图片信息
	 */
	private List<CimOrgPicture> orgPapersList;
	
	/**
	 * 机构环境图片信息
	 */
	private List<CimOrgPicture> orgEnvironmentList;
	
	/**
	 * 机构动态
	 */
	private List<CimOrgDynamic> orgDynamicList;
	
	/**
	 * 机构荣誉证书
	 */
	private List<CimOrgPicture> orgHonorList;
	
	/**
	 * 荣誉
	 */
	private String orgHonor;
	
	/**
	 * 对接的机构类型(0:移动+PC端，1:移动端，2:PC端)
	 */
	private Integer orgJointType;
	
	
	private List<CimOrgAdvertises> orgAdvertises;
	
	/**
	 * 机构可用红包数
	 */
	private Integer platformRedPacketCount;
	
	/**
	 * 机构佣金上限提示语
	 */
	private String orgFeeRatioLimit;
	
	 /**
     *	机构产品佣金率
     */
	private String orgFeeRatio;
	
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
	 * 客服电话
	 */
	private String contact;
	
	/**
	 * 机构证件信息(包含papers和honor)
	 */
	private List<CimOrgPicture> orgCertificatesList;
	
    /**
     *平台产品跳转绑卡类型  0-跳转前不需要绑卡  1-跳转前需要绑卡
     */
	private Integer orgProductUrlSkipBindType;
	
	
	public Double getOrgAmountLimit() {
		return orgAmountLimit;
	}

	public void setOrgAmountLimit(Double orgAmountLimit) {
		this.orgAmountLimit = orgAmountLimit;
	}

	public Integer getOrgInvestdeadlineLimit() {
		return orgInvestdeadlineLimit;
	}

	public void setOrgInvestdeadlineLimit(Integer orgInvestdeadlineLimit) {
		this.orgInvestdeadlineLimit = orgInvestdeadlineLimit;
	}

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

	public String getOrgFeeRatio() {
		return orgFeeRatio;
	}

	public void setOrgFeeRatio(String orgFeeRatio) {
		this.orgFeeRatio = orgFeeRatio;
	}

	public String getOrgFeeRatioLimit() {
		return orgFeeRatioLimit;
	}

	public void setOrgFeeRatioLimit(String orgFeeRatioLimit) {
		this.orgFeeRatioLimit = orgFeeRatioLimit;
	}

	public Integer getPlatformRedPacketCount() {
		return platformRedPacketCount;
	}

	public void setPlatformRedPacketCount(Integer platformRedPacketCount) {
		this.platformRedPacketCount = platformRedPacketCount;
	}
	
	
	public List<CimOrgAdvertises> getOrgAdvertises() {
		return orgAdvertises;
	}

	public void setOrgAdvertises(List<CimOrgAdvertises> orgAdvertises) {
		this.orgAdvertises = orgAdvertises;
	}
	
	public Integer getOrgJointType() {
		return orgJointType;
	}

	public void setOrgJointType(Integer orgJointType) {
		this.orgJointType = orgJointType;
	}
	
	
	public List<ActivityList> getOrgActivitys() {
		return orgActivitys;
	}

	public void setOrgActivitys(List<ActivityList> orgActivitys) {
		this.orgActivitys = orgActivitys;
	}

	public String getOrgHonor() {
		return orgHonor;
	}
	public void setOrgHonor(String orgHonor) {
		this.orgHonor = orgHonor;
	}
	public List<CimOrgPicture> getOrgHonorList() {
		return orgHonorList;
	}

	public void setOrgHonorList(List<CimOrgPicture> orgHonorList) {
		this.orgHonorList = orgHonorList;
	}
	

	public List<CimOrgDynamic> getOrgDynamicList() {
		return orgDynamicList;
	}
	public void setOrgDynamicList(List<CimOrgDynamic> orgDynamicList) {
		this.orgDynamicList = orgDynamicList;
	}
	public List<CimOrgPicture> getOrgPapersList() {
		return orgPapersList;
	}

	public void setOrgPapersList(List<CimOrgPicture> orgPapersList) {
		this.orgPapersList = orgPapersList;
	}

	public List<CimOrgPicture> getOrgEnvironmentList() {
		return orgEnvironmentList;
	}

	public void setOrgEnvironmentList(List<CimOrgPicture> orgEnvironmentList) {
		this.orgEnvironmentList = orgEnvironmentList;
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

	public String getPlatformIco() {
		return platformIco;
	}

	public void setPlatformIco(String platformIco) {
		this.platformIco = platformIco;
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

	public String getDeadLineMinSelfDefined() {
		return deadLineMinSelfDefined;
	}

	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined) {
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}

	public String getDeadLineMaxSelfDefined() {
		return deadLineMaxSelfDefined;
	}

	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined) {
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}


	public String getDeadLineValueText() {
		return deadLineValueText;
	}
	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public String getMinFeeRatio() {
		return minFeeRatio;
	}

	public void setMinFeeRatio(String minFeeRatio) {
		this.minFeeRatio = minFeeRatio;
	}

	public String getMaxFeeRatio() {
		return maxFeeRatio;
	}

	public void setMaxFeeRatio(String maxFeeRatio) {
		this.maxFeeRatio = maxFeeRatio;
	}


	public String getOrgProfile() {
		return orgProfile;
	}

	public void setOrgProfile(String orgProfile) {
		this.orgProfile = orgProfile;
	}

	public String getOrgSecurity() {
		return orgSecurity;
	}

	public void setOrgSecurity(String orgSecurity) {
		this.orgSecurity = orgSecurity;
	}

	public List<CimOrgMemberInfo> getTeamInfos() {
		return teamInfos;
	}

	public void setTeamInfos(List<CimOrgMemberInfo> teamInfos) {
		this.teamInfos = teamInfos;
	}

	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getFeeRateMin() {
		return feeRateMin;
	}

	public void setFeeRateMin(String feeRateMin) {
		this.feeRateMin = feeRateMin;
	}

	public String getFeeRateMax() {
		return feeRateMax;
	}

	public void setFeeRateMax(String feeRateMax) {
		this.feeRateMax = feeRateMax;
	}

	public String getProDaysMin() {
		return proDaysMin;
	}

	public void setProDaysMin(String proDaysMin) {
		this.proDaysMin = proDaysMin;
	}

	public String getProDaysMax() {
		return proDaysMax;
	}

	public void setProDaysMax(String proDaysMax) {
		this.proDaysMax = proDaysMax;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getOrgBack() {
		return orgBack;
	}

	public void setOrgBack(String orgBack) {
		this.orgBack = orgBack;
	}

	public String getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(String trusteeship) {
		this.trusteeship = trusteeship;
	}


	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Integer getOrgFeeType() {
		return orgFeeType;
	}

	public void setOrgFeeType(Integer orgFeeType) {
		this.orgFeeType = orgFeeType;
	}

	public String getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(String orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<CimOrgPicture> getOrgCertificatesList() {
		return orgCertificatesList;
	}

	public void setOrgCertificatesList(List<CimOrgPicture> orgCertificatesList) {
		this.orgCertificatesList = orgCertificatesList;
	}

	public Integer getOrgProductUrlSkipBindType() {
		return orgProductUrlSkipBindType;
	}

	public void setOrgProductUrlSkipBindType(Integer orgProductUrlSkipBindType) {
		this.orgProductUrlSkipBindType = orgProductUrlSkipBindType;
	}

}
