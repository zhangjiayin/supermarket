package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GenerateRedPacketRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5977577602306733331L;
	
	private Long id;//id
	private String fid;// fid
	private Date initDate;// 初始时间
	private Date updateDate;// 更新时间
	private String activityId;// 活动ID
	private String activityName;// 活动名称
	private String productId;// 产品ID
	private String productName;// 产品名称
	private Integer productDeadline;//产品期限
	private Integer createCondition;//生成条件 : 0=不限|1=按产品编号|10=按产品期限并等于产品期限|11=按产品期限并大于等于产品期限
	private String redPaperRuleJson;// 红包规则 格式[{min:金额,max:金额,redPaperType:[{金额,类型id,busType:(1=投资返现|2=现金红包|3=抵现红包)}]}]
	private Integer day;// 有效天数
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductDeadline() {
		return productDeadline;
	}
	public void setProductDeadline(Integer productDeadline) {
		this.productDeadline = productDeadline;
	}
	public Integer getCreateCondition() {
		return createCondition;
	}
	public void setCreateCondition(Integer createCondition) {
		this.createCondition = createCondition;
	}
	public String getRedPaperRuleJson() {
		return redPaperRuleJson;
	}
	public void setRedPaperRuleJson(String redPaperRuleJson) {
		this.redPaperRuleJson = redPaperRuleJson;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	
	
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
