package com.xiaoying.test;

import org.junit.Before;
import org.junit.Test;

import com.openpltsdk.xiaoying.open.XYOpenSDK;
import com.openpltsdk.xiaoying.open.XYResponse;
import com.openpltsdk.xiaoying.open.register.XYRegisterReq;
import com.openpltsdk.xiaoying.open.register.XYRegisterStatusReq;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
public class XYOpenSDKRegisterTest {
    @Before
    public void setup() throws Exception {
        String keyPath = "/Users/bbg/Workspace/xiaoying/git_repo/opensdk-php/certs/client/client.p12";
        String certPath = "/Users/bbg/Workspace/xiaoying/git_repo/opensdk-php/certs/ca.keystore";
        String keypwd = "open";
        String certPwd = "kLStEz";
        XYOpenSDK.sharedInstance().init(keyPath, keypwd, certPath, certPwd);
        XYOpenSDK.sharedInstance().setAppId("WO_AI_KA_TEST");
    }


    @Test
    public void registerTest() throws Exception {
        XYRegisterReq xyRegisterReq = new XYRegisterReq();
        xyRegisterReq.setMobile("13927479113");
        xyRegisterReq.setName("林");
        xyRegisterReq.setIdType(1);
        xyRegisterReq.setIdNo("443426198107121812");
        xyRegisterReq.setEmail("2222222@qq.com");

        XYRegisterReq.IdPictures pictures = xyRegisterReq.new IdPictures();
        pictures.setFront("http://www.th7.cn/d/file/p/2015/06/18/f69d127454bae1b5a257854af6548512.png");
        pictures.setBack("http://www.th7.cn/d/file/p/2015/06/18/f69d127454bae1b5a257854af6548512.png");
        xyRegisterReq.setIdPictures(pictures);

        XYResponse response = XYOpenSDK.sharedInstance().apiRequest(xyRegisterReq);
    }


    @Test
    public void registerStatusTest() throws Exception {
        XYRegisterStatusReq xyRegisterStatusReq = new XYRegisterStatusReq();
        xyRegisterStatusReq.setOpenId("");

        XYResponse response = XYOpenSDK.sharedInstance().apiRequest(xyRegisterStatusReq);
    }

}
