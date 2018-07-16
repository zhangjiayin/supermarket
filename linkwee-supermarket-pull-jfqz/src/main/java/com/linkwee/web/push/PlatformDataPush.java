package com.linkwee.web.push;

public interface PlatformDataPush {

	/**
	 * 投资记录推送
	 */
	void pushInvestRecord()throws Throwable;
	
	/**
	 * 回款记录推送
	 */
	void  pushRepaymentRecord()throws Throwable;
	
}
