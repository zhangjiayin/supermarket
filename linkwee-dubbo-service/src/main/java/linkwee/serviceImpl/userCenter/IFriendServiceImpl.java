package linkwee.serviceImpl.userCenter;

import javax.annotation.Resource;

import linkwee.config.UserCenterConfig;
import linkwee.helper.CommonHelper;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.FriendInfoRlt;
import cn.xn.user.domain.GetFriendReq;
import cn.xn.user.domain.GetSecretReq;
import cn.xn.user.domain.Page;
import cn.xn.user.domain.SearchFriendReq;
import cn.xn.user.domain.UpdateFriendReq;
import cn.xn.user.domain.UpdateSecretReq;
import cn.xn.user.service.IFriendService;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version="1.0",group="p2p")
@SuppressWarnings("unchecked")
public class IFriendServiceImpl implements IFriendService {
	
	@Resource
	private CommonHelper commonHelper;
	@Resource
	private UserCenterConfig userCenterConfig;
	
	@Override
	public CommonRlt<Boolean> updateFriend(UpdateFriendReq req) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateFriend(),new CommonRlt<Boolean>().getClass(),req);
	}

	@Override
	public CommonRlt<Page<FriendInfoRlt>> getFriendList(GetFriendReq req) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getGetFriendList(),new CommonRlt<Page<FriendInfoRlt>>().getClass(),req);
	}
	
	@Override
	public CommonRlt<Page<FriendInfoRlt>> getAllFriend(GetFriendReq req) {
		return commonHelper.userCenterHttpRequest(userCenterConfig.getGetAllFriend(),new CommonRlt<Page<FriendInfoRlt>>().getClass(),req);
	}

	@Override
	public CommonRlt<String> getSecret(GetSecretReq req) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getGetSecret(),new CommonRlt<String>().getClass(),req);
	}

	@Override
	public CommonRlt<Boolean> updateSecret(UpdateSecretReq req) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getUpdateSecret(),new CommonRlt<Boolean>().getClass(),req);
	}

	@Override
	public CommonRlt<Page<FriendInfoRlt>> searchFriend(SearchFriendReq req) {
		// TODO Auto-generated method stub
		return commonHelper.userCenterHttpRequest(userCenterConfig.getSearchFriend(),new CommonRlt<Page<FriendInfoRlt>>().getClass(),req);
	}
}
