package com.linkwee.web.enums;

import com.linkwee.core.base.KvmEnum;

/**
 * 消息推送
 * 
 * @author xuzhao
 * @Date 2016年3月7日 下午3:26:52
 */
public enum SmsTypeEnum implements KvmEnum{
	/**
	 * 金服  用户注册成功
	 */
	CREGISTER(1,"REGISTEROK",""),//金服  用户注册成功
	/**
	 * 金服  定期产品到期回款
	 */
	CFIXEDRETURN(2,"PAYMENT","payment"),//金服  定期产品到期回款
	/**
	 * 金服  定期产品在投资额超（含）10万元，且3天后到期回款  
	 */
	CFIXEDBIGAMOUNTRETURN(3,"PAYMENTREMIND","cusDetail"),//金服  定期产品在投资额超（含）10万元，且3天后到期回款  
	/**
	 * 金服  提现申请处理完成
	 */
	CTXAPPLYSUCCESS(4,"WITHDRAW",""),//金服  提现申请处理完成
	/**
	 * 金服  提现申请处理失败
	 */
	CTXAPPLYFAIL(5,"WITHDRAWFAIL",""),//金服  提现申请处理失败
	
	/**
	 * 理财师    用户（理财师自己）注册成功
	 */
	LREGISTER(6,"REGISTEROK",""),//理财师    用户（理财师自己）注册成功
	/**
	 * 理财师   客户注册完成
	 */
	LCUSTOMERREGIST(7,"CUSREGISTEROK","cusDetail"),//理财师   客户注册完成
	/**
	 * 理财师   平台分配客户
	 */
	LASSIGNCUSTOMER(8,"ASSIGNCUS","cusDetail"),//理财师   平台分配客户
	/**
	 * 理财师  团队一级成员注册完成
	 */
	LGRADEONEREGISTER(9,"JOINOK","teamDetail"),//理财师  团队一级成员注册完成
	/**
	 * 理财师  客户购买成功
	 */
	LCUSTOMERBUY(10,"CUSINVEST","cusDetail"),//理财师  客户购买成功
	/**
	 * 理财师  团队一级成员销售成功
	 */
	LGRADEONESALE(11,"MEMBERINVEST1","teamDetail"),//理财师  团队一级成员销售成功
	/**
	 * 理财师  团队二级成员销售成功
	 */
	LGRADETWOSALE(12,"MEMBERINVEST2","teamDetail"),//理财师  团队二级成员销售成功
	/**
	 * 理财师  客户活期宝赎回完成
	 */
	LCUSTOMERCURRENTRETURN(13,"CUSHUOQI","cusDetail"),//理财师  客户活期宝赎回完成
	/**
	 * 理财师  客户定期产品到期回款
	 */
	LCUSTOMERFIXEDRETURN(14,"CUSPAYMENT","cusDetail"),//理财师  客户定期产品到期回款
	/**
	 * 理财师  客户定期产品在投资额超（含）10万元，且3天后到期回款
	 */
	LCUSTOMERBIGAMOUNTRETURN(15,"CUSPAYMENTREMIND","cusDetail2"),//理财师  客户定期产品在投资额超（含）10万元，且3天后到期回款
	/**
	 * 理财师  客户升级为理财师
	 */
	LCUSTOMERTOLCS(16,"CUSUPGRADE","teamDetail"),//理财师  客户升级为理财师
	/**
	 * 理财师  佣金收益到账
	 */
	LFEERECEIVED(17,"COMMISION","myAccount"),//理财师  佣金收益到账
	/**
	 * 理财师  提现申请处理完成
	 */
	LTXAPPLYSUCCESS(18,"WITHDRAW","withdraw"),//理财师  提现申请处理完成
	/**
	 * 理财师  提现申请处理失败
	 */
	LTXAPPLYFAIL(19,"WITHDRAWFAIL","withdraw"),//理财师  提现申请处理失败
	
	/**
	 * 绑定理财师（理财师）
	 */
	BDLCSLCSSUCCESS(20,"BDLCSLCSSUC","notification"),//绑定理财师（理财师）
	
	/**
	 * 解绑理财师（理财师）
	 */
	JBLCSLCSSUCCESS(21,"JBLCSLCSSUC","notification"),//绑定理财师（理财师）
	
	/**
	 * 绑定理财师（金服）
	 */
	BDLCSJFSUCCESS(22,"BDLCSJFSUC","notification"),//绑定理财师（金服）
	
	/**
	 * 解绑理财师（金服）
	 */
	JBLCSJFSUCCESS(23,"JBLCSJFSUC","notification"),//绑定理财师（金服）
	
	/**
	 * 理财师升级
	 */
	LSJAPPCS(24,"LSJAPPCS","lsjappcs"),//理财师升级成功
	
	/**
	 * 理财师下级解绑-上级消息
	 */
	LCSJBSJSUCCESS(25,"LCSJBSJ","notification"),//绑定理财师（金服）
	
	/**
	 * 理财师下级解绑-下级消息
	 */
	LCSJBXJSUCCESS(26,"LCSJBXJ","notification");//绑定理财师（金服）
	
	private int key;
	private String value;
	private String msg;//app跳转页url

	SmsTypeEnum(int key,String value,String msg){
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
