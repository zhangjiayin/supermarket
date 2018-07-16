package com.linkwee.web.remote;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.ErrorCodeSupport;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.Constants;
import com.linkwee.web.util.ResponseUtil;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.UserAuthenticationRlt;
import com.xiaoniu.account.service.IUserAuthenticationService;
import com.xiaoniu.account.utils.enums.ResultMsgEnum;

@Component
public class PayCenterHandler {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IUserAuthenticationService p2pUserAuthenticationService;
	
	
	public ServiceResponse<UserAuthenticationRlt> queryAuthentication(String userId) {
		CommonRlt<UserAuthenticationRlt> rlt = p2pUserAuthenticationService
				.queryAuthentication(userId);
		return convertCommonRlt(rlt);
	}
	
	public boolean isAuthentication(String userId){
		ServiceResponse<UserAuthenticationRlt> rlt = queryAuthentication(userId);
		if(rlt.isSuccess()){
			return rlt.getData().isAuthentication();
		}else{
			logger.warn("调用iUserAuthenticationService错误:code={},msg={}",rlt.getCode(),rlt.getMessage());
			return false;
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
		if(ResultMsgEnum.DATA_VERSION_ERROR.getReturnCode()==head.getCode() ){
			return new BaseResponse("dataVerionError","数据版本问题");
		}else if(ResultMsgEnum.NO_INFO.getReturnCode()==head.getCode()){
			return new BaseResponse("dataNotFund","数据不存在");
		}else if(ResultMsgEnum.STATUS_NOT_CORRENT.getReturnCode()==head.getCode()){
			return new BaseResponse("statusNotRight","数据状态不正确");
		}else if(ResultMsgEnum.USER_EXIST.getReturnCode()==head.getCode()){
			return new BaseResponse("userAccountNotExists","用户资产账户存在");
		}else if(ResultMsgEnum.CREATE_USER_FAILE.getReturnCode()==head.getCode()){
			return new BaseResponse("createUserAccountError","创建用户资产账户失败");
		}else if(ResultMsgEnum.DATA_NOT_SAME.getReturnCode()==head.getCode()){
			return new BaseResponse("dataNotSame","数据不一致");
		}else if(ResultMsgEnum.USER_NOT_SAME.getReturnCode()==head.getCode()){
			return new BaseResponse("userNotSame","用户不一致");
		}else if(ResultMsgEnum.AMOUNT_NOT_ENOUGH.getReturnCode()==head.getCode()){
			return new BaseResponse("amountNotEnouch","金额不足");
		}else if(ResultMsgEnum.RETURN_AMOUNT_ERROR.getReturnCode()==head.getCode()){
			return new BaseResponse("returnAmountError","回款本金大于投资本金");
		}else if(ResultMsgEnum.RETURN_ING.getReturnCode()==head.getCode()){
			return new BaseResponse("returnIng","打款处理中");
		}else if(ResultMsgEnum.SETTLE_FAILE.getReturnCode()==head.getCode()){
			return new BaseResponse("settleFaile","打款失败，需要解冻或查询打款结果");
		}else if(ResultMsgEnum.IN_ALREADY_SUCCESS.getReturnCode()==head.getCode()){
			return new BaseResponse("inAlreadySuccess","订单充值已成功");
		}else if(ResultMsgEnum.ALREADY_SUCCESS.getReturnCode()==head.getCode()){
			return new BaseResponse("alreadySuccess","投资或回款已成功");
		}else if(ResultMsgEnum.SYSTEM_ERROR.getReturnCode()==head.getCode()){
			return ResponseUtil.getErrorServ();
		}else{
			return new BaseResponse(head.getCode(),head.getMessage());
		}
	}
}
