package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.activity.response.OneYuanDrawRecordResponse;
import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ActWheelWinningRecordMapper;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.model.ActSignTransferRecord;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActSignInfoService;
import com.linkwee.web.service.ActSignRecordService;
import com.linkwee.web.service.ActSignTransferRecordService;
import com.linkwee.web.service.ActWheelWinningRecordService;
import com.linkwee.web.service.SmMessageQueueService;


 /**
 * 
 * @描述：ActWheelWinningRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月01日 10:55:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actWheelWinningRecordService")
public class ActWheelWinningRecordServiceImpl extends GenericServiceImpl<ActWheelWinningRecord, Long> implements ActWheelWinningRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActWheelWinningRecordServiceImpl.class);
	
	@Resource
	private ActWheelWinningRecordMapper actWheelWinningRecordMapper;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Autowired
	private ActSignRecordService signRecordService;
	
	@Autowired
	private ActSignInfoService signInfoService;
	
	@Autowired
	private ActSignTransferRecordService signTransferRecordService;
	
	@Override
    public GenericDao<ActWheelWinningRecord, Long> getDao() {
        return actWheelWinningRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActWheelWinningRecord -- 排序和模糊查询 ");
		Page<ActWheelWinningRecord> page = new Page<ActWheelWinningRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActWheelWinningRecord> list = this.actWheelWinningRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public Double queryInvestTotalMoney(String userId,String startDate,String endDate) {
		// TODO Auto-generated method stub
		return actWheelWinningRecordMapper.queryInvestTotalMoney(userId,startDate,endDate);
	}

	@Override
	public Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType) throws Exception {
		// TODO Auto-generated method stub
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		actWheelWinningRecord.setUserId(userId);
		actWheelWinningRecord.setDrawTimes(i);
		actWheelWinningRecord.setOrderDesc(baseLottery.getPrize());
		actWheelWinningRecord.setMobile(mobile);
		String wheelId = StringUtils.getUUID();
		actWheelWinningRecord.setWheelId(wheelId);
		actWheelWinningRecord.setWinningOrder(baseLottery.getId());
		actWheelWinningRecord.setCrtTime(new Date());
		if(baseLottery.getId() == 1 || baseLottery.getId() == 4){
			//给该用户账号充值
			AcAccountRecharge recharge = new AcAccountRecharge();
			recharge.setRedpacketId(actWheelWinningRecord.getWheelId());
			if(baseLottery.getId() == 1){
				recharge.setTransAmount(new BigDecimal(8));
			}else if(baseLottery.getId() == 4){
				recharge.setTransAmount(new BigDecimal(20));
			}	
			recharge.setUserId(userId);
			recharge.setUserType(userType);
			recharge.setTransType(3);
			recharge.setRemark("幸运大转盘抽取万元大奖活动奖励");
			accountbindService.accountRecharge(recharge);
			actWheelWinningRecord.setIssued(1);
		}else{
			actWheelWinningRecord.setIssued(0);
		}
		Integer resultInteger = actWheelWinningRecordMapper.insertSelective(actWheelWinningRecord);
		
		return resultInteger;
	}
	
	@Override
	public Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType, String activityId) throws Exception {
		// TODO Auto-generated method stub
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		actWheelWinningRecord.setUserId(userId);
		actWheelWinningRecord.setDrawTimes(i);
		actWheelWinningRecord.setOrderDesc(baseLottery.getPrize());
		actWheelWinningRecord.setMobile(mobile);
		actWheelWinningRecord.setExtends1(activityId);
		String wheelId = StringUtils.getUUID();
		actWheelWinningRecord.setWheelId(wheelId);
		actWheelWinningRecord.setWinningOrder(baseLottery.getId());
		actWheelWinningRecord.setCrtTime(new Date());
		if(baseLottery.getId() == 1 || baseLottery.getId() == 2){
			//给该用户账号充值
			AcAccountRecharge recharge = new AcAccountRecharge();
			recharge.setRedpacketId(actWheelWinningRecord.getWheelId());
			if(baseLottery.getId() == 1){
				recharge.setTransAmount(new BigDecimal(8));
			}else if(baseLottery.getId() == 2){
				recharge.setTransAmount(new BigDecimal(18));
			}	
			recharge.setUserId(userId);
			recharge.setUserType(userType);
			recharge.setTransType(14);
			recharge.setRemark("春节大转盘玩赚乐视超级电视活动奖励");
			accountbindService.accountRecharge(recharge);
			actWheelWinningRecord.setIssued(1);
		}else if(baseLottery.getId() == 3 || baseLottery.getId() == 4){
			ActicityRedPacketEnum acticityRedPacketEnum = (ActicityRedPacketEnum) EnumUtils.getKvmEnumByKey(baseLottery.getId(), ActicityRedPacketEnum.values());
			redPacketService.lcsActicityRedPacket(userId, acticityRedPacketEnum);
			actWheelWinningRecord.setIssued(1);
		}else{
			actWheelWinningRecord.setIssued(0);
		}
		Integer resultInteger = actWheelWinningRecordMapper.insertSelective(actWheelWinningRecord);
		
		return resultInteger;
	}

	@Override
	public PaginatorResponse<ActWheelWinningRecord> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Page<ActWheelWinningRecord> page) {
		PaginatorResponse<ActWheelWinningRecord> paginatorResponse = new PaginatorResponse<ActWheelWinningRecord>();
		List<ActWheelWinningRecord> winningRecordPageListResponses = actWheelWinningRecordMapper.queryUserPrizeRecord(actWheelWinningRecord,page);	
		paginatorResponse.setDatas(winningRecordPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Integer queryHasDrawTimes(String userId, String activityId) {
		// TODO Auto-generated method stub
		return actWheelWinningRecordMapper.queryHasDrawTimes(userId,activityId);
	}

	@Override
	public Double queryInvestorHasInvestedTotalMoney(String userId,String startDate, String endDate) {
		// TODO Auto-generated method stub
		return actWheelWinningRecordMapper.queryInvestorHasInvestedTotalMoney(userId,startDate,endDate);
	}

	@Override
	public Double queryInvestorHasInvestedTotalMoneyExceptSomePlatform(String userId, String startDate, String endDate,List<String> platformList) {
		return actWheelWinningRecordMapper.queryInvestorHasInvestedTotalMoneyExceptSomePlatform(userId,startDate,endDate,platformList);
	}
	
	@Override
	public Integer insertDrawRecordWithRemark(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType, String activityId, String remark) throws Exception {
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		actWheelWinningRecord.setUserId(userId);
		actWheelWinningRecord.setDrawTimes(i);
		actWheelWinningRecord.setOrderDesc(baseLottery.getPrize());
		actWheelWinningRecord.setMobile(mobile);
		actWheelWinningRecord.setExtends1(activityId);
		String wheelId = StringUtils.getUUID();
		actWheelWinningRecord.setWheelId(wheelId);
		actWheelWinningRecord.setWinningOrder(baseLottery.getId());
		actWheelWinningRecord.setCrtTime(new Date());
		//String content;
		if(baseLottery.getLotteryType() == 1){
			//给该用户账号充值
			AcAccountRecharge recharge = new AcAccountRecharge();
			recharge.setRedpacketId(actWheelWinningRecord.getWheelId());
			recharge.setTransAmount(baseLottery.getAmount());
			recharge.setUserId(userId);
			recharge.setUserType(userType);
			recharge.setTransType(14);
			recharge.setRemark(remark);
			accountbindService.accountRecharge(recharge);
			actWheelWinningRecord.setIssued(1);
			//content = String.format("恭喜您，抽中%s，已经派发到您的猎财大师账户，赶紧去登陆查看吧。【退订回复TD】", baseLottery.getPrize());
			//messageQueueService.sendMessageAndSysMsg(null, userId, AppTypeEnum.CHANNEL,MsgModuleEnum.LCS_ANNIVERSARY_MONEY,true,baseLottery.getPrize());
		}else if(baseLottery.getLotteryType() == 2){
			ActicityRedPacketEnum acticityRedPacketEnum = (ActicityRedPacketEnum) EnumUtils.getKvmEnumByKey(baseLottery.getId(), ActicityRedPacketEnum.values());
			redPacketService.lcsActicityRedPacket(userId, acticityRedPacketEnum);
			actWheelWinningRecord.setIssued(1);
			//content = String.format("恭喜您，抽中%s，派发后马上使用吧。【退订回复TD】", baseLottery.getPrize());
			//messageQueueService.sendMessageAndSysMsg(null, userId, AppTypeEnum.CHANNEL,MsgModuleEnum.LCS_ANNIVERSARY_REDPACKET,true,baseLottery.getPrize());
		}else{
			actWheelWinningRecord.setIssued(0);
			//content = String.format("太棒了，恭喜您抽中‘%s’活动结束后客服将会与您联系，客服电话：400-888-6987 【退订回复TD】", baseLottery.getPrize());
			//messageQueueService.sendMessageAndSysMsg(null, userId, AppTypeEnum.CHANNEL,MsgModuleEnum.LCS_ANNIVERSARY_MATTER,true,baseLottery.getPrize());
		}
		
		//messageQueueService.sendSingleMessage(mobile,AppTypeEnum.CHANNEL,content,SmsChannelTypeEnum.PROMOTION);		
		
		Integer resultInteger = actWheelWinningRecordMapper.insertSelective(actWheelWinningRecord);
		
		return resultInteger;
	}

	@Override
	public String queryInvestedMoneyExceptSomePlatform(String userId,String startDate, String endDate, List<String> platformList) {
		return actWheelWinningRecordMapper.queryInvestedMoneyExceptSomePlatform(userId,startDate,endDate,platformList);
	}

	@Override
	public PaginatorResponse<InvestRankingListResponse> investRankingList(String startDate, String endDate, List<String> platformList,Page<InvestRankingListResponse> page) {
		PaginatorResponse<InvestRankingListResponse> paginatorResponse = new PaginatorResponse<InvestRankingListResponse>();
		List<InvestRankingListResponse> rankingListResponses = actWheelWinningRecordMapper.investRankingList(startDate,endDate,platformList,page);	
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
	public InvestRankingListResponse rankingListmyRank(String userId, String startDate,String endDate,List<String> platformList) {
		return actWheelWinningRecordMapper.rankingListmyRank(userId,startDate,endDate,platformList);
	}

	@Override
	public PaginatorResponse<InvestRankingListResponse> subAndSelfInvestRankingList(String startDate, String endDate, List<String> platformList,Page<InvestRankingListResponse> page) {
		PaginatorResponse<InvestRankingListResponse> paginatorResponse = new PaginatorResponse<InvestRankingListResponse>();
		List<InvestRankingListResponse> rankingListResponses = actWheelWinningRecordMapper.subAndSelfInvestRankingList(startDate,endDate,platformList,page);	
		for(InvestRankingListResponse response : rankingListResponses){
			String mobile = response.getMobile().substring(0, 3)+"****"+response.getMobile().substring(7);
			response.setMobile(mobile);
		}
		paginatorResponse.setDatas(rankingListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public InvestRankingListResponse subAndSelfInvestMyRank(String userId,String startDate,String endDate, List<String> platformList) {		
		return actWheelWinningRecordMapper.subAndSelfInvestMyRank(userId,startDate,endDate,platformList);	
	}

}
