package com.linkwee.web.request.crm;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.web.model.CfpLevelNode;

public class CfplannerJobGradeUpdateRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3251920568328617454L;
	/**
	 * 职级
	 */
	private String jobGrade;
	/**
	 * 分类理财师节点
	 */
	private List<CfpLevelNode> cfpLevelNodes;
	
	public String getJobGrade() {
		return jobGrade;
	}
	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}
	public List<CfpLevelNode> getCfpLevelNodes() {
		return cfpLevelNodes;
	}
	public void setCfpLevelNodes(List<CfpLevelNode> cfpLevelNodes) {
		this.cfpLevelNodes = cfpLevelNodes;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
