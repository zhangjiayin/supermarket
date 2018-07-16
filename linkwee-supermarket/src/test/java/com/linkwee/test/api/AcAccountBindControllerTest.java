package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.api.request.acc.InitPayPwdRequest;
import com.linkwee.api.request.acc.InputVcodeRequest;
import com.linkwee.api.request.acc.MonthProfixDetailRequest;
import com.linkwee.api.request.acc.MyAccountPageListRequest;
import com.linkwee.api.request.acc.ResetPayPwdRequest;
import com.linkwee.api.request.acc.TwoPwdRequest;
import com.linkwee.api.request.acc.UserWithdrawRequest;
import com.linkwee.api.response.acc.AcAccountBindReponse;
import com.linkwee.api.response.acc.AcAccountTypeReponse;
import com.linkwee.api.response.acc.MonthProfixTotalListResponse;
import com.linkwee.api.response.acc.MyAccount;
import com.linkwee.api.response.acc.MyAccountPageListResponse;
import com.linkwee.api.response.acc.ProvinceInfoResponse;
import com.linkwee.api.response.acc.WithdrawApplyPageListResponse;
import com.linkwee.api.response.acc.WithdrawBankCardResponse;
import com.linkwee.api.response.crm.DirectCfpPageListResponse;
import com.linkwee.api.response.crm.LeaderProfitStatisticsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.acc.AcCityList;
import com.linkwee.web.model.acc.AcProvinceList;

