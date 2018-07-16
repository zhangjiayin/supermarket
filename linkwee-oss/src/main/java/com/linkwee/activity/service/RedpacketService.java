package com.linkwee.activity.service;


import java.util.List;

import com.linkwee.activity.model.ExcelModel;
import com.linkwee.activity.model.ProductUseCondition;
import com.linkwee.activity.model.Redpacket;
import com.linkwee.activity.model.RedpacketCal;
import com.linkwee.activity.model.Redpaper;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.request.RedPacketInfoRequest;
import com.linkwee.web.request.SendRedPacketRequest;

public interface RedpacketService extends GenericService<Redpacket, Long>{
	
	/**
	 * 添加红包
	 * @param redPacketInfoRequest
	 * @throws Exception
	 */
	public void insertRedpacket(RedPacketInfoRequest redPacketInfoRequest,String operator) throws Exception;
	
	
	public void updateRedpacket(RedPacketInfoRequest redPacketInfoRequest,String activityId,String redpacketTypeId,String operator) throws Exception;
	
	/**
	 * 红包列表
	 * @author yalin 
	 * @date 2016年6月28日 下午4:57:07  
	 * @param dataTable
	 * @return
	 */
	public DataTableReturn getRedpacketList(DataTable dataTable);
	
	/**
	 * 红包每日统计列表
	 * @author yalin 
	 * @date 2016年6月28日 下午4:57:13  
	 * @param dataTable
	 * @return
	 */
	public DataTableReturn getRedpaperEveryDayList(DataTable dataTable);
	/**
	 * 批量生成红包
	 * @author yalin 
	 * @date 2016年6月23日 下午5:44:30  
	 * @param redPaperRequests
	 * @param activityId
	 * @param type
	 * @return
	 */
	public void insertBatchRedpacket(SendRedPacketRequest request) throws Exception;
	
	/**
	 * 发送红包到白名单用户
	 * @author yalin 
	 * @date 2016年6月28日 上午11:36:58  
	 * @param request
	 * @return
	 */
	public void insertBatchWhiteListUser(SendRedPacketRequest request) throws Exception;
	
	
	/**
	 * 红包详情
	 * @author yalin 
	 * @date 2016年6月29日 下午3:04:05  
	 * @param request
	 * @return
	 */
	public Redpaper queryRedpaperInfo(SendRedPacketRequest request);
	
	
	public void queryRedpaperInfo(RedPacketInfoRequest redpacketInfo,String activityId,String redpacketTypeId) throws Exception;
	
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
	 * 红包使用规则
	 * @author yalin 
	 * @date 2016年6月30日 下午5:09:14  
	 * @param request
	 * @return
	 */
	public ProductUseCondition queryAllUseRuleByActivityId(SendRedPacketRequest request);
	
	/**
	 * 导入发送理财师红包
	 * @author yalin 
	 * @date 2016年6月30日 下午10:18:49  
	 * @param request
	 * @param datas
	 */
	public String insertImportSend(SendRedPacketRequest request,List<ExcelModel> datas);
	
	/**
	 * 判断红包是否发放
	 * @param activityId
	 * @param redpaperTypeId
	 * @return
	 */
	public boolean isSendRedpaper(String activityId,String redpaperTypeId);
	
	/**
	 * 更新红包规则
	 * @param activityId
	 * @param redpaperTypeId
	 * @param pid
	 */
	public void updateRedpaperRule(String activityId,String redpaperTypeId,String pids) throws Exception;
	
	/**
	 * 导入发送金服用户红包
	 * @author yalin 
	 * @date 2016年7月7日 上午10:00:44  
	 * @param request
	 * @param datas
	 * @return
	 */
	public String insertImportSendInvester(SendRedPacketRequest request,List<ExcelModel> datas);
		
}
