package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActMidautumnTaskMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MidAutumnTaskEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.model.ActMidautumnTask;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.service.ActMidautumnTaskService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.web.service.SmMessageQueueService;


 /**
 * 
 * @描述：ActMidautumnTaskService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 10:12:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actMidautumnTaskService")
public class ActMidautumnTaskServiceImpl extends GenericServiceImpl<ActMidautumnTask, Long> implements ActMidautumnTaskService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActMidautumnTaskServiceImpl.class);
	
	@Resource
	private ActMidautumnTaskMapper actMidautumnTaskMapper;
	
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private CrmOrgAcctRelService orgAcctRelService;
	
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Override
    public GenericDao<ActMidautumnTask, Long> getDao() {
        return actMidautumnTaskMapper;
    }
    
	@Override
	public PaginatorResponse<InvestRankingListResponse> investRankingList(String startDate, String endDate, List<String> platformList,Page<InvestRankingListResponse> page) {
		PaginatorResponse<InvestRankingListResponse> paginatorResponse = new PaginatorResponse<InvestRankingListResponse>();
		List<InvestRankingListResponse> rankingListResponses = actMidautumnTaskMapper.investRankingList(startDate,endDate,platformList,page);	
		for(InvestRankingListResponse response : rankingListResponses){
			BigDecimal b1 = new BigDecimal(response.getInvestAmt());   
			BigDecimal b2 = new BigDecimal("10000");     
			DecimalFormat myformat = new DecimalFormat("0.00");
			String investAmt = myformat.format(b1.divide(b2,2,BigDecimal.ROUND_DOWN))+"万";
			response.setInvestAmt(investAmt);
			String mobile = response.getMobile().substring(0, 3)+"****"+response.getMobile().substring(7);
			response.setMobile(mobile);
		}
		paginatorResponse.setDatas(rankingListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public void finishTask(String userId, MidAutumnTaskEnum midAutumnTaskEnum) {
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("midautumn_rank");
		ActivityList activity = activityListService.selectActiveOne(selectCondition);
		//活动期间才能完成活动任务
		if(activity != null){	
			ActMidautumnTask task = new ActMidautumnTask();
			task.setUserId(userId);
			task = this.selectOne(task);
			//还未记录任务完成状态
			if(task == null){
				task = new ActMidautumnTask();
				boolean regFundStatus = orgAcctRelService.hasRegFund(userId);
				if(regFundStatus){
					task.setFundRegStatus(1);
				}
				task.setUserId(userId);
				if(midAutumnTaskEnum == MidAutumnTaskEnum.FUND_REGISTER){
					task.setFundRegStatus(1);
				}else if(midAutumnTaskEnum == MidAutumnTaskEnum.INVITE_CFPLANNER){
					task.setInviteCfpStatus(1);
				}else if(midAutumnTaskEnum == MidAutumnTaskEnum.INVEST_STATUS){
					task.setInvestStatus(1);
				}
				task.setCreateTime(new Date());
				insert(task);
			}else{
				if((midAutumnTaskEnum == MidAutumnTaskEnum.FUND_REGISTER && task.getFundRegStatus() == 1) || (midAutumnTaskEnum == MidAutumnTaskEnum.INVITE_CFPLANNER && task.getInviteCfpStatus() == 1) || (midAutumnTaskEnum == MidAutumnTaskEnum.INVEST_STATUS && task.getInvestStatus() == 1)){
					return ;
				}else{
					if(midAutumnTaskEnum == MidAutumnTaskEnum.FUND_REGISTER){
						task.setFundRegStatus(1);
					}else if(midAutumnTaskEnum == MidAutumnTaskEnum.INVITE_CFPLANNER){
						task.setInviteCfpStatus(1);
					}else if(midAutumnTaskEnum == MidAutumnTaskEnum.INVEST_STATUS){
						task.setInvestStatus(1);
					}
					task.setLastUpdateTime(new Date());
					if(task.getFundRegStatus() == 1 && task.getInvestStatus() == 1 && task.getInviteCfpStatus() == 1){
						task.setTaskAllStatus(1);				
						//所有任务完成，发送短信    
						CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByInvestor(task.getUserId());
						messageQueueService.sendSingleMessage(crmCfplanner.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.LCS_ONE_HUNDRED_THOUSAND);   
					}
					update(task);
				}	
			}
		}	
	}

	@Override
	public boolean hasFinishInvestStatus(String userId, String startDate,String endDate, List<String> platformList) {
		return actMidautumnTaskMapper.hasFinishInvestStatus(userId,startDate,endDate,platformList);
	}

}
