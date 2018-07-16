package linkwee.serviceImpl.accountCenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import linkwee.config.AccountCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.CreateUserAssetReq;
import com.xiaoniu.account.domain.QueryBalanceReq;
import com.xiaoniu.account.domain.QueryUserAssetReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.UserAssetRlt;
import com.xiaoniu.account.domain.result.UserBalanceRlt;
import com.xiaoniu.account.service.IUserAssetSOAService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IUserAssetSOAServiceImpl implements IUserAssetSOAService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<EmptyObject> createUserAsset(CreateUserAssetReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getCreateUserAsset(),new CommonRlt<EmptyObject>().getClass(),req);
	}

	@Override
	public CommonRlt<UserAssetRlt> getUserAsset(QueryUserAssetReq req) {
		CommonRlt<UserAssetRlt> commonRltLoginRlt = new CommonRlt<UserAssetRlt>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetUserAsset(),new CommonRlt<UserAssetRlt>().getClass(),req);
		UserAssetRlt dataObj =  new UserAssetRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),UserAssetRlt.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<PageResult<UserBalanceRlt>> queryBalanceRecord(QueryBalanceReq req) {
		CommonRlt<PageResult<UserBalanceRlt>> commonRltLoginRlt = new CommonRlt<PageResult<UserBalanceRlt>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetBalanceRecord(),new CommonRlt<PageResult<UserBalanceRlt>>().getClass(),req);
		PageResult<UserBalanceRlt> dataObj =  new PageResult<UserBalanceRlt>();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),new PageResult<UserBalanceRlt>().getClass());
		List<UserBalanceRlt> dataList = new ArrayList<UserBalanceRlt>();
		dataList = JSONObject.parseArray(JSONObject.toJSONString(dataObj.getResult()),UserBalanceRlt.class);
		dataObj.setResult(dataList);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Long> queryBalanceSum(QueryBalanceReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getQueryBalanceSum(),new CommonRlt<Long>().getClass(),req);
	}

}
