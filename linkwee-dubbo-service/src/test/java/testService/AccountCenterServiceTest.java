package testService;

import org.junit.Test;

import test.BaseTest;

import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.account.domain.CheckPayPwdReq;
import com.xiaoniu.account.domain.PaySendSmsBindCardReq;
import com.xiaoniu.account.service.IInRecordAndPayService;
import com.xiaoniu.account.service.IPayPasswordSOAService;
import com.xiaoniu.account.service.IPrepare2PayService;

public class AccountCenterServiceTest extends BaseTest {
	
	@Test
	public void getValuesByType() throws Exception{
		IPrepare2PayService iPrepare2PayService = this.getService("IPrepare2PayServiceImpl", IPrepare2PayService.class);
		logger.info(JSONObject.toJSONString(iPrepare2PayService.queryAllProvince()));
	}
	
	@Test
	public void getValuesByType2() throws Exception{
		IPayPasswordSOAService iPayPasswordSOAService = this.getService("IPayPasswordSOAServiceImpl", IPayPasswordSOAService.class);
		CheckPayPwdReq checkPayPwdReq = new CheckPayPwdReq();
		checkPayPwdReq.setUserId("e415ea08-fc2c-4031-b3f0-38e1b449f63a");
		checkPayPwdReq.setPayPassword("111111");
		checkPayPwdReq.setPartnerId("10004");
		logger.info(JSONObject.toJSONString(iPayPasswordSOAService.checkPayPassword(checkPayPwdReq)));
	}
	
	@Test
	public void getValuesByType3() throws Exception{
		IInRecordAndPayService iInRecordAndPayService = this.getService("IInRecordAndPayServiceImpl", IInRecordAndPayService.class);
		
		PaySendSmsBindCardReq paySendSmsBindCardReq = new PaySendSmsBindCardReq();
		paySendSmsBindCardReq.setPartnerId("10004");
		paySendSmsBindCardReq.setUserId("83889096-d52a-4d0d-9c6c-a388d6c12ff4");
		paySendSmsBindCardReq.setUserName("李启");
		paySendSmsBindCardReq.setUserMobile("18617161373");
		paySendSmsBindCardReq.setUserPayAccount("6214836554585774");
//		paySendSmsBindCardReq.setBankCode("6217007200005814390");
		paySendSmsBindCardReq.setPaymentMethod("mobile_app");
		paySendSmsBindCardReq.setPayType("card_front");
		paySendSmsBindCardReq.setItemName("充值");
		paySendSmsBindCardReq.setIdentityCard("421125198907256417");
		paySendSmsBindCardReq.setTotalAmount(Long.parseLong("10000"));
		paySendSmsBindCardReq.setMediaSource("HLC");
		paySendSmsBindCardReq.setClientIp("127.0.0.1");
		paySendSmsBindCardReq.setReturnUrl("http://www.baidu.com");
		paySendSmsBindCardReq.setCharset("UTF-8");
		paySendSmsBindCardReq.setBankCode("cmb");
		
		logger.info(JSONObject.toJSONString(iInRecordAndPayService.directPay(paySendSmsBindCardReq)));
	}
}
