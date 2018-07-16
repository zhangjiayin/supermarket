package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.xoss.util.WebUtil;

/**
 * 4.5.0职级体验券列表
 * 
 * @Date 2017年10月17日 下午5:36:22
 */
public class JobGradeVoucherResponse extends BaseEntity {
	private static final long serialVersionUID = 7581132087714889997L;
	
	public JobGradeVoucherResponse() {
	}

	public JobGradeVoucherResponse(JobGradeVoucherResponse obj){
		WebUtil.initObj(this, obj);
		this.setActivityAttr(obj.getActivityAttr());
		this.setVoucherType(obj.getVoucherType());
		this.setVoucherName(obj.getVoucherName());
		this.setJobGradeWelfare1(obj.getJobGradeWelfare1());
		this.setJobGradeWelfare2(obj.getJobGradeWelfare2());
		this.setApplyPlatform(obj.getApplyPlatform());
		this.setUseTime(obj.getUseTime());
		this.setExpiresTime(obj.getExpiresTime());
		this.setStatus(obj.getStatus());
	}
	

	/**
	 * 活动属性
	 */
	private String activityAttr;
	
	/**
	 * 券类型	
	 */
	private String voucherType;		
	
	/**
	 * 券名称
	 */
	private String voucherName;			
	
	/**
	 * 职级福利描述1		
	 */
	private String jobGradeWelfare1;	
    
	/**
	 * 职级福利描述2
	 */
	private String jobGradeWelfare2;
	
	/**
	 * 适用平台
	 */
	private String applyPlatform;
	
	/**
	 * 使用时间
	 */
	private String useTime;
	
	/**
	 * 过期时间
	 */
	private String expiresTime;
	
	/**
	 * 状态  2=未使用|3=已使用|4=已过期
	 */
	private String status;

	public String getActivityAttr() {
		return activityAttr;
	}

	public void setActivityAttr(String activityAttr) {
		this.activityAttr = activityAttr;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public String getJobGradeWelfare1() {
		return jobGradeWelfare1;
	}

	public void setJobGradeWelfare1(String jobGradeWelfare1) {
		this.jobGradeWelfare1 = jobGradeWelfare1;
	}

	public String getJobGradeWelfare2() {
		return jobGradeWelfare2;
	}

	public void setJobGradeWelfare2(String jobGradeWelfare2) {
		this.jobGradeWelfare2 = jobGradeWelfare2;
	}

	public String getApplyPlatform() {
		return applyPlatform;
	}

	public void setApplyPlatform(String applyPlatform) {
		this.applyPlatform = applyPlatform;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(String expiresTime) {
		this.expiresTime = expiresTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
