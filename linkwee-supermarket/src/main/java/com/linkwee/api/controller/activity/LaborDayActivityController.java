package com.linkwee.api.controller.activity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.web.model.ActLaborDayChangeFaceRecord;
import com.linkwee.web.service.ActLaborDayChangeFaceRecordService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： LaborDayActivityController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 10:12:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/labor")
@RequestLogging("劳动节活动控制器")
public class LaborDayActivityController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(LaborDayActivityController.class);

	@Resource
	private ActLaborDayChangeFaceRecordService actLaborDayChangeFaceRecordService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	/**
	 * 热爱劳动人数
	 * @param head
	 * @return
	 */
	@RequestLogging("热爱劳动人数")
	@RequestMapping("loveWorkNum")
	@ResponseBody
	public BaseResponse loveWorkNum(AppRequestHead head) {
		String loveWorkNum = sysConfigService.getValuesByKey("loveWorkNum");
		Map<String, String> rltMap = new HashMap<String, String>();
		rltMap.put("loveWorkNum", loveWorkNum);
		return AppResponseUtil.getSuccessResponse(rltMap);	
	}
	
	/**
	 * 记录换脸成功的图片
	 * @param head
	 * @param changeFaceRecord
	 * @return
	 */
	@RequestMapping("/changeFace/record")
    @ResponseBody
	@RequestLogging("记录换脸成功的图片")
    public BaseResponse changeFaceRecord(AppRequestHead head,ActLaborDayChangeFaceRecord changeFaceRecord){				
		changeFaceRecord.setCreateTime(new Date());
		changeFaceRecord.setLastUpdateTime(new Date());
		//过滤emoji表情
		changeFaceRecord.setWeixinNickname(filterEmoji(changeFaceRecord.getWeixinNickname()));
		actLaborDayChangeFaceRecordService.insert(changeFaceRecord);
		return AppResponseUtil.getSuccessResponse();
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
