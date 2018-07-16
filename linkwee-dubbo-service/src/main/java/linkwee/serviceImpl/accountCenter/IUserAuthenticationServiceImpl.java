package linkwee.serviceImpl.accountCenter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.account.domain.UserAuthenticationReq;
import com.xiaoniu.account.domain.UserQueryAuthReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.UserAuthenticationRlt;
import com.xiaoniu.account.service.IUserAuthenticationService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IUserAuthenticationServiceImpl implements IUserAuthenticationService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<EmptyObject> saveAuthentication(UserAuthenticationReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getSaveAuthentication(),new CommonRlt<EmptyObject>().getClass(),req);
	}

	@Override
	public CommonRlt<UserAuthenticationRlt> queryAuthentication(String userId) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("userId", userId);
		CommonRlt<UserAuthenticationRlt> commonRltLoginRlt = new CommonRlt<UserAuthenticationRlt>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getQueryAuthentication(),new CommonRlt<UserAuthenticationRlt>().getClass(),parameterMap);
		UserAuthenticationRlt dataObj =  new UserAuthenticationRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),UserAuthenticationRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<UserAuthenticationRlt> queryAuthentication(UserQueryAuthReq req) {
		CommonRlt<UserAuthenticationRlt> commonRltLoginRlt = new CommonRlt<UserAuthenticationRlt>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getQueryAuthentication(),new CommonRlt<UserAuthenticationRlt>().getClass(),req);
		UserAuthenticationRlt dataObj =  new UserAuthenticationRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),UserAuthenticationRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

}
