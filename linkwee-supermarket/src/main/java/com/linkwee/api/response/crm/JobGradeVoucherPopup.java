package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 4.5.0职级体验券弹出框
 * 
 * @Date 2017年10月17日 下午5:36:22
 */
public class JobGradeVoucherPopup extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;

	/**
	 * 是否有新的体验券
	 */
	private Boolean haveNewJobGrade = false;
	
	/**
	 * 职级
	 */
	private String jobGrade;

	public Boolean getHaveNewJobGrade() {
		return haveNewJobGrade;
	}

	public void setHaveNewJobGrade(Boolean haveNewJobGrade) {
		this.haveNewJobGrade = haveNewJobGrade;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

}
