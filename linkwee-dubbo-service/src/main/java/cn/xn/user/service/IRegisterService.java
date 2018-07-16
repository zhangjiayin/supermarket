/*
 * 文件名: IRegisterService.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package cn.xn.user.service;

import cn.xn.user.domain.BindReq;
import cn.xn.user.domain.CheckMobileReq;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.FiredBindReq;
import cn.xn.user.domain.RegisterReq;
import cn.xn.user.domain.RegisterRlt;

/**
 *
 * @类描述：注册服务
 *
 * @创建人：luohao
 *
 * @创建时间：2016年8月3日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public interface IRegisterService {
    
    /**
     * 注册接口
     * @param model
     * @return 
     * @throws ServiceException
     */
    CommonRlt<RegisterRlt> doRegister(RegisterReq model);

    /**
     * 检查手机号是否已注册
     * @param model
     * @return boolean 值为true表明没注册，值为false表明已经注册
     * @throws ServiceException
     */
    CommonRlt<Boolean> doCheckMobile(CheckMobileReq model);
    
    
    /**
     * 第三方账户绑定
     * @param model
     * @return
     * @throws ServiceException
     */
    CommonRlt<EmptyObject> doBind(BindReq model);
    
    
    /**
     * 解除第三方账户绑定
     * @param model
     * @return
     * @throws ServiceException
     */
    CommonRlt<EmptyObject> doFiredBind(FiredBindReq model);
    
}
