package com.linkwee.web.controller.activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.linkwee.activity.model.ExcelModel;
import com.linkwee.activity.service.RedpacketService;
import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.web.interceptors.DateConvertEditor;
import com.linkwee.web.model.User;
import com.linkwee.web.request.SendRedPacketRequest;
import com.linkwee.web.util.RequestLogging;

/**
 * 导入
 *
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/redpaper")
@RequestLogging("红包管理")
public class ImportController {

	private static final Logger logger = LoggerFactory.getLogger(ImportController.class);

	@Autowired
	private RedpacketService redpacketService;
	private static ExecutorService poolThreadExecutor = Executors.newFixedThreadPool(20);
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
     * 导入理财师信息发送红包
     * @return
     */
    @RequestMapping(value="/importLcs", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("导入理财师信息发放红包")
	public Object importlcs(HttpServletRequest request,HttpSession session) {
    	 final SendRedPacketRequest req = new SendRedPacketRequest();
    	 User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
    	 req.setLoginUserName(user.getUsername()); //设置当前发红包的操作用户
    	 MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
         MultipartFile file  =  multipartRequest.getFile("file");
         String activityId = multipartRequest.getParameter("activityId");
         String redPacketTypeId = multipartRequest.getParameter("redPacketTypeId");
         Integer sendNums = Integer.valueOf(multipartRequest.getParameter("sendNums")); //发送数量
        
         try {
        	 req.setActivityId(activityId);
        	 req.setRedPacketTypeId(redPacketTypeId);
			 InputStream inputStream = file.getInputStream();
			 final List<ExcelModel> datas = PoiImport.dataImport(inputStream, ExcelModel.class);
			 if(datas.isEmpty()){
				 return new ResponseResult(true,"导入的文件为空");
			 }
			 logger.info("importlcsExcel reponse={}", datas);
			 
			 final List<String> errorMsg = new LinkedList<String>();
			 if(sendNums != null && sendNums > 0){
					final CountDownLatch countDownLatch = new CountDownLatch(sendNums);
					for(int i = 0;i<sendNums; i++){
						poolThreadExecutor.execute(new Runnable() {
							@Override
							public void run() {
								try {
									String msg = redpacketService.insertImportSend(req, datas);
									errorMsg.add(msg);
								} catch (Exception e) {
									logger.error("导入理财师红包失败 msg={}", e.getMessage());
								}finally{
									countDownLatch.countDown();
								}
							}
						});
					}
					countDownLatch.await();
					
				}
			 
			 //String msg = StringUtils.join(list, ",");
			 String msg = errorMsg.get(0); //记录失败信息
			 if(StringUtils.isNotBlank(msg)){
				 return new ResponseResult(false,msg);
			 }
			 return new ResponseResult(true,"导入发送红包成功");
		}catch (Exception e) {
			logger.error("导入发送红包失败", e);
			return new ResponseResult(false,"导入发送红包失败"+e.getMessage());
		}
	}
    /**
     * 导入理财师信息发送红包
     * @return
     */
    @RequestMapping(value="/importInvestor", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("导入金服用户信息发放红包")
    public Object importInvestor(HttpServletRequest request,HttpSession session) {
    	final SendRedPacketRequest req = new SendRedPacketRequest();
    	User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
    	req.setLoginUserName(user.getUsername()); //设置当前发红包的操作用户
    	MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
    	MultipartFile file  =  multipartRequest.getFile("file");
    	String activityId = multipartRequest.getParameter("activityId");
    	String redPacketTypeId = multipartRequest.getParameter("redPacketTypeId");
    	Integer sendNums = Integer.valueOf(multipartRequest.getParameter("sendNums")); //发送数量
    	
    	try {
    		req.setActivityId(activityId);
    		req.setRedPacketTypeId(redPacketTypeId);
    		InputStream inputStream = file.getInputStream();
    		final List<ExcelModel> datas = PoiImport.dataImport(inputStream, ExcelModel.class);
    		if(datas.isEmpty()){
    			return new ResponseResult(true,"导入的文件为空");
    		}
    		logger.info("importInvestorExcel reponse={}", datas);
    		
    		final List<String> errorMsg = new LinkedList<String>();
    		if(sendNums != null && sendNums > 0){
    			final CountDownLatch countDownLatch = new CountDownLatch(sendNums);
    			for(int i = 0;i<sendNums; i++){
    				poolThreadExecutor.execute(new Runnable() {
    					@Override
    					public void run() {
    						try {
    							String msg = redpacketService.insertImportSendInvester(req, datas);
    							errorMsg.add(msg);
    						} catch (Exception e) {
    							logger.error("导入金服红包失败 msg={}", e.getMessage());
    						}finally{
    							countDownLatch.countDown();
    						}
    					}
    				});
    			}
    			countDownLatch.await();
    			
    		}
    		
    		String msg = errorMsg.get(0); //记录失败信息
    		if(StringUtils.isNotBlank(msg)){
    			return new ResponseResult(false,msg);
    		}
    		return new ResponseResult(true,"导入发送红包成功");
    	}catch (Exception e) {
    		logger.error("导入发送红包失败", e);
    		return new ResponseResult(false,"导入发送红包失败"+e.getMessage());
    	}
    }


    /**
	 * 下载导入模板
	 * @param response
	 * @param request
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "/downloadImportTemplate")
	public void downloadImportTemplate(HttpServletResponse response,HttpServletRequest request) {
		logger.info("下载导入发放红包模板");
		// 下载本地文件
		String fileName = "sendredpaper.xls"; // 文件的默认保存名letter
		// 读到流中
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF");
		InputStream inStream=null;
		OutputStream outStream=null;
		try {
			inStream = new FileInputStream(path+ "/template/sendredpaper.xls");// 文件的存放路径
			response.reset();
			/*设置头信息以及编码*/
			response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			/*设置下载时的文件名*/
			response.addHeader("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
			outStream=response.getOutputStream();
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				outStream.write(b, 0, len);
		} catch (IOException e) {
			logger.error("下载导入模板出现异常",e);
		}finally{
			try {
				if(inStream!=null){
					inStream.close();
				}
				if(outStream!=null){
					outStream.close();
				}
			} catch (IOException e) {
				logger.error("下载导入模板关闭输入流时出现异常",e);
			}
		}
	}

}
