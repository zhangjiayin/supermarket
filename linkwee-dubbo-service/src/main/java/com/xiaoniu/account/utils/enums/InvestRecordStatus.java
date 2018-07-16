package com.xiaoniu.account.utils.enums;

/**
 * 
 * @author zhoufengheng
 *
 */
public enum InvestRecordStatus {

	STATUS_INIT			("0", "初始状态"),
	STATUS_OK			("1", "投资成功"),
	STATUS_FAIL			("2", "投资失败"),	//
	STATUS_RETURN		("3", "赎回状态"),
	STATUS_RETURN_OVER	("4", "全部赎回"),	//
	;
	
	private String type;
	private String desc;

	private InvestRecordStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static InvestRecordStatus getEnum(String code) {
		InvestRecordStatus[] arr = InvestRecordStatus.values();
		for (InvestRecordStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}

}
