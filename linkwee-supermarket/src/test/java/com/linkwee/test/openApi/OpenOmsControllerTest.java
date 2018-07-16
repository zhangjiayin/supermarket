package com.linkwee.test.openApi;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.openapi.request.OmsAccountBindRequest;
import com.linkwee.openapi.request.OmsAccountExistRequest;
import com.linkwee.openapi.request.OmsInvestmentListRedirectRequest;
import com.linkwee.openapi.request.OmsProductDetailRedirectRequest;
import com.linkwee.openapi.response.AccountExistResponse;
import com.linkwee.openapi.response.OmsProductResponse;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.OrgEnum;
import com.linkwee.test.enums.PathEnum;

public class OpenOmsControllerTest extends BaseTest {

	/**
	 * 开放平台接入-产品列表
	 * @throws Exception
	 */
	@Test
	public void productList() throws Exception {
		
		PaginatorRequest paginatorRequest = new PaginatorRequest();
		paginatorRequest.setPageIndex(1);
		paginatorRequest.setSort(2);
		paginatorRequest.setOrder(1);
		LOGGER.info("开放平台接入-产品列表：paginatorRequest={}",JSONObject.toJSONString(paginatorRequest));
		BaseResponse baseResponse = TestHelper.remote(OrgEnum.OPEN_IN_YOUXINQIANBAO_WEB,this.getUrl(PathEnum.LOCALHOST),"/openapi/oms/product/list",OmsProductResponse.class,paginatorRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 开放平台接入-第三方查询用户是否在领会已存在
	 * @throws Exception
	 */
	@Test
	public void accountExist() throws Exception {
		
		OmsAccountExistRequest omsAccountExistRequest = new OmsAccountExistRequest();
		omsAccountExistRequest.setIdCard("420222199012287351");
		omsAccountExistRequest.setMobile("15220203390");
		omsAccountExistRequest.setIfNeedOldBind(1);
		LOGGER.info("第三方查询用户是否在领会已存在：accountExistRequest={}",JSONObject.toJSONString(omsAccountExistRequest));
		BaseResponse baseResponse = TestHelper.remote(OrgEnum.OPEN_IN_YOUXINQIANBAO_WEB,this.getUrl(PathEnum.PRERELEASE),"/openapi/oms/account/exist",AccountExistResponse.class,omsAccountExistRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 开放平台接入-第三方绑定领会平台账户
	 * @throws Exception
	 */
	@Test
	public void accountBind() throws Exception {
		
		OmsAccountBindRequest omsAccountBindRequest = new OmsAccountBindRequest();
		omsAccountBindRequest.setBankCard("6222601854221454712151");
		omsAccountBindRequest.setBankCode("PAB");
		omsAccountBindRequest.setBankName("平安银行");
		omsAccountBindRequest.setIdCard("429510198806451324");
		omsAccountBindRequest.setMobile("15013674554");
		omsAccountBindRequest.setUserName("刘星");
		LOGGER.info("第三方绑定领会平台账户：omsAccountBindRequest={}",JSONObject.toJSONString(omsAccountBindRequest));
		BaseResponse baseResponse = TestHelper.remote(OrgEnum.OPEN_IN_YOUXINQIANBAO_WEB,this.getUrl(PathEnum.LOCALHOST),"/openapi/oms/account/bind",BaseResponse.class,omsAccountBindRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 开放平台接入-第三方产品详情页面跳转
	 * @throws Exception
	 */
	@Test
	public void productDetailRedirect() throws Exception {
		
		OmsProductDetailRedirectRequest omsProductDetailRedirectRequest = new OmsProductDetailRedirectRequest();
		omsProductDetailRedirectRequest.setProductId("37C7047D41394775B989CCDDBAAE8506");
		omsProductDetailRedirectRequest.setUserId("822b71784d6f497cb891626fac538a14");
		omsProductDetailRedirectRequest.setRequestFrom("web");
		LOGGER.info("第三方产品详情页面跳转：omsProductDetailRedirectRequest={}",JSONObject.toJSONString(omsProductDetailRedirectRequest));
		BaseResponse baseResponse = TestHelper.remote(OrgEnum.OPEN_IN_YOUXINQIANBAO_WEB,this.getUrl(PathEnum.PRERELEASE),"/openapi/oms/product/detail",null,omsProductDetailRedirectRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 开放平台接入-第三方投资记录页面跳转
	 * @throws Exception
	 */
	@Test
	public void investmentListRedirect() throws Exception {
		
		OmsInvestmentListRedirectRequest omsInvestmentListRedirectRequest = new OmsInvestmentListRedirectRequest();
		omsInvestmentListRedirectRequest.setUserId("822b71784d6f497cb891626fac538a14");
		omsInvestmentListRedirectRequest.setRequestFrom("web");
		LOGGER.info("第三方产品详情页面跳转：omsProductDetailRedirectRequest={}",JSONObject.toJSONString(omsInvestmentListRedirectRequest));
		BaseResponse baseResponse = TestHelper.remote(OrgEnum.OPEN_IN_YOUXINQIANBAO_WEB,this.getUrl(PathEnum.PRERELEASE),"/openapi/oms/investment/list",null,omsInvestmentListRedirectRequest);
		LOGGER.info(baseResponse.toString());
	}
}
