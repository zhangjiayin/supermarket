/*
 * 文件名: IPwdService.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package cn.xn.user.service;

import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.ModifyLoginPwdReq;
import cn.xn.user.domain.ResetLoginPwdReq;


/**
 *
 * @类描述：密码服务
 *
 * @创建人：luohao
 *
 * @创建时间：2016年8月3日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public interface IPwdService {
    
    /**
     * 重置登录密码操作
     * @param model
     * @return
     * @throws Exception
     */
    CommonRlt<EmptyObject> doResetLoginPwd(ResetLoginPwdReq model);
    
    /**
     * 修改登录密码操作
     * @param model
     * @return
     * @throws Exception
     */
    CommonRlt<EmptyObject> doModifyLoginPwd(ModifyLoginPwdReq model);
    
}
