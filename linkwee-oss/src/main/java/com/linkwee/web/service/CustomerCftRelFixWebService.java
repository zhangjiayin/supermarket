package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.web.model.ChangeLcsRecord;
import com.linkwee.web.model.Feerate;
import com.linkwee.web.model.InvestRecord;
import com.linkwee.web.model.InvestRecordReq;
import com.linkwee.web.model.InvestorProfitResp;
import com.linkwee.web.model.InvestorReq;
import com.linkwee.web.model.InvestorResp;
import com.linkwee.web.model.MyInvestedCustomerResp;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.UnconventionalRecord;
import com.linkwee.web.model.Usercustomerrel;




public interface CustomerCftRelFixWebService {
	public static enum Error implements ErrorCode{
		SESSION_EXPIRE(142001, "会话已过期，请重新登录"),
		SALE_USER_NOT_EXIST(142002, "理财师不存在"),
		CUSTOMER_NOT_FREE(150000, "客户为非自由客户"),
		CUSTOMER_NOT_EXIST(150001, "客户不存在"),
		SELF_IS_CFP(150001, "客户自己是理财师"),
		CFP_MOBILE_ERROR(150001, "新理财师帐号错误"),
		DB_ERROR(141005, "系统异常"),
		PARAM_ERROR(141005, "参数错误");
		Error(int code,String message){
			this.code = code;
			this.message = message;
		}
		private int code = 0;
		private String message = "";
		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	public SaleUserInfo findSaleUserInfoByMobile(String mobile);
	/**
	 * 客户绑定理财师
	 * @param req
	 * @param sessionid
	 * @return
	 */
	public ServiceResponse<String> boundCfpForCustomer(Usercustomerrel usercustomerrel,UnconventionalRecord ur);
	/**
	 * 客户解绑理财师
	 * @param req
	 * @param sessionid
	 * @return
	 */
	public ServiceResponse<Integer> cleanRelForCustomer(Object[] objects,String opName,String opNumber);
	
	/**
	 * 退出理财师
	 * @param req
	 * @param sessionid
	 * @return
	 */
	public ReturnCode exitFromCfp(String number,String opName,String opNumber);
	
	/**
	 * 转为自由理财师
	 * @param req
	 * @param sessionid
	 * @return
	 */
	public ReturnCode beFreeCfp(Object[] objects,String opName,String opNumber);
	
	/**
	 * 转为新财富理财师
	 * @param mobile
	 * @return
	 */
	public ReturnCode beXcfCfp(String department,String mobile,String opName,String opNumber);
	/**
	 * 理财师变更历史记录
	 * @param req
	 * @return
	 */
	public PaginatorSevResp<UnconventionalRecord> queryUnRecList(PaginatorSevReq pageRequest);
	/**
	 * 投资记录（包含钱罐子投资数据）
	 * @param investRecordReq
	 * @return
	 */
	public PaginatorResponse<InvestRecord> queryInvestRecordList(InvestRecordReq investRecordReq);
	/**
	 * 邀请人数
	 */
	public Integer queryRegCustomerCount(String mobile);
	public PaginatorResponse<InvestorResp> queryInvestorList(InvestorReq investorReq);
	/**
	 * 我邀请的用户
	 */
	public List<MyInvestedCustomerResp> MyInvestedCustomer(InvestorReq investorReq);
	/**
	 * 投资收益列表
	 * @param investorReq
	 * @return
	 */
	public PaginatorResponse<InvestorProfitResp> queryInvestProfitList(InvestorReq investorReq);
	/**
	 * 产品佣金费率
	 */
	public ServiceResponse<List<Feerate>> productFeeRate(); 
	
	public Map<String,Object> queryInvestorAndMoney();
	public Map<String, Object> queryInvestorAndMoneyByDate(String startTime, String endTime);
	/**
	 * 查询客户投资总额
	 */
	public Double queryInvestSum(String userId);
	/**
	 * 总投资人数和投资额
	 * @return
	 */
	public Map<String, Object> queryTotalInvestorAndMoney();
	public Map<String, Object> usedTotalRedPaper();
	
	/**
	 * 重新绑定理财师
	 * @param mobile
	 * @param lcsMobile
	 * @param changeType
	 * @return
	 */
	public ServiceResponse<String> changeLcs(String mobile, String lcsMobile, String changeType);
	
	 /**
	  * 更换理财师操作记录
	  * @param customerMobile
	  * @return
	  */
	 public List<ChangeLcsRecord> queryChangeLcsRecord(String customerMobile);
	
}