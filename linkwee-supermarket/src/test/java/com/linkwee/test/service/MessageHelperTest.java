package com.linkwee.test.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.test.TestSupport;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MobileOsTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysPushMessageService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;


public class MessageHelperTest extends TestSupport {

    @Resource
    private PushMessageHelper pushMessageHelper; //app推送
    @Resource
    private SysPushMessageService sysPushMessageService;
    @Resource 
    private ConfigHelper configHelper;
    @Resource
	private SmMessageQueueService smMessageQueueService; //发短信
    @Resource 
	private WeiXinMsgService weiXinMsgService; //微信推送
   
    //@Test
    public void testPushByAppId() throws Exception {
    	start();
    	Map<String,Object> customermap = new HashMap<String,Object>();
		customermap.put("userNumber", "");
    	pushMessageHelper.pushByAppId(AppTypeEnum.CHANNEL, MobileOsTypeEnum.ANDROID,SmsTypeEnum.CREGISTER, "领会猎财大师一周年活动", "领会一周年加息活动", customermap);
        end();
    }
    
   
    
    @Test
    public void testAnyPushMsg() throws Exception {
    	start();
    	Map<String,Object> customermap = new HashMap<String,Object>();
		customermap.put("customerId", "444444444444444");
		// 4ae43985a736463a892f3db7b998ecd6 c1add0a7c3e2438f853ae270e975d958 1d06a0942eb54adfa6eafabf2f959033 182 1920 4945 8823af891d444641b340087e30eeaedc
		Map<String,Object> urlparam = Maps.newHashMap();
		urlparam.put("productDetailUrl", configHelper.getValue(SysConfigConstant.RECOMEND_DETAIL_URL_INV)+"?productId=9DFC6435063F421D83ED46ED57A1B6ED");
		urlparam.put("productId","9DFC6435063F421D83ED46ED57A1B6ED");
		urlparam.put("orgName","五星财富");
		List<String> userIds = Lists.newArrayList();
		userIds.add("c1add0a7c3e2438f853ae270e975d958");
		//CommonTCSResult rlt = pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.PRODUCTDTL_INC, userIds, "产品推荐", "跳转产品详情", urlparam, true, PersonalMsgTypeEnum.RECOMMEND_PRODUCT);
		//CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL,SmsTypeEnum.LCSDECLARATIONFORMAUDITNOTICE,"822b71784d6f497cb891626fac538a14", "安卓角标测试推送标题", "安卓角标测试推送内容", null,false);
		//Map<String,Object> urlparam = Maps.newHashMap();
		urlparam.put("orgNo","OPEN_WUXINGCAIFU_WEB");
		//pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.PLATFORMDTL_INC, userIds, "平台推荐", "理财师推荐平台", urlparam, false, PersonalMsgTypeEnum.RECOMMEND_PLATFORM);
		//INVITATIONRECORD_INC
		//CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR,SmsTypeEnum.INVITATIONRECORD_INC,"c1add0a7c3e2438f853ae270e975d958", "安卓邀请列表", "安卓邀请列表推送内容", null,false);
		//pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR,SmsTypeEnum.PERSONALMSGCT_INC,"c1add0a7c3e2438f853ae270e975d958", "安卓消息中心", "安卓消息中心推送内容", null,false);
		//MYREDPACKET_INC
		//pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR,SmsTypeEnum.MYREDPACKET_INC,"c1add0a7c3e2438f853ae270e975d958", "安卓我的红包", "安卓我的红包推送内容", null,false);
		//REWARDBALANCE_INC
		//CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR,SmsTypeEnum.WITHDRAWRECORD,"c1add0a7c3e2438f853ae270e975d958", "安卓提现记录", "安卓提现记录", null,false);
		//urlparam.put("typeValue", appType);
		//urlparam.put("typeValue", 3);
		//CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR,SmsTypeEnum.REWARDBALANCE_INC,"c1add0a7c3e2438f853ae270e975d958", "安卓T呗账户奖励", "安卓T呗账户奖励内容", urlparam,false);
		//MYINVESTRECORD_INC
		urlparam.clear();
		urlparam.put("status", 2);
		//1d06a0942eb54adfa6eafabf2f959033  4ae43985a736463a892f3db7b998ecd6  dfaedfbbf40b44e0a6ad2659471569b4
		CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR,SmsTypeEnum.MYINVESTRECORD_INC,"dfaedfbbf40b44e0a6ad2659471569b4", "我的投资", "我的投资内容(回款完成)", urlparam,true);
		//CommonTCSResult rlt=pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.BDLCSLCSSUCCESS, "822b71784d6f497cb891626fac538a14", "解绑理财师提醒", "解绑提醒内容", null, false);
		System.out.println(rlt.getMessage());
        end();
    }
    
    
  
    @Test
    public void testPushMessageList() throws Exception{
    	start();    	
    	System.out.println(pushMessageHelper.pushMessageList("2b3c310e281e4cec93ad7b5ff2f2cba9android").getMessage());    	
        end();
    }
    
   
    /**
     * 推送消息
     * @author yalin 
     * @date 2017年4月6日 上午11:22:26  
     * @throws Exception
     */
   // @Test
	public void  testPushMessage() throws Exception {
    	start();
    	String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCS_LEADER_REWARD_SUBORDINATE_UNBIND); //查询系统配置表
    	String content = String.format(contentTemp,"yalin" +"18676798087".replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2")); 
    	
    	Map<String,Object> urlparam = Maps.newHashMap(); //urlparam 前端打开页面所需参数
    	//构建个人消息对象
    	/*
		SysMsg msg = new SysMsg();				
		msg.setContent(content);
		msg.setStatus(0);// 发布
		msg.setUserNumber(investorUserId);
		msg.setReadStatus(0);// 未读
		msg.setAppType(AppTypeEnum.INVESTOR.getKey());
		msg.setTypeName(PersonalMsgTypeEnum.PROJECTINVEST_INV.getValue());
		msg.setStartTime(new Date());
		msg.setCrtTime(new Date());
		msg.setModifyTime(new Date());
		msg.setLinkBtnTxt("立即查看");
		msg.setLinkUrlKey(PersonalMsgTypeEnum.RECOMMEND_PLATFORM.getMsg());
		
		sysMsgService.add(msg);
		*/
    	//urlparam 前端打开页面所需参数, 
    	//SmsTypeEnum msg值为：cusDetail urlparam.put("customerId","1111111")
    	//msg值为：teamDetail urlparam.put("userNumber","1111111")
    	//isNeedAppMsg 是否需要推送系统消息中心消息 true 同时推送系统消息中心消息Returns:
    	//CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR,SmsTypeEnum.MYINVESTRECORD_INC,"dfaedfbbf40b44e0a6ad2659471569b4", "我的投资", "我的投资内容(回款完成)", urlparam,true);
    	CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
    														SmsTypeEnum.LCS_LEADER_REWARD_SUBORDINATE_UNBIND, 
    														"dfaedfbbf40b44e0a6ad2659471569b4", 
    														"leader奖励核算变更(下级解绑)", content, urlparam, true);
    	System.out.println(rlt.getMessage());
    	end();
	}
    
	/**
	 * 短信+APP推送+微信推送
	 * @author yalin 
	 * @date 2017年4月7日 下午5:23:48  
	 * @throws Exception
	 */
	public void testSendMsg() throws Exception {
		start();
		
		/**
    	 * 单用户推送
    	 */
    	String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LGRADEONESALE); //一级团队成员销售成功
    	String content = String.format(contentTemp,"yalin" +"18676798087".replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"),"天天牛","10000.00","您将获得100元推荐奖励、10元直接管理津贴、1元团队管理津贴。"); 
    	Map<String,Object> urlparam = Maps.newHashMap(); //urlparam 前端打开页面所需参数
    	
    	CommonTCSResult rlt = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, 
				SmsTypeEnum.LGRADEONESALE, //点击跳转
				"dfaedfbbf40b44e0a6ad2659471569b4", 
				"团队一级成员销售成功", content, urlparam, true);
    	System.out.println(rlt.getMessage());
    	
    	/**
    	 * 发短信
    	 */
    	System.out.println(smMessageQueueService.sendSingleMessage("18676798087", AppTypeEnum.CHANNEL, MsgModuleEnum.LCS_LEADER_REWARD_SUBORDINATE_UNBIND,  "黄亚林").getMessage());
    	
    	/**
    	 * 发微信推送
    	 */
		WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
		wxreq.setUseId("dfaedfbbf40b44e0a6ad2659471569b4");
		wxreq.setTemkey(SysConfigConstant.LIECAI_PARENT_INVESTMENT_SUCCESS);
		wxreq.setPlatformName("小牛在线");//平台名称
		wxreq.setOpenTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
		wxreq.setUseType(1);
		weiXinMsgService.sendWeiXinMsgCommon(wxreq);
		end();
	}
   
}
