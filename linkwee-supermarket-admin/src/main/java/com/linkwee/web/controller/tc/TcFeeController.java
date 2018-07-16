package com.linkwee.web.controller.tc;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.export.ExportSupport;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.tc.fee.model.TCFeeSummary;
import com.linkwee.tc.fee.service.TCFeeBalanceService;
import com.linkwee.tc.fee.service.TCFeePayService;
import com.linkwee.tc.fee.service.TCFeeService;
import com.linkwee.tc.fee.service.TCFeesummaryService;
import com.linkwee.web.model.User;
import com.linkwee.web.request.tc.FeeDetailRequest;
import com.linkwee.web.request.tc.FeeRequest;
import com.linkwee.web.response.tc.PayFeeInfoResponse;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("fee")
@RequestLogging("佣金")
public class TcFeeController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(TcFeeController.class);
	
	@Autowired
	private ExportSupport exportSupport;
	
	@Autowired
	private TCFeeBalanceService feeBalanceService;
	
	@Autowired
	private TCFeeService feeService;
	
	@Autowired
	private TCFeePayService feePayService;
	

	@Autowired
	private TCFeesummaryService feesummaryService;
	
	 /**
     * 查看列表
     */
    @RequestMapping(value="initPage")
    public String initPage(Model model) {
    	return "fee/fee-page";
    }
    
    @RequestMapping(value="{mobile}/recordPage")
    @RequestLogging("获取理财师佣金记录页面")
    public Object recordPage(@PathVariable("mobile")String mobile,Model model){
    	model.addAttribute("mobile", mobile);
    	return "fee/fee-record-page";
    }
    
    @RequestMapping(value="{month}/{mobile}/recordDetailPage")
    @RequestLogging("获取理财师佣金记录页面")
    public Object recordDetailPage(@PathVariable("month")String month,@PathVariable("mobile")String mobile,Model model){
    	model.addAttribute("month", month);
    	model.addAttribute("mobile", mobile);
    	return "fee/fee-record-detail-page";
    }
    
    
    
    @RequestMapping("list")
    @ResponseBody
    @RequestLogging("查看列表")
    public Object getList(@RequestParam String  _dt_json ){
    	FeeRequest feeRequest = JsonUtils.fromJsonToObject(_dt_json, FeeRequest.class);
    	if(StringUtils.isBlank(feeRequest.getMobile()))return new ResponseResult(false,"手机号码不能为空");
    	return feeBalanceService.getFeebalanceList(feeRequest);
    }
    
   
    @RequestMapping("record")
    @ResponseBody
    @RequestLogging("查看理财师佣金记录")
    public Object getRecord(@RequestParam String  _dt_json){
    	FeeDetailRequest feeRequest = JsonUtils.fromJsonToObject(_dt_json, FeeDetailRequest.class);
    	if(StringUtils.isBlank(feeRequest.getMobile()))return new ResponseResult(false,"手机号码不能为空");
    	return feeBalanceService.getFeebalanceRecordByMobile(feeRequest);
    }
    
    @RequestMapping("recordDetail")
    @ResponseBody
    @RequestLogging("查看理财师佣金记录明细")
    public Object getRecordDetail(@RequestParam String  _dt_json){
    	FeeDetailRequest feeRequest = JsonUtils.fromJsonToObject(_dt_json, FeeDetailRequest.class);
    	if(StringUtils.isBlank(feeRequest.getMobile()) || StringUtils.isBlank(feeRequest.getMonth()))return new ResponseResult(false,"手机号码或者月份不能为空");
    	return feeBalanceService.getFeeDetailRecord(feeRequest);
    }
    
    @RequestMapping(value="feeModelPage")
    public Object feeModelPage(Model model,HttpSession session){
    	String month = feeService.getMonth();
    	String date[] =StringUtils.split(month, "-");
    	model.addAttribute("year", date[0]);
    	model.addAttribute("month", date[1]);
    	
    	TCFeeSummary feeSummary =null;
    	//默认未计算
    	Integer payStatus = Integer.valueOf(0);
    	boolean isPrePayFee = feePayService.isPrePayFee();
    	boolean isPayFee = feePayService.isPayFee();
    	if(!isPrePayFee && !isPayFee){
    		session.setAttribute("prePayFeeUid", com.linkwee.core.util.StringUtils.getUUID());
    		BigDecimal zeroProfit = BigDecimal.ZERO;
    		Integer zeroNumer =Integer.valueOf(0);
    		feeSummary = new TCFeeSummary();
    		feeSummary.setFeeProfit(zeroProfit);
    		feeSummary.setFeeProfitNumber(zeroNumer);
    		feeSummary.setRecommendProfit(zeroProfit);
    		feeSummary.setRecommendProfitNumer(zeroNumer);
    		feeSummary.setChildManagementProfit(zeroProfit);
    		feeSummary.setChildManagementProfitNumber(zeroNumer);
    		feeSummary.setTeamManagementProfit(zeroProfit);
    		feeSummary.setTeamManagementProfitNumer(zeroNumer);
    		feeSummary.setFeeProfitAdd(zeroProfit);
    		feeSummary.setFeeProfitAddNumber(zeroNumer);
    		feeSummary.setRecommendProfitAdd(zeroProfit);
    		feeSummary.setRecommendProfitAddNumer(zeroNumer);
    		feeSummary.setPersonFeeAdd(zeroProfit);
    		feeSummary.setPersonFeeAddNumber(zeroNumer);
    		feeSummary.setInsuranceFeeProfit(zeroProfit);
    		feeSummary.setInsuranceFeeProfitNumber(zeroNumer);
    		feeSummary.setInsuranceRecommendProfit(zeroProfit);
    		feeSummary.setInsuranceRecommendProfitNumer(zeroNumer);
    		feeSummary.setInsuranceChildManagementProfit(zeroProfit);
    		feeSummary.setInsuranceChildManagementProfitNumber(zeroNumer);
    		feeSummary.setInsuranceTeamManagementProfit(zeroProfit);
    		feeSummary.setInsuranceTeamManagementProfitNumer(zeroNumer);
    		feeSummary.setTotalProfit(zeroProfit);
    		feeSummary.setTotalNumber(zeroNumer);
    		
    	}
    	else if(isPrePayFee && !isPayFee){
    		//已计算，未 发放
    		payStatus = Integer.valueOf(1);
    		session.setAttribute("payFeeUid", com.linkwee.core.util.StringUtils.getUUID());
    		feeSummary = new TCFeeSummary();
    		feeSummary.setBizId(StringUtils.join(date));
    		feeSummary = feesummaryService.selectOne(feeSummary);
    	}else{
    		//已经发放
    		payStatus = Integer.valueOf(2);
    		feeSummary = new TCFeeSummary();
    		feeSummary.setBizId(StringUtils.join(date));
    		feeSummary = feesummaryService.selectOne(feeSummary);
    	}
    	model.addAttribute("payStatus",payStatus);
    	model.addAttribute("summary",feeSummary);
    	
    /*	String monthTrim = StringUtils.join(new Object[]{date[0],date[1]});
    	FeeSummaryResponse feeSummaryResponse = feeBalanceService.getSummary(monthTrim);
    	if(feeSummaryResponse==null){
    		feeSummaryResponse = new FeeSummaryResponse();
    		feeSummaryResponse.setYear(date[0]);
    		feeSummaryResponse.setMonth(date[1]);
    		feeSummaryResponse.setTotalFeeAmt(BigDecimal.ZERO);
    		feeSummaryResponse.setTotalNumber(0);
    	}
    	model.addAttribute("summary",feeSummaryResponse);
    	model.addAttribute("balanceType", feeBalanceService.isSummary(monthTrim, 1)?1:0);
    	model.addAttribute("payType", feeBalanceService.isSummary(monthTrim, 0)?1:0);*/
    	return "fee/fee-pay";
    }
    
   /* @RequestMapping("feeCalcu")
    @ResponseBody
    @RequestLogging("佣金计算")
    public Object feeCalcu(){
    	try {
    		return feeService.feeBalanceProcess();
		} catch (Exception e) {
			LOGGER.error("feeBalanceProcess Exception",e);
		}
    	return new ResponseResult(false,"计算失败,请联系开发");
    }*/
    
    @RequestMapping("feeDownload")
    @RequestLogging("佣金导出")
    public void feeDownload(HttpServletRequest request, HttpServletResponse response){
    	String month = feeService.getMonth();
    	String date[] =StringUtils.split(month, "-");
    	Map<String, Object> datas = new LinkedHashMap<String, Object>();
    	TCFeePay feePayInfo = new TCFeePay();
    	feePayInfo.setMonth(StringUtils.join(date));
    	
    	List<PayFeeInfoResponse> list = feePayService.payFeeInfoDownload();
    	//List<FeebalanceListResponse> list = feeBalanceService.getFeebalanceListByMonth();
	
		
    	if(CollectionUtils.isEmpty(list)){
    		exportSupport.printMsg("无佣金数据导出!",request,response);
    	}else{
    		datas.put("list", list);
    		datas.put("size", Long.valueOf(list.size()));
        	exportSupport.export(request, response, "lcs/fee/feeList.xls", datas);
    	}
		
    }
    
    
    private Lock lock = new ReentrantLock();
    
    @RequestMapping("prePayFee")
    @ResponseBody
    @RequestLogging("佣金结算")
    public Object prePayFee(@RequestParam("reqPrePayFeeUid") String reqPrePayFeeUid,HttpSession session){
    	if(StringUtils.isBlank(reqPrePayFeeUid))return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
    	lock.lock();
    	try{
    		String prePayFeeUid= (String) session.getAttribute("prePayFeeUid");
        	if(StringUtils.isBlank(prePayFeeUid))return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
    		if(prePayFeeUid.equals(reqPrePayFeeUid)){
        		session.removeAttribute("prePayFeeUid");
        	}else{
        		return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
        	}
    	}catch(Exception e){
    		return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
    	}finally{
    		lock.unlock();
    	}
    	try{
    		User user = (User) session.getAttribute("userInfo");
        	return feePayService.prePayFee(user.getUsername());
    	}catch(Exception e){
    		return new ResponseResult(false,e.getMessage());
    	}
    }
    
    @RequestMapping("feePay")
    @ResponseBody
    @RequestLogging("佣金发放")
    public Object feePay(@RequestParam("reqPayFeeUid") String reqPayFeeUid,HttpSession session){
    	
    	if(StringUtils.isBlank(reqPayFeeUid))return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
    	lock.lock();
    	try{
    		String payFeeUid= (String) session.getAttribute("payFeeUid");
        	if(StringUtils.isBlank(payFeeUid))return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
    		if(payFeeUid.equals(reqPayFeeUid)){
        		session.removeAttribute("payFeeUid");
        	}else{
        		return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
        	}
    	}catch(Exception e){
    		return new ResponseResult(false,"无效请求，请刷新页面重新请求!");
    	}finally{
    		lock.unlock();
    	}
    	try {
    		User user = (User) session.getAttribute("userInfo"); 
    		return feePayService.payFee(user.getUsername());
		} catch (Exception e) {
			return new ResponseResult(false,e.getMessage());
		}
    	
    }
    
}
