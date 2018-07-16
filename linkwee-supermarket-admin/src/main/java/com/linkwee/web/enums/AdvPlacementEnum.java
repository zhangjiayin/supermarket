package com.linkwee.web.enums;


/**
 * 
 * @描述：广告类目 *
 * @author chenchy
 * @serial 2016-09-05 10:05:00
 *
 */
public enum AdvPlacementEnum {
	APP_OPENING("app_opening","开屏",0),
	APP_BANNER("app_home_page","Banner页",2),
	PC_IDX_MIDDLE("pc_idx_middle","pc版投呗首页页中（停用）",2),
	PC_BANNER("pc_banner","pc版banner",2),
	PLATFORM_BANNER("platform_banner","平台banner",0),
	PRODUCT_BANNER("product_banner","投资列表banner",0),
	PRODUCT_OPENING("product_opening","首页弹窗",0),
	PRODUCT_DAY("product_day","日进斗金",1),
	PRODUCT_YEAR("product_year","年年有余",1),
	LIECAI_BANNER("liecai_banner","猎财banner",1),
	INSURANCE_BANNER("insurance_banner","保险banner",0),
	LIECAI_ADVISER_BANNER("liecai_adviser_banner","猎财顾问banner",1),
	PC_CASHBACK_BANNER("pc_cashback_banner","PC超级返banner",1);
	
	AdvPlacementEnum(String key,String value,int appType){
		this.key = key;
		this.value = value;
		this.appType = appType;
		
	}
	
	private String key;
	private String value;
	private int appType;
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

	public int getAppType() {
		return appType;
	}
	
	

}
