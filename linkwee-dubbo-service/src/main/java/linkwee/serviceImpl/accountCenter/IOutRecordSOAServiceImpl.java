package linkwee.serviceImpl.accountCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.OutExpectReachTimeReq;
import com.xiaoniu.account.domain.OutRecordReq;
import com.xiaoniu.account.domain.QueryOutRecordReq;
import com.xiaoniu.account.domain.QuerySingleOutRecordReq;
import com.xiaoniu.account.domain.QueryUserOutReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.OutExpectReachTimeRlt;
import com.xiaoniu.account.domain.result.OutRecord2Rlt;
import com.xiaoniu.account.domain.result.OutRecordInfoRlt;
import com.xiaoniu.account.domain.result.UserOutFeeRlt;
import com.xiaoniu.account.domain.result.UserOutSumRlt;
import com.xiaoniu.account.service.IOutRecordSOAService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IOutRecordSOAServiceImpl implements IOutRecordSOAService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<Map<String, String>> userWithdrawRecord(OutRecordReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getUserWithdrawRecord(),new CommonRlt<Map<String, String>>().getClass(),req);
	}

	@Override
	public CommonRlt<UserOutSumRlt> getUserOutSum(QueryUserOutReq req) {
		CommonRlt<UserOutSumRlt> commonRltLoginRlt = new CommonRlt<UserOutSumRlt>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetUserOutSum(),new CommonRlt<UserOutSumRlt>().getClass(),req);
		UserOutSumRlt dataObj =  new UserOutSumRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),UserOutSumRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<UserOutFeeRlt> getUserFee(QueryUserOutReq req) {
		CommonRlt<UserOutFeeRlt> commonRltLoginRlt = new CommonRlt<UserOutFeeRlt>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetUserFee(),new CommonRlt<UserOutFeeRlt>().getClass(),req);
		UserOutFeeRlt dataObj =  new UserOutFeeRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),UserOutFeeRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<PageResult<OutRecord2Rlt>> outRecordListPage(QueryOutRecordReq req) {
		CommonRlt<PageResult<OutRecord2Rlt>> commonRltLoginRlt = new CommonRlt<PageResult<OutRecord2Rlt>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getOutRecordListPage(),new CommonRlt<PageResult<OutRecord2Rlt>>().getClass(),req);
		PageResult<OutRecord2Rlt> dataObj =  new PageResult<OutRecord2Rlt>();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),new PageResult<OutRecord2Rlt>().getClass());
		List<OutRecord2Rlt> outRecord2RltL = new ArrayList<OutRecord2Rlt>();
		outRecord2RltL = JSONObject.parseArray(JSONObject.toJSONString(dataObj.getResult()),OutRecord2Rlt.class);
		dataObj.setResult(outRecord2RltL);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<OutExpectReachTimeRlt> outRecordExpectedReachTime(OutExpectReachTimeReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getOutRecordExpectedReachTime(),new CommonRlt<OutExpectReachTimeRlt>().getClass(),req);
	}

	@Override
	public CommonRlt<OutRecordInfoRlt> querySingleOutRecord(QuerySingleOutRecordReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getQuerySingleOutRecord(),new CommonRlt<OutRecordInfoRlt>().getClass(),req);
	}

}
