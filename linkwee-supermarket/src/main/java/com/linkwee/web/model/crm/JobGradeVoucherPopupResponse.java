package com.linkwee.web.model.crm;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 4.5.0职级体验券
 * 
 * @Date 2017年10月17日 下午5:36:22
 */
public class JobGradeVoucherPopupResponse extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;
	
	public JobGradeVoucherPopupResponse() {
	}
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 职级
	 */
	private String jobGrade;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}
	
}
