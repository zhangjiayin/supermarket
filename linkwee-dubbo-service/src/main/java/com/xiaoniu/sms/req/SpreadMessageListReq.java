package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 推广消息列表 参数
 * @author 颜彩云
 *
 */
public class SpreadMessageListReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315753490753534981L;
	private int currPage = 1;
	private int pageSize = 200;
	private String userId;
	private String mobile;
	private boolean isHistory = true;

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean getIsHistory() {
		return this.isHistory;
	}

	public void setHistory(boolean isHistory) {
		this.isHistory = isHistory;
	}

	public boolean check(){
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		return true;
	}
}
