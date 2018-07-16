package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;


/**
 * 
 * @描述：短信模板
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum MsgModuleEnum implements KeyValueEnum{
	
	//注册 修改登陆密码 修改交易密码  佣金发放
	BINDCARD(0,"BINDCARD","绑卡"),
	REGISTER(1,"REGISTER","用户注册"),
	UPDATELOGINPWD(2,"UPDATELOGINPWD","修改登陆密码"),
	UPDATETRADEPWD(3,"UPDATETRADEPWD","修改交易密码"),
	COMMISSIONPAY(4,"COMMISSIONPAY","佣金发放"),
	BACKLEFTONEDAY(5,"BACKLEFTONEDAY","产品剩余一天到期回款"),
    FINISHWITHDRAWAL(6,"FINISHWITHDRAWAL","提现申请处理完成"),
	RECIVEREDPAPERBYSYS(7,"RECIVEREDPAPERBYSYS","收到投资返现红包(系统发)"),
	RECIVEREDPAPERBYLCS(8,"RECIVEREDPAPERBYLCS","收到投资返现红包(理财师发)"),
	REDPAPEREXPIRED(9,"REDPAPEREXPIRED","红包过期提醒"),
    INVESTUSEREDPAPER(10,"INVESTUSEREDPAPER","投资自动使用红包"),
    ADDRESSINVITATIONTOBEINV(11,"ADDRESSINVITATION","通讯录邀请-成为投资客户"),    
    ADDRESSINVITATIONTOBELCS(12,"ADDRESSINVITATION","通讯录邀请-成为理财师"),
    INVESTORPAYBACKINV(13,"INVESTORPAYBACKINV","投资回款-投资端"),
	WITHDRAWALAPPLY(14,"WITHDRAWALAPPLY","提现申请"),
	UPDATEPWDBYOLDPWD(15,"UPDATEPWDBYOLDPWD","修改登录密码-使用原密码修改"),
	UPDATEPAYPWDBYOLDPWD(16,"UPDATEPAYPWDBYOLDPWD","修改支付密码-使用原密码修改"),
	
	
	LCS_LEADER_REWARD_SUBORDINATE_UNBIND(17,"LCS_LEADER_REWARD_SUBORDINATE_UNBIND","leader奖励核算变更(下级解绑)"),
	LCS_LEADER_REWARD_SUBORDINATE_SPLIT(18,"LCS_LEADER_REWARD_SUBORDINATE_SPLIT","leader奖励核算变更(下级拆分)"),
	LCS_LEADER_REWARD_PAUSE_SUBORDINATE_UNBIND(19,"LCS_LEADER_REWARD_PAUSE_SUBORDINATE_UNBIND","暂停核算leader奖励(下级解绑)"),
	LCS_LEADER_REWARD_PAUSE_SUBORDINATE_SPLIT(20,"LCS_LEADER_REWARD_PAUSE_SUBORDINATE_SPLIT","暂停核算leader奖励(下级拆分)"),
	LCS_LEADER_REWARD_CALCULATION(21,"LCS_LEADER_REWARD_CALCULATION","核算leader奖励(符合5+1)"),
	
	/**
	 * 理财师 收益到账
	 */
	LCS_PROFIT_ACCOUNT(22,"LCS_PROFIT_ACCOUNT","收益到账"),//理财师  收益到账
	INV_HELP_RAISE_RATE(23,"INV_HELP_RAISE_RATE","助力加息完成"),
	
	LCS_SUBORDINATE_ACCOUNTING_REWARD(24,"LCS_SUBORDINATE_ACCOUNTING_REWARD","直接下级理财师独立核算津贴"),
	LCS_KING_OF_GLORY(25,"LCS_KING_OF_GLORY","王者荣耀活动  发送短信给理财师"),
	LCS_ANNIVERSARY_REDPACKET(26,"LCS_ANNIVERSARY_REDPACKET","周年庆活动  发送短信给理财师"),
	LCS_ANNIVERSARY_MONEY(27,"LCS_ANNIVERSARY_MONEY","周年庆活动  发送短信给理财师"),
	LCS_ANNIVERSARY_MATTER(28,"LCS_ANNIVERSARY_MATTER","周年庆活动  发送短信给理财师"),
	LCS_ONE_HUNDRED_THOUSAND(29,"LCS_ONE_HUNDRED_THOUSAND","新手任务 集齐月饼获得10万吃货险"),
	DOUBLE_ELEVEN_ACTIVITY_BONUS(30,"DOUBLE_ELEVEN_ACTIVITY_BONUS","恭喜您,完成光棍节活动第%s笔出单,获得%s元的投资红包,赶紧去使用吧!"),
	JOB_GRADE_VOUCHER(31,"JOB_GRADE_VOUCHER","1张%s职级体验券已发送到您的优惠券里，请注意查收，请在有效期内使用哦!"),
	JOB_USE_TIME_GRADE_VOUCHER(32,"job_use_time_grade_voucher","恭喜您参与“%s”活动获得的1张%s职级体验券系统已自动激活生效，本月投资后奖励金会增收哦！详情使用规则可前往：我的―我的优惠券―职级体验券"),
	SEND_JOB_GRADE_VOUCHER(33,"send_job_grade_voucher","恭喜您参与“%s”活动获得1张%s职级体验券，系统下个月1号会自动激活生效，下月投资后奖励金会增收哦！详情使用规则可前往：我的―我的优惠券―职级体验券"),
	CHRISTMAS_ACTIVITIES_TWELVE(34,"christmas_activities_twelve","恭喜您，获得圣诞老人的大礼物%s，在2小时送达到您的账户。"),
	CHRISTMAS_ACTIVITIES_TOOTHBRUSH(35,"christmas_activities_toothbrush","恭喜您，获得圣诞老人的大礼物%s，请与客服联系：400-888-6987，登记联系方式。"),
	SEND_JOB_GRADE_VOUCHER_NEXT_DAY(37,"send_job_grade_voucher_next_day","恭喜您参与“%s”活动获得1张%s职级体验券，系统会根据生效时间自动激活生效，有效期内投资后奖励金会增收哦！详情使用规则可前往：我的―我的优惠券―职级体验券"),
	INV_OLD_ACCOUNT_BIND(36,"INV_OLD_ACCOUNT_BIND","【领会科技】您正在通过[%s]绑定领会老账户,短信验证码为%s,请留意谨慎操作,如有疑问,请与客服联系：400-888-6987"),
	XIAOSHANGCHENG(38,"XIAOSHANGCHENG","尊敬的用户，你正在登陆小商城，短信验证码%s，请勿泄露。");
	MsgModuleEnum(int key,String value,String msg){
		this.key = key;
		this.value = value;
		this.msg = msg;
	}

	private int key;
	private String value;
	private String msg;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
