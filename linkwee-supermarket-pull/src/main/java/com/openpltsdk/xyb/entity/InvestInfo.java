package com.openpltsdk.xyb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class InvestInfo {
	private String id;
	private String bid;
	private String burl;
	private String username;
	private BigDecimal amount;
	/**
	 * actualAmount:实际投资金额
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 下午2:16:06
	 * @version V1.3.1
	 */
	private BigDecimal actualAmount;
	/**
	 * income:预期投资收益
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 下午2:06:11
	 * @version V1.3.1
	 */
	private BigDecimal income;
	/**
	 * investAt:投资时间
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 下午2:15:39
	 * @version V1.3.1
	 */
	private Date investAt;
	/**
	 * status:投资状态(0:还款中, 1:已还清)
	 * @author zenggang
	 * @date 创建时间：2017年1月3日 下午2:59:59
	 * @version V2.1.9
	 */
	private int status;
	
	/**
	 * userId:领会金融超市用户唯一标识
	 * @author zenggang
	 * @date 创建时间：2017年1月13日 下午7:19:59
	 * @version V1.3.1
	 */
	private String userId;
	
	/**
	 * txId:交易流水号
	 * @author zenggang
	 * @date 创建时间：2017年1月13日 下午7:20:19
	 * @version V1.3.1
	 */
	private String txId;
	
	private String[] tags;
	
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getTxId()
    {
        return txId;
    }
    public void setTxId(String txId)
    {
        this.txId = txId;
    }
   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBurl() {
		return burl;
	}
	public void setBurl(String burl) {
		this.burl = burl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public Date getInvestAt() {
		return investAt;
	}
	public void setInvestAt(Date investAt) {
		this.investAt = investAt;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
