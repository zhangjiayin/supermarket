package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketSendRecord;
import com.linkwee.core.generic.GenericDao;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月08日 10:36:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketSendRecordMapper extends GenericDao<ActRedpacketSendRecord,Long>{
	
	/**
	 *  获取过期红包 
	 */
	List<String> getExpiresRedpacketByTime();
	
	List<String> getExpiresRedpacketByDay();
	
	/**
	 * 获取红包即将过期用户手机号码
	 * @param expireDate
	 * @return
	 */
	List<Map<String,Object>> getAdvanceExpiresRemindMobiles(@Param("expireDate")String expireDate);
	
	/**
	 * 更新发送记录状态
	 * @param sendIds
	 * @return
	 */
	int updateExpiresStatus(@Param("sendIds")List<String> sendIds);
	
	/**
	 * 获取红包记录
	 * @param sendIds
	 * @return
	 */
	List<ActRedpacketSendRecord> getRedpacketSendRecords(@Param("sendIds")List<String> sendIds);
	
	int updateSendRedpackets(@Param("redpackets")List<ActRedpacketDetail> redpackets);

	void updateBirthdayRedpackets(@Param("money")BigDecimal money, @Param("sendId")String sendId);
	
	
	
}
