package com.xiaoniu.sms.enums;
/**
 * 错误消息
 * @author 颜彩云
 *
 */
public enum SmsCodeEnum {
	SUCCESS(0,"成功"),
	PARAM_ERROR(130001,"参数为空"),
	MOBILE_ERROR(130002,"手机号码格式错误"),
	SIGN_ERROR(130003,"签名错误"),
	SYSTEM_ERROR(130004,"服务器内部错误"),
	NO_PARTNER(130005,"找不到业务id为[%s]的信息"),
	NO_MODULE(130006,"业务id[%s]下找不到模块id为[%s]的信息"),
	
	CODE_ERROR(130011,"验证码验证失败"),
	CODE_EXPIRE(130012,"验证码已过期"),
	ERROR_NUMBER_LIMIT(130013,"错误次数超过3次"),

	MAX_COUNT(130021,"实际号码个数超过100"),
	SEND_ERROR(130022,"发送失败"),
	NO_TEMPLATE(130023,"请设置短信模板或消息模板"),
	SENSITIVE_FILTER_ERROR(130024,"敏感词过滤"),
	FREQ_CTRL_ERROR(130025,"频率控制"),
	PUSH_ERROR(130031,"推送消息失败"),
	EMAIL_ERROR(130032,"邮件地址格式错误"),
	NO_SMS_PARAM(130033,"请配置发送短信相关数据"),
	RETRY_ERROR(130034,"调用第三方重试%s次仍返回错误，可能是连接超时")
	;
	
	private int returnCode;
	private String returnMsg;
	
	private SmsCodeEnum(int returnCode,String returnMsg){
		this.returnMsg = returnMsg;
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}
	
	public int getReturnCode() {
		return returnCode;
	}
	
	public static SmsCodeEnum get(int returnCode){
		SmsCodeEnum[] arr = SmsCodeEnum.values();
		for(SmsCodeEnum errorCodeEnum:arr){
			if(errorCodeEnum.getReturnCode()==returnCode){
				return errorCodeEnum;
			}
		}
		return null;
	}
}
