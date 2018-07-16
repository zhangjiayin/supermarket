package com.linkwee.web.model.cim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimOrgAdvertises;
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.model.CimOrgMemberInfo;
import com.linkwee.web.model.CimOrgPicture;

public class OrgInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8316151385656871271L;

	private Integer id;

	/***
	 * 平台编码
	 */
	private String orgNo;

	/**
	 * 机构logo
	 */
	private String platformIco;
	
	/**
	 * 机构前台照片
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
	 * 最小年化收益
	 */
	private BigDecimal feeRateMin;
	
	/**
	 * 最大年化收益
	 */
	private BigDecimal feeRateMax;
	
	/**
	 * 最小产品期限
	 */
	private Integer proDaysMin;
	
	/**
	 * 最大产品期限
	 */
	private Integer proDaysMax;
	
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
	private BigDecimal minFeeRatio;
	
	/**
	 * 最大佣金利率
	 */
	private BigDecimal maxFeeRatio;
	
	/**
	 * 收费类型 1:cpa-按投资人数量进行收费 2:cps-按投资金额进行收费
	 */
	private Integer orgFeeType;

	/**
	 * cpa-金额限制(元)
	 */
	private BigDecimal orgAmountLimit;

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
	 * 是否未对接的机构 (虚拟机构1：是 ,0：否)
	 */
	private Integer orgIsstaticproduct;

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
	 * 2.0机构活动宣传图
	 */
	private List<ActivityList> orgActivitys;
	/**
	 * 1.2机构活动宣传图
	 */
	private List<CimOrgAdvertises> orgAdvertises;
	
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
	private BigDecimal orgFeeRatio;
	
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
     *平台列表logo
     */
	private String platformlistIco;
	/**
	 * 首笔销售费用cpa+cps
	 */
	private Integer isCpaAndCps;
	
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

	public BigDecimal getOrgFeeRatio() {
		return orgFeeRatio;
	}

	public void setOrgFeeRatio(BigDecimal orgFeeRatio) {
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

	public BigDecimal getFeeRateMin() {
		return feeRateMin;
	}

	public void setFeeRateMin(BigDecimal feeRateMin) {
		this.feeRateMin = feeRateMin;
	}

	public BigDecimal getFeeRateMax() {
		return feeRateMax;
	}

	public void setFeeRateMax(BigDecimal feeRateMax) {
		this.feeRateMax = feeRateMax;
	}

	public Integer getProDaysMin() {
		return proDaysMin;
	}

	public void setProDaysMin(Integer proDaysMin) {
		this.proDaysMin = proDaysMin;
	}

	public Integer getProDaysMax() {
		return proDaysMax;
	}

	public void setProDaysMax(Integer proDaysMax) {
		this.proDaysMax = proDaysMax;
	}

	public BigDecimal getMinFeeRatio() {
		return minFeeRatio;
	}

	public void setMinFeeRatio(BigDecimal minFeeRatio) {
		this.minFeeRatio = minFeeRatio;
	}

	public BigDecimal getMaxFeeRatio() {
		return maxFeeRatio;
	}

	public void setMaxFeeRatio(BigDecimal maxFeeRatio) {
		this.maxFeeRatio = maxFeeRatio;
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

	public List<ActivityList> getOrgActivitys() {
		return orgActivitys;
	}

	public void setOrgActivitys(List<ActivityList> orgActivitys) {
		this.orgActivitys = orgActivitys;
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

		if (proDaysMin != null && proDaysMax != null && proDaysMin.equals(proDaysMax)) {
			if (StringUtils.isNotBlank(deadLineMinSelfDefined)) {
				deadLineValueText = deadLineMinSelfDefined;
			} else {
				deadLineValueText = proDaysMin + "天";
			}
		} else {
			if (StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)) {
				deadLineValueText = deadLineMinSelfDefined + "~" + deadLineMaxSelfDefined;
			} else {
				deadLineValueText = proDaysMin + "天~" + proDaysMax + "天";
			}
		}

		return StringUtils.separateNumberChinese(deadLineValueText, ",");

	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public BigDecimal getOrgAmountLimit(){
		return orgAmountLimit;
	}

	public void setOrgAmountLimit(BigDecimal orgAmountLimit) {
		this.orgAmountLimit = orgAmountLimit;
	}

	public Integer getOrgInvestdeadlineLimit() {
		return orgInvestdeadlineLimit;
	}

	public void setOrgInvestdeadlineLimit(Integer orgInvestdeadlineLimit) {
		this.orgInvestdeadlineLimit = orgInvestdeadlineLimit;
	}

	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
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

	public String getPlatformlistIco() {
		return platformlistIco;
	}

	public void setPlatformlistIco(String platformlistIco) {
		this.platformlistIco = platformlistIco;
	}

	public Integer getIsCpaAndCps() {
		return isCpaAndCps;
	}

	public void setIsCpaAndCps(Integer isCpaAndCps) {
		this.isCpaAndCps = isCpaAndCps;
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
