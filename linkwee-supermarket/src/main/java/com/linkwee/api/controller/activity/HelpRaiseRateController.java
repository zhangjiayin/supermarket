package com.linkwee.api.controller.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.request.act.HelpResultRequest;
import com.linkwee.api.request.act.MobileForHelpRequest;
import com.linkwee.api.request.act.MobileRequest;
import com.linkwee.api.request.act.WeixinInfoRequest;
import com.linkwee.api.response.activity.HelpRaiseRateDetailRespspne;
import com.linkwee.api.response.activity.HelpRaiseRateRespspne;
import com.linkwee.api.response.crm.WeiXinInfoResponse;
import com.linkwee.api.response.crm.WeiXinMsgResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.ActivityConstant;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.activity.ActHelpRaiseRate;
import com.linkwee.web.model.activity.ActHelpRaiseRateDetail;
import com.linkwee.web.service.ActHelpRaiseRateDetailService;
import com.linkwee.web.service.ActHelpRaiseRateService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.HttpClientUtil;
import com.linkwee.xoss.util.ResponseUtil;
import com.linkwee.xoss.util.WebUtil;

@Controller
@RequestMapping("/api/helpRaiseRate")
public class HelpRaiseRateController extends BaseController {
			
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private CrmInvestorService crmInvestorService;
	  
	@Resource
	private ActHelpRaiseRateDetailService actHelpRaiseRateDetailService;
	
	@Resource
	private ActHelpRaiseRateService actHelpRaiseRateService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private SmMessageQueueService messageQueueService;
	
	
	/**
	 * 加息活动首页数据
	 */
	@RequestMapping("/homepage")
    @ResponseBody
    public BaseResponse homepage(AppRequestHead head, MobileRequest req){
		
		CrmInvestor inv = null;
		if(req.getMobile() != null && !"".equals(req.getMobile())) {
			inv = crmInvestorService.queryInvestorByMobile(req.getMobile());
			if(inv == null) {
				return AppResponseUtil.getErrorBusi("user is not exist", "网络繁忙");
			}
		} else {
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());		
			if(StringUtils.isBlank(userId) || userId.equals("undefined")){
				return AppResponseUtil.getErrorToken();
			}
			inv = crmInvestorService.queryInvestorByUserId(userId);
			if(inv == null) {
				return AppResponseUtil.getErrorBusi("user is not exist", "网络繁忙");
			}
		}
		
		List<HelpRaiseRateDetailRespspne> list = new ArrayList<HelpRaiseRateDetailRespspne>();
		List<ActHelpRaiseRateDetail> listResp = actHelpRaiseRateDetailService.queryHelpDetailList(inv.getUserId());
		for(ActHelpRaiseRateDetail de : listResp) {
			HelpRaiseRateDetailRespspne detail = new HelpRaiseRateDetailRespspne();
			detail.setDecription(de.getDecription());
			detail.setHelpRate(WebUtil.getDefaultFormat(de.getHelpRate()) + "%");
			detail.setWeixinIcoUrl(de.getWeixinIcoUrl());
			detail.setWeixinNickname(de.getWeixinNickname());
			detail.setHelpTime(DateUtils.format(de.getCreateTime(), DateUtils.FORMAT_MM));
			list.add(detail);
		}
		
		ActHelpRaiseRate req1 = new ActHelpRaiseRate();
		req1.setUserId(inv.getUserId());
		ActHelpRaiseRate bo = actHelpRaiseRateService.selectOne(req1);
		if(bo == null) {
			bo = new ActHelpRaiseRate();
			bo.setCreateTime(new Date());
			bo.setLastUpdateTime(new Date());
			bo.setHelpCount(0);
			bo.setRaiseRate(0.0);
			bo.setUserId(inv.getUserId());
			actHelpRaiseRateService.insert(bo);
		}
		
