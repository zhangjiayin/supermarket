package com.xiaoniu.account.utils.enums;

/**
 * 
 * @Description: 交易类型
 * @author 周锋恒
 * @date 2015年7月30日
 *
 */
public enum TransTypeEnum {

	USER_IN				(13, 	"充值"),
	INVEST				(2, 	"投资"),
	
	ADJUST				(6,		"校正"),
	RED_BAG_IN			(7, 	"红包返现"), 
	SYSTEN_IN			(8, 	"系统充值"),
	ACTIVITY_IN			(9, 	"活动佣金"), 
	PROFIT				(10, 	"返利息"), 
	ACTIVE				(14, 	"活动奖励"),	 //可用投资增加
	//AMOUNT_RETURN		(11, 	"回款本金"), 
	//PROFIT_RETURN		(12, 	"回款利息")
	;
	
	
	private Integer transType;
	
	private String transTypeDesc;

	private TransTypeEnum(int transType, String transTypeDesc) {
		this.transType = transType;
		this.transTypeDesc = transTypeDesc;
	}
	
	public Integer getTransType() {
		return transType;
	}

	public String getTransTypeDesc() {
		return transTypeDesc;
	}

	public static TransTypeEnum getEnum(Integer transType) {
		TransTypeEnum[] arr = TransTypeEnum.values();
		for (TransTypeEnum tmp : arr) {
			if (transType == tmp.getTransType()) {
				return tmp;
			}
		}
		return null;
	}


}
