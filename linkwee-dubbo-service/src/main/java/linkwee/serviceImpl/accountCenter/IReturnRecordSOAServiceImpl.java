package linkwee.serviceImpl.accountCenter;

import java.util.Map;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.account.domain.ReturnRecordReq;
import com.xiaoniu.account.domain.TransferReturnReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.RecordRlt;
import com.xiaoniu.account.service.IReturnRecordSOAService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IReturnRecordSOAServiceImpl implements IReturnRecordSOAService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;

	@Override
	public CommonRlt<Map<String, Object>> userReturnRecord(ReturnRecordReq req) {	
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getUserReturnRecord(),new CommonRlt<Map<String, Object>>().getClass(),req);
	}

	@Override
	public CommonRlt<RecordRlt> transferReturn(TransferReturnReq req) {	
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getTransferReturn(),new CommonRlt<RecordRlt>().getClass(),req);
	}

}
