package com.linkwee.api.activity.advice;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.util.DateUtils;
import com.linkwee.openapi.request.InvestRecordReq;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
import com.linkwee.web.service.ActivityListService;

@Aspect
@Component
public class DoubleElevenAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoubleElevenAdvice.class);
	
	@Resource
	private ActCfpDoubleElevenActivityService doubleElevenActivityService;
	@Resource
	private ActivityListService activityListService;
	
    @AfterReturning(pointcut="execution(* com.linkwee.web.service.impl.CimProductInvestRecordServiceImpl.insertInvestRecordProcess(..))", 
        returning="returnValue")
    public void investAfterReturning(JoinPoint point, Object returnValue) {
    	InvestRecordReq investRecordReq = null;
    	Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == InvestRecordReq.class) {
        	investRecordReq = (InvestRecordReq) args[0];
        }
    	try {
    		ActivityList selectCondition = new ActivityList();
			selectCondition.setActivityCode("double_eleven");
			ActivityList activity = activityListService.selectActiveOne(selectCondition);
			if(activity != null){
				if(DateUtils.compareDate(investRecordReq.getInvestStartTime(), activity.getStartDate()) >= 0 && DateUtils.compareDate(investRecordReq.getInvestStartTime(), activity.getEndDate()) <= 0){
	    			doubleElevenActivityService.invested(investRecordReq.getUserId());
				}
			}   		
		} catch (Exception e) {
			LOGGER.warn("光棍节活动投资记录计算失败={}",JSON.toJSON(e));
		}
    }
    
    @AfterReturning(pointcut="execution(* com.linkwee.web.service.impl.CimInsuranceInfoServiceImpl.initCimInsuranceNotify(..))", 
        returning="returnValue")
    public void insuranceAfterReturning(JoinPoint point, Object returnValue) {
    	try {
    		LOGGER.info("光棍节活动保单记录计算开始");
    		CimInsuranceNotify cimInsuranceNotify = (CimInsuranceNotify) returnValue; 
    		LOGGER.info("光棍节活动保单记录计算开始={}",JSON.toJSON(cimInsuranceNotify));
    		ActivityList selectCondition = new ActivityList();
			selectCondition.setActivityCode("double_eleven");
			ActivityList activity = activityListService.selectActiveOne(selectCondition);
			if(activity != null){
				LOGGER.info("光棍节活动保单记录活动存在");
				if(DateUtils.compareDate(cimInsuranceNotify.getPayTime(), activity.getStartDate()) >= 0 && DateUtils.compareDate(cimInsuranceNotify.getPayTime(), activity.getEndDate()) <= 0){
	    			if(cimInsuranceNotify.getNotifyType() == 9){
	    				LOGGER.info("光棍节活动保单记录时间在活动期");
	            		doubleElevenActivityService.saleInsurance(cimInsuranceNotify.getUserId());
	        		}
				}
			} 		
		} catch (Exception e) {
			LOGGER.warn("光棍节活动保单记录计算失败={}",JSON.toJSON(e));
		}
    }
   
}
