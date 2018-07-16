package com.xiaoniu.account.utils.enums;

/**
 * 
 * @Description: 账户类型
 * @author 李攀文
 * @date 2016年6月3日
 *
 */
public enum AccountTypeEnum {

	INVEST			(1, 	"投资人"),
	LOAN			(2, 	"借贷人"),
	;


	private Integer type;

	private String desc;

	private AccountTypeEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static AccountTypeEnum getEnum(Integer type) {
		AccountTypeEnum[] arr = AccountTypeEnum.values();
		for (AccountTypeEnum tmp : arr) {
			if (type == tmp.getType()) {
				return tmp;
			}
		}
		return null;
	}


}
