package com.linkwee.api.controller.mc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.response.MorningPaperResponse;
import com.linkwee.api.response.mc.ClassroomPageListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.mc.Classroom;
import com.linkwee.web.model.share.ShareContent;
import com.linkwee.web.model.vo.SingleImgInfo;
import com.linkwee.web.service.ClassroomService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.helper.CommonHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.QRCodeUtil;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： ClassroomController控制器
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月03日 11:39:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/classroom")
@RequestLogging("ClassroomController控制器")
public class ClassroomController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomController.class);
	
	private String errorCode = "-1";
	
	@Resource
    private JsonWebTokenHepler jsonWebTokenHepler;

	@Resource
	private ClassroomService classroomService;
	
	@Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@Resource
	private CrmCfplannerService cfplannerService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private CommonHelper commonHelper;
	
	@Resource
	private SysConfigService sysConfigService;
	
	/**
	 * 课程列表(classroom.queryClassRoomList)
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/queryClassroomList")
	@RequestLogging("课程列表")
	@ResponseBody
	public BaseResponse queryClassroomList(@Valid PaginatorRequest req,BindingResult result,AppRequestHead head) throws Exception {
		if (AppResponseUtil.existsParamsError(result)) {
			return AppResponseUtil.getErrorParams(result);
		}
		Map<String,Object> conditions = new HashMap<String, Object>(); //筛选条件
		Page<Classroom> page  = new Page<Classroom>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<Classroom> datas =  null;
		try {
			datas = classroomService.queryClassroomList(page,conditions);
		} catch (Exception e) {
			LOGGER.error("查询课程列表异常", e);
			return  new BaseResponse(errorCode,"查询课程列表失败");
		}
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(StringUtils.isNotBlank(userId) && !userId.equals("undefined")){
			sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_CLASSROOM_READED, userId,appType);
		}
		return AppResponseUtil.getSuccessResponse(datas,ClassroomPageListResponse.class);
	}
	
	/**
	 * 课程详情(classroom.queryClassRoomDetail)
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryClassRoomDetail")
	@RequestLogging("课程详情")
	@ResponseBody
	public BaseResponse queryClassRoomDetail(String id,HttpServletResponse response) throws Exception {
		Classroom classRoom =  null;
		try {
			classRoom = classroomService.selectById(id);
		} catch (Exception e) {
			LOGGER.error("查询课程列表异常", e);
			return  new BaseResponse(errorCode,"查询课程列表失败");
		}
		return AppResponseUtil.getSuccessResponse(classRoom,ClassroomPageListResponse.class);
	}
	
	/**
	 * 精选推荐列表
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("/selectedRecomend/list/4.5.0")
	@RequestLogging("精选推荐列表")
	@ResponseBody
	public BaseResponse selectedRecomendList(AppRequestHead head) throws Exception {
		List<Classroom> selectedRecomendList = classroomService.selectedRecomendList();
		for(Classroom classroom : selectedRecomendList){
			classroom.setContent("");
		}
		return AppResponseUtil.getSuccessResponse(selectedRecomendList);
	}
	
	/**
	 * 精选推荐详情
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectedRecomend/detail/4.5.0")
	@RequestLogging("精选推荐详情")
	@ResponseBody
	public BaseResponse selectedRecomendDetail(String id,HttpServletResponse response) throws Exception {
		Classroom classRoom =  null;
		try {
			classRoom = classroomService.selectById(id);
		} catch (Exception e) {
			LOGGER.error("查询课程列表异常", e);
			return  new BaseResponse(errorCode,"查询课程列表失败");
		}
		return AppResponseUtil.getSuccessResponse(classRoom);
	}
	
	/**
	 * 每日早报
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/morningPaper/4.5.0")
	@RequestLogging("每日早报")
	@ResponseBody
	public BaseResponse morningPaper(AppRequestHead head,HttpServletRequest request) throws Exception {
		if(StringUtils.isBlank(head.getToken())){
			return  new BaseResponse(errorCode,"token不能为空");
		}
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmCfplanner cfplanner = cfplannerService.queryCfplannerInfo(userId);
		MorningPaperResponse resultResp = new MorningPaperResponse();
		Classroom morningPaper =  classroomService.queryMorningPaper();
		if(cfplanner != null){
			resultResp.setHeadImg(cfplanner.getHeadImage());
			resultResp.setUserName(cfplanner.getUserName());			
			String qrcode = cfplanner.getQrcode();				
			if(StringUtils.isBlank(cfplanner.getQrcode())){
				CrmUserInfo crmUserInfo = crmUserInfoService.queryUserInfoByUserId(userId);		
				ShareContent shareContent = commonHelper.getWechatShareRclcs(crmUserInfo.getMobile(), crmUserInfo.getUserName());
				org.springframework.core.io.Resource res = new ClassPathResource("icons/lcs.jpg");
				String logoPath = res.getFile().getPath();
				qrcode = creatRcCode(request,logoPath,shareContent.getShareLink());	
				cfplannerService.updateCfpQrByUserId(userId,qrcode);
			}else{
				qrcode = cfplanner.getQrcode();
			}
			resultResp.setQrcode(qrcode);
		}	
		resultResp.setMorningPaper(morningPaper);
		return AppResponseUtil.getSuccessResponse(resultResp);
	}
	
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
	
	private String creatRcCode(HttpServletRequest request,String logoPath,String content){
		CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
			QRCodeUtil.createQR(outStream,logoPath,content);			
			//准备上传图片数据
            byte[] buffer = null;              
            outStream.close();  
            buffer = outStream.toByteArray(); 
          //开始上传
            HttpPost httppost = new HttpPost(sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL));
            
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(buffer);
            httppost.setEntity(byteArrayEntity);
            httppost.addHeader("Content-Type", "jpeg");
            
            LOGGER.debug("executing request " + httppost.getRequestLine());
            response = httpclient.execute(httppost);
            
            LOGGER.debug("----------------Response-----------------------");
            LOGGER.debug(response.getStatusLine().toString());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String resp = EntityUtils.toString(resEntity);
                LOGGER.debug("Response content length: " + resEntity.getContentLength());
                LOGGER.debug("Response content : " + resp);
                SingleImgInfo o = JsonUtils.fromJsonToObject(resp, SingleImgInfo.class);
                if(o==null || o.getInfo()==null){
                    return null;
                }
                return  o.getInfo().getMd5();
            }
            EntityUtils.consume(resEntity);
		} catch (Exception e) {
			LOGGER.error("生成图片失败!",e);
		}finally {
            try {
                if(response!=null){
                    response.close();
                    response = null;
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
	}
	
}
