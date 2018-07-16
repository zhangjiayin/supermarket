package com.xiaoniu.sms.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * 推广消息
 * @author 颜彩云
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SpreadMessageRlt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3772227698733123177L;
	private Long spreadMessageId;//主键id
	private String source;//来源
	private String title;//标题
	private String content;//内容
	private Integer type;// 类型，1为html页面类型，2为指定url类型
	private Integer status;//是否已读，1为未读，2为已读
	private Date createTime;//创建时间
	private Integer isTop;//是否置顶，0为不置顶，1为置顶

	public Long getSpreadMessageId() {
		return spreadMessageId;
	}

	public void setSpreadMessageId(Long spreadMessageId) {
		this.spreadMessageId = spreadMessageId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
