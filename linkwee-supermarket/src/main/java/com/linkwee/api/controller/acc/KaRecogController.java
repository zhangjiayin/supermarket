package com.linkwee.api.controller.acc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.linkwee.api.request.acc.AcSaveBind;
import com.linkwee.api.request.acc.InputVcodeRequest;
import com.linkwee.api.response.acc.BankCardRecoReponse;
import com.linkwee.api.response.acc.IdCardRecoReponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.plugins.baidu.aip.ocr.AipOcr;
import com.linkwee.web.enums.KaRecogCodeEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.AcSaveBindService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;


@Controller
@RequestMapping(value = "/kareco")
public class KaRecogController{
	 private static final Logger LOGGER = LoggerFactory.getLogger(KaRecogController.class);
	 
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private AcSaveBindService acSaveBindService;
	
	@RequestMapping(value="/idcardReco")
	@ResponseBody
	public BaseResponse idcardReco(HttpServletRequest request) {   
		IdCardRecoReponse rlt = new IdCardRecoReponse();
    	try {
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
            MultipartFile file  =  multipartRequest.getFile("file");
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("detect_direction", "true");
            options.put("detect_risk", "true");
            String APP_ID = sysConfigService.getValuesByKey("BAIDU_APP_ID");
            String API_KEY = sysConfigService.getValuesByKey("BAIDU_API_KEY");	
            String SECRET_KEY = sysConfigService.getValuesByKey("BAIDU_SECRET_KEY");	
            AipOcr client = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
    		// 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(5000);
            client.setSocketTimeoutInMillis(60000);
            JSONObject res = client.idcard(file.getBytes(), "front", options);
            try {
            	Object word = res.get("words_result");
            	Object idword = ((JSONObject) word).get("公民身份号码");
            	Object idno = ((JSONObject) idword).get("words");
            	rlt.setIdCard(idno.toString());
            	Object nameword = ((JSONObject) word).get("姓名");
            	Object name = ((JSONObject) nameword).get("words");
            	rlt.setName(name.toString());
//            	LOGGER.info("身份证自动识别接口,身份证号码【{}】",idno); 
//            	LOGGER.info("身份证自动识别接口,姓名【{}】",name);
            	
			} catch (Exception e) {
				Object errorcode = res.get("error_code");
				String errorMsg = null;
				try {
					errorMsg = EnumUtils.getValueByKey(Integer.parseInt(errorcode.toString()),KaRecogCodeEnum.values());
				} catch (Exception e2) {
				}
				rlt.setErrorCode(errorcode.toString());
				rlt.setErrorMsg(errorMsg);
			}
            
            return AppResponseUtil.getSuccessResponse(rlt);
		}catch (ServiceException e) {
			e.printStackTrace();
			LOGGER.error("idcardReco data ServiceException : {}", e.getMessage());
			rlt.setErrorCode("10001");
			rlt.setErrorMsg("身份证上传异常,请重试!");
			return AppResponseUtil.getSuccessResponse(rlt);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("idcardReco data exception : {}", e.getMessage());
			rlt.setErrorCode("10002");
			rlt.setErrorMsg("身份证上传异常,请重试!");
			return AppResponseUtil.getSuccessResponse(rlt);
		}
    	
	}
	
	@RequestMapping(value="/bankcardReco")
	@ResponseBody
	public BaseResponse bankcardReco(HttpServletRequest request) {   
		BankCardRecoReponse rlt = new BankCardRecoReponse();
    	try {
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
            MultipartFile file  =  multipartRequest.getFile("file");
            String APP_ID = sysConfigService.getValuesByKey("BAIDU_APP_ID");
            String API_KEY = sysConfigService.getValuesByKey("BAIDU_API_KEY");	
            String SECRET_KEY = sysConfigService.getValuesByKey("BAIDU_SECRET_KEY");	
            AipOcr client = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
    		// 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(5000);
            client.setSocketTimeoutInMillis(60000);
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("detect_direction", "true");
            options.put("detect_risk", "true");
            JSONObject res = client.bankcard(file.getBytes(),options);
            try {
            	Object word = res.get("result");
            	Object bankCardNumber = ((JSONObject) word).get("bank_card_number");
            	rlt.setBankCard(bankCardNumber.toString().replace(" ", ""));
//            	LOGGER.info("银行卡自动识别接口,银行卡号码【{}】",bankCardNumber); 
			} catch (Exception e) {
				Object errorcode = res.get("error_code");
				String errorMsg = null;
				try {
					errorMsg = EnumUtils.getValueByKey(Integer.parseInt(errorcode.toString()),KaRecogCodeEnum.values());
				} catch (Exception e2) {
				}
				rlt.setErrorCode(errorcode.toString());
				rlt.setErrorMsg(errorMsg);
			}
            return AppResponseUtil.getSuccessResponse(rlt);
            
		}catch (ServiceException e) {
			e.printStackTrace();
			LOGGER.error("bankcardReco data exception : {}", e.getMessage());
			rlt.setErrorCode("10001");
			rlt.setErrorMsg("银行卡上传异常,请重试!");
			return AppResponseUtil.getSuccessResponse(rlt);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("bankcardReco data exception : {}", e.getMessage());
			rlt.setErrorCode("10002");
			rlt.setErrorMsg("银行卡上传异常,请重试!");
			return AppResponseUtil.getSuccessResponse(rlt);
		}
	}
	
