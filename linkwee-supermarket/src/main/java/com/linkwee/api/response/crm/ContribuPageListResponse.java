package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.model.cim.CimLeaderFee;
import com.linkwee.xoss.util.WebUtil;

/**
 * 团队成员销售记录
 * 
 * @Author chenjl
 * @Date 2017年2月27日 下午4:20:57
 */
public class ContribuPageListResponse extends BaseEntity {
	private static final long serialVersionUID = -5418637411733755341L;

	public ContribuPageListResponse() {

	}

	public ContribuPageListResponse(CimLeaderFee obj) {
		WebUtil.initObj(this, obj);
		this.setHeadImage(obj.getHeadImage());
		this.setUserName(obj.getUserName());
		this.setContrProfit(NumberUtils.getFormat(obj.getLeaderProfit(), "0.00"));
		this.setSaleAmount(NumberUtils.getFormat(obj.getInvestAmt(), "0.00"));
		//满足五加一条件
		this.setRemark("");//该成员满足leader奖励独立核算条件，不再贡献leader奖励
	}

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
	private String contrProfit;
	/**
	 * 销售额
	 */
	private String saleAmount;
	
	/**
	 * 说明--该成员满足leader奖励独立核算条件，不再贡献leader奖励
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

	public String getContrProfit() {
		return contrProfit;
	}

	public void setContrProfit(String contrProfit) {
		this.contrProfit = contrProfit;
	}

	public String getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(String saleAmount) {
		this.saleAmount = saleAmount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
