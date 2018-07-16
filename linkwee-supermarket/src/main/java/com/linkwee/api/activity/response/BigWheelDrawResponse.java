package com.linkwee.api.activity.response;

import java.util.List;

import com.linkwee.api.response.activity.FortunePrizeResponse;

public class BigWheelDrawResponse {
	/**
	 * 中奖奖项id
	 */
	Integer prizeId;
	/**
	 * 剩余抽奖次数
	 */
	Integer leftTimes;
	/**
	 * 奖项id列表
	 */
	List<Integer> prizeIdList;
	/**
	 * 幸运奖
	 */
	FortunePrizeResponse fortunePrizeResponse;
	
	public Integer getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}
	public Integer getLeftTimes() {
		return leftTimes;
	}
	public void setLeftTimes(Integer leftTimes) {
		this.leftTimes = leftTimes;
	}
	public List<Integer> getPrizeIdList() {
		return prizeIdList;
	}
	public void setPrizeIdList(List<Integer> prizeIdList) {
		this.prizeIdList = prizeIdList;
	}
	public FortunePrizeResponse getFortunePrizeResponse() {
		return fortunePrizeResponse;
	}
	public void setFortunePrizeResponse(FortunePrizeResponse fortunePrizeResponse) {
		this.fortunePrizeResponse = fortunePrizeResponse;
	}
	
}
