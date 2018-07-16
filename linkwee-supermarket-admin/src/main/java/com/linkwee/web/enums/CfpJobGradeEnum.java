package com.linkwee.web.enums;

import org.apache.commons.lang.ObjectUtils;

import com.linkwee.core.base.KvmEnum;

/**
 * 
 * @描述：理财师等级
 *
 * @author ch
 * @serial 2016-07-26 16:39:00
 *
 */
public enum CfpJobGradeEnum implements KvmEnum{
	TA(1,"TA","见习",10),
	SM1(2,"SM1","顾问",20),
	SM2(3,"SM2","经理",30),
	SM3(4,"SM3","总监",40),
	WAITSM2(5,"WAITSM2","待定经理",25),
	WAITSM3(6,"WAITSM3","待定总监",35),
	UNDETERMINED(7,"UNDETERMINED","待定",0);
	
	CfpJobGradeEnum(int key,String value,String msg,int levelWeight){
		this.key = key;
		this.value = value;
		this.msg = msg;
		this.levelWeight = levelWeight;
	}

	private int key;
	private String value;
	private String msg;
	private int levelWeight;
	
	public int getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public String getMsg() {
		return msg;
	}
	public int getLevelWeight() {
		return levelWeight;
	}
		
	public static CfpJobGradeEnum getCfpJobGradeEnumByKey(String value){
		for (CfpJobGradeEnum levelEnum : values()) {
			if(ObjectUtils.equals(levelEnum.getValue(), value)){
				return levelEnum;
			}
		}
		return null;
	}
		
}
