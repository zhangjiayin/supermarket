package com.linkwee.web.model.cim;

import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.enums.cim.OrgGradeEnum;
import com.linkwee.web.model.CimOrgRiskManageSynthesize;

public class OrgRiskManage extends CimOrgRiskManageSynthesize{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *机构名称
     */
	private String orgName;
    /**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private String grade;
    /**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private String gradeStr;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGradeStr() {
		gradeStr = EnumUtils.getKvmEnumByValue(grade, OrgGradeEnum.values()).getMsg();
		return gradeStr;
	}
	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}
}
