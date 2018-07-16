package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.CustomerInfoReq;
import cn.xn.user.domain.CustomerInfoRlt;
import cn.xn.user.domain.LoginOutReq;
import cn.xn.user.domain.ResetLoginPwdReq;
import cn.xn.user.service.ICustomerInfoService;
import cn.xn.user.service.ILoginService;
import cn.xn.user.service.IPwdService;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.Constants;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.UserAccountInfo;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.InvestorUserInfoService;
import com.linkwee.web.service.SystemConfigService;
import com.linkwee.web.service.UserAccountInfoService;
import com.linkwee.web.service.UserInfoTCService;
import com.linkwee.web.util.BaseReqTCUtil;
import com.xiaoniu.account.domain.UserBindCardReq;
import com.xiaoniu.account.domain.result.UserBindCardRlt;
import com.xiaoniu.account.service.IPrepare2PayService;
import com.xiaoniu.account.utils.SignUtils;

/**
 * Created by lenli on 2016/5/25.
 *
 * @Author Libin
 * @Date 2016/5/25
 */
@Service(value = "userInfoTCService")
public class UserInfoTCServiceImpl implements UserInfoTCService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserInfoTCServiceImpl.class);

    @Resource
    private ICustomerInfoService p2pCustomerInfoService;

    @Resource
    private IPrepare2PayService p2pPrepare2PayService;

    @Resource
    private SystemConfigService systemConfigService;

    @Resource
    private ILoginService loginService;

    @Resource
    private IPwdService pwdService;
    @Resource InvestorUserInfoService investorUserInfoService;
    @Resource
    private UserAccountInfoService userAccountInfoService;
    


    /**
     * 根据领会平台用户ID查询用户信息
     *
     * @param customerId
     * @return
     * @throws Exception
     */
    @Override
    public CustomerInfoRlt getUserInfo(String customerId) throws Exception {
        CustomerInfoReq customerInfoReq = new CustomerInfoReq();
        customerInfoReq.setMemberNo(customerId);
        BaseReqTCUtil.initializeBaseReqForUser(customerInfoReq);
        CommonRlt<CustomerInfoRlt> customerInfoRltCommonRlt = p2pCustomerInfoService.getUserInfo(customerInfoReq);
        CustomerInfoRlt customerInfoRlt = customerInfoRltCommonRlt.getData();
        LOGGER.info(customerInfoRltCommonRlt.getReturnMsg());
        return customerInfoRlt;
    }

    @Deprecated
    @Override
    public boolean logout(String userId) throws Exception {
        String partnerId= systemConfigService.getValuesByKey(Constants.account_partnerId);
        String signKey = systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY);
        LoginOutReq loginOutReq = new LoginOutReq();
        BaseReqTCUtil.initializeBaseReqForUser(loginOutReq);


