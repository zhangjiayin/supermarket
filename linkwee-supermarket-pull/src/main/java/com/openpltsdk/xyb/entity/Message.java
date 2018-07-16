package com.openpltsdk.xyb.entity;

/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：Message
 * 类描述：交互实体
 * @author zenggang
 * @date 创建时间：2017年1月11日 上午11:25:27
 * 修改人：zenggang
 * 修改时间：2017年1月11日 上午11:25:27
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class Message {
	/**
	 * data:加密ServiceData的数据
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:27:47
	 * @version V1.3.1
	 */
	private String data;
    /**
     * timestamp:时间戳
     * @author zenggang
     * @date 创建时间：2017年1月11日 上午11:27:52
     * @version V1.3.1
     */
    private Long timestamp;
    /**
     * nonce:随机字符串
     * @author zenggang
     * @date 创建时间：2017年1月11日 上午11:27:59
     * @version V1.3.1
     */
    private String nonce;
    /**
     * signature:签名
     * @author zenggang
     * @date 创建时间：2017年1月11日 上午11:28:06
     * @version V1.3.1
     */
    private String signature;
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getSignature() {
        return signature;
    }
    
	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

    public String getData() {
        return data;
    }
}
