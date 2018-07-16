package linkwee.serviceImpl.accountCenter;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.account.domain.TransferRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.DebtTransferRlt;
import com.xiaoniu.account.service.ITransferRecordSOAService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class ITransferRecordSOAServiceImpl implements ITransferRecordSOAService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<DebtTransferRlt> userTransfer(TransferRecordReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getUserTransfer(),new CommonRlt<DebtTransferRlt>().getClass(),req);
	}

}
