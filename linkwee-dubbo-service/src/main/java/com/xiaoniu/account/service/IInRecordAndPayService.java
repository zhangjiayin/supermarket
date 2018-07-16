package com.xiaoniu.account.service;

import java.util.List;
import java.util.Map;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.GetTransTypeReq;
import com.xiaoniu.account.domain.PaySendSmsBindCardReq;
import com.xiaoniu.account.domain.QueryInRecordReq;
import com.xiaoniu.account.domain.QueryRecordReq;
import com.xiaoniu.account.domain.QuickPayReq;
import com.xiaoniu.account.domain.QuickPaySendSmsReq;
import com.xiaoniu.account.domain.SystemInRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.InRecordResult;
import com.xiaoniu.account.domain.result.InRecordRlt;
import com.xiaoniu.account.domain.result.PaymentResult;
import com.xiaoniu.account.domain.result.RecordRlt;
import com.xiaoniu.account.domain.result.TransTypeRlt;

/**
 * 
 * @Description: 获取短信验证码、绑卡支付、一键支付
 * @author 周锋恒
 * @date 2015年7月23日
 *
 */
public interface IInRecordAndPayService {	
	
	
	/**
	 * 首次支付发送短信,快捷支付专用
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, String>> firstPaySendSms(PaySendSmsBindCardReq req); 
	
	/**
	 * 用户首次支付动作, 快捷支付专用
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, String>> firstBindCardPay(PaySendSmsBindCardReq req);
	
	

	/**
	 * 一键支付接口
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, Object>> directPay(PaySendSmsBindCardReq req);
	
	
	
	/**
	 * 系统的各类型充值
	 * @param req
	 * @return Map {inRecordNo:充值流水号}
	 */
	public CommonRlt<Map<String, String>> systemInRecord(SystemInRecordReq req);
	
	
	/**
	 * 接收支付平台通知信息，更新充值记录
	 * @return
	 */
	public CommonRlt<EmptyObject> acceptUnipayNotice(Map<String, String[]> map);
	
	/**
	 * 接收支付平台通知信息，更新充值记录
	 * @return
	 */
	public CommonRlt<EmptyObject> acceptUnipayNotice(PaymentResult paymentResult);
	
	
	/**
	 * 查询充值记录
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<InRecordRlt>> inRecordListPage(QueryInRecordReq req);
	
	
	/**
	 * 获取所有交易类型
	 * @return
	 */
	public CommonRlt<List<TransTypeRlt>> getAllTransType();
	
	/**
	 * 根据业务id获取所有交易类型
	 * @return
	 */
	public CommonRlt<List<TransTypeRlt>> getAllTransType(GetTransTypeReq req);
	
	
	
	
	/**
	 * 已绑卡用户再支付发送短信验证码
	 * @param req
	 * @return
	 */
	public CommonRlt<RecordRlt> quickPaySendSms(QuickPaySendSmsReq req); 
	
	/**
	 * 已绑卡用户再支付
	 * @param req
	 * @return
	 */
	public CommonRlt<RecordRlt> quickPay(QuickPayReq req);
	
	
	/**
	 *  绑卡直接支付
	 * @param req
	 * @return
	 */
	public CommonRlt<RecordRlt> quickPayDirect(QuickPaySendSmsReq req);
	
	
	/**
	 * 查询交易单情况
	 * @param req
	 * @return
	 */
	public CommonRlt<InRecordResult> queryInRecord(QueryRecordReq req);
	
	
}
