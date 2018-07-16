package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.CrmCfpLevelRecordTempMapper;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.model.CrmCfpPromotionCondition;
import com.linkwee.web.response.CfpYearpurAmountResponse;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.CrmCfpPromotionConditionService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;


 /**
 * 
 * @描述：CrmCfpLevelRecordTempService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年04月10日 13:51:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpLevelRecordTempService")
public class CrmCfpLevelRecordTempServiceImpl extends GenericServiceImpl<CrmCfpLevelRecordTemp, Long> implements CrmCfpLevelRecordTempService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLevelRecordTempServiceImpl.class);
	
	@Resource
	private CrmCfpLevelRecordTempMapper crmCfpLevelRecordTempMapper;
	@Resource
	private CrmCfplannerService crmCfplannerService;
	@Resource
	private CrmCfpPromotionConditionService crmCfpPromotionConditionService;
	@Resource
	private WeiXinMsgService weiXinMsgService;
	@Resource
	private PushMessageHelper pushMessageHelper;
	@Resource
	private ConfigHelper configHelper;
	@Resource
	private SmMessageQueueService messageQueueService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Override
    public GenericDao<CrmCfpLevelRecordTemp, Long> getDao() {
        return crmCfpLevelRecordTempMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpLevelRecordTemp -- 排序和模糊查询 ");
		Page<CrmCfpLevelRecordTemp> page = new Page<CrmCfpLevelRecordTemp>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpLevelRecordTemp> list = this.crmCfpLevelRecordTempMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}


	@Override
	@Transactional(propagation=Propagation.NESTED)
	public void calculateYearpurAmount() {
		//有年化业绩（一个月之内）的理财师写理财师职级记录
		Date now = new Date();
		String startTime = DateUtils.format(DateUtils.getFirstDayOfMonth(now), DateUtils.FORMAT_SHORT);
		String endTime = DateUtils.format(now, DateUtils.FORMAT_SHORT);
		Integer month = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(now));
		List<CfpYearpurAmountResponse>  cfpYearpurAmountResponseList  = crmCfpLevelRecordTempMapper.querycfpYearpurAmount(startTime,endTime);
		List<CfpYearpurAmountResponse>  cfpYearpurAmountResponseList0  = crmCfpLevelRecordTempMapper.querycfpYearpurAmount0(startTime,endTime);
		List<CrmCfpLevelRecordTemp>  crmCfpLevelRecordTempList = new ArrayList<CrmCfpLevelRecordTemp>();
		
		//写表前删除理财师职级临时表记录
		crmCfpLevelRecordTempMapper.deleteAll();
		//初始化理财师所有的职级为TA
		crmCfplannerService.initJobgradeTemp();
		
		if(cfpYearpurAmountResponseList != null){	
			for (CfpYearpurAmountResponse cfpYpurARes : cfpYearpurAmountResponseList) {	
				CrmCfpLevelRecordTemp crmCfpLevelRecordTemp = new CrmCfpLevelRecordTemp();
				crmCfpLevelRecordTemp.setUserId(cfpYpurARes.getProfitCfplannerId());
				crmCfpLevelRecordTemp.setMonth(month);
				crmCfpLevelRecordTemp.setYearpurAmount(cfpYpurARes.getYearpurAmount());
				crmCfpLevelRecordTemp.setCurLevel("UNDETERMINED");//当前职级预归为UNDETERMINED(待定)  后期再进行计算
				crmCfpLevelRecordTemp.setPreLevel(cfpYpurARes.getCurLevel());
				crmCfpLevelRecordTemp.setStatus(1);
				crmCfpLevelRecordTemp.setCreateTime(new Date());
				crmCfpLevelRecordTempList.add(crmCfpLevelRecordTemp);
			}
		}
		
		if(cfpYearpurAmountResponseList0 != null){		
			for (CfpYearpurAmountResponse cfpYpurARes0 : cfpYearpurAmountResponseList0) {
				CrmCfpLevelRecordTemp crmCfpLevelRecordTemp = new CrmCfpLevelRecordTemp();
				crmCfpLevelRecordTemp.setUserId(cfpYpurARes0.getProfitCfplannerId());
				crmCfpLevelRecordTemp.setMonth(month);
				crmCfpLevelRecordTemp.setYearpurAmount(new BigDecimal(0));
				crmCfpLevelRecordTemp.setCurLevel(CfpJobGradeEnum.TA.getValue());//当前职级预归为TA  后期再进行计算
				crmCfpLevelRecordTemp.setPreLevel(cfpYpurARes0.getCurLevel());
				crmCfpLevelRecordTemp.setStatus(1);
				crmCfpLevelRecordTemp.setCreateTime(new Date());
				crmCfpLevelRecordTempList.add(crmCfpLevelRecordTemp);
			}
		}
		
		crmCfpLevelRecordTempMapper.insertCfpLevelRecordList(crmCfpLevelRecordTempList);
	}

	@Override
	public void calculateCfpLevel() {
		
		//计算理财师年化业绩并写表
		calculateYearpurAmount();
		
		//查询当前理财师晋升条件
		Map<String, CrmCfpPromotionCondition>  conditionMap = crmCfpPromotionConditionService.queryCrmCfpPromotionCondition();
		
		CrmCfpLevelRecordTemp crmCfpLevelRecordQ = new CrmCfpLevelRecordTemp();
		crmCfpLevelRecordQ.setStatus(1);
		crmCfpLevelRecordQ.setCurLevel("UNDETERMINED");
		List<CrmCfpLevelRecordTemp> crmCfpLevelRecordList = crmCfpLevelRecordTempMapper.selectByCondition(crmCfpLevelRecordQ);
		if(CollectionUtils.isNotEmpty(crmCfpLevelRecordList)){
			
			//计算见习理财师 和理财师顾问
			for (int i= crmCfpLevelRecordList.size()-1;i >= 0; i--) {
				CfpJobGradeEnum cfpJobGradeEnum = null;
				if(crmCfpLevelRecordList.get(i).getYearpurAmount().compareTo(new BigDecimal(conditionMap.get(CfpJobGradeEnum.TA.getValue()).getMaxYearpurAmount())) < 0){//见习理财师	
					cfpJobGradeEnum = CfpJobGradeEnum.TA;
				} else if(crmCfpLevelRecordList.get(i).getYearpurAmount().compareTo(new BigDecimal(conditionMap.get(CfpJobGradeEnum.SM1.getValue()).getMaxYearpurAmount())) < 0 
						&& crmCfpLevelRecordList.get(i).getYearpurAmount().compareTo(new BigDecimal(conditionMap.get(CfpJobGradeEnum.SM1.getValue()).getMinYearpurAmount())) >= 0){//理财师顾问
					cfpJobGradeEnum = CfpJobGradeEnum.SM1;
				} else if(crmCfpLevelRecordList.get(i).getYearpurAmount().compareTo(new BigDecimal(conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getMaxYearpurAmount())) < 0 
						&& crmCfpLevelRecordList.get(i).getYearpurAmount().compareTo(new BigDecimal(conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getMinYearpurAmount())) >= 0){//候选经理
					cfpJobGradeEnum = CfpJobGradeEnum.WAITSM2;
				} else if(crmCfpLevelRecordList.get(i).getYearpurAmount().compareTo(new BigDecimal(conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getMinYearpurAmount())) >= 0){//候选总监
					cfpJobGradeEnum = CfpJobGradeEnum.WAITSM3;
				}
				
				crmCfpLevelRecordList.get(i).setCurLevel(cfpJobGradeEnum.getValue());
				
				if(!cfpJobGradeEnum.getValue().startsWith("WAIT")){
					updateLevelRecord(crmCfpLevelRecordList.get(i));
					crmCfpLevelRecordList.remove(i);
				}
			}
			
			//计算理财师经理
			for (int i= crmCfpLevelRecordList.size()-1;i >= 0; i--) {
				String level = crmCfpLevelRecordList.get(i).getCurLevel();
				if(level.equals(CfpJobGradeEnum.WAITSM2.getValue())){//候选经理
					Map<String, Integer>  countMap = crmCfplannerService.queryJobgradeTempCount(crmCfpLevelRecordList.get(i).getUserId());
					int smCount = 0;
					for (Map.Entry<String, Integer> jobgradeEntry : countMap.entrySet()) {
						if(!jobgradeEntry.getKey().equals(CfpJobGradeEnum.TA.getValue())){
							smCount += jobgradeEntry.getValue();
						}
					}
					if(smCount >= conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getChildLevelCount()){//SM2下级人数
						level = CfpJobGradeEnum.SM2.getValue();
					} else {
						level = CfpJobGradeEnum.SM1.getValue();
					}
					crmCfpLevelRecordList.get(i).setCurLevel(level);
				}
				
				if(!level.startsWith("WAIT")){
					updateLevelRecord(crmCfpLevelRecordList.get(i));
					crmCfpLevelRecordList.remove(i);
				}
			}
			
			//计算理财师总监
			calculateCfpSM3(crmCfpLevelRecordList,conditionMap);		
		}
		//计算下级人数并写表
		calculateCfpLevelTempTypeCount();
	}

	/**
	 * 更新理财师职级记录
	 */
	@Override
	@Transactional(propagation=Propagation.NESTED)
	public void updateLevelRecord(CrmCfpLevelRecordTemp crmCfpLevelRecordTemp) {
		
		//更新理财师职级记录
		CrmCfpLevelRecordTemp crmCfpLevelRecordN = new CrmCfpLevelRecordTemp();
		crmCfpLevelRecordN.setId(crmCfpLevelRecordTemp.getId());
		crmCfpLevelRecordN.setCurLevel(crmCfpLevelRecordTemp.getCurLevel());
		crmCfpLevelRecordN.setUpdateTime(new Date());
		crmCfpLevelRecordTempMapper.updateByPrimaryKeySelective(crmCfpLevelRecordN);
		
		//更新理财师表职级
		crmCfplannerService.updatejobgradeTempByUserId(crmCfpLevelRecordTemp);
	}
	
	public void calculateCfpLevelTempTypeCount(){
		CrmCfpLevelRecordTemp crmCfpLevelRecordQ = new CrmCfpLevelRecordTemp();
		crmCfpLevelRecordQ.setStatus(1);
		List<CrmCfpLevelRecordTemp> crmCfpLevelRecordList = crmCfpLevelRecordTempMapper.selectByCondition(crmCfpLevelRecordQ);
		for (CrmCfpLevelRecordTemp crmCfpLevelRecordTemp : crmCfpLevelRecordList) {
			Map<String, Integer>  countMap = crmCfplannerService.queryJobgradeTempCount(crmCfpLevelRecordTemp.getUserId());
			crmCfpLevelRecordTemp.setTaCount(countMap.get(CfpJobGradeEnum.TA.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.TA.getValue()));
			crmCfpLevelRecordTemp.setSm1Count(countMap.get(CfpJobGradeEnum.SM1.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.SM1.getValue()));
			crmCfpLevelRecordTemp.setSm2Count(countMap.get(CfpJobGradeEnum.SM2.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.SM2.getValue()));
			crmCfpLevelRecordTemp.setSm3Count(countMap.get(CfpJobGradeEnum.SM3.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.SM3.getValue()));
			crmCfpLevelRecordTempMapper.updateByPrimaryKeySelective(crmCfpLevelRecordTemp);
		}
	}
	
	/**
	 * 递归计算理财总监
	 * @param crmCfpLevelRecordList
	 */
	private void calculateCfpSM3(List<CrmCfpLevelRecordTemp> crmCfpLevelRecordList,Map<String, CrmCfpPromotionCondition>  conditionMap){
		for (int i= crmCfpLevelRecordList.size()-1;i >= 0; i--) {
			String level = crmCfpLevelRecordList.get(i).getCurLevel();
			String userId = crmCfpLevelRecordList.get(i).getUserId();
			Map<String, Integer>  countMap = crmCfplannerService.queryJobgradeTempCount(userId);
			if(countMap.get(CfpJobGradeEnum.WAITSM3.getValue()) == null){
				Integer sM1Count = countMap.get(CfpJobGradeEnum.SM1.getValue());
				Integer sM2Count = countMap.get(CfpJobGradeEnum.SM2.getValue());
				if(sM2Count != null && sM2Count >= conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount()){//SM3下级人数
					level = CfpJobGradeEnum.SM3.getValue();
				} else if((sM1Count == null?0:sM1Count)+(sM2Count == null?0:sM2Count) >= conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getChildLevelCount()){//SM2下级人数
					level = CfpJobGradeEnum.SM2.getValue();
				} else {
					level = CfpJobGradeEnum.SM1.getValue();
				}
			}
			if(StringUtils.isNotBlank(level) && !level.startsWith("WAIT")){
				crmCfpLevelRecordList.get(i).setCurLevel(level);
				updateLevelRecord(crmCfpLevelRecordList.get(i));
				crmCfpLevelRecordList.remove(i);
			}			
		}
		
		if(crmCfpLevelRecordList.size() > 0){
			calculateCfpSM3(crmCfpLevelRecordList,conditionMap);
		}
	}
}
