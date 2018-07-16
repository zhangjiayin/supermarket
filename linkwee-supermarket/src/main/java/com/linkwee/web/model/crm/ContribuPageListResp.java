package com.linkwee.web.model.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 团队成员销售记录
 * 
 * @Author chenjl
 * @Date 2017年02月27日 下午4:20:57
 */
public class ContribuPageListResp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 名字
	 */
	private String userName;
	/**
	 * 贡献奖励
	 */
	private Double contrProfit;
	/**
	 * 销售额
	 */
	private Double saleAmount;
	/**
	 * 说明--该成员满足leader奖励独立核算，不再计入贡献明细
	 */
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getContrProfit() {
		return contrProfit;
	}

	public void setContrProfit(Double contrProfit) {
		this.contrProfit = contrProfit;
	}

	public Double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Double saleAmount) {
		this.saleAmount = saleAmount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
