package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.dao.CrmCfpLevelRecordTempMapper;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.CrmCfpLevelRecordTemp;
import com.linkwee.web.model.CrmCfpPromotionCondition;
import com.linkwee.web.model.crm.CrmCfpLevelMonth;
import com.linkwee.web.model.crm.CrmCfpLevelTemp;
import com.linkwee.web.response.CfpLevelWarningResp;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.CrmCfpPromotionConditionService;


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
	private CrmCfpLevelRecordService crmCfpLevelRecordService;
	@Resource
	private CrmCfpPromotionConditionService crmCfpPromotionConditionService;
	
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
	public CrmCfpLevelRecordTemp queryByUserId(String userId) {
		return crmCfpLevelRecordTempMapper.queryByUserId(userId);
	}

	@Override
	public CfpLevelWarningResp cfpLevelWarning(String userId) {
		CfpLevelWarningResp cfpLevelWarningResp =  new CfpLevelWarningResp();
		String cfpLevelTitle = "";//首页职级弹窗提醒
		String cfpLevelTitle2 = "";//个人中心职级弹窗提醒
		String cfpLevelContent = "";//距离下月升级xx
		String cfpLevelDetail = "";//个人年化投资额：还差xxx元|直接推荐理财师：还差xxxx
		
		//查询当前理财师晋升条件
		Map<String, CrmCfpPromotionCondition>  conditionMap = crmCfpPromotionConditionService.queryCrmCfpPromotionCondition();
		
		/**
		 * 新版本追加进度提醒
		 */
		String cfpLevelTitleNew = "";//下月晋级xx进度
		Integer yearpurAmountMaxNew = 0;//年化业绩最大金额
		Double yearpurAmountActualNew = 0d;//实际年化业绩
		int lowerLevelCfpMaxNew = 0;//直接推荐理财师最大人数
		int lowerLevelCfpActualNew = 0;//实际下级理财师人数
		String lowerLevelCfp = null;//直接推荐理财师职级
		
		
		//根据userId查询理财师当前职级和每天职级定级
		Integer monthNow = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(DateUtils.getLastDayOfLastMonth(new Date())));
		
		//每月定级
		CrmCfpLevelMonth crmCfpLevelMonth = new CrmCfpLevelMonth();
		crmCfpLevelMonth.setMonth(monthNow);
		crmCfpLevelMonth.setUserId(userId);
		crmCfpLevelMonth.setStatus(1);
		CrmCfpLevelRecord crmCfpLevelRecord = crmCfpLevelRecordService.selectMonthCfpLevel(crmCfpLevelMonth);
		if(crmCfpLevelRecord == null){
			crmCfpLevelRecord = crmCfpLevelRecordService.initCfpLevel(userId,monthNow);
		}
		
		//每天定级
		CrmCfpLevelTemp crmCfpLevelTemp = new CrmCfpLevelTemp();
		crmCfpLevelTemp.setUserId(userId);
		crmCfpLevelTemp.setStatus(1);
		CrmCfpLevelRecordTemp crmCfpLevelRecordTemp = selectTempCfpLevel(crmCfpLevelTemp);
		if(crmCfpLevelRecordTemp == null){
			crmCfpLevelRecordTemp = crmCfpLevelRecordService.initCfpLevelTemp(userId,null);
		}
		
		Double yearpurAmount = crmCfpLevelRecordTemp.getYearpurAmount();
		yearpurAmount = new BigDecimal(yearpurAmount).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		Integer sm1Count = crmCfpLevelRecordTemp.getSm1Count();
		Integer sm2Count = crmCfpLevelRecordTemp.getSm2Count();
		Integer sm3Count = crmCfpLevelRecordTemp.getSm3Count();
		
		//查询当前职级年化业绩条件
		Double minYearpurAmount =  conditionMap.get(crmCfpLevelRecord.getCurLevel()).getMinYearpurAmount().doubleValue();
		
		if(crmCfpLevelRecord != null && crmCfpLevelRecordTemp != null){
			if(crmCfpLevelRecordTemp.getCurLevelWeight() < crmCfpLevelRecord.getCurLevelWeight()){//未完成本职级条件时
				//文案范围为：顾问、经理、总监
				cfpLevelTitle = "加把劲儿|维持"+CfpJobGradeEnum.getCfpJobGradeEnumByKey(crmCfpLevelRecord.getCurLevel()).getMsg();
				if(crmCfpLevelRecord.getCurLevel().equals("SM1")){
					cfpLevelTitle2 = "距离下月维持顾问:";
					if((minYearpurAmount-yearpurAmount) > 0){					
						cfpLevelDetail += "个人年化投资额：还差"+NumberUtils.getDefaultFormatHalfDown(minYearpurAmount-yearpurAmount)+"元"+"|";
					}
				} else if(crmCfpLevelRecord.getCurLevel().equals("SM2")){
					double minChildLevelCountSm2 = conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getChildLevelCount();//SM2下级人数	
					cfpLevelTitle2 = "距离下月维持经理:";
					if((minYearpurAmount-yearpurAmount) > 0){					
						cfpLevelDetail += "个人年化投资额：还差"+NumberUtils.getDefaultFormatHalfDown(minYearpurAmount-yearpurAmount)+"元"+"|";
					}
					if((minChildLevelCountSm2-(sm1Count+sm2Count+sm3Count)) > 0){//SM2下级人数				
						cfpLevelDetail += "直接推荐理财师：还差"+(minChildLevelCountSm2 -(sm1Count+sm2Count+sm3Count))+"名顾问";
					}
				} else if(crmCfpLevelRecord.getCurLevel().equals("SM3")){
					double minChildLevelCountSm3 = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数
					cfpLevelTitle2 = "距离下月维持总监:";
					if((minYearpurAmount-yearpurAmount) > 0){					
						cfpLevelDetail += "个人年化投资额：还差"+NumberUtils.getDefaultFormatHalfDown(minYearpurAmount-yearpurAmount)+"元"+"|";
					}
					if((minChildLevelCountSm3-(sm2Count+sm3Count)) > 0){//SM3下级人数				
						cfpLevelDetail += "直接推荐理财师：还差"+(minChildLevelCountSm3-(sm2Count+sm3Count))+"名经理";
					}
				}
			} else {//完成当前职级条件
				cfpLevelTitle = "加把劲儿|再升一级";
				cfpLevelContent = "您本月职级为<"+CfpJobGradeEnum.getCfpJobGradeEnumByKey(crmCfpLevelRecord.getCurLevel()).getMsg()+">,";
				if(crmCfpLevelRecordTemp.getCurLevel().equals("TA")){
					double getMinYearpurAmount = conditionMap.get("SM1").getMinYearpurAmount().doubleValue();
					cfpLevelTitle2 = "距离下月升级顾问:";
					cfpLevelContent += "距离升级<顾问>";
					if((getMinYearpurAmount-yearpurAmount) > 0){					
						cfpLevelDetail += "个人年化投资额：还差"+NumberUtils.getDefaultFormatHalfDown(getMinYearpurAmount-yearpurAmount)+"元"+"|";
					}
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM1")){
					double minYearpurAmountSm2 = conditionMap.get("SM2").getMinYearpurAmount().doubleValue();
					double minChildLevelCountSm2 = conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getChildLevelCount();//SM2下级人数	
					cfpLevelTitle2 = "距离下月升级经理:";
					cfpLevelContent += "距离升级<经理>";
					if((minYearpurAmountSm2-yearpurAmount) > 0){					
						cfpLevelDetail += "个人年化投资额：还差"+NumberUtils.getDefaultFormatHalfDown(minYearpurAmountSm2-yearpurAmount)+"元"+"|";
					}
					if((minChildLevelCountSm2-(sm1Count+sm2Count+sm3Count)) > 0){				
						cfpLevelDetail += "直接推荐理财师：还差"+(minChildLevelCountSm2-(sm1Count+sm2Count+sm3Count))+"名顾问";
					}
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM2")){
					double minYearpurAmountSm3 = conditionMap.get("SM3").getMinYearpurAmount().doubleValue();
					double minChildLevelCountSm3 = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数
					cfpLevelTitle2 = "距离下月升级总监:";
					cfpLevelContent += "距离升级<总监>";
					if((minYearpurAmountSm3-yearpurAmount) > 0){					
						cfpLevelDetail += "个人年化投资额：还差"+NumberUtils.getDefaultFormatHalfDown(minYearpurAmountSm3-yearpurAmount)+"元"+"|";
					}
					if((minChildLevelCountSm3-(sm2Count+sm3Count)) > 0){
						cfpLevelDetail += "直接推荐理财师：还差"+(minChildLevelCountSm3-(sm2Count+sm3Count))+"名经理";
					}
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM3")){//不管何种职级，当前已完成总监全部条件
					cfpLevelTitle2 = "已完成总监所需的职级要求";
					cfpLevelTitle = "恭喜您!";
					cfpLevelContent = "您已完成总监所需的个人年化投资额和团队条件,<您下月职级将为总监,并享受总监待遇!>";
				}
			}
			
			/**
			 * 新版本追加进度提醒
			 */
			yearpurAmountActualNew = yearpurAmount;
			if(crmCfpLevelRecord.getCurLevel().equals("TA")){//见习
				if(crmCfpLevelRecordTemp.getCurLevel().equals("TA")){
					cfpLevelTitleNew = "下月升级顾问进度:";
					yearpurAmountMaxNew = conditionMap.get("SM1").getMinYearpurAmount();
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM1")){
					cfpLevelTitleNew = "下月升级经理进度:";
					yearpurAmountMaxNew = conditionMap.get("SM2").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getChildLevelCount();//SM2下级人数	
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm1Count();
					lowerLevelCfp = "顾问";
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM2")){
					cfpLevelTitleNew = "下月升级总监进度:";
					yearpurAmountMaxNew = conditionMap.get("SM3").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数	
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm2Count();
					lowerLevelCfp = "经理";
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM3")){
					cfpLevelTitleNew = "下月维持总监进度:";
					yearpurAmountMaxNew = conditionMap.get("SM3").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数	
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm2Count();
					lowerLevelCfp = "经理";
				}
			} else if(crmCfpLevelRecord.getCurLevel().equals("SM1")){//顾问
				if(crmCfpLevelRecordTemp.getCurLevel().equals("TA") || crmCfpLevelRecordTemp.getCurLevel().equals("SM1")){
					cfpLevelTitleNew = "下月升级经理进度:";
					yearpurAmountMaxNew = conditionMap.get("SM2").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM2.getValue()).getChildLevelCount();//SM2下级人数
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm1Count();
					lowerLevelCfp = "顾问";
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM2")){
					cfpLevelTitleNew = "下月升级总监进度:";
					yearpurAmountMaxNew = conditionMap.get("SM3").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数	
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm2Count();
					lowerLevelCfp = "经理";
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM3")){
					cfpLevelTitleNew = "下月维持总监进度:";
					yearpurAmountMaxNew = conditionMap.get("SM3").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数	
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm2Count();
					lowerLevelCfp = "经理";
				}				
			} else if(crmCfpLevelRecord.getCurLevel().equals("SM2")){//经理
				if(crmCfpLevelRecordTemp.getCurLevel().equals("TA") || crmCfpLevelRecordTemp.getCurLevel().equals("SM1") || crmCfpLevelRecordTemp.getCurLevel().equals("SM2")){
					cfpLevelTitleNew = "下月升级总监进度:";
					yearpurAmountMaxNew = conditionMap.get("SM3").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数	
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm2Count();
					lowerLevelCfp = "经理";
				} else if(crmCfpLevelRecordTemp.getCurLevel().equals("SM3")){
					cfpLevelTitleNew = "下月维持总监进度:";
					yearpurAmountMaxNew = conditionMap.get("SM3").getMinYearpurAmount();
					lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数	
					lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm2Count();
					lowerLevelCfp = "经理";
				}				
			} else if(crmCfpLevelRecord.getCurLevel().equals("SM3")){//总监
				cfpLevelTitleNew = "下月维持总监进度:";
				yearpurAmountMaxNew = conditionMap.get("SM3").getMinYearpurAmount();
				lowerLevelCfpMaxNew = conditionMap.get(CfpJobGradeEnum.SM3.getValue()).getChildLevelCount();//SM3下级人数	
				lowerLevelCfpActualNew = crmCfpLevelRecordTemp.getSm2Count();
				lowerLevelCfp = "经理";
			}
		}
		
		cfpLevelWarningResp.setCfpLevelTitle(cfpLevelTitle);
		cfpLevelWarningResp.setCfpLevelTitle2(cfpLevelTitle2);
		cfpLevelWarningResp.setCfpLevelContent(cfpLevelContent);
		cfpLevelWarningResp.setCfpLevelDetail(cfpLevelDetail);
		
		/**
		 * 新版本追加进度提醒
		 */
		cfpLevelWarningResp.setCfpLevelTitleNew(cfpLevelTitleNew);
		cfpLevelWarningResp.setYearpurAmountMaxNew(yearpurAmountMaxNew);
		cfpLevelWarningResp.setYearpurAmountActualNew(yearpurAmountActualNew);
		cfpLevelWarningResp.setLowerLevelCfpMaxNew(lowerLevelCfpMaxNew);
		cfpLevelWarningResp.setLowerLevelCfpActualNew(lowerLevelCfpActualNew);
		cfpLevelWarningResp.setCfpLevelNextMonth(DateUtils.getFirstDayOfNextMonth(new Date()));
		cfpLevelWarningResp.setLowerLevelCfp(lowerLevelCfp);
		
		return cfpLevelWarningResp;
	}

	@Override
	public CrmCfpLevelRecordTemp selectTempCfpLevel(CrmCfpLevelTemp crmCfpLevelTemp) {
		// TODO Auto-generated method stub
		return crmCfpLevelRecordTempMapper.selectTempCfpLevel(crmCfpLevelTemp);
	}
}
