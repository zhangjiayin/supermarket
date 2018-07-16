package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.enums.ResultMsgEnum;

public class CommonRlt<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1298492507295210421L;

	private Integer returnCode;

	private String returnMsg;

	private T data;

	public CommonRlt() {
	}

	public CommonRlt(Integer returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public CommonRlt(ResultMsgEnum errorMsgEnum) {
		this.returnCode = errorMsgEnum.getReturnCode();
		this.returnMsg = errorMsgEnum.getReturnMsg();
	}
	
	public void setResultMsgEnum(ResultMsgEnum errorMsgEnum){
		if(errorMsgEnum != null){
			this.returnCode = errorMsgEnum.getReturnCode();
			this.returnMsg = errorMsgEnum.getReturnMsg();
		}
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
