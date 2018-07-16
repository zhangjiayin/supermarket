package com.xiaoniu.sms.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * 
 * @author 颜彩云
 *
 */
public class MessageTemplateRlt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3772227698733123177L;
	private String content;//内容
	private Integer type;// 类型, 1为推送消息模板， 2为发送短信模板, 3为微信模板
	private String title;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
