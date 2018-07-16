package cn.xn.cache.domain;

public enum CacheCodeEnum {

	SUCCESS					(0, 	"成功"),
	PARAM_ERROR				(180001, 	"参数错误"), 
	SIGN_ERROR				(180002,	"签名错误"),
	SYSTEM_ERROR			(180003, 	"系统异常"),
	;
	
	private Integer returnCode;
	
	private String returnMsg;

	private CacheCodeEnum(Integer returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}
	
	public Integer getReturnCode() {
		return returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public static CacheCodeEnum getEnum(Integer errorCode) {
		CacheCodeEnum[] arr = CacheCodeEnum.values();
		for (CacheCodeEnum tmp : arr) {
			if (errorCode == tmp.getReturnCode()) {
				return tmp;
			}
		}
		return SUCCESS;
	}

}

