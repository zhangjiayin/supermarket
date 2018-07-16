package com.linkwee.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActCfpDoubleElevenActivity;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.request.funds.ifast.GetOrderUserInfoRequest;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
@Component
public class DoubleElevenSaleFundJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoubleElevenSaleFundJob.class);
	
	@Resource
	private CrmInvestorService investorService;
	@Resource
	private ActivityListService activityListService;
	@Resource
	private ActCfpDoubleElevenActivityService actCfpDoubleElevenActivityService;
	@Resource
	private CrmUserInfoService userInfoService;
	@Resource
	private CrmCfplannerService cfplannerService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		ActivityList selectCondition = new ActivityList();
		selectCondition.setActivityCode("double_eleven");
		ActivityList activity = activityListService.selectOne(selectCondition);
		if(activity == null){
			return;
		}
		GetOrderUserInfoRequest getOrderUserInfoRequest = new GetOrderUserInfoRequest();
		getOrderUserInfoRequest.setStartTime(activity.getStartDate());
		getOrderUserInfoRequest.setEndTime(activity.getEndDate());
		List<CrmUserInfo> userList = userInfoService.getUserInfoByFundOrderTime(getOrderUserInfoRequest);
		if(userList == null){
			return;
		}
		for(CrmUserInfo user : userList){
			CrmInvestor temp = new CrmInvestor();
			temp.setUserId(user.getUserId());
			temp = investorService.selectOne(temp);
			String cfpUserId = temp.getCfplanner();
			if(StringUtils.isNotBlank(cfpUserId)){
				ActCfpDoubleElevenActivity activityTemp = new ActCfpDoubleElevenActivity();
				activityTemp.setUserId(cfpUserId);
				activityTemp = actCfpDoubleElevenActivityService.selectOne(activityTemp);
				String useTime = sysConfigService.getValuesByKey("double_eleven_jobGrade_SM3_use_time");
				String expiresTime = sysConfigService.getValuesByKey("double_eleven_jobGrade_SM3_expires_time");
				if(activityTemp != null){
					if(activityTemp.getFundStatus() == 0){
						activityTemp.setFundStatus(1);
						activityTemp.setLastUpdateTime(new Date());
						actCfpDoubleElevenActivityService.update(activityTemp);
						//出单基金 发放职级体验券
						ActJobGradeVoucher vou = new ActJobGradeVoucher();
						vou.setUserId(cfpUserId);
					    vou.setActivityAttr("光棍节活动");
					    vou.setExpiresTime(DateUtils.parse(expiresTime));
					    vou.setUseTime(DateUtils.parse(useTime));
					    vou.setJobGrade("SM2");
					    try {
							actJobGradeVoucherService.insertJobGradeVoucher(vou);
						} catch (Exception e) {
							LOGGER.info("发放职级体验券异常：vou={},exception={}",JSON.toJSONString(vou),JSON.toJSONString(e));
						}
					}			
				}else{
					//出单基金 发放职级体验券
					activityTemp = new ActCfpDoubleElevenActivity();
					activityTemp.setUserId(cfpUserId);
					activityTemp.setCreateTime(new Date());
					activityTemp.setFundStatus(1);
					actCfpDoubleElevenActivityService.insert(activityTemp);
					ActJobGradeVoucher vou = new ActJobGradeVoucher();
					vou.setUserId(cfpUserId);
				    vou.setActivityAttr("光棍节活动");
				    vou.setExpiresTime(DateUtils.parse(expiresTime));
				    vou.setUseTime(DateUtils.parse(useTime));
				    vou.setJobGrade("SM2");
				    try {
						actJobGradeVoucherService.insertJobGradeVoucher(vou);
					} catch (Exception e) {
						LOGGER.info("发放职级体验券异常：vou={},exception={}",JSON.toJSONString(vou),JSON.toJSONString(e));
					}
				}
			}
		}
	}

}
