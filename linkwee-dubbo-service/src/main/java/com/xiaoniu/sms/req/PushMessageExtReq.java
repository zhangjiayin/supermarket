package com.xiaoniu.sms.req;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 推送消息(扩展) 参数
 * @author 颜彩云
 *
 */
public class PushMessageExtReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315753490753534981L;
	private String customContent;//自定义内容，以json形式传入，不支持嵌套多级
	private String mobile;//手机号码，多个以英文逗号分隔
	private String title = "系统消息";//标题
	private String content;//内容
	private String remark;//
	private List<String> deviceList;
	private boolean insertSystemMessage = true;//默认为保存到系统消息中

	public String getCustomContent() {
		return customContent;
	}

	public void setCustomContent(String customContent) {
		this.customContent = customContent;
	}

	public List<String> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<String> deviceList) {
		this.deviceList = deviceList;
	}

	public boolean isInsertSystemMessage() {
		return insertSystemMessage;
	}

	public void setInsertSystemMessage(boolean insertSystemMessage) {
		this.insertSystemMessage = insertSystemMessage;
	}

	public boolean check(){
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		if (StringUtils.isBlank(content)) {
			return false;
		}
		return true;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
