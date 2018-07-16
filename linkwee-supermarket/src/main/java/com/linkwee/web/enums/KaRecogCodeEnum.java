package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

/**
 * 卡证识别返回CODE
 * */
public enum KaRecogCodeEnum implements KeyValueEnum{
	
	LIMITREACHED(4,"集群超限额"),
	IAMCERTIFICATIONFAILED(14,"IAM鉴权失败"),
	DAILYREQUESTLIMITREACHED(17,"每天流量超限额"),
	QPSREQUESTLIMITREACHED(18,"QPS超限额"),
	TOTALREQUESTLIMITREACHED(19,"请求总量超限额"),
	INVALIDPARAMETER(100,"无效参数"),
	ACCESSTOKENINVALID(110,"Access Token失效"),
	ACCESSTOKENEXPIRED(111,"Access token过期"),
	INTERNALERROR(282000,"服务器内部错误，请再次请求!"),
	INVALIDPARAM(216100,"请求中包含非法参数，请检查后重新尝试！"),
	NOTENOUGHPARAM(216101,"缺少必须的参数，请检查参数是否有遗漏！"),
	SERVICENOTSUPPORT(216102,"请求了不支持的服务，请检查调用的url"),
	PARAMTOOLONG(216103,"请求中某些参数过长，请检查后重新尝试！"),
	APPIDNOTEXIST(216110,"appid不存在，请重新核对信息是否为后台应用列表中的appid"),
	EMPTYIMAGE(216200,"图片为空，请检查后重新尝试"),
	IMAGEFORMATERROR(216201,"上传的图片格式错误！"),
	IMAGESIZEERROR(216202,"上传的图片大小错误"),
	RECOGNIZEERROR(216630,"识别失败，请再次请求"),
	RECOGNIZEBANKCARDERROR(216631,"银行卡识别失败,请重新尝试！"),
	RECOGNIZEIDCARDERROR(216633,"身份证识别失败,请重新尝试！"),
	DETECTERROR(216634,"检测错误，请再次请求"),
	MISSINGPARAMETERS(282003,"请求参数缺失！"),
	BATCHPROCESSINGERROR(282005,"处理批量任务时发生部分或全部错误"),
	BATCHTASKLIMITREACHED(282006,"批量任务处理数量超出限制"),
	URLSIZEERROR(282114,"URL长度超过1024字节或为0"),
	REQUESTIDNOTEXIST(282808,"request id不存在"),
	RESULTTYPEERROR(282809,"返回结果请求错误"),
	IMAGERECOGNIZEERROR(282810,"图像识别失败！");

	
	KaRecogCodeEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	private int key;
	private String value;
	
	
	public int getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

}
