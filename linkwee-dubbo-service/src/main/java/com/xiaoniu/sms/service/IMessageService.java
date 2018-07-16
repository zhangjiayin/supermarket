package com.xiaoniu.sms.service;

import java.util.List;

import com.xiaoniu.sms.domain.SpreadMessageRlt;
import com.xiaoniu.sms.domain.SystemMessageRlt;
import com.xiaoniu.sms.req.DeleteSystemMessageReq;
import com.xiaoniu.sms.req.GetSpreadMessageReq;
import com.xiaoniu.sms.req.GetSystemMessageReq;
import com.xiaoniu.sms.req.GetUnreadCountReq;
import com.xiaoniu.sms.req.SpreadMessageListReq;
import com.xiaoniu.sms.req.SystemMessageListReq;
import com.xiaoniu.sms.req.UpdateSystemMessageReq;
import com.xiaoniu.sms.util.DataGridResult;
import com.xiaoniu.sms.util.SmsResult;

/**
 * 系统/推广消息服务类
 * @author 颜彩云
 *
 */
public interface IMessageService {
	/**
	 * 更新系统消息（更新为已读）
	 * @param req
	 * @return
	 */
	public SmsResult<Object> updateSystemMessage(UpdateSystemMessageReq req);
	
	/**
	 * 删除系统消息（逻辑删除）
	 * @param req
	 * @return
	 */
	public SmsResult<Object> deleteSystemMessage(DeleteSystemMessageReq req);
	/**
	 * 根据用户id获取系统消息
	 * @param req
	 * @return
	 */
	public DataGridResult<List<SystemMessageRlt>> getSystemMessageList(SystemMessageListReq req);
	
	/**
	 * 获取用户单个系统消息
	 * @param req
	 * @return
	 */
	public SmsResult<SystemMessageRlt> getSystemMessageById(GetSystemMessageReq req);
	
	/**
	 * 获取未读消息条数
	 * @param req
	 * @return
	 */
	public SmsResult<Integer> getUnreadCount(GetUnreadCountReq req);
	
	/**
	 * 获取推广消息列表
	 * @param req
	 * @return
	 */
	public DataGridResult<List<SpreadMessageRlt>> getSpreadMessageList(SpreadMessageListReq req);
	
	/**
	 * 获取单个推广消息
	 * @param req
	 * @return
	 */
	public SmsResult<SpreadMessageRlt> getSpreadMessageById(GetSpreadMessageReq req);
}
