package com.linkwee.web.controller.acc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.User;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.request.act.AddFeeCouponInfoRequest;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

 /**
 * 
 * @描述： ActJobGradeVoucherController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月27日 18:00:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "acc/actjobgradevoucher")
@RequestLogging("ActJobGradeVoucherController控制器")
public class ActJobGradeVoucherController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActJobGradeVoucherController.class);

	@Resource
	private ActJobGradeVoucherService actJobGradeVoucherService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
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
    public String actJobGradeVoucher(Model model) {
    	return "actjobgradevoucher/actjobgradevoucher-list";
    }

    /**
     * datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看列表")
	public DataTableReturn getActJobGradeVouchers(@RequestParam String  _dt_json) {
		LOGGER.debug("ActJobGradeVoucher list _dt_json={}", _dt_json);
		DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = actJobGradeVoucherService.selectByDatatables(dataTable);
		return tableReturn;
	}


    /**
     * 添加页面
     */
    @RequestMapping(value="addPage")
    public String getAddFeeCouponAddPage(Model model) {
    	return "actjobgradevoucher/actjobgradevoucher-add";
    }
    
    
    /**
     * 修改页面
     */
    @RequestMapping(value="{voucherId}/editPage")
    public String getAddFeeCouponEditPage(@PathVariable("voucherId")String voucherId,Model model) {
    	try {
    		if(StringUtils.isNotBlank(voucherId)){
    			ActAddFeeCoupon temp = new ActAddFeeCoupon();
    			temp.setCouponId(voucherId);
//    			temp = actAddFeeCouponService.selectOne(temp);
    			CimOrginfo req = new CimOrginfo();
    	  		req.setStatus(1);
//    			model.addAttribute("platformList",cimOrginfoService.selectListByCondition(req));
    			model.addAttribute("voucherId", voucherId);
    			model.addAttribute("addFeeCoupon", temp);
    		}
			
		}catch (ServiceException e) {
			LOGGER.error("actjobgradevoucherEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs",e.getMessage());
		}  catch (Exception e) {
			LOGGER.error("actjobgradevoucherEdit exception : {}", e.getMessage());
			model.addAttribute("errorMgs","查询职级体验券失败");
		}
    	return "actjobgradevoucher/actjobgradevoucher-edit";
    }
    
    @RequestMapping(value="edit")
    @ResponseBody
    @RequestLogging("编辑职级体验券")
	public Object redpacketEdit(@Valid AddFeeCouponInfoRequest addFeeCouponInfo,BindingResult bindResult,HttpSession session) {
    	if(ResponseUtil.existsParamsError(bindResult)) {
   	    	return ResponseUtil.getErrorParams(bindResult);
        }
    	try {
    		if(StringUtils.isBlank(addFeeCouponInfo.getCouponId()))return new ResponseResult(true,"不存在的职级体验券");  		
			User user = (User) session.getAttribute("userInfo"); 
			addFeeCouponInfo.setOperator(user.getUsername());
//			actAddFeeCouponService.updateAddFeeCoupon(addFeeCouponInfo);
			return new ResponseResult(true,"更新成功");
		}catch (ServiceException e) {
			LOGGER.error("addFeeCouponEdit exception : {}", e.getMessage());
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			LOGGER.error("addFeeCouponEdit exception : {}", e.getMessage());
		}
    	return new ResponseResult(false,"更新失败");
	}
    
    /**
   	 * 职级体验券下载导入模板
   	 * @param response
   	 * @param request
   	 * @throws FileNotFoundException
   	 */
   	@RequestMapping(value = "/downloadExcelTemplate")
   	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
   		LOGGER.info("职级体验券导入Excel模板");
   		// 下载本地文件
   		String fileName = "jobGradeVoucher.xls";
   		// 读到流中
   		String path = request.getSession().getServletContext().getRealPath("/WEB-INF");
   		InputStream inStream=null;
   		OutputStream outStream=null;
   		try {
   			inStream = new FileInputStream(path+ "/xls/acc/jobGradeVoucher.xls");
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
   			LOGGER.error("职级体验券下载Excel模板异常",e);
   		}finally{
   			try {
   				if(inStream!=null){
   					inStream.close();
   				}
   				if(outStream!=null){
   					outStream.close();
   				}
   			} catch (IOException e) {
   				LOGGER.error("职级体验券下载Excel模板关闭输入流时出现异常",e);
   			}
   		}
   	}
    
    @RequestMapping(value="importJobGradeVoucherData")
    @ResponseBody
    @RequestLogging("录入批量职级数据")
	public Object importJobGradeVoucherData(HttpServletRequest request,HttpSession session) {   
    	try {
    		User user = (User) session.getAttribute("userInfo"); 
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
            MultipartFile file  =  multipartRequest.getFile("file");
            String activityAttr = multipartRequest.getParameter("activityAttr");
    		String jobGrade = multipartRequest.getParameter("jobGrade");
    		String useTime = multipartRequest.getParameter("useTime");
    		String expiresTime = multipartRequest.getParameter("expiresTime");
    		Date useTimeDate = DateUtils.parse(useTime+" 00:00:00",DateUtils.FORMAT_LONG);
    		Date expiresTimeDate = DateUtils.parse(expiresTime+" 23:59:59",DateUtils.FORMAT_LONG);
            InputStream inputStream = file.getInputStream();
            List<ActJobGradeVoucher>  gradeList = PoiImport.dataImport(inputStream, ActJobGradeVoucher.class);
            if(gradeList == null || gradeList.size() == 0) {
            	return new ResponseResult(false,"导入失败，数据为空");
            }
            CrmCfplanner cfpInfo = null;
			List<ActJobGradeVoucher>  gradeInsertList = new ArrayList<ActJobGradeVoucher>();
            for(ActJobGradeVoucher grade : gradeList) {
            	String mobile = grade.getMobile();
				if(mobile == null || mobile.length() != 11){
					return new ResponseResult(false,"【 " + mobile + " 】手机号码长度不对");
				}
				cfpInfo = crmCfplannerService.queryCfplannerByMobile(mobile);
				if(cfpInfo == null){
					return new ResponseResult(false,"【 " + mobile + " 】用户没有注册理财师");
				}
				grade.setVoucherId(StringUtils.getUUID()); 
				grade.setActivityAttr(activityAttr);
				grade.setUserId(cfpInfo.getUserId());
				grade.setStatus(1);
				String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
				String payMonth = new SimpleDateFormat("yyyy-MM").format(useTimeDate);
				if(CfpJobGradeEnum.getCfpJobGradeEnumByKey(jobGrade).getLevelWeight()<=CfpJobGradeEnum.getCfpJobGradeEnumByKey(cfpInfo.getJobGrade()).getLevelWeight()
						&&thisMonth.equals(payMonth)){
					grade.setStatus(4);//发放的职级没有用户当前的职级大,直接设为已失效
				}
				grade.setJobGrade(jobGrade);
				grade.setJobGradeWeight(CfpJobGradeEnum.getCfpJobGradeEnumByKey(jobGrade).getLevelWeight());
				grade.setCurJobGrade(cfpInfo.getJobGrade());
				grade.setUseTime(useTimeDate);
				grade.setExpiresTime(expiresTimeDate);
				grade.setCreateTime(new Date());
				grade.setUpdateTime(new Date());
				grade.setOperator(user.getUsername());
				gradeInsertList.add(grade);
            }
            actJobGradeVoucherService.insertJobGradeVoucherList(gradeInsertList);
		}catch (ServiceException e) {
			LOGGER.error("importJobGradeVoucherData exception : {}", e.getMessage());
			return new ResponseResult(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("importJobGradeVoucherData exception : {}", e.getMessage());
		}
    	return new ResponseResult(true, "批量调整成功");
	}
	
}
