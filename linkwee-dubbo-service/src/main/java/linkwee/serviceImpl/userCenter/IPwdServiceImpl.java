package linkwee.serviceImpl.userCenter;

import javax.annotation.Resource;

import linkwee.config.UserCenterConfig;
import linkwee.helper.CommonHelper;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.ModifyLoginPwdReq;
import cn.xn.user.domain.ResetLoginPwdReq;
import cn.xn.user.service.IPwdService;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IPwdServiceImpl implements IPwdService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private UserCenterConfig userCenterConfig;

	@Override
	public CommonRlt<EmptyObject> doResetLoginPwd(ResetLoginPwdReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDoResetLoginPwd(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> doModifyLoginPwd(ModifyLoginPwdReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDoModifyLoginPwd(),new CommonRlt<EmptyObject>().getClass(),model);
	}

}
