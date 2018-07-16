package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 系统充值请求参数
 * @author 周锋恒
 * @date 2015年7月29日
 *
 */
public class SystemInRecordReq  implements Serializable {

	private static final long serialVersionUID = -3165246967948030321L;

	/** 业务编号 */
	@NotNull
	private String partnerId;
	
	/** 业务流水 */
	@NotNull
	private String partnerTradeNo;
	
	/** 用户编号 */
	@NotNull
	private String userId;
	
	/** 用户姓名 */
	private String userName;
	
	/** 充值类型  7:红包返现, 8:系统充值, 9:活动佣金, 10:返利息   14:活动奖励   {@see TransTypeEnum} */
	@NotNull
	private Integer bisType;
	
	/** 充值名称 */
	@NotNull
	private String bisName;
	
	/** 充值金额 单位毫 */
	@NotNull
	private Long amount;
	
	/** 红包ID */
	private String redPaperId;
	
	/** 备注信息，用于资金流水展示   */
	private String remark;
	
	/** 签名编码 utf-8 */
	@NotNull
	private String charset;
	
	/** 签名 */
	@NotNull
	private String sign;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getRedPaperId() {
		return redPaperId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRedPaperId(String redPaperId) {
		this.redPaperId = redPaperId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBisType() {
		return bisType;
	}

	public void setBisType(Integer bisType) {
		this.bisType = bisType;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCharset() {
		return charset;
	}


	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
