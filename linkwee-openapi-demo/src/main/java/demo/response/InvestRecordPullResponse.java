package demo.response;

import java.math.BigDecimal;
/**
 * 投资记录
 * @author ch
 * @serial 2016-07-25 17:32:02
 *
 */
public class InvestRecordPullResponse{

	/**
	 * 投资编号
	 */
	//@NotBlank(message="投资编号不能为空")
	//@Length(min=1, max=32, message="投资编号长度必须在1-64位之间")  
	private String investId;
	
	private String txId;
	
	/**
	 * 平台编号
	 */
	private String platfromId;
	
	/**
	 * 用户编号
	 */
	//@NotBlank(message="用户编号不能为空")
	//@Length(min=1, max=64, message="用户编号长度必须为1-64位之间")
	private String userId;
	
	/**
	 * 产品编号
	 */
	//@NotBlank(message="产品编号不能为空")
	//@Length(min=1, max=64, message="产品编号长度必须为1-64位之间")
	private String productId;
	
	
	
	/**
	 * 产品购买日期
	 */
	//@NotNull(message="产品购买日期不能为空")

	private String investStartTime;
	
	/**
	 * 产品回款日期
	 */
	//@NotNull(message="产品回款日期不能为空")
	//@Future
	private String investEndTime;
	
	/**
	 * 投资金额
	 */
	//@NotNull(message="投资金额不能为空")
	//@Min(value=1, message="投资金额必须大于0")
	private BigDecimal investAmount;
	
	
	//@NotNull(message="预期收益不能为空")
	//@Min(value=0, message="预期收益必须大于等于0")
	private BigDecimal profit;


	public String getInvestId() {
		return investId;
	}


	public void setInvestId(String investId) {
		this.investId = investId;
	}


	public String getPlatfromId() {
		return platfromId;
	}


	public void setPlatfromId(String platfromId) {
		this.platfromId = platfromId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getInvestStartTime() {
		return investStartTime;
	}


	public void setInvestStartTime(String investStartTime) {
		this.investStartTime = investStartTime;
	}


	public String getInvestEndTime() {
		return investEndTime;
	}


	public void setInvestEndTime(String investEndTime) {
		this.investEndTime = investEndTime;
	}


	public BigDecimal getInvestAmount() {
		return investAmount;
	}


	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}


	public BigDecimal getProfit() {
		return profit;
	}


	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}


	public String getTxId() {
		return txId;
	}


	public void setTxId(String txId) {
		this.txId = txId;
	}

	
}
