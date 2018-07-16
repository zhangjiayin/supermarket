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
import com.xiaoniu.account.domain.AuthReq;
import com.xiaoniu.account.domain.AuthSendSmsReq;
import com.xiaoniu.account.domain.BankListReq;
import com.xiaoniu.account.domain.SearchCardInfoReq;
import com.xiaoniu.account.domain.UserBindCardReq;
import com.xiaoniu.account.domain.result.CityInfoRlt;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.ProvinceInfoRlt;
import com.xiaoniu.account.domain.result.UserBindCardRlt;
import com.xiaoniu.account.domain.result.UserSettleInfo;
import com.xiaoniu.account.service.IPrepare2PayService;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IPrepare2PayServiceImpl implements IPrepare2PayService {

	@Resource
	private CommonHelper commonHelper;
	@Resource
	private AccountCenterConfig accountCenterConfig;
	
	@Override
	public CommonRlt<Map<String, Object>> getUserBindCard(UserBindCardReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetUserBindCard(),new CommonRlt<Map<String, Object>>().getClass(),req);
	}

	@Override
	public CommonRlt<UserBindCardRlt> getUserBindCardNew(UserBindCardReq req) {
		CommonRlt<UserBindCardRlt> commonRltLoginRlt = new CommonRlt<UserBindCardRlt>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getGetUserBindCardNew(),new CommonRlt<UserBindCardRlt>().getClass(),req);
		UserBindCardRlt dataObj =  new UserBindCardRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),UserBindCardRlt.class);
		commonRltLoginRlt.setData(dataObj);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Map<String, Object>> getUserBindCardNew2(UserBindCardReq req) {
		return null;
	}

	@Override
	public CommonRlt<Map<String, Object>> getUserBindCardWithWX(UserBindCardReq req) {
		return null;
	}

	@Override
	public CommonRlt<UserSettleInfo> queryUserSettleInfo(UserBindCardReq req) {
		CommonRlt<UserSettleInfo> commonRltLoginRlt = new CommonRlt<UserSettleInfo>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getQueryUserSettleInfo(),new CommonRlt<UserSettleInfo>().getClass(),req);
		UserSettleInfo dataObj =  new UserSettleInfo();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltLoginRlt.getData()),UserSettleInfo.class);
		commonRltLoginRlt.setData(dataObj);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Object> queryBankList(BankListReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getQueryBankList(),new CommonRlt<Object>().getClass(),req);
	}

	@Override
	public CommonRlt<Map<String, Object>> searchCardInfo(SearchCardInfoReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getSearchCardInfo(),new CommonRlt<Map<String, Object>>().getClass(),req);
	}

	@Override
	public CommonRlt<List<ProvinceInfoRlt>> queryAllProvince() {
		CommonRlt<List<ProvinceInfoRlt>> commonRltLoginRlt = new CommonRlt<List<ProvinceInfoRlt>>();
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getQueryAllProvince(),new CommonRlt<List<ProvinceInfoRlt>>().getClass());
		List<ProvinceInfoRlt> dataList = new ArrayList<ProvinceInfoRlt>();
		dataList = JSONObject.parseArray(JSONObject.toJSONString(commonRltLoginRlt.getData()),ProvinceInfoRlt.class);
		commonRltLoginRlt.setData(dataList);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<List<CityInfoRlt>> queryCityByProvince(String provinceCode) {
		CommonRlt<List<CityInfoRlt>> commonRltLoginRlt = new CommonRlt<List<CityInfoRlt>>();
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("provinceCode", provinceCode);
		commonRltLoginRlt = commonHelper.accountCenterHttpRequest(accountCenterConfig.getQueryCityByProvince(),new CommonRlt<List<CityInfoRlt>>().getClass(),parameterMap);
		List<CityInfoRlt> dataList = new ArrayList<CityInfoRlt>();
		dataList = JSONObject.parseArray(JSONObject.toJSONString(commonRltLoginRlt.getData()),CityInfoRlt.class);
		commonRltLoginRlt.setData(dataList);
		CommonUtils.checkAccountCenterResponse(commonRltLoginRlt);
		return commonRltLoginRlt;
	}

	@Override
	public CommonRlt<Map<String, Object>> authSendSms(AuthSendSmsReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getAuthSendSms(),new CommonRlt<Map<String, Object>>().getClass(),req);
	}

	@Override
	public CommonRlt<EmptyObject> auth(AuthReq req) {
		return commonHelper.accountCenterHttpRequest(accountCenterConfig.getAuth(),new CommonRlt<EmptyObject>().getClass(),req);
	}

}
