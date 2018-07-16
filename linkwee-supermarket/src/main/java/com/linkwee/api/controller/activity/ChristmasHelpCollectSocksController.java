package com.linkwee.api.controller.activity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.activity.response.ChristmasHomePageResponse;
import com.linkwee.api.request.AwardRequest;
import com.linkwee.api.request.act.SocksCollectHelpRequest;
import com.linkwee.api.request.act.WeixinInfoRequest;
import com.linkwee.api.response.crm.WeiXinInfoResponse;
import com.linkwee.api.response.crm.WeiXinMsgResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.ActivityConstant;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.model.ActChristmasAwardingRecord;
import com.linkwee.web.model.ActChristmasSocks;
import com.linkwee.web.model.ActChristmasSocksDetail;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.service.ActChristmasAwardingRecordService;
import com.linkwee.web.service.ActChristmasSocksDetailService;
import com.linkwee.web.service.ActChristmasSocksService;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.HttpClientUtil;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;
import com.linkwee.xoss.util.WebUtil;

@Controller
@RequestMapping("/api/activity/christmas")
@RequestLogging("圣诞节收集袜子活动")
public class ChristmasHelpCollectSocksController extends BaseController {
			
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private CrmInvestorService crmInvestorService;
	  
	@Resource
	private ActChristmasAwardingRecordService actChristmasAwardingRecordService;
	
	@Resource
	private ActChristmasSocksDetailService actChristmasSocksDetailService;
	
	@Resource
	private ActChristmasSocksService actChristmasSocksService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Resource
	private CrmUserInfoService userInfoService;
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;
	
	/**
	 * 首页数据
	 */
	@RequestMapping("/homepage")
	@RequestLogging("首页数据")
    @ResponseBody
    public BaseResponse homepage(AppRequestHead head,String openId,String userId){	
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
			if(StringUtils.isBlank(userId) || userId.equals("undefined")){
				return AppResponseUtil.getErrorToken();
			}
		}
				
		ActChristmasSocks sockTemp = new ActChristmasSocks();
		sockTemp.setUserId(userId);
		sockTemp = actChristmasSocksService.selectOne(sockTemp);
		
		int usedSocksNum = actChristmasAwardingRecordService.queryUsedSocks(userId);
		
