package com.linkwee.api.request.funds.ifast;

public class BatchGetFundExtendsRequest extends BatchGetFundRequest {

	/**
	 * 查询基金代码或者名称
	 */
	private String queryCodeOrName;
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgCode = "OPEN_IFAST_WEB";

	public String getQueryCodeOrName() {
		return queryCodeOrName;
	}

	public void setQueryCodeOrName(String queryCodeOrName) {
		this.queryCodeOrName = queryCodeOrName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
