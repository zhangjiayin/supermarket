package com.linkwee.web.enums;


/**
 * 
 * @描述：理财师新手任务
 *
 * @author Bob
 * @时间  2015年8月5日上午9:50:32
 *
 */
public enum CfpNewcomerTaskEnum 
{
	/*新手任务*/
	INVITE_CUSTOMER("INVITE_CUSTOMER","理财师新手任务邀请客户"),
	INVITE_CFPLANNER("INVITE_CFPLANNER","理财师新手任务邀请理财师"),
	RECOMMEND_PRODUCT("RECOMMEND_PRODUCT","理财师新手任务推荐产品"),
	RECOMMEND_PLATFORM("RECOMMEND_PLATFORM","理财师新手任务自己购买产品"),
	GRANT_HONGBAO("GRANT_HONGBAO","理财师新手任务派发红包"),
	SEE_PROFIT("SEE_PROFIT","理财师新手任务查看收益"),
	
	/*新手福利*/
	CFPLANNER_NEWCOMERWELFARE_INVITE_CUSTOMER("CFPLANNER_NEWCOMERWELFARE_INVITE_CUSTOMER","理财师新手福利邀请客户"),
	CFPLANNER_INEWCOMERWELFARE_NVITE_CFPLANNER("CFPLANNER_INEWCOMERWELFARE_NVITE_CFPLANNER","理财师新手福利邀请理财师"),
	CFPLANNER_INEWCOMERWELFARE_UPLOAD_HEADIMAGE("CFPLANNER_INEWCOMERWELFARE_UPLOAD_HEADIMAGE","理财师新手福利上传头像"),
	CFPLANNER_NEWCOMERWELFARE_BIND_CARD("CFPLANNER_NEWCOMERWELFARE_BIND_CARD","理财师新手福利绑卡认证"),
	
	/*新手福利六连送*/
	CFP_NEWCOMMER_WELFARE_REGISTER("CFP_NEWCOMMER_WELFARE_REGISTER","理财师新手福利六连送注册"),
	CFP_NEWCOMMER_WELFARE_BINDCARD("CFP_NEWCOMMER_WELFARE_BINDCARD","理财师新手福利六连送绑卡"),
	CFP_NEWCOMMER_WELFARE_INVEST("CFP_NEWCOMMER_WELFARE_INVEST","理财师新手福利六连送自投"),
	CFP_NEWCOMMER_WELFARE_INVITECFP("CFP_NEWCOMMER_WELFARE_INVITECFP","理财师新手福利六连送成功邀请理财师"),
	CFP_NEWCOMMER_WELFARE_INVITECFP_INVEST("CFP_NEWCOMMER_WELFARE_INVITECFP_INVEST","理财师新手福利推荐理财师投资"),
	CFP_NEWCOMMER_WELFARE_ALL("CFP_NEWCOMMER_WELFARE_ALL","理财师新手福利终极大奖");
	
	CfpNewcomerTaskEnum(String code,String message){
		this.code = code;
		this.message = message;
	}

	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
