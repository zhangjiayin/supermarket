package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 投资下单请求参数
 * @author 周锋恒
 * 
 * @Date 2015年12月2日
 */
public class ConfirmInvestOrderReq extends InvestRecordReq {

	private static final long serialVersionUID = -8240154407883431175L;
	
	/** 认筹订单号 ，多个以 "," 格开  */
	@NotNull
	private String orderNos;
	
	/** 红包金额 */
	@NotNull
	private Long redPaperTotalAmount;
		
	/** 手续费 */
	@NotNull
	private Long fee;
	
	/** 操作， cancel:取消认筹，success:认筹成功  */
	@NotNull
	private String operate;

	/** 是否扣除本金 Y:扣除，N：不扣除*/
	private String principalOperate;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getOrderNos() {
		return orderNos;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Long getRedPaperTotalAmount() {
		return redPaperTotalAmount;
	}

	public void setRedPaperTotalAmount(Long redPaperTotalAmount) {
		this.redPaperTotalAmount = redPaperTotalAmount;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}

	public String getPrincipalOperate() {
		return principalOperate;
	}

	public void setPrincipalOperate(String principalOperate) {
		this.principalOperate = principalOperate;
	}
}
