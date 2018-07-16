package com.xiaoniu.sms.enums;

/**
 * 业务id映射枚举值
 * @author 颜彩云
 *
 */
public enum MapperPartnerInfoEnum {
	
	QGZ("QGZ","10002","钱罐子"),
	ZC("ZC","10003","众筹")
	;
	
	private String partnerId;
	private String mapperPartnerId;
	private String desc;
	
	private MapperPartnerInfoEnum(String partnerId,String mapperPartnerId,String desc){
		this.desc = desc;
		this.partnerId = partnerId;
		this.mapperPartnerId = mapperPartnerId;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public String getMapperPartnerId() {
		return mapperPartnerId;
	}

	public String getDesc() {
		return desc;
	}

	public static MapperPartnerInfoEnum getByMapperPartnerId(String mapperPartnerId){
		MapperPartnerInfoEnum[] arr = MapperPartnerInfoEnum.values();
		for (MapperPartnerInfoEnum statusEnum : arr) {
			if (statusEnum.getMapperPartnerId().equalsIgnoreCase(mapperPartnerId)) {
				return statusEnum;
			}
		}
		return null;
	}
	
	public static MapperPartnerInfoEnum getByPartnerId(String partnerId){
		MapperPartnerInfoEnum[] arr = MapperPartnerInfoEnum.values();
		for (MapperPartnerInfoEnum statusEnum : arr) {
			if (statusEnum.getPartnerId().equalsIgnoreCase(partnerId)) {
				return statusEnum;
			}
		}
		return null;
	}
}
