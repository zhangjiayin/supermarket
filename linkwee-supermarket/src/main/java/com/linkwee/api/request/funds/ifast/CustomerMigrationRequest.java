package com.linkwee.api.request.funds.ifast;


public class CustomerMigrationRequest extends IfastBaseRequest {

	//证件姓名(投资者的姓名)	是否必须-是
	private String idName;
											
	//证件号码(投资者提供的证件号码，目前仅支持身份证号码验证)	是否必须-是
	private String idNumber;
											
	//证件类型(0=身份证，目前固定为0)	是否必须-是
	private String idType;
											
	//银行卡号(必须是借记卡号码)	是否必须-是
	private String bankNumber;
											
	//银行编号(商户可以在使用实名验证接口前，先调用银行列表接口获取最新的银行代码)	是否必须-是
	private String bankCode;
											
	//银行预留手机号码	是否必须-是
	private String telephoneNumber;
											
	//电子邮箱	是否必须-否
	private String email;
											
	//是否发送短信通知(true,false)	是否必须-否
	private String isNotify;

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsNotify() {
		return isNotify;
	}

	public void setIsNotify(String isNotify) {
		this.isNotify = isNotify;
	}
}
