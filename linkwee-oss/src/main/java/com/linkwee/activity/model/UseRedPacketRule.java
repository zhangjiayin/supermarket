package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class UseRedPacketRule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8260418502568494047L;
	
	private Long id;//id
	private String fid;// fid
	private Date initDate;// 初始时间
	private Date updateDate;// 更新时间
	private String activityId;// 活动ID
	private String activityName;// 活动名称
	private String productId;// 产品ID
	private String productName;// 产品名称
	private Integer productDeadline;//产品期限
	private Integer useCondition;//使用条件 : 1000=不限|1001=按产品编号|1002=按产品期限并等于产品期限|1003=按产品期限并大于等于产品期限
	private String redPaperRuleJson;// 红包规则 格式[{min:金额,max:金额,redpacketMoney:红包金额,redpacketTypeId:红包类型,busType:(1=投资返现|2=现金红包|3=抵现红包)}]
	
	public UseRedPacketRule() {}

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

	public Integer getUseCondition() {
		return useCondition;
	}

	public void setUseCondition(Integer useCondition) {
		this.useCondition = useCondition;
	}

	public String getRedPaperRuleJson() {
		return redPaperRuleJson;
	}

	public void setRedPaperRuleJson(String redPaperRuleJson) {
		this.redPaperRuleJson = redPaperRuleJson;
	}
	
	
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