		HelpRaiseRateRespspne  rlt = new HelpRaiseRateRespspne();
		rlt.setHelpDetailList(list);
		rlt.setMobile(inv.getMobile());
		rlt.setUserName(inv.getUserName());
		rlt.setRaisedRate(WebUtil.getDefaultFormat(bo.getRaiseRate()));
		return AppResponseUtil.getSuccessResponse(rlt);
		
    }
	
	
	/**
	 * 助力
	 */
	@RequestMapping("/help")
    @ResponseBody
    public BaseResponse help(MobileForHelpRequest req,AppRequestHead head, BindingResult result){
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		
		req.setWeixinNickname(filterEmoji(req.getWeixinNickname()));//过滤emoji表情
		
		ActivityList act = activityListService.queryByCode(ActivityConstant.HELP_RAISE_RATE_CODE);
		
		if(act != null && act.getActivityCode() != null && act.getStartDate() != null && act.getEndDate() != null) {
			if(new Date().compareTo(act.getStartDate()) < 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦");
			}
			if(new Date().compareTo(act.getEndDate()) > 0) {
				return AppResponseUtil.getErrorBusi("error", "亲，活动已经结束了哦");
			}
		} else {
			return AppResponseUtil.getErrorBusi("error", "亲，活动还没开始哦");
		}
		
		CrmInvestor inv = crmInvestorService.queryInvestorByMobile(req.getMobile());
		if(inv == null) {
			return AppResponseUtil.getErrorBusi("user is not exist", "用户不存在");
		}
		
		ActHelpRaiseRate req1 = new ActHelpRaiseRate();
		req1.setUserId(inv.getUserId());
		ActHelpRaiseRate bo = actHelpRaiseRateService.selectOne(req1);
		if(bo == null) {
			bo = new ActHelpRaiseRate();
			bo.setCreateTime(new Date());
			bo.setLastUpdateTime(new Date());
			bo.setHelpCount(0);
			bo.setRaiseRate(0.0);
			bo.setUserId(inv.getUserId());
			actHelpRaiseRateService.insert(bo);
		}
		if(bo.getHelpCount() >= ActivityConstant.HELP_RAISE_COUNT) {
			return AppResponseUtil.getErrorBusi("over", "已经不能再加了◑﹏◐");
		}
		
		Double rate;
		try {
			rate = actHelpRaiseRateService.help(inv.getUserId(), req.getWeixinIcoUrl(), req.getWeixinNickname(), req.getOpenid());
		} catch (Exception e) {
			if("only_help_one".equals(e.getMessage())) {
				return AppResponseUtil.getErrorBusi("error", "您只能助力一次哦");
			} else {
				LOGGER.info("助力加息异常：{}" , e.getMessage());
				LOGGER.info("助力加息参数：{}" , JSON.toJSON(req));
				return AppResponseUtil.getErrorBusi("error", "网络繁忙，请联系客服");
			}
		}
		Map<String, String> rlt = new HashMap<String, String>();
		rlt.put("helpRate", WebUtil.getDefaultFormat(rate));
		
		if(bo.getHelpCount() >= ActivityConstant.HELP_RAISE_COUNT - 1) {
			String content = sysConfigService.getValuesByKey(SysConfigConstant.PUSHMESSAGE_INV_HELP_RAISE_RATE);
			pushMessageHelper.pushMessageAsyn(AppTypeEnum.INVESTOR, SmsTypeEnum.INV_HELP_RAISE_RATE,
					inv.getUserId(), "助力加息完成", content, null, false);
			AppTypeEnum appTypeEnum = (AppTypeEnum)EnumUtils.getEnumByKey(AppTypeEnum.INVESTOR.getKey(), AppTypeEnum.values());
			messageQueueService.sendMessageAndSysMsg(null, inv.getUserId(), appTypeEnum,MsgModuleEnum.INV_HELP_RAISE_RATE,true,"2%");
		}
		
		return AppResponseUtil.getSuccessResponse(rlt);
    }
	
	/**
	 * 获取微信信息
	 * @param req
	 * @param head
	 * @param result
	 * @return
	 */
	@RequestMapping("/getWeixinInfo")
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
	 * 助力结果
	 */
	@RequestMapping("/helpResult")
    @ResponseBody
    public BaseResponse helpResult(HelpResultRequest req,AppRequestHead head, BindingResult result){
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		CrmInvestor inv = crmInvestorService.queryInvestorByMobile(req.getMobile());
		if(inv == null) {
			return AppResponseUtil.getErrorBusi("error", "用户不存在");
		}
		List<ActHelpRaiseRateDetail> detailForCheck = actHelpRaiseRateDetailService.queryForUpdate(inv.getUserId(), req.getOpenid());
		if(detailForCheck == null || detailForCheck.size() == 0) {
			return AppResponseUtil.getErrorBusi("error", "没有助力记录");
		}
		Map<String, String> rlt = new HashMap<String, String>();
		rlt.put("helpRate", WebUtil.getDefaultFormat(detailForCheck.get(0).getHelpRate()));
		return AppResponseUtil.getSuccessResponse(rlt);
		
	}
	
	/**
	 * 过滤emoji标签
	 */
	private String filterEmoji(String source) { 
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
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
