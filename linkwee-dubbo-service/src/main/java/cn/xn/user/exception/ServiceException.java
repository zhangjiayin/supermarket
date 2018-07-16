package cn.xn.user.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -7115364097555754880L;

	private String errorCode;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ServiceException(String errorCode, String cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
