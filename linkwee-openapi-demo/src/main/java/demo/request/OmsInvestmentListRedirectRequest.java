package demo.request;


public class OmsInvestmentListRedirectRequest {

	/**
	 * 领会用户标识
	 */
//	@NotNull(message="领会用户标识不能为空")
	private String userId;
	
	/**
	 * 请求来源   web：PC端  wap：移动端
	 */
//	@NotNull(message="请求来源不能为空")
//	@Pattern(regexp="^(web|wap)$")
	private String requestFrom;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
}
