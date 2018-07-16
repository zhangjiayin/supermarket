package com.xiaoying.test;

import org.junit.Test;

import com.openpltsdk.xiaoying.open.XYOpenSDK;
import com.openpltsdk.xiaoying.open.XYResponse;
import com.openpltsdk.xiaoying.open.register.XYBankCardBindReq;
import com.openpltsdk.xiaoying.open.register.XYBankCardCheckReq;

/**
 * Created by bbg on 26/12/2016.
 */
public class XYOpenSDKBankCardTest {
    @Test
    public void bankCardCheckTest() throws Exception {
        XYBankCardCheckReq xyBankCardCheckReq = new XYBankCardCheckReq();
        xyBankCardCheckReq.setLoanOrderId(0);
        xyBankCardCheckReq.setOpenId("");
        xyBankCardCheckReq.setCode("CMB");
        xyBankCardCheckReq.setCardNo("");
        xyBankCardCheckReq.setReservedMobile("13927479113");
        xyBankCardCheckReq.setProvince("广东");
        xyBankCardCheckReq.setCity("深圳");

        XYResponse response = XYOpenSDK.sharedInstance().apiRequest(xyBankCardCheckReq);
    }


    @Test
    public void bandCardBindTest() throws Exception {
        XYBankCardBindReq xyBankCardBindReq = new XYBankCardBindReq();
        xyBankCardBindReq.setOpenId("");
        xyBankCardBindReq.setLoanOrderId(0);
        xyBankCardBindReq.setSmsCode("");
        xyBankCardBindReq.setVerifiedData("");

        XYResponse response = XYOpenSDK.sharedInstance().apiRequest(xyBankCardBindReq);
    }


}
