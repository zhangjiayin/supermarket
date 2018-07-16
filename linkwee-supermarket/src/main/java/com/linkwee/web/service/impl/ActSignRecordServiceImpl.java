package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.request.act.SignCalendarRequest;
import com.linkwee.api.response.act.BountyDetailResponse;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.api.response.act.SignRecordResponse;
import com.linkwee.api.response.act.SignResponse;
import com.linkwee.api.response.act.SignShareResponse;
import com.linkwee.api.sign.utils.ActSignUtils;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.dao.ActSignRecordMapper;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.model.ActSignInfo;
import com.linkwee.web.model.ActSignRecord;
import com.linkwee.web.service.ActSignInfoService;
import com.linkwee.web.service.ActSignRecordService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.util.AppResponseUtil;


 /**
 * 
 * @描述：ActSignRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月13日 16:59:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actSignRecordService")
public class ActSignRecordServiceImpl extends GenericServiceImpl<ActSignRecord, Long> implements ActSignRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActSignRecordServiceImpl.class);
	
	@Resource
	private ActSignRecordMapper actSignRecordMapper;
	@Resource
	private ActSignInfoService signInfoService;
	@Autowired
	private RedPacketService redPacketService;
	@Autowired
	private SysConfigService sysConfigService;
	
	@Override
    public GenericDao<ActSignRecord, Long> getDao() {
        return actSignRecordMapper;
    }

	@Override
	public boolean hasSignedToday(String userId, Integer appType) {
		ActSignRecord signRecord = new ActSignRecord();
		signRecord.setUserId(userId);
		signRecord.setUserType(appType);
		signRecord.setSignTime(new Date());
		signRecord = actSignRecordMapper.todaySign(signRecord);
		boolean result = false;
		if(signRecord != null){
			result = true;
		}
		return result;
	}

	@Override
	public ActSignRecord queryNewestSign(String userId, Integer appType) {
		ActSignRecord signRecord = new ActSignRecord();
		signRecord.setUserId(userId);
		signRecord.setUserType(appType);
		signRecord = actSignRecordMapper.queryNewestSign(signRecord);
		return signRecord;
	}

	@Override
	public BaseResponse sign(String userId, Integer appType) {
		boolean hasSigned = hasSignedToday(userId,appType);
		if(hasSigned){
			LOGGER.info("今日已签到，不可重复签到！userId={},appType={}",userId,appType);
			return new ErrorResponse("100011","今日已签到！");
		}
		int consecutiveDays = signInfoService.consecutiveDays(userId,appType);
		SignResponse signResponse = ActSignUtils.generateBonus(consecutiveDays+1);
		ActSignRecord signRecord = new ActSignRecord();
		signRecord.setUserId(userId);
		signRecord.setUserType(appType);
		signRecord.setSignAmount(signResponse.getBonus());
		signRecord.setSignTime(new Date());
		signRecord.setSignDate(new Date());
		signRecord.setBountyType(1);
		if(signResponse.getTimes() != 0){
			signRecord.setTimesAmount(signResponse.getBonus().multiply(new BigDecimal(signResponse.getTimes())));
			//连续签到翻倍
			signRecord.setTimesType(2);
		}
		//先更新汇总信息，在插入签到记录（汇总信息依赖签到记录）
		ActSignInfo signInfo = new ActSignInfo();
		signInfo.setUserId(userId);
		signInfo.setUserType(appType);
		signInfo.setSignAmount(signResponse.getBonus().multiply(new BigDecimal(signResponse.getTimes()+1)));
		signInfoService.updateSignInfo(signInfo,1);
		insert(signRecord);
		return AppResponseUtil.getSuccessResponse(signResponse);
	}

	@Override
	public BaseResponse share(String userId, Integer appType) {
		SignShareResponse response = new SignShareResponse();
		ActSignRecord signRecord = todaySign(userId,appType);
		ActSignInfo signInfoTemp = new ActSignInfo();
		signInfoTemp.setUserId(userId);
		signInfoTemp.setUserType(appType);
		signInfoTemp = signInfoService.selectOne(signInfoTemp);
		if(signRecord == null){
			return new ErrorResponse("100012","今日还未签到,不能分享!");
		}else if(signRecord != null && signRecord.getShareStatus() == 1){
			LOGGER.info("今日已分享，重复分享签到无奖励！userId={},appType={}",userId,appType);
			response.setPrizeType(0);
			response.setBouns(signRecord.getSignAmount());
			
		}else{
			if(ActSignUtils.getWeekOfDate(new Date()).equals("周一")){
				//TODO 发红包
				BaseLottery baseLottery = ActSignUtils.generateAward();
				ActicityRedPacketEnum acticityRedPacketEnum = (ActicityRedPacketEnum) EnumUtils.getKvmEnumByKey(baseLottery.getId(), ActicityRedPacketEnum.values());
				try {
					redPacketService.lcsActicityRedPacket(userId, acticityRedPacketEnum);
				} catch (Exception e) {
					LOGGER.info("发放红包失败！userId={},redpacket={}",userId,acticityRedPacketEnum.getValue());
				}
				String vlueStr = sysConfigService.getValuesByKey(acticityRedPacketEnum.getValue());
				if(StringUtils.isBlank(vlueStr)){
					LOGGER.info("send lcsActicityRedPacket The acticity code value does not exist.  userId={},acticity code={}",userId,acticityRedPacketEnum.getValue());
				}
				String[] values= StringUtils.split(vlueStr, ",");
				RedpacketResponse redpacketResponse = redPacketService.redPacketDetail(userId,values[0]);
				//解决前端取错红包金额字段的问题  下个版本需要前端解决
				redpacketResponse.setAmount(redpacketResponse.getRedpacketMoney());
				response.setPrizeType(2);
				response.setRedpacketResponse(redpacketResponse);
				signRecord.setRedpacketId(values[0]);
				ActSignInfo signInfo = new ActSignInfo();
				signInfo.setUserId(userId);
				signInfo.setUserType(appType);
				signInfo.setRedpacketCount(1);
				signInfoService.updateSignInfo(signInfo,2);
			}else if(ActSignUtils.getWeekOfDate(new Date()).equals("周六") && (signInfoTemp.getConsecutiveDays() == 6 || signInfoTemp.getConsecutiveDays() == 14 || signInfoTemp.getConsecutiveDays() == 29)){
				boolean hasShareDouble = hasShareDouble(userId,appType);
				if(!hasShareDouble){					
					response.setPrizeType(1);
					response.setBouns(signRecord.getSignAmount());
					
					signRecord.setTimesAmount(signRecord.getSignAmount());
					signRecord.setTimesType(1);
					ActSignInfo signInfo = new ActSignInfo();
					signInfo.setUserId(userId);
					signInfo.setUserType(appType);
					signInfo.setSignAmount(signRecord.getSignAmount());
					signInfoService.updateSignInfo(signInfo,1);				
				}else{
					response.setPrizeType(0);
					response.setBouns(signRecord.getSignAmount());
				}
			}else{
				boolean hasShareDouble = hasShareDouble(userId,appType);
				if((ActSignUtils.doublePrize() == 1 || ActSignUtils.getWeekOfDate(new Date()).equals("周日")) && !hasShareDouble){
					
					//没有翻倍过（连续签到翻倍），可以进行分享翻倍
					if(signRecord.getTimesAmount() == null){
						
						response.setPrizeType(1);
						response.setBouns(signRecord.getSignAmount());
						
						signRecord.setTimesAmount(signRecord.getSignAmount());
						signRecord.setTimesType(1);
						ActSignInfo signInfo = new ActSignInfo();
						signInfo.setUserId(userId);
						signInfo.setUserType(appType);
						signInfo.setSignAmount(signRecord.getSignAmount());
						signInfoService.updateSignInfo(signInfo,1);
					}else{
						response.setPrizeType(0);
						response.setBouns(signRecord.getSignAmount());
					}
				}else{
					response.setPrizeType(0);
					response.setBouns(signRecord.getSignAmount());
				}
			}
			
			signRecord.setShareStatus(1);
			update(signRecord);
		}
		
		return AppResponseUtil.getSuccessResponse(response);
	}

	private boolean hasShareDouble(String userId, Integer appType) {
		ActSignRecord signRecord = new ActSignRecord();
		signRecord.setUserId(userId);
		signRecord.setUserType(appType);
		int times = actSignRecordMapper.shareDoubleTimes(signRecord);
		if(times >= 1){
			return true;
		}
		return false;
	}

	@Override
	public ActSignRecord todaySign(String userId, Integer appType) {
		ActSignRecord signRecord = new ActSignRecord();
		signRecord.setUserId(userId);
		signRecord.setUserType(appType);
		signRecord.setSignTime(new Date());
		signRecord = actSignRecordMapper.todaySign(signRecord);
		return signRecord;
	}

	@Override
	public ActSignRecord queryLatestSign(String userId, Integer appType) {
		ActSignRecord signRecord = new ActSignRecord();
		signRecord.setUserId(userId);
		signRecord.setUserType(appType);
		signRecord = actSignRecordMapper.queryLatestSign(signRecord);
		return signRecord;
	}

	@Override
	public PaginatorResponse<SignRecordResponse> querySignRecords(String userId, Integer appType, PaginatorRequest request) {
		Page<SignRecordResponse> page  = new Page<SignRecordResponse>(request.getPageIndex(),request.getPageSize());
		PaginatorResponse<SignRecordResponse> paginatorResponse = new PaginatorResponse<SignRecordResponse>();
		List<SignRecordResponse> winningRecordPageListResponses = actSignRecordMapper.querySignRecords(userId,appType,page);	
		paginatorResponse.setDatas(winningRecordPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<String> querySignCalendar(String userId, Integer appType,SignCalendarRequest request) {
		String signTime = request.getSignTime()+"-01";
		return actSignRecordMapper.querySignCalendar(userId,appType,signTime);
	}

	@Override
	public void sendBounty(String userId, Integer userType, BigDecimal amount,int bountyType) {
		ActSignRecord signRecord = new ActSignRecord();
		signRecord.setBountyType(bountyType);
		signRecord.setSignAmount(amount);
		signRecord.setUserId(userId);
		signRecord.setUserType(userType);
		ActSignInfo signInfo = new ActSignInfo();
		signInfo.setUserId(userId);
		signInfo.setUserType(userType);
		signInfo.setSignAmount(amount);
		signInfoService.updateSignInfo(signInfo,2);
		insert(signRecord);
	}

	@Override
	public PaginatorResponse<BountyDetailResponse> queryBountyDetails(String userId, Integer appType, PaginatorRequest request) {
		Page<BountyDetailResponse> page  = new Page<BountyDetailResponse>(request.getPageIndex(),request.getPageSize());
		PaginatorResponse<BountyDetailResponse> paginatorResponse = new PaginatorResponse<BountyDetailResponse>();
		List<BountyDetailResponse> winningRecordPageListResponses = actSignRecordMapper.queryBountyDetails(userId,appType,page);	
		paginatorResponse.setDatas(winningRecordPageListResponses);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
    
}
