package linkwee.serviceImpl.accountCenter;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.account.domain.CheckPayPwdReq;
import com.xiaoniu.account.domain.CheckPayPwdStatusReq;
import com.xiaoniu.account.domain.IsSetPayPwdReq;
import com.xiaoniu.account.domain.ResetPayPwdReq;
import com.xiaoniu.account.domain.SetPayPwdReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.service.IPayPasswordSOAService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IPayPasswordSOAServiceImpl implements IPayPasswordSOAService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<Boolean> checkPayPassword(CheckPayPwdReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getCheckPayPassword(),new CommonRlt<Boolean>().getClass(),req);
	}

	@Override
	public CommonRlt<Boolean> checkPayStatus(CheckPayPwdStatusReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getCheckPayStatus(),new CommonRlt<Boolean>().getClass(),req);
	}

	@Override
	public CommonRlt<EmptyObject> setPayPassword(SetPayPwdReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getSetPayPassword(),new CommonRlt<EmptyObject>().getClass(),req);
	}

	@Override
	public CommonRlt<EmptyObject> resetPayPassword(ResetPayPwdReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getResetPayPassword(),new CommonRlt<EmptyObject>().getClass(),req);
	}

	@Override
	public CommonRlt<Boolean> isSetPayPassword(IsSetPayPwdReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getIsSetPayPassword(),new CommonRlt<Boolean>().getClass(),req);
	}

}
