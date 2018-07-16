package com.linkwee.api.response.cim;

import java.util.ArrayList;

public class ProductPageListResponse extends ProductPageList4Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 标签列表-右上角
	 */
	private ArrayList<String> tagListRight;
	/**
	 * 标签列表-右上角新手标（区分样式）
	 */
	private ArrayList<String> tagListRightNewer;
	/**
	 * 是否理财师推荐	0-未推荐 非0-已推荐
	 */
	private String cfpRecommend;
	
	public ArrayList<String> getTagListRight() {
		return tagListRight;
	}
	public void setTagListRight(ArrayList<String> tagListRight) {
		this.tagListRight = tagListRight;
	}
	public ArrayList<String> getTagListRightNewer() {
		return tagListRightNewer;
	}
	public void setTagListRightNewer(ArrayList<String> tagListRightNewer) {
		this.tagListRightNewer = tagListRightNewer;
	}
	public String getCfpRecommend() {
		return cfpRecommend;
	}
	public void setCfpRecommend(String cfpRecommend) {
		this.cfpRecommend = cfpRecommend;
	}
}
