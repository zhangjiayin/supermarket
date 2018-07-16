package com.linkwee.api.activity.response;

public class OneYuanDrawRecordResponse {

	/**
     *中奖投资人手机号码
     */
	private String mobile;
	
    /**
     *中奖等级
     */
	private Integer winningOrder;
	
    /**
     *等级描述
     */
	private String orderDesc;
	
    /**
     *抽奖方式 1：抽一次 10：抽十次
     */
	private Integer drawType;
	
	/**
	 * 信息类型 1：抽奖记录 2：十连抽信息
	 */
	private Integer msgType;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getWinningOrder() {
		return winningOrder;
	}

	public void setWinningOrder(Integer winningOrder) {
		this.winningOrder = winningOrder;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Integer getDrawType() {
		return drawType;
	}

	public void setDrawType(Integer drawType) {
		this.drawType = drawType;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
}
