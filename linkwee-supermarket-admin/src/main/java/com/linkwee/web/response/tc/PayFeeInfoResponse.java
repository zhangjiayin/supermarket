package com.linkwee.web.response.tc;

import java.math.BigDecimal;

import com.linkwee.core.base.BaseEntity;

public class PayFeeInfoResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143948803346118470L;

	private String billId;
	
	   /**
     *理财师手机号码
     */
	private String userMobile;
	
    /**
     *理财师姓名
     */
	private String userName;
	
	/**
     *佣金
     */
	private BigDecimal feeAmount;
	
	/**
     *年月
     */
	private String month;
	
    /**
     *0:默认; 1:发放中 2:发放成功; 3:发放失败
     */
	private Integer status;
	
	/**
	 * 佣金类型：1001=佣金|1002=推荐津贴|1005=直接管理津贴|1006=团队管理津贴|1011=基础加拥（平台加佣券）|1012=推荐加拥（平台加佣券）|1021=个人加佣（个人加佣券）
	 */
	private String type;
	
    /**
     *产品类型  0-网贷 1-保险
     */
	private Integer productClassify;
	
    /**
     *产品类型字符串  0-网贷 1-保险
     */
	private String productClassifyStr;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFeeAmount() {
		return feeAmount.setScale(4,BigDecimal.ROUND_HALF_UP).toString();
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStatusStr() {
		String statusStr="-";
		switch (status) {
		case 0:
			statusStr = "未发放";
			break;
		case 2:
			statusStr = "已发放";
			break;
		}
		return statusStr;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTypeStr() {
		String typeStr="-";
		if("0".equals(type)){
			typeStr = "老数据";
		}else if("1001".equals(type)){
			typeStr = "基础佣金";
		}else if("1002".equals(type)){
			typeStr = "推荐奖励";
		}else if("1005".equals(type)){
			typeStr = "直接管理津贴";
		}else if("1006".equals(type)){
			typeStr = "团队管理津贴";
		}else if("1011".equals(type)){
			typeStr = "基础加拥(平台)";
		}else if("1012".equals(type)){
			typeStr = "推荐加拥(平台)";
		}else if("1021".equals(type)){
			typeStr = "个人加拥";
		}
		return typeStr;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getProductClassify() {
		return productClassify;
	}

	public void setProductClassify(Integer productClassify) {
		this.productClassify = productClassify;
	}

	public String getProductClassifyStr() {
		if(productClassify == 0){
			productClassifyStr = "网贷";
		} else if(productClassify == 1){
			productClassifyStr = "保险";
		}
		return productClassifyStr;
	}

	public void setProductClassifyStr(String productClassifyStr) {
		this.productClassifyStr = productClassifyStr;
	}
}
