package com.xiaoniu.sms.enums;
/**
 * 消息模板类型枚举
 * @author 颜彩云
 *
 */
public enum MessageTemplateTypeEnum {
	PUSH(1, "消息推送"), SMS(2, "短信"), WECHAT(3, "微信")
	;
	
	private Integer type;
	private String desc;
	
	private MessageTemplateTypeEnum(Integer type,String desc){
		this.type = type;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
	public Integer getType() {
		return type;
	}
	
	public static MessageTemplateTypeEnum get(Integer type){
		MessageTemplateTypeEnum[] arr = MessageTemplateTypeEnum.values();
		for(MessageTemplateTypeEnum errorCodeEnum:arr){
			if (errorCodeEnum.getType().equals(type)) {
				return errorCodeEnum;
			}
		}
		return null;
	}
}
