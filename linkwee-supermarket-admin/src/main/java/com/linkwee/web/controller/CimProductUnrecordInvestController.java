package com.linkwee.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrgFeeStrategyA;
import com.linkwee.web.model.CimProductUnrecordInvest;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.OrgFeeStrategyAResponse;
import com.linkwee.web.model.User;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.model.cim.CimProductDataTable;
import com.linkwee.web.model.cim.CimProductPayImport;
import com.linkwee.web.service.CimOrgFeeStrategyAService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductUnrecordInvestService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimProductUnrecordInvestController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年06月19日 14:44:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "cim/cimproductunrecordinvest")
@RequestLogging("CimProductUnrecordInvestController控制器")
public class CimProductUnrecordInvestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductUnrecordInvestController.class);

	@Resource
	private CimProductUnrecordInvestService cimProductUnrecordInvestService;
	
	@Resource
	private CrmInvestorService userInfoService;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Resource
	private CimOrgFeeStrategyAService  cimOrgFeeStrategyAService;
	
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

    /**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String cimProductUnrecordInvest(Model model) {
    	return "cimproductunrecordinvest/cimproductunrecordinvest-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getCimProductUnrecordInvests(@RequestParam String  _dt_json) {
		LOGGER.debug("CimProductUnrecordInvest list _dt_json={}", _dt_json);
		CimProductDataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, CimProductDataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = cimProductUnrecordInvestService.selectByDatatables(dataTable);
		return tableReturn;
	}
    
    @RequestMapping(value="/payAudit")
    @ResponseBody
    public Object payAudit(String tableId,HttpSession session) {
    	ResponseResult result = null;
		
		LOGGER.info("管理后台-返现投资-批量返现, tableId={}",tableId);
		User user = (User) session.getAttribute("userInfo");
		String[] idslit = tableId.split(",");
		List<String> listStr = new ArrayList<String>();
		for(int i=0;i<idslit.length;i++){
			listStr.add(idslit[i]);
		}
		try {
			cimProductUnrecordInvestService.payAudit(listStr,user.getUsername());
		} catch (Exception e) {
			result = new ResponseResult(false, "批量发放返现失败");
		}
		result = new ResponseResult(true, "批量发放返现成功");
		return result;
    }
    
    /**
     * 录入数据页面
     */
    @RequestMapping(value="importPage")
    public String importPage(Model model,String salesOrgId) {
    	return "cimproductunrecordinvest/importPage";
    }
    
    /**
     * 录入数据页面
     */
    @RequestMapping(value="logs")
    public String logs(Model model,String salesOrgId) {
    	return "cimproductunrecordinvest/logs-list";
    }
    
    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/logslist", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn logslist(@RequestParam String  _dt_json) {
		LOGGER.debug("CimProductUnrecordInvest logslist _dt_json={}", _dt_json);
		CimProductDataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, CimProductDataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = cimProductUnrecordInvestService.selectByDatatablesLogs(dataTable);
		return tableReturn;
	}
    
    /**
	 * 发放返现
	 * @return
	 */
	@RequestMapping("/payById")
	@ResponseBody
	public Object del(@RequestParam String dataId,HttpSession session){
		ResponseResult result = null;
		User user = (User) session.getAttribute("userInfo");
		try {
			cimProductUnrecordInvestService.payById(dataId,user.getUsername());
		} catch (Exception e) {
			result = new ResponseResult(false, "发放返现失败");
		}
		result = new ResponseResult(true, "发放返现成功");
		return result;
	}

    @RequestMapping(value="importRewardData")
    @ResponseBody
    @RequestLogging("发放报单数据数据")
	public Object importRewardData(HttpServletRequest request,HttpSession session) {   
    	try {
    		User user = (User) session.getAttribute("userInfo");
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request; 
    		String fileName = multipartRequest.getParameter("fileName");
    		String fileRemark = multipartRequest.getParameter("fileRemark");
            MultipartFile file  =  multipartRequest.getFile("file");
            Set<String> msg = null;
            InputStream inputStream = file.getInputStream();
            List<CimProductPayImport>  rechargeLimitList = PoiImport.dataImport(inputStream, CimProductPayImport.class);
            if(rechargeLimitList == null || rechargeLimitList.size() == 0) {
            	return new ResponseResult(false,"导入失败，数据为空");
            }
            
            List<CimProductUnrecordInvest> insertList =  new ArrayList<CimProductUnrecordInvest>();
            String batchNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            for(CimProductPayImport pay : rechargeLimitList){
            	CimProductUnrecordInvest invest = new CimProductUnrecordInvest();
            	CrmInvestor userInfo = null;
            	if(pay.getMobile().length()>=10){
            		userInfo = userInfoService.queryInvestorByMobile(pay.getMobile());
            	}
            	CimOrginfoWeb org = cimOrginfoService.selectOrgInfoByOrgName(pay.getOrgNumber());
            	if(org==null) return new ResponseResult(false, pay.getOrgNumber()+"机构名称有误,A专区没有对应的机构");
            	invest.setPlatfrom(org.getOrgNumber());
    			if(userInfo==null) return new ResponseResult(false, pay.getMobile()+"手机号码有误,没有相关投资用户");
    			invest.setUserId(userInfo.getUserId());
    			invest.setCfplannerId(userInfo.getCfplanner());
    			invest.setUserName(userInfo.getUserName());
    			invest.setUserMobile(pay.getMobile());
    			CimOrgFeeStrategyA a = new CimOrgFeeStrategyA();
    			a.setOrgNumber(org.getOrgNumber());
    			List<CimOrgFeeStrategyA> feeCalculateStrategyList = cimOrgFeeStrategyAService.selectListByCondition(a);
    			BigDecimal returnCashAmount = new BigDecimal("0");
    			if(feeCalculateStrategyList != null && feeCalculateStrategyList.size() > 0){
    				for(CimOrgFeeStrategyA feeStrategy : feeCalculateStrategyList){
    					invest.setFeeStrategy(feeStrategy.getFeeStrategy());
    					invest.setFeeRatio(feeStrategy.getFeeRatio());
    					if(feeStrategy.getFeeStrategy() == 1){
    						returnCashAmount = feeStrategy.getFeeVal();
    						invest.setFeeRatio(feeStrategy.getFeeVal());
    					}else if(feeStrategy.getFeeStrategy() == 2){
    						returnCashAmount = pay.getInvestAmt().multiply(feeStrategy.getFeeRatio()).divide(new BigDecimal(100));
    					}else if(feeStrategy.getFeeStrategy() == 3){
    						if(pay.getProductDeadLineValue().compareTo(feeStrategy.getIntervalMinVal().toBigInteger().intValue()) >= 0 && pay.getProductDeadLineValue().compareTo(feeStrategy.getIntervalMaxVal().toBigInteger().intValue()) <= 0){
    							returnCashAmount = pay.getInvestAmt().multiply(feeStrategy.getFeeRatio()).divide(new BigDecimal(100));
    						}
    					}
    				}
    			}
            	invest.setFeeAmt(returnCashAmount);
            	invest.setInvestAmt(pay.getInvestAmt());
            	invest.setInvestImg(" ");
            	invest.setPayStatus(1);
            	invest.setCreateTime(new Date());
            	invest.setUpdateTime(new Date());
            	invest.setOperator(user.getUsername());
            	invest.setPlatfromName(pay.getOrgNumber());
            	invest.setProductDeadLineValue(pay.getProductDeadLineValue());
            	invest.setProductDeadLine(pay.getProductDeadLineValue()+"天");
            	invest.setUploadBatchNo(batchNo);
            	invest.setInvestTime(pay.getInvestTime());
            	invest.setInvestId(StringUtils.getUUID());
            	invest.setStatus(0);
            	invest.setShareStatus(0);
            	invest.setUploadTitle(fileName);
            	invest.setUploadTime(new Date());
            	invest.setUploadRemark(fileRemark);
            	insertList.add(invest);
            }
            cimProductUnrecordInvestService.batchInsert(insertList);
            return new ResponseResult(true, "导入成功", msg);
		}catch (ServiceException e) {
			LOGGER.error("import raward data exception : {}", e.getMessage());
			return new ResponseResult(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("import raward data exception : {}", e.getMessage());
		}
    	return new ResponseResult(false, "导入失败");
	}
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    
    
    /**
   	 * 下载导入模板
   	 * @param response
   	 * @param request
   	 * @throws FileNotFoundException
   	 */
   	@RequestMapping(value = "/downloadExcelTemplate")
   	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
   		LOGGER.info("下载销售机构导入理财师Excel模板");
   		// 下载本地文件
   		String fileName = "pay_data.xls";
   		// 读到流中
   		String path = request.getSession().getServletContext().getRealPath("/WEB-INF");
   		InputStream inStream=null;
   		OutputStream outStream=null;
   		try {
   			inStream = new FileInputStream(path+ "/xls/acc/pay_data.xls");// 文件的存放路径
   			response.reset();
   			response.setContentType("multipart/form-data");
   			response.setCharacterEncoding("UTF-8");
   			response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(), "ISO8859-1") + "\"");
   			outStream=response.getOutputStream();
   			byte[] b = new byte[100];
   			int len;
   			while ((len = inStream.read(b)) > 0)
   				outStream.write(b, 0, len);
   		} catch (IOException e) {
   			LOGGER.error("下载奖励发放Excel模板异常",e);
   		}finally{
   			try {
   				if(inStream!=null){
   					inStream.close();
   				}
   				if(outStream!=null){
   					outStream.close();
   				}
   			} catch (IOException e) {
   				LOGGER.error("下载奖励发放Excel模板关闭输入流时出现异常",e);
   			}
   		}
   	}
	
}
