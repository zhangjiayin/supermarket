package linkwee.serviceImpl.accountCenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.GetTransTypeReq;
import com.xiaoniu.account.domain.PaySendSmsBindCardReq;
import com.xiaoniu.account.domain.QueryInRecordReq;
import com.xiaoniu.account.domain.QueryRecordReq;
import com.xiaoniu.account.domain.QuickPayReq;
import com.xiaoniu.account.domain.QuickPaySendSmsReq;
import com.xiaoniu.account.domain.SystemInRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.InRecordResult;
import com.xiaoniu.account.domain.result.InRecordRlt;
import com.xiaoniu.account.domain.result.PaymentResult;
import com.xiaoniu.account.domain.result.RecordRlt;
import com.xiaoniu.account.domain.result.TransTypeRlt;
import com.xiaoniu.account.service.IInRecordAndPayService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IInRecordAndPayServiceImpl implements IInRecordAndPayService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<Map<String, String>> firstPaySendSms(PaySendSmsBindCardReq req) {
		CommonRlt<Map<String, String>> commonRltLoginRlt = new CommonRlt<Map<String, String>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getFirstPaySendSms(),new CommonRlt<Map<String, String>>().getClass(),req);
		Map<String, String> dataObj =  new HashMap<String, String>();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),Map.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Map<String, String>> firstBindCardPay(PaySendSmsBindCardReq req) {
		CommonRlt<Map<String, String>> commonRltLoginRlt = new CommonRlt<Map<String, String>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getFirstBindCardPay(),new CommonRlt<Map<String, String>>().getClass(),req);
		Map<String, String> dataObj =  new HashMap<String, String>();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),Map.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Map<String, Object>> directPay(PaySendSmsBindCardReq req) {
		CommonRlt<Map<String, Object>> commonRltLoginRlt = new CommonRlt<Map<String, Object>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getDirectPay(),new CommonRlt<Map<String, Object>>().getClass(),req);
		Map<String, Object> dataObj =  new HashMap<String, Object>();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),Map.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Map<String, String>> systemInRecord(SystemInRecordReq req) {
		CommonRlt<Map<String, String>> commonRltLoginRlt = new CommonRlt<Map<String, String>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getSystemInRecord(),new CommonRlt<Map<String, String>>().getClass(),req);
		Map<String, String> dataObj =  new HashMap<String, String>();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),Map.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<EmptyObject> acceptUnipayNotice(Map<String, String[]> map) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getAcceptUnipayNotice(),new CommonRlt<EmptyObject>().getClass(),map);
	}

	@Override
	public CommonRlt<EmptyObject> acceptUnipayNotice(PaymentResult paymentResult) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getAcceptUnipayNotice(),new CommonRlt<EmptyObject>().getClass(),paymentResult);
	}

	@Override
	public CommonRlt<PageResult<InRecordRlt>> inRecordListPage(QueryInRecordReq req) {
		CommonRlt<PageResult<InRecordRlt>> commonRltLoginRlt = new CommonRlt<PageResult<InRecordRlt>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getInRecordListPage(),new CommonRlt<PageResult<InRecordRlt>>().getClass(),req);
		PageResult<InRecordRlt> dataObj =  new PageResult<InRecordRlt>();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),new PageResult<InRecordRlt>().getClass());
		List<InRecordRlt> dataObj4List = new ArrayList<InRecordRlt>();
		dataObj4List = JSONObject.parseArray(JSONObject.toJSONString(dataObj.getResult()),InRecordRlt.class);
		dataObj.setResult(dataObj4List);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<List<TransTypeRlt>> getAllTransType() {
		CommonRlt<List<TransTypeRlt>> commonRltLoginRlt = new CommonRlt<List<TransTypeRlt>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetAllTransType(),new CommonRlt<List<TransTypeRlt>>().getClass());
		List<TransTypeRlt> dataObj =  new ArrayList<TransTypeRlt>();
		dataObj = JSONObject.parseArray(JSONObject.toJSONString(commonRltLoginRlt.getData()),TransTypeRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<List<TransTypeRlt>> getAllTransType(GetTransTypeReq req) {
		CommonRlt<List<TransTypeRlt>> commonRltLoginRlt = new CommonRlt<List<TransTypeRlt>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetAllTransType(),new CommonRlt<List<TransTypeRlt>>().getClass(),req);
		List<TransTypeRlt> dataObj =  new ArrayList<TransTypeRlt>();
		dataObj = JSONObject.parseArray(JSONObject.toJSONString(commonRltLoginRlt.getData()),TransTypeRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<RecordRlt> quickPaySendSms(QuickPaySendSmsReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getQuickPaySendSms(),new CommonRlt<RecordRlt>().getClass(),req);
	}

	@Override
	public CommonRlt<RecordRlt> quickPay(QuickPayReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getQuickPay(),new CommonRlt<RecordRlt>().getClass(),req);
	}

	@Override
	public CommonRlt<RecordRlt> quickPayDirect(QuickPaySendSmsReq req) {
		return null;
	}

	@Override
	public CommonRlt<InRecordResult> queryInRecord(QueryRecordReq req) {
		return null;
	}

}
