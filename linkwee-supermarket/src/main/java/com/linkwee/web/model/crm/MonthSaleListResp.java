package com.linkwee.web.model.crm;

import java.util.Date;
import java.util.List;

import com.linkwee.api.response.crm.MonthSaleListAllowanceListResponse;
import com.linkwee.core.base.BaseEntity;

/**
 * 团队销售列表
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class MonthSaleListResp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 时间
	 */
	private Date bizTime;
	/**
	 *产品名称
	 */
	private String productName;
	/**
	 * 销售金额
	 */
	private Double SaleAmount;
	
	/**
	 * 推荐奖励
	 */
	private Double feeAmount;
	
	/**
	 * 佣金率
	 */
	private Double feeRate;
	
	/**
	 * 推荐奖励类别：1直接；2间接
	 */
	private int type;
	
	/**
	 * 头像
	 */
	private String headImage;
	

	/**
	 * 电话号码
	 */
	private String mobile;
	
	/**
	 * leader奖励 
	 */
	private Double leaderProfit;
	
	/**
	 * 津贴明细
	 */
	private List<MonthSaleListAllowanceListResponse> allowanceList;
	
	/**
	 * 销售理财师属于 收益人向下级别
	 */
	private Integer lowType;
	
	/**
	 * 受益人的直接下级
	 */
	private String directChildren;
	
	/**
	 * 受益人的直接下级
	 */
	private String directChildrenMobile;
	
	/**
	 * billId
	 */
	private String billId;
	

	public Double getLeaderProfit() {
		return leaderProfit;
	}

	public void setLeaderProfit(Double leaderProfit) {
		this.leaderProfit = leaderProfit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBizTime() {
		return bizTime;
	}

	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getSaleAmount() {
		return SaleAmount;
	}

	public void setSaleAmount(Double saleAmount) {
		SaleAmount = saleAmount;
	}

	public Double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public Integer getLowType() {
		return lowType;
	}

	public void setLowType(Integer lowType) {
		this.lowType = lowType;
	}

	public String getDirectChildren() {
		return directChildren;
	}

	public void setDirectChildren(String directChildren) {
		this.directChildren = directChildren;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getDirectChildrenMobile() {
		return directChildrenMobile;
	}

	public void setDirectChildrenMobile(String directChildrenMobile) {
		this.directChildrenMobile = directChildrenMobile;
	}

	
	


}
