package linkwee.serviceImpl.userCenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import linkwee.config.UserCenterConfig;
import linkwee.helper.CommonHelper;
import linkwee.utils.CommonUtils;
import cn.xn.user.domain.BindInfoRlt;
import cn.xn.user.domain.CertReq;
import cn.xn.user.domain.CheckUserRlt;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.CustomerInfoBaseRlt;
import cn.xn.user.domain.CustomerInfoByCertNoReq;
import cn.xn.user.domain.CustomerInfoReq;
import cn.xn.user.domain.CustomerInfoReq2;
import cn.xn.user.domain.CustomerInfoRlt;
import cn.xn.user.domain.CustomerListReq;
import cn.xn.user.domain.CustomerListRlt;
import cn.xn.user.domain.DeviceInfoReq;
import cn.xn.user.domain.DeviceInfoRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.LevelFlagUpdateReq;
import cn.xn.user.domain.LevelUpdateReq;
import cn.xn.user.domain.LevelUpdateRlt;
import cn.xn.user.domain.MemberLevelInfoRlt;
import cn.xn.user.domain.NameFlagReq;
import cn.xn.user.domain.PageCommonRlt;
import cn.xn.user.domain.StatisticReq;
import cn.xn.user.domain.StatisticRlt;
import cn.xn.user.domain.UpdateCustomerInoReq;
import cn.xn.user.domain.UpdateMemberNameReq;
import cn.xn.user.domain.UpdateRefereeReq;
import cn.xn.user.domain.UpdateScanFlagReq;
import cn.xn.user.service.ICustomerInfoService;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class ICustomerInfoServiceImpl implements ICustomerInfoService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private UserCenterConfig userCenterConfig;

	@Override
	public CommonRlt<EmptyObject> updateDeviceInfo(DeviceInfoReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateDeviceInfo(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> createDeviceInfo(DeviceInfoReq model) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getCreateDeviceInfo(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<DeviceInfoRlt> getDeviceInfo(CustomerInfoReq model) {
		CommonRlt<DeviceInfoRlt> commonRltObj = new CommonRlt<DeviceInfoRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetDeviceInfo(),new CommonRlt<DeviceInfoRlt>().getClass(),model);
		DeviceInfoRlt dataObj =  new DeviceInfoRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),DeviceInfoRlt.class);
		commonRltObj.setData(dataObj);
		CommonUtils.checkUserCenterResponse(commonRltObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<CustomerInfoRlt> getUserInfo(CustomerInfoReq model) {
		CommonRlt<CustomerInfoRlt> commonRltObj = new CommonRlt<CustomerInfoRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetUserInfo(),new CommonRlt<CustomerInfoRlt>().getClass(),model);
		CustomerInfoRlt dataObj =  new CustomerInfoRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),CustomerInfoRlt.class);
		commonRltObj.setData(dataObj);
		CommonUtils.checkUserCenterResponse(commonRltObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<List<CustomerInfoRlt>> getUserInfoByCertNo(CustomerInfoByCertNoReq model) {
		CommonRlt<List<CustomerInfoRlt>> commonRltObj = new CommonRlt<List<CustomerInfoRlt>>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetUserInfoByCertNo(),new CommonRlt<List<CustomerInfoRlt>>().getClass(),model);
		List<CustomerInfoRlt> dataListObj =  new ArrayList<CustomerInfoRlt>();
		dataListObj = JSONObject.parseArray(JSONObject.toJSONString(commonRltObj.getData()),CustomerInfoRlt.class);
		commonRltObj.setData(dataListObj);
		CommonUtils.checkUserCenterResponse(commonRltObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<CustomerInfoRlt> getUserInfoByLoginName(CustomerInfoReq2 model) {
		CommonRlt<CustomerInfoRlt> commonRltObj = new CommonRlt<CustomerInfoRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetUserInfoByLoginName(),new CommonRlt<CustomerInfoRlt>().getClass(),model);
		CustomerInfoRlt dataObj =  new CustomerInfoRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),CustomerInfoRlt.class);
		commonRltObj.setData(dataObj);
		CommonUtils.checkUserCenterResponse(commonRltObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<EmptyObject> updateCertInfo(CertReq model) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateCertInfo(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> updateNameFlag(NameFlagReq model) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateNameFlag(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<CheckUserRlt> checkUser(CustomerInfoReq model) {
		CommonRlt<CheckUserRlt> commonRltObj = new CommonRlt<CheckUserRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getCheckUser(),new CommonRlt<CheckUserRlt>().getClass(),model);
		CheckUserRlt dataObj =  new CheckUserRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),CheckUserRlt.class);
		commonRltObj.setData(dataObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<String> findMobileById(CustomerInfoReq model) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getFindMobileById(),new CommonRlt<String>().getClass(),model);
	}

	@Override
	public CommonRlt<EmptyObject> updateCustomerInfo(UpdateCustomerInoReq model) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateCustomerInfo(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<CustomerInfoBaseRlt> getCustomerInfo(CustomerInfoReq model) {
		CommonRlt<CustomerInfoBaseRlt> commonRltObj = new CommonRlt<CustomerInfoBaseRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetCustomerInfo(),new CommonRlt<CustomerInfoBaseRlt>().getClass(),model);
		CustomerInfoBaseRlt dataObj =  new CustomerInfoBaseRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),CustomerInfoBaseRlt.class);
		commonRltObj.setData(dataObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<LevelUpdateRlt> updateMemberLevel(LevelUpdateReq model) {
		CommonRlt<LevelUpdateRlt> commonRltObj = new CommonRlt<LevelUpdateRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateMemberLevel(),new CommonRlt<LevelUpdateRlt>().getClass(),model);
		LevelUpdateRlt dataObj =  new LevelUpdateRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),LevelUpdateRlt.class);
		commonRltObj.setData(dataObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<MemberLevelInfoRlt> doGetMemberLevelInfo(CustomerInfoReq model) {
		CommonRlt<MemberLevelInfoRlt> commonRltObj = new CommonRlt<MemberLevelInfoRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getDoGetMemberLevelInfo(),new CommonRlt<MemberLevelInfoRlt>().getClass(),model);
		MemberLevelInfoRlt dataObj =  new MemberLevelInfoRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),MemberLevelInfoRlt.class);
		commonRltObj.setData(dataObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<EmptyObject> updateMemberLevelFlag(LevelFlagUpdateReq model) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateMemberLevelFlag(),new CommonRlt<EmptyObject>().getClass(),model);
	}

	@Override
	public CommonRlt<BindInfoRlt> getBindInfo(CustomerInfoReq model) {
		CommonRlt<BindInfoRlt> commonRltObj = new CommonRlt<BindInfoRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetBindInfo(),new CommonRlt<BindInfoRlt>().getClass(),model);
		BindInfoRlt dataObj =  new BindInfoRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),BindInfoRlt.class);
		commonRltObj.setData(dataObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<EmptyObject> updateMemberName(UpdateMemberNameReq req) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateMemberName(),new CommonRlt<EmptyObject>().getClass(),req);
	}

	@Override
	public CommonRlt<StatisticRlt> getMemberCount(StatisticReq req) {
		CommonRlt<StatisticRlt> commonRltObj = new CommonRlt<StatisticRlt>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetMemberCount(),new CommonRlt<StatisticRlt>().getClass(),req);
		StatisticRlt dataObj =  new StatisticRlt();
		dataObj = JSONObject.parseObject(JSONObject.toJSONString(commonRltObj.getData()),StatisticRlt.class);
		commonRltObj.setData(dataObj);
		return commonRltObj;
	}

	@Override
	public PageCommonRlt<List<CustomerListRlt>> getCustomerList(CustomerListReq req) {
		PageCommonRlt<List<CustomerListRlt>> commonRltObj = new PageCommonRlt<List<CustomerListRlt>>();
		commonRltObj = commonHelper.userCenterHttpRequest(userCenterConfig.getGetCustomerList(),new PageCommonRlt<List<CustomerListRlt>>().getClass(),req);
		List<CustomerListRlt> dataListObj =  new ArrayList<CustomerListRlt>();
		dataListObj = JSONObject.parseArray(JSONObject.toJSONString(commonRltObj.getData()),CustomerListRlt.class);
		commonRltObj.setData(dataListObj);
		return commonRltObj;
	}

	@Override
	public CommonRlt<EmptyObject> updateScanFlag(UpdateScanFlagReq req) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateScanFlag(),new CommonRlt<EmptyObject>().getClass(),req);
	}

	@Override
	public CommonRlt<Boolean> updateRefereeInfo(UpdateRefereeReq req) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateRefereeInfo(),new CommonRlt<Boolean>().getClass(),req);
	}

}
