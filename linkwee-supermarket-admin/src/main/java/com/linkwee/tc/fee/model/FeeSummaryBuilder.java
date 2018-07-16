package com.linkwee.tc.fee.model;

import java.math.BigDecimal;
import java.util.Date;

import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;

public class FeeSummaryBuilder {
	 /**
	    *结算编号(年+月)
	    */
		private String bizId;
		
	   /**
	    *年
	    */
		private String year;
		
	   /**
	    *月
	    */
		private String moth;
		
		
	   /**
	    *总人数
	    */
		private Integer totalNumber;
		
	   /**
	    *总佣金
	    */
		private BigDecimal totalProfit;
		
	   /**
	    *获取佣金人数
	    */
		private Integer feeProfitNumber;
		
	   /**
	    *佣金
	    */
		private BigDecimal feeProfit;
		
	   /**
	    *获取推荐奖励人数
	    */
		private Integer recommendProfitNumer;
		
	   /**
	    *推荐津贴
	    */
		private BigDecimal recommendProfit;
		
	   /**
	    *获取直接管理津贴人数
	    */
		private Integer childManagementProfitNumber;
		
	   /**
	    *直接管理津贴
	    */
		private BigDecimal childManagementProfit;
		
	   /**
	    *获取团队管理津贴人数
	    */
		private Integer teamManagementProfitNumer;
		
	   /**
	    *团队管理津贴
	    */
		private BigDecimal teamManagementProfit;
		
	   /**
	    *月均佣金
	    */
		private BigDecimal avgFeeProfit;
		
	   /**
	    *创建时间
	    */
		private Date createTime;
		
	   /**
	    *更新时间
	    **/
		private Date updateTime;
		
	   /**
	    *操作人
	    */
		private String operator;
		
		  /**
	     *基础加拥(平台加佣)
	     */
		private BigDecimal feeProfitAdd;
		
	    /**
	     *基础加拥人数(平台加佣)
	     */
		private Integer feeProfitAddNumber;
		
	    /**
	     *获取推荐加拥人数(平台加佣)
	     */
		private Integer recommendProfitAddNumer;
		
	    /**
	     *推荐加拥(平台加佣)
	     */
		private BigDecimal recommendProfitAdd;
		
	    /**
	     *基础加拥(个人加佣)
	     */
		private BigDecimal personFeeAdd;
		
	    /**
	     *基础加拥人数(个人加佣)
	     */
		private Integer personFeeAddNumber;
		
	    /**
	     *保险获取佣金人数
	     */
		private Integer insuranceFeeProfitNumber;
		
	    /**
	     *保险佣金
	     */
		private BigDecimal insuranceFeeProfit;
		
	    /**
	     *保险获取推荐奖励人数
	     */
		private Integer insuranceRecommendProfitNumer;
		
	    /**
	     *保险推荐津贴
	     */
		private BigDecimal insuranceRecommendProfit;
		
	    /**
	     *保险获取直接管理津贴人数
	     */
		private Integer insuranceChildManagementProfitNumber;
		
	    /**
	     *保险直接管理津贴
	     */
		private BigDecimal insuranceChildManagementProfit;
		
	    /**
	     *保险获取团队管理津贴人数
	     */
		private Integer insuranceTeamManagementProfitNumer;
		
	    /**
	     *保险团队管理津贴
	     */
		private BigDecimal insuranceTeamManagementProfit;
		
		public static FeeSummaryBuilder create() {
	        return new FeeSummaryBuilder();
	    }
		
		public FeeSummaryBuilder baseInfo(String bizId, String year,String moth, Date createTime, Date updateTime, String operator) {
			this.bizId = bizId;
			this.year = year;
			this.moth = moth;
			this.createTime = createTime;
			this.updateTime = updateTime;
			this.operator = operator;
			return this;
		}
		
		public FeeSummaryBuilder baseFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.feeProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.feeProfitNumber = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder recommendFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.recommendProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.recommendProfitNumer = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder childManagementFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.childManagementProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.childManagementProfitNumber = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder teamManagementFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.teamManagementProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.teamManagementProfitNumer = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder baseFeeAdd(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.feeProfitAdd = currentMonthPayFeeStatistics.getFeeAmt();
			this.feeProfitAddNumber = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder recommendFeeAdd(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.recommendProfitAdd = currentMonthPayFeeStatistics.getFeeAmt();
			this.recommendProfitAddNumer = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder personFeeAdd(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.personFeeAdd = currentMonthPayFeeStatistics.getFeeAmt();
			this.personFeeAddNumber = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder insuranceBaseFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.insuranceFeeProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.insuranceFeeProfitNumber = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder insuranceRecommendFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.insuranceRecommendProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.insuranceRecommendProfitNumer = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder insuranceChildManagementFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.insuranceChildManagementProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.insuranceChildManagementProfitNumber = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder insuranceTeamManagementFee(CurrentMonthPayFeeStatistics currentMonthPayFeeStatistics){
			this.insuranceTeamManagementProfit = currentMonthPayFeeStatistics.getFeeAmt();
			this.insuranceTeamManagementProfitNumer = currentMonthPayFeeStatistics.getNumber();
			return this;
		}
		
		public FeeSummaryBuilder statisticsFee(){
			this.totalProfit = feeProfit.add(recommendProfit).add(childManagementProfit).add(teamManagementProfit).add(feeProfitAdd).add(recommendProfitAdd).add(personFeeAdd).add(insuranceFeeProfit).add(insuranceRecommendProfit).add(insuranceChildManagementProfit).add(insuranceTeamManagementProfit);
			this.totalNumber = feeProfitNumber+recommendProfitNumer+childManagementProfitNumber+teamManagementProfitNumer+feeProfitAddNumber+recommendProfitAddNumer+personFeeAddNumber+insuranceFeeProfitNumber+insuranceRecommendProfitNumer+insuranceChildManagementProfitNumber+insuranceTeamManagementProfitNumer;
			this.avgFeeProfit = totalProfit .divide(new BigDecimal(totalNumber), 4, BigDecimal.ROUND_DOWN);
			return this;
		}
		
		public TCFeeSummary builder(){
			return new TCFeeSummary(bizId, year, moth, totalNumber, totalProfit, feeProfitNumber, feeProfit, recommendProfitNumer, recommendProfit, childManagementProfitNumber, childManagementProfit, teamManagementProfitNumer, teamManagementProfit, feeProfitAdd, feeProfitAddNumber, recommendProfitAddNumer, recommendProfitAdd, personFeeAddNumber, personFeeAdd, insuranceFeeProfitNumber, insuranceFeeProfit, insuranceRecommendProfitNumer, insuranceRecommendProfit, insuranceChildManagementProfitNumber, insuranceChildManagementProfit, insuranceTeamManagementProfitNumer, insuranceTeamManagementProfit, avgFeeProfit, createTime, updateTime, operator);
		}
		
}
