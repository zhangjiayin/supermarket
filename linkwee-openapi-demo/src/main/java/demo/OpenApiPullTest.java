package demo;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import demo.enums.OrgEnum;
import demo.enums.RequestTypeEnums;
import demo.request.AccountBankRequest;
import demo.request.PullInvestRecordRequest;
import demo.request.PullProductRequest;
import demo.utils.CommonUtils;
import demo.utils.DateUtils;

/**
 * 
 * @author liqimoon
 *
 */
public class OpenApiPullTest{
	
	/**
	 * 整体说明
	 * ********该接口由领会提供      拉取产品模式	  **********
	 * 整体请使用junit测试调用，调用前请看下面修改参数说明
	 * 1：需根据不同的机构修改OrgEnum类下面的枚举类型,对应领会分配的ORG_NUMBER(机构编码),ORG_KEY(机构明钥),ORG_SECRET(机构私钥)
	 * 2：需根据不同的请求地址修改OpenApiTest类下面的常量openBaseUrl(请求路径),openOrgEnum(机构枚举,对应1)
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(OpenApiPullTest.class);
	/**
	 * 请求机构信息  请根据不同的机构进行修改
	 */
	private static OrgEnum openOrgEnum= OrgEnum.OPEN_LUJINSUO_WEB;
	/**
	 * 请求路径   需根据具体的请求地址作修改
	 */
	private static String linkweeBaseUrL = openOrgEnum.getLinkweeBaseUrL();   //领会测试环境联调地址
	
	private static String thirdBaseUrL = openOrgEnum.getThirdBaseUrL();   //第三方测试环境联调地址
	/**
	 * 请求方式   需根据具体的请求方式作修改
	 */
	private static RequestTypeEnums openRequestType = RequestTypeEnums.POST;
	
	/**
	 * 5.1产品拉取接口
	 */
	@Test
	public void pushProduct(){
		
		PullProductRequest pullProductRequest = new PullProductRequest();
//		pullProductRequest.setThirdProductId("xxxxx");
//		pullProductRequest.setStartTime(DateUtils.format(DateUtils.subDay(new Date(), 10)));
//		pullProductRequest.setEndTime(DateUtils.format(new Date()));
		
		LOGGER.info("产品拉取：pullProductRequest={}",JSONObject.toJSONString(pullProductRequest));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,thirdBaseUrL+"/product/pullProduct",pullProductRequest);//发送请求
	}
	
	/**
	 * 5.5投资记录拉取接口
	 */
	@Test
	public void pullInvestRecord(){
		
		PullInvestRecordRequest pullInvestRecordRequest = new PullInvestRecordRequest();
		pullInvestRecordRequest.setStartTime(DateUtils.format(DateUtils.subDay(new Date(), 1)));
		pullInvestRecordRequest.setEndTime(DateUtils.format(new Date()));
		LOGGER.info("投资记录拉取接口：pullInvestRecordRequest={}",JSONObject.toJSONString(pullInvestRecordRequest));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,thirdBaseUrL+"/invest/pullInvestRecord",pullInvestRecordRequest);//发送请求
	}
	
	/**
	 * 5.6投资回款拉取接口
	 */
	@Test
	public void pullRepaymentRecord(){
		
		PullInvestRecordRequest pullInvestRecordRequest = new PullInvestRecordRequest();
		pullInvestRecordRequest.setStartTime(DateUtils.format(DateUtils.subDay(new Date(), 1)));
		pullInvestRecordRequest.setEndTime(DateUtils.format(new Date()));
		LOGGER.info("投资回款拉取接口：pullInvestRecordRequest={}",JSONObject.toJSONString(pullInvestRecordRequest));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,thirdBaseUrL+"/invest/pullRepaymentRecord",pullInvestRecordRequest);//发送请求
	}
	
	/**
	 * 5.4投资客户的银行账户查询接口
	 */
	@Test
	public void accountBank(){
		
		AccountBankRequest accountBankRequest = new AccountBankRequest();
		accountBankRequest.setUserId("efdf40a3aa384e41a8d0cfa45fa2cda1");
		LOGGER.info("投资客户的银行账户查询接口：accountBankRequest={}",JSONObject.toJSONString(accountBankRequest));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,linkweeBaseUrL+"/account/bank",accountBankRequest);//发送请求
	}
	
}
