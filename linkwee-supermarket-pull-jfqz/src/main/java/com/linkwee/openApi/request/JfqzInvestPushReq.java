package com.linkwee.openApi.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 投资记录
 * @author ch
 * @serial 2018-03-20 17:32:02
 *
 */
public class JfqzInvestPushReq implements Serializable{
	private static final long serialVersionUID = 2985070032905812928L;

	/**
	 * 编码 001:订单创建  002:订单支付  003:订单兑付  006:产品推送  007:债匹成功  008:补单成功  009:退款成功
	 */
	@NotBlank(message="数据签名不能为空")
	private String code;
	
	/**
	 * 推送数据
	 */
	private JfqzInvestRecord jfqzInvestRecord;
	
	/**
	 * 推送原始数据
	 */
	private String data;
	
	/**
	 * 数据签名
	 */
	@NotBlank(message="数据签名不能为空")
	private String sign;

	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public JfqzInvestRecord getJfqzInvestRecord() {
		jfqzInvestRecord = JSONObject.parseObject(data, JfqzInvestRecord.class);
		return jfqzInvestRecord;
	}


	public void setJfqzInvestRecord(JfqzInvestRecord jfqzInvestRecord) {
		this.jfqzInvestRecord = jfqzInvestRecord;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
