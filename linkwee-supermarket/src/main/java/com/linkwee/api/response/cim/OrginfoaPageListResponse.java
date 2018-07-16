package com.linkwee.api.response.cim;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.response.OrgFeeStrategyAResponse;

public class OrginfoaPageListResponse extends CimOrgInfoA {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *是否推荐 0-不推荐、1-推荐
     */
	private Integer recommend;
	
    /**
     *机构投资攻略排名
     */
	private Integer strategyIndex;
	
    /**
     *起投期限
     */
	private Integer minDeadLine;
	
    /**
     *起投金额(元)
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal minInvestAmount;
	
    /**
     *官方红包(元)
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal orgRedpacket;
	
    /**
     *机构理财收益率
     */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal orgProductRatio;
	
	/**
	 * 返现收益
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal returnCashAmount;
	
	/**
	 * 理财收益
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal manageAmount;
	
	/**
	 * 最大综合年化收益率
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal maxFeeRatio;

	/**
	 * 投资策略数量
	 */
	private Integer investStrategySize;

	private List<OrgFeeStrategyAResponse> feeCalculateStrategyList;
	
	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getStrategyIndex() {
		return strategyIndex;
	}

	public void setStrategyIndex(Integer strategyIndex) {
		this.strategyIndex = strategyIndex;
	}

	public Integer getMinDeadLine() {
		return minDeadLine;
	}

	public void setMinDeadLine(Integer minDeadLine) {
		this.minDeadLine = minDeadLine;
	}

	public BigDecimal getMinInvestAmount() {
		return minInvestAmount;
	}

	public void setMinInvestAmount(BigDecimal minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}

	public BigDecimal getOrgRedpacket() {
		return orgRedpacket;
	}

	public void setOrgRedpacket(BigDecimal orgRedpacket) {
		this.orgRedpacket = orgRedpacket;
	}

	public BigDecimal getOrgProductRatio() {
		return orgProductRatio;
	}

	public void setOrgProductRatio(BigDecimal orgProductRatio) {
		this.orgProductRatio = orgProductRatio;
	}

	public BigDecimal getReturnCashAmount() {
		returnCashAmount = new BigDecimal("0");
		if(feeCalculateStrategyList != null && feeCalculateStrategyList.size() > 0){
			for(OrgFeeStrategyAResponse feeStrategy : feeCalculateStrategyList){
				if(feeStrategy.getFeeStrategy() == 1){
					returnCashAmount = feeStrategy.getFeeVal();
				}else if(feeStrategy.getFeeStrategy() == 2){
					returnCashAmount = minInvestAmount.multiply(feeStrategy.getFeeRatio()).divide(new BigDecimal(100));
				}else if(feeStrategy.getFeeStrategy() == 3){
					if(minDeadLine.compareTo(feeStrategy.getIntervalMinVal().toBigInteger().intValue()) >= 0 && minDeadLine.compareTo(feeStrategy.getIntervalMaxVal().toBigInteger().intValue()) <= 0){
						returnCashAmount = minInvestAmount.multiply(feeStrategy.getFeeRatio()).divide(new BigDecimal(100));
					}
				}
			}
		}
		return returnCashAmount;
	}

	public void setReturnCashAmount(BigDecimal returnCashAmount) {
		this.returnCashAmount = returnCashAmount;
	}

	public BigDecimal getManageAmount() {
		manageAmount = minInvestAmount.multiply(orgProductRatio).divide(new BigDecimal(100)).divide(new BigDecimal(360),BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(minDeadLine));
		return manageAmount;
	}

	public void setManageAmount(BigDecimal manageAmount) {
		this.manageAmount = manageAmount;
	}

	public BigDecimal getMaxFeeRatio() {
		maxFeeRatio = (getReturnCashAmount().add(getManageAmount()).add(orgRedpacket)).divide(minInvestAmount,BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100)).multiply(new BigDecimal(360)).divide(new BigDecimal(minDeadLine),BigDecimal.ROUND_HALF_DOWN);
		return maxFeeRatio;
	}

	public void setMaxFeeRatio(BigDecimal maxFeeRatio) {
		this.maxFeeRatio = maxFeeRatio;
	}

    public List<OrgFeeStrategyAResponse> getFeeCalculateStrategyList() {
        return feeCalculateStrategyList;
    }

    public void setFeeCalculateStrategyList(List<OrgFeeStrategyAResponse> feeCalculateStrategyList) {
        this.feeCalculateStrategyList = feeCalculateStrategyList;
    }

	public Integer getInvestStrategySize() {
		return investStrategySize;
	}

	public void setInvestStrategySize(Integer investStrategySize) {
		this.investStrategySize = investStrategySize;
	}
}
