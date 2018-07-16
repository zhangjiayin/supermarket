package com.linkwee.openApi.enums;

public enum OrgEnum {
	
	OPEN_LUJINSUO_WEB("OPEN_LUJINSUO_WEB","1AFBD1D079464D4892E06F091FA8A578","81443174B70145E9A97DAA1F45FB58B7","http://premarket.toobei.com/rest/openapi","http://www.testxxxxx.com/xxx/xxxx");
	
	/**
	 * @param orgNumber			机构编码
	 * @param orgKey			机构公钥
	 * @param orgSecret			机构私钥
	 * @param linkweeBaseUrL	领会接口调试地址
	 * @param thirdBaseUrL		合作机构接口自己本地调试地址
	 */
	private OrgEnum(String orgNumber, String orgKey, String orgSecret, String linkweeBaseUrL,String thirdBaseUrL) {
		this.orgNumber = orgNumber;
		this.orgKey = orgKey;
		this.orgSecret = orgSecret;
		this.linkweeBaseUrL = linkweeBaseUrL;
		this.thirdBaseUrL = thirdBaseUrL;
	}
	
	private String orgNumber;
	private String orgKey;
	private String orgSecret;
	private String linkweeBaseUrL;
	private String thirdBaseUrL;
	
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public String getOrgKey() {
		return orgKey;
	}
	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}
	public String getOrgSecret() {
		return orgSecret;
	}
	public void setOrgSecret(String orgSecret) {
		this.orgSecret = orgSecret;
	}
	public String getLinkweeBaseUrL() {
		return linkweeBaseUrL;
	}
	public void setLinkweeBaseUrL(String linkweeBaseUrL) {
		this.linkweeBaseUrL = linkweeBaseUrL;
	}
	public String getThirdBaseUrL() {
		return thirdBaseUrL;
	}
	public void setThirdBaseUrL(String thirdBaseUrL) {
		this.thirdBaseUrL = thirdBaseUrL;
	}
}
