package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.cim.OrgGradeEnum;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 14:20:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrginfo implements Serializable {
	
	private static final long serialVersionUID = 7782033589578297641L;
	
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
	 * 产品期限
	 */
	private String deadLineValueText;
	
	/**
	 * 团队信息
	 */
	private List<CimOrgMemberInfo> teams; //= new ArrayList<CimOrgMemberInfo>()
	
	/**
	 * 平台下可投的产品数量
	 */
	private Integer usableProductNums;
	
	/**
	 * pc端 优质平台广告图片
	 */
	private String orgAdvertisement;
	
	/**
	 * pc端 优质平台广告图片跳转链接
	 */
	private String orgAdvertisementUrl;
	
	/**
     *是否虚拟机构/未对接机构 (1：是 ,0：否)
     */
	private Integer orgIsstaticproduct;
	
    /**
     *机构亮点介绍(多个以英文逗号分隔)
     */
	private String orgAdvantage;
	
    /**
     *机构标签(多个以英文逗号分隔)
     */
	private String orgTag;
	
    /**
     *投呗端产品自定义标签(多个以英文逗号分隔)
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
	 * 机构活动宣传图
	 */
	private List<ActivityList> orgActivitys;
	
	/**
	 * 投呗端平台自定义标签(多个以英文逗号分隔)
	 */
	private String orgInvestTag;
	
	/**
	 * 猎财端平台自定义标签(多个以英文逗号分隔)
	 */
	private String orgPlannerTag;
	
	/**
     *猎财端产品自定义标签(多个以英文逗号分隔)
     */
	private String orgPlannerProductTag;
	
	/**
	 * 对接的机构类型(0:移动+PC端，1:移动端，2:PC端)
	 */
	private Integer orgJointType;
	
	/**
	 * 机构灰度状态(0:否，1:是)
	 */
	private Integer orgGrayStatus;
	
	/**
	 * 最新入驻平台图片(PC端)
	 */
	private String orgNewestImg;
	
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
	 * 是否有红包
	 */
	private boolean hashRedpacket;
    /**
     *列表推荐，是否列表推荐 0-不推荐、1-推荐
     */
	private Integer listRecommend;
	
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
	
    /**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private String gradeStr;

	public boolean isHashRedpacket() {
		return hashRedpacket;
	}

	public void setHashRedpacket(boolean hashRedpacket) {
		this.hashRedpacket = hashRedpacket;
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

	public String getOrgNewestImg() {
		return orgNewestImg;
	}

	public void setOrgNewestImg(String orgNewestImg) {
		this.orgNewestImg = orgNewestImg;
	}

	public Integer getOrgGrayStatus() {
		return orgGrayStatus;
	}

	public void setOrgGrayStatus(Integer orgGrayStatus) {
		this.orgGrayStatus = orgGrayStatus;
	}

	public Integer getOrgJointType() {
		return orgJointType;
	}

	public void setOrgJointType(Integer orgJointType) {
		this.orgJointType = orgJointType;
	}

	public String getOrgPlannerProductTag() {
		return orgPlannerProductTag;
	}

	public void setOrgPlannerProductTag(String orgPlannerProductTag) {
		this.orgPlannerProductTag = orgPlannerProductTag;
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

	public List<ActivityList> getOrgActivitys() {
		return orgActivitys;
	}

	public void setOrgActivitys(List<ActivityList> orgActivitys) {
		this.orgActivitys = orgActivitys;
	}

	public String getOrgAdvertisementUrl() {
		return orgAdvertisementUrl;
	}

	public void setOrgAdvertisementUrl(String orgAdvertisementUrl) {
		this.orgAdvertisementUrl = orgAdvertisementUrl;
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

	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}

	public Integer getUsableProductNums() {
		return usableProductNums;
	}

	public void setUsableProductNums(Integer usableProductNums) {
		this.usableProductNums = usableProductNums;
	}

	public String getDeadLineValueText() {
		if (minDeadLine != null  && maxDeadLine != null && minDeadLine.equals(maxDeadLine)){
			if(StringUtils.isNotBlank(deadLineMinSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined;
			} else {
				deadLineValueText = minDeadLine+"天";
			}
		} else {
			if(StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined+"~"+deadLineMaxSelfDefined;
			} else {
				deadLineValueText = minDeadLine+"天~"+maxDeadLine+"天";
			}
		}
		
		return StringUtils.separateNumberChinese(deadLineValueText, ",");
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
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
	
	public void setPlatformIco(String platformIco){
		this.platformIco = platformIco;
	}
	
	public String getPlatformIco(){
		return platformIco;
	}
	
	public void setPlatformlistIco(String platformlistIco){
		this.platformlistIco = platformlistIco;
	}
	
	public String getPlatformlistIco(){
		return platformlistIco;
	}
	
	public void setPlatformDetailImg(String platformDetailImg){
		this.platformDetailImg = platformDetailImg;
	}
	
	public String getPlatformDetailImg(){
		return platformDetailImg;
	}
	
	public void setBusinessLicense(String businessLicense){
		this.businessLicense = businessLicense;
	}
	
	public String getBusinessLicense(){
		return businessLicense;
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

	// 在往set，map容器中加入元素时，每次都会执行该函数。根据该函数的执行结果再决定是否要执行equals.  
	@Override
	public int hashCode() {
		//return super.hashCode();
		return 0; // 如果这里统一返回0，则一定会执行equals.
	}

	 // 自定义去重的依据，不光要重载equals，还需要重载hashcode  
    // 去重时（发生在往set，map中加入数据时），先获取hashcode,如果hashcode与容器中现有的hashcode不重复，则不再执行equals，而直接认为该key尚未加入到容器中；  
    // 如果hashcode与容器中现有的hashcode重复，再去执行equals,以equals的结果为准。  
	@Override
	public boolean equals(Object obj) {
		CimOrginfo org = (CimOrginfo) obj;
		if(org.getOrgNumber().equals(this.orgNumber)){
			return true;
		}else{
			return false;
		}
		// return obj instanceof CimOrginfo && this.orgNumber != null && this.orgNumber.equals(((CimOrginfo)obj).getOrgNumber()); 
	}

	public Integer getListRecommend() {
		return listRecommend;
	}

	public void setListRecommend(Integer listRecommend) {
		this.listRecommend = listRecommend;
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

	public String getGradeStr() {
		gradeStr = EnumUtils.getKvmEnumByValue(grade, OrgGradeEnum.values()).getMsg();
		return gradeStr;
	}

	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}
}

