package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.constant.ActivityConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActHelpRaiseRateMapper;
import com.linkwee.web.model.activity.ActHelpRaiseRate;
import com.linkwee.web.model.activity.ActHelpRaiseRateDetail;
import com.linkwee.web.service.ActHelpRaiseRateDetailService;
import com.linkwee.web.service.ActHelpRaiseRateService;


 /**
 * 
 * @描述：ActHelpRaiseRateService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年03月02日 11:39:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actHelpRaiseRateService")
public class ActHelpRaiseRateServiceImpl extends GenericServiceImpl<ActHelpRaiseRate, Long> implements ActHelpRaiseRateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActHelpRaiseRateServiceImpl.class);
	
	@Resource
	private ActHelpRaiseRateMapper actHelpRaiseRateMapper;
	
	@Resource
	private ActHelpRaiseRateDetailService actHelpRaiseRateDetailService;
	
	@Override
    public GenericDao<ActHelpRaiseRate, Long> getDao() {
        return actHelpRaiseRateMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActHelpRaiseRate -- 排序和模糊查询 ");
		Page<ActHelpRaiseRate> page = new Page<ActHelpRaiseRate>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActHelpRaiseRate> list = this.actHelpRaiseRateMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	@Transactional
	public Double help(String userId, String weixinIcoUrl, String weixinNickname, String openid) throws Exception {
		
		List<ActHelpRaiseRateDetail> detailForCheck = actHelpRaiseRateDetailService.queryForUpdate(userId, openid);
		if(detailForCheck != null && detailForCheck.size() > 0) {
			 throw new Exception("only_help_one");
		}
		
		ActHelpRaiseRate bo = actHelpRaiseRateMapper.queryByUserIdForUpdate(userId);
		
		int remainCount = ActivityConstant.HELP_RAISE_COUNT - bo.getHelpCount();
		Double remainRate = ActivityConstant.HELP_RAISE_RATE - bo.getRaiseRate();
		
		Double rate = getRandomMoney(remainCount, remainRate);
		
		ActHelpRaiseRate helpforUpdate = new ActHelpRaiseRate();
		helpforUpdate.setId(bo.getId());
		helpforUpdate.setHelpCount(bo.getHelpCount() + 1);
		helpforUpdate.setRaiseRate(bo.getRaiseRate() + rate);
		helpforUpdate.setLastUpdateTime(new Date());
		this.update(helpforUpdate);
		
		ActHelpRaiseRateDetail detail = new ActHelpRaiseRateDetail();
		detail.setCreateTime(new Date());
		detail.setLastUpdateTime(new Date());
		detail.setDecription(getRandDescripton());
		detail.setHelpRate(rate);
		detail.setUserId(userId);
		detail.setWeixinIcoUrl(weixinIcoUrl);
		detail.setWeixinNickname(weixinNickname);
		detail.setOpenid(openid);
		actHelpRaiseRateDetailService.insert(detail);
		
		return rate;
	}
	
	
	/**
	 * 获取随机加息值 
	 * @param remainCount 剩余次数
	 * @param remainRate 剩余利率
	 * @return
	 */
	private Double getRandomMoney(int remainCount, Double remainRate) {
		if(remainCount <= 0 ) {
			return 0.0;
		}
		if(remainRate <= 0.0) {
			return 0.0;
		}
		if(remainCount == 1) {
			return remainRate;
		}
		Random random = new Random();
		Double min = 0.01;
		Double max = remainRate / remainCount * 2;
		Double value = random.nextDouble() * max;
		value = value <= min ? 0.01 : value;
		value = Math.floor(value * 100) / 100;
		return value;
	}
	
	/**
	 * 随机助力描述
	 * @return
	 */
	private String getRandDescripton(){
		int number = new Random().nextInt(12) + 1;
		String descripton = "";
		switch (number) {
			case 1 : descripton = "我就轻轻点一下，财富给你送到家！";
			break;
			case 2 : descripton = "谈钱伤感情，那我就给你送点钱！";
			break;
			case 3 : descripton = "我的能量，超乎你想象！";
			break;
			
			case 4 : descripton = "加息的一小步，友谊的一大步！";
			break;
			case 5 : descripton = "有钱赚叫上我，等下也帮我助力一下！";
			break;
			case 6 : descripton = "能用钱解决的，从来不是问题！";
			break;
			
			case 7 : descripton = "戳了这个按钮，我们就是朋友嘛！";
			break;
			case 8 : descripton = "土豪，我想跟你做朋友很久了！";
			break;
			case 9 : descripton = "为什么有钱赚不叫我，赶紧私聊我！";
			break;
			
			case 10 : descripton = "不喜欢谈钱，但就是想你多赚一点！";
			break;
			case 11 : descripton = "我信钱生钱，更信眼见为实！";
			break;
			case 12 : descripton = "更多收益是好的，我相信你的选择！";
			break;
			
			default : descripton = "我就轻轻点一下，财富给你送到家！";
			break;
		}
		return descripton;
	}
	
	

}
