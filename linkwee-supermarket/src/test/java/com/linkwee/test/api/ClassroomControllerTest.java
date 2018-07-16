package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.response.MorningPaperResponse;
import com.linkwee.api.response.mc.ClassroomPageListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.mc.Classroom;

public class ClassroomControllerTest extends BaseTest{
	
	@Test//课程列表
	public void queryClassroomList() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/classroom/queryClassroomList",this.getToken(),ClassroomPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//课程列表
	public void queryClassRoomDetail() throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("id", "1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/classroom/queryClassRoomDetail",this.getToken(),ClassroomPageListResponse.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//精选推荐列表
	public void selectedRecomendList() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/classroom/selectedRecomend/list/4.5.0",this.getToken(),Classroom.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//精选推荐详情
	public void selectedRecomendDetail() throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("id", "32");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/classroom/selectedRecomend/detail/4.5.0",this.getToken(),Classroom.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}

	@Test//每日早报
	public void morningPaper() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/classroom/morningPaper/4.5.0",this.getToken(),MorningPaperResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
}
