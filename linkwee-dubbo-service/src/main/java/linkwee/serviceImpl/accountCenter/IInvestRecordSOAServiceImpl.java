package linkwee.serviceImpl.accountCenter;

import java.util.Map;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.CancelInvestOrderReq;
import com.xiaoniu.account.domain.ConfirmInvestOrderReq;
import com.xiaoniu.account.domain.InvestOrderReq;
import com.xiaoniu.account.domain.InvestRecordReq;
import com.xiaoniu.account.domain.QueryInvestRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.InvestRecordRlt;
import com.xiaoniu.account.domain.result.RecordRlt;
import com.xiaoniu.account.service.IInvestRecordSOAService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IInvestRecordSOAServiceImpl implements IInvestRecordSOAService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<Map<String, Object>> userInvestRecord(InvestRecordReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getUserInvestRecord(),new CommonRlt<Map<String, Object>>().getClass(),req);
	}

	@Override
	public CommonRlt<RecordRlt> investOrder(InvestOrderReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getInvestOrder(),new CommonRlt<RecordRlt>().getClass(),req);
	}

	@Override
	public CommonRlt<RecordRlt> confirmInvestOrder(ConfirmInvestOrderReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getConfirmInvestOrder(),new CommonRlt<RecordRlt>().getClass(),req);
	}

	@Override
	public CommonRlt<EmptyObject> cancelInvestOrder(CancelInvestOrderReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getCancelInvestOrder(),new CommonRlt<EmptyObject>().getClass(),req);
	}

	@Override
	public CommonRlt<PageResult<InvestRecordRlt>> investRecordListPage(QueryInvestRecordReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getInvestRecordListPage(),new CommonRlt<PageResult<InvestRecordRlt>>().getClass(),req);
	}

}
