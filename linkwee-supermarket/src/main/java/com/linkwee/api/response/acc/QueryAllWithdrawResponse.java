package com.linkwee.api.response.acc;

import java.io.Serializable;
import java.math.BigDecimal;

import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.acc.AcWithdrawApply;
 /**
 * 
 * @描述：V2.1.0_提现记录  实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class QueryAllWithdrawResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public QueryAllWithdrawResponse(){
		
	}
	
	public QueryAllWithdrawResponse(AcWithdrawApply obj){
		WebUtil.initObj(this,obj);
		this.setBisName((obj.getUserType()==1?"猎财-":"T呗-")+"提现");
		this.setTransDate(DateUtils.format(obj.getBisTime(),DateUtils.FORMAT_LONG));
		this.setAmount(NumberUtils.getFormat(obj.getAmount(), "0.00"));
		this.setStatus(obj.getStatus());
		if(obj.getFee().compareTo(BigDecimal.ZERO)==1){
			this.setRemark("手续费:1元");
		}else{
			this.setRemark(obj.getPaymentDate());
		}
	}
	
	/**
     *交易名称
     */
	private String bisName;
	
	/**
     *交易日期 
     */
	private String transDate;
	
	/**
     *提现金额
     */
	private String amount;
	
	/**
	 * 提现处理状态
	 * */
	private String status;
	
	/**
     * 备注
     */
	private String remark;

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}

