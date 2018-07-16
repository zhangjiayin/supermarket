package com.linkwee.api.activity.response;

public class UserWinningRecordResponse {

	/**
	 * 奖品ID
	 */
	private int prizeId;
	/**
	 * 奖品描述
	 */
	private String prizeDesc;
	/**
	 * 中奖时间
	 */
	private String winningTime;
	/**
	 * 是否幸运奖
	 */
	private int isFortunePrize;
	
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
	public String getWinningTime() {
		return winningTime;
	}
	public void setWinningTime(String winningTime) {
		this.winningTime = winningTime;
	}
	public int getIsFortunePrize() {
		return isFortunePrize;
	}
	public void setIsFortunePrize(int isFortunePrize) {
		this.isFortunePrize = isFortunePrize;
	}
}
