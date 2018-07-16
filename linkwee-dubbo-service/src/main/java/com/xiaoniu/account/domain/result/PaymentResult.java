package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.xiaoniu.account.utils.enums.ResultMsgEnum;


public class PaymentResult implements Serializable{

	private static final long serialVersionUID = 3669920862469772246L;

	/** 返回信息 */
	private Integer error_no;
	
	/** 返回信息 */
	private String error_msg;
	
	/** 平台交易单号 */
	private Long provider_payment_id;
	
	/** 订单处理状态  */
	private String status;
	
	/** 第三方失败错误码 */
	private String fail_code;
	
	/** 失败的原因，第三方返回的原因。 */
	private String fail_detail;
	
	/**  支付返回的处理时间  */
	private String return_time;
	
	/** 业务编号 */
	private Integer partner_id;
	
	/** 支付渠道ID  */
	private Integer provider_partner_id;
	
	private String partner_trade_no;
	
	private String buyer_uin;
	
	private Long total_amount;
	
	private String attach;
	
	private String sign_type;
	
	private String input_charset;
	
	private String sign;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public PaymentResult() {
		
	}
	
	public PaymentResult(Integer error_no, String error_msg) {
		this.error_no = error_no;
		this.error_msg = error_msg;
	}
	
	public PaymentResult(ResultMsgEnum errorMsgEnum) {
		this.error_no = errorMsgEnum.getReturnCode();
		this.error_msg = errorMsgEnum.getReturnMsg();
	}

	public String getStatus() {
		return status;
	}

	public String getFail_code() {
		return fail_code;
	}

	public void setFail_code(String fail_code) {
		this.fail_code = fail_code;
	}

	public String getReturn_time() {
		return return_time;
	}

	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}

	public String getPartner_trade_no() {
		return partner_trade_no;
	}

	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProvider_payment_id() {
		return provider_payment_id;
	}

	public void setProvider_payment_id(Long provider_payment_id) {
		this.provider_payment_id = provider_payment_id;
	}

	public String getFail_detail() {
		return fail_detail;
	}

	public void setFail_detail(String fail_detail) {
		this.fail_detail = fail_detail;
	}

	public Integer getError_no() {
		return error_no;
	}

	public void setError_no(Integer error_no) {
		this.error_no = error_no;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public Integer getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(Integer partner_id) {
		this.partner_id = partner_id;
	}

	public Integer getProvider_partner_id() {
		return provider_partner_id;
	}

	public void setProvider_partner_id(Integer provider_partner_id) {
		this.provider_partner_id = provider_partner_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	public String getBuyer_uin() {
		return buyer_uin;
	}

	public void setBuyer_uin(String buyer_uin) {
		this.buyer_uin = buyer_uin;
	}

	public Long getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Long total_amount) {
		this.total_amount = total_amount;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}
	
}
