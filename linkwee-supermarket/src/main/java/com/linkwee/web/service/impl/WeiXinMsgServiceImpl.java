package com.linkwee.web.service.impl;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.model.weixin.First;
import com.linkwee.web.model.weixin.Keyword1;
import com.linkwee.web.model.weixin.Keyword2;
import com.linkwee.web.model.weixin.Keyword3;
import com.linkwee.web.model.weixin.Keyword4;
import com.linkwee.web.model.weixin.Keyword5;
import com.linkwee.web.model.weixin.Remark;
import com.linkwee.web.model.weixin.SmWeixinmsgTemplate;
import com.linkwee.web.model.weixin.TemplateEntity;
import com.linkwee.web.model.weixin.WeiXinEntity;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.web.service.SmWeixinmsgTemplateService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.HttpClientUtil;
import com.linkwee.xoss.util.RandomTextCreator;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("weiXinMsgService")
public class WeiXinMsgServiceImpl implements WeiXinMsgService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinMsgServiceImpl.class);
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private CrmInvestorService crmInvestorService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private SmWeixinmsgTemplateService smWeixinmsgTemplateService;
	
	@Resource
	private CimProductService cimProductService;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Override
	public void sendWeiXinMsgCommon(WeiXinMsgRequest req) {
		String swicth = sysConfigService.getValuesByKey(req.getUseType()==2?SysConfigConstant.TOOBEI_WEIXIN_SWICTH:SysConfigConstant.LIECAI_WEIXIN_SWICTH);
		if(swicth!=null&&"ON".equals(swicth)){
		    SmWeixinmsgTemplate t = new SmWeixinmsgTemplate();
			t.setTemkey(req.getTemkey());
			t.setAppType(req.getUseType());
			SmWeixinmsgTemplate rt = smWeixinmsgTemplateService.selectOne(t);
			//先判断模板是否存在
			if(req!=null&&req.getUseId()!=null&&req.getTemkey()!=null&&rt!=null){
				String openId = null;
				String mobile = null;
				String ret = null;
				if(req.getUseType()==1){
					CrmCfplanner lcs = crmCfplannerService.queryCfplannerByUserId(req.getUseId());
					openId = lcs!=null?lcs.getWeiXinOpenId():null;
					mobile = lcs!=null?lcs.getMobile():null;
				}else{
					CrmInvestor inv = crmInvestorService.queryInvestorByUserId(req.getUseId());
					openId = inv!=null?inv.getWeiXinOpenId():null;
					
					mobile = inv!=null?inv.getMobile():null;
				}
				String accToken = sysConfigService.getValuesByKey((req.getUseType()==2?SysConfigConstant.TOOBEI_ACCTOKEN:SysConfigConstant.LIECAI_ACCTOKEN).trim());
				String url = sysConfigService.getValuesByKey((req.getUseType()==2?SysConfigConstant.TOOBEI_SEND_MSG_URL:SysConfigConstant.LIECAI_SEND_MSG_URL).trim());
				
				//判断用户的微信openId是否存在
				if(openId!=null&&!"".equals(openId)&&accToken!=null&&url!=null){
					String color = "#173177";
					
					JSONObject jsonParam = new JSONObject();
					jsonParam.put("touser", openId);
			        jsonParam.put("template_id", rt.getTemplateId());
			        jsonParam.put("url", rt.getLinkUrl());
			        jsonParam.put("topcolor", color);
			        TemplateEntity te = new TemplateEntity();
			        First first = new First();
			        first.setValue(rt.getFirst());
			        first.setColor(color);
			        te.setFirst(first);
			        Keyword1 keyword1 =new Keyword1();
			        keyword1.setColor(color);
			        Keyword2 keyword2 =new Keyword2();
			        keyword2.setColor(color);
			        Keyword3 keyword3 =new Keyword3();
			        keyword3.setColor(color);
			        Keyword4 keyword4 =new Keyword4();
			        keyword4.setColor(color);
			        Remark remark = new Remark();
			        if(SysConfigConstant.INVITATION_REGISTER_SUCCESS.equals(req.getTemkey())){//邀请注册成功通知
			        	keyword2.setValue(mobile);//账户
			        	keyword3.setValue(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//注册时间
			        	te.setKeyword3(keyword3);
			        	String crmMobile = req.getRecommendMobile();//推荐人手机号码
			        	remark.setValue(String.format(rt.getRemark(),crmMobile.substring(crmMobile.length()-4,crmMobile.length())));
			        }else if(SysConfigConstant.INVESTMENT_SUCCESS.equals(req.getTemkey())
			        		//||SysConfigConstant.LIECAI_PARENT_INVESTMENT_SUCCESS.equals(req.getTemkey())
			        		//||SysConfigConstant.LIECAI_GRANDPARENT_INVESTMENT_SUCCESS.equals(req.getTemkey())
			        		//||SysConfigConstant.LIECAI_GREAT_GRANDPARENT_INVESTMENT_SUCCESS.equals(req.getTemkey())
			        		||SysConfigConstant.LIECAI_INVESTMENT_SUCCESS.equals(req.getTemkey())){//投资成功通知
			        	keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getProductName());//产品名称
			        	keyword3.setValue(req.getReturnRate());//年化收益
			        	keyword4.setValue(req.getAmount());//投资金额
			        	Keyword5 keyword5 =new Keyword5();
					    keyword5.setColor(color);
			        	keyword5.setValue(req.getPaymentDate());//还款日期
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	te.setKeyword5(keyword5);
			        	first.setValue(String.format(rt.getFirst(), req.getUserName(),req.getFee()));
			        	te.setFirst(first);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.RECOMMEND_SUCCESS.equals(req.getTemkey())){//推荐成功通知  理财师推荐产品/平台
			        	keyword1.setValue(req.getRecommendPerson());//推荐人
			        	keyword2.setValue(req.getRecommendedPerson());//被推荐人
			        	remark.setValue(String.format(rt.getRemark(), req.getProductName()));
			        }
	//		        else if(SysConfigConstant.PAYMENT_REMINDER.equals(req.getTemkey())
	//		        		||SysConfigConstant.SOON_PAYMENT_REMINDER.equals(req.getTemkey())
	//		        		||SysConfigConstant.PAYMENT_REMINDER_ACTIVE.equals(req.getTemkey())){//回款提醒(产品剩余3天到期回款)
	//		        	keyword1.setValue(req.getPaymentNumber());//回款编号
	//		        	keyword2.setValue(req.getPaymentDate());//回款日期
	//		        	keyword3.setValue(req.getCustomer());//客户
	//		        	keyword4.setValue(req.getAmount());//回款额
	//		        	te.setKeyword3(keyword3);
	//		        	te.setKeyword4(keyword4);
	//		        	remark.setValue(String.format(rt.getRemark(), req.getPlatformName(),req.getProductName()));
	//		        }
			        else if(SysConfigConstant.PAYMENT_REMINDER.equals(req.getTemkey())
			        		||SysConfigConstant.LIECAI_PAYMENT_REMINDER.equals(req.getTemkey())
			        		||SysConfigConstant.PAYMENT_REMINDER_ACTIVE.equals(req.getTemkey())){//猎财大师回款提醒
			        	first.setValue(String.format(rt.getFirst(), req.getUserName()));
			        	keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getProductName());//产品名称
			        	keyword3.setValue(req.getPaymentDate());//回款时间
			        	keyword4.setValue(req.getAmount());//回款金额
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	remark.setValue(rt.getRemark());
			        	first.setValue(String.format(rt.getFirst(),req.getUserName()));
				        te.setFirst(first);
			        }else if(SysConfigConstant.WITHDRAWALS_ACCOUNT.equals(req.getTemkey())){//提现到账通知
			        	keyword1.setValue(req.getWithdrawAmount());//提现金额
			        	keyword2.setValue(req.getAccount());//提现账户
			        	keyword3.setValue(req.getWithdrawTime());//提现时间
			        	keyword4.setValue(req.getNoticeTime());//到账时间
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.APPLY_WITHDRAWALS_ACCOUNT.equals(req.getTemkey())){//提现申请通知
			        	keyword1.setValue(req.getNickName());//昵称
			        	keyword2.setValue(req.getWithdrawTime());//提现时间
			        	keyword3.setValue(req.getWithdrawAmount());//金额
			        	keyword4.setValue(req.getWithdrawType());//提现方式
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.BIND_SUCCESS.equals(req.getTemkey())){//绑定成功通知 重新绑定理财师
			        	keyword1.setValue(req.getBindName());//绑定姓名
			        	keyword2.setValue(req.getBindAccount());//绑定账户
			        	keyword3.setValue(req.getBindTime());//绑定时间
			        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.RELEASE_RELATIONSHIP.equals(req.getTemkey())){//绑定成功通知 重新绑定理财师
			        	keyword1.setValue(req.getUserName());//用户名
			        	keyword2.setValue(req.getUserType());//用户类型
			        	keyword3.setValue(req.getReleaseTime());//解绑时间
			        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.OPEN_THIRD_ACCOUNT_SUCCESS.equals(req.getTemkey())){//第三方账户开设成功通知
			        	keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getOpenTime());//开通时间
	//		        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.ARRIVAL_REMINDER_COMMISSION.equals(req.getTemkey())
			        		||SysConfigConstant.ARRIVAL_REMINDER_LEADER.equals(req.getTemkey())
			        		||SysConfigConstant.ARRIVAL_REMINDER_RECOMMEND.equals(req.getTemkey())){//到账提醒-佣金
			        	keyword1.setValue(req.getArrivalAmount());//到账金额
			        	keyword2.setValue(req.getArrivalTime());//到账时间
			        	keyword3.setValue(req.getArrivalDetail());//到账详情
			        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.ACTIVITY_COMPLETE.equals(req.getTemkey())){//活动完成通知:用于活动奖励
			        	keyword1.setValue(req.getActivityName());//活动名称
			        	keyword2.setValue(req.getActivityAmount());//奖励金额
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.GRADE_CHANGE.equals(req.getTemkey())){//等级变更通知
			        	keyword1.setValue(req.getUserName());//用户姓名
			        	keyword2.setValue(req.getChangeType());//变更类型
			        	remark.setValue(rt.getRemark());
			        }else if(SysConfigConstant.AUDIT_NOT_PASSED.equals(req.getTemkey())||
			        		SysConfigConstant.AUDIT_PASSED.equals(req.getTemkey())){//报单审核未通过
			        	keyword1.setValue(req.getReason());//原因or结果
			        	keyword2.setValue(req.getAuditTime());//审核时间
			        	remark.setValue(String.format(rt.getRemark(), req.getProductName()));
			        }else if(SysConfigConstant.LIECAI_PARENT_INVESTMENT_SUCCESS.equals(req.getTemkey())){ //团队一级成员销售成功
		        		keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getProductName());//产品名称
			        	keyword3.setValue(req.getReturnRate());//年化收益
			        	keyword4.setValue(req.getAmount());//投资金额
			        	Keyword5 keyword5 =new Keyword5();
					    keyword5.setColor(color);
			        	keyword5.setValue(req.getPaymentDate());//还款日期
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	te.setKeyword5(keyword5);
		        		
		        		StringBuilder sb = new StringBuilder();
		        		if(req.getDirectManagerAllowance() != null && new BigDecimal(req.getDirectManagerAllowance()).compareTo(BigDecimal.ZERO) == 1){ //直接管理津贴
		        			sb.append("、").append(req.getDirectManagerAllowance()).append("元直接管理津贴");
		        		}
		        		if(req.getTeamManagerAllowance() != null && new BigDecimal(req.getTeamManagerAllowance()).compareTo(BigDecimal.ZERO) == 1){ //团队管理津贴 > 0
		        			sb.append("、").append(req.getTeamManagerAllowance()).append("元团队管理津贴");
		        		}
		        		sb.append("。");
		        		first.setValue(String.format(rt.getFirst(), req.getUserName(),req.getFee()==null?"0.00":req.getFee(),sb.toString()));
		        		te.setFirst(first);
			        	remark.setValue(rt.getRemark());
		        	}else if(SysConfigConstant.LIECAI_GRANDPARENT_INVESTMENT_SUCCESS.equals(req.getTemkey())){ //团队二级成员销售成功
		        		keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getProductName());//产品名称
			        	keyword3.setValue(req.getReturnRate());//年化收益
			        	keyword4.setValue(req.getAmount());//投资金额
			        	Keyword5 keyword5 =new Keyword5();
					    keyword5.setColor(color);
			        	keyword5.setValue(req.getPaymentDate());//还款日期
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	te.setKeyword5(keyword5);
		        		StringBuilder sb = new StringBuilder();
		        		if(req.getTeamManagerAllowance() != null){ //团队管理津贴
		        			sb.append("你将获得").append(req.getTeamManagerAllowance()).append("元团队管理津贴。");
		        		}
		        		first.setValue(String.format(rt.getFirst(), req.getUserName(),sb.toString()));
		        		te.setFirst(first);
			        	remark.setValue(rt.getRemark());
		        	}else if(SysConfigConstant.LIECAI_GREAT_GRANDPARENT_INVESTMENT_SUCCESS.equals(req.getTemkey())){ //团队三级成员销售成功
		        		keyword1.setValue(req.getPlatformName());//平台名称
			        	keyword2.setValue(req.getProductName());//产品名称
			        	keyword3.setValue(req.getReturnRate());//年化收益
			        	keyword4.setValue(req.getAmount());//投资金额
			        	Keyword5 keyword5 =new Keyword5();
					    keyword5.setColor(color);
			        	keyword5.setValue(req.getPaymentDate());//还款日期
			        	te.setKeyword3(keyword3);
			        	te.setKeyword4(keyword4);
			        	te.setKeyword5(keyword5);
		        		StringBuilder sb = new StringBuilder();
		        		if(req.getTeamManagerAllowance() != null){ //团队管理津贴
		        			sb.append("你将获得").append(req.getTeamManagerAllowance()).append("元团队管理津贴。");
		        		}
		        		first.setValue(String.format(rt.getFirst(), req.getUserName(),sb.toString()));
		        		te.setFirst(first);
			        	remark.setValue(rt.getRemark());
		        	}else if(SysConfigConstant.PUSHMESSAGE_LCS_SUBORDINATE_ACCOUNTING_REWARD.equals(req.getTemkey())){
			        	/*
			        	格式：
			        	变更通知
						您的团队成员姓名+186****8888、姓名+186****8888、姓名+186****8888本月职级与您相同或比您高，参与您津贴核算的理财师人数变更如下：
						原有数量：29人
						变更结果：18人
						变更日期：2017年2月10日 15:48
						即日起，将根据剩余团队成员年化业绩继续为您核算leader奖励。
			        	
			        	{{first.DATA}}
						原有数量：{{keyword1.DATA}}
						变更结果：{{keyword2.DATA}}
						变更日期：{{keyword3.DATA}}
						{{remark.DATA}}
			        	*/
			        	//List<String> teams = new ArrayList<String>();
			        	StringBuilder sbr = new StringBuilder();
			        	if(null != req.getTeams() && !req.getTeams().isEmpty()){
			        		boolean flag = false;
			        		for(String team : req.getTeams()){
			        			if(flag){
			        				sbr.append("、");
			        			}else{
			        				flag = true;
			        			}
			        			sbr.append(team);
				        	}
			        	}
			        	
			        	//first.setValue(String.format(rt.getFirst(), req.getUserName()+req.getSubordinateCfplannerMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")));//内容
			        	first.setValue(String.format(rt.getFirst(), sbr.toString()));//内容
			        	keyword1.setValue(req.getOriginalNums());//原有数量
			        	keyword2.setValue(req.getChangeResult());//变更结果
			        	keyword3.setValue(req.getChangeDate());//变更日期
			        	te.setKeyword3(keyword3);
			        	remark.setValue(rt.getRemark());
				        te.setFirst(first);
				        
			        }else{//注册成功通知register_success
			        	keyword1.setValue(mobile);//用户名
			        	keyword2.setValue(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//注册时间
			        	remark.setValue(rt.getRemark());
			        }
			        te.setKeyword1(keyword1);
			        te.setKeyword2(keyword2);
			        remark.setColor(color);
			        te.setRemark(remark);
			        jsonParam.put("data", te);
			        try {
						ret = HttpClientUtil.httpPost(String.format(url, accToken) , jsonParam);
					} catch (Exception e) {
						LOGGER.info("微信推送【{}】失败ret【{}】",rt.getTitle(),ret);
					}
			        WeiXinEntity wx = (WeiXinEntity) JSON.parseObject(ret, WeiXinEntity.class); 
			        if(wx!=null&&!"0".equals(wx.getErrcode())){
			        	String retoken = updateWeiXinAccToken(req.getUseType());
			        	try {
							ret = HttpClientUtil.httpPost(String.format(url, retoken) , jsonParam);
						} catch (Exception e) {
							LOGGER.info("微信二次推送【{}】失败ret【{}】",rt.getTitle(),ret);
						}
			        }
			        LOGGER.info("微信推送【{}】ret【{}】",req.getTemkey(),ret);
				}
				
			}
		}
	}

	@Override
	public String updateWeiXinAccToken(Integer useType) {		
		String appID = sysConfigService.getValuesByKey((useType==2?SysConfigConstant.TOOBEI_APPID:SysConfigConstant.LIECAI_APPID).trim());
		String appSecret = sysConfigService.getValuesByKey((useType==2?SysConfigConstant.TOOBEI_APPSECRET:SysConfigConstant.LIECAI_APPSECRET).trim());
		String url = sysConfigService.getValuesByKey((useType==2?SysConfigConstant.TOOBEI_QUERY_ACCTOKEN_URL:SysConfigConstant.LIECAI_QUERY_ACCTOKEN_URL).trim());
		
		String ret = HttpClientUtil.httpsGet(String.format(url, appID, appSecret));
		LOGGER.info("微信返回数据{}",ret);
		WeiXinEntity wx = (WeiXinEntity) JSON.parseObject(ret, WeiXinEntity.class); 
		if(wx!=null&&wx.getAccess_token()!=null){
			sysConfigService.updateSysConfigByKey(useType==2?SysConfigConstant.TOOBEI_ACCTOKEN:SysConfigConstant.LIECAI_ACCTOKEN, wx.getAccess_token(), new Date());
			LOGGER.info("更新微信ACCTOKEN配置成功!");
			return wx.getAccess_token();
		}
		return null;
	}

	@Override
	public void sendWeiXinMsg(WeiXinMsgRequest req) {
		String swicth = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_WEIXIN_SWICTH);
		
		String accToken = sysConfigService.getValuesByKey(SysConfigConstant.TOOBEI_ACCTOKEN);
		String ret = null; 
		//微信推送消息开关
		if(swicth!=null&&"ON".equals(swicth)){
			//先查出用户的OPENID  "openid":"oKuobw3A4MRt-awOQNR-5uxhp-hc","nickname":"容天"
			String openId = null;
			String mobile = null;
			if("2".equals(req.getUseType())){
				CrmInvestor inv = crmInvestorService.queryInvestorByUserId(req.getUseId());
				openId = inv.getWeiXinOpenId();
				mobile = inv.getMobile();
			}else if("1".equals(req.getUseType())){
				CrmCfplanner lcs = crmCfplannerService.queryCfplannerByUserId(req.getUseId());
				openId = lcs.getWeiXinOpenId();
				mobile = lcs.getMobile();
			}
			//注册成功通知
			JSONObject jsonParam = new JSONObject();
			String templateId = "tV97Gv3Kl_76UK6svvrVFvftCTBw9yaW47PQdAnaNlg";//"f5TObpaUc8E_3ybT5CBQhW34ZtQ-TjdPCazwcfqt6Yk";
			jsonParam.put("touser", openId);//"oKuobw0MXgjUYumbyAjmFcjYYHNQ");
	        jsonParam.put("template_id", templateId);
	        jsonParam.put("url", "https://www.toobei.com/app/pages/user/login.html");
	        jsonParam.put("topcolor", "#173177");
	        TemplateEntity t = new TemplateEntity();
	        First first = new First();
	        first.setValue("您已成功注册投呗平台！");
	        first.setColor("#173177");
	        t.setFirst(first);
	        Keyword1 keyword1 =new Keyword1();
	        keyword1.setValue(mobile);
	        keyword1.setColor("#173177");
	        Keyword2 keyword2 =new Keyword2();
	        keyword2.setValue(new Date().toString());
	        keyword2.setColor("#173177");
	        t.setKeyword1(keyword1);
	        t.setKeyword2(keyword2);
	        Remark remark = new Remark();
	        remark.setValue("欢迎加入T呗！为给您提供最优质的服务，我们已为您匹配专属理财师，投资问题随时互动，轻松开启专业理财！   ");
	        remark.setColor("#173177");
//	        t.setName(name);
	        t.setRemark(remark);
	        jsonParam.put("data", t);
	        try {
				ret = HttpClientUtil.httpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ accToken, jsonParam);
			} catch (Exception e) {
				LOGGER.info("微信推送【注册成功通知】失败");
				e.printStackTrace();
			}
		}else{
			SmWeixinmsgTemplate t = new SmWeixinmsgTemplate();
			t.setTemkey("register_success");
			SmWeixinmsgTemplate rt = smWeixinmsgTemplateService.selectOne(t);
			System.out.println(rt);
			
		}
		System.out.println(ret);
	}

	@Override
	public void sendWeiXinMsgListCommon(List<WeiXinMsgRequest> reqList) {
		if(CollectionUtils.isNotEmpty(reqList)){			
			for(WeiXinMsgRequest req: reqList){
				sendWeiXinMsgCommon(req);
			}
		}	
	}
	
	@Override
	public void investRecordProcess(InvestRecordWrapper inRecord) throws Exception {
		//产品购买成功之后推送微信消息给用户 
		if(inRecord!=null){
			WeiXinMsgRequest weixinreq = new WeiXinMsgRequest();
			weixinreq.setUseId(inRecord.getUserId());
			weixinreq.setTemkey(SysConfigConstant.INVESTMENT_SUCCESS);//投资成功通知
			OrgInfo org = cimOrginfoService.findOrgInfo(inRecord.getProductOrgId());
			String platformName = org!=null?org.getOrgName():"";
			weixinreq.setPlatformName(platformName);//平台名称
			weixinreq.setProductName(inRecord.getProductName());
			ProductDetailResponse detail = cimProductService.queryProductDetail(inRecord.getProductId());
			if(detail!=null){
				if(detail.getIsFlow()==1){
					weixinreq.setReturnRate(detail.getFlowMinRate().toString()+"%");
				}else{
					weixinreq.setReturnRate(detail.getFlowMinRate().toString()+"~"+detail.getFlowMaxRate()+"%");
				}
				
			}
			weixinreq.setPaymentDate(DateUtils.format(inRecord.getEndTime(), DateUtils.FORMAT_SHORT));//还款日期fee.getEndTime()
			weixinreq.setAmount(NumberUtils.getFormat(inRecord.getInvestAmt(), "0.00")+"元");
			weixinreq.setUseType(2);
			sendWeiXinMsgCommon(weixinreq);
		}
	}

	@Override
	public void investSuccesssendWeiXinMsg(final List<FeedetailWrapper> feedetails,final InvestRecordWrapper inRecord) {
		//异步发微信消息
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
				for(FeedetailWrapper fee:feedetails){
					//feetype;// 佣金类型。《 1001：基础佣金, 1002： 推荐奖励,  1005： 直接管理津贴,  1006：团队管理津贴》
					if(null == fee || !fee.getFeetype().equals("1001")){
						return;
					}
					if(fee.getFeeamount().compareTo(BigDecimal.ZERO)==1&&fee.getProfitCfplannerId()!=null){//大于0才推送消息
						WeiXinMsgRequest wx = new WeiXinMsgRequest();
						/*	if(BigDecimal.ZERO.equals(fee.getFeeamount())) continue;
						if(fee.getCurCfplannerId().equals(fee.getCfplannerId())){
							wx.setTemkey(SysConfigConstant.LIECAI_INVESTMENT_SUCCESS);//当前理财师
							wx.setUseId(fee.getCfplannerId());
						}else if(fee.getCurCfplannerId().equals(fee.getParentCfplannerId())){
							wx.setTemkey(SysConfigConstant.LIECAI_PARENT_INVESTMENT_SUCCESS);//上级理财师
							wx.setUseId(fee.getParentCfplannerId());
						}else if(fee.getCurCfplannerId().equals(fee.getGrandParentCfplannerId())){
							wx.setTemkey(SysConfigConstant.LIECAI_GRANDPARENT_INVESTMENT_SUCCESS);//上级理财师
							wx.setUseId(fee.getGrandParentCfplannerId());
						}*/
						wx.setTemkey(SysConfigConstant.LIECAI_INVESTMENT_SUCCESS);//投资成功通知
						wx.setUseId(fee.getProfitCfplannerId());
						OrgInfo org = cimOrginfoService.findOrgInfo(fee.getProductOrgId());
						String platformName = org!=null?org.getOrgName():"";
						wx.setPlatformName(platformName);//平台名称
						wx.setProductName(fee.getProductName());//产品名称
						ProductDetailResponse detail = cimProductService.queryProductDetail(fee.getProductId());
						String returnRate = null;//年化收益
						if(detail!=null){
							if(detail.getIsFlow()==1){
								returnRate = detail.getFlowMinRate().toString()+"%";
							}else{
								returnRate = detail.getFlowMinRate().toString()+"~"+detail.getFlowMaxRate()+"%";
							}
							
						}
						wx.setReturnRate(returnRate);//年化收益
						wx.setAmount(NumberUtils.getFormat(fee.getInvestmentAmount(), "0.00")+"元");//投资金额
						wx.setPaymentDate(DateUtils.format(inRecord.getEndTime(), DateUtils.FORMAT_SHORT));//还款日期fee.getEndTime()
						wx.setFee(NumberUtils.getFormat(fee.getFeeamount(), "0.00")); //1001
						CrmUserInfo crm = crmUserInfoService.queryUserInfoByUserId(fee.getInvestorId());
						wx.setUserName(crm!=null?crm.getUserName()+RandomTextCreator.encrypTion(crm.getMobile()):"");
						wx.setUseType(1);
						wxList.add(wx);
					}
				}
				sendWeiXinMsgListCommon(wxList);
			}
		});
		
	}

	@Override
	public void teamMemberSellSuccessWeiXinMsg(final List<FeedetailWrapper> feedetails, final InvestRecordWrapper investRecord) {
		//异步发微信消息
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				Map<String, FeedetailWrapper> maps = Maps.newHashMap();
				final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
				for(FeedetailWrapper fee:feedetails){
					//feetype 佣金类型。《 1001：基础佣金, 1002： 推荐奖励,  1005： 直接管理津贴,  1006：团队管理津贴》
					//lowType 下级类型(0=默认|1=下级2=下下级)
					String  key = StringUtils.join(new Object[]{fee.getFeetype(),fee.getLowType()}); //生成key
					maps.put(key, fee);
				}
				
				FeedetailWrapper feedetailWrapper_10060 = maps.get("10060"); // 一级团队成员津贴
				FeedetailWrapper feedetailWrapper_1002 = maps.get("1002"); //推荐奖励
				//for(Map.Entry<String, FeedetailWrapper> entry : maps.entrySet())
				if(feedetailWrapper_10060 == null) {
					//feedetailWrapper_1002.getFeeamount().compareTo(BigDecimal.ZERO) == 1
					if(feedetailWrapper_1002 != null){//推荐奖励>0推送(返回值：0=等于、-1小于、1=大于)
						WeiXinMsgRequest wx = handleTeamWeiXinMsgRequest(investRecord, feedetailWrapper_1002);
						wx.setTemkey(SysConfigConstant.LIECAI_PARENT_INVESTMENT_SUCCESS);//当前理财师
						wx.setUseId(feedetailWrapper_1002.getProfitCfplannerId()); //微信推送接受方
						wx.setFee(NumberUtils.getFormat(feedetailWrapper_1002.getFeeamount(), "0.00"));
						wx.setUseType(1);
						wxList.add(wx);
					}
				}else{
					WeiXinMsgRequest wx = handleTeamWeiXinMsgRequest(investRecord, feedetailWrapper_10060);
			    	wx.setTemkey(SysConfigConstant.LIECAI_PARENT_INVESTMENT_SUCCESS);//当前理财师
					wx.setUseId(feedetailWrapper_10060.getProfitCfplannerId()); //微信推送接受方
					if(feedetailWrapper_1002 != null){
						wx.setFee(NumberUtils.getFormat(feedetailWrapper_1002.getFeeamount(), "0.00")); 
					}
					FeedetailWrapper feedetailWrapper_1005 = maps.get("1005");//直接管理津贴
					if(feedetailWrapper_1005 != null){
						wx.setDirectManagerAllowance(NumberUtils.getFormat(feedetailWrapper_1005.getFeeamount(), "0.00"));
					}
					
					wx.setTeamManagerAllowance(NumberUtils.getFormat(feedetailWrapper_10060.getFeeamount(), "0.00")); //团队管理津贴
					wx.setUseType(1);
					wxList.add(wx);
				}
				/*
				if(feedetailWrapper_10060 != null) {
				    	WeiXinMsgRequest wx = handleTeamWeiXinMsgRequest(investRecord, feedetailWrapper_10060);
				    	wx.setTemkey(SysConfigConstant.LIECAI_PARENT_INVESTMENT_SUCCESS);//当前理财师
						wx.setUseId(feedetailWrapper_10060.getProfitCfplannerId()); //微信推送接受方
						FeedetailWrapper feedetailWrapper_1002 = maps.get("1002"); //推荐奖励
						if(feedetailWrapper_1002 != null){
							wx.setFee(NumberUtils.getFormat(feedetailWrapper_1002.getFeeamount(), "0.00")); 
						}
						FeedetailWrapper feedetailWrapper_1005 = maps.get("1005");//直接管理津贴
						if(feedetailWrapper_1005 != null){
							wx.setDirectManagerAllowance(NumberUtils.getFormat(feedetailWrapper_1005.getFeeamount(), "0.00"));
						}
						
						wx.setTeamManagerAllowance(NumberUtils.getFormat(feedetailWrapper_10060.getFeeamount(), "0.00")); //团队管理津贴
						wxList.add(wx);
					 }
				*/
				FeedetailWrapper feedetailWrapper_10061 = maps.get("10061");//二级团队成员
				
				if(feedetailWrapper_10061 != null){
					WeiXinMsgRequest wx = handleTeamWeiXinMsgRequest(investRecord, feedetailWrapper_10061);
					wx.setTemkey(SysConfigConstant.LIECAI_GRANDPARENT_INVESTMENT_SUCCESS);
					wx.setUseId(feedetailWrapper_10061.getProfitCfplannerId());
					wx.setTeamManagerAllowance(NumberUtils.getFormat(feedetailWrapper_10061.getFeeamount(), "0.00")); //团队管理津贴
					wx.setUseType(1);
					wxList.add(wx);
				}
				
				FeedetailWrapper feedetailWrapper_10062 = maps.get("10062"); //三级团队成员
				if(feedetailWrapper_10062 != null){
					WeiXinMsgRequest wx = handleTeamWeiXinMsgRequest(investRecord, feedetailWrapper_10062);
					wx.setTemkey(SysConfigConstant.LIECAI_GREAT_GRANDPARENT_INVESTMENT_SUCCESS);
					wx.setUseId(feedetailWrapper_10062.getProfitCfplannerId());
					wx.setTeamManagerAllowance(NumberUtils.getFormat(feedetailWrapper_10062.getFeeamount(), "0.00")); //团队管理津贴
					wx.setUseType(1);
					wxList.add(wx);
				}
				
				sendWeiXinMsgListCommon(wxList); //推送微信消息
			}

		});
	}
	
	/**
	 * 封装WeiXinMsgRequest
	 * @author yalin 
	 * @date 2017年4月11日 下午4:13:45  
	 * @param investRecord
	 * @param feedetail
	 * @return
	 */
	private WeiXinMsgRequest handleTeamWeiXinMsgRequest(InvestRecordWrapper investRecord,FeedetailWrapper feedetail) {
		//推送微信消息
		WeiXinMsgRequest wx = new WeiXinMsgRequest();
		OrgInfo org = cimOrginfoService.findOrgInfo(feedetail.getProductOrgId());
		String platformName = org!=null?org.getOrgName():"";
		wx.setPlatformName(platformName);//平台名称
		wx.setProductName(feedetail.getProductName());//产品名称
		ProductDetailResponse detail = cimProductService.queryProductDetail(feedetail.getProductId());
		String returnRate = null;//年化收益
		if(detail!=null){
			if(detail.getIsFlow()==1){ //固定利率
				returnRate = detail.getFlowMinRate().toString()+"%";
			}else{ //浮动利率
				returnRate = detail.getFlowMinRate().toString()+"~"+detail.getFlowMaxRate()+"%";
			}
		}
		wx.setReturnRate(returnRate);//年化收益
		wx.setAmount(NumberUtils.getFormat(feedetail.getInvestmentAmount(), "0.00")+"元");//投资金额
		wx.setPaymentDate(DateUtils.format(investRecord.getEndTime(), DateUtils.FORMAT_SHORT));//还款日期fee.getEndTime()
		CrmUserInfo crm = crmUserInfoService.queryUserInfoByUserId(feedetail.getInvestorId());
		wx.setUserName(crm!=null?crm.getUserName()+RandomTextCreator.encrypTion(crm.getMobile()):""); //用户名+手机号
		wx.setUseType(1);
		return wx;
	}

	@Override
	public void pushWeiXinMsg(List<FeedetailWrapper> feedetailWrappers,InvestRecordWrapper investRecord) {
		//异步发微信消息
		/*
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				teamMemberSellSuccessWeiXinMsg(feedetailWrappers,investRecord);//一、二、三级团队成员微信消息推送
				investSuccesssendWeiXinMsg(feedetailWrappers,investRecord); //微信客户投资成功通知
			}
		});
		*/
		teamMemberSellSuccessWeiXinMsg(feedetailWrappers,investRecord);//一、二、三级团队成员微信消息推送
		investSuccesssendWeiXinMsg(feedetailWrappers,investRecord); //微信客户投资成功通知
	}
	
	
}
