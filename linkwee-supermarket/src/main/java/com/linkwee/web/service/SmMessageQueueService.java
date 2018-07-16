package com.linkwee.web.service;

import java.util.Collection;
import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsChannelTypeEnum;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.response.CommonTCSResult;
 /**
 * 
 * @描述： SmMessageTemplateService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月18日 11:33:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmMessageQueueService extends GenericService<SmMessageQueue,Long>{

	/**
	 * 查询SmMessageTemplate列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 单条短信推送
	 * @param mobile 手机号
	 * @param content 模板占位符参数
	 * @param appType 端口
	 * @param moduleId 模板模块ID
	 * @return
	 * @throws Exception
	 */
	public CommonTCSResult sendSingleMessage(String mobile,AppTypeEnum appType,MsgModuleEnum moduleId,Object...content )  ;
	/**
	 * 批量推送（同内容，如推广，公告）
	 * @param mobile
	 * @param content
	 * @param appType 端口
	 * @param moduleId 模板模块ID
	 * @return
	 */
	public CommonTCSResult batchSendMessage( Collection<String> mobile,AppTypeEnum appType,MsgModuleEnum moduleId,Object...params);
	public CommonTCSResult batchSendSmMessageAndPersonalMsg( Collection<String> mobiles,AppTypeEnum appType,MsgModuleEnum moduleId,boolean isNeedSendPeronalMsg,Collection<String> userIds,PersonalMsgTypeEnum msgType,Object...params);
	/**
	 * 查询待推送短信消息
	 * @param condition
	 * @return
	 */
	public List<String> queryWaitingMsgTmp(SmMessageQueue condition);
	/**
	 * 手机验证码校验
	 * @param mobile
	 * @param moduleId 模板模块ID
	 * @param inputCode 输入验证码
	 * @return
	 * @throws Exception
	 */
	public boolean checkVerificationCode(String mobile,MsgModuleEnum moduleId,String inputCode) throws Exception;
	/**
	 * 根据消息队列待发送记录发送
	 * @param messages
	 * @return
	 */
	public CommonTCSResult sendDiffContentMessage(List<SmMessageQueue> messages);
	public CommonTCSResult sendMessageAndSysMsg(String mobile,String userId,AppTypeEnum appType, MsgModuleEnum moduleId,boolean needSendMsg,Object... contentArgs);
	public String queryMessageTemplate(MsgModuleEnum moduleId,AppTypeEnum appType,Object... contentArgs );
	/**
	 * 发送验证码-预发布不返回错误信息给前端
	 * @return
	 */
	public boolean isPreReturnMsg();
	
	public CommonTCSResult sendSingleMessage(String mobile,	AppTypeEnum appType, String content, SmsChannelTypeEnum channelType) throws Exception;
}