	@RequestMapping(value="/baseReco")
	@ResponseBody
	public BaseResponse baseReco(HttpServletRequest request) {   
		BankCardRecoReponse rlt = new BankCardRecoReponse();
		String responerrorCode = "";
    	try {
    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
            MultipartFile file  =  multipartRequest.getFile("file");
            String APP_ID = sysConfigService.getValuesByKey("BAIDU_APP_ID");
            String API_KEY = sysConfigService.getValuesByKey("BAIDU_API_KEY");	
            String SECRET_KEY = sysConfigService.getValuesByKey("BAIDU_SECRET_KEY");	
            AipOcr client = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
    		// 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(5000);
            client.setSocketTimeoutInMillis(60000);
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("detect_direction", "true");
            options.put("detect_risk", "true");
            JSONObject res = client.general(file.getBytes(),options);
            try {
            	Object word = res.get("words_result");
            	String xmString = "";  
                xmString = new String(word.toString());  
                if(word!=null&&word.toString().length()>422){
                	rlt.setBankCard(xmString.substring(404,422));
                	LOGGER.info("解析到身份证内容:【{}】",xmString.substring(404,422));
                }else{
                	rlt.setBankCard(word.toString());
                	LOGGER.info("没有解析到身份证,返回:【{}】", word.toString()) ; 
                }
			} catch (Exception e) {
				Object errorcode = res.get("error_code");
				String errorMsg = null;
				try {
					errorMsg = EnumUtils.getValueByKey(Integer.parseInt(errorcode.toString()),KaRecogCodeEnum.values());
				} catch (Exception e2) {
				}
				rlt.setErrorCode(errorcode.toString());
				rlt.setErrorMsg(errorMsg);
			}
            return AppResponseUtil.getSuccessResponse(rlt);
            
		}catch (ServiceException e) {
			e.printStackTrace();
			LOGGER.error("baseReco data exception : {}", e.getMessage());
			rlt.setErrorCode(responerrorCode);
			rlt.setErrorMsg("图文识别解析异常,请重试!");
			return AppResponseUtil.getSuccessResponse(rlt);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("baseReco data exception : {}", e.getMessage());
			rlt.setErrorCode(responerrorCode);
			rlt.setErrorMsg("图文识别解析异常,请重试!");
			return AppResponseUtil.getSuccessResponse(rlt);
		}
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
	  
	@RequestMapping("/saveBankCard")
	@ResponseBody
	public BaseResponse saveBankCard(AcSaveBind req,AppRequestHead head) throws Exception {
		AcSaveBind bind = new AcSaveBind();
		bind.setMobile(req.getMobile());
		List<AcSaveBind> list = acSaveBindService.selectListByCondition(bind);
		if(list.size()>0){
			acSaveBindService.update(req);
		}else{
			acSaveBindService.saveBankCard(req);
		}
		return AppResponseUtil.getSuccessResponse();
     }
	
	@RequestMapping("/getBankCardInfo")
	@ResponseBody
	public BaseResponse getBankCardInfo(AcSaveBind req) {
		AcSaveBind rlt = acSaveBindService.selectOne(req);
		return AppResponseUtil.getSuccessResponse(rlt);
	}
	
	
	@RequestMapping("/saveAddress")
	@ResponseBody
	public BaseResponse saveAddress(AcSaveBind req,AppRequestHead head) throws Exception {
		acSaveBindService.update(req);
		return AppResponseUtil.getSuccessResponse();
     }
	
	@RequestMapping("/isExist")
	@ResponseBody
	public BaseResponse isExist(AcSaveBind req,AppRequestHead head) throws Exception {
		AcSaveBind bind = new AcSaveBind();
		bind.setMobile(req.getMobile());
		List<AcSaveBind> list = acSaveBindService.selectListByCondition(bind);
		if(list.size()>0){
			return new ErrorResponse("-1","该手机号码已经注册!");
		}
		return AppResponseUtil.getSuccessResponse();
     }
	
	
	@RequestMapping("/inputVcode")
	@ResponseBody
	public BaseResponse verifyVcode(InputVcodeRequest req) throws Exception {
		boolean flag = false;
		try {
			if(req!=null&&req.getType()==0){
				flag = accountbindService.checkVerifyCode2(req.getMobile(),req.getVcode());
			}else{
				flag = accountbindService.checkVerifyCode3(req.getMobile(),req.getVcode());
			}
		} catch (Exception e) {
			return AppResponseUtil.getErrorBusi("send_error","校验验证码异常");
		}
		if(!flag){
			return AppResponseUtil.getErrorBusi("send_error","验证码不正确");
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "true");
		return AppResponseUtil.getSuccessResponse(map);
	}
}
