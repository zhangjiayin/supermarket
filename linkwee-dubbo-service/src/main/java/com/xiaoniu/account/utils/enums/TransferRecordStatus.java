package com.xiaoniu.account.utils.enums;

/**
 * 
 * @author zhoufengheng
 *
 */
public enum TransferRecordStatus {

	STATUS_INIT	("0", "初始状态"),
	STATUS_OK	("1", "转让成功"),
	STATUS_FAIL	("2", "转让失败"),
	;
	
	private String type;
	private String desc;

	private TransferRecordStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static TransferRecordStatus getEnum(String code) {
		TransferRecordStatus[] arr = TransferRecordStatus.values();
		for (TransferRecordStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}

}
