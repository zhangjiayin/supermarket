package demo.request;


public class OmsExistAccountRequest {
	/**
	 * 身份证
	 */
//	@NotBlank(message="身份证不能为空")
//	@Pattern(regexp="(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)")
	private String idCard;
	
	/**
	 * 手机号
	 */
//	@NotNull(message="手机号不能为空")
//	@Pattern(regexp="^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$")
	private String mobile;
	
	/**
	 * 短信认证验证码
	 */
//	@NotNull(message="短信认证验证码不能为空")
	private String msgCode;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
}
