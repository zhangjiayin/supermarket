package cn.xn.user.enums;

public enum ResultMsgEnum {

	SUCCESS					(0, 	"成功"),
	PARAM_ERROR				(110001, 	"参数错误"), 
	FAILURE_DATABASE		(110002, 	"数据库异常"),
	LOGINNAME_NO_EXIST		(110003, 	"登录名不存在"),
	LOGIN_OLDPWD_ERROR		(110004, 	"原登录密码错误"),
	LOGIN_PWD_ERROR			(110005, 	"登录密码错误"),
	PAY_PWD_ERROR			(110006, 	"支付密码错误"),
	PAY_PWD_LOCK			(110007, 	"支付密码输入错误次数已大于最大"),
	LOGIN_PWD_LOCK			(110008, 	"账户已被锁定，请明日0点后再试！"),
	LOGINNAME_EXIST			(110009, 	"登录名已存在"),
	REFEREEUID_NOTEXIST		(110010, 	"推荐人不存在"),
	NO_INFO					(110011, 	"未获取到数据"),
	TOKEN_NOEXIST			(110012, 	"token不存在"),
	STATUS_NOT_CORRENT		(110013,	"数据状态不正确"),
	SIGN_ERROR				(110014,	"签名错误"),
	NOT_BINDING				(110015,	"账户没有绑定"),
	SYSTEM_ERROR			(110016, 	"系统异常"),
	MEMBERNO_NO_EXIST		(110017, 	"用户编码不存在"),
	MEMBER_LOCK				(110018, 	"您的账户因安全问题被冻结,请联系客服核实"),
	LOGIN_ERROR_LEFT		(110019, 	"输入密码错误，剩余%s次机会"),
	UNION_BINDED			(110020, 	"该外部账户已经被绑定"),
	MEMBER_BINDED			(110021, 	"该用户已经绑定了其他外部账户"),
	INFO_EXPIRE				(110022, 	"已经超过了修改时效"),
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

