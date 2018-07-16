package com.linkwee.test.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.response.AccountBookStatisticResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.CrmUserAccountBook;

public class AccountBookTest extends BaseTest {
   
    @Test
	public void statistics() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/accountbook/statistics/4.5.0",this.getToken(),AccountBookStatisticResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
    
    @Test
	public void investingList() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/accountbook/investing/list/4.5.0",this.getToken(),CrmUserAccountBook.class);
		LOGGER.debug(baseResponse.toString());
	}
    
    @Test
	public void investingDetail() throws Exception {
    	Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("id", "1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/accountbook/investing/detail/4.5.0",this.getToken(),CrmUserAccountBook.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
    
    @Test
	public void investingEdit() throws Exception {
    	CrmUserAccountBook requset = new CrmUserAccountBook();
    	/*requset.setId(1);*/
    	requset.setInvestAmt(new BigDecimal("100.50"));
    	requset.setProfit(new BigDecimal("10.01"));
    	requset.setInvestDirection("SSSSSSSSSSSSS");
    	requset.setRemark("备注");
    	requset.setStatus(true);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/accountbook/investing/edit/4.5.0",this.getToken(),CrmUserAccountBook.class,requset);
		LOGGER.debug(baseResponse.toString());
	}
}
