package testService;

import org.junit.Test;

import test.BaseTest;

import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.sms.req.BaseReq;
import com.xiaoniu.sms.req.GenerateCodeReq;
import com.xiaoniu.sms.service.IRandomCodeService;

public class SmsCenterServiceTest extends BaseTest{
	
	@Test
	public void getValuesByType(){
		IRandomCodeService iRandomCodeService = this.getService("IRandomCodeServiceImpl", IRandomCodeService.class);
		GenerateCodeReq generateCodeReq = new GenerateCodeReq();
		generateCodeReq.setMobile("18617161373");
		generateCodeReq.setCount(6);
		generateCodeReq.setSend(1);
		
		addBaseProperties(generateCodeReq);
        
		logger.info(JSONObject.toJSONString(iRandomCodeService.generateCode(generateCodeReq)));
	}
	
	/**
	 * 添加系统参数和签名
	 */
	public void addBaseProperties(BaseReq baseReq){
		baseReq.setIp("127.0.0.1");
		baseReq.setModuleId("RESETLOGIN");
		baseReq.setPartnerId("LHLC");
	}
}
