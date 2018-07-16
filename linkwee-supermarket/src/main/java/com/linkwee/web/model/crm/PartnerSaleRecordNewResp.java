package com.linkwee.web.model.crm;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.api.response.crm.MonthSaleListAllowanceListResponse;
import com.linkwee.core.base.BaseEntity;

/**
 * 团队成员销售记录3.0
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class PartnerSaleRecordNewResp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 业务时间
	 */
	private Date bizDate;
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 销售额年化
	 */
	private Double yearpurAmount;
	/**
	 * 销售额
	 */
	private Double purAmount;
	
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
	
	/**
	 * 级别描述
	 */
	private int lowType;
	
	/**
	 * billId
	 */
	private String billId;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Double getYearpurAmount() {
		return yearpurAmount;
	}

	public void setYearpurAmount(Double yearpurAmount) {
		this.yearpurAmount = yearpurAmount;
	}

	public Double getPurAmount() {
		return purAmount;
	}

	public void setPurAmount(Double purAmount) {
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

	public int getLowType() {
		return lowType;
	}

	public void setLowType(int lowType) {
		this.lowType = lowType;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}


}
