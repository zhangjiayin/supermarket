package com.linkwee.tc.fee.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.tc.fee.model.TCFeeSummary;
import com.linkwee.tc.fee.service.CurrentMonthPayFeeService;
import com.linkwee.tc.fee.service.TCFeePayService;
import com.linkwee.tc.fee.service.TCFeesummaryService;
import com.linkwee.web.dao.TCFeePayMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.model.SmMessageTemplate;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.mc.SysPushMessage;
import com.linkwee.web.model.weixin.WeiXinMsgRequest;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;
import com.linkwee.web.response.tc.PayFeeInfoResponse;
import com.linkwee.web.service.AcBalanceRecordService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;


 /**
 * 
 * @描述：CimFeePayService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFeePayService")
public class TCFeePayServiceImpl extends GenericServiceImpl<TCFeePay, Long> implements TCFeePayService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TCFeePayServiceImpl.class);
	
	@Autowired
	private TCFeePayMapper feePayMapper;
	
	@Autowired
	private AcBalanceRecordService  balanceRecordService;
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Resource
	private TCFeesummaryService feesummaryService;
	
	@Resource
	private List<CurrentMonthPayFeeService> currentMonthPayFeeServices;
	
	@Resource
	private SysConfigService sysConfigService;
	
	/*	private ExecutorService es;
	
	@PostConstruct
	private void init(){
		es = Executors.newFixedThreadPool(currentMonthPayFeeServices.size());
	}
	
	@PreDestroy
	private void destroy(){
		if(es==null)return;
		es.shutdown();
	}*/
	
	@Override
    public GenericDao<TCFeePay, Long> getDao() {
        return feePayMapper;
    }
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override 
	public void payFee(List<TCFeePay> noPayFeeList, String month, Date time,String operator,Collection<SysMsg> sysMsgs,Collection<SysPushMessage> pushMsgs,Collection<SmMessageQueue> messages) throws Exception {
		List<String> noPayFeeIds = Lists.newArrayListWithCapacity(noPayFeeList.size());
		//消息模板
		SmMessageTemplate condit = new SmMessageTemplate();
		condit.setModuleId( MsgModuleEnum.COMMISSIONPAY.getValue());
		condit.setAppType(AppTypeEnum.CHANNEL.getKey());//提现模板 理财师和投资的一致
		SmMessageTemplate messageTmp = messageQueueService.queryMessageTemplete(condit);
		
		final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
		try{
			List<AcBalanceRecord> balanceRecords = Lists.newArrayListWithCapacity(noPayFeeList.size());
			AcBalanceRecord balanceRecord;
			for (TCFeePay noPayFee : noPayFeeList) {
				noPayFeeIds.add(noPayFee.getBillId());
				balanceRecord = new AcBalanceRecord();
				balanceRecord.setOrderId(com.linkwee.core.util.StringUtils.getUUID());
				balanceRecord.setUserType(1);
				balanceRecord.setUserId(noPayFee.getUserId());
				balanceRecord.setUserName(noPayFee.getUserName());
				balanceRecord.setMobile(noPayFee.getUserMobile());
				balanceRecord.setTransType(12);
				balanceRecord.setTypeName("佣金与leader奖");
				balanceRecord.setRemark(month +"月佣金与leader奖");
				balanceRecord.setTransAmount(noPayFee.getFeeAmount());
				balanceRecord.setTransDate(time);
				balanceRecord.setSerialNumber(noPayFee.getBillId());
				balanceRecord.setCreatePerson(operator);
				balanceRecords.add(balanceRecord);
				if(noPayFee.getFeeAmount()!=null && noPayFee.getFeeAmount().intValue() > 0 && messageTmp != null){
					String content = String.format(messageTmp.getContent(),DateUtils.getCommissionMonth(),noPayFee.getFeeAmount().setScale(2, BigDecimal.ROUND_DOWN));
					sysMsgs.add(sysMsgService.fillSysMsg(AppTypeEnum.CHANNEL.getKey(),null,noPayFee.getUserId(),content));
					messages.add(messageQueueService.fillSmMessageQueue(AppTypeEnum.CHANNEL.getKey(),noPayFee.getUserMobile(),content,MsgModuleEnum.COMMISSIONPAY));
					pushMsgs.add(new SysPushMessage(noPayFee.getUserId(),month+"佣金发放",content));
				}
				//封装微信消息实体
				WeiXinMsgRequest wx = new WeiXinMsgRequest();
				wx.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_COMMISSION);
				wx.setUseId(noPayFee.getUserId());
				wx.setUseType("1");
				wx.setArrivalAmount(NumberUtils.getFormat(noPayFee.getFeeAmount(), "0.00")+"元");//到账金额
				wx.setArrivalTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//到账时间
				wx.setArrivalDetail(month.substring(0, 4)+"年"+month.substring(4, 6) +"月佣金");//到账详情
				wxList.add(wx);
			}
			feePayMapper.updateStatus(noPayFeeIds, 1, "processing", "处理中");
			balanceRecordService.grantProfit(balanceRecords);
			feePayMapper.updateStatus(noPayFeeIds, 2,"success","成功");
		}catch(Exception e){
			feePayMapper.updateStatus(noPayFeeIds, 3,"failure","失败");
			throw e;
		}
		//推送微信消息
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
		          weiXinMsgService.sendWeiXinMsgListCommon(wxList);
			}
		});	
	}
	
	private String[] getMonth() {
		String[] dates = new String[2];
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		if (month == 0) {
			//1月份处理去年12月份
			year = year - 1;
			month = 12;
		}
		dates[0] = String.valueOf(year);
		dates[1] = month < 10 ?  StringUtils.join(new Object[]{0,month}) : String.valueOf(month);
		return dates;
	}
	
	private String joinyearAndMonth(){
		String[] yearAndMonthAry = getMonth();
		return StringUtils.join(yearAndMonthAry);
	}
	
	@Override
	public boolean isPrePayFee() {
		return isPrePayFee(joinyearAndMonth());
	}

	@Override
	public boolean isPayFee() {
		return isPayFee(joinyearAndMonth());
	}

	private boolean isPrePayFee(String month){
		return feePayMapper.isPayFeeSummaryByStutas(month, Integer.valueOf(1));
	}
	
	private boolean isPayFee(String month){
		return feePayMapper.isPayFeeSummaryByStutas(month, Integer.valueOf(0));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public ResponseResult prePayFee(String operator) throws ServiceException{
		String[] yearAndMonthAry = getMonth();
		try{
			final String month = StringUtils.join(yearAndMonthAry);
			if(isPrePayFee(month))return new ResponseResult(Boolean.FALSE,yearAndMonthAry[0]+"年"+yearAndMonthAry[1]+"月份佣金已经结算,请勿重复结算,操作人:"+operator);
			Date time = DateTime.now().toDate();
			final FeeSummaryBuilder feeSummaryBuilder = FeeSummaryBuilder.create().baseInfo(month, yearAndMonthAry[0], yearAndMonthAry[1], time, time, operator);
			
			String yearAndMonth = StringUtils.join(yearAndMonthAry,"-");
			final String monthStart = StringUtils.join(new Object[]{yearAndMonth,"-01 00:00:00"});
			final String monthEnd = StringUtils.join(new Object[]{yearAndMonth,"-31 23:59:59"});
			for (CurrentMonthPayFeeService currentMonthPayFeeService : currentMonthPayFeeServices) {
				currentMonthPayFeeService.prePayFee(month, monthStart, monthEnd,feeSummaryBuilder);
			}
			TCFeeSummary feeSummary = feeSummaryBuilder.statisticsFee().builder();
			feesummaryService.insert(feeSummary);
			return new ResponseResult(Boolean.TRUE,yearAndMonthAry[0]+"年"+yearAndMonthAry[1]+"月份佣金结算完成");
		}catch(Exception e){
			String errormsg = yearAndMonthAry[0]+"年"+yearAndMonthAry[1]+"月份佣金结算异常,请联系开发,操作人:"+operator;
			LOGGER.error(errormsg, e);
			throw new ServiceException(errormsg);
		}

	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public ResponseResult payFee(String operator) throws ServiceException{
		String[] yearAndMonthAry = getMonth();
		try{
			final String month = StringUtils.join(yearAndMonthAry);
			if(!isPrePayFee(month))return new ResponseResult(Boolean.FALSE,yearAndMonthAry[0]+"年"+yearAndMonthAry[1]+"月份佣金未结算,请先结算,操作人:"+operator);
			if(isPayFee(month))return new ResponseResult(Boolean.FALSE,yearAndMonthAry[0]+"年"+yearAndMonthAry[1]+"月份佣金发放,请勿重复发放,操作人:"+operator);
			String yearAndMonth = StringUtils.join(yearAndMonthAry,"-");
			final String monthStart = StringUtils.join(new Object[]{yearAndMonth,"-01 00:00:00"});
			final String monthEnd = StringUtils.join(new Object[]{yearAndMonth,"-31 23:59:59"});
			//发送短信
			List<TCFeePay> noPayFeeList = feePayMapper.getPayFeeByCurMonthUseMessage(month);
			BigDecimal totalProfit= BigDecimal.ZERO;
			Integer totalNumber = Integer.valueOf(0);
			for (CurrentMonthPayFeeService currentMonthPayFeeService : currentMonthPayFeeServices) {
				CurrentMonthPayFeeStatistics result = currentMonthPayFeeService.payFee(month, monthStart, monthEnd,operator);
				totalProfit = totalProfit.add(result.getFeeAmt());
				totalNumber += result.getNumber();
			}
			feesummaryService.updateFeesummaryTypeBybizId(month, operator);
			
			//发送当月的汇总短信
			if(noPayFeeList!=null&&noPayFeeList.size()>0){
				sendMessage(month,noPayFeeList);
			}
			return new ResponseResult(Boolean.TRUE,yearAndMonthAry[0]+"年"+yearAndMonthAry[1]+"月份佣金发放完成,总发放:"+totalNumber+" 人,发放金额:"+totalProfit.setScale(4,BigDecimal.ROUND_DOWN).toString()+" 元");
		}catch(Exception e){
			String errormsg = yearAndMonthAry[0]+"年"+yearAndMonthAry[1]+"月份佣金发放异常,请联系开发,操作人:"+operator;
			LOGGER.error(errormsg, e);
			throw new ServiceException(errormsg);
		}
	}

	private void sendMessage(String month,List<TCFeePay> noPayFeeList) {
		final List<SysMsg> sysMsgs = Lists.newArrayList();
		final List<SysPushMessage> pushMsgs = Lists.newArrayList();
		final List<SmMessageQueue> messages = Lists.newArrayList();
		final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
		
		//消息模板
		SmMessageTemplate condit = new SmMessageTemplate();
		condit.setModuleId( MsgModuleEnum.COMMISSIONPAY.getValue());
		condit.setAppType(AppTypeEnum.CHANNEL.getKey());//提现模板 理财师和投资的一致
		SmMessageTemplate messageTmp = messageQueueService.queryMessageTemplete(condit);
		
		for(TCFeePay noPayFee:noPayFeeList){
			String money = noPayFee.getFeeAmount().setScale(2, BigDecimal.ROUND_DOWN).toString();
			if(!"0.00".equals(money)){//金额小于0.01不发送信息给用户
				if(noPayFee.getFeeAmount()!=null && messageTmp != null){
					String content = String.format(messageTmp.getContent(),DateUtils.getCommissionMonth(),noPayFee.getFeeAmount().setScale(2, BigDecimal.ROUND_DOWN));
					sysMsgs.add(sysMsgService.fillSysMsg(AppTypeEnum.CHANNEL.getKey(),null,noPayFee.getUserId(),content));
					SmMessageQueue sms = messageQueueService.fillSmMessageQueue(AppTypeEnum.CHANNEL.getKey(),noPayFee.getUserMobile(),content,MsgModuleEnum.COMMISSIONPAY);
					sms.setMonth(month);
					sms.setMoney(noPayFee.getFeeAmount().setScale(2, BigDecimal.ROUND_DOWN).toString());
					messages.add(sms);
					pushMsgs.add(new SysPushMessage(noPayFee.getUserId(),month+"佣金发放",content));
				}
				//封装微信消息实体
				WeiXinMsgRequest wx = new WeiXinMsgRequest();
				wx.setTemkey(SysConfigConstant.ARRIVAL_REMINDER_COMMISSION);
				wx.setUseId(noPayFee.getUserId());
				wx.setUseType("1");
				wx.setArrivalAmount(NumberUtils.getFormat(noPayFee.getFeeAmount(), "0.00")+"元");//到账金额
				wx.setArrivalTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//到账时间
				wx.setArrivalDetail(month.substring(0, 4)+"年"+month.substring(4, 6) +"月佣金");//到账详情
				wxList.add(wx);
			}
		}
		
		//消息
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				
				//短信通知
				String channel = useMessageChannel();
				if("0".equals(channel)){
					messageQueueService.batchSendDiffContentMessageAndPmsg(messages,true,sysMsgs);
				}else if("1".equals(channel)){
					messageQueueService.batchSendDiffContentMessageAndPmsgJuHe(messages, true,sysMsgs);
				}
				//通知栏推送
				Map<String,Object> urlparam = Maps.newHashMap();
				urlparam.put("profitType", "2");//类型1待发放，2已发放
				urlparam.put("month", getMonth());
				pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL, SmsTypeEnum.LFEERECEIVED,urlparam,pushMsgs, null);
				//推送微信消息
	            weiXinMsgService.sendWeiXinMsgListCommon(wxList);
			}
		});
	}

	protected String useMessageChannel() {//使用那个短信通道 0是梦网  1是聚合
		String channel = sysConfigService.getValuesByKey("change_message_channel", 1);
		return channel;
	}
	
	@Override
	public List<PayFeeInfoResponse> payFeeInfoDownload() {
		return feePayMapper.payFeeInfoDownload(StringUtils.join( getMonth()));
	}
}
