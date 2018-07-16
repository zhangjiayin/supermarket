package com.linkwee.web.service;

import java.util.Map;

public interface PushMsgService {

	/**
	 * 定期产品到期回款 推送消息
	 * @Auther xuzhao
	 * @Date 2016年3月16日 下午2:20:14
	 * @param customerId
	 * @param bizTime
	 * @param productName
	 * @param amount
	 */
	void pushMSgRepayment(String customerId,String bizTime, String productName, double amount);

	/**
	 * 定期产品在投资额超（含）10万元，且3天后到期回款 推送消息
	 * @Auther xuzhao
	 * @Date 2016年3月16日 下午2:20:18
	 * @param userId
	 * @param productName
	 * @param investAmt
	 */
	void willRepaymentPushMsg(String userId, String productName, Double investAmt);
	
	
	/**
	 * 推送消息
	 * @Auther liqi
	 * @Date 2016年5月27日 下午21:08:20
	 * @param appType app类型
	 * @param type 消息类型
	 * @param userId 用户id
	 * @param values 消息参数，用,分隔
	 * @param isTimingTask 是否需要定时发送  1-需要      0-不需要
	 * @param content 添加个人消息内容
	 * @param pushType 推送方式：0-消息栏+个人消息  1-消息栏 2-个人消息
	 * @return
	 */
	public boolean pushMessage(int appType, int type, String userId, String values, int isTimingTask, String content,int pushType,Map<String, Object> urlparam);
	
	
	
}
