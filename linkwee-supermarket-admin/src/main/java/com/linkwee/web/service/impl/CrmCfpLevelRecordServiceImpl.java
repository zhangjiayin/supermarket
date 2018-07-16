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
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.CrmCfpLevelRecordMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.CrmCfpPromotionCondition;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.BatchChangeGrade;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.weixin.WeiXinMsgRequest;
import com.linkwee.web.request.CfpLevelStatisticsRequest;
import com.linkwee.web.response.CfpLevelDataStatisticsListResp;
import com.linkwee.web.response.CfpYearpurAmountResponse;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.CrmCfpPromotionConditionService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;


 /**
 * 
 * @描述：CrmCfpLevelRecordService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpLevelRecordService")
public class CrmCfpLevelRecordServiceImpl extends GenericServiceImpl<CrmCfpLevelRecord, Long> implements CrmCfpLevelRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpLevelRecordServiceImpl.class);
	
	@Resource
	private CrmCfpLevelRecordMapper crmCfpLevelRecordMapper;
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
	@Resource
	private SysMsgService sysMsgService;
	
	@Override
    public GenericDao<CrmCfpLevelRecord, Long> getDao() {
        return crmCfpLevelRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpLevelRecord -- 排序和模糊查询 ");
		Page<CrmCfpLevelRecord> page = new Page<CrmCfpLevelRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpLevelRecord> list = this.crmCfpLevelRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void calculateYearpurAmount() {
		//有年化业绩（一个月之内）的理财师写理财师职级记录
		Date now = new Date();
		Date firstDayOfLastMonthDate = DateUtils.getFirstDayOfMonth(DateUtils.getLastDayOfLastMonth(now));
		String startTime = DateUtils.format(firstDayOfLastMonthDate, DateUtils.FORMAT_SHORT);
		String endTime = DateUtils.format(DateUtils.getFirstDayOfMonth(now), DateUtils.FORMAT_SHORT);
		Integer month = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(firstDayOfLastMonthDate));
		List<CfpYearpurAmountResponse>  cfpYearpurAmountResponseList  = crmCfpLevelRecordMapper.querycfpYearpurAmount(startTime,endTime);
		List<CfpYearpurAmountResponse>  cfpYearpurAmountResponseList0  = crmCfpLevelRecordMapper.querycfpYearpurAmount0(startTime,endTime);
		List<CrmCfpLevelRecord>  crmCfpLevelRecordList = new ArrayList<CrmCfpLevelRecord>();
		
		//写表前更新理财师职级记录当前 有效-->无效
		crmCfpLevelRecordMapper.updateYearpurAmountNow();
		//初始化理财师所有的职级为TA
		crmCfplannerService.initJobgrade();
		
		if(cfpYearpurAmountResponseList != null){		
			for (CfpYearpurAmountResponse cfpYpurARes : cfpYearpurAmountResponseList) {	
				CrmCfpLevelRecord crmCfpLevelRecord = new CrmCfpLevelRecord();
				crmCfpLevelRecord.setUserId(cfpYpurARes.getProfitCfplannerId());
				crmCfpLevelRecord.setMonth(month);
				crmCfpLevelRecord.setYearpurAmount(cfpYpurARes.getYearpurAmount());
				crmCfpLevelRecord.setCurLevel("UNDETERMINED");//当前职级预归为UNDETERMINED(待定)  后期再进行计算
				crmCfpLevelRecord.setPreLevel(cfpYpurARes.getCurLevel());
				crmCfpLevelRecord.setStatus(1);
				crmCfpLevelRecord.setCreateTime(new Date());
				crmCfpLevelRecordList.add(crmCfpLevelRecord);
			}
		}
		
		if(cfpYearpurAmountResponseList0 != null){	
			for (CfpYearpurAmountResponse cfpYpurARes0 : cfpYearpurAmountResponseList0) {
				CrmCfpLevelRecord crmCfpLevelRecord = new CrmCfpLevelRecord();
				crmCfpLevelRecord.setUserId(cfpYpurARes0.getProfitCfplannerId());
				crmCfpLevelRecord.setMonth(month);
				crmCfpLevelRecord.setYearpurAmount(new BigDecimal(0));
				crmCfpLevelRecord.setCurLevel(CfpJobGradeEnum.TA.getValue());//当前职级预归为TA  后期再进行计算
				crmCfpLevelRecord.setPreLevel(cfpYpurARes0.getCurLevel());
				crmCfpLevelRecord.setStatus(1);
				crmCfpLevelRecord.setCreateTime(new Date());
				crmCfpLevelRecordList.add(crmCfpLevelRecord);
			}
		}
		
		crmCfpLevelRecordMapper.insertCfpLevelRecordList(crmCfpLevelRecordList);
		
		for (CrmCfpLevelRecord crmCfpLevelRecord : crmCfpLevelRecordList) {
			//推送消息
			sendCfpLevelMsg(crmCfpLevelRecord);
		}
	}

	@Override
	public void calculateCfpLevel() {
		
		//计算理财师年化业绩（一个月之内）并写表
		calculateYearpurAmount();
		
		//查询当前理财师晋升条件
		Map<String, CrmCfpPromotionCondition>  conditionMap = crmCfpPromotionConditionService.queryCrmCfpPromotionCondition();
		
		CrmCfpLevelRecord crmCfpLevelRecordQ = new CrmCfpLevelRecord();
		crmCfpLevelRecordQ.setStatus(1);
		crmCfpLevelRecordQ.setCurLevel("UNDETERMINED");
		List<CrmCfpLevelRecord> crmCfpLevelRecordList = crmCfpLevelRecordMapper.selectByCondition(crmCfpLevelRecordQ);
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
				} else {
					//更新理财师表职级
					crmCfplannerService.updatejobgradeByUserId(crmCfpLevelRecordList.get(i));
				}
			}
			
			//计算理财师经理
			for (int i= crmCfpLevelRecordList.size()-1;i >= 0; i--) {
				String level = crmCfpLevelRecordList.get(i).getCurLevel();
				if(level.equals(CfpJobGradeEnum.WAITSM2.getValue())){//候选经理
					Map<String, Integer>  countMap = crmCfplannerService.queryJobgradeCount(crmCfpLevelRecordList.get(i).getUserId());
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
		calculateCfpLevelTypeCount();
	}

	/**
	 * 更新理财师职级记录
	 */
	@Override
	public void updateLevelRecord(CrmCfpLevelRecord crmCfpLevelRecord) {
		
		//更新理财师职级记录
		CrmCfpLevelRecord crmCfpLevelRecordN = new CrmCfpLevelRecord();
		crmCfpLevelRecordN.setId(crmCfpLevelRecord.getId());
		crmCfpLevelRecordN.setCurLevel(crmCfpLevelRecord.getCurLevel());
		crmCfpLevelRecordN.setUpdateTime(new Date());
		crmCfpLevelRecordMapper.updateByPrimaryKeySelective(crmCfpLevelRecordN);
		
		//更新理财师表职级
		crmCfplannerService.updatejobgradeByUserId(crmCfpLevelRecord);
		
		//推送消息
		sendCfpLevelMsg(crmCfpLevelRecord);
	}
	
	public void calculateCfpLevelTypeCount(){
		CrmCfpLevelRecord crmCfpLevelRecordQ = new CrmCfpLevelRecord();
		crmCfpLevelRecordQ.setStatus(1);
		List<CrmCfpLevelRecord> crmCfpLevelRecordList = crmCfpLevelRecordMapper.selectByCondition(crmCfpLevelRecordQ);
		for (CrmCfpLevelRecord crmCfpLevelRecord : crmCfpLevelRecordList) {
			Map<String, Integer>  countMap = crmCfplannerService.queryJobgradeCount(crmCfpLevelRecord.getUserId());
			crmCfpLevelRecord.setTaCount(countMap.get(CfpJobGradeEnum.TA.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.TA.getValue()));
			crmCfpLevelRecord.setSm1Count(countMap.get(CfpJobGradeEnum.SM1.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.SM1.getValue()));
			crmCfpLevelRecord.setSm2Count(countMap.get(CfpJobGradeEnum.SM2.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.SM2.getValue()));
			crmCfpLevelRecord.setSm3Count(countMap.get(CfpJobGradeEnum.SM3.getValue()) == null ? 0:countMap.get(CfpJobGradeEnum.SM3.getValue()));
			crmCfpLevelRecordMapper.updateByPrimaryKeySelective(crmCfpLevelRecord);
		}
	}
	
	/**
	 * 递归计算理财总监
	 * @param crmCfpLevelRecordList
	 */
	private void calculateCfpSM3(List<CrmCfpLevelRecord> crmCfpLevelRecordList,Map<String, CrmCfpPromotionCondition>  conditionMap){
		for (int i= crmCfpLevelRecordList.size()-1;i >= 0; i--) {
			String level = crmCfpLevelRecordList.get(i).getCurLevel();
			String userId = crmCfpLevelRecordList.get(i).getUserId();
			Map<String, Integer>  countMap = crmCfplannerService.queryJobgradeCount(userId);
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
	
	public void sendCfpLevelMsg(final CrmCfpLevelRecord crmCfpLevelRecord){
		//只有等级发生变化时才推送消息
		if(!crmCfpLevelRecord.getPreLevel().equals(crmCfpLevelRecord.getCurLevel()) && !"UNDETERMINED".equals(crmCfpLevelRecord.getCurLevel()) && !crmCfpLevelRecord.getCurLevel().startsWith("WAIT")){
			//职级value
			final String cfpLevel = crmCfpLevelRecord.getCurLevel();
			//职级名称
			final String cfpLevelName = CfpJobGradeEnum.getCfpJobGradeEnumByKey(cfpLevel).getMsg();
			
			final String wxreqRemark;
			final String msgContent;
			if(cfpLevel.equals(CfpJobGradeEnum.TA.getValue()) || cfpLevel.equals(CfpJobGradeEnum.SM1.getValue())){//见习/顾问
				msgContent = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCSJJTZ_JXGW), cfpLevelName);
				wxreqRemark = "您本月将无法获得直接管理津贴和团队管理津贴，销售佣金和推荐奖励正常发放。加油吧，少年！";
			} else {//经理/总监
				msgContent = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCSJJTZ_JLZJ), cfpLevelName);
				wxreqRemark = "本月将按照最新职级为您核算并发放津贴。继续加油吧";
			}
			
			//通知栏+个人消息中心
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					if(cfpLevel.equals(CfpJobGradeEnum.SM2.getValue()) || cfpLevel.equals(CfpJobGradeEnum.SM3.getValue())){
						//通知栏+个人消息中心
						CommonTCSResult khjblcsBL = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.LCSJJTZ, crmCfpLevelRecord.getUserId(), "晋级通知",  msgContent,  null, true);
						LOGGER.info("理财师晋级通知推送消息： {}",khjblcsBL.getCode() == 0 ? "推送成功" : "推送失败");	
						
						//推送微信消息 
						final WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
						wxreq.setUseId(crmCfpLevelRecord.getUserId());
						wxreq.setTemkey(SysConfigConstant.GRADE_CHANGE);
						wxreq.setChangeType("职级评定为"+cfpLevelName);//变更类型
						wxreq.setUseType(String.valueOf(1));
						wxreq.setRemark(wxreqRemark);
						weiXinMsgService.sendWeiXinMsgCommon(wxreq);
					}else{
						SysMsg sysmsg = new SysMsg();
	       				sysmsg.setContent(msgContent);
	       				sysmsg.setStatus(0);// 发布
	       				sysmsg.setUserNumber(crmCfpLevelRecord.getUserId());
	       				sysmsg.setReadStatus(0);// 未读
	       				sysmsg.setAppType(1);//理财师
	       				sysmsg.setStartTime(new Date());
	       				sysmsg.setCrtTime(new Date());
	       				sysmsg.setModifyTime(new Date());
	       				sysMsgService.addMsg(sysmsg);
					}
				}
			});
		}
	}
	
	/**
	 * 更新理财师职级
	 */
	@Override
	public int updateLevelRecord(String userId, String jobGrade,String currentUser) {
		CrmCfpLevelRecord crmCfpLevelRecord = new CrmCfpLevelRecord();
		crmCfpLevelRecord.setUserId(userId);
		crmCfpLevelRecord.setStatus(1);
		CrmCfpLevelRecord temp = crmCfpLevelRecordMapper.selectOneByCondition(crmCfpLevelRecord);
		if(temp != null){
			crmCfpLevelRecord.setStatus(0);
			crmCfpLevelRecordMapper.updateLevelRecordByUserId(crmCfpLevelRecord);
			temp.setPreLevel(temp.getCurLevel());
		}else{
			temp = new CrmCfpLevelRecord();
			temp.setUserId(userId);
			temp.setPreLevel(CfpJobGradeEnum.TA.getValue());
			temp.setMonth(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(DateUtils.getLastDayOfLastMonth(new Date()))));
			temp.setYearpurAmount(new BigDecimal(0));
			temp.setCurLevelWeight(10);//对于已经确定为TA的  直接写写死其权重
		}
		
		temp.setId(null);		
		temp.setCurLevel(jobGrade);
		temp.setOptType(2);
