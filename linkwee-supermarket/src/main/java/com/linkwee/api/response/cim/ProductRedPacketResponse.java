package com.linkwee.api.response.cim;

import java.util.List;

import com.linkwee.act.redpacket.model.ActRedpacketRuleDetail;

public class ProductRedPacketResponse {

	/**
	 * 红包使用List及规则
	 */
	private List<ActRedpacketRuleDetail>  actRedpacketRuleDetailList;

	public List<ActRedpacketRuleDetail> getActRedpacketRuleDetailList() {
		return actRedpacketRuleDetailList;
	}

	public void setActRedpacketRuleDetailList(
			List<ActRedpacketRuleDetail> actRedpacketRuleDetailList) {
		this.actRedpacketRuleDetailList = actRedpacketRuleDetailList;
	}
}
