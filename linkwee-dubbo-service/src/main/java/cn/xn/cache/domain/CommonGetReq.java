package cn.xn.cache.domain;

public class CommonGetReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bid;
	private String key;
	private String value;
	private int expire;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
}
