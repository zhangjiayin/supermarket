/*package com.linkwee.test.funds.ifast;

import java.lang.reflect.Type;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.test.BaseTest;
import com.linkwee.xoss.funds.sdk.ifast.base.IfastBaseRespons;
import com.linkwee.xoss.funds.sdk.ifast.request.GetBankCodeRequest;
import com.linkwee.xoss.funds.sdk.ifast.response.GetBankCodeResponse;

public class BastTest extends BaseTest {
	
	@Test
	public void test(){
		IfastBaseRespons<GetBankCodeRequest, GetBankCodeResponse> ifastBaseResponsNew = new IfastBaseRespons<GetBankCodeRequest, GetBankCodeResponse>();
		GetBankCodeRequest getBankCodeRequest = new GetBankCodeRequest();
		getBankCodeRequest.setBankNumber("123456789");
		GetBankCodeResponse getBankCodeResponse = new GetBankCodeResponse();
		getBankCodeResponse.setBankCode("ABCD");
		getBankCodeResponse.setBankName("中国银行");
		
		ifastBaseResponsNew.setCode("0000");
		ifastBaseResponsNew.setData(getBankCodeResponse);
		ifastBaseResponsNew.setMessage("请求成功");
		ifastBaseResponsNew.setRequest(getBankCodeRequest);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(ifastBaseResponsNew);
		LOGGER.info("对象转json   jsonData={}",jsonData);
		
		Type listType = new TypeToken<IfastBaseRespons<GetBankCodeRequest, GetBankCodeResponse>>(){}.getType();
		IfastBaseRespons<GetBankCodeRequest, GetBankCodeResponse>  ifastBaseRespons = gson.fromJson(jsonData,listType);
		LOGGER.info("BankCode={}",ifastBaseRespons.getData().getBankCode());
	    System.out.println(ifastBaseRespons.getData().getBankCode());
	}

}
*/