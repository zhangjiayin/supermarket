package cn.xn.cache.domain;

import java.io.Serializable;
import java.util.Date;

public class Param implements Serializable{
	private static final long serialVersionUID = 238051395452414316L;
	private String bid;
	private String uid;
	private String tokenId;
	private String source;
	private Date date;

	public Param() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("|bid:").append(bid).append("|uid:").append(uid).append("|tokenid:").append(tokenId);
		return sb.toString();
	}

}
