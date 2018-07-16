package com.linkwee.web.service;

import cn.xn.user.domain.CustomerInfoRlt;

import com.linkwee.web.response.CommonTCSResult;
import com.xiaoniu.account.domain.result.UserBindCardRlt;

/**
 * Created by lenli on 2016/5/25.
 *从技术平台中获得用户相关信息
 * @Author Libin
 * @Date 2016/5/25
 */
public interface UserInfoTCService {

    /**
     * 根据领会平台用户ID查询用户信息
     * @return
     * @throws Exception
     */
    public CustomerInfoRlt getUserInfo(String customerId) throws Exception;

    /**
     * 退出APP登录
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean logout(String userId) throws Exception;


    public UserBindCardRlt findUserBindCardById(String customerId) throws Exception;

    /**
     * 更新用户登录密码
     * @param userId
     * @param newPassword
     * @return
     * @throws Exception
     */
    public CommonTCSResult resetUserPasswd(String userId, String newPassword, String userType) throws Exception;
    
    /**
     * 用户银行信息
     */
    public void generatorInvestorAccountInfoFile() throws Exception ;

}