//		String currentUser = JSON.toJSONString(SecurityUtils.getSubject().getPrincipal());
		temp.setOperator(currentUser);
		temp.setCreateTime(new Date());
		temp.setUpdateTime(new Date());
		temp.setStatus(1);
		int result = crmCfpLevelRecordMapper.insertSelective(temp);
		return result;
	}

	@Override
	public List<CfpLevelDataStatisticsListResp> queryStatisticsList(CfpLevelStatisticsRequest cfpLevelStatisticsRequest) {
		List<CfpLevelDataStatisticsListResp> cfpLevelDataStatisticsListRespList = new ArrayList<CfpLevelDataStatisticsListResp>();
		cfpLevelDataStatisticsListRespList = crmCfpLevelRecordMapper.queryStatisticsList(cfpLevelStatisticsRequest);
		return cfpLevelDataStatisticsListRespList;
	}

	@Override
	public String batchChangeGrade(List<BatchChangeGrade> gradeList) {
		String  msg = "";
		int i = 0;
		for(BatchChangeGrade grade:gradeList){
			CrmCfpLevelRecord crmCfpLevelRecord = new CrmCfpLevelRecord();
			crmCfpLevelRecord.setUserId(grade.getUserId());
			crmCfpLevelRecord.setStatus(1);
			CrmCfpLevelRecord temp = crmCfpLevelRecordMapper.selectOneByCondition(crmCfpLevelRecord);
			if(temp!=null){
				int curGrade = CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getGrade())==null?0:CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getGrade()).getLevelWeight();
				int preGrade = CfpJobGradeEnum.getCfpJobGradeEnumByKey(temp.getCurLevel()).getLevelWeight();
				if(curGrade<=preGrade){ 
					msg = msg+"【"+grade.getMobile()+"】";//职级等于或小于原来不能调整
					i= i+1;
				    continue;
				}
				//更新tcrm_cfplanner表调整后的职级
				CrmCfplanner crmCfplanner = new CrmCfplanner();
				crmCfplanner.setUserId(grade.getUserId());
				crmCfplanner.setJobGrade(grade.getGrade());
				crmCfplanner.setJobGradeWeight(CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getGrade()).getLevelWeight());
				crmCfplannerService.updateByUserId(crmCfplanner);
				//更新tcrm_cfp_level_record表，先将原数据status置为0，重新插入最新数据
				String currentUser = JSON.toJSONString(SecurityUtils.getSubject().getPrincipal());
				updateLevelRecord(grade.getUserId(),grade.getGrade(),currentUser);
			}
		}
		if(i!=0){
			msg = msg + "号码调整失败!  ";
			if(gradeList.size()!=i) msg = msg + "其余号码调整成功!";
		} 
		return msg;
	}
}
