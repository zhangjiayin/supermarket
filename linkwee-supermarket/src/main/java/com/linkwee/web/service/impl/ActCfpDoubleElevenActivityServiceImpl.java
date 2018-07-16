package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.funds.ifast.GetOrderListRequest;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ActCfpDoubleElevenActivityMapper;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.model.ActCfpDoubleElevenActivity;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.web.service.IfastFundService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;


 /**
 * 
 * @描述：ActCfpDoubleElevenActivityService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actCfpDoubleElevenActivityService")
public class ActCfpDoubleElevenActivityServiceImpl extends GenericServiceImpl<ActCfpDoubleElevenActivity, Long> implements ActCfpDoubleElevenActivityService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActCfpDoubleElevenActivityServiceImpl.class);
	
	@Resource
	private ActCfpDoubleElevenActivityMapper actCfpDoubleElevenActivityMapper;
	@Resource
	private CrmCfplannerService cfplannerService;
	@Resource
	private IfastFundService ifastFundService;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private CrmInvestorService investorService;
	@Resource
	private ActivityListService activityListService;
	@Autowired
	private RedPacketService redPacketService;
	@Resource
	private SmMessageQueueService messageQueueService;
	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;
	@Resource
	private SysConfigService sysConfigService;
	
	@Override
    public GenericDao<ActCfpDoubleElevenActivity, Long> getDao() {
        return actCfpDoubleElevenActivityMapper;
    }
    
	@Override
	public boolean hasSaleFund(String userId) {
		if(!"undefined".equals(userId)){	
			List<CrmInvestor> investorList = cfplannerService.queryInvestorList(userId);
			for(CrmInvestor investor : investorList){
				GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
				if(crmOrgAcctRelService.ifOrgAccountExist(investor.getUserId(), getOrderListRequest.getOrgCode())){						
					getOrderListRequest.setUserId(investor.getUserId());
					LOGGER.info("【奕丰金融】获取用户的订单列表	getOrderListRequest={}",getOrderListRequest);
					boolean hasOrder = false;
					if(hasOrder){
						saleFund(investor.getUserId());
						return true;
					}
				} 
			}
		} else {
		    return false;
		}
		return false;
	}

	@Override
	public void invested(String userId) throws Exception {
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("double_eleven");
		ActivityList activity = activityListService.selectActiveOne(selectCondition);
		if(activity == null){
			return;
		}
		CrmInvestor temp = new CrmInvestor();
		temp.setUserId(userId);
		temp = investorService.selectOne(temp);
		String cfpUserId = temp.getCfplanner();
		if(StringUtils.isNotBlank(cfpUserId)){
			ActCfpDoubleElevenActivity activityTemp = new ActCfpDoubleElevenActivity();
			activityTemp.setUserId(cfpUserId);
			activityTemp = selectOne(activityTemp);
			CrmCfplanner crmCfplanner = cfplannerService.queryCfplannerByUserId(cfpUserId);
			if(activityTemp != null){
				int saleNum = activityTemp.getSaleNum();				 
				if(saleNum == 0){
					//第1笔出单  发红包
					redPacketService.sendDoubleElevenRedpacket(cfpUserId, ActicityRedPacketEnum.DOUBLE_ELEVEN_11);				 
					messageQueueService.sendSingleMessage(crmCfplanner.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.DOUBLE_ELEVEN_ACTIVITY_BONUS,"1","11.00");
				}else if(saleNum == 10){
					//第11笔出单  设置第11笔订单的时间
					activityTemp.setElevenOrderTime(new Date());
				}else if(saleNum == 20){
					//第21笔出单  发红包
					redPacketService.sendDoubleElevenRedpacket(cfpUserId, ActicityRedPacketEnum.DOUBLE_ELEVEN_111);
					messageQueueService.sendSingleMessage(crmCfplanner.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.DOUBLE_ELEVEN_ACTIVITY_BONUS,"21","111.00");
				}else if(saleNum == 30){
					//第31笔出单  发红包
					redPacketService.sendDoubleElevenRedpacket(cfpUserId, ActicityRedPacketEnum.DOUBLE_ELEVEN_1111);
					messageQueueService.sendSingleMessage(crmCfplanner.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.DOUBLE_ELEVEN_ACTIVITY_BONUS,"31","1111.00","大礼包");
				}
				activityTemp.setSaleNum(saleNum+1);
				activityTemp.setLastUpdateTime(new Date());
				update(activityTemp);
			}else{
				//第1笔出单  发红包 插入记录
				redPacketService.sendDoubleElevenRedpacket(cfpUserId, ActicityRedPacketEnum.DOUBLE_ELEVEN_11);
				messageQueueService.sendSingleMessage(crmCfplanner.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.DOUBLE_ELEVEN_ACTIVITY_BONUS,"1","11.00");
				activityTemp = new ActCfpDoubleElevenActivity();
				activityTemp.setUserId(cfpUserId);
				activityTemp.setCreateTime(new Date());
				activityTemp.setSaleNum(1);
				insert(activityTemp);
			}
		}
	}

	@Override
	public void saleInsurance(String userId) {
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("double_eleven");
		ActivityList activity = activityListService.selectActiveOne(selectCondition);
		if(activity == null){
			return;
		}
		CrmInvestor temp = new CrmInvestor();
		temp.setUserId(userId);
		temp = investorService.selectOne(temp);
		String cfpUserId = temp.getCfplanner();
		if(StringUtils.isNotBlank(cfpUserId)){
			ActCfpDoubleElevenActivity activityTemp = new ActCfpDoubleElevenActivity();
			activityTemp.setUserId(cfpUserId);
			activityTemp = selectOne(activityTemp);
			String useTime = sysConfigService.getValuesByKey("double_eleven_jobGrade_SM3_use_time");
			String expiresTime = sysConfigService.getValuesByKey("double_eleven_jobGrade_SM3_expires_time");
			if(activityTemp != null){
				if(activityTemp.getInsuranceStatus() == 0){
					activityTemp.setInsuranceStatus(1);
					activityTemp.setLastUpdateTime(new Date());
					update(activityTemp);
					//出单保险 发放职级体验券
					ActJobGradeVoucher vou = new ActJobGradeVoucher();
					vou.setUserId(cfpUserId);
				    vou.setActivityAttr("光棍节活动");
				    vou.setExpiresTime(DateUtils.parse(expiresTime));
				    vou.setUseTime(DateUtils.parse(useTime));
				    vou.setJobGrade("SM3");
				    actJobGradeVoucherService.insertJobGradeVoucher(vou);
				}			
			}else{
				//出单保险 发放职级体验券
				activityTemp = new ActCfpDoubleElevenActivity();
				activityTemp.setUserId(cfpUserId);
				activityTemp.setCreateTime(new Date());
				activityTemp.setInsuranceStatus(1);
				insert(activityTemp);
				ActJobGradeVoucher vou = new ActJobGradeVoucher();
				vou.setUserId(cfpUserId);
			    vou.setActivityAttr("光棍节活动");
			    vou.setExpiresTime(DateUtils.parse(expiresTime));
			    vou.setUseTime(DateUtils.parse(useTime));
			    vou.setJobGrade("SM3");
			    actJobGradeVoucherService.insertJobGradeVoucher(vou);
			}
		}
	}

	@Override
	public void saleFund(String userId) {
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("double_eleven");
		ActivityList activity = activityListService.selectActiveOne(selectCondition);
		if(activity == null){
			return;
		}
		CrmInvestor temp = new CrmInvestor();
		temp.setUserId(userId);
		temp = investorService.selectOne(temp);
		String cfpUserId = temp.getCfplanner();
		if(StringUtils.isNotBlank(cfpUserId)){
			ActCfpDoubleElevenActivity activityTemp = new ActCfpDoubleElevenActivity();
			activityTemp.setUserId(cfpUserId);
			activityTemp = selectOne(activityTemp);
			String useTime = sysConfigService.getValuesByKey("double_eleven_jobGrade_SM2_use_time");
			String expiresTime = sysConfigService.getValuesByKey("double_eleven_jobGrade_SM2_expires_time");
			if(activityTemp != null){
				if(activityTemp.getFundStatus() == 0){
					activityTemp.setFundStatus(1);
					activityTemp.setLastUpdateTime(new Date());
					update(activityTemp);
					//出单基金 发放职级体验券
					ActJobGradeVoucher vou = new ActJobGradeVoucher();
				    vou.setUserId(cfpUserId);
				    vou.setActivityAttr("光棍节活动");
				    vou.setExpiresTime(DateUtils.parse(expiresTime));
				    vou.setUseTime(DateUtils.parse(useTime));
				    vou.setJobGrade("SM2");
				    actJobGradeVoucherService.insertJobGradeVoucher(vou);
				}			
			}else{
				//出单基金 发放职级体验券
				activityTemp = new ActCfpDoubleElevenActivity();
				activityTemp.setUserId(cfpUserId);
				activityTemp.setCreateTime(new Date());
				activityTemp.setFundStatus(1);
				insert(activityTemp);
				ActJobGradeVoucher vou = new ActJobGradeVoucher();
				vou.setUserId(cfpUserId);
			    vou.setActivityAttr("光棍节活动");
			    vou.setExpiresTime(DateUtils.parse(expiresTime));
			    vou.setUseTime(DateUtils.parse(useTime));
			    vou.setJobGrade("SM2");
			    actJobGradeVoucherService.insertJobGradeVoucher(vou);
			}
		}
	}

}
