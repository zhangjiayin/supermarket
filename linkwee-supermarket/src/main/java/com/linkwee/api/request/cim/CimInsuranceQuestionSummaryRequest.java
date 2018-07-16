package com.linkwee.api.request.cim;

import javax.validation.constraints.NotNull;

import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年12月29日 15:37:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimInsuranceQuestionSummaryRequest extends BaseEntity  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
    /**
     *性别
     */
	@NotNull(message = "性别不能为空")
	private String sex;
	
    /**
     *年龄
     */
	@NotNull(message = "年龄不能为空")
	private String age;
	
    /**
     *家庭成员
     */
	@NotNull(message = "家庭成员不能为空")
	private String familyMember;
	
    /**
     *年收入
     */
	@NotNull(message = "年收入不能为空")
	private String yearIncome;
	
    /**
     *家庭贷款
     */
	@NotNull(message = "家庭贷款不能为空")
	private String familyLoan;
	
    /**
     *家庭保障
     */
	@NotNull(message = "家庭保障不能为空")
	private String familyEnsure;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(String familyMember) {
		this.familyMember = familyMember;
	}

	public String getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(String yearIncome) {
		this.yearIncome = yearIncome;
	}

	public String getFamilyLoan() {
		return familyLoan;
	}

	public void setFamilyLoan(String familyLoan) {
		this.familyLoan = familyLoan;
	}

	public String getFamilyEnsure() {
		return familyEnsure;
	}

	public void setFamilyEnsure(String familyEnsure) {
		this.familyEnsure = familyEnsure;
	}
	
}

