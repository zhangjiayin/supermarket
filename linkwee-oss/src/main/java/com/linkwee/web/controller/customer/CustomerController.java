package com.linkwee.web.controller.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xn.user.domain.ResetLoginPwdReq;
import cn.xn.user.service.ICustomerInfoService;
import cn.xn.user.service.IPwdService;
import cn.xn.user.utils.RequestSignUtils;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.api.BaseController;
import com.linkwee.web.constant.ResponseConstant;
import com.linkwee.web.constant.WebConstants;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.ChangeLcsRecord;
import com.linkwee.web.model.InRecordLogEntry;
import com.linkwee.web.model.InvestRecord;
import com.linkwee.web.model.InvestRecordReq;
import com.linkwee.web.model.InvestorDtlResp;
import com.linkwee.web.model.InvestorProfitResp;
import com.linkwee.web.model.InvestorReq;
import com.linkwee.web.model.InvestorResp;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.Msg;
import com.linkwee.web.model.MyInvestedCustomerResp;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.model.product.WithdrawLogEntry;
import com.linkwee.web.remote.AccountCenterHandler;
import com.linkwee.web.response.PageResponse;
import com.linkwee.web.response.QueryInRecordLogResponse;
import com.linkwee.web.response.QueryWithdrawLogResponse;
import com.linkwee.web.service.CustomerCftRelFixWebService;
import com.linkwee.web.service.InvestorUserInfoService;
import com.linkwee.web.service.MsgService;
import com.linkwee.web.service.PushMsgService;
import com.linkwee.web.service.RcCustomerService;
import com.linkwee.web.service.SystemConfigService;
import com.linkwee.web.service.UsercustomerrelService;
import com.linkwee.web.util.ErrorCode;
import com.linkwee.web.util.RequestLogging;
import com.xiaoniu.account.service.IInRecordAndPayService;
import com.xiaoniu.account.service.IOutRecordSOAService;
import com.xiaoniu.account.service.IPrepare2PayService;

@RequestMapping("invest")
@Controller
@RequestLogging("投资客户")
public class CustomerController extends BaseController {
	
	@Resource
	private IOutRecordSOAService p2pIOutRecordSOAService;
	@Resource
	private InvestorUserInfoService investorUserInfoService;
	@Resource
	private IPrepare2PayService p2pPrepare2PayService;
	@Resource 
	private ICustomerInfoService p2pCustomerInfoService;
	@Resource
	private IPwdService p2pPwdService;
	@Resource
	private CustomerCftRelFixWebService customerCftRelFixService;
	@Resource
	private SystemConfigService systemConfigService;
	@Resource
	private RcCustomerService rcCustomerService;
	@Resource
	private UsercustomerrelService usercustomerrelService;
	@Resource
	private IInRecordAndPayService p2pInRecordAndPayService;
	@Resource
	private AccountCenterHandler accountCenterHandler;
	@Resource
	private MsgService msgService;
	@Resource
	private PushMsgService pushMsgService;
	
	
	/**
	 * 投资客户列表
	 */
	
