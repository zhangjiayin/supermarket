package com.linkwee.web.remote;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.xn.user.domain.BaseReq;
import cn.xn.user.domain.BindLoginReq;
import cn.xn.user.domain.BindReq;
import cn.xn.user.domain.CheckLoginPwdByMemberNoReq;
import cn.xn.user.domain.CheckLoginPwdReq;
import cn.xn.user.domain.CheckLoginReq;
import cn.xn.user.domain.CheckMobileReq;
import cn.xn.user.domain.CheckPayPwdReq;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.CustomerInfoReq;
import cn.xn.user.domain.CustomerInfoRlt;
import cn.xn.user.domain.DeviceInfoReq;
import cn.xn.user.domain.DeviceInfoRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.FiredBindReq;
import cn.xn.user.domain.LoginOutReq;
import cn.xn.user.domain.LoginReq;
import cn.xn.user.domain.LoginRlt;
import cn.xn.user.domain.MemberLevelInfoRlt;
import cn.xn.user.domain.ModifyLoginPwdReq;
import cn.xn.user.domain.ReSetPayPwdReq;
import cn.xn.user.domain.ResetLoginPwdReq;
import cn.xn.user.domain.SetPayPwdReq;
import cn.xn.user.enums.ResultMsgEnum;
import cn.xn.user.enums.SourceType;
import cn.xn.user.enums.SystemType;
import cn.xn.user.exception.ServiceException;
import cn.xn.user.service.ICustomerInfoService;
import cn.xn.user.service.ILoginService;
import cn.xn.user.service.IPwdService;
import cn.xn.user.service.IRegisterService;
import cn.xn.user.utils.RequestSignUtils;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.ErrorCodeSupport;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.Constants;
import com.linkwee.web.api.RequestHead;
import com.linkwee.web.request.DeviceInfoRequest;
import com.linkwee.web.util.ResponseUtil;
import com.linkwee.web.util.WebUtil;

