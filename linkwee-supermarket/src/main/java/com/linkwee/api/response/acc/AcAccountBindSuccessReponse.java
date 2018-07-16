package com.linkwee.api.response.acc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.linkwee.api.request.acc.AddBankCardRequest;
import com.linkwee.core.util.WebUtil;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class AcAccountBindSuccessReponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public AcAccountBindSuccessReponse(){
		
	}
	
	public AcAccountBindSuccessReponse(AddBankCardRequest obj){
		WebUtil.initObj(this,obj);
//		this.setBankCard(obj.getBankCard()!=null?"****************"+obj.getBankCard().substring(obj.getBankCard().length()-4):null);
//		this.setBankName(obj.getBankName());
//		this.setIdCard(obj.getIdCard()!=null?"**** **** **** "+obj.getIdCard().substring(obj.getIdCard().length()-4):null);
//		this.setUserName(obj.getUserName());
//		this.setMobile(obj.getMobile()!=null?
//						obj.getMobile().substring(0, 3)+"****"+obj.getMobile().substring(obj.getMobile().length()-4, obj.getMobile().length()):null);
	    this.setRemark(obj.getBankName()!=null?obj.getBankName()+"(尾号"+obj.getBankCard().substring(obj.getBankCard().length()-4)+")":"");
	    this.setHaveBind(obj.getHaveBind());
	}
	
//	/**
//	 * 银行卡号
//	 */
//	private String bankCard;
//	
//	/**
//	 * 银行名称
//	 */
//	private String bankName;
//	
//	/**
//	 * 真实姓名
//	 */
//	private String userName;
//	
//	/**
//	 * 身份证号
//	 */
//	private String idCard;
//	
//	/**
//	 * 预留手机号码
//	 */
//	private String mobile;
	
	/**
	 * 绑卡成功返回信息
	 */
	private String remark;
	
	/**
	 * 是否绑过卡 0否 1绑定过
	 * */
	private String haveBind;


	public String getHaveBind() {
		return haveBind;
	}

	public void setHaveBind(String haveBind) {
		this.haveBind = haveBind;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString()
	{
	  return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}