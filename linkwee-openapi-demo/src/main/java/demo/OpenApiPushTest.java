package demo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import demo.enums.OrgEnum;
import demo.enums.RequestTypeEnums;
import demo.request.AccountBankRequest;
import demo.request.InvestRecordReq;
import demo.request.ProductPushRequest;
import demo.request.ProductUpdatRequest;
import demo.request.RepaymentRecordReq;
import demo.utils.CommonUtils;
import demo.utils.DateUtils;
import demo.utils.StringUtils;

/**
 * 
 * @author liqimoon
 *
 */
public class OpenApiPushTest{
	
	/**
	 * 整体说明
	 * ********该接口由领会提供      推送产品模式	  **********
	 * 整体请使用junit测试调用，调用前请看下面修改参数说明
	 * 1：需根据不同的机构修改OrgEnum类下面的枚举类型,对应领会分配的ORG_NUMBER(机构编码),ORG_KEY(机构明钥),ORG_SECRET(机构私钥)
	 * 2：需根据不同的请求地址修改OpenApiTest类下面的常量openBaseUrl(请求路径),openOrgEnum(机构枚举,对应1)
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(OpenApiPushTest.class);
	/**
	 * 请求机构信息  请根据不同的机构进行修改
	 */
	private static OrgEnum openOrgEnum= OrgEnum.OPEN_LUJINSUO_WEB;
	/**
	 * 请求路径   需根据具体的请求地址作修改
	 */
	private static String openBaseUrl = openOrgEnum.getLinkweeBaseUrL();   //领会测试环境联调地址
	/**
	 * 请求方式   需根据具体的请求方式作修改
	 */
	private static RequestTypeEnums openRequestType = RequestTypeEnums.POST;
	
	/**
	 * 5.1产品推送接口
	 */
	@Test
	public void pushProduct(){
		
		ProductPushRequest productPushRequest = new ProductPushRequest();
		productPushRequest.setThirdProductId("ljs117222");
		productPushRequest.setProductDesc("187");
		productPushRequest.setProductName("887");
		productPushRequest.setProductType(999);
		productPushRequest.setBuyedTotalMoney(new BigDecimal(2500000.05));
		productPushRequest.setBuyedTotalPeople(30);
		productPushRequest.setBuyIncreaseMoney(new BigDecimal(100.05));
		productPushRequest.setBuyMaxMoney(new BigDecimal(1000000.05));
		productPushRequest.setBuyMinMoney(new BigDecimal(100.05));
		productPushRequest.setBuyTotalMoney(new BigDecimal(10000000.05));
		productPushRequest.setIsCollect(1);
		productPushRequest.setCollectBeginTime(DateUtils.format(new Date()));
		productPushRequest.setCollectEndTime(DateUtils.format(new Date()));
		productPushRequest.setIsFlow(2);
		productPushRequest.setFlowMaxRate(new BigDecimal(10.88));
		productPushRequest.setFlowMinRate(new BigDecimal(2.88));
		productPushRequest.setInterestWay(2);
		productPushRequest.setIsQuota(2);
		productPushRequest.setIsRedemption(3);
		productPushRequest.setRedemptionTime(30);
		productPushRequest.setAssignmentTime(60);
		productPushRequest.setMoneyType(1);
		productPushRequest.setRepaymentWay(5);
		productPushRequest.setRiskControlType(1);
		productPushRequest.setRiskLevel(1);
		productPushRequest.setValidBeginDate(DateUtils.format(new Date()));
		productPushRequest.setValidEndDate(DateUtils.format(new Date()));
		productPushRequest.setIsFixedDeadline(2);
		productPushRequest.setDeadLineMinValue(90);
		productPushRequest.setDeadLineMaxValue(150);
		productPushRequest.setDeadLineMinSelfDefined("1个月");
		productPushRequest.setDeadLineMaxSelfDefined("2个月");
		productPushRequest.setIsHaveProgress(0);
		productPushRequest.setSaleStartTime(DateUtils.format(new Date()));
		productPushRequest.setIfRookie(2);
		LOGGER.info("推送产品：productSendRequestNew={}",JSONObject.toJSONString(productPushRequest));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/product/pushProduct",productPushRequest);//发送请求
	}
	
	
	/**
	 * 5.2产品更新接口
	 */
	@Test
	public void updateProduct(){
		
		ProductUpdatRequest productUpdatRequest = new ProductUpdatRequest();
		productUpdatRequest.setBuyedTotalMoney(new BigDecimal("0"));
		productUpdatRequest.setBuyedTotalPeople(35);
		productUpdatRequest.setThirdProductId("DQ2000");
		productUpdatRequest.setStatus(1);
	//	productUpdatRequest.setFlowMinRate(new BigDecimal(13.5));
	//	productUpdatRequest.setFlowMaxRate(new BigDecimal(13.5));
//		productUpdatRequest.setSaleEndTime(DateUtils.format(new Date()));
//		productUpdatRequest.setBuyTotalMoney(new BigDecimal(222220000.05));
		LOGGER.info("产品更新接口：productUpdatRequest={}",JSONObject.toJSONString(productUpdatRequest));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/product/updateProduct",productUpdatRequest);//发送请求
	}
	
	
	/**
	 * 5.4投资客户的银行账户查询接口
	 */
	@Test
	public void accountBank(){
		
		AccountBankRequest accountBankRequest = new AccountBankRequest();
		accountBankRequest.setUserId("efdf40a3aa384e41a8d0cfa45fa2cda1");
		LOGGER.info("投资客户的银行账户查询接口：accountBankRequest={}",JSONObject.toJSONString(accountBankRequest));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/account/bank",accountBankRequest);//发送请求
	}
	
