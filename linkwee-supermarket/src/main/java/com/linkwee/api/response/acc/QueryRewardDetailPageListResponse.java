package com.linkwee.api.response.acc;

import java.io.Serializable;
import java.math.BigDecimal;

import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.acc.AcWithdrawApply;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年02月10日 11:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class QueryRewardDetailPageListResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public QueryRewardDetailPageListResponse(){
		
	}
	
	public QueryRewardDetailPageListResponse(AcWithdrawApply obj){
		WebUtil.initObj(this,obj);
		this.setTranName(obj.getBisName());
		this.setUserType(obj.getUserType()==1?"猎财":"T呗");
		this.setTranTime(DateUtils.format(obj.getBisTime(),DateUtils.FORMAT_LONG));
		this.setAmount("-"+NumberUtils.getFormat(obj.getTotalAmount(), "0.00"));
		this.setStatus(obj.getStatus());
		if(obj.getFee().compareTo(BigDecimal.ZERO)==1&&"5".equals(obj.getStatus())){
//			this.setWithdrawRemark("到账金额:"+NumberUtils.getFormat(obj.getAmount(), "0.00")+"元 手续费:1元");
			this.setWithdrawRemark("扣除手续费:1.00元,实际到账:"+NumberUtils.getFormat(obj.getAmount(), "0.00")+"元");
		}else if(obj.getFee().compareTo(BigDecimal.ZERO)==1){
			this.setWithdrawRemark("手续费:1元");
		}else if(obj.getFee().compareTo(BigDecimal.ZERO)==0&&"5".equals(obj.getStatus())){
//			this.setWithdrawRemark("手续费:0元");
		}else{
			this.setWithdrawRemark(obj.getPaymentDate());
		}
		if("6".equals(obj.getStatus())||"7".equals(obj.getStatus())){
			this.setFailureCause(replaceRemark(obj.getRemark()));
		}
		if(obj.getStatus()!=null&&!"".equals(obj.getStatus())){
			this.setRemark(withDrawStatus(obj.getStatus()));
		}
	}
	
	public String replaceRemark(String remark){
		if(remark==null) return null;
		remark = remark.replace("BankErrorMsg:银行返回:[1801]银行返回：[1801]", "");
		remark = remark.replace("BankErrorMsg:银行返回:[CCJ2]银行返回：[CCJ2]", "");
		remark = remark.replace("BankErrorMsg:银行返回：代发失败[GEMS0004CB0042", "");
		remark = remark.replace("BankErrorMsg:", "");
		remark = remark.replace("ErrorMsg:", "");
		remark = remark.replace("]", "");
		return remark;
	}
	
	public String withDrawStatus(String status){
		if("1".equals(status)){
			return "提现中";
		}else if("2".equals(status)||"8".equals(status)){
			return "已提交银行，待到账";
		}else if("3".equals(status)){
			return "审核不通过";
		}else if("5".equals(status)){
			return "提现成功";
		}else if("6".equals(status)||"7".equals(status)){
			return "提现失败";
		}
		return status;
	}
	
	/**
     *用户类型
     */
	private String userType;
	
    /**
     *交易类型
     */
	private String tranName;
	
	/**
     *交易时间
     */
	private String tranTime;
	
	/**
     *金额
     */
	private String amount;
	
	/**
     *备注 
     */
	private String remark;
	
	/**
     *提现状态 
     */
	private String status;
	
	/**
     *提现备注
     */
	private String withdrawRemark;
	
	/**
     *提现失败原因
     */
	private String failureCause;
	
	
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFailureCause() {
		return failureCause;
	}

	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}

	public String getTranName() {
		return tranName;
	}

	public void setTranName(String tranName) {
		this.tranName = tranName;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWithdrawRemark() {
		return withdrawRemark;
	}

	public void setWithdrawRemark(String withdrawRemark) {
		this.withdrawRemark = withdrawRemark;
	}

	public String encrypTion(String str) {
		String  copy = "**************************************";
		StringBuilder  restr = new StringBuilder();
		if(str!=null&&str.length()>7){
			int i = str.length()-7;
			restr = restr.append(str.substring(0, 3)).append(copy.substring(0, i)).append(str.substring(str.length()-4, str.length()));
			return restr.toString();
		}else{
			return str;
		}
	}
	
}

