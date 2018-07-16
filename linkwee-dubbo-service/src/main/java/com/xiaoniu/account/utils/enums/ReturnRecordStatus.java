package com.xiaoniu.account.utils.enums;

/**
 * 
 * @author zhoufengheng
 *
 */
public enum ReturnRecordStatus {

	STATUS_INIT	("0", "初始状态"),
	STATUS_OK	("1", "回款成功"),
	STATUS_FAIL	("2", "回款失败"),
	;
	
	private String type;
	private String desc;

	private ReturnRecordStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static ReturnRecordStatus getEnum(String code) {
		ReturnRecordStatus[] arr = ReturnRecordStatus.values();
		for (ReturnRecordStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}

}
