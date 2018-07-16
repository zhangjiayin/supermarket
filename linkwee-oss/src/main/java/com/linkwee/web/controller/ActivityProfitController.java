package com.linkwee.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.linkwee.core.constant.Constants;
import com.linkwee.core.datatable.DataInfo;
import com.linkwee.core.datatable.DataResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.ErrorField;
import com.linkwee.core.result.Result;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.interceptors.DateConvertEditor;
import com.linkwee.web.model.ActivityProfit;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.rbac.PermissionSign;
import com.linkwee.web.service.ActivityProfitService;
import com.linkwee.web.service.SystemConfigService;
import com.linkwee.web.service.UsercustomerrelService;
import com.linkwee.web.util.ExcelReader;
import com.xiaoniu.account.domain.SystemInRecordReq;
import com.xiaoniu.account.service.IInRecordAndPayService;
import com.xiaoniu.account.utils.SignUtils;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月24日 14:18:12
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/activityprofit")
public class ActivityProfitController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityProfitController.class);

	@Resource
	private ActivityProfitService activityProfitService;
	
	@Resource
	private UsercustomerrelService usercustomerrelService;
	
	@Resource
	private IInRecordAndPayService p2pInRecordAndPayService;
	
	@Resource
	private SystemConfigService systemConfigService;
	
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
     * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequiresPermissions(value = PermissionSign.SYS_GRAYLIST_ALL)
    public String activityProfit(Model model) {
    	return "activity/activityprofit-list";
    }

    /**
     * datatables的例子<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
	public DataTableReturn getActivityProfits(@RequestParam String  _dt_json) {
		LOGGER.debug("ActivityProfit list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initFOrders();
		DataTableReturn tableReturn = activityProfitService.selectByDatatables(dataTable);
		return tableReturn;
	}


    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public DataResult save(@RequestParam String rows) {
    	DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class); 
    	@SuppressWarnings("unchecked")
		Map<String,ActivityProfit> map =  (Map<String, ActivityProfit>) df.getData();
    	DataResult dr = new DataResult();
    	List<ActivityProfit> datas = new ArrayList<ActivityProfit>();
    	List<ErrorField> errors = new ArrayList<ErrorField>();
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();    
        Validator validator = factory.getValidator();   
        //下面用到bean属性copy，需要对日期进行转换
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dateConverter, java.util.Date.class); 
    	try {
			if(DataInfo.ACTION_CREATE.equals(df.getAction())){
				for (String key : map.keySet()) {
					ActivityProfit r = new ActivityProfit();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<ActivityProfit>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<ActivityProfit> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        }    
					this.activityProfitService.insert(r);
				}
			}
			if(DataInfo.ACTION_EDIT.equals(df.getAction())){
				for (String key : map.keySet()) {
					ActivityProfit r = new ActivityProfit();
					BeanUtils.copyProperties(r, map.get(key));
					datas.add(r);
					Set<ConstraintViolation<ActivityProfit>> constraintViolations = validator.validate(r);    
			        for (ConstraintViolation<ActivityProfit> constraintViolation : constraintViolations) {    
			            errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
			            dr.setFieldErrors(errors);
			            return dr;
			        } 
					this.activityProfitService.update(r);
				}
			}
			if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
				for (String key : map.keySet()) {
					this.activityProfitService.delete(Long.parseLong(key));
				}
			}
		} catch (Exception e) {
			dr.setError(e.getMessage());
		}
    	dr.setData(datas);
    	return dr;
	}
    
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Result upload(@RequestParam MultipartFile file, @RequestParam String rewardName) throws IOException{
		String fileName = file.getOriginalFilename();
		LOGGER.info("用户上传导入数据  文件名=【{}】",fileName);
		String excelType = fileName.substring(fileName.lastIndexOf(".")+1);
		List<Map<String,String>> list = null;
		try {
			list = ExcelReader.readExcel(file.getInputStream(), excelType);
		} catch (Exception e) {
			LOGGER.info("|UPLOAD ExcelReader.readExcel解析异常  文件名=【{}】",fileName);
			e.printStackTrace();
			return new Result(false,500,"EXCEL解析异常！");
		}
		
		int count = 0;
		Double sussessSum = 0.0;
		Double failSum = 0.0;
		Double doubleProfit = 0.0;
		StringBuilder errorMesg = new StringBuilder();
		if(list!=null){
			LOGGER.info("=========发放名单{}===========",list.size());
			for(Map<String,String> obj:list){
				try{
					String mobile = obj.get("手机号码");
					String profit =  obj.get("收益");
					doubleProfit = Double.parseDouble(profit);
					String profitType = "2"; //obj.get("收益类别");
					//String issueTime = obj.get("发放时间");
					String activityCode = "tzfx";//obj.get("活动代码");
					
					//String tradeNo = obj.get("交易编号");
					StringBuilder tradeNo = new StringBuilder();
					tradeNo.append("tzfx-").append(DateUtils.getCurrentShortDateStr()).append("-").append(mobile).append("-").append(profit);
					String charset = "UTF-8";
					String partnerTradeNo = tradeNo.toString();
					Usercustomerrel sale =  usercustomerrelService.queryByMobile(mobile);
					if(sale==null){
						failSum = failSum+doubleProfit;
						errorMesg.append(String.format("该手机号码不存在! 手机号码%s 金额%s \n",mobile,profit));
						continue;
					}
					SystemInRecordReq req = new SystemInRecordReq();
					req.setAmount(new Double((Double.parseDouble(profit)*10000)).longValue());//操作金额 单位毫 (1元=10000毫)
					req.setCharset(charset);//签名编码 UTF-8
					req.setPartnerId(getPartnerId());//业务编号
					req.setBisName("活动奖励");//业务名称
					req.setBisType(14);//充值类型   6:校正, 7:红包返现, 8:系统充值, 9:活动佣金, 10:返利息, 14 : 活动奖励 ，15：朋友红包，16：宝箱竞猜
					req.setRemark(rewardName);
					req.setUserId(sale.getCustomerid());//统一用户ID 买家编号
					req.setUserName(sale.getCustomername());
					req.setPartnerTradeNo(partnerTradeNo);//业务流水
					req.setSign(SignUtils.addSign(req, req.getCharset(), getSignKey()));//签名
					
					ActivityProfit activityProfit = new ActivityProfit();
					activityProfit.setActivityCode(activityCode);//活动编码
					activityProfit.setIssueTime(new Date());//发放日期
					activityProfit.setProfit(new Double(profit));//收益
					activityProfit.setProfitType(profitType);//收益类别:1红包
					activityProfit.setUserNumber(sale.getCustomerid());//理财师编码
					activityProfit.setPartnerTradeNo(partnerTradeNo);//业务流水
			        //系统充值，对资产数据进行操作
					com.xiaoniu.account.domain.result.CommonRlt<Map<String, String>> rlt = p2pInRecordAndPayService.systemInRecord(req);
					LOGGER.debug("req={},rlt={}",req,rlt);
					if(rlt.getReturnCode()==Constants.SUCCESS){//返回码 0：成功
						 String inRecordNo = null;
						 try {
							 inRecordNo = rlt.getData().get("inRecordNo");
							 activityProfit.setInRecordNo(inRecordNo);
							 activityProfit.setCustomerid(sale.getCustomerid());
							 activityProfit.setRemark(rewardName);
							 Integer id = activityProfitService.insert(activityProfit);
							 LOGGER.info("活动添加activityProfitService.addActivityProfit  id={} inRecordNo={}",id,inRecordNo);
							 count++;
							 sussessSum=sussessSum+doubleProfit;
							
						} catch (Exception e) {
							failSum = failSum+doubleProfit;
							LOGGER.error("该笔奖励可能重复发放! 手机号码{} 金额{}",mobile,profit);
							errorMesg.append(String.format("该笔奖励可能重复发放! 手机号码%s 金额%s \n",mobile,profit));
						}
						 
					}else{
						failSum = failSum+doubleProfit;
						errorMesg.append(String.format("该笔奖励发放失败! 手机号码%s 金额%s \n",mobile,profit));
					}
				}catch(Exception e){
					failSum = failSum+doubleProfit;
					LOGGER.error("发放奖励失败：params={},error={}",obj.toString(),e);
					errorMesg.append("发放奖励失败："+obj.toString()+" \n");
				}
		    }
		}
		DecimalFormat df = new DecimalFormat("0.00");
		if(list!=null&&list.size()>0&&list.size()==count){
			LOGGER.info("========发放奖励成功{}============",count);
			return new Result(true,200,String.format("发放奖励成功【%s】笔 ,成功发放金额【%s】", count,df.format(sussessSum)));
		}else{
			LOGGER.info("========发放奖励失败{}==============",list.size()-count);
			return new Result(false,500,String.format("发放奖励成功【%s】笔,成功发放金额【%s】\n发放奖励失败【%s】笔,失败发放金额【%s】\n错误信息:\n【%s】",count,df.format(sussessSum), list.size()-count,df.format(failSum),errorMesg.toString()));
		}
		
	}
	
	
	/**
	  * 获取签名Key
	  * @return
	  */
	 private String getSignKey() {
		String signkey = systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY);