	@RequestMapping("toinvestorlist")
	public String toinvestorlist(){
		return "investor/investorList";
	}
	@RequestMapping("investorlist")
	@ResponseBody
	@RequestLogging("投资客户列表")
	public PageResponse<InvestorResp> investorList(InvestorReq investorReq){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		String customer = investorReq.getCustomer();
		logsb.append("investorList|customer=").append(customer);
		PageResponse<InvestorResp> response = new PageResponse<InvestorResp>();
		if(StringUtils.isBlank(customer)){
			return response;
		}
		try {
			
			PaginatorResponse<InvestorResp> resp = customerCftRelFixService.queryInvestorList(investorReq);
			if (resp != null ) {
				response.setRows(resp.getDatas());
				response.setTotal(resp.getTotalCount());
			} else{
				response.setErrorcode(ErrorCode.SYSTEM_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	/**
	 *  提现记录
	 * @return
	 */
	@RequestMapping("towithdrawlist")
	public String toWithDrawList(String customer,Model model){
		model.addAttribute("customer", customer);
		return "investor/withDrawList";
	}
	/**
	 * 提现记录
	 * @return
	 */
	@RequestMapping("withdrawlist")
	@ResponseBody
	@RequestLogging("提现记录")
	public Object withDrawListPage( @RequestParam String customer){
		long start = System.currentTimeMillis();
		StringBuffer logsb = new StringBuffer();
		PageResponse<WithdrawLogEntry> response = new PageResponse<WithdrawLogEntry>();
		logsb.append("withDrawListPage|mobile:").append(customer);
		if(StringUtils.isBlank(customer)){
			logger.info(logsb.toString());
			return response;
		}
		Usercustomerrel usercustomerrel = usercustomerrelService.queryByMobile(customer);
		if(usercustomerrel == null){
			return response;
		}

		try {
			ServiceResponse<QueryWithdrawLogResponse> rlt = accountCenterHandler.queryWithdrawLog(customer);
			List<WithdrawLogEntry> list = new ArrayList<WithdrawLogEntry>();
			if(rlt!=null && rlt.getData()!= null){
				list = rlt.getData().getDatas();
			}else{
				return response;
			}
			List<Map<String,Object>> footer = new ArrayList<Map<String,Object>>();
		/*	Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", "<font color='red'>账户余额：</font>");
			map.put("bisTime", "0.0000");
			footer.add(map);*/
			/*Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("status", "<font color='red'>可用余额：</font>");
			map1.put("bisTime", "0.0000");
			footer.add(map1);*/
			
			BigDecimal withDrawTotal = new BigDecimal(0);
			for(WithdrawLogEntry item :list){
				if(item.getAmount()!= null){
					withDrawTotal = withDrawTotal.add(new BigDecimal(item.getAmount()));
				}
				
			}
			Map<String,Object> map2 = new HashMap<String,Object>();
			map2.put("status", "<font color='red'>提现总额：</font>");
			map2.put("bisTime", NumberUtils.getFormat(withDrawTotal, "0.0000"));
			footer.add(map2);
			
			response.setRows(list);
			response.setTotal(rlt.getData().getTotalCount());
			response.setFooter(footer);
			/*if (rlt.isSuccess()) {
				return ResponseUtil.getSuccessResponse(rlt.getData());
			} else {
				return ResponseUtil.getErrorBusi(accountCenterHandler.convertError(rlt.getHead()));
			}*/
			return response;
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		
	}
	/**
	 *  充值记录
	 * @return
	 */
	@RequestMapping("torechargelist")
	public String toRechargeList(String customer,Model model){
		model.addAttribute("customer", customer);
		return "investor/rechargeList";
	}
	/**
	 * 充值记录
	 * @return
	 */
	@RequestMapping("rechargelist")
	@ResponseBody
	@RequestLogging("充值记录")
	public Object rechargeList( @RequestParam String customer){
		long start = System.currentTimeMillis();
		StringBuffer logsb = new StringBuffer();
		logsb.append("rechargeList|mobile:").append(customer);
		PageResponse<InRecordLogEntry> response = new PageResponse<InRecordLogEntry>();
		if(StringUtils.isBlank(customer)){
			logger.info(logsb.toString());
			return response;
		}
		Usercustomerrel usercustomerrel = usercustomerrelService.queryByMobile(customer);
		if(usercustomerrel == null){
			logsb.append("投资客户信息不存在");
			logger.info(logsb.toString());
			return response;
		}
		try {
			ServiceResponse<QueryInRecordLogResponse> rlt = accountCenterHandler.queryInRecordLog(customer);
			List<InRecordLogEntry> list = new ArrayList<InRecordLogEntry>();
			if(rlt!=null && rlt.getData()!= null){
				list = rlt.getData().getDatas();
			}else{
				return response;
			}
			List<Map<String,Object>> footer = new ArrayList<Map<String,Object>>();
		/*	Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", "<font color='red'>账户余额：</font>");
			map.put("bisTime", "0.0000");
			footer.add(map);*/
			/*Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("status", "<font color='red'>可用余额：</font>");
			map1.put("bisTime", "0.0000");
			footer.add(map1);*/
			
			BigDecimal rechargeTotal = new BigDecimal(0);
			for(InRecordLogEntry item :list){
				if(item.getAmount()!= null){
					rechargeTotal = rechargeTotal.add(new BigDecimal(item.getAmount()));
				}
				
			}
			Map<String,Object> map2 = new HashMap<String,Object>();
			map2.put("status", "<font color='red'>充值总额：</font>");
			map2.put("bisTime", NumberUtils.getFormat(rechargeTotal, "0.0000"));
			footer.add(map2);
			
			response.setRows(list);
			response.setTotal(list.size());
			response.setFooter(footer);
			/*if (rlt.isSuccess()) {
				return ResponseUtil.getSuccessResponse(rlt.getData());
			} else {
				return ResponseUtil.getErrorBusi(accountCenterHandler.convertError(rlt.getHead()));
			}*/
			return response;
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
	}

	private String getSignKey(String orgStr) {
		//return systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY);
		//"lh_userCenter_signKey"
		return systemConfigService.getValuesByKey(orgStr);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("toinvestlist")
	public String toIngestList(String customer,Model model){
		model.addAttribute("customer",customer);
		return "investor/investList";
	}
	
	/**
	 * 投资记录
	 */
	@RequestMapping("investlist")
	@ResponseBody
	@RequestLogging("投资记录")
	public Object investlist(InvestRecordReq investRecordReq){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("queryInvestRecordList|params=").append(investRecordReq.toString());
		PageResponse<InvestRecord> response = new PageResponse<InvestRecord>();
		try {
			if (StringUtils.isBlank(investRecordReq.getCustomer())) {
				//查询条件为空，直接返回
				logger.info("参数错误");
				return response;
			}
			Usercustomerrel usercustomerrel = usercustomerrelService.queryByMobile(investRecordReq.getCustomer());
			if(usercustomerrel == null){
				logger.info("usercustomerrelService|invoke queryByMobile error");
				return response;
			}
			PaginatorResponse<InvestRecord> resp = customerCftRelFixService.queryInvestRecordList(investRecordReq);
			List<InvestRecord> list = resp.getDatas();
			List<Map<String,Object>> footer = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			Double investSum = customerCftRelFixService.queryInvestSum(usercustomerrel.getCustomerid());
			map.put("profit", "<font color='red'>投资总额：</font>");
			map.put("investAmount",investSum == null ? "0.0000" : NumberUtils.getFormat(investSum,"0.0000"));
			footer.add(map);
			response.setRows(list);
			response.setTotal(resp.getTotalCount());
			response.setFooter(footer);
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	
	/**
	 * 客户详情
	 * @param reqeust
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "investorDetail")
	@RequestLogging("投资客户详情")
	public String investDtl(String mobile,Model model) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("bindCardInfo|mobile=").append("|mobile=").append(mobile);
		InvestorDtlResp investorDtlResp = new InvestorDtlResp();
		try {
			if (StringUtils.isBlank(mobile)) {
				//查询条件为空，直接返回
				logsb.append("|参数错误");
				logger.info(logsb.toString());
			}
			investorDtlResp.setCustomerMobile(mobile);
			InvestorDtlResp investorDtlResp2 = investorUserInfoService.queryInvestorDetail(mobile);
			if (investorDtlResp2 != null) {
				investorDtlResp = investorDtlResp2;
				investorDtlResp.setHeadImage(systemConfigService.getImageUrl(investorDtlResp.getHeadImage()));
				
				//邀请人数
				Integer totalCustomer = customerCftRelFixService.queryRegCustomerCount(mobile);
				investorDtlResp.setInvestedNumber(totalCustomer);
				List<ChangeLcsRecord> changeLcsRecordList = customerCftRelFixService.queryChangeLcsRecord(mobile);
				investorDtlResp.setChangeLcsRecordList(changeLcsRecordList);
			} else {
				logsb.append("|findSaleInfoByMobile invoke failed");
				logger.info(logsb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		model.addAttribute("dtl", investorDtlResp);
		return "investor/investorDetail";
	}
	/**
	 * 密码重置
	 */
	@RequestMapping("topwdreset")
	public String toPwdReset(String mobile,Model model){
		Usercustomerrel usercustomerrel = usercustomerrelService.queryByMobile(mobile);
		model.addAttribute("dtl",usercustomerrel);
		return "investor/pwdReset";
	}
	/**
	 * 登录密码重置
	 * @param reqeust
	 * @param mobile
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "resetpwd")
	@ResponseBody
	@RequestLogging("登录密码重置")
	public Object resetPwd(HttpServletRequest reqeust,@RequestParam String mobile,@RequestParam String newPwd) {
		ResponseResult result = null;
		InvestorUserInfo iUserInfo = investorUserInfoService.queryInvestorUserInfoByMobile(mobile);
		if(iUserInfo == null){
			result = new ResponseResult(ResponseConstant.FAILURE,"查询客户信息错误");
			return result;
		}
		result =  new ResponseResult();
		try{
			ResetLoginPwdReq model = new ResetLoginPwdReq();
			model.setLoginName(mobile);
			model.setLoginNewPwd(newPwd);
			model.setAppVersion("1.0");
			model.setSystemType("channel");
			model.setSourceType("PCWeb");
			model.setSign(RequestSignUtils.addSign(model,getSignKey(WebConstants.USER_MD5_SIGN_KEY)));
			cn.xn.user.domain.CommonRlt<cn.xn.user.domain.EmptyObject> changeRlt = p2pPwdService.doResetLoginPwd(model);
			if(changeRlt.getReturnCode() ==0){//消息中心写数据
				int appType= AppTypeEnum.CHANNEL.getKey();//待定
				String userId = iUserInfo.getUserId();
				String content = String.format("亲爱的%s,您的密码信息已于%s由管理员更新,敬请留意", iUserInfo.getMobile(),DateUtils.getCurrentDate());
				Msg msg= new Msg();
				msg.setAppType(appType);
				msg.setStatus(0);
				msg.setType(1);//待定
				msg.setUserNumber(userId);
				msg.setContent(content);
				msgService.insert(msg);
				result.setIsFlag(true);
				}else{
					result.setIsFlag(false);
					result.setMsg(changeRlt.getReturnMsg());
				}
		}catch(Exception e){
			result.setIsFlag(false);
		}
		return result;
		
	}
	/*
	 * 邀请的客户
	 * @return
	 */
	@RequestMapping("toinvestedlist")
	public String toInvestedList(@RequestParam String mobile,Model model){
		model.addAttribute("customer", mobile);
		return "investor/investedList";
	}
	
	/**
	 * 我邀请的客户
	 */
	@RequestMapping("investedlist")
	@ResponseBody
	@RequestLogging("邀请的客户")
	public Object investedCustomerList(InvestorReq investorReq){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("MyInvestedCustomer|params=").append(investorReq.toString());
		PageResponse<MyInvestedCustomerResp> response = new PageResponse<MyInvestedCustomerResp>();
		List<MyInvestedCustomerResp> investedList = new ArrayList<MyInvestedCustomerResp>();
		try {
			if(StringUtils.isBlank(investorReq.getCustomer())){
				logger.info("参数错误customer:"+investorReq.getCustomer());
				return response;
			}
			investedList = customerCftRelFixService.MyInvestedCustomer(investorReq);
			response.setTotal(investedList.size());
			response.setRows(investedList);
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	
	/**
	 * 投资收益列表
	 * @return
	 */
	@RequestMapping("toinvestprofitlist")
	public String toInvestProfitList(){
		return "investor/investProfitList";
	}
	
	/**
	 * 投资收益列表
	 */
	@RequestMapping("investprofitlist")
	@ResponseBody
	@RequestLogging("投资收益列表")
	public Object investProfitList(InvestorReq investorReq){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("queryInvestProfitList|params=").append(investorReq.toString());
		PageResponse<InvestorProfitResp> response = new PageResponse<InvestorProfitResp>();
		/*if(StringUtils.isBlank(investorReq.getCustomer())){
			return response;
		}*/
		try {
			
			PaginatorResponse<InvestorProfitResp> resp = customerCftRelFixService.queryInvestProfitList(investorReq);
			List<InvestorProfitResp> respList = resp.getDatas();
			 Map<String,Object>  redMap = customerCftRelFixService.usedTotalRedPaper();//webRedPaperService.getInvestorLatlyUse();
			 for(InvestorProfitResp item :respList){
				 if(!StringUtils.isBlank(item.getCustomerId()) &&
						 String.valueOf(redMap.get(item.getCustomerId())) !=null &&
						 !"null".equals(String.valueOf(redMap.get(item.getCustomerId())).toLowerCase())
						 ){
				 item.setRedpaperAmout(Double.valueOf(String.valueOf(redMap.get(item.getCustomerId()))));
				 }
			 }
				response.setRows(respList);
				response.setTotal(resp.getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	
	
	@RequestMapping("statdata")
	@ResponseBody
	@RequestLogging("投资客户数据概览-报表图")
	public Object dataViewInvestMoney(String startDate,String endDate){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("dataViewInvestorAmount|startTime=").append(startDate).append("endTime=").append(endDate);
		if(StringUtils.isBlank(startDate)  || StringUtils.isBlank(endDate)){
			startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
			endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		}

		Map<String, Object> mapData = null;
		String msg = "";
		 try {
			 mapData = customerCftRelFixService.queryInvestorAndMoneyByDate(startDate, endDate);
			msg = "查询数据成功";
			logsb.append(msg);
			logger.info(logsb.toString());
		} catch (Exception e) {
			msg = "查询数据错误";
			logsb.append(msg);
			logger.info(logsb.toString());
		}finally{
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		 
		return mapData;
	}
	
	/**
	 * 数据概览
	 * @param model
	 * @return
	 */
	@RequestMapping("dataview")
	@RequestLogging("投资数据概览-页面")
	public String dataView(Model model){
		String startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
		String endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		long startTime = System.currentTimeMillis();
		Map<String,Object> mapRlt = customerCftRelFixService.queryInvestorAndMoney();
		System.out.println("customerCftRelFixService.queryInvestorAndMoney():"+(System.currentTimeMillis()-startTime));
		model.addAttribute("data",mapRlt);
		/*startTime = System.currentTimeMillis();
		model.addAttribute("total",queryTotalData());*/
		System.out.println("queryTotalData():"+(System.currentTimeMillis()-startTime));
		return "investor/investorDateView";
	}
	/**
	 * 总投资人数和年化投资额 异步加载
	 */
	@RequestMapping("totaldata")
	@ResponseBody
	public Map<String, Object> queryTotalData(){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		Map<String, Object> mapData = null;
		String msg = "";
		 try {
			 mapData = customerCftRelFixService.queryTotalInvestorAndMoney();
			msg = "查询数据成功";
			logsb.append(msg);
			logger.info(logsb.toString());
		} catch (Exception e) {
			msg = "查询数据错误";
			logsb.append(msg);
			logger.info(logsb.toString());
		}finally{
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		 
		return mapData;
	}
	
	@RequestMapping("/removeHeadImage")
	@RequestLogging("删除投资用户头像")
	@ResponseBody
	public ResponseResult removeInvestorHeadImage(String mobile) throws Exception{
		ResponseResult result = new ResponseResult();
		if(com.linkwee.core.util.StringUtils.isNotBlank(mobile) && investorUserInfoService.removeInvestorHeadImage(mobile)){
			result.setIsFlag(true);
		}
		else{
			result.setIsFlag(false);
		}
		return result;
	}

	
}
