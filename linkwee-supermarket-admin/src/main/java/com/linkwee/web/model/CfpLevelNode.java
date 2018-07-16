package com.linkwee.web.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CfpLevelNode {
	//用户Id
	private String userId;
	//父Id
	private String parentId;
	//个人业绩年化
	private BigDecimal personAmount;
	//下级理财师
	private List<CfpLevelNode> childrens;
	//理财师当前计算的职级
	private String level;
	//职级权重
	private int levelWeight;
	//见习数量
	private int probationNum;
	//顾问数量
	private int adviserNum;
	//经理数量
	private int managerNum;
	//总监数量
	private int directorNum;
	//计算的月份
	private int month;
	//理财师上次计算的职级
	private String jobGrade;
	
	public BigDecimal getPersonAmount() {
		return personAmount;
	}
	public void setPersonAmount(BigDecimal personAmount) {
		this.personAmount = personAmount;
	}
	public List<CfpLevelNode> getChildrens() {
		if(childrens == null){
			childrens = new LinkedList<CfpLevelNode>();
		}
		return childrens;
	}
	public void setChildrens(List<CfpLevelNode> childrens) {
		this.childrens = childrens;
	}
	public int getAdviserNum() {
		return adviserNum;
	}
	public void setAdviserNum(int adviserNum) {
		this.adviserNum = adviserNum;
	}
	public int getManagerNum() {
		return managerNum;
	}
	public void setManagerNum(int managerNum) {
		this.managerNum = managerNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public int getDirectorNum() {
		return directorNum;
	}
	public void setDirectorNum(int directorNum) {
		this.directorNum = directorNum;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getJobGrade() {
		return jobGrade;
	}
	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}
	public int getProbationNum() {
		return probationNum;
	}
	public void setProbationNum(int probationNum) {
		this.probationNum = probationNum;
	}
	public int getLevelWeight() {
		return levelWeight;
	}
	public void setLevelWeight(int levelWeight) {
		this.levelWeight = levelWeight;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
