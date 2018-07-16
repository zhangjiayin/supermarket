package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 获取消息模板 参数
 * @author 颜彩云
 *
 */
public class GetMessageTemplateReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3803206583786777580L;
	private Integer type;//1为消息推送，2为短信，3为微信，见MessageTemplateTypeEnum
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public boolean check(){
		if (type == null) {
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
