package com.linkwee.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.activity.model.ProductUseCondition;
import com.linkwee.activity.model.Redpacket;
import com.linkwee.activity.model.RedpacketCal;
import com.linkwee.activity.model.Redpaper;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.request.SendRedPacketRequest;

public interface RedpacketMapper extends GenericDao<Redpacket, Long>{
	
	/**
	 * 批量发送红包
	 * @author yalin 
	 * @date 2016年6月30日 下午7:01:46  
	 * @param list
	 * @return
	 */
	public Integer insertBatchRedpacket(List<Redpacket> list);
	/**
	 * 红包列表
	 * @author yalin 
	 * @date 2016年6月22日 下午3:17:04  
	 * @param dt
	 * @param bounds
	 * @return
	 */
	public List<Redpaper> getRedpacketList(DataTable dt,RowBounds bounds);
	/**
	 * 红包每日统计列表
	 * @author yalin 
	 * @date 2016年6月22日 下午3:17:04  
	 * @param dt
	 * @param bounds
	 * @return
	 */
	public List<RedpacketCal> getRedpaperEveryDayList(DataTable dt,RowBounds bounds);
	
	/**
	 * 红包详情
	 * @author yalin 
	 * @date 2016年6月29日 下午3:04:05  
	 * @param request
	 * @return
	 */
	public Redpaper queryRedpaperInfo(SendRedPacketRequest request);
	
	/**
	 * 红包特定产品使用条件
	 * @author yalin 
	 * @date 2016年6月29日 下午3:22:03  
	 * @param request
	 * @return
	 */
	public List<ProductUseCondition> queryProductUseCondition(SendRedPacketRequest request);
	
	/**
	 * 指定红包统计
	 * @author yalin 
	 * @date 2016年6月29日 下午3:04:05  
	 * @param request
	 * @return
	 */
	public RedpacketCal queryRedpaperCal(SendRedPacketRequest request);
	
	/**
	 * 发送失败插入失败的红包记录
	 * @author yalin 
	 * @date 2016年6月30日 下午7:02:10  
	 * @param list
	 * @return
	 */
	public Integer insertBatchSendFailRedpacket(List<Redpacket> list);
	
	
	/**
	 * 判断红包是否发放
	 * @param activityId
	 * @param redpaperTypeId
	 * @return
	 */
	public int isSendRedpaper(@Param("activityId")String activityId,@Param("redpaperTypeId")String redpaperTypeId);
	
}
