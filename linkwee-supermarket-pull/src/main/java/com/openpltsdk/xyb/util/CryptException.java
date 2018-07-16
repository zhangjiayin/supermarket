package com.openpltsdk.xyb.util;

public class CryptException extends Exception {
	private static final long serialVersionUID = -2125031025287319917L;
	
	public final static int OK = 0;
	public final static int MISSING_SERVICE_NAME = 101;
	public final static int UNKNOWN_SERVICE_ERROR = 102;
	public final static int VALIDATE_SIGNATURE_ERROR = 103;
	public final static int VALIDATE_TIMESTAMP_ERROR = 104;
	public final static int VALIDATE_APPID_ERROR = 105;
	public final static int PARSE_JSON_ERROR = 106;
	public final static int GEN_RETURN_MSG_ERROR = 107;
	public final static int COMPUTE_SIGNATURE_ERROR = 108;
	public final static int ENCRYPT_AES_ERROR = 109;
	public final static int DECRYPT_AES_ERROR = 110;
	public final static int INVALID_PARAMETER = 201;
	public final static int USER_NOT_EXISTS = 202;
	public final static int START_GREAT_THAN_END = 203;
	public final static int TIME_RANGE_EXCEED = 204;
	public final static int QUERY_ITEM_COUNT_EXCEED = 205;
	public final static int APPLICATION_ERROR = 500;
	public final static int TELEPHONE_HAVE_USED = 1001;
	public final static int EMAIL_HAVE_USED = 1002;
	public final static int IDCARD_HAVE_USED = 1003;
	public final static int USERNAME_USED = 1004;

	private int code;

	private static String getMessage(int code) {
		switch (code) {
		case MISSING_SERVICE_NAME:
			return "缺少 Service Name";
		case UNKNOWN_SERVICE_ERROR:
			return "Service Name不存在";
		case VALIDATE_SIGNATURE_ERROR:
			return "签名验证失败";
		case VALIDATE_TIMESTAMP_ERROR:
			return "时间戳过期";
		case VALIDATE_APPID_ERROR:
			return "AppID校验失败";
		case PARSE_JSON_ERROR:
			return "JSON反序列化出错";
		case GEN_RETURN_MSG_ERROR:
			return "生成返回包失败";
		case COMPUTE_SIGNATURE_ERROR:
			return "生成签名失败";
		case ENCRYPT_AES_ERROR:
			return "加密失败";
		case DECRYPT_AES_ERROR:
			return "解密失败";
		case INVALID_PARAMETER:
			return "请求参数出错";
		case USER_NOT_EXISTS:
			return "用户不存在";
		case START_GREAT_THAN_END:
			return "startTime 不能大于endTime";
		case TIME_RANGE_EXCEED:
			return "时间查询跨度不能超过1个月";
		case QUERY_ITEM_COUNT_EXCEED:
			return "查询项数量过多";
		case APPLICATION_ERROR:
			return "系统异常";
		case TELEPHONE_HAVE_USED:
			return "手机号已占用";
		case EMAIL_HAVE_USED:
			return "邮箱号已占用";
		case IDCARD_HAVE_USED:
			return "身份证号已占用";
		case USERNAME_USED:
			return "用户名已绑定";
		default:
			return null; // cannot be
		}
	}

	public int getCode() {
		return code;
	}

	public CryptException(int code) {
		super(getMessage(code));
		this.code = code;
	}
}
