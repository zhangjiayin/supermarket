package com.xiaoniu.account.utils.enums;

/**
 * 
 * @author zhoufengheng
 *
 */
public enum InvestOrderStatus {

	STATUS_INIT				("0", "初始状态"),
	STATUS_OK				("1", "预售成功"),
	STATUS_INVEST_SUCCESS	("2", "投资成功"),	//
	STATUS_CANCEL			("3", "取消订单"),
	;
	
	private String type;
	private String desc;

	private InvestOrderStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static InvestOrderStatus getEnum(String code) {
		InvestOrderStatus[] arr = InvestOrderStatus.values();
		for (InvestOrderStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}

}