public class AcAccountBindControllerTest extends BaseTest{
	
	
	@Test//修改交易密码
	public void modifyPayPwd() throws Exception {
		TwoPwdRequest ver = new TwoPwdRequest();
		ver.setNewPwd("123456");
		ver.setOldPwd("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/modifyPayPwd",this.getToken(),AcAccountTypeReponse.class,ver);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void queryBankName() throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("bankCard", "6217680300458176");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryBankName",this.getToken(),AcAccountTypeReponse.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//重置支付密码
	public void resetPayPwd() throws Exception {
		ResetPayPwdRequest ver = new ResetPayPwdRequest();
		ver.setResetPayPwdToken("y546q90h6rk3nflx");
		ver.setPwd("111111");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/resetPayPwd",this.getToken(),AcAccountTypeReponse.class,ver);
		LOGGER.debug(baseResponse.toString());
	}
	@Test//重置支付密码-输入手机验证(inputVcode)
	public void inputVcode() throws Exception {
		InputVcodeRequest ver = new InputVcodeRequest();
		ver.setVcode("3110");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/inputVcode",this.getToken(),AcAccountTypeReponse.class,ver);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//重置支付密码-点击手机发送验证码(sendVcode)
	public void sendVcode() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/sendVcode",this.getToken(),AcAccountTypeReponse.class,null);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//累计提现金额
	public void getWithdrawSummary() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/getWithdrawSummary",this.getToken(),AcAccountTypeReponse.class,null);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//账户类型 
	public void queryAccountType() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryAccountType",this.getToken(),AcAccountTypeReponse.class,null);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//提现记录明细  
	public void queryWithdrawLog() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryWithdrawLog",this.getToken(),WithdrawApplyPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test//提现请求  
	public void testuserWithdrawRequest() throws Exception {
		UserWithdrawRequest req = new UserWithdrawRequest();
		req.setUserName("陈佳良");
		req.setIdCard("44142219870510263X");
		req.setAmount("20");
		req.setBankCard("6226097805198232");
		req.setBankCode("CMB");
		req.setBankName("招商银行");
		req.setCity("深圳");
		req.setKaihuhang("南山科技园支行");
		req.setUserType(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/userWithdrawRequest",this.getToken(),WithdrawBankCardResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//提现查询银行卡信息
	public void testgetWithdrawBankCard() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/getWithdrawBankCard",this.getToken(),WithdrawBankCardResponse.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//账户明细  
	public void testMyaccountDetail() throws Exception {
		MyAccountPageListRequest req = new MyAccountPageListRequest();
		req.setPageIndex(2);
		req.setPageSize(10);
//		req.setUserType("2");
		req.setTypeValue("15");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/myaccountDetail/pageList",this.getToken(),MyAccountPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//我的账户
	public void testMyAccount() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/myaccount",this.getToken(),MyAccount.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//查询绑卡信息
	public void testUserBindCard() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/getUserBindCard",this.getToken(),AcAccountBindReponse.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test//检查用户是否设置支付密码
	public void verifyPayPwdState() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/verifyPayPwdState",this.getToken(),Map.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	
	
	@Test//检查用户是否设置支付密码
	public void initPayPwd() throws Exception {
		InitPayPwdRequest req = new InitPayPwdRequest();
		req.setPwd("123456");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/initPayPwd",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test//是否已经绑卡(个人设置)
	public void testPersoncenterSetting() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/personcenter/setting",this.getToken(),AcProvinceList.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//查省份
	public void testQueryAllProvince() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryAllProvince",this.getToken(),ProvinceInfoResponse.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//查城市
	public void testQueryCityByProvince() throws Exception {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("provinceId", "53");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/queryCityByProvince",this.getToken(),AcCityList.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//查询支付机构支持的银行
	public void testQueryAllBank() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/queryAllBank",this.getToken(),ProvinceInfoResponse.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//账户余额
	public void testAccountBalance() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/accountBalance",this.getToken(),null);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test// 账户余额月份收益总计列表
	public void testmonthProfixTotalList() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/monthProfixTotalList",this.getToken(),null);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//月度收益统计
	public void testmonthProfixStatistics() throws Exception {
		MonthProfixDetailRequest req = new  MonthProfixDetailRequest();
		req.setMonth("2016-11");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/monthProfixStatistics",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//月度收益明细列表
	public void testmonthProfixDetailList() throws Exception {
		MonthProfixDetailRequest req = new  MonthProfixDetailRequest();
		req.setMonth("2016-12");
		req.setProfixType("1");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/monthProfixDetailList",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//月度收益明细列表2.1
	public void testmonthProfixDetailListNew() throws Exception {
		MonthProfixDetailRequest req = new  MonthProfixDetailRequest();
		req.setMonth("2017-11");
		req.setProfixType("2");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/monthProfixDetailList/2.1",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test//4.5.4猎财余额  账单明细
	public void testAccountBalanceDetails() throws Exception {
		PaginatorRequest req = new  PaginatorRequest();
		req.setPageIndex(4);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/accountBalanceDetails",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//月度收益统计2.1
	public void testmonthProfixStatisticsNew() throws Exception {
		MonthProfixDetailRequest req = new  MonthProfixDetailRequest();
		req.setMonth("2017-04");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/monthProfixStatistics/2.1",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//月度收益统计3.0
	public void testmonthProfixStatisticsNew2() throws Exception {
		MonthProfixDetailRequest req = new  MonthProfixDetailRequest();
		req.setMonth("2017-11");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(),"/api/account/monthProfixStatistics/3.0",this.getToken(),Map.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test// 账户余额月份收益总计列表2.1
	public void testmonthProfixTotalListNew() throws Exception {
		MonthProfixDetailRequest req = new MonthProfixDetailRequest();
		req.setPageSize(10);
		req.setPageIndex(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/account/monthProfixTotalList/2.1",this.getToken(),MonthProfixTotalListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//T呗奖励余额
	public void testGetAccountBalance() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/getAccountBalance",this.getToken(),MyAccount.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//猎才大师—资金明细-账户余额
	public void testGetLieCaiBalance() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/getLieCaiBalance",this.getToken(),MyAccount.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//T呗奖励明细(收入、支出)
	public void testQueryRewardDetail() throws Exception {
		MyAccountPageListRequest req = new MyAccountPageListRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		req.setTypeValue("2");//奖励类型(1=奖励收入明细|2=奖励支出明细)
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryRewardDetail",this.getToken(),MyAccountPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//猎财大师收支明细
	public void testQueryIncomeAndOutDetail() throws Exception {
		MyAccountPageListRequest req = new MyAccountPageListRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		req.setTypeValue("2");//收支明细(0=全部1=收入明细|2=支出明细)
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryIncomeAndOutDetail",this.getToken(),MyAccountPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//提现记录明细  
	public void queryAllWithdraw() throws Exception {
		PaginatorRequest req = new PaginatorRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/account/queryAllWithdraw",this.getToken(),WithdrawApplyPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test//团队leader奖励-累计奖励
	public void testLeaderProfit() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/personcenter/partner/leaderProfit",this.getToken(),LeaderProfitStatisticsResponse.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test// 团队leader奖励-直属理财师团队
	public void testDirectCfpPageList() throws Exception {
		MyAccountPageListRequest req = new MyAccountPageListRequest();
		req.setPageIndex(2);
		req.setPageSize(7);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/personcenter/partner/directCfpPageList",this.getToken(),DirectCfpPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test//团队leader奖励-成员贡献明细
	public void testContribuPageList() throws Exception {
		MyAccountPageListRequest req = new MyAccountPageListRequest();
		req.setPageIndex(1);
		req.setPageSize(10);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/partner/contribuPageList",this.getToken(),DirectCfpPageListResponse.class,req);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	
	
	@Test//判断理财师leader奖励满足状态
	public void leaderProfitStatus() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/personcenter/partner/leaderProfitStatus",this.getToken(),LeaderProfitStatisticsResponse.class,new Object[]{});
		LOGGER.debug(baseResponse.toString());
	}
	
}
