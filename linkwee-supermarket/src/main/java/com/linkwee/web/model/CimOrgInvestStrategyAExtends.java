package com.linkwee.web.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.web.response.OrgFeeStrategyAResponse;

public class CimOrgInvestStrategyAExtends extends CimOrgInvestStrategyA{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private List<OrgFeeStrategyAResponse> feeCalculateStrategyList;
	
	public BigDecimal getReturnCashAmount() {
		returnCashAmount = new BigDecimal("0");
		if(feeCalculateStrategyList != null && feeCalculateStrategyList.size() > 0){
			for(OrgFeeStrategyAResponse feeStrategy : feeCalculateStrategyList){
				if(feeStrategy.getFeeStrategy() == 1){
					returnCashAmount = feeStrategy.getFeeVal();
				}else if(feeStrategy.getFeeStrategy() == 2){
					returnCashAmount = getMinInvestAmount().multiply(feeStrategy.getFeeRatio()).divide(new BigDecimal(100));
				}else if(feeStrategy.getFeeStrategy() == 3){
					if(getMinDeadLine().compareTo(feeStrategy.getIntervalMinVal().toBigInteger().intValue()) >= 0 && getMinDeadLine().compareTo(feeStrategy.getIntervalMaxVal().toBigInteger().intValue()) <= 0){
						returnCashAmount = getMinInvestAmount().multiply(feeStrategy.getFeeRatio()).divide(new BigDecimal(100));
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
		manageAmount = getMinInvestAmount().multiply(getOrgProductRatio()).divide(new BigDecimal(100)).divide(new BigDecimal(360),BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(getMinDeadLine()));
		return manageAmount;
	}

	public void setManageAmount(BigDecimal manageAmount) {
		this.manageAmount = manageAmount;
	}

	public BigDecimal getMaxFeeRatio() {
		maxFeeRatio = (getReturnCashAmount().add(getManageAmount()).add(getOrgRedpacket())).divide(getMinInvestAmount(),BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100)).multiply(new BigDecimal(360)).divide(new BigDecimal(getMinDeadLine()),BigDecimal.ROUND_HALF_DOWN);
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
}
