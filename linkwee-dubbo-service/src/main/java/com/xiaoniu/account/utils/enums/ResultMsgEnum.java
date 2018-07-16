package com.xiaoniu.account.utils.enums;

/**
 * 
 * @Description: 通用返回信息
 * @author 周锋恒
 * @date 2015年7月24日
 *
 */
public enum ResultMsgEnum {

	SUCCESS				(0, 		"成功"),
	PARAM_ERROR			(101001, 	"参数错误"), 
	SIGN_ERROR			(101002, 	"签名错误"),
	
	DATA_VERSION_ERROR	(102001, 	"数据版本问题"), 
	NO_INFO				(102002, 	"数据不存在"),
	STATUS_NOT_CORRENT	(102003,	"数据状态不正确"),
	USER_EXIST			(102004, 	"用户资产账户存在"),
	CREATE_USER_FAILE	(102005, 	"创建用户资产账户失败"),
	DATA_NOT_SAME		(102006, 	"数据不一致"),
	USER_NOT_SAME		(102007,	"用户不一致"),
	
	AMOUNT_NOT_ENOUGH	(103001, 	"金额不足"), 
	RETURN_AMOUNT_ERROR	(103002, 	"回款本金大于投资本金"),
	RETURN_ING			(103003, 	"打款处理中"),
	SETTLE_FAILE		(103004, 	"打款失败，需要解冻或查询打款结果"),
	IN_ALREADY_SUCCESS	(103005, 	"订单充值已成功"),
	ALREADY_SUCCESS		(103006, 	"投资或回款已成功"),
	
	SYSTEM_ERROR		(109999, 	"系统异常"), 
	;
	
	
	private Integer returnCode;
	private String returnMsg;

	private ResultMsgEnum(Integer returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}
	
	public Integer getReturnCode() {
		return returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public static ResultMsgEnum getEnum(Integer errorCode) {
		ResultMsgEnum[] arr = ResultMsgEnum.values();
		for (ResultMsgEnum tmp : arr) {
			if (errorCode == tmp.getReturnCode()) {
				return tmp;
			}
		}
		return SUCCESS;
	}

}
