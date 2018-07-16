package com.linkwee.web.enums;

import com.linkwee.core.base.KvmEnum;

/**
 * 
 * @描述：理财师等级
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum CfpLevelEnum implements KvmEnum{
	PROBATION(1,"PROBATION","理财师","V1"),
	INTERNSHIP(2,"INTERNSHIP","高级理财师","V2"),
	JUNIOR(3,"JUNIOR","资深理财师","V3"),
	MIDDLE(4,"MIDDLE","中级理财师","V4"),
	HIGH(5,"HIGH","高级理财师","V5"),
	SENIOR(6,"SENIOR","资深理财师","V6"),
	SUPER(7,"SUPER","超级理财师","V7");
	
	CfpLevelEnum(int key,String value,String msg,String level){
		this.key = key;
		this.value = value;
		this.msg = msg;
		this.level = level;
	}

	private int key;
	private String value;
	private String msg;
	private String level;
	
	public int getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public String getMsg() {
		return msg;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
