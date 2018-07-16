package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CfplannerManagerDao;
import com.linkwee.web.dao.TorginfoDao;
import com.linkwee.web.enums.CfplannerOperationType;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.crm.CfpManagerDetailResp;
import com.linkwee.web.model.crm.CfplannerListNewResp;
import com.linkwee.web.model.crm.CrmCfplannerOperation;
import com.linkwee.web.model.crm.LcsDetailSalesInfoResp;
import com.linkwee.web.model.crm.LcsHongbaoListResp;
import com.linkwee.web.model.crm.LcsHongbaoListResponse;
import com.linkwee.web.model.crm.TeamAllowanceListResp;
import com.linkwee.web.model.weixin.WeiXinMsgRequest;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.response.CfpCustomerProfitListResp;
import com.linkwee.web.response.CfpTeamListResp;
import com.linkwee.web.service.CfplannerManagerService;
import com.linkwee.web.service.CrmCfplannerOperationService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ThreadpoolService;


@Service("cfplannerManagerService")
public class CfplannerManagerServiceImpl implements CfplannerManagerService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(CfplannerManagerServiceImpl.class);
	
	@Resource
	private CfplannerManagerDao cfplannerManagerDao;

	@Resource
	private TorginfoDao torginfoDao;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private CrmCfplannerOperationService crmCfplannerOperationService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	

	@Override
	public DataTableReturn queryLcsList(DataTable dataTable, LcsListRequest request) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CfpManagerDetailResp> page = new Page<CfpManagerDetailResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfpManagerDetailResp> list = cfplannerManagerDao.queryLcsList(request,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
	
	@Override
	public DataTableReturn queryLcsListNew(DataTable dataTable, LcsListRequest request) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<CfplannerListNewResp> page = new Page<CfplannerListNewResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfplannerListNewResp> list = cfplannerManagerDao.queryLcsListNew(request,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public CfpManagerDetailResp queryLcsDetail(String mobile) {
		CfpManagerDetailResp resp = cfplannerManagerDao.queryLcsDetail(mobile);
		if(resp == null){
			return null;
		}
		Date disabledLoginEndTime = DateUtils.addDay(resp.getDisabledLoginTime(), 90);
		resp.setDisabledLoginEndTime(disabledLoginEndTime);
		//更换上级记录
		List<CrmCfplannerOperation> changeParentRecordList = queryChangeParentRecordList(resp.getUserId());
		resp.setChangeParentRecordList(changeParentRecordList);
		return resp;
	}
	
	@Override
	public CfpManagerDetailResp queryLcsDetailNew(String mobile) {
		CfpManagerDetailResp resp = cfplannerManagerDao.queryLcsDetailNew(mobile);
		if(resp == null){
			return null;
		}
		Date disabledLoginEndTime = DateUtils.addDay(resp.getDisabledLoginTime(), 90);
		resp.setDisabledLoginEndTime(disabledLoginEndTime);
		//更换上级记录
		List<CrmCfplannerOperation> changeParentRecordList = queryChangeParentRecordList(resp.getUserId());
		resp.setChangeParentRecordList(changeParentRecordList);
		return resp;
	}

	@Override
	public CfpManagerDetailResp queryLcsInfo(String moible) {
		if(StringUtils.isNotBlank(moible)){
			CfpManagerDetailResp resp = cfplannerManagerDao.queryLcsDetail(moible);
			return resp;
		}
		return null;
	}

	/**
	 * 更换上级记录
	 * @param number
	 * @return
	 */
	private List<CrmCfplannerOperation> queryChangeParentRecordList(String userId) {
		List<CrmCfplannerOperation> list = crmCfplannerOperationService.queryChangeParentRecordList(userId);
		return list;
	}

	@Override
	@Transactional
	public void exitLcs(CrmCfplanner crmCfplanner) {
		// 操作记录
		CrmCfplannerOperation operation = new CrmCfplannerOperation();
		operation.setCfplanner(crmCfplanner.getUserId());
		operation.setCreateTime(new Date());
		operation.setLastUpdateTime(new Date());
		operation.setOperationAdmin("admin");
		operation.setParentId(crmCfplanner.getParentId());
		operation.setRemarks(CfplannerOperationType.QUIT_CFPLANNER.getMessage());
		operation.setType(CfplannerOperationType.QUIT_CFPLANNER.getCode());
		crmCfplannerOperationService.insert(operation);
		
		//将上级设置为理财师
		CrmInvestor crmInvestor = new CrmInvestor();
		crmInvestor.setUserId(crmCfplanner.getUserId());
		crmInvestor.setCfplanner(crmCfplanner.getParentId());
		crmInvestorService.updateByUserId(crmInvestor);

		//删除理财师帐号数据
		crmCfplannerService.delete((long)crmCfplanner.getId());
	}

	@Override
	public boolean removeCfplannerHeadImage(String mobile) throws Exception {
		return cfplannerManagerDao.removeCfplannerHeadImage(mobile)>0;
	}
	
	/**
	 * 更换上级
	 * @param mobile
	 * @param parentMobile
	 * @param changeType
	 */
	@Override
	public void changeParent(String mobile, String parentMobile, String changeType, CrmCfplanner saleUserInfo) {
		CrmCfplanner saleUserForUpdate = new CrmCfplanner();
		if("1".equals(changeType)) {
			//更改新上级
			CrmCfplanner parentNew = crmCfplannerService.queryCfplannerByInvestMobile(parentMobile);
			if(parentNew == null){
				return;
			}
			saleUserForUpdate.setUserId(saleUserInfo.getUserId());
			saleUserForUpdate.setParentId(parentNew.getUserId());
			crmCfplannerService.updateByUserId(saleUserForUpdate);
			//操作记录
			CrmCfplannerOperation operation = new CrmCfplannerOperation();
			operation.setCfplanner(saleUserInfo.getUserId());
			operation.setParentId(parentNew.getUserId());
			operation.setCreateTime(new Date());
			operation.setLastUpdateTime(new Date());
			operation.setOperationAdmin("admin");
			operation.setRemarks(CfplannerOperationType.CHANGE_PARENT.getMessage());
			operation.setType(CfplannerOperationType.CHANGE_PARENT.getCode());
			crmCfplannerOperationService.insert(operation);
			//微信消息推送 下级绑定通知    亲，有理财师与您建立绑定关系，成为您的下级理财师。具体信息如下：
			final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
			WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
			wxreq.setUseId(parentNew.getUserId());
			wxreq.setTemkey(SysConfigConstant.SUBORDINATE_BIND_SUCCESS);//客户绑定
			wxreq.setUserName(saleUserInfo.getUserName());//用户名
			wxreq.setMobile(saleUserInfo.getMobile());
			wxreq.setBindTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//绑定时间
			wxreq.setUseType("1");
			wxList.add(wxreq);
			//发给当前理财师 绑定通知(上级绑定成功) 亲，您已经成功绑定一个新的上级理财师！上级理财师具体信息如下：
			WeiXinMsgRequest wxreq2 = new WeiXinMsgRequest();
			wxreq2.setUseId(saleUserInfo.getUserId());
			wxreq2.setTemkey(SysConfigConstant.SUPERIOR_BIND_SUCCESS);//客户绑定
			wxreq2.setUserName(parentNew.getUserName());//用户名
			wxreq2.setMobile(parentNew.getMobile());
			wxreq2.setBindTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//绑定时间
			wxreq2.setUseType("1");
			wxList.add(wxreq2);
			ThreadpoolService.execute(new Runnable() {
				 @Override
				 public void run() {
					 weiXinMsgService.sendWeiXinMsgListCommon(wxList);
				 }
			 });
			
		} else if ("2".equals(changeType)) {
			//变为一级理财师
			saleUserForUpdate.setUserId(saleUserInfo.getUserId());
			saleUserForUpdate.setParentId("");
			crmCfplannerService.updateByUserId(saleUserForUpdate);
			//操作记录
			CrmCfplannerOperation operation = new CrmCfplannerOperation();
			operation.setCfplanner(saleUserInfo.getUserId());
			operation.setParentId(saleUserInfo.getParentId());
			operation.setCreateTime(new Date());
			operation.setLastUpdateTime(new Date());
			operation.setOperationAdmin("admin");
			operation.setRemarks(CfplannerOperationType.REMOVE_PARENT.getMessage());
			operation.setType(CfplannerOperationType.REMOVE_PARENT.getCode());
			crmCfplannerOperationService.insert(operation);
		}
	}

	@Override
	public boolean hasCustomerOrTeam(String userId) {
		if(crmCfplannerService.queryTeamMemberCount(userId) > 0) {
			return true;
		}
		if(crmCfplannerService.queryCustomerCount(userId) > 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询理财师客户列表
	 * @param detailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryCfpCustomerProfitList(CfpManagerDetailResp detailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn = new DataTableReturn();
		if(null == detailResp || StringUtils.isBlank(detailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpCustomerProfitListResp> page = new Page<CfpCustomerProfitListResp>(dataTable.getStart() / dataTable.getLength()+1,dataTable.getLength());
		List<CfpCustomerProfitListResp> cfpCustomerProfitListRespList = cfplannerManagerDao.queryCfpCustomerProfitList(detailResp,page);
        dataTableReturn.setData(cfpCustomerProfitListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}

	/**
	 * 查询理财师团队列表
	 * @param lcsDetailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryCfpTeamList(CfpManagerDetailResp lcsDetailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn  = new DataTableReturn();
		if(null == lcsDetailResp || StringUtils.isBlank(lcsDetailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpTeamListResp> page  = new Page<CfpTeamListResp>(dataTable.getStart() / dataTable.getLength() +1,dataTable.getLength());
		List<CfpTeamListResp> cfpTeamListRespList = cfplannerManagerDao.queryCfpTeamList(lcsDetailResp,page);
		dataTableReturn.setData(cfpTeamListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}
	
	
	/**
	 * 查询理财师直接、间接、三级理财师
	 * @param lcsDetailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryLcsTeamDetail(CfpManagerDetailResp lcsDetailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn  = new DataTableReturn();
		if(null == lcsDetailResp || StringUtils.isBlank(lcsDetailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpTeamListResp> page  = new Page<CfpTeamListResp>(dataTable.getStart() / dataTable.getLength() +1,dataTable.getLength());
		List<CfpTeamListResp> cfpTeamListRespList = cfplannerManagerDao.queryLcsTeamDetail(lcsDetailResp,page);
		dataTableReturn.setData(cfpTeamListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}
	
	/**
	 * 查询理财师直接、间接、三级理财师(条件查询)
	 * @param lcsDetailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryLcsTeamDetailCondition(CfpManagerDetailResp lcsDetailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn  = new DataTableReturn();
		if(null == lcsDetailResp || StringUtils.isBlank(lcsDetailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpTeamListResp> page  = new Page<CfpTeamListResp>(dataTable.getStart() / dataTable.getLength() +1,dataTable.getLength());
		List<CfpTeamListResp> cfpTeamListRespList = cfplannerManagerDao.queryLcsTeamDetailCondition(lcsDetailResp,page);
		dataTableReturn.setData(cfpTeamListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}
	
	/**
	 * 奖励、津贴
	 * @param lcsDetailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryRewardAllowanceCondition(CfpManagerDetailResp lcsDetailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn  = new DataTableReturn();
		if(null == lcsDetailResp || StringUtils.isBlank(lcsDetailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpTeamListResp> page  = new Page<CfpTeamListResp>(dataTable.getStart() / dataTable.getLength() +1,dataTable.getLength());
		List<CfpTeamListResp> cfpTeamListRespList = cfplannerManagerDao.queryRewardAllowanceCondition(lcsDetailResp,page);
		for(CfpTeamListResp cfpTeam:cfpTeamListRespList){
			cfpTeam.setTeamRela(cfplannerManagerDao.queryTeamRela(lcsDetailResp.getUserId(),cfpTeam.getParentId()));
		}
		dataTableReturn.setData(cfpTeamListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}
	
	@Override
	public Map<String, Object> getLcsDateStaticCount() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("lcs", cfplannerManagerDao.getLcsDateStaticCount());
		map.put("lcsValid", cfplannerManagerDao.getValidLcsDateStaticCount());
		return map;
	}

	@Override
	public Map<String, Object> getLcsDataStatic(Map<String, Object> map) {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		Date start = (Date)map.get("startDate");
		Date end = (Date)map.get("endDate");
		int searchDay = DateUtils.countDays(start,end)+1;
		
		Map<String, Object> lcs= new LinkedHashMap<String, Object>();
		//lcs.put("total", lcsDataViewDao.getLcsDateStaticTotal(map));
		List<Map<String, Object>> lcsData = cfplannerManagerDao.getLcsDateStatic(map);
		
		List<Object> list =null;
		
		if(lcsData.size() < searchDay){
			Map<String,Object> dataMap = new LinkedHashMap<String, Object>();
			for(Map<String,Object> item:lcsData){
				dataMap.put(item.get("date").toString(),item);
			}
			list = new ArrayList<Object>();
			List<String> dates = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			for(String date:dates){
				if(!dataMap.containsKey(date)){
					Map<String,Object> temp = new HashMap<String,Object>();
					
					temp.put("date", date);
					temp.put("count", 0);
					list.add(temp);
				}else{
					list.add(dataMap.get(date));
				}
			}
		}
		
		lcs.put("data",list==null?lcsData:list);
		
		
		data.put("lcs", lcs);
		
		lcs= new LinkedHashMap<String, Object>();
		
		//lcs.put("total", lcsDataViewDao.getValidLcsDateStaticTotal(map));
		List<Map<String, Object>> validLcsDate = cfplannerManagerDao.getValidLcsDateStatic(map);
		
		
	
		list =null;
		
		if(validLcsDate.size() < searchDay){
			
			Map<String,Object> dataMap = new LinkedHashMap<String, Object>();
			for(Map<String,Object> item:validLcsDate){
				dataMap.put(item.get("date").toString(),item);
			}
			list = new ArrayList<Object>();
			List<String> dates = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			for(String date:dates){
				if(!dataMap.containsKey(date)){
					Map<String,Object> temp = new HashMap<String,Object>();
					
					temp.put("date", date);
					temp.put("count", 0);
					list.add(temp);
				}else{
					list.add(dataMap.get(date));
				}
			}
		}
		
		lcs.put("data",list==null?validLcsDate:list);
		data.put("lcsValid", lcs);
		return data;
	}

	@Override
	public LcsDetailSalesInfoResp queryLcsDetailSalesInfoResp(String userId) {
		
		LcsDetailSalesInfoResp  re = new LcsDetailSalesInfoResp();
		re.setActivityReward(cfplannerManagerDao.queryActivityReward(userId));
		re.setAllowance(cfplannerManagerDao.queryAllowance(userId));
		re.setCurrInvestAmount(cfplannerManagerDao.queryCurrInvestAmount(userId));
		re.setFee(cfplannerManagerDao.queryFee(userId));
		List<LcsHongbaoListResp> hongbaoList = cfplannerManagerDao.queryHongbaoList(userId);
		List<LcsHongbaoListResponse> hongbaoListResponse = new ArrayList<LcsHongbaoListResponse>();
		for(LcsHongbaoListResp bo : hongbaoList) {
			LcsHongbaoListResponse hong = new LcsHongbaoListResponse(bo);
			hongbaoListResponse.add(hong);
		}
		re.setHongbaoList(hongbaoListResponse);
		re.setLeaderReward(cfplannerManagerDao.queryLeaderReward(userId));
		re.setTotalSaleAmount(cfplannerManagerDao.queryTotalSaleAmount(userId));
		re.setTotalSaleCount(cfplannerManagerDao.queryTotalSaleCount(userId));
		Double totalProfit = re.getActivityReward() + re.getAllowance() + re.getFee() + re.getLeaderReward();
		re.setTotalProfit(totalProfit);
		return re;
	}

	@Override
	public DataTableReturn queryCfpLevelList(CfpManagerDetailResp lcsDetailResp,DataTable dataTable) {
		DataTableReturn dataTableReturn  = new DataTableReturn();
		if(null == lcsDetailResp || StringUtils.isBlank(lcsDetailResp.getUserId())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CrmCfpLevelRecord> page  = new Page<CrmCfpLevelRecord>(dataTable.getStart() / dataTable.getLength() +1,dataTable.getLength());
		List<CrmCfpLevelRecord> cfpLevelListRespList = cfplannerManagerDao.queryCfpLevelList(lcsDetailResp,page);
		dataTableReturn.setData(cfpLevelListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}

	
	@Override
	public DataTableReturn queryTeamAllowanceList(DataTable dataTable, ListDetailRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		Page<TeamAllowanceListResp> page = new Page<TeamAllowanceListResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<TeamAllowanceListResp> list = this.cfplannerManagerDao.queryTeamAllowanceList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
	

}
