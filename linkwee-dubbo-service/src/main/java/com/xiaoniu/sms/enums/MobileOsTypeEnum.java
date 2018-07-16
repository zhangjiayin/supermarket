package com.xiaoniu.sms.enums;
/**
 * 手机操作系统枚举值
 * @author 颜彩云
 *
 */
public enum MobileOsTypeEnum {
	ANDROID("android","5","安卓"),
	IOS("ios","4","IOS"),
	;
	
	private String osType;
	private String oldType;
	private String osDesc;
	
	private MobileOsTypeEnum(String osType,String oldType,String osDesc){
		this.osType = osType;
		this.osDesc = osDesc;
		this.oldType = oldType;
	}

	public String getOldType(){
		return oldType;
	}
	
	public String getOsType() {
		return osType;
	}
	
	public String getOsDesc() {
		return osDesc;
	}
	
	public static MobileOsTypeEnum get(String osType){
		MobileOsTypeEnum[] arr = MobileOsTypeEnum.values();
		for(MobileOsTypeEnum osTypeEnum:arr){
			if(osTypeEnum.getOsType().equalsIgnoreCase(osType)){
				return osTypeEnum;
			}
		}
		return null;
	}
}