//		String signkey = "fcb059f6bbaf7e0045c12209a491560b";
		return signkey;
	 }
	 
	 private String getPartnerId() {
//		 String partnerId = systemConfigService.getValuesByKey(Constants.account_partnerId);//.getValue(Constants.account_partnerId);
		 String partnerId = "10004";
	     return partnerId;
	 }
	 
	 /**
		 * 下载导入模板
		 * @param response
		 * @param request
		 * @throws FileNotFoundException
		 */
	@RequestMapping(value = "/downloadExcelTemplate")
	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
		LOGGER.info("下载发放奖励Excel模板");
		// 下载本地文件
		String fileName = "activity_profit.xlsx";
		// 读到流中
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF");
		InputStream inStream=null;
		OutputStream outStream=null;
		try {
			inStream = new FileInputStream(path+ "/xls/profit/activity_profit.xlsx");// 文件的存放路径
			response.reset();
			response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
			outStream=response.getOutputStream();
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				outStream.write(b, 0, len);
		} catch (IOException e) {
			LOGGER.error("下载发放奖励Excel模板异常",e);
		}finally{
			try {
				if(inStream!=null){
					inStream.close();
				}
				if(outStream!=null){
					outStream.close();
				}
			} catch (IOException e) {
				LOGGER.error("下载发放奖励Excel模板关闭输入流时出现异常",e);
			}
		}
	}

}
