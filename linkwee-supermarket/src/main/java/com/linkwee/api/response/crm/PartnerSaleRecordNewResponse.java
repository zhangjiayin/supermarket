package com.linkwee.api.response.crm;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.crm.PartnerSaleRecordNewResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 团队成员销售记录3.0
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class PartnerSaleRecordNewResponse extends BaseEntity {
	private static final long serialVersionUID = -5418637411733755341L;

	public PartnerSaleRecordNewResponse() {

	}

	public PartnerSaleRecordNewResponse(PartnerSaleRecordNewResp obj) {
		WebUtil.initObj(this, obj);
		this.setBizDate(DateUtils.format(obj.getBizDate(), DateUtils.FORMAT_MM));
		this.setAllowanceList(obj.getAllowanceList());
		if( obj.getLowType() == 1) {
			this.setGradeDesc("下级理财师");
		} else if( obj.getLowType() == 2) {
			this.setGradeDesc("下下级理财师");
		} else {
			this.setGradeDesc("");
		}
	}

	/**
	 * 业务时间
	 */
	private String bizDate;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 销售额
	 */
	private String purAmount;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 级别描述
	 */
	private String gradeDesc;
	/**
	 * 津贴明细
	 */
	private List<MonthSaleListAllowanceListResponse> allowanceList;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPurAmount() {
		return purAmount;
	}

	public void setPurAmount(String purAmount) {
		this.purAmount = purAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGradeDesc() {
		return gradeDesc;
	}

	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}

	public List<MonthSaleListAllowanceListResponse> getAllowanceList() {
		return allowanceList;
	}

	public void setAllowanceList(List<MonthSaleListAllowanceListResponse> allowanceList) {
		this.allowanceList = allowanceList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
