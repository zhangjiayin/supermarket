package linkwee.serviceImpl.userCenter;

import javax.annotation.Resource;

import linkwee.config.UserCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;
import cn.xn.user.domain.BindLoginReq;
import cn.xn.user.domain.CheckLoginPwdByMemberNoReq;
import cn.xn.user.domain.CheckLoginPwdReq;
import cn.xn.user.domain.CheckLoginReq;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.LoginErrorTimeReq;
import cn.xn.user.domain.LoginErrorTimeRlt;
import cn.xn.user.domain.LoginOutReq;
import cn.xn.user.domain.LoginReq;
import cn.xn.user.domain.LoginRlt;
import cn.xn.user.service.ILoginService;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class ILoginServiceImpl implements ILoginService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private UserCenterConfig userCenterConfig;
	
	@Override
	public CommonRlt<LoginRlt> doLogin(LoginReq model) {
		// TODO Auto-generated method stub
		CommonRlt<LoginRlt> commonRltLoginRlt = new CommonRlt<LoginRlt>();
		commonRltLoginRlt = commonHelper.userCenterHttpRequest(userCenterConfig.getDoLogin(),new CommonRlt<LoginRlt>().getClass(),model);
		LoginRlt loginRlt =  new LoginRlt();
		loginRlt = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),LoginRlt.class);
		commonRltLoginRlt.setData(loginRlt);
		CommonUtils.checkUserCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Boolean> doIsLogin(CheckLoginReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDoIsLogin(),new CommonRlt<Boolean>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> doLoginOut(LoginOutReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDoLoginOut(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> delayToken(CheckLoginReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDelayToken(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<LoginRlt> doBindLogin(BindLoginReq model) {
		// TODO Auto-generated method stub
		CommonRlt<LoginRlt> commonRltLoginRlt = new CommonRlt<LoginRlt>();
		commonRltLoginRlt = commonHelper.userCenterHttpRequest(userCenterConfig.getDoBindLogin(),new CommonRlt<LoginRlt>().getClass(),model);
		LoginRlt loginRlt =  new LoginRlt();
		loginRlt = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),LoginRlt.class);
		commonRltLoginRlt.setData(loginRlt);
		CommonUtils.checkUserCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Boolean> docheckLoginPwd(CheckLoginPwdReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDocheckLoginPwd(),new CommonRlt<Boolean>().getClass(),model);
	}

	@Override
	public CommonRlt<Boolean> docheckLoginPwdByMemberNo(CheckLoginPwdByMemberNoReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDocheckLoginPwdByMemberNo(),new CommonRlt<Boolean>().getClass(),model);
	}

	@Override
	public CommonRlt<LoginErrorTimeRlt> getLoginErrorTime(LoginErrorTimeReq req) {
		CommonRlt<LoginErrorTimeRlt> commonRltObj = new CommonRlt<LoginErrorTimeRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetLoginErrorTime(),new CommonRlt<LoginErrorTimeRlt>().getClass(),req);
		LoginErrorTimeRlt dataObj =  new LoginErrorTimeRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),LoginErrorTimeRlt.class);
		commonRltObj.setData(dataObj);
		CommonUtils.checkUserCenterResponse(commonRltObj);
		return commonRltObj;
	}

}
