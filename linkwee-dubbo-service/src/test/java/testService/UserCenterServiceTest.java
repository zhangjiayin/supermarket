package testService;

import org.junit.Test;

import test.BaseTest;
import cn.xn.user.domain.BaseReq;
import cn.xn.user.domain.CheckMobileReq;
import cn.xn.user.domain.GetFriendReq;
import cn.xn.user.enums.SystemType;
import cn.xn.user.service.IFriendService;
import cn.xn.user.service.IRegisterService;
import cn.xn.user.utils.RequestSignUtils;

import com.alibaba.fastjson.JSONObject;

public class UserCenterServiceTest extends BaseTest{
	
	@Test
	public void getValuesByType(){
		IFriendService iFriendService = this.getService("IFriendServiceImpl", IFriendService.class);
		GetFriendReq getFriendReq =  new GetFriendReq();
		getFriendReq.setMobile("18400009009");
		getFriendReq.setMemberNo("27e5ea2b-d12e-4c75-88e1-9838baabcc9c");
		getFriendReq.setType(0);
		
		addBaseProperties(getFriendReq);
        
		logger.info(JSONObject.toJSONString(iFriendService.getAllFriend(getFriendReq)));
	}
	
	@Test
	public void doCheckMobile(){
		IRegisterService iRegisterService = this.getService("IRegisterServiceImpl", IRegisterService.class);
		CheckMobileReq checkMobileReq = new CheckMobileReq();
		checkMobileReq.setLoginName("18400009009");
		
		addBaseProperties(checkMobileReq);
        
		logger.info(JSONObject.toJSONString(iRegisterService.doCheckMobile(checkMobileReq)));
	}
	
	/**
	 * 添加系统参数和签名
	 */
	public void addBaseProperties(BaseReq baseReq){
		baseReq.setAppVersion("1.0");
		baseReq.setSourceType("wechat");
		baseReq.setSystemType(SystemType.CHANNEL.getText());
        String sign = RequestSignUtils.addSign(baseReq,"S5Yn9H5qk0Yf1ZhwGum3SdwnVZ7fMAUD");
        baseReq.setSign(sign);
	}
}
