package com.linkwee.api.response.cim;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.web.model.*;

public class OrginfoaDetailResponse extends CimOrgInfoA {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A专区机构投资攻略表
	 */
	List<CimOrgInvestStrategyAExtends> cimOrgInvestStrategyAExtendsList;
	
	/**
	 * banner活动
	 */
	List<ActivityList> activityLists;

	/**
	 * 团队成员信息
	 */
	private List<CimOrgMemberinfoA> teamInfos;

	/**
	 * 机构证件图片信息
	 */
	private List<CimOrgPicture> orgPapersList;

	/**
	 * 机构环境图片信息
	 */
	private List<CimOrgPicture> orgEnvironmentList;

	/**
	 * 最大返现金额
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal maxCashBack;

	public List<CimOrgInvestStrategyAExtends> getCimOrgInvestStrategyAExtendsList() {
		return cimOrgInvestStrategyAExtendsList;
	}

	public void setCimOrgInvestStrategyAExtendsList(
			List<CimOrgInvestStrategyAExtends> cimOrgInvestStrategyAExtendsList) {
		this.cimOrgInvestStrategyAExtendsList = cimOrgInvestStrategyAExtendsList;
	}

	public List<ActivityList> getActivityLists() {
		return activityLists;
	}

	public void setActivityLists(List<ActivityList> activityLists) {
		this.activityLists = activityLists;
	}

	public List<CimOrgMemberinfoA> getTeamInfos() {
		return teamInfos;
	}

	public void setTeamInfos(List<CimOrgMemberinfoA> teamInfos) {
		this.teamInfos = teamInfos;
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

	public BigDecimal getMaxCashBack() {
		if(getCimOrgInvestStrategyAExtendsList() != null && getCimOrgInvestStrategyAExtendsList().size() > 0){
			BigDecimal temp = getCimOrgInvestStrategyAExtendsList().get(0).getReturnCashAmount();
			for(CimOrgInvestStrategyAExtends strategyAExtends : getCimOrgInvestStrategyAExtendsList()){
				if(strategyAExtends.getReturnCashAmount().compareTo(temp) >= 0){
					temp = strategyAExtends.getReturnCashAmount();
				}
			}
			return temp;
		}else{
			return new BigDecimal("0.00");
		}
	}

	public void setMaxCashBack(BigDecimal maxCashBack) {
		this.maxCashBack = maxCashBack;
	}
}
