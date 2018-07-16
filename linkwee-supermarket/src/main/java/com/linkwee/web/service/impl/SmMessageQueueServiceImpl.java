package com.linkwee.web.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.SmMessageQueueMapper;
import com.linkwee.web.dao.SmMessageTemplateMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MessageSendErrorEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsChannelTypeEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.model.SmMessageTemplate;
import com.linkwee.web.model.SysConfig;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.util.HttpClientUtil;
import com.linkwee.xoss.util.InterceptUtility;
import com.linkwee.xoss.util.MessageSendUtil;


 /**
 * 
 * @描述：SmMessageTemplateService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月18日 11:33:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smMessageTemplateService")
public class SmMessageQueueServiceImpl extends GenericServiceImpl<SmMessageQueue, Long> implements SmMessageQueueService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmMessageQueueServiceImpl.class);
	
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //配置您申请的KEY
    public static final String APPKEY ="*************************";
	@Resource
	private SmMessageQueueMapper smMessageQueueMapper;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private SmMessageTemplateMapper smMessageTemplateMapper;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private CrmUserInfoService userInfoService;
	
	@Override
    public GenericDao<SmMessageQueue, Long> getDao() {
        return smMessageQueueMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmMessageTemplate -- 排序和模糊查询 ");
		Page<SmMessageQueue> page = new Page<SmMessageQueue>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmMessageQueue> list = this.smMessageQueueMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		return tableReturn;
	}

	
	/**
	 * 查询系统短信发送设置
	 * @return
	 */
	public boolean querySendMsgSet() {
		boolean isNeedSendMsg = true;
		String vcodeSet = sysConfigService.getValuesByKey(SysConfigConstant.KEY_NO_SEND_VCODE);
		if(vcodeSet!=null && "1".equals(vcodeSet)){//设置为不发送短信消息
			isNeedSendMsg = false;
		}
		return isNeedSendMsg;
	}
	
	/**
	 * 发送验证码-预发布不返回错误信息给前端
	 * @return
	 */
	@Override
	public boolean isPreReturnMsg() {
		boolean isPreReturnMsg = true;
		String vcodeSet = sysConfigService.getValuesByKey(SysConfigConstant.PRE_NO_RETURN_MSG);
		if(vcodeSet!=null && "1".equals(vcodeSet)){
			isPreReturnMsg = false;
		}
		return isPreReturnMsg;
	}

	@Override
	/**
	 * 同内容群发
	 * 如果moduleId 为空将按照传入的 content发送，不为空根据配置的模板发送
	 */
	public CommonTCSResult batchSendMessage(Collection<String> mobiles, AppTypeEnum appType,MsgModuleEnum moduleId,Object...params) {
		//List<SmMessageQueue> sendTmpList = new ArrayList<SmMessageQueue>(); 不记录短信发送日记
		CommonTCSResult retCode = new CommonTCSResult();
		boolean isNeedSendMsg = querySendMsgSet();
		//mobiles 分批处理		
		List<List<String>> mobiless =  Lists.newArrayList();
		InterceptUtility.subsection(Lists.newArrayList(mobiles) , mobiless,100);
		
		String content = "";
		String tmpId = null;
		String juheContent = null;
		if(isNeedSendMsg){
			//Date startSend = new Date();
			if(moduleId!=null){
				SmMessageTemplate condit = new SmMessageTemplate();
				condit.setModuleId(moduleId.getValue());
				condit.setAppType(appType.getKey());
				SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
				content = String.format(tmp.getContent(),params);
				tmpId = tmp.getJuheTplId();
				juheContent = String.format(tmp.getJuheKey(), params);
			}
			Map<String,String> HttpParams = fillAccInfo(appType);
			for (final List<String> list : mobiless) {
				
				String channel = useMessageChannel();
				String code = null;
				if("0".equals(channel)){//梦网
					code =   sendSameContentMessage(list, content,HttpParams);
				}else if(tmpId!=null&&"1".equals(channel)){//聚合
					try {
						Iterator<String> it = list.iterator();
						  while(it.hasNext()){
							  code = sendSingleMessageJuHe(tmpId,it.next(), juheContent);
						  }
					} catch (UnsupportedEncodingException e) {
						LOGGER.info("聚合短信batchSendMessage异常!");
					}
				}
				
				//int sendStatus = 0;
				if(code != null){
					if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
						//sendStatus = 2;
						retCode = new CommonTCSResult(0,"发送成功");
					}else{
						//sendStatus = 3;
						retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					}
				}
				/*for(String str:list){
					SmMessageQueue model = new SmMessageQueue();
					model.setType(2);//个人消息
					model.setAppType(appType.getKey());
					model.setSendTo(str);
					model.setContent(content);
					model.setCreateTime(startSend);
					model.setStatus(sendStatus);
					model.setSendTime(new Date());
					model.setMsgType((byte)moduleId.getKey());
					sendTmpList.add(model);
				}*/
				LOGGER.info("SmMessageQueueService发送短信执行：code={},message={},mobiles={}",new Object[]{retCode.getCode(),retCode.getMessage(),list});
			}
			
			//smMessageQueueMapper.insertBatch(sendTmpList);
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		return retCode;
		
	}

	@Override
	public List<String> queryWaitingMsgTmp(SmMessageQueue condition) {
		List<SmMessageQueue> smMlist = smMessageQueueMapper.selectByCondition(condition);
		List<String> mobiles = new ArrayList<String>();
		for(SmMessageQueue item:smMlist){
			mobiles.add(item.getSendTo());
		}
		return mobiles;
	}
	
	@Override
	public boolean checkVerificationCode(String mobile,MsgModuleEnum moduleId,String inputCode) throws Exception{
		if(StringUtils.isBlank(mobile)){
			throw new Exception("手机号不能为空");
		}
		if(moduleId == null){
			throw new Exception("模块编码不能为空");
		}
		if(StringUtils.isBlank(inputCode)){
			throw new Exception("验证码不能为空");
		}
		boolean ret = false;
		SmMessageQueue t = new SmMessageQueue();
		t.setStatus(2);//发送成功
		t.setModuleId(moduleId.getValue());
		t.setSendTo(mobile);
		SmMessageQueue retMsg = smMessageQueueMapper.checkVcode(t);
		if(inputCode.equals(retMsg.getContent())){
			ret = true;
		}
		return ret;
	}
	/**
	 * 不同内容群发
	 * 群发的必须是同一渠道和端口
	 * @param messages
	 * @return
	 */
	public CommonTCSResult sendDiffContentMessage(List<SmMessageQueue> messages){
		CommonTCSResult retCode =null;
		boolean isNeedSendMsg = querySendMsgSet();
		if(isNeedSendMsg){
			if(messages!=null && messages.size()>100){//同内容批量 一个包一次不能超过100个手机号
				return new CommonTCSResult(MessageSendErrorEnum.MOBILECOUNTOVERFLOW.getKey(),MessageSendErrorEnum.MOBILECOUNTOVERFLOW.getValue());
			}
			Map<String,SmMessageTemplate> mstTepMap = getMsgTepMap();
			SmMessageQueue smMessageQueue = messages.get(0);
			 Map<String, String> params = fillAccInfo(AppTypeEnum.valueOf(String.valueOf(smMessageQueue.getAppType())));
			  StringBuffer multixmt = new StringBuffer();
			  for(int i=0;i<messages.size();i++){
				  multixmt.append("0|");//无流水号
				  multixmt.append("*|");//无通道号
				  multixmt.append(messages.get(i).getSendTo()).append("|");//手机号
				  /**
				   * 根据配置的消息模板发送内容
				   */
				  if(StringUtils.isNotBlank(messages.get(i).getModuleId())){
					  SmMessageTemplate tmp =  mstTepMap.get(messages.get(i).getAppType()+messages.get(i).getModuleId());
					  multixmt.append(String.format(tmp.getContent(), messages.get(i).getContent()));
				  }else{
					  multixmt.append(messages.get(i).getContent());
				  }
				  
				  if(i < messages.size() -1){
					  multixmt.append(",");
				  }
				  
			  }
			  params.put("multixmt", multixmt.toString());
			 
			 /* 0|*|13800138000|xOO6wyy7ttOtyrnTwyE= */
			  try {
				  String url = findMessageServerUrl();
				  String method = "MongateMULTIXSend";
				  String retMsg = HttpClientUtil.httpPost(url+method, params);
				  String code = MessageSendUtil.RegexString(retMsg);
				  int sendStatus = 0;
				  if(code != null){
						if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
							sendStatus = 2;//成功
							retCode = new CommonTCSResult(0,"发送成功");
						}else{
							sendStatus = 3;//失败
							retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
						}
					}
					LOGGER.debug("SmMessageQueueService发送短信执行：code={},message={}",retCode.getCode(),retCode.getMessage());
					//更新状态
					 for(SmMessageQueue item :messages){
						 item.setStatus(sendStatus);
					  }
					smMessageQueueMapper.updateBathch(messages);
				  return retCode;
			} catch (Exception e) {
				return null;
			}
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		return retCode;
	}

	public Map<String,SmMessageTemplate> getMsgTepMap() {
		Map<String,SmMessageTemplate> msgTepMap = new HashMap<String,SmMessageTemplate>();
		//加载短信模板信息
		SmMessageTemplate t = new SmMessageTemplate();
		t.setStatus(1);//没停用的模板
		List<SmMessageTemplate> tmpList = smMessageTemplateMapper.selectByCondition(t);
		for(SmMessageTemplate item:tmpList){
			msgTepMap.put(item.getAppType()+item.getModuleId(),item);
		}
		return msgTepMap;
	}
	
	private String sendSingleMessage(String mobile,String content,AppTypeEnum appType){
		 Map<String, String> params = fillAccInfo(appType);
		  params.put("pszMobis", mobile);
		  params.put("pszMsg", content);
		  params.put("iMobiCount", "1");
		  params.put("pszSubPort", "*");
		  params.put("MsgId", "0");
		  
		  try {
			  String url = findMessageServerUrl();
			  String method = "MongateSendSubmit";
			  String retMsg = HttpClientUtil.httpPost(url+method, params);
			  LOGGER.info("============================>>>>梦网发送短信<<<<<===============================retMsg==="+retMsg);
			  return MessageSendUtil.RegexString(retMsg);
		} catch (Exception e) {
			return null;
		}
	}
	
	//2.聚合发送短信
    public String sendSingleMessageJuHe(String tplId,String mobile,String content) throws UnsupportedEncodingException{
        String result =null;
        String url =findJuHeSendUrl();//请求接口地址
//        Map params = new HashMap();//请求参数
        Map<String, String> params = new HashMap<String,String>();
        params.put("mobile",mobile);//接收短信的手机号码
        params.put("tpl_id",tplId);//短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_value",content);//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        params.put("key",findJuHeAppKey());//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        String sign = null;
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            Map<String, String> resultMap = new HashMap<String, String>();
    		Set<String> it = object.keySet();  
            // 遍历jsonObject数据，添加到Map对象  
    		for (String key:it){  
    			 String value = String.valueOf(object.get(key));  
                 resultMap.put(key, value); 
            }  
            sign = resultMap.get("error_code");
            LOGGER.info("============================>>>>聚合发送短信<<<<<===============================code==="+sign);
            if(sign!=null&&"0".equals(sign)){
            	LOGGER.debug(object.get("result").toString());
            }else{
            	LOGGER.debug(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return sign;
    }
    
    /**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符串
    * @throws Exception
    */
   public static String net(String strUrl, Map<String, String> params,String method) throws Exception {
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method==null || method.equals("GET")){
               strUrl = strUrl+"?"+urlencode(params);
           }
           URL url = new URL(strUrl);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.setRequestProperty("User-agent", userAgent);
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                       out.writeBytes(urlencode(params));
               } catch (Exception e) {
            	   LOGGER.debug("聚合接口net异常!");
               }
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               reader.close();
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }
   
    //将map型转为请求参数型
	public static String urlencode(Map<String,String> data) {
	       StringBuilder sb = new StringBuilder();
	       for (Map.Entry<String, String> i : data.entrySet()) {
	           try {
	               sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
	           } catch (UnsupportedEncodingException e) {
	               e.printStackTrace();
	           }
	       }
	       return sb.toString();
	   }
	
	private String sendSameContentMessage(Collection<String> mobiles,String content,Map<String, String> params){
		/*if(mobiles!=null && mobiles.size()>100){//同内容批量 一个包一次不能超过100个手机号
			return String.valueOf(MessageSendErrorEnum.MOBILECOUNTOVERFLOW.getKey());
		}*/
		 //Map<String, String> params = fillAccInfo(appType);
		  StringBuffer pszMobis = new StringBuffer();
		  Iterator<String> it = mobiles.iterator();
		  while(it.hasNext()){
			  pszMobis.append(it.next()).append(",");
			  
		  }
		  if(pszMobis.toString().endsWith(",")){	
			  params.put("pszMobis", pszMobis.toString().substring(0, pszMobis.toString().length() - 1));
	      }else{
	    	  params.put("pszMobis", pszMobis.toString());
	      }
		  params.put("pszMsg", content);
		  params.put("iMobiCount", String.valueOf(mobiles.size()));
		  params.put("pszSubPort", "*");
		  params.put("MsgId", "0");
		  
		  try {
			  String url = findMessageServerUrl();
			  String method = "MongateSendSubmit";
			  String retMsg = HttpClientUtil.httpPost(url+method, params);
			  return MessageSendUtil.RegexString(retMsg);
		} catch (Exception e) {
			return null;
		}
		  
	}
	

	public String findMessageServerUrl() {
		String url = sysConfigService.getValuesByKey("mw_send_message_url", 0);
		return url;
	}
	
	public String findJuHeAppKey() {
		String appyKey = sysConfigService.getValuesByKey("juhe_send_message_key", 1);
		return appyKey;
	}
	
	public String findJuHeSendUrl() {
		String appyKey = sysConfigService.getValuesByKey("juhe_send_message_url", 1);
		return appyKey;
	}
	
	public String useMessageChannel() {//使用那个短信通道 0是梦网  1是聚合
		String channel = sysConfigService.getValuesByKey("change_message_channel", 1);
		return channel;
	}

	public Map<String, String> fillAccInfo(AppTypeEnum appType) {
		Map<String ,String> params = new HashMap<String,String>();
		
		SysConfig condit = new SysConfig();
		condit.setConfigName("梦网短信");
		condit.setAppType(appType.getKey());
		
		List<SysConfig> syslist = sysConfigService.queryfuzzily(condit);
		
		for(SysConfig item:syslist){
			
			if(item.getConfigKey().endsWith("userId") ){
				params.put("userId", item.getConfigValue());// 账户信息设置
			}
			if(item.getConfigKey().endsWith("pwd")){
				params.put("password", item.getConfigValue());// 账户信息设置
			}
			
		}
		return params;
	}

	@Override
	public CommonTCSResult sendSingleMessage(String mobile,	AppTypeEnum appType, MsgModuleEnum moduleId,Object... contentArgs){
		CommonTCSResult retCode = new CommonTCSResult();
		if(StringUtils.isBlank(mobile)){
			retCode = new CommonTCSResult(-1,"手机号不能为空");
		}
		if(appType ==null){
			retCode = new CommonTCSResult(-1,"appType不能为空");
		}
		if(moduleId ==null){
			retCode = new CommonTCSResult(-1,"moduleId不能为空");
		}
		
		boolean isNeedSendMsg = querySendMsgSet();
		String content = "";
		if(isNeedSendMsg){
			/*SmMessageQueue model = new SmMessageQueue();
			model.setType(2);//个人消息
			model.setAppType(appType.getKey());
			model.setSendTo(mobile);	
			model.setMsgType((byte)1);*/
			String tmpId = null;
			String juheContent = null;
			
			if(moduleId!=null){
				SmMessageTemplate condit = new SmMessageTemplate();
				condit.setModuleId(moduleId.getValue());
				condit.setAppType(appType.getKey());
				SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
				if(tmp != null){
					content = String.format(tmp.getContent(), contentArgs);
					tmpId = tmp.getJuheTplId();
					juheContent = String.format(tmp.getJuheKey(), contentArgs);
				}
				//model.setContent(content);
			}
			
			//model.setCreateTime(new Date());\
			String code = null;
			String channel = useMessageChannel();
			if("0".equals(channel)){//梦网
				code = sendSingleMessage(mobile, content,appType);
			}else if(tmpId!=null&&"1".equals(channel)){//聚合
				try {
					code = sendSingleMessageJuHe(tmpId,mobile, juheContent);
				} catch (UnsupportedEncodingException e) {
					LOGGER.info("聚合短信UnsupportedEncodingException异常!");
				}
			}
			
			if(code != null){
				if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
					//model.setStatus(2);//成功
					retCode = new CommonTCSResult(0,"发送成功");
					LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}else{
					//model.setStatus(3);//失败
					retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					LOGGER.error("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}
			}
			
			//model.setSendTime(new Date());
			//smMessageQueueMapper.insertSelective(model);
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
			LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
		}		
		return retCode;
	}
	@Override
	public CommonTCSResult sendMessageAndSysMsg(String mobile,String userId,AppTypeEnum appType, MsgModuleEnum moduleId,boolean needSendMsg,Object... contentArgs){
		CommonTCSResult retCode = new CommonTCSResult();
		if(mobile == null && userId == null){
			return  new CommonTCSResult(-1,"mobile 和 userId 不能同时为空！");
		}
		if(appType ==null){
			return new CommonTCSResult(-1,"appType不能为空");
		}
		if(moduleId ==null){
			return new CommonTCSResult(-1,"moduleId不能为空");
		}
		
		
		String content = "";
		String tmpId = "";
		String juheContent = "";
		if(moduleId!=null){
			SmMessageTemplate condit = new SmMessageTemplate();
			condit.setModuleId(moduleId.getValue());
			condit.setAppType(appType.getKey());
			SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
			tmpId = tmp.getJuheTplId();
			juheContent = String.format(tmp.getJuheKey(), contentArgs);
			if(tmp != null){
				content = String.format(tmp.getContent(), contentArgs);
			}
		}
		if(needSendMsg){
			CrmUserInfo userInfo = null;
			if(mobile!=null){
				userInfo = userInfoService.selectCrmUserInfoByMobile(mobile);
				userId = userInfo.getUserId();
			}else{
				userInfo = userInfoService.queryUserInfoByUserId(userId);
				mobile = userInfo.getMobile();
			}
			if(userInfo!=null) {
				SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(userInfo.getUserId());
				msg.setReadStatus(0);// 未读
				msg.setAppType(appType.getKey());
				sysMsgService.addMsg(msg);
			}
		}
		boolean isNeedSendMsg = querySendMsgSet();		
		if(isNeedSendMsg){
			String channel = useMessageChannel();
			String code = null;
			if("0".equals(channel)){//梦网
				code = sendSingleMessage(mobile, content,appType);			
			}else if("1".equals(channel)){
				try {
					code = sendSingleMessageJuHe(tmpId,mobile, juheContent);
				} catch (UnsupportedEncodingException e) {
					LOGGER.info("聚合短信sendMessageAndSysMsg异常");
				}
			}
			if(code != null){
				if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
					retCode = new CommonTCSResult(0,"发送成功");
					LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}else{
					retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					LOGGER.error("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
				}
			}
			
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
			LOGGER.info("SmMessageQueueService发送短信执行：mobile={},content={},appType={},moduleId={},code={},message={}",new Object[]{mobile, content,appType,moduleId,retCode.getCode(),retCode.getMessage()});
		}		
		return retCode;
	}
	
	@Override
	public CommonTCSResult sendSingleMessage(String mobile,	AppTypeEnum appType, String content, SmsChannelTypeEnum channelType)
			throws Exception {
		if(StringUtils.isBlank(mobile)){
			throw new Exception("手机号不能为空");
		}
		if(appType ==null){
			throw new Exception("appType不能为空");
		}
		if(content ==null){
			throw new Exception("content不能为空");
		}
		CommonTCSResult retCode = new CommonTCSResult();
		boolean isNeedSendMsg = querySendMsgSet();
		if(isNeedSendMsg){
			SmMessageQueue model = new SmMessageQueue();
			//content = new String(content.getBytes(),"utf-8");
			model.setType(2);//个人消息
			model.setAppType(appType.getKey());
			model.setSendTo(mobile);	
			
			model.setCreateTime(new Date());
			LOGGER.debug("SmMessageQueueService发送短信输入：mobile={},content={},appType={},channelType={}", new Object[]{mobile, content,appType,channelType});
			String code = sendSingleMessage(mobile, content,appType);
			
			if(code != null){
				if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
					model.setStatus(2);//成功
					retCode = new CommonTCSResult(0,"发送成功");
				}else{
					model.setStatus(3);//失败
					retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
				}
			}
			LOGGER.debug("SmMessageQueueService发送短信执行：code={},message={}",retCode.getCode(),retCode.getMessage() );
			model.setSendTime(new Date());
			smMessageQueueMapper.insertSelective(model);
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		return retCode;
	}

	@Override
	public CommonTCSResult batchSendSmMessageAndPersonalMsg(
			Collection<String> mobiles, AppTypeEnum appType,
			MsgModuleEnum moduleId, boolean isNeedSendPeronalMsg,
			Collection<String> userIds,PersonalMsgTypeEnum msgType, Object... params) {
		List<SmMessageQueue> sendTmpList = new ArrayList<SmMessageQueue>();
		CommonTCSResult retCode = new CommonTCSResult();
		boolean isNeedSendMsg = querySendMsgSet();
		//mobiles 分批处理		
		List<List<String>> mobiless =  Lists.newArrayList();
		InterceptUtility.subsection(Lists.newArrayList(mobiles) , mobiless,100);
		
		String content = "";
		if(moduleId!=null){
			SmMessageTemplate condit = new SmMessageTemplate();
			condit.setModuleId(moduleId.getValue());
			condit.setAppType(appType.getKey());
			SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
			content = String.format(tmp.getContent(),params);
		}
		if(isNeedSendMsg){
			Date startSend = new Date();
			
			Map<String,String> HttpParams = fillAccInfo(appType);
			for (final List<String> list : mobiless) {
				String code =   sendSameContentMessage(list, content,HttpParams);
				int sendStatus = 0;
				if(code != null){
					if(MessageSendUtil.isSuccessReturn(Long.valueOf(code))){//发送成功
						sendStatus = 2;
						retCode = new CommonTCSResult(0,"发送成功");
					}else{
						sendStatus = 3;
						retCode = new CommonTCSResult(Integer.valueOf(code),EnumUtils.getEnumByKey(Integer.valueOf(code), MessageSendErrorEnum.values()).getValue());
					}
				}
				for(String str:list){
					SmMessageQueue model = new SmMessageQueue();
					model.setType(2);//个人消息
					model.setAppType(appType.getKey());
					model.setSendTo(str);
					model.setContent(content);
					model.setCreateTime(startSend);
					model.setStatus(sendStatus);
					model.setSendTime(new Date());
					model.setMsgType((byte)moduleId.getKey());
					sendTmpList.add(model);
				}
				LOGGER.debug("SmMessageQueueService发送短信执行：code={},message={}",retCode.getCode(),retCode.getMessage());
			}
			
			smMessageQueueMapper.insertBatch(sendTmpList);
			
		}else{
			retCode = new CommonTCSResult(100,"系统未开启短信发送功能");
		}
		if(isNeedSendPeronalMsg && userIds != null && userIds.size()>0){
			sysMsgService.batchAddMsgs(appType, content, userIds, msgType);
		}
		return retCode;
		
	}
	
	public String queryMessageTemplate(MsgModuleEnum moduleId,AppTypeEnum appType,Object... contentArgs ){
		SmMessageTemplate condit = new SmMessageTemplate();
		condit.setModuleId(moduleId.getValue());
		condit.setAppType(appType.getKey());
		SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
		if(tmp != null){
			return String.format(tmp.getContent(), contentArgs);
		}else{
			return null;
		}
		
	}


	

}
