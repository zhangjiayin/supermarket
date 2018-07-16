/*
 * 文件名: ILoginService.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package cn.xn.user.service;

import cn.xn.user.domain.BindLoginReq;
import cn.xn.user.domain.CheckLoginPwdByMemberNoReq;
import cn.xn.user.domain.CheckLoginPwdReq;
import cn.xn.user.domain.CheckLoginReq;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.LoginErrorTimeReq;
import cn.xn.user.domain.LoginErrorTimeRlt;
import cn.xn.user.domain.LoginOutReq;
import cn.xn.user.domain.LoginReq;
import cn.xn.user.domain.LoginRlt;
import cn.xn.user.exception.ServiceException;


/**
 *
 * @类描述：登录服务
 *
 * @创建人：luohao
 *
 * @创建时间：2016年8月3日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public interface ILoginService {
    
    /**
     * 登录接口
     * 
     * @param model
     * @return
     * @throws ServiceException
     */
    CommonRlt<LoginRlt> doLogin(LoginReq model);
    
    /**
     * 验证是否登录接口
     * 
     * @param model
     * @return true:token有效，false：token无效
     * @throws ServiceException
     */
    CommonRlt<Boolean> doIsLogin(CheckLoginReq model);

    /**
     * 退出登录接口
     * 
     * @param model
     * @return
     * @throws ServiceException
     */
    CommonRlt<EmptyObject> doLoginOut(LoginOutReq model);


    
    CommonRlt<EmptyObject> delayToken(CheckLoginReq model); 
    

    CommonRlt<LoginRlt> doBindLogin(BindLoginReq model);    

    /**
     * 根据用户登录名验证登录密码是否正确
     * 
     * @param model
     * @return true:密码正确，false：密码错误
     * @throws ServiceException
     */
    CommonRlt<Boolean> docheckLoginPwd(CheckLoginPwdReq model);
    
    /**
     *根据用户ID验证登录密码是否正确
     * 
     * @param model
     * @return true:密码正确，false：密码错误
     * @throws ServiceException
     */
    CommonRlt<Boolean> docheckLoginPwdByMemberNo(CheckLoginPwdByMemberNoReq model);
    
	public CommonRlt<LoginErrorTimeRlt> getLoginErrorTime(LoginErrorTimeReq req) ;
}
