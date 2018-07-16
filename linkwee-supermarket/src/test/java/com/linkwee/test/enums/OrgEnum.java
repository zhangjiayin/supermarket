package com.linkwee.test.enums;

public enum OrgEnum {
	
	OPEN_LEXIANGBAO_WEB("OPEN_LEXIANGBAO_WEB","554F7824938B41A19B0EEA13C60D95A0","A2EEF4AF39244A5AAD63C437550BC839"),
	OPEN_YUECAIHUI_WEB("OPEN_YUECAIHUI_WEB","57DB9B51C6E54F1494B49ED74C19C12B","C975CA2E3BA746F9AB05C749A6F93FE0"),
	OPEN_WUXINGCAIFU_WEB("OPEN_WUXINGCAIFU_WEB","E63117E6BFEF4769A829E2A2312D7B91","9428BE380BB74FFCB5C006D4B51B2FE9"),
	OPEN_ERONGSUO_WEB("OPEN_ERONGSUO_WEB","2E04A86DFF30467DBBA6712D419A31AC","34CEDE6649BA474D88F687261399E7C6"),
	OPEN_XINYONGBAO_WEB("OPEN_XINYONGBAO_WEB","A6E23A75255D4615AF51FC00B022F8DD","8295B100B3884D88AE2A4624DC2825FC"),
	OPEN_XINCHENGJINFU_WEB("OPEN_XINCHENGJINFU_WEB","B8C76362A9F8448AB7276EFFB32B7D7F","F1024C96854F48DFBC7C580A348A5547"),
	OPEN_XIAONIUZAIXIAN_WEB("OPEN_XIAONIUZAIXIAN_WEB","9B8D10A29A3C43149286A945355FB15A","6247E898CDD34E2FA79863DCE90230E6"),
	OPEN_LUJINSUO_WEB("OPEN_LUJINSUO_WEB","1AFBD1D079464D4892E06F091FA8A578","81443174B70145E9A97DAA1F45FB58B7"),
	OPEN_IN_YOUXINQIANBAO_WEB("OPEN_IN_YOUXINQIANBAO_WEB","3BD37807B06E4E4A8B96C599340F719C","6D677262A92B4AE7A03049148EFA146E");
	
	/**
	 * @param orgNumber			机构编码
	 * @param orgKey			机构公钥
	 * @param orgSecret			机构私钥
	 * @param linkweeBaseUrL	领会接口调试地址
	 * @param thirdBaseUrL		合作机构接口自己本地调试地址()
	 */
	private OrgEnum(String orgNumber, String orgKey, String orgSecret) {
		this.orgNumber = orgNumber;
		this.orgKey = orgKey;
		this.orgSecret = orgSecret;
	}
	
	private String orgNumber;
	private String orgKey;
	private String orgSecret;
	
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
}
