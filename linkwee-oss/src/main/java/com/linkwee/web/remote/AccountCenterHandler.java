package com.linkwee.web.remote;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.ErrorCodeSupport;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.Constants;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.model.InRecordLogEntry;
import com.linkwee.web.model.MyAssetResp;
import com.linkwee.web.model.product.WithdrawLogEntry;
import com.linkwee.web.response.QueryInRecordLogResponse;
import com.linkwee.web.response.QueryWithdrawLogResponse;
import com.linkwee.web.response.WithdrawSummaryResponse;
import com.linkwee.web.service.SystemConfigService;
import com.linkwee.web.service.UsercustomerrelService;
import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.QueryInRecordReq;
import com.xiaoniu.account.domain.QueryOutRecordReq;
import com.xiaoniu.account.domain.QueryUserAssetReq;
import com.xiaoniu.account.domain.QueryUserOutReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.InRecordRlt;
import com.xiaoniu.account.domain.result.OutRecord2Rlt;
import com.xiaoniu.account.domain.result.UserAssetRlt;
import com.xiaoniu.account.domain.result.UserOutSumRlt;
import com.xiaoniu.account.service.IInRecordAndPayService;
import com.xiaoniu.account.service.IOutRecordSOAService;
import com.xiaoniu.account.service.IUserAssetSOAService;
import com.xiaoniu.account.utils.SignUtils;

@Component
public class AccountCenterHandler {
	protected static final Logger logger = LoggerFactory.getLogger(AccountCenterHandler.class);

	@Resource
	private IInRecordAndPayService p2pInRecordAndPayService;
	
	@Resource
	private IOutRecordSOAService p2pIOutRecordSOAService;
	
	@Resource
	private IUserAssetSOAService p2pIUserAssetSOAService;
	

	@Resource
	private SystemConfigService systemConfigService;
	@Resource
	private UsercustomerrelService usercustomerrelService;
	
