package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.controller.activity.BaseLottery;
import com.linkwee.web.controller.activity.OneYuanDrawUtil;
import com.linkwee.web.dao.ActOneYuanDrawRecordMapper;
import com.linkwee.web.model.ActOneYuanDrawRecord;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.request.PrizeSendRequest;
import com.linkwee.web.response.FortunePrizeResponse;
import com.linkwee.web.response.PrizeSendResponse;
import com.linkwee.web.service.ActOneYuanDrawRecordService;
import com.linkwee.web.service.ActWheelWinningRecordService;


 /**
 * 
 * @描述：ActOneYuanDrawRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月08日 13:46:30
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actOneYuanDrawRecordService")
public class ActOneYuanDrawRecordServiceImpl extends GenericServiceImpl<ActOneYuanDrawRecord, Long> implements ActOneYuanDrawRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActOneYuanDrawRecordServiceImpl.class);
	
	@Resource
	private ActOneYuanDrawRecordMapper actOneYuanDrawRecordMapper;
	@Resource
	private ActWheelWinningRecordService wheelWinningRecordService;
	
	@Override
    public GenericDao<ActOneYuanDrawRecord, Long> getDao() {
        return actOneYuanDrawRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActOneYuanDrawRecord -- 排序和模糊查询 ");
		Page<ActOneYuanDrawRecord> page = new Page<ActOneYuanDrawRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActOneYuanDrawRecord> list = this.actOneYuanDrawRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn queryPrizeSendList(PrizeSendRequest prizeSendRequest, DataTable dataTable) {
		Page<PrizeSendResponse> page = new Page<PrizeSendResponse>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		List<PrizeSendResponse> newsRequestList = actOneYuanDrawRecordMapper.queryPrizeSendList(prizeSendRequest,page);
		DataTableReturn dataTableReturn =new DataTableReturn();
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setData(newsRequestList);
		return dataTableReturn;
	}

	@Override
	public void sendFortunePrize(Integer prizeId,String sendOperater) {
		actOneYuanDrawRecordMapper.sendFortunePrize(prizeId,sendOperater);		
	}

	@Override
	public void addfourtune(List<BaseLottery> baseLotteryList, String bizId,String userId,Integer drawType,String activityId) {
		for(BaseLottery baseLottery : baseLotteryList){
			//随机生成手机号码
			String mobile = OneYuanDrawUtil.getTel();
    		ActOneYuanDrawRecord oneYuanDrawRecord = new ActOneYuanDrawRecord();
    		oneYuanDrawRecord.setUserId(userId);
    		oneYuanDrawRecord.setDrawTimes(1);
    		oneYuanDrawRecord.setOrderDesc(baseLottery.getPrize());
    		oneYuanDrawRecord.setMobile(mobile);
    		String wheelId = StringUtils.getUUID();
    		oneYuanDrawRecord.setWheelId(wheelId);
    		oneYuanDrawRecord.setWinningOrder(baseLottery.getId());
    		oneYuanDrawRecord.setCrtTime(new Date());
    		oneYuanDrawRecord.setBizId(bizId);
    		oneYuanDrawRecord.setIsVirtual(1);
    		oneYuanDrawRecord.setDrawType(drawType);
    		oneYuanDrawRecord.setAddressType(baseLottery.getAddressType());
    		oneYuanDrawRecord.setSendOperator("system");
    		oneYuanDrawRecord.setIssued(1);
    		actOneYuanDrawRecordMapper.insertSelective(oneYuanDrawRecord);
    		Integer resultInteger = oneYuanDrawRecord.getId();
    		int roundTime = (resultInteger / 1800) * 2 + 1;
    		int leftCount = resultInteger % 1800;
    		
    		ActWheelWinningRecord actWheelWinningRecord = new ActWheelWinningRecord();
    		actWheelWinningRecord.setIssued(1);
    		actWheelWinningRecord.setSendOperator("system");
    		
    		FortunePrizeResponse response = null;
    		if(leftCount == 200 || leftCount == 1000){
    			if(leftCount > 600){
    				roundTime += 1;
    				leftCount -= 600;
    			}
    			response = new FortunePrizeResponse(roundTime,leftCount,1,"经理体验卷"); 
    		}else if(leftCount == 400 || leftCount == 1400){
    			if(leftCount > 600){
    				roundTime += 1;
    				leftCount -= 600;
    			}
    			response = new FortunePrizeResponse(roundTime,leftCount,2,"50元京东E卡"); 
    		}else if(leftCount == 600 || leftCount == 1800){
    			if(leftCount > 600){
    				roundTime += 1;
    				leftCount -= 600;
    			}
    			response = new FortunePrizeResponse(roundTime,leftCount,3,"100元京东E卡");
    		}
    		if(response != null){		
    			actWheelWinningRecord.setUserId(userId);
    			actWheelWinningRecord.setDrawTimes(1);
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
    			actWheelWinningRecord.setIsVirtual(1);
    			wheelWinningRecordService.insert(actWheelWinningRecord);
    		}
    	}
	}

}