	/**
	 * 5.5投资记录推送接口
	 */
	@Test
	public void pushInvestRecord(){
		
		Random random = new Random();
		InvestRecordReq investRecordReq =null;
		investRecordReq = new InvestRecordReq();
		investRecordReq.setInvestId(StringUtils.getUUID());
		investRecordReq.setUserId("995e2df93fb3451186088158d95f3268");
		investRecordReq.setTxId("none");
		investRecordReq.setInvestStartTime(DateUtils.format(new Date()));
		investRecordReq.setInvestEndTime(DateUtils.format(DateUtils.addDay(new Date(), 180)));
		investRecordReq.setProductId("00A54305-9F61-4CCB-9DD1-E59EE4F11F64");
		investRecordReq.setInvestAmount(new BigDecimal(random.nextInt(100000)));
		investRecordReq.setProfit(new BigDecimal(2400d));
		investRecordReq.setPlatfromId("7777777");
		LOGGER.info("投资记录推送接口：investRecordReq={}",JSONObject.toJSONString(investRecordReq));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/invest/pushInvestRecord",investRecordReq);//发送请求
	}
	
	/**
	 * 5.6投资回款推送接口
	 */
	@Test
	public void pushRepaymentRecord(){
		
		RepaymentRecordReq repaymentRecordReq = new RepaymentRecordReq();
		repaymentRecordReq.setUserId("11db98256efd4b8f88fa1016e432bd80");
		repaymentRecordReq.setRepaymentId(StringUtils.getUUID());
		repaymentRecordReq.setInvestId("e62221ccc12d42cb9566eedede690c69");
		repaymentRecordReq.setProductId("123456789");
		repaymentRecordReq.setRepaymentAmount(new BigDecimal(-0.83d));
		repaymentRecordReq.setProfit(new BigDecimal(500d));
		repaymentRecordReq.setRepaymentTime(DateUtils.format(DateUtils.addDay(new Date(), 180)));
		repaymentRecordReq.setStatus(2);
		LOGGER.info("投资回款推送接口：repaymentRecordReq={}",JSONObject.toJSONString(repaymentRecordReq));
		
		CommonUtils.httpRequest(openOrgEnum,openRequestType,openBaseUrl+"/invest/pushRepaymentRecord",repaymentRecordReq);//发送请求
	}
}
