package linkwee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountCenterConfig {

	@Value("${ac.firstPaySendSms}")
	private String firstPaySendSms;
	
	@Value("${ac.firstBindCardPay}")
	private String firstBindCardPay;
	
	@Value("${ac.directPay}")
	private String directPay;
	
	@Value("${ac.systemInRecord}")
	private String systemInRecord;
	
	@Value("${ac.acceptUnipayNotice}")
	private String acceptUnipayNotice;
	
	@Value("${ac.inRecordListPage}")
	private String inRecordListPage;
	
	@Value("${ac.getAllTransType}")
	private String getAllTransType;
	
	@Value("${ac.quickPaySendSms}")
	private String quickPaySendSms;
	
	@Value("${ac.quickPay}")
	private String quickPay;
	
	@Value("${ac.investOrder}")
	private String investOrder;
	
	@Value("${ac.cancelInvestOrder}")
	private String cancelInvestOrder;
	
	@Value("${ac.confirmInvestOrder}")
	private String confirmInvestOrder;
	
	@Value("${ac.userInvestRecord}")
	private String userInvestRecord;
	
	@Value("${ac.investRecordListPage}")
	private String investRecordListPage;
	
	@Value("${ac.userWithdrawRecord}")
	private String userWithdrawRecord;
	
	@Value("${ac.outRecordListPage}")
	private String outRecordListPage;
	
	@Value("${ac.getUserOutSum}")
	private String getUserOutSum;
	
	@Value("${ac.getUserFee}")
	private String getUserFee;
	
	@Value("${ac.querySingleOutRecord}")
	private String querySingleOutRecord;
	
	@Value("${ac.outRecordExpectedReachTime}")
	private String outRecordExpectedReachTime;
	
	@Value("${ac.checkPayPassword}")
	private String checkPayPassword;
	
	@Value("${ac.checkPayStatus}")
	private String checkPayStatus;
	
	@Value("${ac.setPayPassword}")
	private String setPayPassword;
	
	@Value("${ac.resetPayPassword}")
	private String resetPayPassword;
	
	@Value("${ac.isSetPayPassword}")
	private String isSetPayPassword;
	
	@Value("${ac.getUserBindCard}")
	private String getUserBindCard;
	
	@Value("${ac.queryUserSettleInfo}")
	private String queryUserSettleInfo;
	
	@Value("${ac.queryBankList}")
	private String queryBankList;
	
	@Value("${ac.searchCardInfo}")
	private String searchCardInfo;
	
	@Value("${ac.authSendSms}")
	private String authSendSms;
	
	@Value("${ac.auth}")
	private String auth;
	
	@Value("${ac.queryAllProvince}")
	private String queryAllProvince;
	
	@Value("${ac.queryCityByProvince}")
	private String queryCityByProvince;
	
	@Value("${ac.getUserBindCardNew}")
	private String getUserBindCardNew;
	
	@Value("${ac.userReturnRecord}")
	private String userReturnRecord;
	
	@Value("${ac.transferReturn}")
	private String transferReturn;
	
	@Value("${ac.userTransfer}")
	private String userTransfer;
	
	@Value("${ac.getUserAsset}")
	private String getUserAsset;
	
	@Value("${ac.getBalanceRecord}")
	private String getBalanceRecord;
	
	@Value("${ac.createUserAsset}")
	private String createUserAsset;
	
	@Value("${ac.queryBalanceSum}")
	private String queryBalanceSum;
	
	@Value("${ac.saveAuthentication}")
	private String saveAuthentication;
	
	@Value("${ac.queryAuthentication}")
	private String queryAuthentication;
	
	@Value("${ac.investOrder.tc}")
	private String investOrderTC;
	
	@Value("${ac.cancelInvestOrder.tc}")
	private String cancelInvestOrderTC;
	
	@Value("${ac.confirmInvestOrder.tc}")
	private String confirmInvestOrderTC;
	
	@Value("${ac.userInvestRecord.tc}")
	private String userInvestRecordTC;
	
	@Value("${ac.investRecordListPage.tc}")
	private String investRecordListPageTC;

	public String getFirstPaySendSms() {
		return firstPaySendSms;
	}

	public void setFirstPaySendSms(String firstPaySendSms) {
		this.firstPaySendSms = firstPaySendSms;
	}

	public String getFirstBindCardPay() {
		return firstBindCardPay;
	}

	public void setFirstBindCardPay(String firstBindCardPay) {
		this.firstBindCardPay = firstBindCardPay;
	}

	public String getDirectPay() {
		return directPay;
	}

	public void setDirectPay(String directPay) {
		this.directPay = directPay;
	}

	public String getSystemInRecord() {
		return systemInRecord;
	}

	public void setSystemInRecord(String systemInRecord) {
		this.systemInRecord = systemInRecord;
	}

	public String getAcceptUnipayNotice() {
		return acceptUnipayNotice;
	}

	public void setAcceptUnipayNotice(String acceptUnipayNotice) {
		this.acceptUnipayNotice = acceptUnipayNotice;
	}

	public String getInRecordListPage() {
		return inRecordListPage;
	}

	public void setInRecordListPage(String inRecordListPage) {
		this.inRecordListPage = inRecordListPage;
	}

	public String getGetAllTransType() {
		return getAllTransType;
	}

	public void setGetAllTransType(String getAllTransType) {
		this.getAllTransType = getAllTransType;
	}

	public String getQuickPaySendSms() {
		return quickPaySendSms;
	}

	public void setQuickPaySendSms(String quickPaySendSms) {
		this.quickPaySendSms = quickPaySendSms;
	}

	public String getQuickPay() {
		return quickPay;
	}

	public void setQuickPay(String quickPay) {
		this.quickPay = quickPay;
	}

	public String getInvestOrder() {
		return investOrder;
	}

	public void setInvestOrder(String investOrder) {
		this.investOrder = investOrder;
	}

	public String getCancelInvestOrder() {
		return cancelInvestOrder;
	}

	public void setCancelInvestOrder(String cancelInvestOrder) {
		this.cancelInvestOrder = cancelInvestOrder;
	}

	public String getConfirmInvestOrder() {
		return confirmInvestOrder;
	}

	public void setConfirmInvestOrder(String confirmInvestOrder) {
		this.confirmInvestOrder = confirmInvestOrder;
	}

	public String getUserInvestRecord() {
		return userInvestRecord;
	}

	public void setUserInvestRecord(String userInvestRecord) {
		this.userInvestRecord = userInvestRecord;
	}

	public String getInvestRecordListPage() {
		return investRecordListPage;
	}

	public void setInvestRecordListPage(String investRecordListPage) {
		this.investRecordListPage = investRecordListPage;
	}

	public String getUserWithdrawRecord() {
		return userWithdrawRecord;
	}

	public void setUserWithdrawRecord(String userWithdrawRecord) {
		this.userWithdrawRecord = userWithdrawRecord;
	}

	public String getOutRecordListPage() {
		return outRecordListPage;
	}

	public void setOutRecordListPage(String outRecordListPage) {
		this.outRecordListPage = outRecordListPage;
	}

	public String getGetUserOutSum() {
		return getUserOutSum;
	}

	public void setGetUserOutSum(String getUserOutSum) {
		this.getUserOutSum = getUserOutSum;
	}

	public String getGetUserFee() {
		return getUserFee;
	}

	public void setGetUserFee(String getUserFee) {
		this.getUserFee = getUserFee;
	}

	public String getQuerySingleOutRecord() {
		return querySingleOutRecord;
	}

	public void setQuerySingleOutRecord(String querySingleOutRecord) {
		this.querySingleOutRecord = querySingleOutRecord;
	}

	public String getOutRecordExpectedReachTime() {
		return outRecordExpectedReachTime;
	}

	public void setOutRecordExpectedReachTime(String outRecordExpectedReachTime) {
		this.outRecordExpectedReachTime = outRecordExpectedReachTime;
	}

	public String getCheckPayPassword() {
		return checkPayPassword;
	}

	public void setCheckPayPassword(String checkPayPassword) {
		this.checkPayPassword = checkPayPassword;
	}

	public String getCheckPayStatus() {
		return checkPayStatus;
	}

	public void setCheckPayStatus(String checkPayStatus) {
		this.checkPayStatus = checkPayStatus;
	}

	public String getSetPayPassword() {
		return setPayPassword;
	}

	public void setSetPayPassword(String setPayPassword) {
		this.setPayPassword = setPayPassword;
	}

	public String getResetPayPassword() {
		return resetPayPassword;
	}

	public void setResetPayPassword(String resetPayPassword) {
		this.resetPayPassword = resetPayPassword;
	}

	public String getIsSetPayPassword() {
		return isSetPayPassword;
	}

	public void setIsSetPayPassword(String isSetPayPassword) {
		this.isSetPayPassword = isSetPayPassword;
	}

	public String getGetUserBindCard() {
		return getUserBindCard;
	}

	public void setGetUserBindCard(String getUserBindCard) {
		this.getUserBindCard = getUserBindCard;
	}

	public String getQueryUserSettleInfo() {
		return queryUserSettleInfo;
	}

	public void setQueryUserSettleInfo(String queryUserSettleInfo) {
		this.queryUserSettleInfo = queryUserSettleInfo;
	}

	public String getQueryBankList() {
		return queryBankList;
	}

	public void setQueryBankList(String queryBankList) {
		this.queryBankList = queryBankList;
	}

	public String getSearchCardInfo() {
		return searchCardInfo;
	}

	public void setSearchCardInfo(String searchCardInfo) {
		this.searchCardInfo = searchCardInfo;
	}

	public String getAuthSendSms() {
		return authSendSms;
	}

	public void setAuthSendSms(String authSendSms) {
		this.authSendSms = authSendSms;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getQueryAllProvince() {
		return queryAllProvince;
	}

	public void setQueryAllProvince(String queryAllProvince) {
		this.queryAllProvince = queryAllProvince;
	}

	public String getQueryCityByProvince() {
		return queryCityByProvince;
	}

	public void setQueryCityByProvince(String queryCityByProvince) {
		this.queryCityByProvince = queryCityByProvince;
	}

	public String getGetUserBindCardNew() {
		return getUserBindCardNew;
	}

	public void setGetUserBindCardNew(String getUserBindCardNew) {
		this.getUserBindCardNew = getUserBindCardNew;
	}

	public String getUserReturnRecord() {
		return userReturnRecord;
	}

	public void setUserReturnRecord(String userReturnRecord) {
		this.userReturnRecord = userReturnRecord;
	}

	public String getTransferReturn() {
		return transferReturn;
	}

	public void setTransferReturn(String transferReturn) {
		this.transferReturn = transferReturn;
	}

	public String getUserTransfer() {
		return userTransfer;
	}

	public void setUserTransfer(String userTransfer) {
		this.userTransfer = userTransfer;
	}

	public String getGetUserAsset() {
		return getUserAsset;
	}

	public void setGetUserAsset(String getUserAsset) {
		this.getUserAsset = getUserAsset;
	}

	public String getGetBalanceRecord() {
		return getBalanceRecord;
	}

	public void setGetBalanceRecord(String getBalanceRecord) {
		this.getBalanceRecord = getBalanceRecord;
	}

	public String getCreateUserAsset() {
		return createUserAsset;
	}

	public void setCreateUserAsset(String createUserAsset) {
		this.createUserAsset = createUserAsset;
	}

	public String getQueryBalanceSum() {
		return queryBalanceSum;
	}

	public void setQueryBalanceSum(String queryBalanceSum) {
		this.queryBalanceSum = queryBalanceSum;
	}

	public String getSaveAuthentication() {
		return saveAuthentication;
	}

	public void setSaveAuthentication(String saveAuthentication) {
		this.saveAuthentication = saveAuthentication;
	}

	public String getQueryAuthentication() {
		return queryAuthentication;
	}

	public void setQueryAuthentication(String queryAuthentication) {
		this.queryAuthentication = queryAuthentication;
	}

	public String getInvestOrderTC() {
		return investOrderTC;
	}

	public void setInvestOrderTC(String investOrderTC) {
		this.investOrderTC = investOrderTC;
	}

	public String getCancelInvestOrderTC() {
		return cancelInvestOrderTC;
	}

	public void setCancelInvestOrderTC(String cancelInvestOrderTC) {
		this.cancelInvestOrderTC = cancelInvestOrderTC;
	}

	public String getConfirmInvestOrderTC() {
		return confirmInvestOrderTC;
	}

	public void setConfirmInvestOrderTC(String confirmInvestOrderTC) {
		this.confirmInvestOrderTC = confirmInvestOrderTC;
	}

	public String getUserInvestRecordTC() {
		return userInvestRecordTC;
	}

	public void setUserInvestRecordTC(String userInvestRecordTC) {
		this.userInvestRecordTC = userInvestRecordTC;
	}

	public String getInvestRecordListPageTC() {
		return investRecordListPageTC;
	}

	public void setInvestRecordListPageTC(String investRecordListPageTC) {
		this.investRecordListPageTC = investRecordListPageTC;
	}
	
}
