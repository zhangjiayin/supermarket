package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.act.redpacket.model.ActRedpacketBriefDetailAndRule;
import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketRuleDetail;
import com.linkwee.api.request.act.RedpacketRequest;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;

 /**
 * 
 * @描述：红包明细接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月31日 10:52:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketDetailMapper extends GenericDao<ActRedpacketDetail,Long>{
	
	/**
	 * 批量插入红包
	 * @param redpackets
	 * @return
	 */
	int inserts(@Param("redpackets")List<ActRedpacketDetail> redpackets);
	
	/**
	 * 查询用户可用红包
	 * @param userId
	 * @return
	 */
	Integer queryInvestorRedPacketCount(@Param("userId")String userId);

	
	/**
	 * 根据红包状态查客户的红包信息
	 * @param userId
	 * @param type 红包状态（1=未使用|2=已使用|3=已过期）
	 * @param page
	 * @return
	 */
	List<RedpacketResponse> queryInvestorRedPacket(@Param("userId")String userId,@Param("type")Integer type,RowBounds page);
	
	/**
	 * 查询理财师可派发红包
	 * @param userId
	 * @return
	 */
	Integer queryCfplannerRedPacketCount(@Param("userId")String userId);
	
	/**
	 * 根据红包状态查询理财师的红包信息
	 * @param userId 
	 * @param type 红包状态（1=可派发|2=已派发|3=已过期）
	 * @param page
	 * @return
	 */
	List<RedpacketResponse> queryCfplannerRedPacket(@Param("userId")String userId,@Param("type")Integer type,RowBounds page);
	
	/**
	 * 获取发放红包编号 
	 * @param userId
	 * @param redpacketId
	 * @param count
	 * @return
	 */
	List<String> getSendRedpacketIds(@Param("userId")String userId,@Param("redpacketId")String redpacketId,@Param("count")Integer count);
	
	/**
	 * 发放红包
	 * @param redpacketIds
	 * @return
	 */
	int sendRedpacket(ActRedpacketDetail sendRedpacketDetail);
	
	/**
	 * 更新红包过期状态
	 * @param redpacketSendIds
	 * @return
	 */
	int updateExpirationStatus(@Param("sendRedpacketIds")List<String> redpacketSendIds);
	
	/**
	 * 使用红包
	 * @param redpacketDetailId
	 * @return
	 */
	int useRedpacket(@Param("redpacketId")String redpacketId,@Param("updateTime")Date updateTime);
	
	/**
	 * 获取用户可使用红包
	 * @param userId
	 * @return
	 */
	List<ActRedpacketDetail> getUserUsableRedpackets(@Param("userId")String userId);
	
	
	List<Map<String, String>> getProductNames(@Param("pids")Set<String> pids);
	
	/**
	 * 获取红包使用规则
	 * @param rids
	 * @return
	 */
	List<ActRedpacketRuleDetail> getRedpacketRulesByRid(@Param("rids")Set<String> rids);
	
	/**
	 * 根据用户ID查询用户可用红包使用规则
	 * @param userId
	 * @return
	 */
	List<ActRedpacketRuleDetail> getRedpacketRulesByUid(@Param("userId")String userId);
	
	/**
	 * 根据用户ID查询用户可用红包详情及使用规则 
	 * @param userId
	 * @return
	 */
	List<ActRedpacketBriefDetailAndRule> getRedpacketBriefDetailAndRule(@Param("userId")String userId);
	
	
	int getUserRedpacketCountByRid(@Param("userId")String userId,@Param("rids")Set<String> rids);
	
	List<RedpacketResponse> getUserRedpacketByRid(@Param("userId")String userId,@Param("rids")Set<String> rids,RowBounds page);

	/**
	 * 查询理财师红包列表4.0
	 * @param redpacketRequest
	 * @param page
	 * @return
	 */
	List<RedpacketResponse> queryCfplannerRedPacket4(RedpacketRequest redpacketRequest, Page<RedpacketResponse> page);

	/**
	 * 查询理财师红包列表数量4.0
	 * @param redpacketRequest
	 * @return
	 */
	Integer queryRedPacketCount4(RedpacketRequest redpacketRequest);

	/**
	 * 转发派发红包4.5.0
	 * @param redpacketDetail
	 * @return
	 */
	int sendRedpacketToCfp(ActRedpacketDetail redpacketDetail);

	/**
	 * 查询理财师红包列表4.5.0
	 * @param userId
	 * @param page
	 * @return
	 */
	List<RedpacketResponse> queryCfplannerRedPacket5(String userId,Page<RedpacketResponse> page);

	/**
	 * 可使用红包数量4.5.0
	 * @param userId
	 * @return
	 */
	int queryRedPacketCount5(String userId);

	/**
	 * 用户最新获得的红包
	 * @param userId
	 * @return
	 */
	ActRedpacketDetail queryNewestRedPacket(String userId);

}
