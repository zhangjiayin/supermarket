package com.xiaoniu.sms.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * 系统消息
 * @author 颜彩云
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SystemMessageRlt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3772227698733123177L;
	private Long systemMessageId;//主键id
	private String userId;//用户id
	private Long userIdLong;//用户id long
	private String title;//标题
	private String content;//内容
	private Integer status;//状态，1为正常，2为已删除
	private Integer isRead;//是否已读，1为未读，2为已读
	private Date createTime;//创建时间

	public Long getSystemMessageId() {
		return systemMessageId;
	}

	public void setSystemMessageId(Long systemMessageId) {
		this.systemMessageId = systemMessageId;
	}

	/**
	 * 用户id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 用户id Long（预留）
	 * @return
	 */
	public Long getUserIdLong() {
		return userIdLong;
	}

	public void setUserIdLong(Long userIdLong) {
		this.userIdLong = userIdLong;
	}
	/**
	 * 系统消息标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 系统消息内容
	 * @return
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 状态（1为正常，2为已删除）
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 是否已读（1为未读，2为已读）
	 * @return
	 */
	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * 创建时间
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
