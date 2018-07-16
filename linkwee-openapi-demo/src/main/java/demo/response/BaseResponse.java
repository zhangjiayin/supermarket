package demo.response;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：api返回数据
 *
 * @author Bob
 * @时间  2015年7月31日上午10:16:16
 *
 */
public class BaseResponse  implements Serializable{

	private static final long serialVersionUID = -7671756385477179547L;
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);
	
	/**
	 * api返回码
	 */
	private String code;
	/**
	 * api返回消息
	 */
	private String msg;
	
	public BaseResponse() {
		
	}
	public BaseResponse(int code, String msg) {
		this.code = code+"";
		this.msg = msg;
	}
	public BaseResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
