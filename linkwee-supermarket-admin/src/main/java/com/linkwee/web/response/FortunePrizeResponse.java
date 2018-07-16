package com.linkwee.web.response;

public class FortunePrizeResponse {
	/**
	 * 轮次
	 */
	private int roundTime;
	/**
	 * 点数
	 */
	private int count;
	/**
	 * 奖品ID
	 */
	private int prizeId;
	/**
	 * 奖品描述
	 */
	private String prizeDesc;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 中奖时间
	 */
	private String winningTime;
	/**
	 * 是否使用奖励金 1：奖励金 2：猎财余额
	 */
	private Integer isUseBounty;
	
	public int getRoundTime() {
		return roundTime;
	}
	public void setRoundTime(int roundTime) {
		this.roundTime = roundTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}
	public String getPrizeDesc() {
		return prizeDesc;
	}
	public void setPrizeDesc(String prizeDesc) {
		this.prizeDesc = prizeDesc;
	}
	
	public FortunePrizeResponse() {
		super();
	}
	public FortunePrizeResponse(int roundTime, int count, int prizeId,
			String prizeDesc) {
		super();
		this.roundTime = roundTime;
		this.count = count;
		this.prizeId = prizeId;
		this.prizeDesc = prizeDesc;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWinningTime() {
		return winningTime;
	}
	public void setWinningTime(String winningTime) {
		this.winningTime = winningTime;
	}
	public Integer getIsUseBounty() {
		return isUseBounty;
	}
	public void setIsUseBounty(Integer isUseBounty) {
		this.isUseBounty = isUseBounty;
	}
}