	/**
	 * 提现记录查询
	 * @param head
	 * @param req
	 * @param logsb
	 * @return
	 */
	public ServiceResponse<QueryWithdrawLogResponse> queryWithdrawLog(String customer) {
		StringBuilder logsb = new StringBuilder();
		String partnerId = getPartnerId();
		
		String userId = getUserId(customer);
		if(StringUtils.isBlank(userId)){
			return new ServiceResponse<QueryWithdrawLogResponse>(new ErrorCodeSupport(1234,"查询客户信息错误"));
		}
		
		String charset = "UTF-8";
		String signkey = getSignKey();
		int totalRecord = 120;
		QueryOutRecordReq queryOutRecordReq = new QueryOutRecordReq();
		queryOutRecordReq.setPartnerId(partnerId);
		queryOutRecordReq.setUserId(userId);
		queryOutRecordReq.setCurrentPage(1);
		queryOutRecordReq.setPageSize(totalRecord);
		queryOutRecordReq.setCharset(charset);
		queryOutRecordReq.setSign(SignUtils.addSign(queryOutRecordReq, queryOutRecordReq.getCharset(), signkey));
		
		CommonRlt<PageResult<OutRecord2Rlt>> commonRlt = p2pIOutRecordSOAService.outRecordListPage(queryOutRecordReq);
		logsb.append("|outRecordListPage|invokeRetCode=")
				.append(commonRlt.getReturnCode().intValue())
				.append("|invokeRetMsg=").append(commonRlt.getReturnMsg());
		logger.info(logsb.toString());
		if (commonRlt.getReturnCode().intValue() == 0) {
			//查询成功
			PageResult<OutRecord2Rlt> pageResult = commonRlt.getData();
			List<OutRecord2Rlt> outRecord2RltList = pageResult.getResult();
			QueryWithdrawLogResponse queryWithdrawLogResponse = new QueryWithdrawLogResponse();
			
			List<WithdrawLogEntry> datas = new ArrayList<WithdrawLogEntry>();
            if (outRecord2RltList != null && outRecord2RltList.size() > 0) {
            	int size = outRecord2RltList.size();
            	OutRecord2Rlt outRecord2Rlt = null;
            	for (int i = 0; i < size; i++) {
            		outRecord2Rlt = outRecord2RltList.get(i);
            		WithdrawLogEntry entry = new WithdrawLogEntry();
            		entry.setBisTime(outRecord2Rlt.getBisTime());
            		double totalAmount = outRecord2Rlt.getTotalAmount();
            		entry.setTotalAmount(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(totalAmount / 10000)));
            		double amount = outRecord2Rlt.getAmount();
            		entry.setAmount(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(amount / 10000)));
            		double fee = outRecord2Rlt.getFee();
            		entry.setFee(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(fee / 10000)));
            		entry.setStatus(outRecord2Rlt.getStatus());
            		
            		datas.add(entry);
            	}
            }
            queryWithdrawLogResponse.setDatas(datas);
            
            return new ServiceResponse<QueryWithdrawLogResponse>(queryWithdrawLogResponse);
		} else {
			//查询失败
			return new ServiceResponse<QueryWithdrawLogResponse>(convertError(commonRlt));
		}
	}
	
	/**
	 * 查询充值记录
	 * @param head
	 * @param req
	 * @param logsb
	 * @return
	 */
	public ServiceResponse<QueryInRecordLogResponse> queryInRecordLog(String customer) {
		StringBuilder logsb = new StringBuilder();
		String partnerId = getPartnerId();
		
		String userId = getUserId(customer);
		String charset = "UTF-8";
		String signkey = getSignKey();
		
		int totalRecord = 120 ;
		QueryInRecordReq queryInRecordReq = new QueryInRecordReq();
		queryInRecordReq.setPartnerId(partnerId);
		queryInRecordReq.setUserId(userId);
		queryInRecordReq.setCurrentPage(1);
		queryInRecordReq.setPageSize(totalRecord);
		queryInRecordReq.setCharset(charset);
		queryInRecordReq.setSign(SignUtils.addSign(queryInRecordReq, queryInRecordReq.getCharset(), signkey));
		
		CommonRlt<PageResult<InRecordRlt>> commonRlt = p2pInRecordAndPayService.inRecordListPage(queryInRecordReq);
		logsb.append("|inRecordListPage|invokeRetCode=")
		.append(commonRlt.getReturnCode().intValue())
		.append("|invokeRetMsg=").append(commonRlt.getReturnMsg());
		logger.info(logsb.toString());
		if (commonRlt.getReturnCode().intValue() == 0) {
			//查询成功
			PageResult<InRecordRlt> pageResult = commonRlt.getData();
			List<InRecordRlt> inRecord2RltList = pageResult.getResult();
			QueryInRecordLogResponse queryInRecordLogResponse = new QueryInRecordLogResponse();
			List<InRecordLogEntry> datas = new ArrayList<InRecordLogEntry>();
			if (inRecord2RltList != null && inRecord2RltList.size() > 0) {
				int size = inRecord2RltList.size();
				InRecordRlt inRecord2Rlt = null;
            	for (int i = 0; i < size; i++) {
            		inRecord2Rlt = inRecord2RltList.get(i);
            		InRecordLogEntry entry = new InRecordLogEntry();
            		entry.setInRecordNo(inRecord2Rlt.getInRecordNo());
            		entry.setPartnerId(inRecord2Rlt.getPartnerId());
            		entry.setUserId(inRecord2Rlt.getUserId());
            		entry.setUserName(inRecord2Rlt.getUserName());
            		entry.setBisType(inRecord2Rlt.getBisType().intValue() + "");
            		entry.setBisName(inRecord2Rlt.getBisName());
            		entry.setBisTime(inRecord2Rlt.getBisTime());
            		double amount = inRecord2Rlt.getAmount();
            		entry.setAmount(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(amount / 10000)));
            		entry.setStatus(inRecord2Rlt.getStatus());
            		
            		datas.add(entry);
            	}
			}
			queryInRecordLogResponse.setDatas(datas);
			
        	return new ServiceResponse<QueryInRecordLogResponse>(queryInRecordLogResponse);
		} else {
			//查询失败
			return new ServiceResponse<QueryInRecordLogResponse>(convertError(commonRlt));
		}
	}
	
	
	/**
	 * 查询用户累计提现
	 * @param head
	 * @param logsb
	 * @return
	 */
	public ServiceResponse<WithdrawSummaryResponse> getWithdrawSummary(	String customer, StringBuilder logsb) {
		String partnerId = getPartnerId();
		
		String userId = getUserId(customer);
		String charset = "UTF-8";
		String signkey = getSignKey();
		
		QueryUserOutReq queryUserOutReq = new QueryUserOutReq();
		queryUserOutReq.setPartnerId(partnerId);
		queryUserOutReq.setUserId(userId);
		queryUserOutReq.setCharset(charset);
		queryUserOutReq.setSign(SignUtils.addSign(queryUserOutReq, queryUserOutReq.getCharset(), signkey));
		
		CommonRlt<UserOutSumRlt> commonRlt = p2pIOutRecordSOAService.getUserOutSum(queryUserOutReq);
		logsb.append("|getUserOutSum|invokeRetCode=")
				.append(commonRlt.getReturnCode().intValue())
				.append("|invokeRetMsg=").append(commonRlt.getReturnMsg());
		if (commonRlt.getReturnCode().intValue() == 0) {
			//查询成功
			UserOutSumRlt data = commonRlt.getData();
			double outTotalAmount = data.getOutTotalAmount();
			double outTotalFee = data.getOutTotalFee();
			double outingAmount = data.getOutingAmount();
			double outingFee = data.getOutingFee();
			
			WithdrawSummaryResponse withdrawSummaryResponse = new WithdrawSummaryResponse();
			withdrawSummaryResponse.setOutTotalAmount(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(outTotalAmount / 10000)));
			withdrawSummaryResponse.setOutTotalFee(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(outTotalFee / 10000)));
			withdrawSummaryResponse.setOutingAmount(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(outingAmount / 10000)));
			withdrawSummaryResponse.setOutingFee(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(outingFee / 10000)));
			
			return new ServiceResponse<WithdrawSummaryResponse>(withdrawSummaryResponse);
		} else {
			return new ServiceResponse<WithdrawSummaryResponse>(convertError(commonRlt));
		}
	}
	
	public <T> ServiceResponse<T> convertCommonRlt(final CommonRlt<T> commonRlt) {
		if (commonRlt.getReturnCode().intValue() == Constants.SUCCESS) {
			return new ServiceResponse<T>(commonRlt.getData());
		} else {
			return new ServiceResponse<T>(convertError(commonRlt));
		}
	}

	@SuppressWarnings("rawtypes")
	public ErrorCode convertError(CommonRlt commonRlt) {
		return new ErrorCodeSupport(commonRlt.getReturnCode(),
				commonRlt.getReturnMsg());
	}
	
	public BaseResponse convertError(ReturnCode head) {
		if (30001 == head.getCode()) {
			return new BaseResponse("noBindCard", "用户未绑卡");
		} else {
			return new BaseResponse(head.getCode(), head.getMessage());
		}
	}
	
	/**
	  * 获取签名Key
	  * @return
	  */
	private String getSignKey() {
		return systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY);
	}

	private String getPartnerId() {
		return systemConfigService.getValuesByKey(Constants.account_partnerId);
	}
	private String getUserId(String customer){
		return usercustomerrelService.findUserIdByMobile(customer);
	}
	 
	/**
	 * 查询我的资产
	 * @Auther xuzhao
	 * @Date 2016年1月18日 下午4:24:45
	 * @param head
	 * @param logsb
	 * @return
	 */
	public ServiceResponse<MyAssetResp> getMyAsset(String customer, StringBuilder logsb) {
		String partnerId = getPartnerId();
		String userId = getUserId(customer);
		
		String charset = "UTF-8";
		String signkey = getSignKey();
		
		QueryUserAssetReq queryUserAssetReq = new QueryUserAssetReq();
		queryUserAssetReq.setPartnerId(partnerId);
		queryUserAssetReq.setUserId(userId);
		queryUserAssetReq.setCharset(charset);
		queryUserAssetReq.setSign(SignUtils.addSign(queryUserAssetReq, queryUserAssetReq.getCharset(), signkey));
		
		CommonRlt<UserAssetRlt> commonRlt = p2pIUserAssetSOAService.getUserAsset(queryUserAssetReq);
		logsb.append("|getUserAsset|invokeRetCode=")
				.append(commonRlt.getReturnCode().intValue())
				.append("|invokeRetMsg=").append(commonRlt.getReturnMsg());
		if (commonRlt.getReturnCode().intValue() == 0) {
			UserAssetRlt data = commonRlt.getData(); 
			double toInvestAmount = data.getToInvestAmount();
			double totalAmount = data.getTotalAmount();
			MyAssetResp myAssetResp = new MyAssetResp();
			myAssetResp.setAccountBalance(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(toInvestAmount / 10000)));
			myAssetResp.setTotalAmount(NumberUtils.getDefaultFormatHalfDown(Double.valueOf(totalAmount / 10000)));
			myAssetResp.setCurrentAmount("0.00");
			myAssetResp.setFloatAmount("0.00");
			return new ServiceResponse<MyAssetResp>(myAssetResp);
		} else {
			return new ServiceResponse<MyAssetResp>(convertError(commonRlt));
		}
	}

	
}