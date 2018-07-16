package com.linkwee.test.api.cim;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.CimProductUnrecordInvestRequest;
import com.linkwee.api.request.cim.CimSunburnPageListRequest;
import com.linkwee.api.request.cim.CimThumbsUpRequest;
import com.linkwee.api.request.cim.OrginfoaDetailRequest;
import com.linkwee.api.request.cim.OrginfoaPageListRequest;
import com.linkwee.api.response.cim.CimSunburnListResponse;
import com.linkwee.api.response.cim.OrginfoaDetailResponse;
import com.linkwee.api.response.cim.OrginfoaPageListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.model.acc.AcSaveBind;
import com.linkwee.web.model.cim.CimUnrecordInvestListResp;

public class CimOrgInfoAControllerTest extends BaseTest{

	@Test
	public void orginfoaPageListTest() throws Exception {
		OrginfoaPageListRequest orginfoaPageListRequest = new OrginfoaPageListRequest();
		orginfoaPageListRequest.setPageIndex(1);
		orginfoaPageListRequest.setPageSize(10);
		LOGGER.info("请求参数 orginfoaPageListRequest={}",JSONObject.toJSONString(orginfoaPageListRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/pageList",this.getToken(),OrginfoaPageListResponse.class,orginfoaPageListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void orginfoaDetailTest() throws Exception {
		OrginfoaDetailRequest orginfoaDetailRequest = new OrginfoaDetailRequest();
		orginfoaDetailRequest.setOrgCode("OPEN_A_PPMONEY_WEB");
		LOGGER.info("请求参数 orginfoaDetailRequest={}",JSONObject.toJSONString(orginfoaDetailRequest));
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/orginfoa/orginfoaDetail",this.getToken(),OrginfoaDetailResponse.class,orginfoaDetailRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void queryOrginfoaListTest() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/orginfoaList",this.getToken(),CimOrgInfoA.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void reportRecordTest() throws Exception {
		CimProductUnrecordInvestRequest cimProductUnrecordInvestRequest = new CimProductUnrecordInvestRequest();
		cimProductUnrecordInvestRequest.setPlatfrom("OPEN_A_PPMONEY_WEB");
		cimProductUnrecordInvestRequest.setProductDeadLineValue(30);
		cimProductUnrecordInvestRequest.setInvestAmt(new BigDecimal(5000));
		cimProductUnrecordInvestRequest.setInvestTime(new Date());
		cimProductUnrecordInvestRequest.setInvestImg("cddc277a2434d22400bd82e8ae6e9784,cddc277a2434d22400bd82e8ae6e9784,cddc277a2434d22400bd82e8ae6e9784");
		cimProductUnrecordInvestRequest.setShareStatus(1);
		LOGGER.info("请求参数 cimProductUnrecordInvestRequest={}",JSONObject.toJSONString(cimProductUnrecordInvestRequest));
		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/reportRecord",this.getToken(),BaseResponse.class,cimProductUnrecordInvestRequest);
		LOGGER.debug(baseResponse.toString());
	}	
	
	@Test
	public void testUnrecordPageList() throws Exception {
		PaginatorRequest req = new  PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		req.setOrder(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/unrecordPageList",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}	
	
	
	@Test
	public void testSunburnPageList() throws Exception {
		CimSunburnPageListRequest req = new  CimSunburnPageListRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		req.setType(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/sunburnPageList",this.getToken(),CimSunburnListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testThumbsUp() throws Exception {
		CimThumbsUpRequest req = new CimThumbsUpRequest();
		req.setId(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/thumbsUp",this.getToken(),CimSunburnListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}	
	

	@Test
	public void testSunburn() throws Exception {
		CimThumbsUpRequest req = new CimThumbsUpRequest();
		req.setId(245);
		req.setImage("123456789");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/sunburn",this.getToken(),CimSunburnListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}	
	
	
	@Test
	public void testSunburnDetail() throws Exception {
		CimThumbsUpRequest req = new CimThumbsUpRequest();
		req.setId(315);
		
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/orginfoa/sunburnDetail",this.getToken(),CimUnrecordInvestListResp.class,req);
		LOGGER.debug(baseResponse.toString());
	}	
	
	@Test
	public void testOrgAtotalAmt() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/orginfoa/orgAtotalAmt",this.getToken(),CimSunburnListResponse.class);
		LOGGER.debug(baseResponse.toString());
	}	
	
	@Test
	public void testSaveBankCard() throws Exception {
		//请求参数：登录的手机号、持卡人姓名、身份证号、银行卡号、开户行名、地区、银行卡预留手机号、交易密码
		AcSaveBind req = new AcSaveBind();
		req.setMobile("18603027025");
		req.setUserName("战三");
		req.setIdCard("441025687456230");
		req.setBankCard("8656458658954625");
		req.setKaihuhang("开户行名");
		req.setRegion("深圳市南山区科技园光明路12号");
		req.setReserveMobile("13510002322");
		req.setTranPwd("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/kareco/saveBankCard",this.getToken(),CimUnrecordInvestListResp.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void getBankCardInfo() throws Exception {
		//请求参数：登录的手机号、持卡人姓名、身份证号、银行卡号、开户行名、地区、银行卡预留手机号、交易密码
		AcSaveBind req = new AcSaveBind();
		req.setMobile("18603027024");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/kareco/getBankCardInfo",this.getToken(),AcSaveBind.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void getsaveAddress() throws Exception {
		//请求参数：登录的手机号、持卡人姓名、身份证号、银行卡号、开户行名、地区、银行卡预留手机号、交易密码
		AcSaveBind req = new AcSaveBind();
		req.setMobile("18603027024");
		req.setConMobile("13699996111");
		req.setAddress("香港投铜锣湾123");
		req.setConsignee("张三收");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/kareco/saveAddress",this.getToken(),AcSaveBind.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
}
