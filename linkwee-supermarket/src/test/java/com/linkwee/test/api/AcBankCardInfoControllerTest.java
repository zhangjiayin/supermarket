package com.linkwee.test.api;

import org.junit.Test;

import com.linkwee.api.request.acc.AddBankCardRequest;
import com.linkwee.api.response.acc.AcAccountBindReponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.openapi.request.AccountBankRequest;
import com.linkwee.openapi.response.AccountBankReponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class AcBankCardInfoControllerTest extends BaseTest{
	
	
	@Test
	public void testAddBankCard() throws Exception {
		AddBankCardRequest cardreq = new AddBankCardRequest();
//		cardreq.setBankCard("6226096552765177");
//		cardreq.setBankCard("5268550796294726");//平安信用卡
		cardreq.setBankCard("6226097805198232");//交通银行信用卡
		cardreq.setBankCode("CMB");
		cardreq.setBankName("中国招商银行");
		cardreq.setCity("深圳");
		cardreq.setIdCard("44142219870510263X");
		cardreq.setMobile("18603027024");
		cardreq.setUserName("陈佳良");
		cardreq.setKaihuhang("南山科技园支行");
		cardreq.setBankId(5);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/addBankCard",this.getToken(),AcAccountBindReponse.class,cardreq);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testopenapi() throws Exception {
		AccountBankRequest cardreq = new AccountBankRequest();
		cardreq.setUserId("efdf40a3aa384e41a8d0cfa45fa2cda1");
		BaseResponse baseResponse = TestHelper.remote(this.getUrl(PathEnum.LOCALHOST),"/openapi/account/bank",AccountBankReponse.class,cardreq);
		LOGGER.debug(baseResponse.toString());
	}
}
