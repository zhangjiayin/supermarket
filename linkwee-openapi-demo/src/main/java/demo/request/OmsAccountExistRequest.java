package demo.request;


public class OmsAccountExistRequest {

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
	 * 0-不需要  1-需要  默认为0
	 */
//	@Range(min=0,max=1,message="是否需要绑定老用户必需在0-1之间")
	private Integer ifNeedOldBind = 0;

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

	public Integer getIfNeedOldBind() {
		return ifNeedOldBind;
	}

	public void setIfNeedOldBind(Integer ifNeedOldBind) {
		this.ifNeedOldBind = ifNeedOldBind;
	}
}
