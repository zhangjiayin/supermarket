package com.linkwee.web.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.Msg;
import com.linkwee.web.model.MsgResp;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月26日 20:05:52
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface MsgDao extends BasePageDao<Msg>{
	
	/**
	 * 删除个人消息		
	 * @param msgs 消息id
	 * @param userNumber 用户编码
	 */
	public Integer deletePersonMsgs(@Param("msgs") String[] msgs,@Param("userNumber")String userNumber);
	
	/**
	 * 消息分页
	 * @param request
	 * @return
	 */
	public PageList<MsgResp> querySysMsgResp(PageRequest request);
	
	
	/**
	 * 统计未读消息数
	 * @param userNumber 用户编号
	 * @param personMsgDate 上次查看个人消息的时间
	 * @param sysMsgDate 上次查看系统消息的时间
	 * @param app类别
	 * @return
	 */
	public Integer queryUnReadMsgCount(@Param("userNumber")String userNumber,@Param("personMsgDate")Date personMsgDate
			,@Param("sysMsgDate")Date sysMsgDate,@Param("appType")Integer appType);

	public int queryUnReadSysMsgCount(@Param("sysMsgDate")Date sysMsgDate,@Param("appType")Integer appType);

	/**
	 * 统计个人未读消息
	 * @Auther ZhongLing
	 * @Date 2016年1月25日
	 * @param userId
	 * @param sysMsgDate
	 * @return
	 */
	public Integer queryPersonUnreadCount(@Param("userNumber")String userNumber, @Param("personMsgDate")Date personMsgDate,@Param("appType")Integer appType);
	


	public PageList<MsgResp> queryPersonMsgResp(PageRequest req);

}
