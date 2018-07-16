package com.linkwee.api.response.crm;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.crm.MonthSaleListResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 团队销售列表
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class MonthSaleListNewResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public MonthSaleListNewResponse() {
	}

	public MonthSaleListNewResponse(MonthSaleListResp obj) {
		WebUtil.initObj(this, obj);
		this.setBizTime(DateUtils.format(obj.getBizTime(),DateUtils.FORMAT_MM));
		this.setAllowanceList(obj.getAllowanceList());
		if(obj.getLowType() != null && obj.getLowType() == 1) {
			this.setGradeDesc("下级理财师");
		} else if(obj.getLowType() != null && obj.getLowType() == 2) {
			this.setGradeDesc("下下级理财师");
		} else {
			this.setGradeDesc("");
		}
		if(obj.getLowType() != 0 ){
			this.setUserName(obj.getDirectChildren());
			this.setMobile(obj.getDirectChildrenMobile());
		}
		
		
	}
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 时间
	 */
	private String bizTime;
	/**
	 *产品名称
	 */
	private String productName;
	/**
	 * 销售金额
	 */
	private String SaleAmount;
	
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 电话号码
	 */
	private String mobile;
	
	/**
	 * 津贴明细
	 */
	private List<MonthSaleListAllowanceListResponse> allowanceList;
	
	/**
	 * 级别描述
	 */
	private String gradeDesc;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBizTime() {
		return bizTime;
	}

	public void setBizTime(String bizTime) {
		this.bizTime = bizTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSaleAmount() {
		return SaleAmount;
	}

	public void setSaleAmount(String saleAmount) {
		SaleAmount = saleAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<MonthSaleListAllowanceListResponse> getAllowanceList() {
		return allowanceList;
	}

	public void setAllowanceList(List<MonthSaleListAllowanceListResponse> allowanceList) {
		this.allowanceList = allowanceList;
	}

	public String getGradeDesc() {
		return gradeDesc;
	}

	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}

	


}
