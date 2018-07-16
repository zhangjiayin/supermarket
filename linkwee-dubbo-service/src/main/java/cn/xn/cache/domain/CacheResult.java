package cn.xn.cache.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CacheResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int returnCode = 0; 				//错误码，0为成功，其他为失败, 该字段一定会返回
	private String returnMsg;					//错误消息
	private T data;//数据
	
	public CacheResult(){
		
	}
	
	public CacheResult(CacheCodeEnum codeEnum){
		this.returnCode = codeEnum.getReturnCode();
		this.returnMsg = codeEnum.getReturnMsg();
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
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
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
