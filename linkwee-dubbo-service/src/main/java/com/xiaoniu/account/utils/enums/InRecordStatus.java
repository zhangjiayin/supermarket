package com.xiaoniu.account.utils.enums;

/**
 * 
 * @author zhoufengheng
 *
 */
public enum InRecordStatus {

	STATUS_ORDER("0", "下单成功，等待充值"),		//首次支付时发送短信和直接下单
	STATUS_VALID("1", "短信验证，等待充值"),	
	STATUS_OK	("2", "充值成功"),
	STATUS_FAIL	("3", "充值失败"),
	;
	
	private String type;
	private String desc;

	private InRecordStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static InRecordStatus getEnum(String code) {
		InRecordStatus[] arr = InRecordStatus.values();
		for (InRecordStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}

}
