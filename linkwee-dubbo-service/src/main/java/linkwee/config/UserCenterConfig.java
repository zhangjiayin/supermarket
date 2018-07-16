package linkwee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserCenterConfig {
	
	@Value("${uc.updateFriend}")
	private String updateFriend;
	
	@Value("${uc.getFriendList}")
	private String getFriendList;
	
	@Value("${uc.getAllFriend}")
	private String getAllFriend;
	
	@Value("${uc.getSecret}")
	private String getSecret;
	
	@Value("${uc.updateSecret}")
	private String updateSecret;
	
	@Value("${uc.searchFriend}")
	private String searchFriend;
	
	@Value("${uc.updateDeviceInfo}")
	private String updateDeviceInfo;
	
	@Value("${uc.createDeviceInfo}")
	private String createDeviceInfo;
	
	@Value("${uc.getDeviceInfo}")
	private String getDeviceInfo;
	
	@Value("${uc.getUserInfo}")
	private String getUserInfo;
	
	@Value("${uc.getUserInfoByCertNo}")
	private String getUserInfoByCertNo;
	
	@Value("${uc.getUserInfoByLoginName}")
	private String getUserInfoByLoginName;
	
	@Value("${uc.updateCertInfo}")
	private String updateCertInfo;
	
	@Value("${uc.updateNameFlag}")
	private String updateNameFlag;
	
	@Value("${uc.checkUser}")
	private String checkUser;
	
	@Value("${uc.findMobileById}")
	private String findMobileById;
	
	@Value("${uc.updateCustomerInfo}")
	private String updateCustomerInfo;
	
	@Value("${uc.getCustomerInfo}")
	private String getCustomerInfo;
	
	@Value("${uc.updateMemberLevel}")
	private String updateMemberLevel;
	
	@Value("${uc.doGetMemberLevelInfo}")
	private String doGetMemberLevelInfo;
	
	@Value("${uc.updateMemberLevelFlag}")
	private String updateMemberLevelFlag;
	
	@Value("${uc.getBindInfo}")
	private String getBindInfo;
	
	@Value("${uc.updateMemberName}")
	private String updateMemberName;
	
	@Value("${uc.getMemberCount}")
	private String getMemberCount;
	
	@Value("${uc.getCustomerList}")
	private String getCustomerList;
	
	@Value("${uc.updateScanFlag}")
	private String updateScanFlag;
	
	@Value("${uc.updateRefereeInfo}")
	private String updateRefereeInfo;
	
	@Value("${uc.doLogin}")
	private String doLogin;
	
	@Value("${uc.doIsLogin}")
	private String doIsLogin;
	
	@Value("${uc.doLoginOut}")
	private String doLoginOut;
	
	@Value("${uc.delayToken}")
	private String delayToken;
	
	@Value("${uc.doBindLogin}")
	private String doBindLogin;
	
	@Value("${uc.docheckLoginPwd}")
	private String docheckLoginPwd;
	
	@Value("${uc.docheckLoginPwdByMemberNo}")
	private String docheckLoginPwdByMemberNo;
	
	@Value("${uc.getLoginErrorTime}")
	private String getLoginErrorTime;
	
	@Value("${uc.doResetLoginPwd}")
	private String doResetLoginPwd;
	
	@Value("${uc.doModifyLoginPwd}")
	private String doModifyLoginPwd;
	
	@Value("${uc.checkPayPassword}")
	private String checkPayPassword;
	
	@Value("${uc.setPayPassword}")
	private String setPayPassword;
	
	@Value("${uc.resetPayPassword}")
	private String resetPayPassword;
	
	@Value("${uc.isSetPayPassword}")
	private String isSetPayPassword;
	
	@Value("${uc.checkPayStatus}")
	private String checkPayStatus;
	
	@Value("${uc.doRegister}")
	private String doRegister;
	
	@Value("${uc.doCheckMobile}")
	private String doCheckMobile;
	
	@Value("${uc.doBind}")
	private String doBind;
	
	@Value("${uc.doFiredBind}")
	private String doFiredBind;
	
	public String getUpdateFriend() {
		return updateFriend;
	}
	public void setUpdateFriend(String updateFriend) {
		this.updateFriend = updateFriend;
	}
	public String getGetFriendList() {
		return getFriendList;
	}
	public void setGetFriendList(String getFriendList) {
		this.getFriendList = getFriendList;
	}
	public String getGetAllFriend() {
		return getAllFriend;
	}
	public void setGetAllFriend(String getAllFriend) {
		this.getAllFriend = getAllFriend;
	}
	public String getGetSecret() {
		return getSecret;
	}
	public void setGetSecret(String getSecret) {
		this.getSecret = getSecret;
	}
	public String getUpdateSecret() {
		return updateSecret;
	}
	public void setUpdateSecret(String updateSecret) {
		this.updateSecret = updateSecret;
	}
	public String getSearchFriend() {
		return searchFriend;
	}
	public void setSearchFriend(String searchFriend) {
		this.searchFriend = searchFriend;
	}
	public String getUpdateDeviceInfo() {
		return updateDeviceInfo;
	}
	public void setUpdateDeviceInfo(String updateDeviceInfo) {
		this.updateDeviceInfo = updateDeviceInfo;
	}
	public String getCreateDeviceInfo() {
		return createDeviceInfo;
	}
	public void setCreateDeviceInfo(String createDeviceInfo) {
		this.createDeviceInfo = createDeviceInfo;
	}
	public String getGetDeviceInfo() {
		return getDeviceInfo;
	}
	public void setGetDeviceInfo(String getDeviceInfo) {
		this.getDeviceInfo = getDeviceInfo;
	}
	public String getGetUserInfo() {
		return getUserInfo;
	}
	public void setGetUserInfo(String getUserInfo) {
		this.getUserInfo = getUserInfo;
	}
	public String getGetUserInfoByCertNo() {
		return getUserInfoByCertNo;
	}
	public void setGetUserInfoByCertNo(String getUserInfoByCertNo) {
		this.getUserInfoByCertNo = getUserInfoByCertNo;
	}
	public String getGetUserInfoByLoginName() {
		return getUserInfoByLoginName;
	}
	public void setGetUserInfoByLoginName(String getUserInfoByLoginName) {
		this.getUserInfoByLoginName = getUserInfoByLoginName;
	}
	public String getUpdateCertInfo() {
		return updateCertInfo;
	}
	public void setUpdateCertInfo(String updateCertInfo) {
		this.updateCertInfo = updateCertInfo;
	}
	public String getUpdateNameFlag() {
		return updateNameFlag;
	}
	public void setUpdateNameFlag(String updateNameFlag) {
		this.updateNameFlag = updateNameFlag;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public String getFindMobileById() {
		return findMobileById;
	}
	public void setFindMobileById(String findMobileById) {
		this.findMobileById = findMobileById;
	}
	public String getUpdateCustomerInfo() {
		return updateCustomerInfo;
	}
	public void setUpdateCustomerInfo(String updateCustomerInfo) {
		this.updateCustomerInfo = updateCustomerInfo;
	}
	public String getGetCustomerInfo() {
		return getCustomerInfo;
	}
	public void setGetCustomerInfo(String getCustomerInfo) {
		this.getCustomerInfo = getCustomerInfo;
	}
	public String getUpdateMemberLevel() {
		return updateMemberLevel;
	}
	public void setUpdateMemberLevel(String updateMemberLevel) {
		this.updateMemberLevel = updateMemberLevel;
	}
	public String getDoGetMemberLevelInfo() {
		return doGetMemberLevelInfo;
	}
	public void setDoGetMemberLevelInfo(String doGetMemberLevelInfo) {
		this.doGetMemberLevelInfo = doGetMemberLevelInfo;
	}
	public String getUpdateMemberLevelFlag() {
		return updateMemberLevelFlag;
	}
	public void setUpdateMemberLevelFlag(String updateMemberLevelFlag) {
		this.updateMemberLevelFlag = updateMemberLevelFlag;
	}
	public String getGetBindInfo() {
		return getBindInfo;
	}
	public void setGetBindInfo(String getBindInfo) {
		this.getBindInfo = getBindInfo;
	}
	public String getUpdateMemberName() {
		return updateMemberName;
	}
	public void setUpdateMemberName(String updateMemberName) {
		this.updateMemberName = updateMemberName;
	}
	public String getGetMemberCount() {
		return getMemberCount;
	}
	public void setGetMemberCount(String getMemberCount) {
		this.getMemberCount = getMemberCount;
	}
	public String getGetCustomerList() {
		return getCustomerList;
	}
	public void setGetCustomerList(String getCustomerList) {
		this.getCustomerList = getCustomerList;
	}
	public String getUpdateScanFlag() {
		return updateScanFlag;
	}
	public void setUpdateScanFlag(String updateScanFlag) {
		this.updateScanFlag = updateScanFlag;
	}
	public String getUpdateRefereeInfo() {
		return updateRefereeInfo;
	}
	public void setUpdateRefereeInfo(String updateRefereeInfo) {
		this.updateRefereeInfo = updateRefereeInfo;
	}
	public String getDoLogin() {
		return doLogin;
	}
	public void setDoLogin(String doLogin) {
		this.doLogin = doLogin;
	}
	public String getDoIsLogin() {
		return doIsLogin;
	}
	public void setDoIsLogin(String doIsLogin) {
		this.doIsLogin = doIsLogin;
	}
	public String getDoLoginOut() {
		return doLoginOut;
	}
	public void setDoLoginOut(String doLoginOut) {
		this.doLoginOut = doLoginOut;
	}
	public String getDelayToken() {
		return delayToken;
	}
	public void setDelayToken(String delayToken) {
		this.delayToken = delayToken;
	}
	public String getDoBindLogin() {
		return doBindLogin;
	}
	public void setDoBindLogin(String doBindLogin) {
		this.doBindLogin = doBindLogin;
	}
	public String getDocheckLoginPwd() {
		return docheckLoginPwd;
	}
	public void setDocheckLoginPwd(String docheckLoginPwd) {
		this.docheckLoginPwd = docheckLoginPwd;
	}
	public String getDocheckLoginPwdByMemberNo() {
		return docheckLoginPwdByMemberNo;
	}
	public void setDocheckLoginPwdByMemberNo(String docheckLoginPwdByMemberNo) {
		this.docheckLoginPwdByMemberNo = docheckLoginPwdByMemberNo;
	}
	public String getGetLoginErrorTime() {
		return getLoginErrorTime;
	}
	public void setGetLoginErrorTime(String getLoginErrorTime) {
		this.getLoginErrorTime = getLoginErrorTime;
	}
	public String getDoResetLoginPwd() {
		return doResetLoginPwd;
	}
	public void setDoResetLoginPwd(String doResetLoginPwd) {
		this.doResetLoginPwd = doResetLoginPwd;
	}
	public String getDoModifyLoginPwd() {
		return doModifyLoginPwd;
	}
	public void setDoModifyLoginPwd(String doModifyLoginPwd) {
		this.doModifyLoginPwd = doModifyLoginPwd;
	}
	public String getCheckPayPassword() {
		return checkPayPassword;
	}
	public void setCheckPayPassword(String checkPayPassword) {
		this.checkPayPassword = checkPayPassword;
	}
	public String getSetPayPassword() {
		return setPayPassword;
	}
	public void setSetPayPassword(String setPayPassword) {
		this.setPayPassword = setPayPassword;
	}
	public String getResetPayPassword() {
		return resetPayPassword;
	}
	public void setResetPayPassword(String resetPayPassword) {
		this.resetPayPassword = resetPayPassword;
	}
	public String getIsSetPayPassword() {
		return isSetPayPassword;
	}
	public void setIsSetPayPassword(String isSetPayPassword) {
		this.isSetPayPassword = isSetPayPassword;
	}
	public String getCheckPayStatus() {
		return checkPayStatus;
	}
	public void setCheckPayStatus(String checkPayStatus) {
		this.checkPayStatus = checkPayStatus;
	}
	public String getDoRegister() {
		return doRegister;
	}
	public void setDoRegister(String doRegister) {
		this.doRegister = doRegister;
	}
	public String getDoCheckMobile() {
		return doCheckMobile;
	}
	public void setDoCheckMobile(String doCheckMobile) {
		this.doCheckMobile = doCheckMobile;
	}
	public String getDoBind() {
		return doBind;
	}
	public void setDoBind(String doBind) {
		this.doBind = doBind;
	}
	public String getDoFiredBind() {
		return doFiredBind;
	}
	public void setDoFiredBind(String doFiredBind) {
		this.doFiredBind = doFiredBind;
	}
}
