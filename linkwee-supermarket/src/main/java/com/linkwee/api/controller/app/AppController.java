package com.linkwee.api.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.linkwee.xoss.util.HtmlFilterUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.FeedbackRequest;
import com.linkwee.api.request.NewsDetailRequest;
import com.linkwee.api.request.NewsPageListRequest;
import com.linkwee.api.request.SysConfigRequest;
import com.linkwee.api.response.NewsDtlResponse;
import com.linkwee.api.response.NewsListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.SmAppVersion;
import com.linkwee.web.model.SmFeedback;
import com.linkwee.web.model.SmNews;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.mc.Classroom;
import com.linkwee.web.response.AppVersionResponse;
import com.linkwee.web.response.DownloadAppListResponse;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.ClassroomService;
import com.linkwee.web.service.CrmCfpNewcomerTaskService;
import com.linkwee.web.service.SmAppVersionService;
import com.linkwee.web.service.SmFeedbackService;
import com.linkwee.web.service.SmNewsService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping(value = "/api/app")
@RequestLogging("APP系统配置")
public class AppController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);
	
	@Resource
	private SmFeedbackService smFeedbackService;
	
	@Resource
	private SmAppVersionService smAppVersionService;
	
	@Resource
	private SmNewsService smNewsService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private ConfigHelper configHelper;
	
	@Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private ClassroomService classroomService;
	
	@Resource
	private CrmCfpNewcomerTaskService crmCfpNewcomerTaskService;
	
	private String errorCode = "140003";
	
    /**
	 * 查询客户端版本
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestLogging("查询客户端版本")
	@RequestMapping("/appVersion")
	public BaseResponse appversion(AppRequestHead head) {
		
		if(StringUtils.isBlank(head.getOrgNumber())){
			return  new BaseResponse(errorCode,"OrgNumber不能为空");
		}
		if(StringUtils.isBlank(head.getAppVersion())){
			return  new BaseResponse(errorCode,"AppVersion不能为空");
		}
		
		PlatformEnum platForm = AppUtils.getPlatform(head.getOrgNumber());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmAppVersion data = smAppVersionService.getAppVersion(platForm,appType);
		if(data!=null){
			AppVersionResponse rlt = new AppVersionResponse(data);
			int currFlag = WebUtil.compareVersion(head.getAppVersion(),data.getVersion());
			int minFlag = WebUtil.compareVersion(head.getAppVersion(),data.getMinVersion());
			if(currFlag>=0){//0不需要升级,1提示升级,2强制升级
				rlt.setUpgrade("0");
			}else{
				if(minFlag>=0){
					rlt.setUpgrade("1");
				}else{
					rlt.setUpgrade("2");
				}
			}
			return AppResponseUtil.getSuccessResponse(rlt);
		}else{
			AppVersionResponse rlt = new AppVersionResponse();
			rlt.setUpgrade("0");
			return AppResponseUtil.getSuccessResponse(rlt);
		}
	}
	
	/**
	 * APP下载列表
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestLogging("APP下载列表")
	@RequestMapping("/downloadAppList")
	public BaseResponse downloadAppList(AppRequestHead head) {
		
		if(StringUtils.isBlank(head.getOrgNumber())){
			return  new BaseResponse(errorCode,"OrgNumber不能为空");
		}
		
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		List<SmAppVersion> datas = smAppVersionService.queryNewAppVersion(appType);
		return AppResponseUtil.getSuccessResponse(datas,DownloadAppListResponse.class);
	}
	
	/**
	 * APP下载列表，解决前端不能在requestHead中切换的问题
	 * @param head
	 * @param appType
	 * @return
	 */
	@ResponseBody
	@RequestLogging("APP下载列表(根据前端传AppType)")
	@RequestMapping("/downloadAppList/diffByAppType")
	public BaseResponse downloadAppListDiffByAppType(AppRequestHead head,String appType) {
		
		if(StringUtils.isBlank(head.getOrgNumber())){
			return  new BaseResponse(errorCode,"OrgNumber不能为空");
		}
		
		if(StringUtils.isBlank(appType)){
			return  new BaseResponse(errorCode,"appType不能为空");
		}
		
		Integer appTypeTemp = ((AppTypeEnum)EnumUtils.getEnumByValue(appType, AppTypeEnum.values())).getKey();
		List<SmAppVersion> datas = smAppVersionService.queryNewAppVersion(appTypeTemp);
		return AppResponseUtil.getSuccessResponse(datas,DownloadAppListResponse.class);
	}
	
	/**
	 * APP日志
	 * @param head
	 * @return
	 */
	@ResponseBody
	@RequestLogging("APP日志")
	@RequestMapping("/appLogList/2.0.3")
	public BaseResponse appLogList(AppRequestHead head,String specialOrgNumber) {
		
		if(StringUtils.isBlank(specialOrgNumber)){
			return  new BaseResponse(errorCode,"specialOrgNumber不能为空");
		}
			
		PlatformEnum platForm = AppUtils.getPlatform(specialOrgNumber);
		Integer appType = AppUtils.getAppType(specialOrgNumber).getKey();
		List<SmAppVersion> datas = smAppVersionService.queryAppLogList(appType,platForm);
		return AppResponseUtil.getSuccessResponse(datas);
	}
	
	/**
	 * 添加反馈意见
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/suggestion")
	@ResponseBody
	@RequestLogging("添加反馈意见")
	public BaseResponse feedbackSuggestion(FeedbackRequest feedbackRequest,AppRequestHead appRequestHead) throws Exception {
		
		if(StringUtils.isBlank(appRequestHead.getOrgNumber())){
			return  new BaseResponse("140002","OrgNumber不能为空");
		}
		if(StringUtils.isBlank(appRequestHead.getToken())){
			return  new BaseResponse(errorCode,"Token不能为空");
		}
		
		LOGGER.info("添加反馈意见, feedbackRequest={}",JSONObject.toJSONString(feedbackRequest));
		LOGGER.info("反馈内容,content={}",feedbackRequest.getContent());
		//String content = filterEmoji(feedbackRequest.getContent());
		String content = feedbackRequest.getContent()==null?"":feedbackRequest.getContent().replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		content = HtmlFilterUtil.filterHtml(content);
		LOGGER.info("反馈内容,content={}",content);
		Integer appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());
		SmFeedback smFeedback = new SmFeedback();
		smFeedback.setUserId(userId);
		smFeedback.setAppType(appType);
		smFeedback.setContent(content);
		int rltCount = smFeedbackService.insert(smFeedback);
		if(rltCount > 0){
			return AppResponseUtil.getSuccessResponse();
		}
		else{
			return new BaseResponse(-1,"添加失败，请联系客服");
		}		
	}
	
	/**
     * 检测是否有emoji字符
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }
 
        int len = source.length();
 
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
 
            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }
 
        return false;
    }
 
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
 
    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
 
        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;
 
        int len = source.length();
 
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
 
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
 
                buf.append(codePoint);
            } else {
            }
        }
 
        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
 
    }
	
	/**
	 * 资讯-分页
	 * @param req
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/newsPageList")
	@ResponseBody
	@RequestLogging("资讯分页")
	public BaseResponse newsPageList(NewsPageListRequest newsPageListRequest,AppRequestHead appRequestHead) throws Exception {
		
		if(StringUtils.isBlank(appRequestHead.getOrgNumber())){
			return  new BaseResponse("140002","OrgNumber不能为空");
		}		
		
		Integer appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());
		if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
			sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_NEWS_READED, userId,appType);
		}
			
		//取得AppType的值
		newsPageListRequest.setAppType(appType);
    	LOGGER.info("查询资讯分页, newsPageListRequest={}",JSONObject.toJSONString(newsPageListRequest));
    	Page<SmNews> page  = new Page<SmNews>(newsPageListRequest.getPageIndex(),newsPageListRequest.getPageSize());
		PaginatorResponse<SmNews> rlt = smNewsService.queryNewsPageList(newsPageListRequest,page);
		return AppResponseUtil.getSuccessResponse(rlt,NewsListResponse.class);
	}
	
	
	@RequestMapping("/newsTop/4.0.0")
	@ResponseBody
	@RequestLogging("首页热门资讯")
	public BaseResponse newsTop(NewsPageListRequest newsPageListRequest,AppRequestHead appRequestHead) throws Exception {
		
		if(StringUtils.isBlank(appRequestHead.getOrgNumber())){
			return  new BaseResponse("140002","OrgNumber不能为空");
		}		
		
		Integer appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());
		if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
			sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_NEWS_READED, userId,appType);
		}
			
		//取得AppType的值
		newsPageListRequest.setAppType(appType);
		newsPageListRequest.setLimitNumber(2);
    	LOGGER.info("首页热门资讯, request={}",JSONObject.toJSONString(newsPageListRequest));
    	List<SmNews> rlt = smNewsService.queryTop(newsPageListRequest);
		return AppResponseUtil.getSuccessResponse(rlt,NewsListResponse.class);
	}
		
	/**
	 * 资讯详情
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pageList/detail")
	@ResponseBody
	@RequestLogging("资讯详情")
	public BaseResponse newsDetail(@Valid NewsDetailRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		SmNews rlt = smNewsService.findNewsDtl(req.getNewsId());
		return AppResponseUtil.getSuccessResponse(rlt,NewsDtlResponse.class);
	}
	
	/**
     * 根据应用类别查看系统配置列表
     * @author hxb
     * @param appType
     * @return
     */
    @RequestMapping(value="/sys-config/appType", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("根据应用类别查看系统配置列表")
    public BaseResponse getSysConfigsByAppType(AppRequestHead head,SysConfigRequest sysConfigRequest) {
    	LOGGER.info("根据应用类别查看系统配置列表, sysConfigRequest={}", JSONObject.toJSONString(sysConfigRequest));
    	Page<SysConfig> page  = new Page<SysConfig>(sysConfigRequest.getPageIndex(),sysConfigRequest.getPageSize());
		PaginatorResponse<SysConfig> rlt = sysConfigService.selectByAppType(sysConfigRequest, page);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    /**
     * 查看系统配置列表
     * @author hxb
     * @param 
     * @return
     */
    @RequestMapping(value="/default-config", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看系统配置列表")
    public BaseResponse getSysConfigs(AppRequestHead appRequestHead,SysConfigRequest sysConfigRequest) {
    	
    	if(StringUtils.isBlank(appRequestHead.getOrgNumber())){
			return  new BaseResponse("140002","OrgNumber不能为空");
		}	
    	
    	LOGGER.info("查看系统配置列表, sysConfigRequest={}", JSONObject.toJSONString(sysConfigRequest));
    	Integer appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getKey();
    	
    	List<SysConfig> sysList = sysConfigService.querySysConfigByName("客户端配置");

    	Map<String,String> rlt = new HashMap<String,String>();
    	for(SysConfig item:sysList){
    		if(item.getAppType().equals(appType) || item.getAppType().equals(0)){
    			rlt.put(item.getConfigKey(),item.getConfigValue());
    		}    		  		
    	}  
    	
		return AppResponseUtil.getSuccessResponse(rlt);
	}
    
    /**
     * ios补丁
     * @return
     */
    @RequestMapping("iosPatchData")
	@ResponseBody
    public BaseResponse iosPatchData(AppRequestHead head) {
    	String pathchData = "";
    	HashMap<String ,String > map = new HashMap<String ,String >();
		SysConfig conf = new SysConfig();
		conf.setConfigKey(SysConfigConstant.IOS_PATCH_DATA);
		conf.setConfigType(SysConfigConstant.IOS_PATCH_DATA);    
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			conf.setAppType(1);
		} else  {
			conf.setAppType(2);
		}
		conf = sysConfigService.selectOne(conf);
		if(conf != null && conf.getConfigValue() != null) {
			pathchData = conf.getConfigValue();
		}
		map.put("pathchData", pathchData);
		return AppResponseUtil.getSuccessResponse(map);
	}
    
    @RequestMapping(value="news/readed/2.0.2")
	@ResponseBody
	@RequestLogging("是否有未读的资讯")
	public BaseResponse hasReaded(AppRequestHead head) {
    	LOGGER.info("是否有未读的资讯请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_NEWS_READED, userId, appType);
		SmNews newestNews = smNewsService.queryNewest(appType);
		Map<String,Object> resp = new HashMap<String,Object>();
		Integer compareResult;
		if(newestNews == null && apiInvokeLog == null){
			compareResult = 0;
		}else if(apiInvokeLog == null){
			compareResult = 1;
		}else if(newestNews == null){
			compareResult = 0;
		}else{
			compareResult =DateUtils.compareDate(apiInvokeLog.getChgTime(), newestNews.getValidBegin());
			if(compareResult < 0){
				compareResult = 1;
			}
			else {
				compareResult = 0;
			}	
		}
		
		resp.put("readed", compareResult);		
		return AppResponseUtil.getSuccessResponse(resp);
		
	}
    
    @RequestMapping(value="newsandactivity/readed/2.0.2")
	@ResponseBody
	@RequestLogging("是否有未读的资讯、课堂或活动")
	public BaseResponse newsAndActivityReaded(AppRequestHead head) {
    	LOGGER.info("是否有未读的资讯、课堂或活动请求参数AppRequestHead = {}" ,JSON.toJSONString(head));
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		if(StringUtils.isBlank(userId) || userId.equals("undefined") || appType == null){
			return new ErrorResponse("100002","参数错误");
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_NEWS_READED, userId, appType);
		SmNews newestNews = smNewsService.queryNewest(appType);
		Map<String,Object> resp = new HashMap<String,Object>();
		Integer compareResult;
		if(newestNews == null && apiInvokeLog == null){
			compareResult = 0;
		}else if(apiInvokeLog == null){
			compareResult = 1;
		}else if(newestNews == null){
			compareResult = 0;
		}else{
			compareResult =DateUtils.compareDate(apiInvokeLog.getChgTime(), newestNews.getValidBegin());
			if(compareResult < 0){
				compareResult = 1;
			}
			else {
				compareResult = 0;
			}	
		}
			
		SysApiInvokeLog apiInvokeLogActivity = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_ACTIVITY_READED, userId, appType);
		ActivityList newestActivity = activityListService.queryNewest(appType);
		Integer compareResult2;
		if(newestActivity == null && apiInvokeLogActivity == null){
			compareResult2 = 0;
		}else if(apiInvokeLogActivity == null){
			compareResult2 = 1;		
		}else if(newestActivity == null){
			compareResult2 = 0;	
		}else{
			compareResult2 =DateUtils.compareDate(apiInvokeLogActivity.getChgTime(), newestActivity.getStartDate());
			if(compareResult2 < 0){
				compareResult2 = 1;
			}
			else {
				compareResult2 = 0;
			}	
		}
		
		SysApiInvokeLog apiInvokeLogClassroom = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CLASSROOM_READED, userId, appType);
		Classroom newestClassroom = classroomService.queryNewest(appType);
		Integer compareResult3;
		if(newestClassroom == null && apiInvokeLogClassroom == null){
			compareResult3 = 0;
		}else if(apiInvokeLogClassroom == null){
			compareResult3 = 1;		
		}else if(newestClassroom == null){
			compareResult3 = 0;	
		}else{
			compareResult3 =DateUtils.compareDate(apiInvokeLogClassroom.getChgTime(), newestClassroom.getValidBegin());
			if(compareResult3 < 0){
				compareResult3 = 1;
			}
			else {
				compareResult3 = 0;
			}	
		}
		
		Integer liecaiResult;
		if(compareResult == 0 && compareResult2 == 0 && compareResult3 == 0){
			liecaiResult = 0;
		}else {
			liecaiResult = 1;
		}
		
		if(AppUtils.isChannelApp(head.getOrgNumber())){
			resp.put("unFinishNewcomerTaskCount", crmCfpNewcomerTaskService.queryUnFinishNewcomerTaskCount(userId));		
		}
	
		resp.put("newsReaded", compareResult);
		resp.put("activityReaded", compareResult2);
		resp.put("classroomReaded", compareResult3);
		resp.put("liecaiReaded", liecaiResult);
		return AppResponseUtil.getSuccessResponse(resp);		
	}
   
    
	/**
     * 查询系统配置列表
     * @param appType
     * @return
     */
    @RequestMapping(value="/sysConfig/config", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查询系统配置列表")
    public BaseResponse getSysConfig(AppRequestHead appRequestHead,SysConfig sysConfig) {
    	sysConfig.setAppType(AppUtils.getAppType(appRequestHead.getOrgNumber()).getKey());
    	LOGGER.info("查询系统配置列表, sysConfig={}", JSONObject.toJSONString(sysConfig));
    	sysConfig = sysConfigService.getSysConfig(sysConfig);
    	if(sysConfig == null){
    		return AppResponseUtil.getErrorData("-1", "查询无系统配置");
    	} else {
    		return AppResponseUtil.getSuccessResponse(sysConfig);
    	}
	}
    
	/**
     * 查询系统配置列表-多个
     * @param appType
     * @return
     */
    @RequestMapping(value="/sysConfig/configs", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查询系统配置列表")
    public BaseResponse getSysConfigs(AppRequestHead appRequestHead,SysConfig sysConfig) {
    	sysConfig.setAppType(AppUtils.getAppType(appRequestHead.getOrgNumber()).getKey());
    	LOGGER.info("查询系统配置列表, sysConfig={}", JSONObject.toJSONString(sysConfig));
    	List<SysConfig> sysConfigList = sysConfigService.selectListByCondition(sysConfig);
    	if(CollectionUtils.isNotEmpty(sysConfigList)){
    		return AppResponseUtil.getErrorData("-1", "查询无系统配置");
    	} else {
    		return AppResponseUtil.getSuccessResponse(sysConfigList);
    	}
	}
    
    @RequestMapping("/newsTop/4.6.0")
	@ResponseBody
	@RequestLogging("发现置顶资讯列表")
	public BaseResponse stickNewsTopList(NewsPageListRequest newsPageListRequest,AppRequestHead appRequestHead) throws Exception {
		
		if(StringUtils.isBlank(appRequestHead.getOrgNumber())){
			return  new BaseResponse("140002","OrgNumber不能为空");
		}		
		Integer appType = AppUtils.getAppType(appRequestHead.getOrgNumber()).getKey();
		newsPageListRequest.setAppType(appType);
		newsPageListRequest.setLimitNumber(3);
    	LOGGER.info("发现置顶资讯列表, request={}",JSONObject.toJSONString(newsPageListRequest));
    	List<SmNews> newsList = smNewsService.queryTop(newsPageListRequest);
    	Classroom newestClassroom = classroomService.queryNewest(appType);
    	SmNews news = new SmNews();
    	news.setTitle("每日财经早报—"+DateUtils.format(newestClassroom.getValidBegin(), DateUtils.FORMAT_SHORT_CN));
    	news.setValidBegin(newestClassroom.getValidBegin());
    	List<SmNews> rlt = new ArrayList<SmNews>();
    	rlt.add(news);
    	for(SmNews temp : newsList){
    		rlt.add(temp);
    	}
		return AppResponseUtil.getSuccessResponse(rlt,NewsListResponse.class);
	}
}
