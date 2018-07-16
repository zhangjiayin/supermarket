package demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import demo.enums.OrgEnum;
import demo.enums.RequestTypeEnums;
import demo.request.OmsAccountBindRequest;
import demo.request.OmsAccountExistRequest;
import demo.request.OmsExistAccountRequest;
import demo.request.OmsInvestmentListRedirectRequest;
import demo.request.OmsProductDetailRedirectRequest;
import demo.request.PaginatorRequest;
import demo.utils.CommonUtils;

public class OpenOmsTest {

	/**
	 * 整体说明
	 * ********该接口由领会提供      接入领会产品模式	  **********
	 * 整体请使用junit测试调用，调用前请看下面修改参数说明
	 * 1：需根据不同的机构修改OrgEnum类下面的枚举类型,对应领会分配的ORG_NUMBER(机构编码),ORG_KEY(机构明钥),ORG_SECRET(机构私钥)
	 * 2：需根据不同的请求地址修改OpenApiTest类下面的常量openBaseUrl(请求路径),openOrgEnum(机构枚举,对应1)
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(OpenApiPushTest.class);
	/**
	 * 请求机构信息  请根据不同的机构进行修改
	 */
	private static OrgEnum openOrgEnum= OrgEnum.OPEN_IN_YOUXINQIANBAO_WEB;
	/**
	 * 请求路径   需根据具体的请求地址作修改
	 */
	private static String openBaseUrl = openOrgEnum.getLinkweeBaseUrL();   //领会测试环境联调地址
	/**
	 * 请求方式   需根据具体的请求方式作修改
	 */
	private static RequestTypeEnums openRequestType = RequestTypeEnums.POST;
	
	/**
	 * 4.1 获取领会产品列表接口
	 */
	@Test
	public void productList(){
		
		PaginatorRequest paginatorRequest = new PaginatorRequest();
		paginatorRequest.setPageIndex(1);
		paginatorRequest.setPageSize(10);
		LOGGER.info("开放平台接入-获取领会产品列表：paginatorRequest={}",JSONObject.toJSONString(paginatorRequest));
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/oms/product/list",paginatorRequest);//发送请求
	}
	
	/**
	 * 4.2查询用户是否已存在接口
	 */
	@Test
	public void accountExist(){
		
		OmsAccountExistRequest omsAccountExistRequest = new OmsAccountExistRequest();
		omsAccountExistRequest.setIdCard("430626197312227422");
		omsAccountExistRequest.setMobile("13622606750");
		omsAccountExistRequest.setIfNeedOldBind(0);
		LOGGER.info("开放平台接入-查询用户是否已存在接口：accountExistRequest={}",JSONObject.toJSONString(omsAccountExistRequest));
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/oms/account/exist",omsAccountExistRequest);//发送请求
	}
	
	/**
	 * 4.3绑定已存在老用户接口
	 */
	@Test
	public void existAccountBind(){
		
		OmsExistAccountRequest omsExistAccountRequest = new OmsExistAccountRequest();
		omsExistAccountRequest.setIdCard("430626197312227422");
		omsExistAccountRequest.setMobile("13622606750");
		omsExistAccountRequest.setMsgCode("123456abcd");;
		LOGGER.info("开放平台接入-绑定已存在老用户接口：omsExistAccountRequest={}",JSONObject.toJSONString(omsExistAccountRequest));
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/oms/account/old_account_bind",omsExistAccountRequest);//发送请求
	}
	
	/**
	 * 4.4绑定领会平台账户接口(新用户)
	 */
	@Test
	public void accountBind(){
		
		OmsAccountBindRequest omsAccountBindRequest = new OmsAccountBindRequest();
		omsAccountBindRequest.setBankCard("6222601854221454712151");
		omsAccountBindRequest.setBankCode("PAB");
		omsAccountBindRequest.setBankName("平安银行");
		omsAccountBindRequest.setIdCard("429510198806451324");
		omsAccountBindRequest.setMobile("15013674554");
		omsAccountBindRequest.setUserName("刘星");
		LOGGER.info("开放平台接入-绑定领会平台账户：omsAccountBindRequest={}",JSONObject.toJSONString(omsAccountBindRequest));
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/oms/account/bind",omsAccountBindRequest);//发送请求
	}
	
	/**
	 * 4.5产品详情页面接口（页面form表单跳转）
	 * 
	 * 备注：请求返回结果为空   为正常现象,表明签名验证及整个流程都正常,只是在领会后端进行了页面的重定向处理   不做任何返回
	 */
	@Test
	public void productDetailRedirect(){
		
		OmsProductDetailRedirectRequest omsProductDetailRedirectRequest = new OmsProductDetailRedirectRequest();
		omsProductDetailRedirectRequest.setProductId("37C7047D41394775B989CCDDBAAE8506");
		omsProductDetailRedirectRequest.setUserId("822b71784d6f497cb891626fac538a14");
		omsProductDetailRedirectRequest.setRequestFrom("web");
		LOGGER.info("开放平台接入-产品详情页面form表单跳转：omsProductDetailRedirectRequest={}",JSONObject.toJSONString(omsProductDetailRedirectRequest));
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/oms/account/product/detail",omsProductDetailRedirectRequest);//发送请求
	}
	
	/**
	 * 4.6投资记录页面（页面form表单）
	 * 
	 * 备注：请求返回结果为空   为正常现象,表明签名验证及整个流程都正常,只是在领会后端进行了页面的重定向处理  不做任何返回
	 */
	@Test
	public void investmentListRedirect(){
		
		OmsInvestmentListRedirectRequest omsInvestmentListRedirectRequest = new OmsInvestmentListRedirectRequest();
		omsInvestmentListRedirectRequest.setUserId("822b71784d6f497cb891626fac538a14");
		omsInvestmentListRedirectRequest.setRequestFrom("web");
		LOGGER.info("开放平台接入-投资记录页面form表单跳转：omsInvestmentListRedirectRequest={}",JSONObject.toJSONString(omsInvestmentListRedirectRequest));
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/oms/account/investment/list",omsInvestmentListRedirectRequest);//发送请求
	}
}
