package com.linkwee.api.controller.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.activity.response.FinancialCalculationResponse;
import com.linkwee.api.activity.response.YearStaPersonAchiResponse;
import com.linkwee.api.activity.response.YearStaTeamAchiResponse;
import com.linkwee.api.response.activity.YearMaxProfitUserResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActFinancialCalculationInfo;
import com.linkwee.web.model.SysHomepageCommission;
import com.linkwee.web.service.ActFinancialCalculationInfoService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysHomepageCommissionService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ActCfpDoubleElevenActivityController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/activity/yearStatistics")
@RequestLogging("2017猎财大师年度账单控制器")
public class YearStatisticsController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(YearStatisticsController.class);
	
	@Resource
	private ActivityListService activityListService;
	@Resource
	private SysHomepageCommissionService sysHomepageCommissionService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
    private CrmCfplannerService crmCfplannerService;
	@Resource
	private ActFinancialCalculationInfoService actFinancialCalculationInfoService;
	
	@RequestLogging("个人业绩")
	@RequestMapping("personAchievement")
	@ResponseBody
	public BaseResponse personAchievement(AppRequestHead head,String userId) {
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
			if(StringUtils.isBlank(userId) || userId.equals("undefined")){
				return AppResponseUtil.getErrorToken();
			}
		}
		LOGGER.info("用户userid={}查看个人业绩",userId);
		YearStaPersonAchiResponse resp = activityListService.yearStatiPersonAchievement(userId);
		return AppResponseUtil.getSuccessResponse(resp);	
	}
	
	@RequestLogging("团队业绩")
	@RequestMapping("teamAchievement")
	@ResponseBody
	public BaseResponse teamAchievement(AppRequestHead head,String userId) {
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
			if(StringUtils.isBlank(userId) || userId.equals("undefined")){
				return AppResponseUtil.getErrorToken();
			}
		}
		LOGGER.info("用户userid={}查看团队业绩",userId);
		List<String> teamCfpList = activityListService.teamCfp(userId);
		if(teamCfpList == null){
			teamCfpList = new ArrayList<String>();
		}
		teamCfpList.add(userId);
		YearStaTeamAchiResponse resp = activityListService.yearTeamAchievement(teamCfpList);
		if(resp == null){
			return AppResponseUtil.getErrorToken();
		}else{
			YearMaxProfitUserResponse yearMaxProfitUserResponse = activityListService.yearMaxProfitUser(userId);
			if(yearMaxProfitUserResponse != null){
				resp.setMaxProfitUserName(yearMaxProfitUserResponse.getUserName());
				resp.setMaxProfitUserHeadImg(yearMaxProfitUserResponse.getHeadImage());
			}			
		}
		return AppResponseUtil.getSuccessResponse(resp);	
	}
	
	@RequestLogging("猎财大师业绩")
	@RequestMapping("liecaiAchievement")
	@ResponseBody
	public BaseResponse liecaiAchievement(AppRequestHead head,String userId) {
		SysHomepageCommission sysHomepageCommission = sysHomepageCommissionService.select2017Newest();
		Map<String,Object> resp = new HashMap<String,Object>();
		resp.put("commissionAmount", sysHomepageCommission.getAmount().replace(",", ""));
		String fromTime = sysConfigService.getValuesByKey("addNewCfpNumFromTime", 1);
		int addTime = crmCfplannerService.query2017NewCfpNumber(fromTime,"2017-12-31 23:59:59");
		resp.put("activeUserNumber", 8215+addTime);
		Date nowDate = new Date();
		int compareResult = DateUtils.compareDate(nowDate, DateUtils.parse("2017-12-31 23:59:59"));
		if(compareResult > 0){
			resp.put("deadTime", "2017-12-31");
		}else{
			resp.put("deadTime", DateUtils.format(nowDate, DateUtils.FORMAT_SHORT));
		}
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	@RequestLogging("财运测算信息")
	@RequestMapping("financial/calculation")
	@ResponseBody
	public BaseResponse financialCalculation(AppRequestHead head,String userId) {
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
			if(StringUtils.isBlank(userId) || userId.equals("undefined")){
				return AppResponseUtil.getErrorToken();
			}
		}
		ActFinancialCalculationInfo temp = new ActFinancialCalculationInfo();
		temp.setUserId(userId);
		temp = actFinancialCalculationInfoService.selectOne(temp);
		FinancialCalculationResponse response = new FinancialCalculationResponse();
		if(temp != null){
			response.setFinancial(temp.getFinancial());
			response.setFlag(temp.getFlag());
			response.setHasCalculated(true);
		}else{
			response.setHasCalculated(false);
		}		
		return AppResponseUtil.getSuccessResponse(response);
	}
	
	@RequestLogging("财运测算")
	@RequestMapping("financial/calculate")
	@ResponseBody
	public BaseResponse financialCalculate(AppRequestHead head,String userId,String flag) {
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
			if(StringUtils.isBlank(userId) || userId.equals("undefined")){
				return AppResponseUtil.getErrorToken();
			}
		}
		if(StringUtils.isBlank(flag)){
			return AppResponseUtil.getErrorBusi("100089", "参数错误，flag不能为空！");
		}
		ActFinancialCalculationInfo temp = new ActFinancialCalculationInfo();
		temp.setUserId(userId);
		temp = actFinancialCalculationInfoService.selectOne(temp);
		FinancialCalculationResponse response = new FinancialCalculationResponse();
		if(temp == null){
			temp = new ActFinancialCalculationInfo();
			temp.setUserId(userId);
			temp.setFlag(flag);
			int range = 100 - 10;
	        double rand = Math.random();
			String financial = (int)(10 + Math.round(rand * range))+"万";
			temp.setFinancial(financial);
			temp.setCreateTime(new Date());
			actFinancialCalculationInfoService.insert(temp);
		}	
		response.setFinancial(temp.getFinancial());
		response.setFlag(temp.getFlag());
		return AppResponseUtil.getSuccessResponse(response);
	}
	
}
