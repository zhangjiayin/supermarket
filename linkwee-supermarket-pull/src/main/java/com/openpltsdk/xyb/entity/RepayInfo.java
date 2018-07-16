package com.openpltsdk.xyb.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：RepayInfo
 * 类描述：回款信息
 * @author zenggang
 * @date 创建时间：2017年1月11日 上午11:07:20
 * 修改人：zenggang
 * 修改时间：2017年1月11日 上午11:07:20
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class RepayInfo {
	/**
	 * id:订单ID
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:09:57
	 * @version V1.3.1
	 */
	private String id;
	/**
	 * investId:投资订单ID
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:10:04
	 * @version V1.3.1
	 */
	private String investId;
	/**
	 * bid:标的id
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:10:12
	 * @version V1.3.1
	 */
	private String bid;
	/**
	 * username:三方平台用户名。后续依此进行关联
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:10:24
	 * @version V1.3.1
	 */
	private String username;
	/**
	 * amount:回款金额
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:10:55
	 * @version V1.3.1
	 */
	private BigDecimal amount;
	/**
	 * rate:回款利率
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:11:08
	 * @version V1.3.1
	 */
	private BigDecimal rate;
	/**
	 * income:回款收益
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:11:14
	 * @version V1.3.1
	 */
	private BigDecimal income;
	/**
	 * type:回款类型：0:普通回款，1:转让回款
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:11:20
	 * @version V1.3.1
	 */
	private int type;
	/**
	 * repayAt:回款时间
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:11:26
	 * @version V1.3.1
	 */
	private Date repayAt;
	/**
	 * tags:标签
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:11:34
	 * @version V1.3.1
	 */
	private String[] tags;
	
	/**
	 * userId:领会金融超市用户唯一标识
	 * @author zenggang
	 * @date 创建时间：2017年1月13日 下午7:21:32
	 * @version V1.3.1
	 */
	private String userId;
	
	/**
	 * status:回款状态=2 ：需进行多次回款操作时的状态, 此状态下最后一笔回款状态需要修改为3
	 * @author zenggang
	 * @date 创建时间：2017年1月13日 下午7:21:46
	 * @version V1.3.1
	 */
	private String status;
	
	
	public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
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
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getRepayAt() {
		return repayAt;
	}
	public void setRepayAt(Date repayAt) {
		this.repayAt = repayAt;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
