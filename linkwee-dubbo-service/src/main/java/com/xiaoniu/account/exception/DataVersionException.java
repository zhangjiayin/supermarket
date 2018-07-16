package com.xiaoniu.account.exception;

public class DataVersionException extends RuntimeException {

	private static final long serialVersionUID = -7115364097555754880L;

	private String errorCode;

	public DataVersionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataVersionException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public DataVersionException(String errorCode, String cause) {
		super(cause);
	}
	
	public DataVersionException(String cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