//        loginService.doLoginOut()
        return false;
    }

    /**
     * 查询客户银行卡绑定信息
     * @param customerId
     * @throws Exception
     */
    @Override
    public UserBindCardRlt findUserBindCardById(String customerId) throws Exception {
        String partnerId= systemConfigService.getValuesByKey(Constants.account_partnerId);
        String signKey = systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY);
        String charset = "UTF-8";
        UserBindCardReq userBindCardReq = new UserBindCardReq();
        userBindCardReq.setPartnerId(partnerId);
        userBindCardReq.setUserId(customerId);
        userBindCardReq.setCharset(charset);
        userBindCardReq.setSign(SignUtils.addSign(userBindCardReq,userBindCardReq.getCharset(),signKey));
        com.xiaoniu.account.domain.result.CommonRlt<UserBindCardRlt> invokeRet = p2pPrepare2PayService.getUserBindCardNew(userBindCardReq);
        LOGGER.info("查询客户银行卡绑定信息参数SINGKEY:{}",signKey);
        LOGGER.info("查询客户银行卡绑定信息参数:{},结果为:{}",userBindCardReq,invokeRet);
        if(invokeRet.getReturnCode().intValue()==0){
            return invokeRet.getData();
        }
        if(invokeRet.getReturnCode().intValue() == 30001){
            UserBindCardRlt userBindCardRlt = new UserBindCardRlt();
            userBindCardRlt.setBankName(invokeRet.getReturnMsg());
            userBindCardRlt.setBankCode("");
            return userBindCardRlt;
        }
        return null;
    }

    /**
     * 更新用户登录密码
     * @param userId
     * @param newPassword
     * @param userType  cn.xn.user.enums.SystemType
     * @return
     * @throws Exception
     */
    @Override
    public CommonTCSResult resetUserPasswd(String userId, String newPassword, String userType) throws Exception {
        CommonTCSResult commonTCSResult = new CommonTCSResult();
        String partnerId= systemConfigService.getValuesByKey(Constants.account_partnerId);
        String signKey = systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY);
        ResetLoginPwdReq resetLoginPwdReq = new ResetLoginPwdReq();
        resetLoginPwdReq.setLoginName(userId);
        resetLoginPwdReq.setLoginNewPwd(newPassword);
        BaseReqTCUtil.initializeBaseReqForUser(resetLoginPwdReq);
        resetLoginPwdReq.setSign(BaseReqTCUtil.generateSign(resetLoginPwdReq,signKey));
        resetLoginPwdReq.setSystemType(userType);
        CommonRlt commonRlt = pwdService.doResetLoginPwd(resetLoginPwdReq);
        commonTCSResult.setCode(commonRlt.getReturnCode());
        commonTCSResult.setMessage(commonRlt.getReturnMsg());
        commonTCSResult.setData(commonRlt.getData());
        LOGGER.info(String.format("code=%d|message=%s",commonTCSResult.getCode(),commonTCSResult.getMessage()));
        return commonTCSResult;
    }
    
    /**
     * 查询客户银行卡绑定信息
     * @param userId
     * @throws Exception
     */
    
    
	@Override
	@Transactional(value="transactionManager",propagation = Propagation.NOT_SUPPORTED)  
    public void generatorInvestorAccountInfoFile() throws Exception {
    	Page<InvestorUserInfo> page  = new Page<InvestorUserInfo>(1,100); //第一页，默认每页100条
    	Map<String,Object> conditions = new HashMap<String,Object>();
    	conditions.put("status", 0);
    	PaginatorResponse<InvestorUserInfo> investorPage = investorUserInfoService.queryInvestorUserInfo(page, conditions);
    	List<InvestorUserInfo> investorList =investorPage.getDatas(); 
    	
    	//签名配置参数
    	String charset = "UTF-8";
    	String partnerId= systemConfigService.getValuesByKey(Constants.account_partnerId);
        String signKey = systemConfigService.getValuesByKey(Constants.TRANS_MD5_SIGN_KEY);
    	
    	
    	 wirtieAccInfo(investorList,partnerId,signKey,charset);
    	
    	for(int i=2;i<=investorPage.getPageCount();i++){
    		page  = new Page<InvestorUserInfo>(i,100); //第一页，默认每页100条
    		investorPage = investorUserInfoService.queryInvestorUserInfo(page, conditions);
    		investorList =investorPage.getDatas(); 
    		wirtieAccInfo(investorList,partnerId,signKey,charset);
    	}
    }

	@SuppressWarnings("static-access")
	public void wirtieAccInfo(List<InvestorUserInfo> investorList,String partnerId,String signKey,String charset) throws InterruptedException {
		List<UserAccountInfo> accountInfoList = new ArrayList<UserAccountInfo>();
		if(investorList ==null || (investorList !=null &&investorList.size() < 1)){
			LOGGER.info("任务完成，已无需要查询的客户信息");
		}
		for(InvestorUserInfo investor :investorList){
			 UserBindCardReq userBindCardReq = new UserBindCardReq();
	         userBindCardReq.setPartnerId(partnerId);
	         userBindCardReq.setUserId(investor.getUserId());
	         userBindCardReq.setCharset(charset);
	         userBindCardReq.setSign(SignUtils.addSign(userBindCardReq,userBindCardReq.getCharset(),signKey));
    	
    	com.xiaoniu.account.domain.result.CommonRlt<UserBindCardRlt> invokeRet = p2pPrepare2PayService.getUserBindCardNew(userBindCardReq);
        LOGGER.info("查询客户银行卡绑定信息参数SINGKEY:{}",signKey);
        LOGGER.info("查询客户银行卡绑定信息参数:{},结果为:{}",userBindCardReq,invokeRet);
        UserAccountInfo accInfo = new UserAccountInfo();
        accInfo.setUserId(investor.getUserId());
    	accInfo.setMobile(investor.getMobile());
	        if(invokeRet.getReturnCode().intValue()==0){
	        	 UserBindCardRlt userBindCardRlt =  invokeRet.getData();
	             accInfo.setUserName(userBindCardRlt.getUserName());
	             accInfo.setIdCard(userBindCardRlt.getIdentityCard());
	             accInfo.setBankCode(userBindCardRlt.getBankCode());
	             accInfo.setBankName(userBindCardRlt.getBankName());
	             accInfo.setBankCard(userBindCardRlt.getUserCardNo());
	        }if(invokeRet.getReturnCode().intValue() == 30001){//用户未绑卡
	        	accInfo.setRemark("未绑卡");
	        }
	        accountInfoList.add(accInfo);
	        //
	    	Thread current = Thread.currentThread();  
	    	current.sleep(1000);
        
    	}//该页调用完成
    	
    	//将拼接的内容追加到文件中    	
    	//String path = TxtFileUtil.getFilePath();
    	//TxtFileUtil.appendContent(path,toWriteContent.toString());
		if(accountInfoList.size()>0){
		userAccountInfoService.addBatch(accountInfoList);
		}
        
	}
    
    
}
