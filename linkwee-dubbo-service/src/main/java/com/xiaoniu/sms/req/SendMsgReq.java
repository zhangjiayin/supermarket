package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 发送短信 接口参数
 * @author 颜彩云
 *
 */
public class SendMsgReq extends BaseReq implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8155425081372647364L;
	
	private String mobile;// 接收信息的号码，多个以英文逗号（,）分隔，最多100个
	private String value = "";//替换值，多个以英文逗号分隔
	private String content;
	private String customContent;//自定义内容，以json形式传入，不支持嵌套多级
	private int type = 0;
	
	public SendMsgReq(){
		
	}
	
	public SendMsgReq(String partnerId,String moduleId,String mobile, String content) {
		super.setPartnerId(partnerId);
		super.setModuleId(moduleId);
		this.mobile = mobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCustomContent() {
		return customContent;
	}

	public void setCustomContent(String customContent) {
		this.customContent = customContent;
	}

	public boolean check(){
		if(StringUtils.isBlank(mobile)){ 
			return false;
		}
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		if (StringUtils.isBlank(super.getModuleId())) {
			return false;
		}
		return true;
	}

}
