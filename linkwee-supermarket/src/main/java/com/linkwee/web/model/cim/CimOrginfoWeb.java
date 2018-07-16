package com.linkwee.web.model.cim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.linkwee.web.model.CimOrgMemberInfo;

/**
 * 
 * 描述：机构后台运营系统实体
 * @author yalin
 * @date 2016年8月25日 上午10:27:12 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class CimOrginfoWeb implements Serializable {

	
	private static final long serialVersionUID = -8504209779156685224L;
	
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
	private String name;
	
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
	private String platformIco;
	
    /**
     *平台列表logo
     */
	private String platformlistIco;
	
    /**
     *平台详情图片
     */
	private String platformDetailImg;
	
    /**
     *营业执照
     */
	private String businessLicense;
	
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
     *查帐号是否存在于第三方平台url
     */
	private String orgUserExistUrl;
	
    /**
     *用户资产余额查询接口（可选）
     */
	private String orgUserbalanceUrl;
	
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
     *	机构产品佣金率
     */
	private BigDecimal orgFeeRatio;
	
    /**
     *	收费类型 
     *	1:cpa-按投资人数量进行收费
     *	2:cps-按投资金额进行收费
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
	 * 收费模式
	 */
	private List<CimOrgFeeRecord> orgFeeRecords;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgAccount() {
		return orgAccount;
	}

	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}

	public String getOrgPassword() {
		return orgPassword;
	}

	public void setOrgPassword(String orgPassword) {
		this.orgPassword = orgPassword;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIcpFiling() {
		return icpFiling;
	}

	public void setIcpFiling(String icpFiling) {
		this.icpFiling = icpFiling;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPlatformIco() {
		return platformIco;
	}

	public void setPlatformIco(String platformIco) {
		this.platformIco = platformIco;
	}

	public String getPlatformlistIco() {
		return platformlistIco;
	}

	public void setPlatformlistIco(String platformlistIco) {
		this.platformlistIco = platformlistIco;
	}

	public String getPlatformDetailImg() {
		return platformDetailImg;
	}

	public void setPlatformDetailImg(String platformDetailImg) {
		this.platformDetailImg = platformDetailImg;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getHomepageSort() {
		return homepageSort;
	}

	public void setHomepageSort(Integer homepageSort) {
		this.homepageSort = homepageSort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getMinProfit() {
		return minProfit;
	}

	public void setMinProfit(BigDecimal minProfit) {
		this.minProfit = minProfit;
	}

	public BigDecimal getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(BigDecimal maxProfit) {
		this.maxProfit = maxProfit;
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

	public Integer getMinDeadLine() {
		return minDeadLine;
	}

	public void setMinDeadLine(Integer minDeadLine) {
		this.minDeadLine = minDeadLine;
	}

	public Integer getMaxDeadLine() {
		return maxDeadLine;
	}

	public void setMaxDeadLine(Integer maxDeadLine) {
		this.maxDeadLine = maxDeadLine;
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

	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public String getOrgProductUrl() {
		return orgProductUrl;
	}

	public void setOrgProductUrl(String orgProductUrl) {
		this.orgProductUrl = orgProductUrl;
	}

	public String getOrgUsercenterUrl() {
		return orgUsercenterUrl;
	}

	public void setOrgUsercenterUrl(String orgUsercenterUrl) {
		this.orgUsercenterUrl = orgUsercenterUrl;
	}

	public String getOrgBindUserUrl() {
		return orgBindUserUrl;
	}

	public void setOrgBindUserUrl(String orgBindUserUrl) {
		this.orgBindUserUrl = orgBindUserUrl;
	}

	public String getOrgUserbalanceUrl() {
		return orgUserbalanceUrl;
	}

	public void setOrgUserbalanceUrl(String orgUserbalanceUrl) {
		this.orgUserbalanceUrl = orgUserbalanceUrl;
	}

	public String getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(String trusteeship) {
		this.trusteeship = trusteeship;
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

	public BigDecimal getOrgFeeRatio() {
		return orgFeeRatio;
	}

	public void setOrgFeeRatio(BigDecimal orgFeeRatio) {
		this.orgFeeRatio = orgFeeRatio;
	}

	public Integer getOrgFeeType() {
		return orgFeeType;
	}

	public void setOrgFeeType(Integer orgFeeType) {
		this.orgFeeType = orgFeeType;
	}

	public BigDecimal getOrgAmountLimit() {
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

	public List<CimOrgMemberInfo> getTeams() {
		return teams;
	}

	public void setTeams(List<CimOrgMemberInfo> teams) {
		this.teams = teams;
	}

	public List<CimOrgFeeRecord> getOrgFeeRecords() {
		return orgFeeRecords;
	}

	public void setOrgFeeRecords(List<CimOrgFeeRecord> orgFeeRecords) {
		this.orgFeeRecords = orgFeeRecords;
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
