package linkwee.serviceImpl.userCenter;

import javax.annotation.Resource;

import linkwee.config.UserCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;
import cn.xn.user.domain.BindReq;
import cn.xn.user.domain.CheckMobileReq;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.FiredBindReq;
import cn.xn.user.domain.RegisterReq;
import cn.xn.user.domain.RegisterRlt;
import cn.xn.user.service.IRegisterService;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IRegisterServiceImpl implements IRegisterService{
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private UserCenterConfig userCenterConfig;

	@Override
	public CommonRlt<RegisterRlt> doRegister(RegisterReq model) {
		CommonRlt<RegisterRlt> commonRltLoginRlt = new CommonRlt<RegisterRlt>();
		commonRltLoginRlt = commonHelper.userCenterHttpRequest(userCenterConfig.getDoRegister(),new CommonRlt<RegisterRlt>().getClass(),model);
		RegisterRlt dataObj =  new RegisterRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),RegisterRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkUserCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Boolean> doCheckMobile(CheckMobileReq model) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDoCheckMobile(),new CommonRlt<Boolean>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> doBind(BindReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDoBind(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> doFiredBind(FiredBindReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getDoFiredBind(),new CommonRlt<EmptyObject>().getClass(),model);
	}
}
