package com.linkwee.api.controller.cim;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.crm.BindOrgAcctRequest;
import com.linkwee.api.request.crm.PlatformManagerListRequest;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.api.response.cim.PlatformAcctManagerListResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.SignUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.enums.RequestTypeEnums;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimOrgAdvertises;
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.model.CimOrgPicture;
import com.linkwee.web.model.CimOrgRechargeLimit;
import com.linkwee.web.model.CimOrgRisk;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.cim.PcOrgInfo;
import com.linkwee.web.model.crm.PlatformAcctManagerListResp;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.request.OrgInfoRequest;
import com.linkwee.web.request.orgInfo.OrgDetailRequest;
import com.linkwee.web.request.orgInfo.OrgJumpRequest;
import com.linkwee.web.request.orgInfo.OrgPageList4Request;
import com.linkwee.web.request.orgInfo.OrgRecommendByChooseRequest;
import com.linkwee.web.request.orgInfo.OrgRecommendChooseRequest;
import com.linkwee.web.request.orgInfo.OrgThirdDataDetailRequest;
import com.linkwee.web.request.orgInfo.OrgUrlSkipParameterRequest;
import com.linkwee.web.response.CimNewOrgListResponse;
import com.linkwee.web.response.CimOrgListResponse;
import com.linkwee.web.response.CimPcOrgListResponse;
import com.linkwee.web.response.InvestProductStatisticsResponse;
import com.linkwee.web.response.InvestStatisticsResponse;
import com.linkwee.web.response.PcPlannerRecommendResponse;
import com.linkwee.web.response.PlannerRecommendResponse;
import com.linkwee.web.response.orgInfo.CimOrginfoChannelResponse;
import com.linkwee.web.response.orgInfo.CimOrginfoInvestorResponse;
import com.linkwee.web.response.orgInfo.CimPcOrginfoInvestorResponse;
import com.linkwee.web.response.orgInfo.InvestmentStrategyResponse;
import com.linkwee.web.response.orgInfo.OrgInfoResponse;
import com.linkwee.web.response.orgInfo.OrgPageListResponse;
import com.linkwee.web.response.orgInfo.OrgRecommendChooseResponse;
import com.linkwee.web.response.orgInfo.OrgSaleProductResponse;
import com.linkwee.web.response.orgInfo.OrgThirdDataDetailResponse;
import com.linkwee.web.response.orgInfo.PcOrgInfoResponse;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CimOrgAdvertisesService;
import com.linkwee.web.service.CimOrgDynamicService;
import com.linkwee.web.service.CimOrgPictureService;
import com.linkwee.web.service.CimOrgRechargeLimitService;
import com.linkwee.web.service.CimOrgRiskService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysGrayReleaseService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.constant.TimeSetConstants;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.thirdsdk.xiaoying.helper.XiaoyingkeJiHelper;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.OpenHttpUtils;
import com.linkwee.xoss.util.RequestLogging;

