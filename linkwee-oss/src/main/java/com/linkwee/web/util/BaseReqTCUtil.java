package com.linkwee.web.util;


import cn.xn.user.domain.BaseReq;
import com.linkwee.core.constant.Constants;
import com.xiaoniu.account.utils.SignUtils;

/**
 * Created by lenli on 2016/5/25.
 *构建一个调用技术平台中心的服务基本请求对象
 * @Author Libin
 * @Date 2016/5/25
 */
public class BaseReqTCUtil {

    public static void  initializeBaseReqForAccount(){
    }

    /**
     * 初始化技术平台中心用户基本请求信息对象
     * @param baseReq
     */
    public static void initializeBaseReqForUser(BaseReq baseReq){
        baseReq.setAppVersion(Constants.APP_VERSION);
        baseReq.setSourceType("Channel");
        baseReq.setSystemType("QGZ");
    }

    public static String generateSign(Object request,String signKey){
        return SignUtils.addSign(request,"UTF-8",signKey);
    }
}