@Component
public class UserCenterHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ILoginService p2pLoginService;
	
	@Resource
	private IRegisterService p2pRegisterService;
	
	@Resource
	private IPwdService p2pPwdService;
	
	@Resource
	private ICustomerInfoService p2pCustomerInfoService;
	
	/**
	 * 获取登录用户信息
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public ServiceResponse<CustomerInfoRlt> getUserInfo(RequestHead req) throws Exception{
		CustomerInfoReq model = new CustomerInfoReq();
		initBaseReq(req,model);
		model.setMemberNo(WebUtil.getUserId(req.getToken()));
		CommonRlt<CustomerInfoRlt> rlt = p2pCustomerInfoService.getUserInfo(model);
		logger.debug("ICustomerInfoService获取用户信息,model={},result={}", model, rlt);
		return convertCommonRlt(rlt);
	}
	
	/**
	 * 获取登录用户信息
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public CustomerInfoRlt getLoginUser(RequestHead head) throws Exception{
		ServiceResponse<CustomerInfoRlt> rlt= getUserInfo(head);
		if(rlt.isSuccess()){
			return rlt.getData();
		}else{
			logger.warn("ICustomerInfoService获取用户信息,model={},result={}",head,rlt);
			return null;
		}
	}
	
	
	/**
	 * 是否登录
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public boolean isLogin(RequestHead req) throws Exception{
		CheckLoginReq model = new CheckLoginReq();
		model.setMemberNo(WebUtil.getUserId(req.getToken()));
		model.setTokenId(WebUtil.getUserToken(req.getToken()));
		initBaseReq(req,model);
		CommonRlt<Boolean> rlt = p2pLoginService.doIsLogin(model);
		return rlt.getReturnCode()==Constants.SUCCESS&&rlt.getData();
	}
	
	/**
	 *  登录
	 * @param requestHead
	 * @param mobile
	 * @param password
	 * @return
	 */
	public ServiceResponse<LoginRlt> doLogin(RequestHead requestHead,String mobile,String password){
		LoginReq model = new LoginReq();
		model.setLoginName(mobile);
		model.setLoginPwd(password);
		initBaseReq(requestHead,model);
		CommonRlt<LoginRlt> rlt = p2pLoginService.doLogin(model);
		logger.info("UserService用户登录,req={},result={}", model, rlt);
		return convertCommonRlt(rlt);
	}
	/**
	 * 手势密码登录
	 * @param requestHead
	 * @param userId
	 * @param token
	 * @return
	 */
	public ServiceResponse<EmptyObject> doLogin(RequestHead head){
		CheckLoginReq model = new CheckLoginReq();
		initBaseReq(head,model);
		String userId = WebUtil.getUserId(head.getToken());
		String token  = WebUtil.getUserToken(head.getToken());
		model.setMemberNo(userId);
		model.setTokenId(token);
		CommonRlt<EmptyObject> rlt = p2pLoginService.delayToken(model);
		logger.info("iLoginService手势密码登录,参数={},result={}", model, rlt);
		return convertCommonRlt(rlt);
	}
	
	
	/**
	 * 用绑定账号登录
	 * @param head
	 * @param unionId
	 */
	public ServiceResponse<LoginRlt> bindLogin(RequestHead head,String unionId){
		try{
			BindLoginReq model = new BindLoginReq();
			initBaseReq(head,model);
			model.setfUnionid(unionId);
			model.setSysSource(SourceType.WECHAT.getText());
			CommonRlt<LoginRlt> rlt = p2pLoginService.doBindLogin(model);
			logger.info("ILoginService微信用户关联登录,参数={},result={}", model, rlt);
			return convertCommonRlt(rlt);
		}catch(Exception e){
			logger.warn("微信绑定失败:",e);
		}
		return null;
	}
	
	/**
	 * 绑定账号
	 * @param head
	 * @param unionId
	 * @return
	 */
	public void doBind(RequestHead head,String userId,String unionId){
		try{
			BindReq model = new BindReq();
			initBaseReq(head,model);
			model.setfName(SourceType.WECHAT.getText());
			model.setSysSource(SourceType.WECHAT.getText());
			model.setMemberNo(userId);
			model.setfUnionid(unionId);
			CommonRlt<EmptyObject> rlt = p2pRegisterService.doBind(model);
			logger.info("IRegisterService首次登录绑定微信关联关系, req={},result={}", model, rlt);
		}catch(Exception e){
			logger.warn("微信绑定失败:",e);
		}
	}
	
	
	/**
	 * 解除绑定账号
	 * @param head
	 */
	public void firedBind(RequestHead head){
		try{
			FiredBindReq model = new FiredBindReq();
			String userId = WebUtil.getUserId(head.getToken());
			initBaseReq(head,model);
			model.setMemberNo(userId);
			model.setSysSource(SourceType.WECHAT.getText());
			CommonRlt<EmptyObject> rlt = p2pRegisterService.doFiredBind(model);
			logger.info("ILoginService微信用户关联登录,参数={},result={}", model, rlt);
		}catch(Exception e){
			logger.warn("微信取消绑定失败:",e);
		}
	}
	
	
	/**
	 * 退出
	 * @param req
	 * @return
	 */
	public ServiceResponse<EmptyObject> doLogout(RequestHead req){
		LoginOutReq model = new LoginOutReq();
		String userId = WebUtil.getUserId(req.getToken());
		model.setMemberNo(userId);
		model.setTokenId(WebUtil.getUserToken(req.getToken()));
		initBaseReq(req,model);
		CommonRlt<EmptyObject> rlt = p2pLoginService.doLoginOut(model);
		logger.info("UserService用户登录,req={},result={}", model, rlt);
		firedBind(req);
		return convertCommonRlt(rlt);
	}
	
	/**
	 * 记录设备信息
	 * @param head
	 * @param deviceInfo
	 */
	public void doDeviceInfo(RequestHead head,DeviceInfoRequest deviceInfo) {
		try {
			DeviceInfoReq model = new DeviceInfoReq();
			model.setMemberNo(WebUtil.getUserId(head.getToken()));
			model.setDeviceId(deviceInfo.getDeviceId());
			model.setDeviceDetail(deviceInfo.getDeviceModel());
			model.setDeviceResolution(deviceInfo.getResolution());
			model.setToken(deviceInfo.getDeviceToken());
			model.setDeviceToken(deviceInfo.getDeviceToken());
			model.setSystemVersion(deviceInfo.getSystemVersion());
			this.initBaseReq(head, model);
			if(head.getToken()!=null){
				CustomerInfoReq customer = new CustomerInfoReq();
				customer.setMemberNo(WebUtil.getUserId(head.getToken()));
				this.initBaseReq(head, customer);
				CommonRlt<DeviceInfoRlt> rlt = p2pCustomerInfoService.getDeviceInfo(customer);
				if(rlt.getReturnCode()==Constants.SUCCESS){
					 if(rlt.getData()!=null){
						 p2pCustomerInfoService.updateDeviceInfo(model);
						 logger.info("iCustomerInfoService更新设备信息,参数={},result={}", deviceInfo, rlt);
					 }else{
						 p2pCustomerInfoService.createDeviceInfo(model);
						 logger.info("iCustomerInfoService创建设备信息,参数={},result={}", deviceInfo, rlt);
					 }
				}
			}else{
				CommonRlt<EmptyObject> rlt = p2pCustomerInfoService.createDeviceInfo(model);
				logger.info("iCustomerInfoService创建设备信息,参数={},result={}", deviceInfo, rlt);
			}
		} catch (ServiceException e) {
			logger.error("iCustomerInfoService处理设备信息异常,req={},参数={}", deviceInfo, e);
		}
	}
	
	/**
	 * 检测登录密码是否正确
	 * @param head
	 * @param mobile 手机号码
	 * @param pwd 密码
	 * @return
	 */
	public boolean docheckLoginPwd(RequestHead head,String mobile,String pwd){
		CheckLoginPwdReq model = new CheckLoginPwdReq();
		model.setLoginName(mobile);
		model.setLoginPwd(pwd);
		initBaseReq(head,model);
		CommonRlt<Boolean> ret = p2pLoginService.docheckLoginPwd(model);
		if(ret.getReturnCode()==0&&!ret.getData()){
			return false;
		}
		return true;
	}
	
	/**
	 * 检测是否为钱罐子用户
	 * @param head
	 * @param mobile 手机号码
	 * @return 
	 */
	public boolean isQgzUser(RequestHead head,String mobile){
		CheckMobileReq model = new CheckMobileReq();
		model.setLoginName(mobile);
		initBaseReq(head,model);
		CommonRlt<Boolean> registerRlt = p2pRegisterService.doCheckMobile(model);
		return  registerRlt.getReturnCode()==0&&!registerRlt.getData();
	}
	
	
	
	/**
	 * 检测密码是否正确
	 * @param head
	 * @param loginPwd
	 * @return
	 */
	public boolean doVerifyLoginPwd(RequestHead head,String loginPwd) {
		CheckLoginPwdByMemberNoReq model = new CheckLoginPwdByMemberNoReq();
		initBaseReq(head,model);
		String userId = WebUtil.getUserId(head.getToken());
		model.setMemberNo(userId);
		model.setLoginPwd(loginPwd);
		CommonRlt<Boolean> rlt = p2pLoginService.docheckLoginPwdByMemberNo(model);
		logger.info("ILoginService验证登录密码,model={},result={}", model, rlt);
		return rlt.getReturnCode()==0&&rlt.getData();
	}
	
	/**
	 * 修改登录密码
	 * @param head
	 * @param oldLoginPwd 旧密码
	 * @param newLoginPwd 新密码
	 */
	public ServiceResponse<EmptyObject> doModifyLoginPwd(RequestHead head, String oldLoginPwd,
			String newLoginPwd) {
		ModifyLoginPwdReq model = new ModifyLoginPwdReq();
		initBaseReq(head,model);
		String userId = WebUtil.getUserId(head.getToken());
		String token = WebUtil.getUserToken(head.getToken());
		model.setMemberNo(userId);
		model.setTokenId(token);
		model.setLoginPwd(oldLoginPwd);
		model.setLoginNewPwd(newLoginPwd);
		CommonRlt<EmptyObject> rlt = p2pPwdService.doModifyLoginPwd(model);
		logger.info("p2pPwdService修改登录密码,model={},result={}", model, rlt);
		return convertCommonRlt(rlt);
	}
	
	/**
	 * 重置登录密码
	 * @param head
	 * @param mobile 手机号码
	 * @param newLoginPwd 新密码
	 * @return
	 */
	public ServiceResponse<EmptyObject> doResetLoginPwd(RequestHead head,String mobile,String newLoginPwd) {
		ResetLoginPwdReq model = new ResetLoginPwdReq();
		initBaseReq(head,model);
		model.setLoginName(mobile);
		model.setLoginNewPwd(newLoginPwd);
		CommonRlt<EmptyObject> rlt = p2pPwdService.doResetLoginPwd(model);
		logger.info("UserService重置登录密码,model={},result={}", model, rlt);
		return convertCommonRlt(rlt);
	}
	
	/**
	 * 验证交易密码
	 * @param head
	 * @param payPwd 交易密码
	 * @return
	 */
	public boolean doCheckPayPwd(RequestHead head,String payPwd) {
		CheckPayPwdReq model = new CheckPayPwdReq();
		String userId = WebUtil.getUserId(head.getToken());
		model.setMemberNo(userId);
		model.setPayPwd(payPwd);
		initBaseReq(head,model);
		CommonRlt<Boolean> rlt = p2pPwdService.doCheckPayPwd(model);
		logger.info("iPwdService验证交易密码,model={},result={}", model, rlt);
		return rlt.getReturnCode()==0&&rlt.getData();
	}

	/**
	 * 修改交易密码
	 * @param head
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 */
	public ServiceResponse<EmptyObject> doModifyPayPwd(RequestHead head, String oldPwd,
			String newPwd) {
		ReSetPayPwdReq model = new ReSetPayPwdReq();
		initBaseReq(head,model);
		String userId = WebUtil.getUserId(head.getToken());
		model.setMemberNo(userId);
		model.setPayPwd(oldPwd);
		model.setPayNewPwd(newPwd);
		CommonRlt<EmptyObject> rlt = p2pPwdService.doReSetPayPwd(model);
		logger.info("p2pPwdService修改交易密码,model={},result={}", model, rlt);
		return convertCommonRlt(rlt);
	}
	
	
	/**
	 * 重置交易密码
	 * @param head
	 * @param mobile 手机号码
	 * @param newPwd 新密码
	 * @return
	 */
	public ServiceResponse<EmptyObject> doResetPayPwd(RequestHead head,String newPwd) {
		SetPayPwdReq model = new SetPayPwdReq();
		initBaseReq(head,model);
		String userId = WebUtil.getUserId(head.getToken());
		model.setMemberNo(userId);
		model.setPayNewPwd(newPwd);
		CommonRlt<EmptyObject> rlt = p2pPwdService.doSetPayPwd(model);
		logger.info("iPwdService重置交易密码,model={},result={}", model, rlt);
		return convertCommonRlt(rlt);
	}
	/**
	 * 查询用户会员等级信息
	 * @param head
	 * @param userId
	 * @return
	 */
	public MemberLevelInfoRlt doGetMemberLevelInfo(RequestHead head,String userId) {
		CustomerInfoReq model = new CustomerInfoReq();
		initBaseReq(head,model);
		model.setMemberNo(userId);
		CommonRlt<MemberLevelInfoRlt> rlt = p2pCustomerInfoService.doGetMemberLevelInfo(model);
		logger.info("ICustomerInfoService查询会员等级,model={},result={}", model, rlt);
		ServiceResponse<MemberLevelInfoRlt> memberLevelInfoRlt= convertCommonRlt(rlt);
		if(memberLevelInfoRlt.isSuccess()){
			return rlt.getData();
		}else{
			logger.warn("ICustomerInfoService查询会员等级,model={},result={}", model, memberLevelInfoRlt);
			return null;
		}
	}
	
	
	/**
	 * 
	 * @param commonRlt
	 * @return
	 */
	public  <T>ServiceResponse<T> convertCommonRlt(final CommonRlt<T> commonRlt){
		if(commonRlt.getReturnCode()==Constants.SUCCESS){
			return new ServiceResponse<T>(commonRlt.getData());
		}else{
			return new ServiceResponse<T>(convertError(commonRlt));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public  ErrorCode convertError(CommonRlt commonRlt){
		return new ErrorCodeSupport(commonRlt.getReturnCode(),commonRlt.getReturnMsg());
	}
	
	public  BaseResponse convertError(ReturnCode head){
		if(ResultMsgEnum.LOGINNAME_NO_EXIST.getReturnCode()==head.getCode() ){
			return new BaseResponse("userNotExists","用户不存在");
		}else if(ResultMsgEnum.LOGINNAME_EXIST.getReturnCode()==head.getCode()){
			return new BaseResponse("userExists","登录名已存在");
		}else if(ResultMsgEnum.LOGIN_PWD_ERROR.getReturnCode()==head.getCode()){
			return new BaseResponse("passwordNotRight","密码错误");
		}else if(ResultMsgEnum.LOGIN_OLDPWD_ERROR.getReturnCode()==head.getCode()){
			return new BaseResponse("passwordNotRight","原始密码错误");
		}else if(ResultMsgEnum.PAY_PWD_ERROR.getReturnCode()==head.getCode()){
			return new BaseResponse("payPwdNotRight","支付密码错误");
		}else if(ResultMsgEnum.PAY_PWD_LOCK.getReturnCode()==head.getCode()){
			return new BaseResponse("payPwdLock","支付密码输入错误次数已大于最大");
		}else if(ResultMsgEnum.LOGIN_PWD_LOCK.getReturnCode()==head.getCode()){
			return new BaseResponse("loginPwdLock","登录密码输入错误次数已大于最大");
		}else if(ResultMsgEnum.REFEREEUID_NOTEXIST.getReturnCode()==head.getCode()){
			return new BaseResponse("rcUserNotExists","推荐人不存在");
		}else if(ResultMsgEnum.NO_INFO.getReturnCode()==head.getCode()){
			return new BaseResponse("noInfo","未获取到数据");
		}else if(ResultMsgEnum.TOKEN_NOEXIST.getReturnCode()==head.getCode()){
			return new BaseResponse("tokenInvalide","token无效");
		}else if(ResultMsgEnum.STATUS_NOT_CORRENT.getReturnCode()==head.getCode()){
			return new BaseResponse("statusNotRight","数据状态不正确");
		}else if(ResultMsgEnum.NOT_BINDING.getReturnCode()==head.getCode()){
			return new BaseResponse("accountNotBind","账户没有绑定");
		}else if(ResultMsgEnum.MEMBERNO_NO_EXIST.getReturnCode()==head.getCode()){
			return new BaseResponse("membernoNotExists","用户不存在");
		}else if(ResultMsgEnum.SYSTEM_ERROR.getReturnCode()==head.getCode()){
			return ResponseUtil.getErrorServ();
		}else{
			return new BaseResponse(head.getCode(),head.getMessage());
		}
	}
	
	
	public void initBaseReq(RequestHead head,BaseReq model){
		model.setAppVersion(head.getApp_version());
		model.setSourceType(WebUtil.getSysType(head.getApp_key()));
	}
	
	public void setValue(RequestHead head,BaseReq model){
		//用户中心的用户数据正在分离，所以用户中心所有接口都做了调整
		// sign参数由可以为空变成不可为空	
		if(head.getApp_key().startsWith("investor")){
			model.setSystemType(SystemType.CHANNEL2.getText());
		} else if(head.getApp_key().startsWith("channel")){		
			model.setSystemType(SystemType.CHANNEL.getText());
		} else {
			model.setSystemType(SystemType.CHANNEL.getText());
		}
		logger.info("领会用户中心:SourceType={},SystemType={}",model.getSourceType(),model.getSystemType());
		logger.info("领会用户中心签名Key={}", getRedisKeyForSystemConfig("lh_userCenter_signKey"));
		model.setSign(RequestSignUtils.addSign(model,getRedisKeyForSystemConfig("lh_userCenter_signKey")));
	}
	
	/**
	 * INSERT INTO `t_system_config_new` 
	 * (`f_name`, `f_type`, `f_key`, `f_value`, `f_remark`, `f_crt_time`, `f_app_type`) 
	 * VALUES 
	 * ('领会用户中心签名Key', 'userCenter', 'lh_userCenter_signKey', 'M7TI1HEZvWqW69Hrpznns0MvnJcDxpc8', '领会用户中心签名KEY', now() ,0);
	 * COMMIT;
	 * @param key
	 * @return
	 */
	public String getRedisKeyForSystemConfig(String key){
		return "M7TI1HEZvWqW69Hrpznns0MvnJcDxpc8";
	}
	
}
