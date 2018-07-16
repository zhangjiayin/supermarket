package demo.response;

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
public class SuccessResponse<T> extends BaseResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * api返回数据 
	 */
	private T data;
	
	public SuccessResponse() {
		super("0","success");
	}

	public SuccessResponse(T data) {
		super("0","success");
		this.data = data;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