/**
 * 机构接口控制器
 *
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/platfrom")
@RequestLogging("机构")
public class PlatfromController extends BaseController{
	
	@Resource
	private CimOrginfoService cimOrgInfoService;
	@Resource
	private SysConfigService sysconfigService;
	@Resource
	private CimProductService cimProductService;
	@Resource
	private SysThirdkeyConfigService sysThirdkeyConfigService;
	@Resource
	private  AcAccountBindService accountbindService;
	@Resource
	private CrmUserInfoService crmUserInfoService;
	@Resource
	private SysConfigService sysConfigService; //系统配置
	@Resource
	private CrmInvestorService crmInvestorService;  //投资用户服务
	@Resource
	private CimOrgPictureService cimOrgPictureService;  //机构图片配置服务
	@Resource
	private CimOrgDynamicService cimOrgDynamicService;  //机构动态服务
	@Resource
	private ActivityListService activityListService; //活动服务
	@Resource
	private SysGrayReleaseService sysGrayReleaseService; //灰度服务
	@Resource
	private CimOrgAdvertisesService cimOrgAdvertisesService;  //机构活动宣传图配置服务
	@Resource
	private SysMsgService sysMsgService;//站内个人消息服务
	@Resource
	private CimOrgRiskService cimOrgRiskService; //机构风控信息服务
	@Resource 
	private WeiXinMsgService weiXinMsgService;
	@Resource
	private RedPacketService redPacketService; //红包服务
	@Resource
	private XiaoyingkeJiHelper xiaoyingkeJiHelper;
	
	@Resource
	private ConfigHelper configHelper;
	
	@Resource
	private CimOrgRechargeLimitService orgRechargeLimitService;
	
	@Resource
	private JedisCluster jedisCluster;

	/**
     * 热门机构/优质机构  不分页
     * @return
     */
    @RequestMapping(value="/highQualityPlatform")
    @ResponseBody
    @RequestLogging("优质机构")
	public BaseResponse getRecommendOrgInfo(AppRequestHead head,PaginatorRequest req) {
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	
    	LOGGER.debug("优质机构 request = {}", JSON.toJSONString(head));
    	//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		Page<CimOrginfo> page  = new Page<CimOrginfo>(req.getPageIndex(),req.getPageSize()); //默认每页10条
    		PaginatorResponse<CimOrginfo> pcRecommendOrg = cimOrgInfoService.findPcRecommendOrg(page,isGrayUser);
    		
    		return AppResponseUtil.getSuccessResponse(pcRecommendOrg,CimPcOrgListResponse.class); 
    		
    	}
    	
		List<CimOrginfo> orgDatas = cimOrgInfoService.findRecommendOrg(isGrayUser); //移动端
		
		return AppResponseUtil.getSuccessResponse(orgDatas,CimOrgListResponse.class);
		
	}
    
    /**
     * PC端 投呗 最新入驻机构  
     * 固定返回8家 不分页
     * @return
     */
    @RequestMapping(value="/queryLatestOrg")
    @ResponseBody
    @RequestLogging("最新入驻机构")
	public BaseResponse queryLatestOrg(AppRequestHead head) {
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	LOGGER.debug("PC端最新入驻机构 request = {}", JSON.toJSONString(head));
    	List<CimOrginfo> latestDatas = cimOrgInfoService.queryLatestOrg(isGrayUser);
    	return AppResponseUtil.getSuccessResponse(latestDatas,CimNewOrgListResponse.class);
    }

	/**
	 * 机构分页-分页
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/pageList")
	@ResponseBody
	@RequestLogging("机构列表")
	public BaseResponse newsPageList(@Valid OrgInfoRequest req,BindingResult result,AppRequestHead head){
    	LOGGER.debug("机构列表请求参数 OrgInfoRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
    	if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	
    	Page<CimOrginfo> page  = new Page<CimOrginfo>(req.getPageIndex(),req.getPageSize()); //默认每页10条
    	Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
    	
    	conditions.put("userId", userId);
    	
    	//灰度用户判定
    	conditions.put("isGrayUser", isGrayUser);
    	
    	//机构级别
    	if(StringUtils.isNotBlank(req.getSecurityLevel())){
    		conditions.put("securityLevel", req.getSecurityLevel());
    	}
    	//产品期限
    	if(StringUtils.isNotBlank(req.getProductDeadLine()) && req.getProductDeadLine().contains(",")){
    		String[] deadLine = req.getProductDeadLine().split(",");
    		if(deadLine.length == 2){
    			String minDeadLine = deadLine[0];
    			String maxDeadLine = deadLine[1];
    			conditions.put("minDeadLine", minDeadLine);
    			conditions.put("maxDeadLine", maxDeadLine);
    		}
    	}
    	//年化收益
    	if(StringUtils.isNotBlank(req.getYearProfit()) && req.getYearProfit().contains(",")){
    		String[] yearProfit = req.getYearProfit().split(",");
    		if(yearProfit.length == 2){
    			String minYearProfit = yearProfit[0];
    			String maxYearProfit = yearProfit[1];
    			conditions.put("minYearProfit", minYearProfit);
    			conditions.put("maxYearProfit", maxYearProfit);
    		}
    	}
    	
    	//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){

    		if(StringUtils.isNotBlank(req.getBackground())){ //机构背景
    			conditions.put("background", req.getBackground());
    		}
    		
    		if(StringUtils.isNotBlank(req.getCity())){ //机构所在城市
    			if(!req.getCity().equals("other")){
    				conditions.put("city", req.getCity());
    			}else{
    				//查询机构所在城市
    				List<SysConfig> orgCityList = sysConfigService.querySysConfigByName("PC端机构筛选条件-所在城市"); //机构背景配置
    				conditions.put("orgCityList", orgCityList);
    				conditions.put("city", "other");
    			}
    		}
    		
    		conditions.put("appType",head.getOrgNumber()); 
    		PaginatorResponse<CimOrginfo> orgPcdatas = cimOrgInfoService.queryOrgList(page,conditions); //PC版投呗
    		
    		return AppResponseUtil.getSuccessResponse(orgPcdatas,CimPcOrginfoInvestorResponse.class);

    		
    	}
    	conditions.put("appType",head.getOrgNumber()); //1理财师，2投资者
		PaginatorResponse<CimOrginfo> orgdatas = cimOrgInfoService.queryOrgList(page,conditions); //机构列表的分页信息
		//PaginatorSevResp
		
		//理财师
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			return AppResponseUtil.getSuccessResponse(orgdatas,CimOrginfoChannelResponse.class);
		}
		//投资者
		return AppResponseUtil.getSuccessResponse(orgdatas,CimOrginfoInvestorResponse.class);
	}
	
    
	/**
	 * 移动端 机构详情
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/detailOld")
	@ResponseBody
	@RequestLogging("机构信息详情")
	public BaseResponse orgDetail(@Valid OrgDetailRequest req,BindingResult result, AppRequestHead head){
    	
    	long startDate = System.currentTimeMillis();
    	long startDateStart = startDate;
    	
    	LOGGER.debug("机构信息详情请求参数 OrgDetailRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
			OrgInfo orgInfo = cimOrgInfoService.findOrgInfo(req.getOrgNo());
			
			if(orgInfo != null){
				return AppResponseUtil.getSuccessResponse(orgInfo,OrgInfoResponse.class);
			}else{
				return AppResponseUtil.getErrorBusi("orgNotExist","此平台不存在");
			}
    	}else{
    					
    		startDate = System.currentTimeMillis();
    		//移动端
    		OrgInfo orgInfo = cimOrgInfoService.findOrgInfo(req.getOrgNo());
    		
    		//OrgInfo orgInfo = new OrgInfo();
    		
    		//LOGGER.info("机构信息详情,移动端信息endTime={}",System.currentTimeMillis()-startDate);
        	startDate = System.currentTimeMillis();
    		
    		if(orgInfo != null){
    			
    			
    	    	LOGGER.info("机构信息详情,startTime={}",System.currentTimeMillis());
   			
    			//平台可用红包数
        		int platformRedPacketCount = redPacketService.patformRedPacketCount(orgInfo.getOrgNo(),orgInfo.getOrgFeeType(), JsonWebTokenHepler.getUserIdByToken(head.getToken()));
        		orgInfo.setPlatformRedPacketCount(platformRedPacketCount);
        		
        		//LOGGER.info("机构信息详情,平台可用红包数endTime={}",System.currentTimeMillis()-startDate);
            	startDate = System.currentTimeMillis();
        		
    	    	//List<CimOrgAdvertises> orgAdvertisesList = new ArrayList<CimOrgAdvertises>();
    	    	
    	    	//List<CimOrgAdvertises> orgAdvertisesList = new ArrayList<CimOrgAdvertises>();
    	    	
    			List<CimOrgAdvertises> orgAdvertisesList = cimOrgAdvertisesService.queryOrgAdvertisesList(req.getOrgNo()); //[] 机构活动宣传图
    			//LOGGER.info("机构信息详情,机构活动宣传图 endTime={}",System.currentTimeMillis()-startDate);
            	startDate = System.currentTimeMillis();
            	//List<CimOrgPicture> orgPapersList = new ArrayList<CimOrgPicture>();
    			List<CimOrgPicture> orgPapersList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),1,1); //公司证件照
    			//LOGGER.info("机构信息详情,公司证件照endTime={}",System.currentTimeMillis()-startDate);
            	startDate = System.currentTimeMillis();
            	
            	//List<CimOrgPicture> orgEnvironmentList = new ArrayList<CimOrgPicture>();
            	
            	//List<CimOrgPicture> orgHonorList = new ArrayList<CimOrgPicture>();
            	
    			List<CimOrgPicture> orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),2,1); //办公环境照
    			//LOGGER.info("机构信息详情,办公环境照endTime={}",System.currentTimeMillis()-startDate);
            	startDate = System.currentTimeMillis();
    			List<CimOrgPicture> orgHonorList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),3,1); //荣誉证书
    			//LOGGER.info("机构信息详情,荣誉证书endTime={}",System.currentTimeMillis()-startDate);
            	startDate = System.currentTimeMillis();
    			Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
        		conditions.put("activityPlatform", orgInfo.getOrgName()); //平台名称
        		//理财师
        		if(AppUtils.isChannelApp(head.getOrgNumber())){
        			conditions.put("appType", 1); //理财师
        		}else{
        			conditions.put("appType", 2); //T呗
        		}
        		
        		//List<ActivityList> orgActivityList = new ArrayList<ActivityList>();
        		//List<CimOrgDynamic> orgDynamicList = new ArrayList<CimOrgDynamic>();
        		
        		List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(conditions); //[] 机构活动宣传图
        		//LOGGER.info("机构信息详情,机构活动宣传图222endTime={}",System.currentTimeMillis()-startDate);
            	startDate = System.currentTimeMillis();
        		List<CimOrgDynamic> orgDynamicList = cimOrgDynamicService.queryCimOrgDynamicList(req.getOrgNo()); //[] 机构动态
        		//LOGGER.info("机构信息详情,机构动态endTime={}",System.currentTimeMillis()-startDate);
            	startDate = System.currentTimeMillis();
        		
        		if(orgAdvertisesList != null){
        			orgInfo.setOrgAdvertises(orgAdvertisesList);
        		}
        		
        		if(orgPapersList != null){
        			orgInfo.setOrgPapersList(orgPapersList);
        		}
        		if(orgEnvironmentList != null){
        			orgInfo.setOrgEnvironmentList(orgEnvironmentList);
        		}
        		if(orgHonorList != null){
        			orgInfo.setOrgHonorList(orgHonorList);
        		}
        		
        		if(orgActivityList != null){
        			orgInfo.setOrgActivitys(orgActivityList);
        		}
        		
        		if(orgDynamicList != null){
        			orgInfo.setOrgDynamicList(orgDynamicList);
        		}
        		//LOGGER.info("机构信息详情,endTime={}",System.currentTimeMillis()-startDate);
            	LOGGER.info("机构信息详情,时间总计={}",System.currentTimeMillis()-startDateStart);
        		return AppResponseUtil.getSuccessResponse(orgInfo,OrgInfoResponse.class);
    		}else{
				return AppResponseUtil.getErrorBusi("orgNotExist","此平台不存在");
			}
    	}
		
	}
    
    /**
	 * 移动端 机构详情
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/detail")
	@ResponseBody
	@RequestLogging("机构信息详情(redis)")
	public BaseResponse orgDetailNew(@Valid OrgDetailRequest req,BindingResult result, AppRequestHead head){
    	
    	long startDate = System.currentTimeMillis();
    	long startDateStart = startDate;
    	
    	LOGGER.debug("机构信息详情请求参数 OrgDetailRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		
		OrgInfo orgInfo = null;
		if(jedisCluster != null){//若redis存在
			String redisOrgKey = req.getOrgNo() + "_platform_detail";
			if(jedisCluster.exists(redisOrgKey)){
				orgInfo = JSON.parseObject(jedisCluster.get(redisOrgKey), OrgInfo.class);
			} else {
				orgInfo = cimOrgInfoService.findOrgInfo(req.getOrgNo());
				if(orgInfo != null){	
					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,JSON.toJSONString(orgInfo));
				}
			}
		} else {
			orgInfo = cimOrgInfoService.findOrgInfo(req.getOrgNo());
		}
		
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){		
			if(orgInfo != null){
				return AppResponseUtil.getSuccessResponse(orgInfo,OrgInfoResponse.class);
			}else{
				return AppResponseUtil.getErrorBusi("orgNotExist","此平台不存在");
			}
    	}else{   				
    		if(orgInfo != null){ 			   			
    			//平台可用红包数
        		int platformRedPacketCount = redPacketService.patformRedPacketCount(orgInfo.getOrgNo(),orgInfo.getOrgFeeType(), JsonWebTokenHepler.getUserIdByToken(head.getToken()));
        		orgInfo.setPlatformRedPacketCount(platformRedPacketCount);
        		      		   	
    	    	List<CimOrgAdvertises> orgAdvertisesList = new ArrayList<CimOrgAdvertises>();	    	
    			//List<CimOrgAdvertises> orgAdvertisesList = cimOrgAdvertisesService.queryOrgAdvertisesList(req.getOrgNo()); //[] 机构活动宣传图
    				    	
    	    	//[] 机构活动宣传图
        		if(jedisCluster != null){//若redis存在
        			String redisOrgKey = req.getOrgNo() + "_platform_detail_org_adv";
        			if(jedisCluster.exists(redisOrgKey)){
        				orgAdvertisesList = JSON.parseArray(jedisCluster.get(redisOrgKey), CimOrgAdvertises.class);
        			} else {
        				orgAdvertisesList = cimOrgAdvertisesService.queryOrgAdvertisesList(req.getOrgNo());
        				if(orgAdvertisesList != null){	
        					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,JSON.toJSONString(orgAdvertisesList));
        				}
        			}
        		} else {
        			orgAdvertisesList = cimOrgAdvertisesService.queryOrgAdvertisesList(req.getOrgNo());
        		}
    			
            	List<CimOrgPicture> orgPapersList = new ArrayList<CimOrgPicture>();
            	//List<CimOrgPicture> orgPapersList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),1,1); //公司证件照
            	if(jedisCluster != null){//若redis存在
        			String redisOrgKey = req.getOrgNo() + "_platform_detail_org_paper";
        			if(jedisCluster.exists(redisOrgKey)){
        				orgPapersList = JSON.parseArray(jedisCluster.get(redisOrgKey), CimOrgPicture.class);
        			} else {
        				orgPapersList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),1,1);
        				if(orgPapersList != null){	
        					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,JSON.toJSONString(orgPapersList));
        				}
        			}
        		} else {
        			orgPapersList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),1,1);
        		}
            	
            	List<CimOrgPicture> orgEnvironmentList = new ArrayList<CimOrgPicture>();
            	//List<CimOrgPicture> orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),2,1); //办公环境照
            	if(jedisCluster != null){//若redis存在
        			String redisOrgKey = req.getOrgNo() + "_platform_detail_org_envi";
        			if(jedisCluster.exists(redisOrgKey)){
        				orgEnvironmentList = JSON.parseArray(jedisCluster.get(redisOrgKey), CimOrgPicture.class);
        				//LOGGER.info("机构信息详情,办公环境照走缓存");
        			} else {
        				orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),2,1);
        				if(orgEnvironmentList != null){	
        					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,JSON.toJSONString(orgEnvironmentList));
        				}
        			}
        		} else {
        			orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),2,1);
        		}
            	
            	List<CimOrgPicture> orgHonorList = new ArrayList<CimOrgPicture>();
            	//List<CimOrgPicture> orgHonorList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),3,1); //荣誉证书
            	if(jedisCluster != null){//若redis存在
        			String redisOrgKey = req.getOrgNo() + "_platform_detail_org_honor";
        			if(jedisCluster.exists(redisOrgKey)){
        				orgHonorList = JSON.parseArray(jedisCluster.get(redisOrgKey), CimOrgPicture.class);
        			} else {
        				orgHonorList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),3,1);
        				if(orgHonorList != null){	
        					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,JSON.toJSONString(orgHonorList));
        				}
        			}
        		} else {
        			orgHonorList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),3,1);
        		}
            	
    			Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
        		conditions.put("activityPlatform", orgInfo.getOrgName()); //平台名称
        		//理财师
        		if(AppUtils.isChannelApp(head.getOrgNumber())){
        			conditions.put("appType", 1); //理财师
        		}else{
        			conditions.put("appType", 2); //T呗
        		}
        		
        		List<ActivityList> orgActivityList = new ArrayList<ActivityList>();
        		//List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(conditions); //[] 机构活动宣传图
        		if(jedisCluster != null){//若redis存在
        			String redisOrgKey = orgInfo.getOrgName() + "_platform_detail_org_activity";
        			if(jedisCluster.exists(redisOrgKey)){
        				orgActivityList = JSON.parseArray(jedisCluster.get(redisOrgKey), ActivityList.class);
        			} else {
        				orgActivityList = activityListService.queryPlatformActivities(conditions);
        				if(orgActivityList != null){	
        					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,JSON.toJSONString(orgActivityList));
        				}
        			}
        		} else {
        			orgActivityList = activityListService.queryPlatformActivities(conditions);
        		}
        		
        		List<CimOrgDynamic> orgDynamicList = new ArrayList<CimOrgDynamic>();
        		//List<CimOrgDynamic> orgDynamicList = cimOrgDynamicService.queryCimOrgDynamicList(req.getOrgNo()); //[] 机构动态
        		if(jedisCluster != null){//若redis存在
        			String redisOrgKey = req.getOrgNo() + "_platform_detail_org_dynamic";
        			if(jedisCluster.exists(redisOrgKey)){
        				orgDynamicList = JSON.parseArray(jedisCluster.get(redisOrgKey), CimOrgDynamic.class);
        				//LOGGER.info("机构信息详情,机构动态走缓存");
        			} else {
        				orgDynamicList = cimOrgDynamicService.queryCimOrgDynamicList(req.getOrgNo());
        				if(orgDynamicList != null){	
        					jedisCluster.setex(redisOrgKey, (int)TimeSetConstants.ORGSECRET_VALID_DATE/1000,JSON.toJSONString(orgDynamicList));
        				}
        			}
        		} else {
        			orgDynamicList = cimOrgDynamicService.queryCimOrgDynamicList(req.getOrgNo());
        		}
        		
        		if(orgAdvertisesList != null){
        			orgInfo.setOrgAdvertises(orgAdvertisesList);
        		}
        		
        		if(orgPapersList != null){
        			orgInfo.setOrgPapersList(orgPapersList);
        		}
        		if(orgEnvironmentList != null){
        			orgInfo.setOrgEnvironmentList(orgEnvironmentList);
        		}
        		if(orgHonorList != null){
        			orgInfo.setOrgHonorList(orgHonorList);
        		}
        		
        		if(orgActivityList != null){
        			orgInfo.setOrgActivitys(orgActivityList);
        		}
        		
        		if(orgDynamicList != null){
        			orgInfo.setOrgDynamicList(orgDynamicList);
        		}
            	LOGGER.info("机构信息详情,时间总计={}",System.currentTimeMillis()-startDateStart);
        		return AppResponseUtil.getSuccessResponse(orgInfo,OrgInfoResponse.class);
    		}else{
				return AppResponseUtil.getErrorBusi("orgNotExist","此平台不存在");
			}
    	}
		
	}
    

	/**
	 * PC端T呗机构详情
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/pcOrgDetail")
	@ResponseBody
	@RequestLogging("PC端T呗机构信息详情")
	public BaseResponse orgPcDetail(@Valid OrgDetailRequest req,BindingResult result, AppRequestHead head){
    	LOGGER.debug("PC端T呗机构信息详情请求参数 OrgDetailRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		
			PcOrgInfo pcOrgInfo = cimOrgInfoService.findPcOrgInfo(req.getOrgNo());
			if(pcOrgInfo == null){
				return AppResponseUtil.getErrorBusi("orgNotExist","PC端此平台不存在");
			}
			
			//平台可用红包数
    		int platformRedPacketCount = redPacketService.patformRedPacketCount(pcOrgInfo.getOrgNumber(),pcOrgInfo.getOrgFeeType(), JsonWebTokenHepler.getUserIdByToken(head.getToken()));
    		pcOrgInfo.setPlatformRedPacketCount(platformRedPacketCount);
    		
			Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
    		conditions.put("activityPlatform", pcOrgInfo.getOrgName()); //平台名称
    		conditions.put("appType", 2); //T呗
    		
    		List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(conditions); //[] 机构活动宣传图
    		List<CimOrgPicture> orgPapersList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),1,2); //公司证件照
    		List<CimOrgPicture> orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),2,2); //办公环境照
    		List<CimOrgPicture> orgHonorList = cimOrgPictureService.queryOrgPictureList(req.getOrgNo(),3,2); //荣誉证书
    		List<CimOrgRisk> orgRiskList = cimOrgRiskService.queryOrgRiskInfoByOrgNumber(req.getOrgNo()); //机构风控信息
    		
    		if(orgRiskList != null){
    			pcOrgInfo.setOrgRiskList(orgRiskList);
    		}
    		if(orgPapersList != null){
    			pcOrgInfo.setOrgPapersList(orgPapersList);
    		}
    		if(orgEnvironmentList != null){
    			pcOrgInfo.setOrgEnvironmentList(orgEnvironmentList);
    		}
    		if(orgHonorList != null){
    			pcOrgInfo.setOrgHonorList(orgHonorList);
    		}
    		if(orgActivityList != null){
    			pcOrgInfo.setOrgActivityList(orgActivityList);
    		}
			return AppResponseUtil.getSuccessResponse(pcOrgInfo,PcOrgInfoResponse.class);
    	}else{
    		return AppResponseUtil.getErrorBusi("requestParamError","不是PC端投呗");
    	}
	}
    /**
	 * 机构筛选条件
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/platformHead")
	@ResponseBody
	@RequestLogging("机构筛选条件")
	public BaseResponse platformHead(AppRequestHead head) throws Exception {
    	LOGGER.debug("机构筛选条件:"+head);
		Map<String,Object> rlt = new HashMap<String,Object>();
		List<SysConfig> sysList = sysconfigService.querySysConfigByName("机构筛选条件-");
		List<Map<String,Object>> orgLevelList = new ArrayList<Map<String,Object>>(); //机构评级
		List<Map<String,Object>> profitList = new ArrayList<Map<String,Object>>(); //机构年化收益
		List<Map<String,Object>> deadlineList = new ArrayList<Map<String,Object>>(); //机构产品期限
		for(SysConfig item:sysList){
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("key", item.getConfigKey());
			data.put("value", item.getConfigValue());
			if(item.getConfigType().endsWith("grade")){ //评级
				orgLevelList.add(data);
			}else if(item.getConfigType().endsWith("profit")){ //年化收益
				profitList.add(data);
			}else if(item.getConfigType().endsWith("days")){ //产品期限
				deadlineList.add(data);
			}
		}
		rlt.put("orgLevel", orgLevelList);
		rlt.put("profit", profitList);
		rlt.put("deadline", deadlineList);
		
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		List<SysConfig> pcSysList = sysconfigService.querySysConfigByName("PC端机构筛选条件-");
    		List<Map<String,Object>> orgCityList = new ArrayList<Map<String,Object>>(); //所在城市
    		List<Map<String,Object>> orgBackgroundList = new ArrayList<Map<String,Object>>(); //机构背景
    		
    		for(SysConfig config : pcSysList){
    			Map<String, Object> data = new HashMap<String, Object>();
    			data.put("key", config.getConfigKey());
    			data.put("value", config.getConfigValue());
    			if(config.getConfigType().endsWith("background")){ //机构背景
    				orgBackgroundList.add(data);
    			}else if(config.getConfigType().endsWith("city")){ //所在城市
    				orgCityList.add(data);
    			}
    		}
    		rlt.put("background", orgBackgroundList);
    		rlt.put("city", orgCityList);
    	}
		LOGGER.debug("机构筛选条件 | query success!");
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    
    /**
     * 机构在售产品
     * @author yalin 
     * @date 2016年7月26日 下午2:04:55  
     * @param req
     * @param validResult
     * @param head
     * @return
     * @throws Exception
     */
    @RequestMapping("platformSaleProducts")
	@ResponseBody
	@RequestLogging("机构在售产品")
	public BaseResponse platformSaleProducts( @Valid OrgDetailRequest req,BindingResult validResult,AppRequestHead head) throws Exception {
    	if (AppResponseUtil.existsParamsError(validResult)) {
			return AppResponseUtil.getErrorParams(validResult);
		}
    	Page<OrgSaleProductResponse> page  = new Page<OrgSaleProductResponse>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<OrgSaleProductResponse> result = cimProductService.queryOrgSaleProducts(req.getOrgNo(), page);
		return AppResponseUtil.getSuccessResponse(result);
	}
    
    
    /**
	 * 机构帐号管理列表
	 */
	@RequestMapping("accountManager/pageList")
	@ResponseBody
	@RequestLogging("机构帐号管理列表")
	public BaseResponse platformAcctManagerPageList(@Valid PlatformManagerListRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2");
		Map<String ,Object> query = new HashMap<String ,Object>();
		query.put("type", req.getType());
		query.put("userId", userId);
		query.put("isGrayUser", isGrayUser);
		Page<PlatformAcctManagerListResp> page = new Page<PlatformAcctManagerListResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<PlatformAcctManagerListResp> rlt = cimOrgInfoService.queryPlatformAcctManagerPageList(query, page);
		return AppResponseUtil.getSuccessResponse(rlt,PlatformAcctManagerListResponse.class);
	}
	
	/**
	 * 机构帐号管理统计
	 */
	@RequestMapping("accountManager/statistics")
	@ResponseBody
	@RequestLogging("机构帐号管理统计")
	public BaseResponse platformAcctManagerStatistics(AppRequestHead head) throws Exception {
		Map<String ,String > map = new HashMap<String, String>();
		Map<String ,Object > query = new HashMap<String, Object>();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2");
		query.put("userId", userId);
		query.put("isGrayUser", isGrayUser);
		//int bindOrgAccountCount = cimOrgInfoService.queryOrgAccountCount(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		//int unBindOrgAccountCount = cimOrgInfoService.queryOrgCount() - bindOrgAccountCount;
		int unBindOrgAccountCount = cimOrgInfoService.unBindOrgAccountCount(query);
		List<PlatformAcctManagerListResp> bindList = cimOrgInfoService.bindOrgAccountCount(query);
		List<PlatformAcctManagerListResp> removeList = new ArrayList<PlatformAcctManagerListResp>();
		
		if(bindList != null && bindList.size() > 0){
			for(PlatformAcctManagerListResp bo : bindList) {
				//查看合作结束的平台并且用户在平台的投资记录数大于0的平台
				if((bo.getStatus() == null || bo.getStatus() == 0) && bo.getInvestCount() == 0){
					removeList.add(bo);
				}
			}
		}
		bindList.removeAll(removeList); //集合中移除合作结束并且投资记录数为0的平台
		map.put("bindOrgAccountCount", "" + bindList.size());//绑定机构帐号数
		map.put("unBindOrgAccountCount", "" + unBindOrgAccountCount);//未绑定机构数
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 绑定机构帐号
	 */
	@RequestMapping("bindOrgAcct")
	@ResponseBody
	@RequestLogging("绑定机构帐号")
	public BaseResponse bindOrgAcct(@Valid BindOrgAcctRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		final String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(cimOrgInfoService.isBindOrgAcct(userId, req.getPlatFromNumber())){
			return AppResponseUtil.getErrorBusi("orgAccountExist","用户已绑定此第三方平台");
		}
//		20180614 去掉注册前绑卡认证		
//		AcAccountBind account = accountbindService.selectAccountByUserId(userId);
//		if(account == null || account.getBankCard() == null || account.getIdCard() == null) {
//			return AppResponseUtil.getErrorBusi("userNotBindBankCard","用户未绑卡不能进行第三方平台绑定");
//		}
		String rlt = null;
		try {
			CimOrginfo org = new CimOrginfo();
			org.setOrgNumber(req.getPlatFromNumber());
			org = cimOrgInfoService.selectOne(org);
			SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
			sysThirdkeyConfig.setOrgNumber(req.getPlatFromNumber());
			sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
			if(sysThirdkeyConfig == null || org == null || org.getOrgBindUserUrl() == null){
				LOGGER.info("绑定第三方平台第三方配置数据不存在: platFormNumber = {}; userId = {}" , req.getPlatFromNumber(), userId);
				return AppResponseUtil.getErrorBusi("dataError","第三方配置数据异常，请联系管理员");
			}
			CrmUserInfo user = crmUserInfoService.queryUserInfoByUserId(userId);
			
			LOGGER.info("绑定第三方平台请求参数 : url = {}" , org.getOrgBindUserUrl());
			Map<String , String> param = new HashMap<String , String>();
			param.put("userId", userId);
			param.put("mobile", user.getMobile());
			
			//注册地址为空则不可以跳转
			if(StringUtils.isBlank(org.getOrgBindUserUrl())){
				return AppResponseUtil.getErrorBusi("dataError","暂时不可以注册哦!请稍后再试");
			}
			
			/**
			 * 发送请求
			 * 1：小赢科技使用小赢证书key方式校验   需单独处理
			 */
			if("OPEN_XIAOYINGLICAI_WEB".equals(sysThirdkeyConfig.getOrgNumber())){
				rlt = xiaoyingkeJiHelper.getRequestResult(org.getOrgBindUserUrl(), param);
			} else {		
				rlt = OpenHttpUtils.httpRequest(sysThirdkeyConfig, RequestTypeEnums.GET, org.getOrgBindUserUrl(), param);
			}
			
			LOGGER.info("绑定第三方平台返回数据" + rlt);
			@SuppressWarnings("unchecked")
			SuccessResponse<Map<String ,Object >> jb = JSONObject.toJavaObject(JSONObject.parseObject(rlt), SuccessResponse.class);
			if(jb == null) {
				LOGGER.error("绑定第三方平台第三方返回数据异常: platFormNumber = {}; userId = {}" , req.getPlatFromNumber(), userId);
				return AppResponseUtil.getErrorBusi("dataError","第三方返回数据异常，请联系管理员");
			}
			if("0".equals(jb.getCode())){
				if(jb.getData().get("isNewUser").toString().equals("N")) {
					//老用户
					return AppResponseUtil.getErrorBusi("old_user_error", "您是该平台老用户，通过T呗投资不能享受红包等奖励，建议购买其他平台产品");
				}
				CrmOrgAcctRel bo = new CrmOrgAcctRel();
				bo.setUserId(userId);
				bo.setOrgNumber(req.getPlatFromNumber());
				bo.setOrgAccount(jb.getData().get("orgAccount").toString());
				bo.setIsInvested(jb.getData().get("isInvested").toString().equals("Y") ? 1 : 0);
				bo.setIsNewUser(jb.getData().get("isNewUser").toString().equals("Y") ? 1 : 0);
				bo.setOrgAccountType(1);
				bo.setUpdateTime(new Date());
				bo.setCreatTime(new Date());
				cimOrgInfoService.bindOrgAcct(bo);
				
				//站内信
				final String platformName = org == null ? "" : org.getName();
			    String content = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_THIRD_ACC_BIND_SUCCESS_INV),platformName,platformName);
			    final SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(AppTypeEnum.INVESTOR.getKey());
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						sysMsgService.addMsg(msg);
					}
				});
				
				//异步发微信消息
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
						wxreq.setUseId(userId);
						wxreq.setTemkey(SysConfigConstant.OPEN_THIRD_ACCOUNT_SUCCESS);
						wxreq.setPlatformName(platformName);//平台名称
						wxreq.setOpenTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
						wxreq.setUseType(2);
						weiXinMsgService.sendWeiXinMsgCommon(wxreq);
					}
				});
				
			} else {
				if("OPEN_XIAONIUZAIXIAN_WEB".equals(org.getOrgNumber()) && jb.getMsg().contains("手机号码已存在") ) {
					//小牛用户绑定 老用户不能绑定提示
					LOGGER.info("绑定第三方平台失败: {}" ,jb.getMsg());
					return AppResponseUtil.getErrorBusi("old_user_error", "您是小牛在线老用户，通过T呗投资不能享受红包等奖励，建议购买其他平台产品");
				}
				LOGGER.info("绑定第三方平台失败: {}" ,jb.getMsg());
				return AppResponseUtil.getErrorBusi("system_error", jb.getMsg());
			}
		} catch (Exception e) {
			LOGGER.error("绑定机构帐号异常: req={},resp={}", new Object[]{req,rlt,e});
			return AppResponseUtil.getErrorBusi("system_error","绑定失败，请联系管理员");
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 平台产品购买跳转地址
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getOrgProductUrl")
	@ResponseBody
	@RequestLogging("机构产品跳转地址")
	public BaseResponse getOrgProductUrl(@Valid OrgJumpRequest req,BindingResult result, AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		//根据产品id查询产品信息
		CimProduct cimProduct = cimProductService.queryProductDetail(req.getProductId()); 
		
		if(cimProduct != null){
			OrgUrlSkipParameterRequest orgUrlSkipParameterRequest = new OrgUrlSkipParameterRequest();
			orgUrlSkipParameterRequest.setOrgNo(req.getOrgNo());
			orgUrlSkipParameterRequest.setThirdProductId(cimProduct.getThirdProductId());
			orgUrlSkipParameterRequest.setUrlSkipType(1);
			Map<String,String> paramsMap = cimOrgInfoService.getOrgUrlSkipParameter(orgUrlSkipParameterRequest,head);
			
			LOGGER.info("机构产品跳转地址最终参数列表={}",JSONObject.toJSONString(paramsMap));
			return AppResponseUtil.getSuccessResponse(paramsMap);
		} else{
			return AppResponseUtil.getErrorBusi("-1", "不存在的平台产品");
		}
	}
	
	/**
	 * 平台用户中心跳转地址
	 * @param orgUrlSkipParameterRequest
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getOrgUserCenterUrl")
	@ResponseBody
	@RequestLogging("机构用户中心跳转地址")
	public BaseResponse getOrgUserCenterUrl(@Valid OrgUrlSkipParameterRequest orgUrlSkipParameterRequest,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		
		orgUrlSkipParameterRequest.setUrlSkipType(2);//用户中心
		Map<String,String> paramsMap = cimOrgInfoService.getOrgUrlSkipParameter(orgUrlSkipParameterRequest,head);
		
        LOGGER.info("机构用户中心跳转地址最终参数列表={}",JSONObject.toJSONString(paramsMap));
		return AppResponseUtil.getSuccessResponse(paramsMap);
	}
	
	/**
	 * 平台跳转地址参数(非产品)
	 * @param orgUrlSkipParameterRequest
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/getOrgUrlSkipParameter")
	@ResponseBody
	@RequestLogging("平台跳转地址参数(非产品)")
	public BaseResponse getOrgUrlSkipParameter(@Valid OrgUrlSkipParameterRequest orgUrlSkipParameterRequest,BindingResult result,AppRequestHead head){
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		
		Map<String,String> paramsMap = cimOrgInfoService.getOrgUrlSkipParameter(orgUrlSkipParameterRequest,head);
		
		StringBuffer returnStrBuffer = new StringBuffer();
		for (Map.Entry<String,String> entry:paramsMap.entrySet()) {
			returnStrBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		returnStrBuffer.deleteCharAt(returnStrBuffer.length()-1);
		String returnStr = returnStrBuffer.toString();
        LOGGER.info("平台跳转(非产品)地址参数={}",returnStr);
        try {
        	returnStr = URLEncoder.encode(returnStrBuffer.toString(), "UTF-8");
		} catch (Exception e) {
			LOGGER.error("平台跳转(非产品)地址编码异常",e);
		}
		return AppResponseUtil.getSuccessResponse(returnStr);
	}
	/**
	 * 是否绑定机构帐号
	 */
	@RequestMapping("isBindOrgAcct")
	@ResponseBody
	@RequestLogging("是否绑定机构帐号")
	public BaseResponse isBindOrgAcct(@Valid BindOrgAcctRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		boolean flag = cimOrgInfoService.isBindOrgAcct(JsonWebTokenHepler.getUserIdByToken(head.getToken()),req.getPlatFromNumber());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isBind", flag);
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 帐号是否存在于第三方平台
	 */
	@RequestMapping("isExistInPlatform")
	@ResponseBody
	@RequestLogging("帐号是否存在于第三方平台")
	public BaseResponse isExistInPlatform(@Valid BindOrgAcctRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CimOrginfo org = new CimOrginfo();
		org.setOrgNumber(req.getPlatFromNumber());
		org = cimOrgInfoService.selectOne(org);
		SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		sysThirdkeyConfig.setOrgNumber(req.getPlatFromNumber());
		sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
		if(sysThirdkeyConfig == null || org == null){
			LOGGER.info("帐号是否存在于第三方平台配置数据错误");
			return AppResponseUtil.getErrorBusi("dataError","第三方配置数据异常");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(org.getOrgUserExistUrl() == null || "".equals(org.getOrgUserExistUrl())) {
			map.put("isExist", false);
			return AppResponseUtil.getSuccessResponse(map);
		}
		CrmUserInfo user = crmUserInfoService.queryUserInfoByUserId(userId);
		
		LOGGER.debug("帐号是否存在于第三方平台 : url = {}" , org.getOrgBindUserUrl());
		Map<String , String> param = new HashMap<String , String>();
		param.put("mobile", user.getMobile());
		param.put("userId", user.getUserId());
		
		/**
		 * 发送请求
		 * 1：小赢科技使用小赢证书key方式校验   需单独处理
		 */
		String rlt = null;
		if("OPEN_XIAOYINGLICAI_WEB".equals(sysThirdkeyConfig.getOrgNumber())){
			rlt = xiaoyingkeJiHelper.getRequestResult(org.getOrgUserExistUrl(), param);
		} else {		
			rlt = OpenHttpUtils.httpRequest(sysThirdkeyConfig, RequestTypeEnums.POST, org.getOrgUserExistUrl(), param);
		}
		
		LOGGER.info("帐号是否存在于第三方平台返回数据" + rlt);
		try {
			@SuppressWarnings("unchecked")
			SuccessResponse<Map<String ,Object >> jb = JSONObject.toJavaObject(JSONObject.parseObject(rlt), SuccessResponse.class);
			if(jb == null || jb.getData() == null) {
				return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
			}
			if("0".equals(jb.getCode()) ){
				if("Y".equals(jb.getData().get("isExist"))){
					map.put("isExist", true);
				}else {
					map.put("isExist", false);
				}
			} else {
				return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
			}
		} catch (Exception e) {
			return AppResponseUtil.getErrorBusi("Third party returns data error", "网络繁忙，请联系客服");
		}
		return AppResponseUtil.getSuccessResponse(map);
	}
	
	/**
	 * 机构推荐选择列表
	 * @param orgRecommendChooseRequest
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestLogging("机构推荐选择列表")
	@RequestMapping("/recommendChooseList")
	public BaseResponse recommendChooseList(AppRequestHead head,OrgRecommendChooseRequest orgRecommendChooseRequest){
		if(StringUtils.isBlank(orgRecommendChooseRequest.getOrgCode())){
			return AppResponseUtil.getErrorBusi("org_number_isNull", "机构编码为null,机构推荐失败");
		}
		OrgRecommendChooseResponse orgRecommendChooseResponse = cimOrgInfoService.recommendChooseList(head,orgRecommendChooseRequest);
		return AppResponseUtil.getSuccessResponse(orgRecommendChooseResponse);
	}
	
	/**
	 * 产品选择推荐
	 * @param orgRecommendByChooseRequest
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestLogging("机构选择推荐")
	@RequestMapping("/recommendByChoose")
	public BaseResponse recommendByChoose(AppRequestHead head,OrgRecommendByChooseRequest orgRecommendByChooseRequest){
		if(StringUtils.isBlank(orgRecommendByChooseRequest.getOrgCode())){
			return AppResponseUtil.getErrorBusi("org_number_isNull", "机构编码为null,机构推荐失败");
		}
		cimOrgInfoService.recommendByChoose(head,orgRecommendByChooseRequest);
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 选择未对接机构
	 */
	@RequestMapping("selectPlatfrom")
	@ResponseBody
	@RequestLogging("选择未对接机构")
	public BaseResponse selectPlatfrom(AppRequestHead head) throws Exception {
		return AppResponseUtil.getSuccessResponse(cimOrgInfoService.queryOrgByStatus(1));
	}
	
	/**
	 * 查询理财师给投资客户推荐的平台
	 */
	@RequestMapping("/queryPlannerRecommendPlatfrom")
	@ResponseBody
	@RequestLogging("查询理财师给投资客户推荐的平台")
	public BaseResponse queryPlannerRecommendPlatfrom(PaginatorRequest req,AppRequestHead head) throws Exception {
		/**
		 * 获取用户id
		 */
		String investUserId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmInvestor investor = crmInvestorService.queryPlannerByInvestUserId(investUserId); //查询用户的上级理财师
		Page<CimOrginfo> page  = new Page<CimOrginfo>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		
		//判断是否PC端投呗
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber()) && AppUtils.isInvestorApp(head.getOrgNumber())){
    		if(StringUtils.isNotBlank(investor.getCfplanner())){ //token为空未登录(广告图片隐藏)
    			PaginatorResponse<CimOrginfo> pcRecommendPlatfroms = cimOrgInfoService.queryPcPlannerRecommendPlatfrom(page, investUserId, investor.getCfplanner());
    			return  AppResponseUtil.getSuccessResponse(pcRecommendPlatfroms,PcPlannerRecommendResponse.class);
    		}else{
    			return  AppResponseUtil.getErrorBusi("planner_notExist","客户没有理财师!"); 
    		}
    	}
    	
    	//移动端
		if(StringUtils.isNotBlank(investor.getCfplanner())){
			PaginatorResponse<CimOrginfo> recommendPlatfroms = cimOrgInfoService.queryPlannerRecommendPlatfrom(page, investUserId, investor.getCfplanner());
			return AppResponseUtil.getSuccessResponse(recommendPlatfroms,PlannerRecommendResponse.class);
		}else{
			return AppResponseUtil.getErrorBusi("planner_notExist","客户没有理财师!");
		}
	}
	
	/**
	 * 投资攻略
	 */
	@RequestMapping("/investmentStrategy")
	@ResponseBody
	@RequestLogging("投资攻略")
	public BaseResponse queryInvestmentStrategy(String orgCode) throws Exception {
		InvestmentStrategyResponse investmentStrategyResponse = cimOrgInfoService.queryInvestmentStrategy(orgCode);
		return AppResponseUtil.getSuccessResponse(investmentStrategyResponse);
	}
	
	/**
	 * 查询机构动态信息
	 */
	@RequestMapping("/queryOrgDynamicInfo")
	@ResponseBody
	@RequestLogging("查询机构动态信息")
	public BaseResponse queryOrgDynamicInfo(int orgDynamicId,AppRequestHead head) throws Exception {
		CimOrgDynamic orgDynamicInfo = cimOrgDynamicService.queryOrgDynamicInfo(orgDynamicId);
		return AppResponseUtil.getSuccessResponse(orgDynamicInfo);
	}
	
	/**
	 * 查询机构安全保障
	 */
	@RequestMapping("/queryOrgSecurity")
	@ResponseBody
	@RequestLogging("查询机构安全保障")
	public BaseResponse queryOrgSecurity(String orgNumber) throws Exception {
		Map<String , String> param = new HashMap<String , String>();
		String orgSecurity = cimOrgInfoService.queryOrgSecurity(orgNumber);
		param.put("orgSecurity", orgSecurity);
		return AppResponseUtil.getSuccessResponse(param);
	}
	
	/**
	 * 精选平台
	 */
	@RequestMapping("/choicenessPlatform")
	@ResponseBody
	@RequestLogging("精选平台")
	public BaseResponse choicenessPlatfrom(AppRequestHead head) throws Exception {
		LOGGER.debug("精选平台 head = {}", JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户
    	List<CimOrginfo> newList =  cimOrgInfoService.queryChoicenessPlatfrom(isGrayUser,userId,head.getOrgNumber());
		return AppResponseUtil.getSuccessResponse(newList,CimOrginfoChannelResponse.class);
	}
	
	/**
	 * 机构分页-4.0版本
	 * @param head
	 * @param paginatorRequest
	 * @return
	 */
    @RequestMapping(value="/orgPageList/4.0")
	@ResponseBody
	@RequestLogging("机构列表4.0")
	public BaseResponse orgPageList4(AppRequestHead appRequestHead,OrgPageList4Request orgPageList4Request){
    	LOGGER.debug("机构列表4.0 请求参数 orgPageList4Request = {}" ,JSON.toJSONString(orgPageList4Request));	
    	orgPageList4Request.setIfHaveGray(sysGrayReleaseService.ifHaveGrayPermission(JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken()), "0,2"));//设置灰度权限
    	PaginatorResponse<OrgPageListResponse> rlt = cimOrgInfoService.queryOrgPageList4(appRequestHead,orgPageList4Request);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    /**
     * 我的投资平台
     * @return
     */
    @RequestMapping(value="/investedPlatform/4.0.0")
    @ResponseBody
    @RequestLogging("我的投资平台")
	public BaseResponse investedPlatform(AppRequestHead head,PaginatorRequest req) {
    	long startDate = System.currentTimeMillis();
    	LOGGER.info("我的投资平台,startTime={}",System.currentTimeMillis());
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken()); //获取用户id
    	Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2"); //判断是否灰度用户 	
    	LOGGER.info("我的投资平台,灰度查询endTime={}",System.currentTimeMillis()-startDate);
    	startDate = System.currentTimeMillis();
    	Map<String, String> statisticsMap = cimOrgInfoService.investStatistics(userId,isGrayUser);
    	LOGGER.info("我的投资平台,总体统计查询endTime={}",System.currentTimeMillis()-startDate);
    	startDate = System.currentTimeMillis();
    	String investingAmt = statisticsMap.get("investAmt");
    	List<InvestStatisticsResponse> platformInvestStatistics = cimOrgInfoService.platformInvestStatistics(userId,isGrayUser);    	
    	List<InvestStatisticsResponse> investingStatisticList = new ArrayList<InvestStatisticsResponse>();
    	LOGGER.info("我的投资平台,平台统计查询endTime={}",System.currentTimeMillis()-startDate);
    	startDate = System.currentTimeMillis();
    	for(InvestStatisticsResponse temp : platformInvestStatistics){
    		if(new BigDecimal(temp.getInvestingAmt()).compareTo(new BigDecimal(0)) != 0){
    			investingStatisticList.add(temp);  			
    		}
    	}
    	BigDecimal leftPercent = new BigDecimal(1);
    	
    	for(int i = 0 ; i < investingStatisticList.size(); i++){ 		
    		InvestStatisticsResponse temp = investingStatisticList.get(i);
    		if(i == investingStatisticList.size()-1){
    			temp.setTotalPercent(NumberUtils.getFormat(leftPercent.multiply(new BigDecimal(100)),"0.00"));
    			break;
    		}
    		BigDecimal rate = new BigDecimal(temp.getInvestAmt()).divide(new BigDecimal(investingAmt), 4, RoundingMode.HALF_UP);
    		String totalPercent = NumberUtils.getFormat(rate.multiply(new BigDecimal(100)),"0.00");
    		temp.setTotalPercent(totalPercent);
    		leftPercent = leftPercent.subtract(rate); 
    	}
    	LOGGER.info("我的投资平台,平台百分比计算endTime={}",System.currentTimeMillis()-startDate);
    	startDate = System.currentTimeMillis();
    	for(InvestStatisticsResponse temp : platformInvestStatistics){
    		
    		/*SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
			sysThirdkeyConfig.setOrgNumber(temp.getOrgNumber());
			sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);*/
    		
    		Map<String,String> paramsMap = new HashMap<String,String>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			paramsMap.put("orgNumber", temp.getOrgNumber());//机构编码
			paramsMap.put("orgKey",temp.getOrgKey());//机构公钥
			paramsMap.put("timestamp",simpleDateFormat.format(new Date()));
			paramsMap.put("orgAccount",temp.getOrgAccount());//第三方机构用户账号
			//判断是否PC端
	    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber())){
	    		paramsMap.put("requestFrom","web");//PC端
	    	}else{
	    		paramsMap.put("requestFrom","wap");//移动端
	    	}
	    	
	        String sign = SignUtils.sign(paramsMap,temp.getOrgSecret());//机构私钥
	        temp.setTimestamp(paramsMap.get("timestamp"));
	        temp.setSign(sign);
	        temp.setOrgKey(temp.getOrgKey());
	        temp.setRequestFrom(paramsMap.get("requestFrom"));
	        
	        temp.setOrgSecret(null);
	        
			/**
			 * 小赢科技 账户单独处理 urlecode编码
			 */
			if("OPEN_XIAOYINGLICAI_WEB".equals(temp.getOrgNumber())){
				try {		
					temp.setOrgAccount(URLEncoder.encode(temp.getOrgAccount(), "UTF-8"));//第三方机构用户账号
				} catch (Exception e) {
					LOGGER.error("小赢科技 账户单独处理 urlecode编码异常",JSONObject.toJSONString(paramsMap));
				}
			}
    	}
    	LOGGER.info("我的投资平台,机构公钥签名等endTime={}",System.currentTimeMillis()-startDate);
    	startDate = System.currentTimeMillis();
    	List<InvestProductStatisticsResponse> investProductStatistics = cimOrgInfoService.investProductStatistics(userId,isGrayUser);
    	LOGGER.info("我的投资平台,在投投资记录信息endTime={}",System.currentTimeMillis()-startDate);
    	startDate = System.currentTimeMillis();
    	BigDecimal totalYearAmount = new BigDecimal(0);
    	for(InvestProductStatisticsResponse productStatistic : investProductStatistics){
    		BigDecimal yearAmount = CalculateTools.yearpurAmountCompute(productStatistic.getInvestingAmt(),productStatistic.getProductDeadLine());
    		productStatistic.setYearAmount(yearAmount);
    		totalYearAmount = totalYearAmount.add(yearAmount);
    	}   	
    	BigDecimal totalYearProfit = new BigDecimal(0);
    	for(InvestProductStatisticsResponse productStatistic : investProductStatistics){
    		totalYearProfit = totalYearProfit.add(productStatistic.getYearAmount().multiply(productStatistic.getProductRate()).divide(new BigDecimal(100)));
    		totalYearProfit = totalYearProfit.add(productStatistic.getTotalFeeAmount());
    		totalYearProfit = totalYearProfit.add(productStatistic.getRedpacketProfit());
    		totalYearProfit = totalYearProfit.add(productStatistic.getVirtualPaltformBouns());
    	}
    	BigDecimal yearProfitRate = new BigDecimal("0.00"); 
    	if(totalYearAmount.compareTo(new BigDecimal(0)) != 0){
    		yearProfitRate = totalYearProfit.divide(totalYearAmount, 4, RoundingMode.HALF_UP);
    		if(yearProfitRate.compareTo(new BigDecimal("0.30")) >=0 ){
    			yearProfitRate = new BigDecimal("0.30");
    		}
    	}
    	LOGGER.info("我的投资平台,综合年化计算endTime={}",System.currentTimeMillis()-startDate);
    	startDate = System.currentTimeMillis();
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.putAll(statisticsMap);
    	resultMap.put("platformInvestStatisticsList", platformInvestStatistics);	
    	resultMap.put("investingStatisticList", investingStatisticList);
    	resultMap.put("investingPlatformNum", investingStatisticList.size());
    	resultMap.put("yearProfitRate", NumberUtils.getFormat(yearProfitRate.multiply(new BigDecimal(100)),"0.00"));
    	LOGGER.info("我的投资平台,endTime={}",System.currentTimeMillis()-startDate);
    	LOGGER.info("我的投资平台,时间总计={}",System.currentTimeMillis()-startDate);
		return AppResponseUtil.getSuccessResponse(resultMap);
		
	}
    
    /**
     * 机构银行充值限制详情列表4.1.1
     * @param req
     * @param result
     * @param head
     * @return
     */
    @RequestMapping(value="/orgRechargeLimitList/4.1.1")
	@ResponseBody
	@RequestLogging("机构银行充值限制详情列表4.1.1")
	public BaseResponse orgRechargeLimitList(@Valid OrgDetailRequest req,BindingResult result, AppRequestHead head){
    	LOGGER.info("机构银行充值限制详情列表请求参数 OrgRechargeLimitRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(req),JSON.toJSONString(head));
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		CimOrgRechargeLimit condition = new CimOrgRechargeLimit();
		condition.setOrgNumber(req.getOrgNo());
		List<CimOrgRechargeLimit> rlt = orgRechargeLimitService.selectListByCondition(condition);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    /**
     * 第三方机构数据详情
     * @param req
     * @param result
     * @param head
     * @return
     */
    @RequestMapping(value="/orgThirdDataDetail")
	@ResponseBody
	@RequestLogging("第三方机构数据详情")
	public BaseResponse orgThirdDataDetail(AppRequestHead head,OrgThirdDataDetailRequest orgThirdDataDetailRequest){
    	LOGGER.info("第三方机构数据详情 OrgRechargeLimitRequest = {} , AppRequestHead = {}" ,JSON.toJSONString(orgThirdDataDetailRequest),JSON.toJSONString(head));
    	orgThirdDataDetailRequest.setUserId(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		OrgThirdDataDetailResponse rlt = cimOrgInfoService.selectOrgThirdDataDetail(orgThirdDataDetailRequest);
		return AppResponseUtil.getSuccessResponse(rlt);
	}    
    
}
