package com.xiaoniu.account.utils.enums;

/**
 * 
 * @author zhoufengheng
 *
 */
public enum OutRecordStatus {

	STATUS_INIT			("0", 	"提现记录"),
	STATUS_APPLY		("1", 	"待审核，资金已冻结"),
	STATUS_APPLY_OK		("2", 	"已审核，等待打款"),
	STATUS_APPLY_FAIL	("3", 	"审核不通过，已解冻"),	//终态
	STATUS_FROZEN_FAIL	("4", 	"冻结失败"),				//终态
	STATUS_SUCCESS		("5", 	"提现成功"),				//终态
	STATUS_SETTLE_FAIL	("6", 	"打款失败，需要解冻或查询打款结果"),
	STATUS_UNFREEZE		("7", 	"打款失败，已解冻"),		//终态
	STATUS_PROCESSING	("8", 	"打款处理中")	
	;
	
	private String type;
	private String desc;

	private OutRecordStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static OutRecordStatus getEnum(String code) {
		OutRecordStatus[] arr = OutRecordStatus.values();
		for (OutRecordStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}

}
