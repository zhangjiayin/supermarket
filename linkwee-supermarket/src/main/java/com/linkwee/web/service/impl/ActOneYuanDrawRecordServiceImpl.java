package com.linkwee.web.service.impl;

import java.math.BigDecimal;
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
import com.linkwee.api.activity.response.UserWinningRecordResponse;
import com.linkwee.api.response.activity.FortunePrizeResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ActOneYuanDrawRecordMapper;
import com.linkwee.web.enums.ActicityPersonAddFeeTicketEnum;
import com.linkwee.web.enums.ActicityPersonAddFeeTicketPeriodEnum;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.model.ActOneYuanDrawRecord;
import com.linkwee.web.model.ActPersonAddfeeTicketSenduseDetail;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.model.ActSignTransferRecord;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.model.acc.AcAccountDeduct;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.ActOneYuanDrawRecordService;
import com.linkwee.web.service.ActPersonAddfeeTicketSenduseDetailService;
import com.linkwee.web.service.ActSignInfoService;
import com.linkwee.web.service.ActSignRecordService;
import com.linkwee.web.service.ActSignTransferRecordService;
import com.linkwee.web.service.ActWheelWinningRecordService;
import com.linkwee.web.service.SysConfigService;


 /**
 * 
 * @描述：ActOneYuanDrawRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月04日 11:38:32
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actOneYuanDrawRecordService")
public class ActOneYuanDrawRecordServiceImpl extends GenericServiceImpl<ActOneYuanDrawRecord, Long> implements ActOneYuanDrawRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActOneYuanDrawRecordServiceImpl.class);
	
	@Resource
	private ActOneYuanDrawRecordMapper actOneYuanDrawRecordMapper;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Autowired
	private ActSignRecordService signRecordService;
	
	@Autowired
	private ActSignInfoService signInfoService;
	
	@Autowired
	private ActSignTransferRecordService signTransferRecordService;
	
	@Autowired
	private ActWheelWinningRecordService wheelWinningRecordService;
	
	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;
	
	@Autowired
	private ActPersonAddfeeTicketSenduseDetailService personAddFeeTicketService;
	
	@Override
    public GenericDao<ActOneYuanDrawRecord, Long> getDao() {
        return actOneYuanDrawRecordMapper;
    }
      
    @Override
	@Transactional
	public FortunePrizeResponse insertOneYuanDrawRecord(BaseLottery baseLottery, Integer i,String userId, String mobile, Integer userType, String activityId,String remark,int signTimes, int liecaiTimes,String bizId,Integer drawType) throws Exception {
		ActOneYuanDrawRecord oneYuanDrawRecord = new ActOneYuanDrawRecord();
		oneYuanDrawRecord.setUserId(userId);
		oneYuanDrawRecord.setDrawTimes(i);
		oneYuanDrawRecord.setOrderDesc(baseLottery.getPrize());
		oneYuanDrawRecord.setMobile(mobile);
		String wheelId = StringUtils.getUUID();
		oneYuanDrawRecord.setWheelId(wheelId);
		oneYuanDrawRecord.setWinningOrder(baseLottery.getId());
		oneYuanDrawRecord.setCrtTime(new Date());
		oneYuanDrawRecord.setBizId(bizId);
		oneYuanDrawRecord.setIsVirtual(0);
		oneYuanDrawRecord.setDrawType(drawType);
		oneYuanDrawRecord.setAddressType(baseLottery.getAddressType());
		if(baseLottery.getLotteryType() == 1){//现金红包
			//给该用户账号充值
			AcAccountRecharge recharge = new AcAccountRecharge();
			recharge.setRedpacketId(oneYuanDrawRecord.getWheelId());
			recharge.setTransAmount(baseLottery.getAmount());
			recharge.setUserId(userId);
			recharge.setUserType(userType);
			recharge.setTransType(14);
			recharge.setRemark(remark);
			accountbindService.accountRecharge(recharge);
			oneYuanDrawRecord.setIssued(1);
			oneYuanDrawRecord.setSendOperator("system");
		}else if(baseLottery.getLotteryType() == 2){//投资返现红包
			ActicityRedPacketEnum acticityRedPacketEnum = (ActicityRedPacketEnum) EnumUtils.getKvmEnumByKey(baseLottery.getId(), ActicityRedPacketEnum.values());
			redPacketService.lcsActicityRedPacket(userId, acticityRedPacketEnum);
			oneYuanDrawRecord.setIssued(1);
			oneYuanDrawRecord.setSendOperator("system");
		}else if(baseLottery.getLotteryType() == 4){//职级体验券
			//发放职级体验券
			String useTime = DateUtils.format(DateUtils.addDay(new Date(), 1), DateUtils.FORMAT_SHORT)+" 00:00:00";
			String expiresTime = DateUtils.format(DateUtils.getLastDayOfMonth(DateUtils.addDay(new Date(), 1)), DateUtils.FORMAT_SHORT) + " 23:59:59";
			ActJobGradeVoucher vou = new ActJobGradeVoucher();
		    vou.setUserId(userId);
		    vou.setActivityAttr("一元抽奖活动");
		    vou.setExpiresTime(DateUtils.parse(expiresTime));
		    vou.setUseTime(DateUtils.parse(useTime));
		    vou.setJobGrade("SM2");
		    actJobGradeVoucherService.insertJobGradeVoucher(vou,"下一天生效职级体验券");
			oneYuanDrawRecord.setIssued(1);
			oneYuanDrawRecord.setSendOperator("system");
		}else if(baseLottery.getLotteryType() == 5){//奖励金
			BigDecimal amount = baseLottery.getAmount();
			int bountyType = 2;
			signRecordService.sendBounty(userId,userType,amount,bountyType);
			oneYuanDrawRecord.setIssued(1);
			oneYuanDrawRecord.setSendOperator("system");
		}else if(baseLottery.getLotteryType() == 6){//个人加拥券
	        ActicityPersonAddFeeTicketEnum personAddFeeTicketEnum = (ActicityPersonAddFeeTicketEnum) EnumUtils.getKvmEnumByKey(baseLottery.getId(), ActicityPersonAddFeeTicketEnum.values());
	        ActicityPersonAddFeeTicketPeriodEnum personAddFeeTicketPeriodEnum = (ActicityPersonAddFeeTicketPeriodEnum) EnumUtils.getKvmEnumByKey(baseLottery.getId(), ActicityPersonAddFeeTicketPeriodEnum.values());
	        personAddFeeTicketService.lcsActivityPersonAddFeeTicket(userId,personAddFeeTicketEnum,personAddFeeTicketPeriodEnum,remark);
			oneYuanDrawRecord.setIssued(1);
			oneYuanDrawRecord.setSendOperator("system");
		}else{//其他手动发放
			oneYuanDrawRecord.setIssued(0);		
		}
		
		//是否使用奖励金抽奖
		Integer isUseBounty = 0;
		
		if(signTimes > 0){
			isUseBounty = 1;
			//抽奖转出记录
			ActSignTransferRecord signTransferRecord = new ActSignTransferRecord();
			signTransferRecord.setTransferId(StringUtils.getUUID());
			signTransferRecord.setUserId(userId);
			signTransferRecord.setUserType(userType);
			signTransferRecord.setTransferAmount(new BigDecimal(1));
			signTransferRecord.setTransferTime(new Date());
			signTransferRecord.setTransferType(2);
			signTransferRecordService.insert(signTransferRecord);
			//更新奖励金汇总信息
			ActSignInfo temp = new ActSignInfo();
			temp.setUserId(userId);
			temp.setUserType(userType);
			temp.setTransferAmount(new BigDecimal(1));
			signInfoService.updateSignInfo(temp,2);
		}else if(liecaiTimes >0){
			isUseBounty = 2;
			//从猎财余额中扣除
			AcAccountDeduct accountDeduct = new AcAccountDeduct();
			accountDeduct.setUserId(userId);
			accountDeduct.setTransType(24);
			accountDeduct.setTransAmount(new BigDecimal(1));
			accountDeduct.setRemark("一元抽奖消耗猎财余额一元");
			accountbindService.accountDeduct(accountDeduct);
		}else {
			throw new RuntimeException("次数不够抽奖");
		}
		
		actOneYuanDrawRecordMapper.insertSelective(oneYuanDrawRecord);
		Integer resultInteger = oneYuanDrawRecord.getId();
		int roundTime = (resultInteger / 1800) * 2 + 1;
		int leftCount = resultInteger % 1800;
		
		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
		
		FortunePrizeResponse response = null;
		if(leftCount == 200 || leftCount == 1000){
			if(leftCount > 600){
				roundTime += 1;
				leftCount -= 600;
			}
			response = new FortunePrizeResponse(roundTime,leftCount,1,"总监体验卷"); 
			actWheelWinningRecord.setIssued(1);
			actWheelWinningRecord.setSendOperator("system");
			actWheelWinningRecord.setAddressType(0);
			//总监体验券发放
			String useTime = DateUtils.format(DateUtils.addDay(new Date(), 1), DateUtils.FORMAT_SHORT)+" 00:00:00";
			String expiresTime = DateUtils.format(DateUtils.getLastDayOfMonth(DateUtils.addDay(new Date(), 1)), DateUtils.FORMAT_SHORT) + " 23:59:59";
			ActJobGradeVoucher vou = new ActJobGradeVoucher();
		    vou.setUserId(userId);
		    vou.setActivityAttr("一元抽奖活动");
		    vou.setExpiresTime(DateUtils.parse(expiresTime));
		    vou.setUseTime(DateUtils.parse(useTime));
		    vou.setJobGrade("SM3");
		    actJobGradeVoucherService.insertJobGradeVoucher(vou,"下一天生效职级体验券");
		}else if(leftCount == 400 || leftCount == 1400){
			if(leftCount > 600){
				roundTime += 1;
				leftCount -= 600;
			}
			response = new FortunePrizeResponse(roundTime,leftCount,2,"50元京东E卡"); 
			actWheelWinningRecord.setIssued(0);
			actWheelWinningRecord.setAddressType(3);
		}else if(leftCount == 600 || leftCount == 0){
			if(leftCount > 600){
				roundTime += 1;
				leftCount -= 600;
			}
			if(leftCount == 0){
				roundTime -= 1;
				leftCount = 1200;
			}
			response = new FortunePrizeResponse(roundTime,leftCount,3,"100元京东E卡");
			actWheelWinningRecord.setIssued(0);
			actWheelWinningRecord.setAddressType(3);
		}
		if(response != null){		
			actWheelWinningRecord.setUserId(userId);
			actWheelWinningRecord.setDrawTimes(i);
			actWheelWinningRecord.setOrderDesc(response.getPrizeDesc());
			actWheelWinningRecord.setWinningOrder(response.getPrizeId());
			actWheelWinningRecord.setExtends2(roundTime+"");
			actWheelWinningRecord.setExtends3(leftCount+"");
			actWheelWinningRecord.setMobile(mobile);
			actWheelWinningRecord.setExtends1(activityId);
			String wheelId2 = StringUtils.getUUID();
			actWheelWinningRecord.setWheelId(wheelId2);			
			actWheelWinningRecord.setCrtTime(new Date());
			actWheelWinningRecord.setBizId(bizId);		
			actWheelWinningRecord.setIsVirtual(0);
			wheelWinningRecordService.insert(actWheelWinningRecord);
		}else{
			response = new FortunePrizeResponse();		
		}
		response.setIsUseBounty(isUseBounty);
		
		return response;
	}

	@Override
	public FortunePrizeResponse batchInsertOneYuanDrawRecord(List<BaseLottery> baseLotteryList, Integer i, String userId,String mobile, Integer userType, String activityId, String remark,int signTimes, int liecaiTimes) throws Exception {
		String bizId = StringUtils.getUUID();
		FortunePrizeResponse response = null;
		for(BaseLottery baseLottery : baseLotteryList){
			FortunePrizeResponse responseTemp = insertOneYuanDrawRecord(baseLottery,1,userId,mobile,userType,activityId,"一元抽奖活动",signTimes,liecaiTimes,bizId,baseLotteryList.size());
			if(responseTemp != null && responseTemp.getPrizeId() != 0){
				response = responseTemp;
			}
			if(responseTemp != null && responseTemp.getIsUseBounty() == 1){
				signTimes--;
			}else if(responseTemp != null && responseTemp.getIsUseBounty() == 2){
				liecaiTimes--;
			}
		}
		return response;
	}

	@Override
	public List<OneYuanDrawRecordResponse> queryOneYuanDrawRecord(String activityId) {
		return actOneYuanDrawRecordMapper.queryOneYuanDrawRecord(activityId);
	}

	@Override
	public Integer queryMaxId() {
		return actOneYuanDrawRecordMapper.queryMaxId();
	}

	@Override
	public PaginatorResponse<UserWinningRecordResponse> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Integer isfortunePrize, Page<UserWinningRecordResponse> page) {
		PaginatorResponse<UserWinningRecordResponse> paginatorResponse = new PaginatorResponse<UserWinningRecordResponse>();
		List<UserWinningRecordResponse> winningRecordPageListResponses = null;
		if(isfortunePrize == 1){
			winningRecordPageListResponses = actOneYuanDrawRecordMapper.queryUserFortunePrizeRecord(actWheelWinningRecord,page);	
		}else if(isfortunePrize == 0){
			winningRecordPageListResponses = actOneYuanDrawRecordMapper.queryUserPrizeRecord(actWheelWinningRecord,page);	
		}	 
		paginatorResponse.setDatas(winningRecordPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<FortunePrizeResponse> queryFortunePrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Page<FortunePrizeResponse> page) {
		PaginatorResponse<FortunePrizeResponse> paginatorResponse = new PaginatorResponse<FortunePrizeResponse>();
		List<FortunePrizeResponse> winningRecordPageListResponses = actOneYuanDrawRecordMapper.queryFortunePrizeRecord(actWheelWinningRecord,page);	
		for(FortunePrizeResponse responseTemp : winningRecordPageListResponses){
			String mobile = responseTemp.getMobile().substring(0, 3)+"****"+responseTemp.getMobile().substring(7);
			responseTemp.setMobile(mobile);
		}
		paginatorResponse.setDatas(winningRecordPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

}
