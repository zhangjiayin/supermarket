package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;


/**
 * 
 * @描述：合伙人等级
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum MsgModuleEnum implements KeyValueEnum{
	
	//注册 修改登陆密码 修改交易密码  佣金发放
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
    ADDRESSINVITATIONTOBEINV(11,"ADDRESSINVITATIONTOBEINV","通讯录邀请-成为投资客户"),
    ADDRESSINVITATIONTOBELCS(12,"ADDRESSINVITATIONTOBELCS","通讯录邀请-成为理财师"),
    ACTIVITYREWARDRELEASE(13,"ACTIVITYREWARDRELEASE","系统活动奖励发放"),
    WITHDRAWALAPPLY(14,"WITHDRAWALAPPLY","提现申请"),
    PUSHMESSAGE_LCSJJTZ_JXGW(15,"PUSHMESSAGE_LCSJJTZ_JXGW","理财师晋级通知  发送短信给理财师(见习顾问)"),
    PUSHMESSAGE_LCSJJTZ_JLZJ(16,"PUSHMESSAGE_LCSJJTZ_JLZJ","理财师晋级通知  发送短信给理财师(经理总监)"),
	LCS_KING_OF_GLORY(17,"LCS_KING_OF_GLORY","王者荣耀活动  发送短信给理财师"),
	JOB_GRADE_VOUCHER(18,"JOB_GRADE_VOUCHER","1张%s职级体验券已发送到您的优惠券里，请注意查收，请在有效期内使用哦!"),
	JOB_USE_TIME_GRADE_VOUCHER(19,"job_use_time_grade_voucher","恭喜您参与“%s”活动获得的1张%s职级体验券系统已自动激活生效，本月投资后奖励金会增收哦！详情使用规则可前往：我的―我的优惠券―职级体验券"),
	SEND_JOB_GRADE_VOUCHER(20,"send_job_grade_voucher","恭喜您参与“%s”活动获得1张%s职级体验券，系统下个月1号会自动激活生效，下月投资后奖励金会增收哦！详情使用规则可前往：我的―我的优惠券―职级体验券"),
	BIRTHDAYREMINDER(21,"BIRTHDAYREMINDER","花开一季，岁月一轮，猎财大师祝您生日快乐！18元生日红包已经发送到您的账户,请登录查看"),
	THREDAYWITHOUTINVESTREMIND(22,"THREDAYWITHOUTINVESTREMIND","亲爱的用户，您有528元新手红包还未使用，赶紧来使用吧"),
	UNRECORDINVESTSUCCESS(23,"UNRECORDINVESTSUCCESS","尊敬的用户，您于%s提交的报单已审核通过，获得猎财返现金额%s元。"),
	UNRECORDINVESTFAILURE(24,"UNRECORDINVESTFAILURE","尊敬的用户，您于%s提交的报单审核未通过，请重新报单。如有疑问请联系客服400-888-6987。"),
	PAYSUCCESS(24,"PAYSUCCESS","尊敬的用户，您于%s投资信用宝%s天标的%s元，获得猎财返现%s元，将在3个工作日内发放至猎财余额。"),
	HUIKUAN(24,"HUIKUAN","您购买的%s%s天标的%s元，预计三天后到期，您可关注猎财更多超值理财计划！");
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
