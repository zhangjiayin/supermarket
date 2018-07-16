package com.linkwee.tc.fee.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericService;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.web.model.SmMessageQueue;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.mc.SysPushMessage;
import com.linkwee.web.response.tc.PayFeeInfoResponse;
 /**
 * 
 * @描述： CimFeePayService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeePayService extends GenericService<TCFeePay,Long>{

	void payFee(List<TCFeePay> noPayFeeList,String month,Date time,String operator,Collection<SysMsg> sysMsgs,Collection<SysPushMessage> pushMsgs,Collection<SmMessageQueue> messages) throws Exception;
	
	
	/**
	 * 是否计算佣金
	 * @return
	 */
	boolean isPrePayFee();
	
	/**
	 * 是否发放佣金
	 * @return
	 */
	boolean isPayFee();
	
	/**
	 * 预支付本月佣金
	 * @param operator
	 * @return
	 * @throws ServiceException
	 */
	ResponseResult prePayFee(String operator)throws ServiceException;
	
	/**
	 * 支付本月佣金
	 * @param operator
	 * @return
	 * @throws ServiceException
	 */
	ResponseResult payFee(String operator)throws ServiceException;
	
	
	/**
	 * 下载支付佣金数据
	 * @return
	 */
	List<PayFeeInfoResponse> payFeeInfoDownload();
}
