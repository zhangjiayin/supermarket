package com.linkwee.web.enums;

import com.linkwee.core.base.KvmEnum;

public enum ActicityRedPacketEnum implements KvmEnum{
	NEWYEAR_WHEEL_68(3,"NEWYEAR_WHEEL_68","68元投资返现红包"),//春节大转盘68元投资返现红包
	NEWYEAR_WHEEL_88(4,"NEWYEAR_WHEEL_88","88元投资返现红包"),//春节大转盘88元投资返现红包
	VALENTINE_WHEEL_28(771,"ANNIVERSARY_WHEEL_28","28元投资返现红包"),//七夕（周年）转盘28元投资返现红包
	VALENTINE_WHEEL_100(772,"ANNIVERSARY_WHEEL_100","100元投资返现红包"),//七夕（周年）转盘100元投资返现红包
	VALENTINE_WHEEL_250(773,"ANNIVERSARY_WHEEL_250","250元投资返现红包"),//七夕（周年）转盘250元投资返现红包
	DOUBLE_ELEVEN_11(11,"DOUBLE_ELEVEN_11","28元投资返现红包"),//光棍节11元投资返现红包
	DOUBLE_ELEVEN_111(111,"DOUBLE_ELEVEN_111","100元投资返现红包"),//光棍节111元投资返现红包
	DOUBLE_ELEVEN_1111(1111,"DOUBLE_ELEVEN_1111","250元投资返现红包"),//光棍节1111元投资返现红包
	SIGN_ONE_50(21,"SIGN_ONE_50","50元投资返现红包"),//签到红包1-50元投资返现红包
	SIGN_TWO_30(22,"SIGN_TWO_30","30元投资返现红包"),//签到红包2-30元投资返现红包
	SIGN_THREE_30(23,"SIGN_THREE_30","30元投资返现红包"),//签到红包3-30元投资返现红包
	CHRISTMAS_COLLET_SOCKS_12(1201,"CHRISTMAS_COLLET_SOCKS_12","12元投资返现红包"),//圣诞节收集袜子12元投资返现红包
	CHRISTMAS_COLLET_SOCKS_25(1202,"CHRISTMAS_COLLET_SOCKS_25","25元投资返现红包"),//圣诞节收集袜子25元投资返现红包
	ONE_YUAN_DRAW_3(20181007,"ONE_YUAN_DRAW_3","3元投资返现红包"),//一元抽奖3元投资返现红包
	ONE_YUAN_DRAW_16(20181011,"ONE_YUAN_DRAW_16","16元投资返现红包"),//一元抽奖16元投资返现红包
	ONE_YUAN_DRAW_1(20181013,"ONE_YUAN_DRAW_1","1元投资返现红包"),//一元抽奖1元投资返现红包
	ONE_YUAN_DRAW_NEW_3(20181014,"ONE_YUAN_DRAW_NEW_3","3元投资返现红包(新)");//一元抽奖3元投资返现红包（新）
	
	private int key;
	private String value;
	private String msg;

	ActicityRedPacketEnum(int key,String value,String msg){
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}

}