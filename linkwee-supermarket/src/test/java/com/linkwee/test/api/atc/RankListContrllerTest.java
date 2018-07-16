package com.linkwee.test.api.atc;

import org.junit.Test;

import com.linkwee.act.rankList.model.Ranklist;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;

public class RankListContrllerTest extends BaseTest{

	@Test
	public void queryRankListDataTest() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(PathEnum.LOCALHOST),"/api/act/rankList/zyb/rank",this.getToken(),Ranklist.class);
		LOGGER.debug(baseResponse.toString());
	} 
	
	@Test
	public void queryMyRankDataTest() throws Exception{
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/act/rankList/zyb/myRank",this.getToken(),BaseResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
}