		ChristmasHomePageResponse response = new ChristmasHomePageResponse();
		response.setUserId(userId);
		//根据活动统计表判断信是否已读 未读时插入变成已读
		if(sockTemp == null){
			response.setLetterReadStatus(0);
			sockTemp = new ActChristmasSocks();
			sockTemp.setUserId(userId);
			sockTemp.setCreateTime(new Date());
			actChristmasSocksService.insert(sockTemp);
		}else{
			response.setLetterReadStatus(1);
			response.setSocksNum(sockTemp.getSocksNum());
			response.setLeftSocksNum(sockTemp.getSocksNum()-usedSocksNum);
		}
		if(StringUtils.isNotBlank(openId)){
			ActChristmasSocksDetail temp = new ActChristmasSocksDetail();
			temp.setUserId(userId);
			temp.setOpenid(openId);
			temp = actChristmasSocksDetailService.selectOne(temp);
			if(temp != null){
				response.setHasHelped(1);
			}
		}
		CrmUserInfo user = userInfoService.queryUserInfoByUserId(userId);
		response.setUserName(user.getUserName() == null ? user.getMobile().substring(0, 3)+"****"+user.getMobile().substring(7) : user.getUserName().substring(0, 1)+"*"+user.getUserName().substring(2));
		response.setMobile(user.getMobile());
		return AppResponseUtil.getSuccessResponse(response);
		
    }
	
	/**
	 * 助力记录
	 */
	@RequestMapping("/help/record")
	@RequestLogging("助力记录")
    @ResponseBody
    public BaseResponse helpRecord(String userId){
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		List<ActChristmasSocksDetail> helpRecords = actChristmasSocksDetailService.queryHelpRecord(userId);
		return AppResponseUtil.getSuccessResponse(helpRecords);		
	}
	
	/**
	 * 中奖记录
	 */
	@RequestMapping("/awarding/record")
	@RequestLogging("中奖记录")
    @ResponseBody
    public BaseResponse awardingRecord(String userId){
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		List<ActChristmasAwardingRecord> helpRecords = actChristmasAwardingRecordService.queryAwardingRecord(userId);
		return AppResponseUtil.getSuccessResponse(helpRecords);	
	}
	
	/**
	 * 助力
	 */
	@RequestMapping("/help")
	@RequestLogging("助力")
    @ResponseBody
    public BaseResponse help(SocksCollectHelpRequest req,String userId, BindingResult result){
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}		
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		req.setWeixinNickname(filterEmoji(req.getWeixinNickname()));//过滤emoji表情		
		ActivityList act = activityListService.queryByCode(ActivityConstant.CHRISTMAS_COLLECT_SOCKS_CODE);	
		if(act != null && act.getActivityCode() != null && act.getStartDate() != null && act.getEndDate() != null) {
			if(new Date().compareTo(act.getStartDate()) < 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦!");
			}
			if(new Date().compareTo(act.getEndDate()) > 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动已经结束了哦!");
			}
		} else {
			return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦!");
		}
		
		int sockNum;
		try {
			sockNum = actChristmasSocksDetailService.help(userId,req);
		} catch (Exception e) {
			if("only_help_one".equals(e.getMessage())) {
				return AppResponseUtil.getErrorBusi("error", "您只能助力一次哦!");
			} else {
				LOGGER.info("收集袜子助力异常：{}" , e.getMessage());
				LOGGER.info("收集袜子助力参数：{}" , JSON.toJSON(req));
				return AppResponseUtil.getErrorBusi("error", "网络繁忙，请联系客服!");
			}
		}
		Map<String, String> rlt = new HashMap<String, String>();
		rlt.put("sockNum", WebUtil.getDefaultFormat(sockNum));
		return AppResponseUtil.getSuccessResponse(rlt);
    }
	
	/**
	 * 兑奖
	 * @param head
	 * @return
	 */
	@RequestMapping("/award")
	@RequestLogging("兑奖")
    @ResponseBody
    public BaseResponse award(AwardRequest awardRequest,String userId){			
		String prizeId = awardRequest.getPrizeId();
		if(StringUtils.isBlank(userId) || userId.equals("undefined")){
			return AppResponseUtil.getErrorToken();
		}
		
		ActivityList act = activityListService.queryByCode(ActivityConstant.CHRISTMAS_COLLECT_SOCKS_CODE);	
		if(act != null && act.getActivityCode() != null && act.getStartDate() != null && act.getEndDate() != null) {
			if(new Date().compareTo(act.getStartDate()) < 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦！");
			}
			if(new Date().compareTo(act.getEndDate()) > 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动已经结束了哦！");
			}
		} else {
			return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦！");
		}	
		
		if(!prizeId.equals("1") && !prizeId.equals("2") && !prizeId.equals("3") && !prizeId.equals("4") && !prizeId.equals("5") && !prizeId.equals("6") && !prizeId.equals("7")){
			return AppResponseUtil.getErrorBusi("error", "兑换奖品的参数错误！");
		}
			
		ActChristmasSocks sockTemp = new ActChristmasSocks();
		sockTemp.setUserId(userId);
		sockTemp = actChristmasSocksService.selectOne(sockTemp);
		int usedSocksNum = actChristmasAwardingRecordService.queryUsedSocks(userId);
		int leftSocksNum = sockTemp.getSocksNum()-usedSocksNum;
		if((prizeId.equals("1") && leftSocksNum < 6) 
				|| (prizeId.equals("2") && leftSocksNum < 9)
				|| (prizeId.equals("3") && leftSocksNum < 29)
				|| (prizeId.equals("4") && leftSocksNum < 49)
				|| (prizeId.equals("5") && leftSocksNum < 69)
				|| (prizeId.equals("6") && leftSocksNum < 99)
				|| (prizeId.equals("7") && leftSocksNum < 199)){
			return AppResponseUtil.getErrorBusi("error", "剩余袜子数量不足以兑换该奖品！");
		}
		
		ActChristmasAwardingRecord awardingRecord = new ActChristmasAwardingRecord();
		awardingRecord.setUserId(userId);
		awardingRecord.setPrizeId(Integer.parseInt(prizeId));
		awardingRecord = actChristmasAwardingRecordService.selectOne(awardingRecord);
		if(awardingRecord != null){
			return AppResponseUtil.getErrorBusi("10001", "每个奖品仅可兑换一次！");
		}
		
		//没有兑换并且有剩余的	
		String totalMoney = actChristmasAwardingRecordService.queryInvestedMoneyExceptSomePlatform(userId,DateUtils.format(act.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(act.getEndDate(), DateUtils.FORMAT_LONG),null);
		if(Double.valueOf(totalMoney) < 2000){
			return AppResponseUtil.getErrorBusi("10002", "年化投资额大于2000才能兑奖！");
		}
		
		ActChristmasAwardingRecord temp = new ActChristmasAwardingRecord();
		temp.setPrizeId(Integer.parseInt(prizeId));
		List<ActChristmasAwardingRecord> awardingRecordList = actChristmasAwardingRecordService.selectListByCondition(temp);
		int prizeTotalNum = 0;
		String configValue = "";
		if(Integer.parseInt(prizeId) == 4){
			configValue = sysConfigService.getValuesByKey(ActivityConstant.CHRISTMAS_BATTERY_NUM);
		}else if(Integer.parseInt(prizeId) == 5){
			configValue = sysConfigService.getValuesByKey(ActivityConstant.CHRISTMAS_HUMIDIFIER_NUM);
		}else if(Integer.parseInt(prizeId) == 6){
			configValue = sysConfigService.getValuesByKey(ActivityConstant.CHRISTMAS_TOOTHBRUSH_NUM);
		}else if(Integer.parseInt(prizeId) == 7){
			configValue = sysConfigService.getValuesByKey(ActivityConstant.CHRISTMAS_KINDLE_NUM);
		}
				
		if(StringUtils.isNotBlank(configValue)){		
			prizeTotalNum = Integer.parseInt(configValue);
			if(awardingRecordList.size() >= prizeTotalNum){
				return AppResponseUtil.getErrorBusi("10003", "该奖品已经兑换完了！");
			}
		}
		
		ActChristmasAwardingRecord awardingRecordTemp = new ActChristmasAwardingRecord();
		awardingRecordTemp.setUserId(userId);
		awardingRecordTemp.setPrizeId(Integer.parseInt(prizeId));
		awardingRecordTemp.setCreateTime(new Date());
		if(Integer.parseInt(prizeId) == 1){
			awardingRecordTemp.setPrizeDescription("12元投资红包");
			awardingRecordTemp.setSocksNum(6l);
			try {
				redPacketService.lcsActicityRedPacket(userId, ActicityRedPacketEnum.CHRISTMAS_COLLET_SOCKS_12);
			} catch (Exception e) {
				LOGGER.info("发放红包失败！userId={},redpacket={}",userId,ActicityRedPacketEnum.CHRISTMAS_COLLET_SOCKS_12.getValue());
			}
		}else if(Integer.parseInt(prizeId) == 2){
			awardingRecordTemp.setPrizeDescription("25元投资红包");
			awardingRecordTemp.setSocksNum(9l);
			try {
				redPacketService.lcsActicityRedPacket(userId, ActicityRedPacketEnum.CHRISTMAS_COLLET_SOCKS_25);
			} catch (Exception e) {
				LOGGER.info("发放红包失败！userId={},redpacket={}",userId,ActicityRedPacketEnum.CHRISTMAS_COLLET_SOCKS_25.getValue());
			}
		}else if(Integer.parseInt(prizeId) == 3){
			awardingRecordTemp.setPrizeDescription("总监职级体验券");
			awardingRecordTemp.setSocksNum(29l);
			String useTime = sysConfigService.getValuesByKey("christmas_jobGrade_SM3_use_time");
			String expiresTime = sysConfigService.getValuesByKey("christma_jobGrade_SM3_expires_time");
			ActJobGradeVoucher vou = new ActJobGradeVoucher();
		    vou.setUserId(userId);
		    vou.setActivityAttr("圣诞节活动");
		    vou.setExpiresTime(DateUtils.parse(expiresTime));
		    vou.setUseTime(DateUtils.parse(useTime));
		    vou.setJobGrade("SM3");
		    actJobGradeVoucherService.insertJobGradeVoucher(vou);
		}else if(Integer.parseInt(prizeId) == 4){
			awardingRecordTemp.setPrizeDescription("小米充电宝");
			awardingRecordTemp.setSocksNum(49l);
		}else if(Integer.parseInt(prizeId) == 5){
			awardingRecordTemp.setPrizeDescription("德尔玛加湿器");
			awardingRecordTemp.setSocksNum(69l);
		}else if(Integer.parseInt(prizeId) == 6){
			awardingRecordTemp.setPrizeDescription("米家电动牙刷");
			awardingRecordTemp.setSocksNum(99l);
		}else if(Integer.parseInt(prizeId) == 7){
			awardingRecordTemp.setPrizeDescription("kindle 电子书阅读器");
			awardingRecordTemp.setSocksNum(199l);
		}
		actChristmasAwardingRecordService.insert(awardingRecordTemp);
		CrmInvestor user = crmInvestorService.queryInvestorByUserId(userId);
		if(Integer.parseInt(prizeId) == 1 || Integer.parseInt(prizeId) == 2 || Integer.parseInt(prizeId) == 3){
			messageQueueService.sendSingleMessage(user.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.CHRISTMAS_ACTIVITIES_TWELVE,awardingRecordTemp.getPrizeDescription());
		}else{
			messageQueueService.sendSingleMessage(user.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.CHRISTMAS_ACTIVITIES_TOOTHBRUSH,awardingRecordTemp.getPrizeDescription());
		}
		
		return AppResponseUtil.getSuccessResponse();
    }
	
	/**
	 * 获取微信信息
	 * @param req
	 * @param head
	 * @param result
	 * @return
	 */
	@RequestMapping("/getWeixinInfo")
	@RequestLogging("根据T呗获取微信信息")
    @ResponseBody
    public BaseResponse getWeixinInfo(WeixinInfoRequest req, BindingResult result){
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String appID = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_APPID).trim();
		String appSecret = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_APPSECRET).trim();
		String url = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_QUERY_OPENID_URL).trim();
		//根据微信code获得用户的openId
		String ret = HttpClientUtil.httpsGet(String.format(url, appID, appSecret, req.getCode()));
		LOGGER.info("根据微信code获得用户的openId【{}】", ret);
		WeiXinMsgResponse wx = (WeiXinMsgResponse) JSON.parseObject(ret, WeiXinMsgResponse.class);
		if(wx == null || wx.getOpenid() == null){
			LOGGER.info("根据微信code获得用户的openId是被【{}】", wx.getErrmsg());
			return AppResponseUtil.getErrorBusi(wx.getErrmsg(), "网络繁忙");
		}
		
		String urlForWeixinInfo = "https://api.weixin.qq.com/sns/userinfo"
				+ "?access_token=" + wx.getAccess_token()
				+ "&openid=" + wx.getOpenid()
				+ "&lang=zh_CN";
		String weixinInfoRtl = HttpClientUtil.httpsGet(urlForWeixinInfo);
		LOGGER.info("获得微信信息结果【{}】", weixinInfoRtl);
		WeiXinInfoResponse weixinInfo = (WeiXinInfoResponse) JSON.parseObject(weixinInfoRtl, WeiXinInfoResponse.class);
		return AppResponseUtil.getSuccessResponse(weixinInfo);
	}
	
	/**
	 * 获取微信信息
	 * @param req
	 * @param head
	 * @param result
	 * @return
	 */
	@RequestMapping("/getLieCaiWeixinInfo")
	@RequestLogging("根据猎财大师获取微信信息")
    @ResponseBody
    public BaseResponse getLieCaiWeixinInfo(WeixinInfoRequest req, BindingResult result){
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		String appID = sysConfigService.getValuesByKey(SysConfigConstant.LIECAI_APPID).trim();
		String appSecret = sysConfigService.getValuesByKey(SysConfigConstant.LIECAI_APPSECRET).trim();
		String url = sysConfigService.getValuesByKey(SysConfigConstant.LIECAI_QUERY_OPENID_URL).trim();
		//根据微信code获得用户的openId
		String ret = HttpClientUtil.httpsGet(String.format(url, appID, appSecret, req.getCode()));
		LOGGER.info("根据微信code获得用户的openId【{}】", ret);
		WeiXinMsgResponse wx = (WeiXinMsgResponse) JSON.parseObject(ret, WeiXinMsgResponse.class);
		if(wx == null || wx.getOpenid() == null){
			LOGGER.info("根据微信code获得用户的openId是被【{}】", wx.getErrmsg());
			return AppResponseUtil.getErrorBusi(wx.getErrmsg(), "网络繁忙");
		}
		
		String urlForWeixinInfo = "https://api.weixin.qq.com/sns/userinfo"
				+ "?access_token=" + wx.getAccess_token()
				+ "&openid=" + wx.getOpenid()
				+ "&lang=zh_CN";
		String weixinInfoRtl = HttpClientUtil.httpsGet(urlForWeixinInfo);
		LOGGER.info("获得微信信息结果【{}】", weixinInfoRtl);
		WeiXinInfoResponse weixinInfo = (WeiXinInfoResponse) JSON.parseObject(weixinInfoRtl, WeiXinInfoResponse.class);
		return AppResponseUtil.getSuccessResponse(weixinInfo);
	}
	
	/**
	 * 过滤emoji标签
	 */
	private String filterEmoji(String source) { 
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("");
                return source ;
            }
        return source;
       }
       return source; 
    }
	
}
